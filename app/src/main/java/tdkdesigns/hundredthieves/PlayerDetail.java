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
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import tdkdesigns.hundredthieves.Model.PlayerPanel;

public class PlayerDetail extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase database;
    DatabaseReference players;

    String playerId = "";

    Toolbar toolbar;
    ImageView player_image;
    TextView player_name, player_bio, player_role;

    PlayerPanel currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        database = FirebaseDatabase.getInstance();
        players = database.getReference("Player");

        player_image = findViewById(R.id.player_image);
        player_name = findViewById(R.id.player_name);
        player_bio = findViewById(R.id.player_bio);
        player_role = findViewById(R.id.player_role);

        if(getIntent() !=null){
            playerId = getIntent().getStringExtra("PlayerId");
        }
        if(playerId != null && !playerId.isEmpty()){
            loadPlayerDetail(playerId);
        }
    }

    private void loadPlayerDetail(String playerId) {
        players.child(playerId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentPlayer = dataSnapshot.getValue(PlayerPanel.class);

                assert currentPlayer != null;
                toolbar.setTitle(currentPlayer.getName());
                Picasso.with(getBaseContext()).load(currentPlayer.getImage())
                        .into(player_image);
                player_name.setText(currentPlayer.getName());
                player_role.setText(currentPlayer.getRole());
                player_bio.setText(currentPlayer.getBio());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
        getMenuInflater().inflate(R.menu.player_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent home = new Intent(PlayerDetail.this, Home.class);
            startActivity(home);
        } else if (id == R.id.nav_schedule) {
            Intent schedule = new Intent(PlayerDetail.this, Schedule.class);
            startActivity(schedule);
        } else if (id == R.id.nav_team) {
            Intent roster = new Intent(PlayerDetail.this, Roster.class);
            startActivity(roster);
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
