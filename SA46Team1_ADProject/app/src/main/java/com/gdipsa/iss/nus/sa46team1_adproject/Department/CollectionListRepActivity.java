package com.gdipsa.iss.nus.sa46team1_adproject.Department;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.DepartmentDetails;
import com.gdipsa.iss.nus.sa46team1_adproject.Data.DisbursementList;
import com.gdipsa.iss.nus.sa46team1_adproject.DisbursementListDepartmentActivity;
import com.gdipsa.iss.nus.sa46team1_adproject.DisbursementListDepartmentAdapter;
import com.gdipsa.iss.nus.sa46team1_adproject.R;

import java.util.ArrayList;
import java.util.List;

public class CollectionListRepActivity extends AppBaseDepartmentActivity {

    private RecyclerView mRecyclerViewDisbursementRepListDepartment;
    private CollectionListRepAdapter adapter;
    private ProgressBar progressBar;

    private String collectionPoint;
    String employeeDeptCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_list_rep);
        setTitle("Collection List");

        mRecyclerViewDisbursementRepListDepartment = findViewById(R.id.recycler_view_disbursement_list_rep);
        progressBar = findViewById(R.id.progressbar_disbursement_list_rep);

        //Temporary Data
//        List<DisbursementList> result = new ArrayList<DisbursementList>();
//        DisbursementList disbursementList = new DisbursementList("DH-074", "2018-08-02T00:00:00", "Registrar Dept", "Open", "University Hospital", "Edwin Teo");
//        result.add(disbursementList);
//
//        progressBar.setVisibility(View.GONE);
//        adapter = new CollectionListRepAdapter(CollectionListRepActivity.this, result);
//        mRecyclerViewDisbursementRepListDepartment.setAdapter(adapter);
//        mRecyclerViewDisbursementRepListDepartment.setLayoutManager(new LinearLayoutManager(CollectionListRepActivity.this));

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        employeeDeptCode = pref.getString("DepartmentCode", "Department Code");

        new DepartmentDetailsTask().execute();


//        new MyTask().execute(collectionPoint);

    }



    private class MyTask extends AsyncTask<String, Void, List<DisbursementList>> {
        @Override
        protected List<DisbursementList> doInBackground(String... params) {
            return DisbursementList.listDepartmentDisbursementList(params[0]);
        }
        @Override
        protected void onPostExecute(List<DisbursementList> result) {

            if (result.size() == 0){
                TextView emptyTextView = findViewById(R.id.textView_disbursement_list_empty_rep);
                emptyTextView.setVisibility(View.VISIBLE);
            }

            progressBar.setVisibility(View.GONE);
            adapter = new CollectionListRepAdapter(CollectionListRepActivity.this, result);
            mRecyclerViewDisbursementRepListDepartment.setAdapter(adapter);
            mRecyclerViewDisbursementRepListDepartment.setLayoutManager(new LinearLayoutManager(CollectionListRepActivity.this));

        }
    }


    private class DepartmentDetailsTask extends AsyncTask<Void, Void, List<DepartmentDetails>> {
        @Override
        protected List<DepartmentDetails> doInBackground(Void... params) {
            return DepartmentDetails.departmentDetailsList();
        }
        @Override
        protected void onPostExecute(List<DepartmentDetails> result) {

            for(DepartmentDetails departmentDetails : result){
                if (departmentDetails.getDepartmentCode().equals(employeeDeptCode)){
                    collectionPoint = departmentDetails.getCollectionPointName();
                }
            }

            new MyTask().execute(collectionPoint);





        }
    }





}
