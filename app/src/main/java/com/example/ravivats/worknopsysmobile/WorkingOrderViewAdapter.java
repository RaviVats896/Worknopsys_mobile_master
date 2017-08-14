package com.example.ravivats.worknopsysmobile;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class WorkingOrderViewAdapter extends RecyclerView
        .Adapter<WorkingOrderViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyOrdersViewAdapter";
    private ArrayList<WorkingOrderObject> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView task, weekDate, customerName, customerAddress, projectName;

        public DataObjectHolder(View itemView) {
            super(itemView);
            weekDate = (TextView) itemView.findViewById(R.id.weekDateText);
            task = (TextView) itemView.findViewById(R.id.taskText);
            customerName = (TextView) itemView.findViewById(R.id.customerNameText);
            customerAddress = (TextView) itemView.findViewById(R.id.customerAddressText);
            projectName = (TextView) itemView.findViewById(R.id.projectNameText);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public WorkingOrderViewAdapter(ArrayList<WorkingOrderObject> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.task.setText(mDataset.get(position).getmTask());
        holder.weekDate.setText(mDataset.get(position).getmWeekDate());
        holder.projectName.setText(mDataset.get(position).getmProjectName());
        holder.customerName.setText(mDataset.get(position).getmCustomerName());
        holder.customerAddress.setText(mDataset.get(position).getmCustomerAddress());
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