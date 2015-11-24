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

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private LocationManager locationManager;
    private String locationProviderNetwork = LocationManager.NETWORK_PROVIDER;
    private String locationProviderGps = LocationManager.GPS_PROVIDER;

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            makeNewLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void makeNewLocation(Location location) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();

        Log.i(TAG, "lat: " + lat + ", lng: " + lng);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        try{
            locationManager.requestLocationUpdates(locationProviderNetwork, 0, 0, locationListener);
        }catch (SecurityException ex){
                ex.printStackTrace();
        }


    }


}
