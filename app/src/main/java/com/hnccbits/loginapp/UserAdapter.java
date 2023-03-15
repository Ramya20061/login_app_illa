package com.hnccbits.loginapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hnccbits.loginapp.databinding.UserItemBinding;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private final Context context;

    private final AsyncListDiffer<UserModel> mDiffer;

    public UserAdapter(Context context) {
        this.context = context;
        mDiffer = new AsyncListDiffer<>(this, new UserDiffUtilCallback());
    }

    public void submitList(List<UserModel> newList) {
        mDiffer.submitList(newList);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(UserItemBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserModel userModel = mDiffer.getCurrentList().get(position);
        UserItemBinding binding = holder.binding;
        binding.name.setText(userModel.getName());
        binding.email.setText(userModel.getEmail());
        binding.city.setText(userModel.getCity());
        binding.age.setText(String.valueOf(userModel.getAge()));
    }

    @Override
    public int getItemCount() {
        return mDiffer.getCurrentList().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public UserItemBinding binding;

        public ViewHolder(UserItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
