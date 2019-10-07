package imransk.ml.marketing_cost.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import imransk.ml.marketing_cost.AdapterClass.TableDetailsAdapterClass;
import imransk.ml.marketing_cost.R;


import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import imransk.ml.marketing_cost.Databse.DataOperation;
import imransk.ml.marketing_cost.POJOClass.Info_POJO;

public class See_All_Info_selected_Table_List_Activity extends AppCompatActivity {

    List<Info_POJO> info_pojos;
    RecyclerView recyclerView;
    DataOperation dataOperation;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see__all__list);
        recyclerView = findViewById(R.id.recyclerView_ID);


        Intent intent = getIntent();
        String table_Name = intent.getStringExtra("table_name");

        dataOperation = new DataOperation(this);
        info_pojos = dataOperation.get_Table_Info("[" + table_Name + "]");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        TableDetailsAdapterClass adapterClass = new TableDetailsAdapterClass(this, info_pojos);
        recyclerView.setAdapter(adapterClass);

        recyclerView.setLayoutManager(linearLayoutManager);

    }
}
