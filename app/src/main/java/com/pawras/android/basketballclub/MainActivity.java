
package com.pawras.android.basketballclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.pawras.android.basketballclub.adapters.PagerAdapter;
import com.pawras.android.basketballclub.add_new.CreateNew;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pawras.android.basketballclub.fragment.Coaches;
import com.pawras.android.basketballclub.fragment.Forum;
import com.pawras.android.basketballclub.fragment.Players;
import com.pawras.android.basketballclub.fragment.Teams;

public class MainActivity extends BaseActivity {
    // used for debugging to the logs
    private static final String TAG = "MainActivity";

    // instance variables
    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    //*************Test*************************
    private DatabaseReference mDatabaseReference;
    String id="0";

    //*******************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("FORUM"));
        tabLayout.addTab(tabLayout.newTab().setText("COACHES"));
        tabLayout.addTab(tabLayout.newTab().setText("PLAYERS"));
        tabLayout.addTab(tabLayout.newTab().setText("TEAMS"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

         mViewPager = (ViewPager) findViewById(R.id.container);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // Button launches NewPostActivity to the message board
        findViewById(R.id.fab_new_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewPostActivity.class));
            }
        });

        findViewById(R.id.floating_action_button_coach).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  startActivity(new Intent(MainActivity.this, NewPostActivity.class));
                Toast.makeText(MainActivity.this, "Selected page Coach ", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.floating_action_button_player).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(MainActivity.this, NewPostActivity.class));
                Toast.makeText(MainActivity.this, "Selected page Player ", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.floating_action_button_team).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  startActivity(new Intent(MainActivity.this, NewPostActivity.class));
                Toast.makeText(MainActivity.this, "Selected page Team", Toast.LENGTH_SHORT).show();
            }
        });


        // Attach the page change listener inside the activity
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                if(position == 0){
                    findViewById(R.id.floating_action_button_team).setVisibility(View.VISIBLE);
                    findViewById(R.id.floating_action_button_coach).setVisibility(View.GONE);
                    findViewById(R.id.floating_action_button_player).setVisibility(View.GONE);
                    findViewById(R.id.floating_action_button_team).setVisibility(View.GONE);
                }
                if(position == 1){
                    findViewById(R.id.floating_action_button_team).setVisibility(View.GONE);
                    findViewById(R.id.floating_action_button_coach).setVisibility(View.VISIBLE);
                    findViewById(R.id.floating_action_button_player).setVisibility(View.GONE);
                    findViewById(R.id.floating_action_button_team).setVisibility(View.GONE);
                }
                if(position == 2){
                    findViewById(R.id.floating_action_button_team).setVisibility(View.GONE);
                    findViewById(R.id.floating_action_button_coach).setVisibility(View.GONE);
                    findViewById(R.id.floating_action_button_player).setVisibility(View.VISIBLE);
                    findViewById(R.id.floating_action_button_team).setVisibility(View.GONE);
                }
                if(position == 3){
                    findViewById(R.id.floating_action_button_team).setVisibility(View.GONE);
                    findViewById(R.id.floating_action_button_coach).setVisibility(View.GONE);
                    findViewById(R.id.floating_action_button_player).setVisibility(View.GONE);
                    findViewById(R.id.floating_action_button_team).setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        //******************************************************************************************
        // test data
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("teams");

        // Create a list of Coaches
        // problem reading array list
        //ArrayList<Coach> coach = new ArrayList<Coach>();
        //mDatabaseReference.push().setValue(new Coach("Pat", "Murphy", "30/05/86", "pat@gmail.com", "Willow bank ", "0214219786"));
        //mDatabaseReference.push().setValue(new Coach("Simon", "O'Neill", "15/04/88", "simon@gmail.com", "Church Road ", "0875698556"));
        //mDatabaseReference.push().setValue(new Coach("Tom", "O'Mahony", "10/01/77", "tom@gmail.com", "The Glen", "0854659876"));
        //mDatabaseReference.push().setValue(new Coach("Peter", "Mc Cabe", "30/05/86", "peter@gmail.com", "Whitethorn", "0214291673"));
        //mDatabaseReference.push().setValue(new Coach("Charles", "Ryan", "12/12/67", "charles@gmail.com", "College Road", "08565893547"));

        // mDatabaseReference.push().setValue(new Player("Sinead", "Murphy", "30/05/86", "Sinead@gmail.com", "Willow bank ", "0214219786"));
        // mDatabaseReference.push().setValue(new Player("Sarah", "O'Connell", "30/05/86", "Sarah@gmail.com", "Willow bank ", "0214219786"));
        // mDatabaseReference.push().setValue(new Player("June", "Reilly", "30/05/86", "June@gmail.com", "Willow bank ", "0214219786"));
        // mDatabaseReference.push().setValue(new Player("Pat", "Forest", "30/05/86", "pat@gmail.com", "Willow bank ", "0214219786"));
        // mDatabaseReference.push().setValue(new Player("Amy", "McCree", "30/05/86", "Amy@gmail.com", "Willow bank ", "0214219786"));

        // ArrayList<Player> players = new ArrayList<Player>();
        // players.add(new Player("Sinead", "Murphy", "30/05/86", "Sinead@gmail.com", "Willow bank ", "0214219786"));
        // mDatabaseReference.push().setValue(new Team("Team 1",new Coach("TEST", "Test", "30/05/86", "pat@gmail.com", "Willow bank ", "0214219786"),players));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.action_logout) {
            // sign out using firebaseUI
            FirebaseAuth.getInstance().signOut();
            // Start the login activity
            startActivity(new Intent(this, SignInActivity.class));
            // done with this activity
            finish();
            return true;
        } else if (i==R.id.create) {
            startActivity(new Intent(MainActivity.this, CreateNew.class));
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
}
