package tdkdesigns.hundredthieves;

import android.content.Intent;
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
import tdkdesigns.hundredthieves.Model.Panel;
import tdkdesigns.hundredthieves.ViewHolder.PanelViewHolder;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase database;
    DatabaseReference panel;

    RecyclerView recycler_panel;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Panel, PanelViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Firebase
        database = FirebaseDatabase.getInstance();
        panel = database.getReference("Panel");

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
        //recycler_panel.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_panel.setLayoutManager(layoutManager);

        loadPanels();
    }

    private void loadPanels(){
        Toast.makeText(Home.this, "Test!",
                Toast.LENGTH_LONG).show();
        adapter = new FirebaseRecyclerAdapter<Panel, PanelViewHolder>(Panel.class,
                R.layout.panel_list,
                PanelViewHolder.class,
                panel) {
            @Override
            protected void populateViewHolder(PanelViewHolder viewHolder, Panel model, int position) {

                Toast.makeText(Home.this, "This is my Toast message!",
                        Toast.LENGTH_LONG).show();
                viewHolder.txtPanelName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imageView);

                final Panel clickItem = model;

                viewHolder.setItemClickListener(new ItemClickListener(){
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent PanelAction = new Intent(Home.this, MainActivity.class);
                        PanelAction.putExtra("PanelId",adapter.getRef(position).getKey());
                        PanelAction.putExtra("PanelName", clickItem.getName());
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
            // Handle the camera action
        } else if (id == R.id.nav_schedule) {

        } else if (id == R.id.nav_team) {

        } else if (id == R.id.nav_media) {

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_contact) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
