package com.example.levon.departments;

import android.graphics.Point;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private ImageView splashBackgroundImage;
    private ImageView splashLogo;
    private TextView appNameText;
    private TextView appVersionText;
    private FrameLayout fragmentConatiner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        beginSplashScreenAnimation();
        //startLoginStep();
    }

    private void initViews() {
        splashBackgroundImage = findViewById(R.id.splash_background_image);
        splashLogo = findViewById(R.id.splash_logo);
        appNameText = findViewById(R.id.app_name_text);
        appVersionText = findViewById(R.id.app_version_text);
        fragmentConatiner = findViewById(R.id.login_fragment_container);
    }

    private void beginSplashScreenAnimation() {
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int displayHeight = point.y;
        splashBackgroundImage.animate().
                translationY(-displayHeight).
                setDuration(2000).
                setStartDelay(3000).
                withEndAction( new Runnable() {
                    @Override
                    public void run() {
                        startLoginStep();
                    }
                });
        disappearAnimation(splashLogo, 3000, 1000, null);
        disappearAnimation(appNameText, 3000, 1000, null);
        disappearAnimation(appVersionText, 3000, 1000, null);
    }

    private void startLoginStep() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().
                add(R.id.login_fragment_container, new LoginFragment()).
                commit();
        appearAnimation(fragmentConatiner, 0, 2000, new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    private void disappearAnimation(final View view,
                                    int startDelay,
                                    int duration,
                                    final Runnable endAction) {
        if (endAction != null) {
            view.animate().
                    alpha(0).
                    setStartDelay(startDelay).
                    setDuration(duration).
                    withEndAction(endAction);
        } else {
            view.animate().
                    alpha(0).
                    setStartDelay(startDelay).
                    setDuration(duration);
        }
    }

    private void appearAnimation(final View view,
                                 int startDelay,
                                 int duration,
                                 final Runnable endAction) {
        if (endAction != null) {
            view.animate().
                    alpha(1).
                    setStartDelay(startDelay).
                    setDuration(duration).
                    withEndAction(endAction);
        } else {
            view.animate().
                    alpha(1).
                    setStartDelay(startDelay).
                    setDuration(duration);
        }
    }
}
