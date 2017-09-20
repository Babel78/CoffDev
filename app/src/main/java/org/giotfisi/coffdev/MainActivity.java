package org.giotfisi.coffdev;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.MenuItem;

import com.estimote.coresdk.common.config.EstimoteSDK;
import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;
import com.estimote.coresdk.recognition.packets.EstimoteLocation;
import com.estimote.coresdk.service.BeaconManager;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    BeaconManager beaconManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigator);
        beaconManager= new BeaconManager(getApplicationContext());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, HomeFragment.newInstance());
        transaction.commit();
        EstimoteSDK.initialize(getApplicationContext(),"beacon1-bix","257d621c9f372a11b7a6b8c630e10a31");
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                android.support.v4.app.Fragment selectFragment=null;
                switch (item.getItemId()){
                    case R.id.action_products:
                        selectFragment= ProductsFragment.newInstance();
                        break;

                    case R.id.action_home:
                        selectFragment= HomeFragment.newInstance();
                        break;

                    case R.id.action_orden:
                        selectFragment= OrdenFragment.newInstance();
                        break;
                }
                if(selectFragment!=null){
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainer, selectFragment);
                    transaction.commit();
                }
                return true;
            }

        });
        beaconManager.connect(new BeaconManager.ServiceReadyCallback(){
            @Override
            public void onServiceReady() {
                beaconManager.startLocationDiscovery();
            }
        });
        //Beacon TLM
        /*beaconManager.setTelemetryListener(new BeaconManager.TelemetryListener() {
            @Override
            public void onTelemetriesFound(List<EstimoteTelemetry> telemetries) {
                Log.d(TAG,"Beacons Telemetry: " + telemetries);
            }
        });*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        SystemRequirementsChecker.checkWithDefaultDialogs(this);
        beaconManager.setLocationListener(new BeaconManager.LocationListener() {
            @Override
            public void onLocationsFound(List<EstimoteLocation> beacons) {
                String beaconLemon="1013cf850dde10459a65b4b57f0c0c1e";
                String beaconBeetroot="6bc493a45db2119392c3b579e33c0c08";
                for(EstimoteLocation beacon: beacons){
                    if(beacons.size()==1){
                        switch (beacon.id.toHexString()){
                            case "1013cf850dde10459a65b4b57f0c0c1e":
                                showNotification1("CoffDev", "Get new offerts 1");
                                break;

                            case "6bc493a45db2119392c3b579e33c0c08":
                                showNotification2("CoffDev", "Get new offerts 2");
                                break;
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        beaconManager.startLocationDiscovery();

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //beaconManager.stopTelemetryDiscovery();
        beaconManager.stopLocationDiscovery();

    }

    @Override protected void onStop() {
        super.onStop();
    }

    private boolean notificacionAlreadyshown=false;
    public void showNotification1(String title,String message) {
        if (notificacionAlreadyshown) {
            return;
        } else {
            Intent i=new Intent(this,ofertActivity.class);
            NotificationCompat.Builder mBuilder;
            NotificationManager mNotifyMgr = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

            int icono = R.drawable.ic_ofert;
            PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, i, 0);
            mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext())
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(icono)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setLights(Color.CYAN, 1, 0)
                    .setVibrate(new long[]{100, 250, 100, 500})
                    .setAutoCancel(true)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

            mNotifyMgr.notify(1, mBuilder.build());
            notificacionAlreadyshown = true;
        }
    }
    private boolean notificacionAlreadyshown2=false;
    public void showNotification2(String title,String message){
        if(notificacionAlreadyshown2){
            return;
        }
        else{
            NotificationCompat.Builder mBuilder;
            NotificationManager mNotifyMgr =(NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
            int icono = R.drawable.ic_ofert;
            Intent i=new Intent(this,ofert2Activity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, i, 0);
            mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext())
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(icono)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setLights(Color.CYAN,1,0)
                    .setVibrate(new long[] {100, 250, 100, 500})
                    .setAutoCancel(true)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
            mNotifyMgr.notify(1, mBuilder.build());
            notificacionAlreadyshown2=true;
        }
    }
}
