package com.example.androidfoodapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidfoodapp.UserVariables.UVariables;
import com.example.androidfoodapp.ViewHolder.MenuViewHolder;
import com.example.androidfoodapp.databinding.ActivityHomePageBinding;
import com.example.androidfoodapp.model.ItemCategory;
import com.example.androidfoodapp.ui.basket.Basket;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomePageBinding binding;

    //Variables for database connection and table reference
    FirebaseDatabase database;
    DatabaseReference Item_Category;

    //Variable for user's name to be displayed on the app
    TextView txtUserName;

    RecyclerView recyclerMenu;
    RecyclerView.LayoutManager layoutManager;

    Button btnMenu;

    Button btnBasket;

    Button btnPastOrders;

    Button btnHelp;

    Button btnContact;

    Button btnSignOut;

    FirebaseRecyclerAdapter<ItemCategory, MenuViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Menu");
        setSupportActionBar(binding.appBarHomePage.toolbar);

        //firebase connection
        database = FirebaseDatabase.getInstance();
        Item_Category = database.getReference("Item_Category");

        btnMenu = (Button) findViewById(R.id.menubutton);
        btnBasket = (Button) findViewById(R.id.basketbutton);
        btnPastOrders = (Button) findViewById(R.id.pastOrdersbutton);
        btnHelp = (Button) findViewById(R.id.helpbutton);
        btnContact = (Button) findViewById(R.id.contactbutton);
        btnSignOut = (Button) findViewById(R.id.signOutbutton);


        btnMenu.setOnClickListener(view -> {
            Intent orderIntent = new Intent(HomePage.this, HomePage.class);
            startActivity(orderIntent);
        });

        btnBasket.setOnClickListener(view -> {
            Intent basketIntent = new Intent(HomePage.this, Basket.class);
            startActivity(basketIntent);
        });

        btnPastOrders.setOnClickListener(view -> {
            Intent orderIntent = new Intent(HomePage.this, ViewOrders.class);
            startActivity(orderIntent);
        });

        btnHelp.setOnClickListener(view -> {
            Intent orderIntent = new Intent(HomePage.this, helpMenu.class);
            startActivity(orderIntent);
        });

        btnContact.setOnClickListener(view -> {
            Intent orderIntent = new Intent(HomePage.this, ContactDetails.class);
            startActivity(orderIntent);
        });

        btnSignOut.setOnClickListener(view -> {
            Intent switchUser = new Intent(HomePage.this, Signin.class);
            switchUser.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(switchUser);
        });

        binding.appBarHomePage.fab.setOnClickListener(view -> {
            Intent basketIntent = new Intent(HomePage.this, Basket.class);
            startActivity(basketIntent);
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_page);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        //Displays name for currently logged in user
        View headerView = navigationView.getHeaderView(0);
        txtUserName = (TextView) headerView.findViewById(R.id.txtUserName);
        txtUserName.setText(UVariables.activeUser.getName());
        //Loading the menu
        recyclerMenu = (RecyclerView) findViewById(R.id.recycler_menu);
        recyclerMenu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerMenu.setLayoutManager(layoutManager);

        loadMenu();
    }
    private void loadMenu() {

        adapter = new FirebaseRecyclerAdapter<ItemCategory, MenuViewHolder>(ItemCategory.class, R.layout.menu_item, MenuViewHolder.class, Item_Category) {
            @Override
            protected void populateViewHolder(MenuViewHolder menuViewHolder, ItemCategory itemCategory, int pos) {
                menuViewHolder.txtMenuName.setText(itemCategory.getCategory());
                Picasso.get().load(itemCategory.getImage())
                        .into(menuViewHolder.imageView);
                final ItemCategory clickItem = itemCategory;
                menuViewHolder.setItemClickListener((view, position, isLongClick) -> {
                    //Get the category ID (CatId) to show correct items with each category
                    Intent itemList = new Intent(HomePage.this, ItemList.class);
                    itemList.putExtra("Item_CategoryId", adapter.getRef(position).getKey());
                    startActivity(itemList);
                });
            }
        };
        recyclerMenu.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_page);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        int id = item.getItemId();

        if (id == R.id.menu) {

        } else if (id == R.id.basket) {
            Intent basketIntent = new Intent(HomePage.this, Basket.class);
            startActivity(basketIntent);
        } else if (id == R.id.order) {
            Intent orderIntent = new Intent(HomePage.this, ViewOrders.class);
            startActivity(orderIntent);
        } else if (id == R.id.sign_out) {
            Intent switchUser = new Intent(HomePage.this, Signin.class);
            switchUser.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(switchUser);
        }

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}

