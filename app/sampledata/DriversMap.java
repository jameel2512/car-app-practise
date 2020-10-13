package com.example.jameel.everica;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Interpolator;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.github.glomadrian.materialanimatedswitch.MaterialAnimatedSwitch;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Locale;

public class DriversMap extends FragmentActivity implements OnMapReadyCallback
        ,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener
        //com.google.android.gms.location.LocationListener
{

    LocationManager locationManager;
    LocationListener locationListener;

    private static final int MY_PERMISSION_REQUEST_CODE=200;
    private static final int PLAY_SERVICES_RESOURCE_REQUEST_CODE=201;

    private static int UPDATE_INTERVAL=5001;
    private static int FASTEST_INTERVAL=5005;
    private static int DISPLACEMENT=5003;

    Marker marker_current;
    GeoFire geoFire;
    DatabaseReference dbRef_driver;
    MaterialAnimatedSwitch locationSwitch;
    SupportMapFragment mapFragment;




    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private Location lastlocation;
    private LocationRequest locationRequest;

    private List<LatLng> playlinelist;
    private Marker pickLocationMarker;
    private float v;
    double lat,lang;
    Handler handler;
    LatLng startPosition,currentPostition,endPosition;
    private int index,next;
    private Button button_Go;
    private EditText editText_searchtext;
    private PolylineOptions polylineOptions,blackpolylineOptions;
    private Polyline blackpolyline,greypolyline;


//
//    FirebaseAuth firebaseAuth;
//    DatabaseReference customerDbRef;
//    private LatLng customerPickUpLocation;
//    Button button_logoutMapbtn, button_settingMapbtn, button_callacarMapbtn;
//    String customerID;
//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        locationSwitch=(MaterialAnimatedSwitch) findViewById(R.id.id_location_switch);

        locationSwitch.setOnCheckedChangeListener(new MaterialAnimatedSwitch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(boolean isOnline) {
                if(isOnline)
                {
                    startLocationUpdate();
                    DisplayLocation();
                    Snackbar.make(mapFragment.getView(),"You Are Online",Snackbar.LENGTH_SHORT).show();
                    Toast.makeText(DriversMap.this, "You are Online", Toast.LENGTH_LONG).show();


                }else
                {
                    stopLocationUpdates();
                    marker_current.remove();
                    Snackbar.make(mapFragment.getView(),"You Are Ofline",Snackbar.LENGTH_SHORT).show();

                    Toast.makeText(DriversMap.this, "you are Offline", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dbRef_driver= FirebaseDatabase.getInstance().getReference("Drivers");
        geoFire=new GeoFire(dbRef_driver);

        setUpLocation();

//        firebaseAuth=FirebaseAuth.getInstance();
//        currentUser=firebaseAuth.getCurrentUser();
//        customerID= FirebaseAuth.getInstance().getCurrentUser().getUid();
        //     customerDbRef=FirebaseDatabase.getInstance().getReference().child("Customer Requests");


//        button_logoutMapbtn = (Button) findViewById(R.id.id_customer_logoutmapbtn);
//        button_settingMapbtn = (Button) findViewById(R.id.id_customer_settingmapbtn);
//        button_callacarMapbtn = (Button) findViewById(R.id.id_customer_callacarmapbtn);



//        button_logoutMapbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                firebaseAuth.signOut();
//
//                LogOutCustomer();
//            }
//        });


//        button_callacarMapbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                GeoFire geoFire=new GeoFire(customerDbRef);
//                geoFire.setLocation(customerID,new GeoLocation(lastlocation.getLatitude(),lastlocation.getLongitude()));
//
//                customerPickUpLocation=new LatLng(lastlocation.getLatitude(),lastlocation.getLongitude());
//                mMap.addMarker(new MarkerOptions().position(customerPickUpLocation).title("PickUp cstmr Location"));
//
//
//            }
//        });
//


    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode)
        {
            case MY_PERMISSION_REQUEST_CODE:
                if (grantResults.length> 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    if(checkplayservices())
                    {
                        buildGoogleApiClient();
                        createLocationRequest();
                        if(locationSwitch.isChecked())
                        {
                            DisplayLocation();
                        }
                    }
                }
        }

//        if (requestCode == 1) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (android.location.LocationListener) locationListener);
//                }
//            }
//        }




    }




    private void setUpLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED)
        {

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION ,
                    Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSION_REQUEST_CODE);
        }
        else
        {


            if(checkplayservices())
            {
                buildGoogleApiClient();
                createLocationRequest();
                if(locationSwitch.isChecked())
                {
                    DisplayLocation();
                }
            }
        }
    }





    private void createLocationRequest() {

        locationRequest=new LocationRequest();
        locationRequest.setInterval(UPDATE_INTERVAL);
        locationRequest.setFastestInterval(FASTEST_INTERVAL);
        locationRequest.setPriority(locationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setSmallestDisplacement(DISPLACEMENT);
    }

    private boolean checkplayservices() {

    int resultCode= GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

    if (resultCode != ConnectionResult.SUCCESS) {

        if (GooglePlayServicesUtil.isUserRecoverableError(resultCode))
            GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOURCE_REQUEST_CODE).show();
        else {
            Toast.makeText(this, "This Deveice Not Supported", Toast.LENGTH_SHORT).show();
            finish();
        }
        return false;
    }
    return true;

    }


    private void buildGoogleApiClient(){

        googleApiClient=new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        googleApiClient.connect();
    }




    private void stopLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }

        LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient,this);


    }

    private void startLocationUpdate() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,locationRequest,this);
    }

    private void DisplayLocation()
    {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }

        lastlocation=LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if(lastlocation!=null)
        {
            if (locationSwitch.isChecked())
            {
                final double latitude=lastlocation.getLatitude();
                final double longitude=lastlocation.getLongitude();
                geoFire.setLocation(FirebaseAuth.getInstance().getCurrentUser().getUid(), new GeoLocation(latitude, longitude), new
                        GeoFire.CompletionListener() {
                            @Override
                            public void onComplete(String key, DatabaseError error) {

                                if(marker_current!=null)
                                {
                                    marker_current.remove();
                                    marker_current=mMap.addMarker(new MarkerOptions()
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.car))
                                            .position(new LatLng(latitude,longitude))
                                            .title("You"));

                                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng (latitude,longitude),15.0f));

                                    rotateMarker(marker_current,-360,mMap);

                                }
                            }
                        }
                );
            }
        }
        else
        {
            Toast.makeText(this, "Error!!  Cant Get Your Location", Toast.LENGTH_SHORT).show();
        }

    }





    private void rotateMarker(final Marker marker_current, final float i, GoogleMap mMap) {

        final Handler handler=new Handler();
        final long start= SystemClock.uptimeMillis();
        final float startRotation=marker_current.getRotation();
        final long duration=1500;

        final android.view.animation.Interpolator interpolator1=new LinearInterpolator();

        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed=SystemClock.uptimeMillis()-start;
                float t= interpolator1.getInterpolation((float)elapsed/duration);
                float rot=t*i+(1-t)*startRotation;
                marker_current.setRotation(-rot > 180?rot/2:rot);
                if (t < 1.0)
                {
                    handler.postDelayed(this,16);
                }
            }
        });

    }







    @Override
    public void onConnected(@Nullable Bundle bundle) {
        DisplayLocation();
        startLocationUpdate();
    }



    @Override
    public void onConnectionSuspended(int i) {
        googleApiClient.connect();
    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }



    @Override
    public void onLocationChanged(Location location) {

    }



