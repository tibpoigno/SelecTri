package project.thibaulpoignonec.selectri;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.SphericalUtil;

import org.w3c.dom.Document;

import java.util.Vector;

import Cartographie.Position;
import DataBase.CompilateurXML;
import DataBase.DataBaseXml;
import PointDeCollecte.*;

/*
 * Affiche une carte puis ajoute des bacs dessus (Bitmap) en fonction du type passé au
 * constructeur.
 *
 * Peux renvoyer vers un itineraire
 */
public class MapActivity extends FragmentActivity implements OnMapReadyCallback {//}, LocationListener {
    private LocationManager locationManager;


    public GoogleMap mMap;
    private String typeDebac;
    protected DataBaseXml dataBaseXml;
    private boolean isMarked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle bundle = getIntent().getExtras();
        typeDebac = bundle.getString("TYPE");
        isMarked = false;
    }
/*
    @Override
    public void onResume() {
        super.onResume();

        //Obtention de la référence du service
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

        //Si le GPS est disponible, on s'y abonne
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            abonnementGPS();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        //On appelle la méthode pour se désabonner
        desabonnementGPS();
    }

    /*
     * Méthode permettant de s'abonner à la localisation par GPS.
     */
    /*
    public void abonnementGPS() {
        //On s'abonne
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: !!

            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);
    }*/

    /**
     * Méthode permettant de se désabonner de la localisation par GPS.
     */
    /*
    public void desabonnementGPS() {
        //Si le GPS est disponible, on s'y abonne
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: !!
            return;
        }
        locationManager.removeUpdates(this);
    }

    public void onLocationChanged(final Location location) {
        //On affiche dans un Toat la nouvelle Localisation
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        final StringBuilder msg = new StringBuilder("lat : ");
        msg.append(latitude);
        msg.append( "; lng : ");
        msg.append(longitude);
        Log.d("Position", msg.toString());

        Toast.makeText(this, msg.toString(), Toast.LENGTH_SHORT).show();
        if(mMap != null) mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude,longitude)));

        if(isMarked == false) {drawMarkers(dataBaseXml.getBacs(typeDebac), typeDebac); isMarked = true;}
        //android.location.Location maPosition = mMap.getMyLocation();

    }

    public void onProviderDisabled(final String provider) {
        //Si le GPS est désactivé on se désabonne
        if("gps".equals(provider)) {
            desabonnementGPS();
        }
    }
    public void onProviderEnabled(final String provider) {
        //Si le GPS est activé on s'abonne
        if("gps".equals(provider)) {
            abonnementGPS();
        }
    }

    public void onStatusChanged(final String provider, final int status, final Bundle extras) { }

    private void setUpMap() {




    }*/
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomGesturesEnabled(true);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED)
        {
            mMap.setMyLocationEnabled(true);
        }
        else
        {
            // TODO : faire un toast...
        }

        showMap(typeDebac);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);

        // Récuperer la position du telephone
        Location myLocation = locationManager.getLastKnownLocation(provider);

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // latitude de la position actuelle
        double latitude = myLocation.getLatitude();

        // longitude de la position actuelle
        double longitude = myLocation.getLongitude();

        // Create a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);

        // Show the current location in Google Map

        int radius = dataBaseXml.getRadius(new Position(latitude,longitude),1);

        // Zoom dans Google Map
        setZoom(radius,latLng);
    }
    private void setZoom(int radius, LatLng point)
    {

        int mapHeightInDP = 200;
        Resources r = getResources();
        int mapSideInPixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mapHeightInDP, r.getDisplayMetrics());

        LatLngBounds latLngBounds = calculateBounds(point, radius);
        if(latLngBounds != null){
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(latLngBounds, mapSideInPixels, mapSideInPixels, 1);
            if(mMap != null)
                mMap.animateCamera(cameraUpdate);
        }
    }

    private LatLngBounds calculateBounds(LatLng center, double radius) {
        return new LatLngBounds.Builder().
                include(SphericalUtil.computeOffset(center, radius, 0)).
                include(SphericalUtil.computeOffset(center, radius, 90)).
                include(SphericalUtil.computeOffset(center, radius, 180)).
                include(SphericalUtil.computeOffset(center, radius, 270)).build();
    }


    public void drawBacs(String type)
    {
        CompilateurXML compilateurXML = new CompilateurXML(this);
        Document bdd = null;
        try {
        if(type.equals("dmr") | type.equals("recyclable") | type.equals("verre"))
             bdd = compilateurXML.getBddInXml(getResources().openRawResource(R.raw.bdd_brute_pays_de_brest));
        else{
            if(type.equals("textile"))
                    bdd = compilateurXML.loadXMLFromString(getResources().openRawResource(R.raw.bdd_brest_textile));
            else{
                if(type.equals("pile"))
                    bdd = compilateurXML.loadXMLFromString(getResources().openRawResource(R.raw.bdd_brest_pile));
                else{
                    if(type.equals("decheterie"))
                        bdd = compilateurXML.loadXMLFromString(getResources().openRawResource(R.raw.bdd_brest_decheterie));
                }
            }
        }
        } catch (Exception e) {e.printStackTrace();}

        Log.d("CARTE", "DOM bdd crée");
        Log.d("CARTE", CompilateurXML.toString(bdd));


        dataBaseXml = new DataBaseXml(this,bdd, "brest");
        Log.d("CARTE", "bdd crée");
        if(isMarked == false) {isMarked = true; drawMarkers(dataBaseXml.getBacs(typeDebac), typeDebac); }

    }

    public void showMap(String type) {
        drawBacs(type);
    }

    public boolean drawMarkers(Vector<PointDeCollecte> pdts, String type) {
        BitmapDescriptor icon = pdts.firstElement().addMarker(this);

        for (PointDeCollecte pdt : pdts) {
            Log.d("CARTE : drawMarkers())", "AJOUT PUSH-PIN");
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(pdt.getPosition().getLongitude(), pdt.getPosition().getLatitude()))
                    .title(type)
                    .icon(icon));
        }
        return true;
    }
}
