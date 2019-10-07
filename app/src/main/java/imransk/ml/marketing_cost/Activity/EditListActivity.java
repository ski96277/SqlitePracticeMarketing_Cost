package imransk.ml.marketing_cost.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import imransk.ml.marketing_cost.AdapterClass.AdapterClass_Edit;
import imransk.ml.marketing_cost.Databse.DataOperation;
import imransk.ml.marketing_cost.Databse.DatabaseHelperClass;
import imransk.ml.marketing_cost.POJOClass.Info_POJO;
import imransk.ml.marketing_cost.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EditListActivity extends AppCompatActivity {

    private RecyclerView editrecyler;
    List<Info_POJO> info_pojos = new ArrayList<>();
    DataOperation dataOperation;
    TextView totalCostTV;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);
        this.editrecyler = (RecyclerView) findViewById(R.id.edit_recyler);
        totalCostTV=findViewById(R.id.totalCost_TV);
        //set title
        getSupportActionBar().setTitle(getResources().getString(R.string.edit_list_Title));

        dataOperation = new DataOperation(this);
        info_pojos = dataOperation.get_ALL_Info_Table();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        editrecyler.setLayoutManager(linearLayoutManager);

        final AdapterClass_Edit adapterClassEdit = new AdapterClass_Edit(this, info_pojos);
        adapterClassEdit.notifyDataSetChanged();
        editrecyler.setAdapter(adapterClassEdit);

        //set Total price
        totalCostTV.setText(getResources().getString(R.string.total_text)+dataOperation.TotalPrice(DatabaseHelperClass.table_name));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();

                final AlertDialog.Builder builder=new AlertDialog.Builder(EditListActivity.this);
                builder.setTitle(getResources().getString(R.string.delete_alert_title))
                        .setMessage(getResources().getString(R.string.delete_alert_message))
                        .setPositiveButton(getResources().getString(R.string.delete_yes_btn), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String id = info_pojos.get(position).getId();
                                boolean status = dataOperation.deleteUser(id);

                                if (status) {
                                    //set Total price
                                    totalCostTV.setText(getResources().getString(R.string.total_text)+dataOperation.TotalPrice(DatabaseHelperClass.table_name));

                                    adapterClassEdit.setInfo_pojos(dataOperation.get_ALL_Info_Table());
                                    Toast.makeText(EditListActivity.this, getResources().getString(R.string.delete_text), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(EditListActivity.this, getResources().getString(R.string.cancel_text), Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.no_text), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                builder.setCancelable(true);
                                adapterClassEdit.setInfo_pojos(dataOperation.get_ALL_Info_Table());
                            }
                        })
                        .setCancelable(true)
                        .create().show();


            }
        }).attachToRecyclerView(editrecyler);

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EditListActivity.this,MainActivity.class)
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK));

    }
}