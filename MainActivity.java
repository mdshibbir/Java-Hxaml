package com.drawerlayout.drawerlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerlayout;
    MaterialToolbar materialtoolbar;
    FrameLayout framelayout;
    NavigationView navigationview;
    TextView hadertitle;
    View haderView;
    ImageView imagehader;

    LottieAnimationView loading;

    WebView webview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_main);
        drawerlayout = findViewById(R.id.drawerlayout);
        materialtoolbar = findViewById(R.id.materialtoolbar);
        framelayout = findViewById(R.id.framelayout);
        navigationview = findViewById(R.id.navigationview);
        haderView = navigationview.getHeaderView(0);
        hadertitle = haderView.findViewById(R.id.hadertitle);
        imagehader = haderView.findViewById(R.id.imagehader);
        loading = findViewById(R.id.loading);




        getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);


        webview = (WebView) findViewById(R.id.webview);
        webview.setWebChromeClient(new WebChromeClient() {
        public void onProgressChanged(WebView view, int progress)
        {
            //Make the bar disappear after URL is loaded, and changes string to
            //setTitle("Loading...");
            setProgress(progress * 100); //Make the bar disappear after URL is loaded

            // Return the app name after finish loading
            if(progress == 100){
                loading.setVisibility(View.INVISIBLE);
            }else {
                loading.setVisibility(View.VISIBLE);
            }

        }
    });


        webview.loadUrl("http://www.google.com/");




        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                MainActivity.this,drawerlayout,materialtoolbar,R.string.Drawer_Close,R.string.Drawer_Open);
        drawerlayout.addDrawerListener(toggle);

        //-----------------------------------------------------------------------

        materialtoolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId()==R.id.Home) {
                    Toast.makeText(MainActivity.this,"Home",Toast.LENGTH_LONG).show();

                }
                return false;
            }
        });

        //------------------------------------------------------------------------------------
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.Home1){
                    //Toast.makeText(MainActivity.this,"Home",Toast.LENGTH_LONG).show();

                   MyFragment.WEB_URL = "http://www.google.com/";

                    FragmentManager fmanager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fmanager.beginTransaction();
                    fragmentTransaction.add(R.id.framelayout,new MyFragment());
                    fragmentTransaction.commit();

                    drawerlayout.closeDrawer(GravityCompat.START);
                }
                else if (item.getItemId()==R.id.Profile1) {
                   // Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_LONG).show();

                   MyFragment.WEB_URL = "http://www.google.com/";

                    FragmentManager fmanager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fmanager.beginTransaction();
                    fragmentTransaction.add(R.id.framelayout,new MyFragment());
                    fragmentTransaction.commit();

                    drawerlayout.closeDrawer(GravityCompat.START);
                    //hadertitle.setText("umme sumaiya");
                }
                return false;
            }
        });


    }

    //-------------------------------------------oncrate ENDS



}