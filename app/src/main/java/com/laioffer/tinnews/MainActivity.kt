package com.laioffer.tinnews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private var navController: NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView = findViewById<BottomNavigationView>(R.id.nav_view)
        val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?

        // 控制中间的flow
        // give control to fragment
        navController = navHostFragment!!.navController
        // give controller to navigation view, so they bind together
        NavigationUI.setupWithNavController(navView, navController!!)
        // give controller to the action bar (on the top)
//        NavigationUI.setupActionBarWithNavController(this, navController);

//        NewsApi api = RetrofitClient.newInstance(this).create(NewsApi.class);
//
//        // trigger the function when you have the data to call the function back
//        api.getTopHeadlines("US").enqueue(new Callback<NewsResponse>() {
//            @Override
//            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
//                if (response.isSuccessful()) {
//                    Log.d("getTopHeadlines", response.body().toString());
//                } else {
//                    Log.d("getTopHeadlines", response.toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<NewsResponse> call, Throwable t) {
//                Log.d("getTopHeadlines", t.toString());
//            }
//        });
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController!!.navigateUp()
    }
}