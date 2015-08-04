package ru.feriatos.android.codeviriumtestproject;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends Activity {

    private static String TAG = SplashActivity.class.getName();

    // sleep for some time
    private static long SLEEP_TIME = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // removes title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // fullscreen mode
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        // start timer and launch main activity
        IntentLauncher launcher = new IntentLauncher();
        launcher.start();
    }


    private class IntentLauncher extends Thread {


        // sleep for some time and than start new activity.
        @Override
        public void run() {
            try {
                Thread.sleep(SLEEP_TIME * 1000); // sleeping
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            // start main activity
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            SplashActivity.this.startActivity(intent);
            SplashActivity.this.finish();
        }
    }
}