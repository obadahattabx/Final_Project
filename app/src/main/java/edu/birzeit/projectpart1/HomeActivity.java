package edu.birzeit.projectpart1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import edu.birzeit.projectpart1.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;
    MenuItem logut;
    public static int c=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBaseHelper dataBaseHelper = new
                DataBaseHelper(HomeActivity.this, MainActivity.nameDatabase, null, 1);

//        dataBaseHelper.Update_proprtyAfterjason(ProgresBarAnimation.proepertJson.get(0),
//                       String.valueOf(0));


        for(int i=0;i<ProgresBarAnimation.proepertJson.size();i++){


            if(dataBaseHelper.checkProperty_id(ProgresBarAnimation.proepertJson.get(i).getID())) {
                System.out.println(ProgresBarAnimation.proepertJson.get(i).getID());
                dataBaseHelper.insertjasonProperty(ProgresBarAnimation.proepertJson.get(i));
            }

        }

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarHome.toolbar);
        binding.appBarHome.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_postproperty, R.id.nav_slideshow,R.id.nav_search,R.id.nav_profile_agency,R.id.nav_profile_tenent,R.id.nav_edit,R.id.logut_frag,R.id.nav_notifiction,R.id.nav_historyAgency,R.id.historyList_tenant)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        navigationView.getMenu().findItem(R.id.nav_historyTenant).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_historyAgency).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_profile_tenent).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_profile_agency).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_postproperty).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_edit).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_search).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_notifiction).setVisible(true);


        if (MainActivity.type_user.equals("TENANT") ) {
            navigationView.getMenu().findItem(R.id.nav_historyAgency).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_profile_agency).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_postproperty).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_edit).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_notifiction).setVisible(false);
        }
        else if(MainActivity.type_user.equals("GEST")){
            navigationView.getMenu().findItem(R.id.nav_historyTenant).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_profile_tenent).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_historyAgency).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_profile_agency).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_postproperty).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_edit).setVisible(false);

        }
        else{
            navigationView.getMenu().findItem(R.id.nav_historyTenant).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_profile_tenent).setVisible(false);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logut:
                try {
                    Intent intent=new Intent(HomeActivity.this,Login.class);
                    intent.cloneFilter();
                    startActivity(intent);

                }catch (Exception e){

                }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logut_frag:
                try {
                    Intent intent=new Intent(HomeActivity.this,Login.class);
                    startActivity(intent);
                    break;
                }
                catch (Exception e){

                }

        }
        return false;
    }
}