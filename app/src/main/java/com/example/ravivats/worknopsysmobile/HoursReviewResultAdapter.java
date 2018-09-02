package com.example.ravivats.worknopsysmobile;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ravi Vats on 02-09-2018.
 */

public class HoursReviewResultAdapter extends RecyclerView
        .Adapter<HoursReviewResultAdapter
        .WODataObjectHolder> {
    private static String LOG_TAG = "HrsReviewResultAdapter";
    private ArrayList<HoursReviewResultsObject> mDataset;
    private static HoursReviewResultAdapter.MyClickListener myClickListener;

    public static class WODataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView goingTime, workingTime, breakTime, returningTime, workDate;

        public WODataObjectHolder(View itemView) {
            super(itemView);
            goingTime = (TextView) itemView.findViewById(R.id.card_wo_goingTime);
            workingTime = (TextView) itemView.findViewById(R.id.card_wo_workingTime);
            breakTime = (TextView) itemView.findViewById(R.id.card_wo_breakTime);
            returningTime = (TextView) itemView.findViewById(R.id.card_wo_returningTime);
            workDate = (TextView) itemView.findViewById(R.id.card_wo_workDate);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }

    }

    public void setOnItemClickListener(HoursReviewResultAdapter.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public HoursReviewResultAdapter(ArrayList<HoursReviewResultsObject> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public HoursReviewResultAdapter.WODataObjectHolder onCreateViewHolder(ViewGroup parent,
                                                                       int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row_hours_review, parent, false);

        HoursReviewResultAdapter.WODataObjectHolder dataObjectHolder = new HoursReviewResultAdapter.WODataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(HoursReviewResultAdapter.WODataObjectHolder holder, int position) {
        holder.goingTime.setText(mDataset.get(position).getGoingTime());
        holder.workingTime.setText(mDataset.get(position).getWorkingTime());
        holder.breakTime.setText(mDataset.get(position).getBreakTime());
        holder.returningTime.setText(mDataset.get(position).getReturningTime());
        holder.workDate.setText(mDataset.get(position).getWorkDate());
    }

    public void addItem(HoursReviewResultsObject dataObj, int index) {
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
