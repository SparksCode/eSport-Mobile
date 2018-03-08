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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import tdkdesigns.hundredthieves.Interface.ItemClickListener;
import tdkdesigns.hundredthieves.Model.SchedulePanel;
import tdkdesigns.hundredthieves.ViewHolder.ScheduleViewHolder;

public class Schedule extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase database;
    DatabaseReference schedule;

    RecyclerView recycler_schedule;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<SchedulePanel, ScheduleViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        //Firebase
        database = FirebaseDatabase.getInstance();
        schedule = database.getReference("Schedule");

        //Navigation
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("100 Thieves");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Load Schedule
        recycler_schedule = findViewById(R.id.recycler_schedule);
        recycler_schedule.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_schedule.setLayoutManager(layoutManager);

        //Populate Schedule Panels
        loadSchedules();
    }

    private void loadSchedules(){
        adapter = new FirebaseRecyclerAdapter<SchedulePanel, ScheduleViewHolder>(SchedulePanel.class,
                R.layout.schedule_list,
                ScheduleViewHolder.class,
                schedule) {
            @Override
            protected void populateViewHolder(final ScheduleViewHolder viewHolder, final SchedulePanel model, int position) {
                viewHolder.txtScheduleName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imageView);

                viewHolder.setItemClickListener(new ItemClickListener(){
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent match = new Intent(Schedule.this, ScheduleList.class);
                        match.putExtra("ScheduleId", adapter.getRef(position).getKey());
                        match.putExtra("TournamentName", viewHolder.txtScheduleName.getText());
                        startActivity(match);
                    }
                });
            }
        };
        recycler_schedule.setAdapter(adapter);
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
        getMenuInflater().inflate(R.menu.schedule, menu);
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
            Intent home = new Intent(Schedule.this, Home.class);
            startActivity(home);
        } else if (id == R.id.nav_schedule) {
            //Intent schedule = new Intent(Schedule.this, Schedule.class);
            //startActivity(schedule);
        } else if (id == R.id.nav_team) {
            Intent roster = new Intent(Schedule.this, Roster.class);
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
