package project.androidserviceexample;

/**
 * Created by waron on 7/9/2560.
 */

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.annotation.Nullable;

/**
 * Created by Belal on 12/30/2016.
 */

public class MyService extends Service {
    //creating a mediaplayer object
    private MediaPlayer player;
    Vibrator v;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //getting systems default ringtone
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        final Handler handler = new Handler();
        player = MediaPlayer.create(this,
                Settings.System.DEFAULT_RINGTONE_URI);
        //setting loop play to true
        //this will make the ringtone continuously playing
        player.setLooping(true);

        //staring the player
        player.start();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                player.stop();
                v.vibrate(500);
            }
        }, 5000);


        //we have some options for service
        //start sticky means service will be explicity started and stopped
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //stopping the player when service is destroyed

    }
}