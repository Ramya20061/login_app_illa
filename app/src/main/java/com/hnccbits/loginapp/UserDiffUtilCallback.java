package com.hnccbits.loginapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class UserDiffUtilCallback extends DiffUtil.ItemCallback<UserModel> {

    @Override
    public boolean areItemsTheSame(@NonNull UserModel oldItem, @NonNull UserModel newItem) {
        return oldItem.getEmail().equals(newItem.getEmail());
    }

    @Override
    public boolean areContentsTheSame(@NonNull UserModel oldItem, @NonNull UserModel newItem) {
        return oldItem.getName().equals(newItem.getName()) || oldItem.getEmail().equals(newItem.getEmail()) || oldItem.getAge() == newItem.getAge() || oldItem.getCity().equals(newItem.getCity());
    }
}
