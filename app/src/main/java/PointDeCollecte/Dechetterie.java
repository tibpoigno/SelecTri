package PointDeCollecte;
import project.thibaulpoignonec.selectri.MapActivity;
import project.thibaulpoignonec.selectri.R;

import android.util.Log;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/**
 * Created by Thibault on 17/05/2016.
 */
public class Dechetterie extends PointDeCollecte
{
    public Dechetterie(double latitude, double longitude, boolean _isBuried)
    {
        super(latitude, longitude, _isBuried);
    }

    public BitmapDescriptor addMarker(MapActivity mMap)
    {
        //TODO : Ajouter un marker décheterie
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.marker_decheterie);
        return icon;
    }
}
