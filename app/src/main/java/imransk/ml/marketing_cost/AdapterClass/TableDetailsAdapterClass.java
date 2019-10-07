package imransk.ml.marketing_cost.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import imransk.ml.marketing_cost.POJOClass.Info_POJO;
import imransk.ml.marketing_cost.R;



import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TableDetailsAdapterClass extends RecyclerView.Adapter<TableDetailsAdapterClass.Viewclass> {


    Context context;
    List<Info_POJO> info_pojos;


    public TableDetailsAdapterClass(@NonNull Context context, List<Info_POJO> info_pojos) {
        this.context = context;
        this.info_pojos = info_pojos;
    }

    @NonNull
    @Override
    public Viewclass onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_view_edit, null);

        return new Viewclass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewclass viewclass, int i) {

//        viewclass.serial.setText(info_pojos.get(i).getId());
        viewclass.serial.setText(String.valueOf(i + 1));
        viewclass.item.setText(info_pojos.get(i).getItem());
        viewclass.price.setText(info_pojos.get(i).getPrice() + " Tk");

    }

    @Override
    public int getItemCount() {
        return info_pojos.size();
    }

    public class Viewclass extends RecyclerView.ViewHolder {
        TextView item;
        TextView price;
        TextView serial;


        public Viewclass(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.date_TV_ID);
            price = itemView.findViewById(R.id.total_cost_TV_ID);
            serial = itemView.findViewById(R.id.serial_TV);
        }
    }
}
