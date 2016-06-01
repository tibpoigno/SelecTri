package Cartographie;

/**
 * Created by Thibault on 17/05/2016.
 */
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;
public class Position
{
    private double latitude;
    private double longitude;

    public Position(double _latitude, double _longitude)
    {
        latitude =_latitude;
        longitude = _longitude;
    }
    public boolean isIn(Position location, double radius)
    {
        if(getRadius(location)<radius)
            return true;
        else
            return  false;
    }
    /*
     * Renvoit la distance entre 2 points
     */
    public double getRadius(Position point)
    {
        return SphericalUtil.computeDistanceBetween(new LatLng(longitude,latitude),new LatLng(point.getLatitude(),point.getLongitude())) ; // avec API google
    }
    public double getLatitude(){return latitude;}
    public double getLongitude(){return longitude;}
}
