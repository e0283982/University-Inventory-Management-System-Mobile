package com.gdipsa.iss.nus.sa46team1_adproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.Employee;
import com.gdipsa.iss.nus.sa46team1_adproject.Department.RequisitionHistoryActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by Chirag Shetty
 */

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button signin;
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.login_username);
        password = (EditText) findViewById(R.id.login_password);
        signin = (Button) findViewById(R.id.login_signin);
        error = (TextView) findViewById(R.id.error_message);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username_value = username.getText().toString();
                String password_value = password.getText().toString();
                Employee employee = new Employee(username_value, password_value);
                HttpGenerateToken(employee);
            }
        });
    }

    public void HttpGenerateToken(Employee employee) {

        new AsyncTask<Employee, Void, Employee>() {
            @Override
            protected Employee doInBackground(Employee... params) {
                try {
                    params[0].setToken(Employee.getToken(params[0]));

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            username.setText("");
                            password.setText("");
                        }
                    });



//                    return params[0];
                } catch (MalformedURLException e) {

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            error.setText("Server Error. Please contact site admin");
                        }
                    });

                }catch(FileNotFoundException e){

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(getBaseContext(), "Username or password didn't match. Try again", Toast.LENGTH_LONG).show();
                        }
                    });

                    Log.e("postStream Exception", StackTrace.trace(e));

                } catch (IOException e) {

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            error.setText("Error in application. Please contact site admin");
                        }
                    });
                }

                return params[0];
            }
            @Override
            protected void onPostExecute(Employee result) {
                if(result.getToken() != ""){
                    HttpGetEmployee(result);
                }
                else{
                    //Code to show error message. Like Username or password not matched
                }
            }
        }.execute(employee);
    }
    public void HttpGetEmployee(Employee result){
        new AsyncTask<Employee, Void, Void>() {
            @Override
            protected Void doInBackground(Employee... params) {
                try {
                    Employee employee = Employee.getEmployeeRole(params[0]);

                    //Shared preferences
                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("EmployeeID", employee.getEmployeeId());
                    editor.putString("EmployeeName", employee.getEmployeeName());
                    editor.putString("DepartmentCode", employee.getDepartmentCode());
                    editor.putString("EmployeeRole", employee.getRole());
                    editor.putString("EmployeeEmail", employee.getEmail());
                    editor.commit();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void result) {
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                String employeeRole = pref.getString("EmployeeRole", "Employee Role");

                if (employeeRole.equals("Store Clerk")){
                    Intent intent = new Intent(LoginActivity.this, RetrievalListActivity.class);
                    startActivity(intent);
                    finish();
                } else if (employeeRole.equals("Employee")){
                    Intent intent = new Intent(LoginActivity.this, RequisitionHistoryActivity.class);
                    startActivity(intent);
                    finish();
                } else if (employeeRole.equals("Employee Representative")){
                    Intent intent = new Intent(LoginActivity.this, RequisitionHistoryActivity.class);
                    startActivity(intent);
                    finish();
                } else if (employeeRole.equals("Department Head") || employeeRole.equals("Store Manager") || employeeRole.equals("Store Supervisor")){
                    Toast.makeText(LoginActivity.this, "Not authorised", Toast.LENGTH_SHORT).show();
                }

            }
        }.execute(result);
    }
}

