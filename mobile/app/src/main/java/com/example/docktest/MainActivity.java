package com.example.docktest;
/**
 *
 * @author Haoyang Cui
 * @Purpose: This is the main activity of the application. There are four Dock Buttons in this
 * page, which are CreateCase, ViewNyCase, CaseReport And Login&Register. Users can jump to any
 * page of these four by attaching the related button. All the related pages extends Fragment
 * according to the customer's requirement. If users start the application for the first time,
 * it will jump into the CreateCase fragment by default so that users can submit a case report
 * as fast as them could.
 * */


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import Fragments.ViewMyCases;

public class MainActivity extends AppCompatActivity {

    //private TextView mTextMessage;
    private Button mSubmitButton;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        /**
         *  Listening to the users' attachment on the button.
         * */
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    // jump into CreateCase Page
                    replaceFragment(new CreateCase());
                    return true;
                case R.id.navigation_mycase:
                    // if it's login user, jump into ViewMyCase page
                    if(Utils.Data.getLoginState()){
                        replaceFragment(new ViewMyCases());
                        return true;
                    }
                    else{
                        // if guest, show the toast that they need to login first
                        Toast.makeText(MainActivity.this, "Sorry, you need to login first",
                                Toast.LENGTH_SHORT).show();
                        replaceFragment(new Unlogin_mycase_page());
                        return true;
                    }
                case R.id.navigation_dashboard:
                    // jump into the View Case Report Page
                    replaceFragment(new ViewReportSelection());
                    return true;
                case R.id.navigation_notifications:
                    // for login user, they can update personal info or logout by touching the button
                    if(Utils.Data.getLoginState()){
                        replaceFragment(new EditPersonalInfo());
                        return true;
                    }
                    else{ // for guest, jump into the Login & Register Page
                        Intent intent2= new Intent(MainActivity.this,Login.class) ;
                        startActivity(intent2);
                        finish();
                        return true;
                    }

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // initialize all the related components
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSubmitButton = (Button) findViewById(R.id.button);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // jump into Create Case page by default
        getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.right_layout,new CreateCase())
                    .addToBackStack(null)
                    .commit();

    }

    // the method is used by the replacement of fragment
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.right_layout, fragment);
        transaction.commit();
    }

}
