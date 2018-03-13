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
import tdkdesigns.hundredthieves.Model.PlayerPanel;
import tdkdesigns.hundredthieves.ViewHolder.PlayerViewHolder;

public class PlayerList extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase database;
    DatabaseReference playerList;
    FirebaseRecyclerAdapter<PlayerPanel, PlayerViewHolder> adapter;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    String teamId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roster_list);

        database = FirebaseDatabase.getInstance();
        playerList = database.getReference("Player");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Roster");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = findViewById(R.id.recycler_player);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if(getIntent() !=null){
           teamId = getIntent().getStringExtra("TeamId");
        }
        if(teamId != null && !teamId.isEmpty()){
            String teamName = getIntent().getStringExtra("TeamName");
            toolbar.setTitle(teamName);
            loadPlayerList(teamId);
        }

    }

    private void loadPlayerList(String teamId) {
        adapter = new FirebaseRecyclerAdapter<PlayerPanel, PlayerViewHolder>(PlayerPanel.class,
                R.layout.player_layout,
                PlayerViewHolder.class,
                playerList.orderByChild("TeamId").equalTo(teamId)
        ) {
            @Override
            protected void populateViewHolder(PlayerViewHolder viewHolder, final PlayerPanel model, int position) {
                viewHolder.txtPlayerName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imageView);
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent playerDetail = new Intent(PlayerList.this, PlayerDetail.class);
                        playerDetail.putExtra("PlayerId", adapter.getRef(position).getKey());
                        startActivity(playerDetail);
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
        getMenuInflater().inflate(R.menu.roster_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent home = new Intent(PlayerList.this, Home.class);
            startActivity(home);
        } else if (id == R.id.nav_schedule) {
            Intent schedule = new Intent(PlayerList.this, Schedule.class);
            startActivity(schedule);
        } else if (id == R.id.nav_team) {
            Intent roster = new Intent(PlayerList.this, Roster.class);
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
