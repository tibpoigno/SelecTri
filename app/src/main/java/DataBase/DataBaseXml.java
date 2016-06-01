package DataBase;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

import Cartographie.Position;
import PointDeCollecte.BacDmr;
import PointDeCollecte.BacRecyclable;
import PointDeCollecte.BacVerre;
import PointDeCollecte.Dechetterie;
import PointDeCollecte.PointDeCollecte;
import PointDeCollecte.PointRecyclagePile;
import PointDeCollecte.RelaiHabits;

// Debug
// Json

/**
 * Created by Thibault on 17/05/2016.
 */

/*
 * Implémente la lecture de Documents xml (objets DOM)
 * et la création de points de collecte suivant le type de bac rechercher
 */
public class DataBaseXml
{
    protected FragmentActivity context;
    protected Document bdd;
    public Vector<PointDeCollecte> output;
    protected String ville;

    public DataBaseXml(FragmentActivity _context,Document _bdd, String _ville) // Pour tests seulement...
    {
        this.context = _context;
        ville=_ville;
        bdd = _bdd;
    }
    public Vector<PointDeCollecte> getBacs(String type)
    {
        return parseBdd(type);
    }

    /*
     * Parse le document Dom obtenu avec le compilateurXML,
     * puis renvoit les objets correspondants aux données lues.
     */
    private Vector<PointDeCollecte> parseBdd(String type)
    {
        Vector<PointDeCollecte> bacs=null;
        try
        {
            Element nList =(Element) bdd.getElementsByTagName(type).item(0);

            NodeList nBacs = nList.getElementsByTagName("pdt");

            bacs = new Vector<PointDeCollecte>();

                    for (int i=0; i<nBacs.getLength(); i++)
                    {
                        Node bacNode = nBacs.item(i);

                        if (bacNode.getNodeType() == Node.ELEMENT_NODE)
                        {
                            Element element = (Element) bacNode;
                            double latitude = Double.parseDouble(getValue("latitude", element));
                            double longitude = Double.parseDouble(getValue("longitude", element));
                            //Log.d("BAC N°" + i, "  --> Latitude = " + latitude + " et Longitude = " + longitude);
                            switch (type)
                            {
                                case "recyclable":
                                    bacs.addElement(new BacRecyclable(latitude,longitude,false));
                                    break;
                                case "dmr":
                                    bacs.addElement(new BacDmr(latitude,longitude,false));
                                    break;
                                case "verre":
                                    bacs.addElement(new BacVerre(latitude,longitude,false));
                                    break;
                                case "textile":
                                    bacs.addElement(new RelaiHabits(latitude,longitude,false));
                                    break;
                                case "pile":
                                    bacs.addElement(new PointRecyclagePile(latitude,longitude,false));
                                    break;
                                case "decheterie":
                                    bacs.addElement(new Dechetterie(latitude,longitude,false));
                                    break;
                                default:Log.d("ERREUR : parseBdd()", "PB POUR CREATION OBJET...");
                            }
                        }
                    }
        }
        catch (Exception e) {e.printStackTrace();}
        output = bacs;
        return bacs;
    }
    /*
     * Extraction de la valeur d'une entrée XML
     */
    private static String getValue(String tag, Element element)
    {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
    /*
     * Calcule la distance entre la position actuelle et celle du Point le plus proche
     */
    public int getRadius(Position pos, int nb)
    {
        int size = output.size();
        Integer[] distances = new Integer[size];
        Log.d("Size: "," "+ size);
        int i = 0;
        for (PointDeCollecte pdt : output) {
            int dist = (int)pdt.getPosition().getRadius(pos);
            distances[i] = dist;
            Log.d("Distance: "," "+ dist);
            i++;
        }
        int min = (int) Collections.min(Arrays.asList(distances));

        Log.d("Min: "," "+ min);
        return min;
    }
}
