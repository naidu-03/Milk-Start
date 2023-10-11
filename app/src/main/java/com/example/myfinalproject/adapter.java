package com.example.myfinalproject;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {
    Context context;
    ArrayList<history> list;
    public adapter(Context context,ArrayList<history>list) {
        this.context = context;
        this.list=list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.table_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        history l=list.get(position);
        holder.date.setText(l.getdate());
        holder.cost_of.setText(l.getcost());
        holder.milk_quan.setText(l.getorder());
        holder.total.setText(l.gettotal());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView date, cost_of,milk_quan,total;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            date = itemView.findViewById(R.id.date);
            total=itemView.findViewById(R.id.total);
            cost_of = itemView.findViewById(R.id.costof);
            milk_quan = itemView.findViewById(R.id.quan);
        }
    }
}
