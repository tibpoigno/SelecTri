package PointDeCollecte;
import project.thibaulpoignonec.selectri.MapActivity;
import project.thibaulpoignonec.selectri.R;

import android.util.Log;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;


/**
 * Created by Thibault on 17/05/2016.
 */
public class BacVerre extends PointDeCollecte
{
    public BacVerre(double latitude, double longitude, boolean _isBuried)
    {
        super(latitude, longitude, _isBuried);
    }

    public BitmapDescriptor addMarker(MapActivity mMap)
    {

        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.marker_verre);
        return icon;
    }
}
