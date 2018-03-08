package tdkdesigns.hundredthieves;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import tdkdesigns.hundredthieves.Interface.ItemClickListener;
import tdkdesigns.hundredthieves.Model.MatchPanel;
import tdkdesigns.hundredthieves.ViewHolder.MatchViewHolder;

public class ScheduleList extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase database;
    DatabaseReference matchList;
    FirebaseRecyclerAdapter<MatchPanel, MatchViewHolder> adapter;

    TextView txtOpponent, txtDate;
    ImageView outcome;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    String scheduleId = "";
    String tournamentName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_list);

        //Process Intent Data
        if(getIntent() != null){
            scheduleId = getIntent().getStringExtra("ScheduleId");
            tournamentName = getIntent().getStringExtra("TournamentName");
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        if(!tournamentName.isEmpty()) {
            toolbar.setTitle(tournamentName);
        }
        else{
            toolbar.setTitle("Tournament Matches");
        }
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Initialize Firebase
        database = FirebaseDatabase.getInstance();
        matchList = database.getReference("Tournament");

        txtOpponent = findViewById(R.id.matchOpponent);
        txtDate = findViewById(R.id.matchDate);
        outcome = findViewById(R.id.matchOutcome);

        recyclerView = findViewById(R.id.recycler_schedule);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if(!scheduleId.isEmpty()){
            getMatchInfo(scheduleId);
        }
    }

    private void getMatchInfo(String scheduleId){
    adapter = new FirebaseRecyclerAdapter<MatchPanel, MatchViewHolder>(MatchPanel.class,
            R.layout.match_layout,
            MatchViewHolder.class,
            matchList.orderByChild("ScheduleID").equalTo(scheduleId)) {
        @Override
        protected void populateViewHolder(MatchViewHolder viewHolder, MatchPanel model, int position) {
            viewHolder.txtMatchOpponent.setText(model.getOpponent());
            viewHolder.txtMatchDate.setText(model.getDate());

            viewHolder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {

                }
            });
        }
    };

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.schedule_list, menu);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent home = new Intent(ScheduleList.this, Home.class);
            startActivity(home);
        } else if (id == R.id.nav_schedule) {
            Intent schedule = new Intent(ScheduleList.this, Schedule.class);
            startActivity(schedule);
        } else if (id == R.id.nav_team) {
            Intent roster = new Intent(ScheduleList.this, Roster.class);
            startActivity(roster);
        } else if (id == R.id.nav_media) {

        } else if (id == R.id.nav_store) {
            Intent store = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.100thieves.com/store/"));
            startActivity(store);
        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_contact) {
            Intent store = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.100thieves.com/contact-1/"));
            startActivity(store);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
