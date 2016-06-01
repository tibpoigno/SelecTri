package PointDeCollecte;

import com.google.android.gms.maps.model.BitmapDescriptor;

import project.thibaulpoignonec.selectri.MapActivity;


/**
 * Created by Thibault on 17/05/2016.
 */

/*
 *  Interface forcant les points de collectes a posseder une méthode d'affichage
 */
public interface Drawable
{
    BitmapDescriptor addMarker(MapActivity mMap);
}
