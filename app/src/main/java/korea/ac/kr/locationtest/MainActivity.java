package korea.ac.kr.locationtest;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    private LocationManager locationManager;
    private String locationProviderNetwork = LocationManager.NETWORK_PROVIDER;
    private String locationProviderGps = LocationManager.GPS_PROVIDER;
    private TextView txtViewLocationInfoOne;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtViewLocationInfoOne = (TextView) findViewById(R.id.txtViewLocationInfo);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);


    }

    public void getLocationInfoContinuous(View view){
        switch (view.getId()){
            case R.id.btnGetLocationInfoContinuous:
                Toast.makeText(getApplicationContext(), "getting location..", Toast.LENGTH_SHORT).show();
                startLocationService();
        }
    }
//
//    public void getLocationInfoOne(View view){
//
//
//
//
//
//
//        switch (view.getId()){
//            case R.id.btnGetLocationInfoOne:
//                Location location =locationManager.getLastKnownLocation(locationProviderNetwork);
//                Double latitude = location.getLatitude();
//                Double longitude = location.getLongitude();
//                String msg = "Latitude : "+ latitude + "\nLongitude:"+ longitude;
//
//                txtViewLocationInfoOne.setText(msg);
//
//                Log.i("GPSLocationService", msg);
////                Toast.makeText(getApplicationContext(), "getting location..", Toast.LENGTH_SHORT).show();
//
//        }
//    }

    private void startLocationService() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        GPSListener gpsListener = new GPSListener();

        try{
            locationManager.requestLocationUpdates(locationProviderNetwork, 1000, 0, gpsListener);
        }catch (SecurityException ex){
            ex.printStackTrace();
        }

    }

    class GPSListener implements LocationListener {

        public void onLocationChanged(Location location) {

            //capture location data sent by current provider
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            String msg = "Latitude : "+ latitude + "\nLongitude:"+ longitude;
            txtViewLocationInfoOne.setText(msg);

            Log.i("GPSLocationService", msg);
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

        }

        public void onProviderDisabled(String provider) {
            Toast.makeText(getApplicationContext(), "onProviderDisabled", Toast.LENGTH_LONG).show();
        }

        public void onProviderEnabled(String provider) {
            Toast.makeText(getApplicationContext(), "onProviderEnabled", Toast.LENGTH_LONG).show();
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            Toast.makeText(getApplicationContext(), "onStatusChanged", Toast.LENGTH_LONG).show();
        }

    }
}
