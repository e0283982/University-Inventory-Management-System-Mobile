package com.gdipsa.iss.nus.sa46team1_adproject.Department;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.DisbursementList;
import com.gdipsa.iss.nus.sa46team1_adproject.DisbursementListDepartmentAdapter;
import com.gdipsa.iss.nus.sa46team1_adproject.R;

import java.util.ArrayList;
import java.util.List;

public class CollectionListRepActivity extends AppBaseDepartmentActivity {

    private RecyclerView mRecyclerViewDisbursementRepListDepartment;
    private CollectionListRepAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_list_rep);
        setTitle("Collection List");

        mRecyclerViewDisbursementRepListDepartment = findViewById(R.id.recycler_view_disbursement_list_rep);
        progressBar = findViewById(R.id.progressbar_disbursement_list_rep);

        //Temporary Data
        List<DisbursementList> result = new ArrayList<DisbursementList>();
        DisbursementList disbursementList = new DisbursementList("DH-074", "2018-08-02T00:00:00", "Registrar Dept", "Open", "University Hospital", "Edwin Teo");
        result.add(disbursementList);

        progressBar.setVisibility(View.GONE);
        adapter = new CollectionListRepAdapter(CollectionListRepActivity.this, result);
        mRecyclerViewDisbursementRepListDepartment.setAdapter(adapter);
        mRecyclerViewDisbursementRepListDepartment.setLayoutManager(new LinearLayoutManager(CollectionListRepActivity.this));

    }
}
