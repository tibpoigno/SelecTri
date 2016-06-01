package PointDeCollecte;
import Cartographie.Position;

/**
 * Created by Thibault on 17/05/2016.
 */

/*
 * Classe mère ( et abstraite ) représentant un point de collecte
 */
public abstract class PointDeCollecte implements Drawable
{
    protected boolean isBuried;
    protected Position position;

    public PointDeCollecte(double latitude, double longitude, boolean _isBuried)
    {
        position = new Position(latitude,longitude);
        isBuried=_isBuried;
    }
    public double getDistance(PointDeCollecte pdt)
    {
        return pdt.getPosition().getRadius(position);
    }
    public Position getPosition()
    {
        return position;
    }
}

