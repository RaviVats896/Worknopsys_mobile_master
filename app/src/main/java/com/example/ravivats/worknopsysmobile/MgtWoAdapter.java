package com.example.ravivats.worknopsysmobile;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MgtWoAdapter extends RecyclerView
        .Adapter<MgtWoAdapter
        .DataObjectHolder> {
    private static String LOG_TAG_MGT = "MgtWoAdapter";
    private ArrayList<WorkingOrderObject> mDataset;
    private static MgtWoAdapter.MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView weekDayMgt, weekDateMgt, customerNameMgt, customerAddressMgt, projectNameMgt, activityMgt;

        public DataObjectHolder(View itemView) {
            super(itemView);
            weekDateMgt = (TextView) itemView.findViewById(R.id.weekDateTextMgt);
            weekDayMgt = (TextView) itemView.findViewById(R.id.weekDayTextMgt);
            customerNameMgt = (TextView) itemView.findViewById(R.id.customerNameTextMgt);
            customerAddressMgt = (TextView) itemView.findViewById(R.id.customerAddressTextMgt);
            projectNameMgt = (TextView) itemView.findViewById(R.id.projectNameTextMgt);
            activityMgt = (TextView) itemView.findViewById(R.id.activityTextMgt);
            Log.i(LOG_TAG_MGT, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MgtWoAdapter.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public MgtWoAdapter(ArrayList<WorkingOrderObject> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public MgtWoAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row_mgt, parent, false);

        MgtWoAdapter.DataObjectHolder dataObjectHolder = new MgtWoAdapter.DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(MgtWoAdapter.DataObjectHolder holder, int position) {
        holder.weekDayMgt.setText(mDataset.get(position).getmTask());
        holder.weekDateMgt.setText(mDataset.get(position).getmWeekDate());
        holder.projectNameMgt.setText(mDataset.get(position).getmProjectName());
        holder.activityMgt.setText(mDataset.get(position).getmActivity());
        holder.customerNameMgt.setText(mDataset.get(position).getmCustomerName());
        holder.customerAddressMgt.setText(mDataset.get(position).getmCustomerAddress());
    }

    public void addItem(WorkingOrderObject dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
