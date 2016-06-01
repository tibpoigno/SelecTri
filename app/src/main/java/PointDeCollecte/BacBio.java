package PointDeCollecte;

import project.thibaulpoignonec.selectri.MapActivity;
import project.thibaulpoignonec.selectri.R;

import android.graphics.drawable.Icon;
import android.util.Log;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;


/**
 * Created by Thibault on 17/05/2016.
 */
public class BacBio extends PointDeCollecte
{
    static Icon img;
    public BacBio(double latitude, double longitude, boolean _isBuried)
    {
        super(latitude, longitude, _isBuried);
    }

    public BitmapDescriptor addMarker(MapActivity mMap)
    {
        // TODO : a implémenter (optionel...)
        BitmapDescriptor icon = null;//BitmapDescriptorFactory.fromResource(R.drawable.marker_);
        return icon;
    }
}
