package tomerbu.edu.rssdemo;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //find View
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //toggle: on off
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //navigation view (the left side menu)
        NavigationView navigationView = findViewById(R.id.nav_view);
        //set this as onItemListener...
        navigationView.setNavigationItemSelectedListener(this);

        changeFrame(RssFragment.newInstance("http://www.ynet.co.il/Integration/StoryRss2.xml"));
        navigationView.setCheckedItem(R.id.nav_ynet);
    }

    //onBackPressed -> Activity method.
    //if the drawer is open, close it...
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

    //on drawer item selected:
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_ynet:
                changeFrame(RssFragment.newInstance("http://www.ynet.co.il/Integration/StoryRss2.xml"));
                break;
            case R.id.nav_walla:
                changeFrame(RssFragment.newInstance("http://rss.walla.co.il/?w=/1/0/12/@rss.e"));
                break;
            case R.id.nav_sports:
                changeFrame(RssFragment.newInstance("http://www.one.co.il/cat/coop/xml/rss/newsfeed.aspx"));
                break;
                //the upper part is used for fragment transactions.
//                Two parts to the nav, the lower is used for intents.
            case R.id.nav_share:
                //TODO: Add sharing" Settings Activity...";
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeFrame(Fragment fragment) {
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.frame, fragment).
                commit();
    }
}
