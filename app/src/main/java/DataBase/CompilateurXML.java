package DataBase;


import android.support.v4.app.FragmentActivity;
import android.util.Log;



import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import project.thibaulpoignonec.selectri.R;

/**
 * Created by Thibault on 17/05/2016.
 */

/*
 * Classe qui permet de parser/transformer des fichier xml
 * et de renvoyer un objet DOM (Arbre de noeuds XML)
 *
 * Non implémenter ici : telechargement/sauvegarde sur carte SD
 */
public class CompilateurXML implements AsyncReponse
{
    private String fileName;
    private String url;
    private String XsltFile;

    DownloadXML asyncTask;
    Boolean bddReady;


    private Document outputDoc;

    protected FragmentActivity context;

    private String bdd; // Pour tests seulement...

    public CompilateurXML(FragmentActivity _context)// Pour tests seulement...
        {
            this.context = _context;
    }

    public void processFinish(Document output)
    {
        outputDoc = transformXmlDocument(output,context.getResources().openRawResource(R.raw.xslt_xml));
        Log.e("Download terminé ! ", toString(outputDoc));

    }

    public void update()
    {
        String urlPaysDeBrest = "http://geo.brest-metropole.fr/arcgis/services/public/GPB_ESP/MapServer/WFSServer?service=wfs&version=2.0&request=GetFeature&typeNames=public_GPB_ESP%3ADECHETS_PROPRETE_-_Points_d_apport_volontaire__Pays_de_Brest_&sortBy=public_GPB_ESP%3ATYPE";
        download(urlPaysDeBrest);

        //newBdd = xmlToJson(newBdd);
        //save(newBdd);
    }
    private boolean download(String url)
    {
        DownloadXML asyncTask = new DownloadXML(this);
        asyncTask.execute(url);
        return true;
    }
    public Document getBddInXml (InputStream xml)
    {
        Document myXmlDocument=null;
        try
        {
            myXmlDocument = loadXMLFromString(xml);
            //Log.e("BDD brute (DOM)", toString(myXmlDocument));
            myXmlDocument = transformXmlDocument(
                    myXmlDocument,
                    context.getResources().openRawResource(R.raw.xslt_xml));
            /*
            // TODO : ajouter les nodes textile, pile et decheterie
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(false);
            DocumentBuilder builder = null;
            try {
                builder = factory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
            Document bddTextile = null;
            Document bddPile = null;
            Document bddDecheterie = null;
            try
            {
                bddTextile= builder.parse(context.getResources().openRawResource(R.raw.bdd_brest_textile),"UTF-8");
                //bddPile= builder.parse(context.getResources().openRawResource(R.raw.bdd_brest_pile),"UTF-8");
                //bddDecheterie= builder.parse(context.getResources().openRawResource(R.raw.bdd_brest_decheterie),"UTF-8");
            } catch (SAXException e) {
                e.printStackTrace();
            }
            if(bddTextile != null)
            {
                Node newtype = bddTextile.getElementsByTagName("brest").item(0);
                NodeList villeCible = myXmlDocument.getElementsByTagName("brest");
                Node typeCible = villeCible.item(0);

                typeCible.appendChild()

            }
            if(bddPile != null)
            {
                Node newtype = bddPile.getElementsByTagName("pile").item(0);
                Node villeCible = myXmlDocument.getElementsByTagName("brest").item(0);
                villeCible.appendChild(newtype);
            }
            if(bddDecheterie != null)
            {
                Node newtype = bddDecheterie.getElementsByTagName("decheterie").item(0);
                Node villeCible = myXmlDocument.getElementsByTagName("brest").item(0);
                villeCible.appendChild(newtype);
            }*/

            return myXmlDocument;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String toString(Document doc) {
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error converting to String", ex);
        }
    }

    public Document loadXMLFromString(InputStream xml) throws Exception
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();

        return builder.parse(xml,"UTF-8");
    }
    private boolean save(String bdd)
    {
        return true;
    }

    public final Document transformXmlDocument(Document sourceDocument, InputStream xsltFile) {

        DOMSource xmlSource = new DOMSource(sourceDocument);
        StreamSource xsltSource = new StreamSource(xsltFile);

        Document transformedData = null;

        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(xsltSource);

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            StreamResult result = new StreamResult(output);

            transformer.transform(xmlSource, result);

            DocumentBuilder resultBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            transformedData = resultBuilder.parse(
                    new InputSource(
                            new StringReader(
                                    new String(output.toByteArray())
                            )
                    )
            );
        } catch (Exception e) {
            Log.e("XSLT Transformation", e.getMessage());
        }

        return transformedData;
    }





}
