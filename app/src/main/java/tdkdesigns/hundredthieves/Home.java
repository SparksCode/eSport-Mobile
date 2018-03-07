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
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import tdkdesigns.hundredthieves.Interface.ItemClickListener;
import tdkdesigns.hundredthieves.Model.HomePanel;
import tdkdesigns.hundredthieves.ViewHolder.PanelViewHolder;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase database;
    DatabaseReference panel;

    RecyclerView recycler_panel;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<HomePanel, PanelViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Firebase
        database = FirebaseDatabase.getInstance();
        panel = database.getReference("HomePanel");

        // Navigation
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

        //Load Panels
        recycler_panel = findViewById(R.id.recycler_panel);
        recycler_panel.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_panel.setLayoutManager(layoutManager);

        //Populate Link Panels
        loadPanels();

        //Welcome Message
        Toast.makeText(Home.this, "Welcome to the Den!", Toast.LENGTH_LONG).show();
    }

    private void loadPanels(){
        adapter = new FirebaseRecyclerAdapter<HomePanel, PanelViewHolder>(HomePanel.class,
                R.layout.panel_list,
                PanelViewHolder.class,
                panel) {
            @Override
            protected void populateViewHolder(PanelViewHolder viewHolder, final HomePanel model, int position) {
                viewHolder.txtPanelName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imageView);

                //final HomePanel clickItem = model;
                final String URL = model.getURL();

                viewHolder.setItemClickListener(new ItemClickListener(){
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent webBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                        startActivity(webBrowser);
                    }
                });
            }
        };
        recycler_panel.setAdapter(adapter);
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
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            //Intent home = new Intent(Home.this, Home.class);
            //startActivity(home);
        } else if (id == R.id.nav_schedule) {
            Intent schedule = new Intent(Home.this, Schedule.class);
            startActivity(schedule);
        } else if (id == R.id.nav_team) {
            Intent roster = new Intent(Home.this, Roster.class);
            startActivity(roster);
        } else if (id == R.id.nav_media) {

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_store) {
            Intent store = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.100thieves.com/store/"));
            startActivity(store);
        } else if (id == R.id.nav_contact) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
