package com.example.myfinalproject;

        import android.content.Context;
        import android.content.Intent;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.webkit.SafeBrowsingResponse;
        import android.widget.Button;
        import android.widget.EdgeEffect;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> {

    // variable for our array list and context
    String quantxt;
    private ArrayList<farmerslist> farmerArrayList;
    private Context context;
    boolean change= true;


    public Myadapter(ArrayList<farmerslist> farmerArrayList, Context context) {
        this.farmerArrayList = farmerArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lidt_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        farmerslist modal = farmerArrayList.get(position);
        holder.farmer_name.setText(modal.getId());
        holder.cost_milk.setText(modal.getcost()+" Rs");
        holder.avail_of_milk.setText(modal.getavail()+" L");
        holder.quantity.setText("");
       holder.quantity.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               change=true;

           }

           @Override
           public void afterTextChanged(Editable editable) {
             if (change){  change=false;}
              quantxt= editable.toString();

           }
       });
        holder.Buy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String farid= modal.getId();
                String fcost=modal.getcost();
                String fmilk=modal.getavail();

                Intent intent=new Intent(context,order.class);
                intent.putExtra("quantity",quantxt);
                intent.putExtra("fid",farid);
                intent.putExtra("fcost",fcost);
                intent.putExtra("fmilk",fmilk);

                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return farmerArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView farmer_name, cost_milk, avail_of_milk;
        private EditText quantity;

       private Button Buy;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            farmer_name = itemView.findViewById(R.id.Name_farmer);
            quantity = itemView.findViewById(R.id.quantity_milk);
            cost_milk = itemView.findViewById(R.id.cost_milk);
            avail_of_milk = itemView.findViewById(R.id.avail_milk);
            Buy=itemView.findViewById(R.id.buy);
        }
    }
}

