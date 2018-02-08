package com.pawras.android.basketballclub.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.pawras.android.basketballclub.R;
import com.pawras.android.basketballclub.models.CoachAndPlayer;
import java.util.List;

public class PopulateListOrders extends RecyclerView.Adapter<PopulateListOrders.MyViewHolder> {

    private List<CoachAndPlayer> orderList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView customerName, requiredPerson, orderStatus,status;

        public MyViewHolder(View view) {
            super(view);
            customerName = (TextView) view.findViewById(R.id.customerName);
            requiredPerson = (TextView) view.findViewById(R.id.requiredPerson);
        }
    }


    public PopulateListOrders(List<CoachAndPlayer> orderList) {
        this.orderList = orderList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_order, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CoachAndPlayer mDisplayOrders = orderList.get(position);
        holder.customerName.setText(mDisplayOrders.getFirst_name()+" "+mDisplayOrders.getSure_name());
        holder.requiredPerson.setText(mDisplayOrders.getContact_number());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}