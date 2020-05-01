package com.holiday.jetpackstudy.databinding;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.holiday.jetpackstudy.R;
import com.holiday.jetpackstudy.model.User;

/**
 * 用data binding实现item view
 */
public class DbUserAdapter extends RecyclerView.Adapter<DbUserAdapter.UserAdapterHolder> {

    private ObservableArrayList<User> mUserList;

    public DbUserAdapter(ObservableArrayList<User> userList) {
        mUserList = userList;
    }

    @NonNull
    @Override
    public UserAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvItemDataBindingBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.rv_item_data_binding, parent, false);
        return new UserAdapterHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterHolder holder, int position) {
        holder.getBinding().setUser(mUserList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mUserList == null) {
            return 0;
        }
        return mUserList.size();
    }

    class UserAdapterHolder extends RecyclerView.ViewHolder {

        private RvItemDataBindingBinding mBinding;

        UserAdapterHolder(RvItemDataBindingBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public RvItemDataBindingBinding getBinding() {
            return mBinding;
        }
    }

}

