package com.example.anders.createuserapp;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

public class UtilityBuilders {

    public static void buildBottomNavigationView(final Activity activity){
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                activity.findViewById(R.id.bottomNavView_Bar);
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.id_profile:
                        Intent intent1 = new Intent(activity, ProfileActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        activity.startActivity(intent1);
                        //activity.startActivity(new Intent(activity, ProfileActivity.class));
                        break;

                    case R.id.id_collect:
                        Intent intent3 = new Intent (activity, CollectOverviewActivity.class);
                        intent3.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        activity.startActivity(intent3);
                        //mIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        //activity.startActivity(new Intent(activity, CollectOverviewActivity.class));
                        break;
                }
                return;
            }
        });
    }
}
