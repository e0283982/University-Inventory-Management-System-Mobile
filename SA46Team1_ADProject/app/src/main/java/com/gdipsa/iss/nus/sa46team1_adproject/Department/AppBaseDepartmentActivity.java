package com.gdipsa.iss.nus.sa46team1_adproject.Department;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.gdipsa.iss.nus.sa46team1_adproject.DescriptionSearchActivity;
import com.gdipsa.iss.nus.sa46team1_adproject.DisbursementListActivity;
import com.gdipsa.iss.nus.sa46team1_adproject.LoginActivity;
import com.gdipsa.iss.nus.sa46team1_adproject.QRCodeSearchActivity;
import com.gdipsa.iss.nus.sa46team1_adproject.R;
import com.gdipsa.iss.nus.sa46team1_adproject.RetrievalListActivity;

public class AppBaseDepartmentActivity extends AppCompatActivity implements MenuItem.OnMenuItemClickListener {

    private FrameLayout view_stub;
    private NavigationView navigation_view;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Menu drawerMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_app_department_base);
        view_stub = (FrameLayout) findViewById(R.id.view_department_stub);
        navigation_view = (NavigationView) findViewById(R.id.navigation_department_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.department_drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, 0, 0);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerMenu = navigation_view.getMenu();
        for(int i = 0; i < drawerMenu.size(); i++) {
            drawerMenu.getItem(i).setOnMenuItemClickListener(this);
        }

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String employeeRole = pref.getString("EmployeeRole", "Employee Role");

        if (employeeRole.equals("Employee")){
            //Set visibility of the drawer menu based on the roles
            drawerMenu.getItem(2).setVisible(false);
            drawerMenu.getItem(3).setVisible(false);
        }

        //Change the employee and employee email
        View headerView = navigation_view.getHeaderView(0);
        TextView employeeNameTextView = headerView.findViewById(R.id.textView_employee_name_nav_header_main);
        employeeNameTextView.setText(pref.getString("EmployeeName", "Employee Role"));

        TextView employeeEmailTextView = headerView.findViewById(R.id.textView_employee_email_nav_header_main);
        employeeEmailTextView.setText(pref.getString("EmployeeEmail", "Employee Email"));

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void setContentView(int layoutResID) {
        if (view_stub != null) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            View stubView = inflater.inflate(layoutResID, view_stub, false);
            view_stub.addView(stubView, lp);
        }
    }

    @Override
    public void setContentView(View view) {
        if (view_stub != null) {
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            view_stub.addView(view, lp);
        }
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (view_stub != null) {
            view_stub.addView(view, params);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_department_new_requisition) {

            Intent intent = new Intent(this, NewRequisitionActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_department_requisition_history) {

            Intent intent = new Intent(this, RequisitionHistoryActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_department_rep_collection_list) {

            Intent intent = new Intent(this, CollectionListRepActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_department_rep_req_history) {

            Intent intent = new Intent(this, DepartmentRepRequisitionHistoryActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_department_rep_qr_code) {

            Intent intent = new Intent(this, DepartmentRepQRCodeActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_department_logout) {

            SharedPreferences pref =
                    PreferenceManager.getDefaultSharedPreferences
                            (getApplicationContext());

            SharedPreferences.Editor editor = pref.edit();
            editor.clear();
            editor.commit();

            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }

        return false;
    }


}
