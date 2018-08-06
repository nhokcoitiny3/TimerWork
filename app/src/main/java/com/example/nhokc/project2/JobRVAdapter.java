package com.example.nhokc.project2;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.nhokc.project2.databinding.ItemRvListCvBinding;

public class JobRVAdapter extends RecyclerView.Adapter<JobRVAdapter.ViewHolder> {
    private IJob iJob;
    private Context context;
    private int lastPosition = -1;
    private ItemRvListCvBinding binding;
    public void setIdataTimer(IJob iJob) {
        this.iJob = iJob;
    }

    public JobRVAdapter(Context context) {
        this.context= context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_rv_list_cv,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Job job = iJob.getJob(position);
        holder.binding.txtJob.setText(job.toString());
    }

    @Override
    public int getItemCount() {
        return iJob.getCount();
    }

    public void addItem(int i) {
        notifyItemInserted(i);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ItemRvListCvBinding binding;
        public ViewHolder(ItemRvListCvBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    public interface IJob{
        int getCount();
        Job getJob(int position);
    }

}
