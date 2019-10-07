package imransk.ml.marketing_cost.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import imransk.ml.marketing_cost.AdapterClass.AllTableAdapterClass;
import imransk.ml.marketing_cost.Databse.DataOperation;
import imransk.ml.marketing_cost.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class All_TableActivity extends AppCompatActivity {

    List<String> alltable;
    RecyclerView recyclerView;
    DataOperation dataOperation;
    AllTableAdapterClass allTableAdapterClass;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell__table);
        //set title
        getSupportActionBar().setTitle(getResources().getString(R.string.all_table_list_title));

        recyclerView = findViewById(R.id.recyclerView_ID);

        dataOperation = new DataOperation(this);
        alltable = dataOperation.getAllTable();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        allTableAdapterClass = new AllTableAdapterClass(this, alltable);
        recyclerView.setAdapter(allTableAdapterClass);

//action on recycler item

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();

                final AlertDialog.Builder builder = new AlertDialog.Builder(All_TableActivity.this);
                builder.setTitle("Delete ?")
                        .setMessage("Do you want to delete this ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dataOperation.table_Delete("[" + alltable.get(position) + "]");

                                allTableAdapterClass = new AllTableAdapterClass(All_TableActivity.this, dataOperation.getAllTable());
                                recyclerView.setAdapter(allTableAdapterClass);

                                Toast.makeText(All_TableActivity.this, "deleted", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                allTableAdapterClass = new AllTableAdapterClass(All_TableActivity.this, dataOperation.getAllTable());
                                recyclerView.setAdapter(allTableAdapterClass);
                                builder.setCancelable(true);

                            }
                        })
                        .setCancelable(true)
                        .create().show();


            }
        }).attachToRecyclerView(recyclerView);

    }
}
