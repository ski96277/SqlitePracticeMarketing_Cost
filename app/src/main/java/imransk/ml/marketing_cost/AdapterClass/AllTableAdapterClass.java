package imransk.ml.marketing_cost.AdapterClass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import imransk.ml.marketing_cost.Activity.See_All_Info_selected_Table_List_Activity;
import imransk.ml.marketing_cost.Databse.DataOperation;
import imransk.ml.marketing_cost.R;


import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllTableAdapterClass extends RecyclerView.Adapter<AllTableAdapterClass.ViewClass> {

    Context context;
    List<String> alltable;

    public AllTableAdapterClass(@NonNull Context context, List<String> alltable) {

        this.context = context;
        this.alltable = alltable;
    }

    @NonNull
    @Override
    public ViewClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_view, null);

        return new ViewClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewClass viewClass, int i) {

        viewClass.dateTV.setText(alltable.get(i));
//get Total Cost
        DataOperation dataOperation = new DataOperation(context);
        int price = dataOperation.TotalPrice("[" + alltable.get(i) + "]");
        viewClass.total_cost.setText("Total =" + price);

    }

    @Override
    public int getItemCount() {
        return alltable.size();
    }

    public class ViewClass extends RecyclerView.ViewHolder {
        TextView dateTV;
        TextView total_cost;

        public ViewClass(@NonNull View itemView) {
            super(itemView);
            dateTV = itemView.findViewById(R.id.date_TV_ID);
            total_cost = itemView.findViewById(R.id.total_cost_TV_ID);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    String tableName = alltable.get(position);
                    context.startActivity(new Intent(context, See_All_Info_selected_Table_List_Activity.class)
                            .putExtra("table_name", tableName));
                }
            });
        }
    }
}
