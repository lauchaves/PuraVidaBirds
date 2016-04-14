package cr.ac.itcr.puravidabirds;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import cr.ac.itcr.puravidabirds.access_data.DBAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        AboutFragment.OnFragmentInteractionListener,
        addBirdFragment.OnFragmentInteractionListener,
        BirdFragment.OnFragmentInteractionListener
        {

//, NOMBRECLASEFRAGMENT.OnNavigationItemSelectedListener


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


       String welcome= "Welcome, "+DBAdapter.maname.toString()+ "!";
        Log.d("welcome!!=", welcome);

        View headerView = navigationView.getHeaderView(0);
        TextView _text = (TextView) headerView.findViewById(R.id.textView);
        _text.setText(welcome);
        _text.setTextSize(15);

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Fragment fragment = new BirdFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();


        } else if (id == R.id.nav_gallery) {
            Fragment fragment = new addBirdFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();

        } else if (id == R.id.nav_slideshow) {

            Fragment fragment2 = new BirdFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment2).commit();



        } else if (id == R.id.nav_manage) {
            Fragment fragment2 = new BirdFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment2).commit();


        } else if(id==R.id.nav_about){
            Fragment fragment = new AboutFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
        } else if (id == R.id.nav_send) {
            finish();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