//
//
//    private void LogOutCustomer() {
//
//        Intent intent = new Intent(DriversMap.this, Users_Login_And_Register_Activity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//        finish();
//
//    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;







//        // Add a marker in Sydney and move the camera
//        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//        locationListener = new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//                mMap.clear();
//                LatLng User = new LatLng(location.getLatitude(), location.getLongitude());
//                mMap.addMarker(new MarkerOptions().position(User).title("Your Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(User));
//                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
//                try {
//                    List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//                    if (addressList != null && addressList.size() > 0) {
//                        Toast.makeText(getApplicationContext(), "Hiiii", Toast.LENGTH_SHORT).show();
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
////            @Override
////            public void onStatusChanged(String s, int i, Bundle bundle) {
////
////            }
////
////            @Override
////            public void onProviderEnabled(String s) {
////
////            }
////
////            @Override
////            public void onProviderDisabled(String s) {
////
////            }
//
//
//        };
//
//        if (Build.VERSION.SDK_INT < 16) {
//            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
//                    != PackageManager.PERMISSION_GRANTED &&
//                    ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
//                            != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return;
//            }
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (android.location.LocationListener) locationListener);
//        }
//        else
//        {
//            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
//            {
//                ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},1);
//            }
//            else
//            {
//                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0, (android.location.LocationListener) locationListener);
//            }
//        }





//        if (ActivityCompat.checkSelfPermission(this,
//                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            return;
//        }
//
//        buildGoogleApiClient();
//        mMap.setMyLocationEnabled(true);


        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


    }




//
//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//
//
//        locationRequest = new LocationRequest();
//        locationRequest.setInterval(1000);
//        locationRequest.setFastestInterval(1000);
//        locationRequest.setPriority(locationRequest.PRIORITY_HIGH_ACCURACY);
//
//        if (ActivityCompat.checkSelfPermission
//                (this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            return;
//        }
//        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
//
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//
//        lastlocation=location;
//
//        LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
//
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
//
//        String userID= FirebaseAuth.getInstance().getCurrentUser().getUid();
//        DatabaseReference DriverAvailiableRefrenece= FirebaseDatabase.getInstance().getReference("DriversAvailiable");
//
//        GeoFire geoFire=new GeoFire(DriverAvailiableRefrenece);
//        geoFire.setLocation(userID,new GeoLocation(location.getLatitude(),location.getLongitude()));
//
//    }
//
//
//    protected synchronized  void buildGoogleApiClient(){
//
//        googleApiClient=new GoogleApiClient.Builder(this)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .addApi(LocationServices.API)
//                .build();
//
//        googleApiClient.connect();
//    }
//
//    protected void onStop() {
//        super.onStop();
//
//        String userID= FirebaseAuth.getInstance().getCurrentUser().getUid();
//        DatabaseReference DriverAvailiableRefrenece= FirebaseDatabase.getInstance().getReference("Drivers Availiable");
//
//        GeoFire geoFire=new GeoFire(DriverAvailiableRefrenece);
//        geoFire.removeLocation(userID);
//    }
//
//






}
