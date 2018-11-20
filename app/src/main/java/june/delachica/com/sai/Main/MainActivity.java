package june.delachica.com.sai.Main;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import io.ghyeok.stickyswitch.widget.StickySwitch;
import june.delachica.com.sai.Modules.Chat.ChatbotFragment;
import june.delachica.com.sai.Modules.DashboardFragment;
import june.delachica.com.sai.Modules.Diary.DiaryFragment;
import june.delachica.com.sai.Modules.Mood.MoodFragment;
import june.delachica.com.sai.R;
import june.delachica.com.sai.Modules.Settings.SettingsFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    TextView myTV;
    Typeface myFont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        myTV = (TextView)findViewById(R.id.TextView);
//        myFont = Typeface.createFromAsset(this.getAssets(),"fonts/._AvenirNextLTPro-Regular.otf");
//        myFont = Typeface.createFromAsset(this.getAssets(),"fonts/._AvenirNextLTPro-Heavy.otf");
//        myFont = Typeface.createFromAsset(this.getAssets(),"fonts/._AvenirNextLTPro-Bold.otf");
//        myFont = Typeface.createFromAsset(this.getAssets(),"fonts/._AvenirNextLTPro-Demi.otf");

//        StickySwitch stickySwitch = findViewById(R.id.stick_switch);
//        stickySwitch.setOnSelectedChangeListener(new StickySwitch.OnSelectedChangeListener(){
//            @Override
//            public void onSelectedChange(@NotNull StickySwitch.Direction direction, @NotNull String s){
//                Toast.makeText(getBaseContext(),"You have selected" +s, Toast.LENGTH_SHORT).show();
//            }
//        });

//        CardView chatSai = findViewById(R.id.chatSai);
//        chatSai.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(MainActivity.this, SaiChatBot.class);
//                startActivity(i);
//            }
//        });
//
//        CardView thoughtJournal = findViewById(R.id.thoughtJournal);
//        thoughtJournal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(MainActivity.this, ThoughtJournal.class);
//                startActivity(i);
//            }
//        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
//            navigationView.setCheckedItem(R.id.nav_view);
//        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navDashboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardFragment()).commit();
                break;
            case R.id.navJournal:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DiaryFragment()).commit();
                break;
            case R.id.navChatSai:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ChatbotFragment()).commit();
                break;
            case R.id.navTrackMood:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MoodFragment()).commit();
                break;
            case R.id.navSettings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}