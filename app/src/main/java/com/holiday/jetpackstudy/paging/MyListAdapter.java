package com.holiday.jetpackstudy.paging;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.holiday.jetpackstudy.R;
import com.holiday.jetpackstudy.databinding.RvItemPagingBinding;
import com.holiday.jetpackstudy.room.entity.User;
import com.holiday.jetpackstudy.utils.QrLog;
import com.holiday.jetpackstudy.utils.QrToast;

/**
 * paging的分页加载思路：滑动时，onBindViewHolder - getItem - loadAround
 */
public class MyListAdapter extends PagedListAdapter<User, MyListAdapter.UserAdapterHolder> {

    protected MyListAdapter() {

        super(new DiffUtil.ItemCallback<User>() {
            @Override
            public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                //是否是同一个item，一般用数据源的唯一标识
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                //内容是否发生变更，一般重写equals
                return oldItem.equals(newItem);
            }
        });
    }

    @NonNull
    @Override
    public UserAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QrLog.e("onCreateViewHolder");
        RvItemPagingBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.rv_item_paging, parent, false);
        return new UserAdapterHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterHolder holder, int position) {
        QrLog.e("onBindViewHolder = " + position);
        final User user = getItem(position);
        holder.getBinding().setUser(user);
        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QrToast.show(user.getName());
            }
        });
        //业务场景如点赞和取消，先访问网络接口，成功则更新本地数据(内存or数据库)，然后UI自动刷新
        holder.getBinding().cbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //省去了网络接口访问
                user.setSelected(isChecked);
            }
        });
    }

    class UserAdapterHolder extends RecyclerView.ViewHolder {

        private RvItemPagingBinding mBinding;

        UserAdapterHolder(RvItemPagingBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public RvItemPagingBinding getBinding() {
            return mBinding;
        }
    }

    //以下方法用于log
    @Override
    public void onCurrentListChanged(@Nullable PagedList<User> previousList, @Nullable PagedList<User> currentList) {
        super.onCurrentListChanged(previousList, currentList);
        QrLog.e("onCurrentListChanged : previous = " +
                (null == previousList ? 0 : previousList.size()) +
                " , current = " +
                (null == currentList ? 0 : currentList.size()));
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull UserAdapterHolder holder) {
        super.onViewDetachedFromWindow(holder);
        QrLog.e("onViewDetachedFromWindow");
    }

    @Override
    public void onViewAttachedToWindow(@NonNull UserAdapterHolder holder) {
        super.onViewAttachedToWindow(holder);
        QrLog.e("onViewAttachedToWindow");
    }

    //https://wanandroid.com/wenda/show/12148
    @Override
    public void onViewRecycled(@NonNull UserAdapterHolder holder) {
        super.onViewRecycled(holder);
        QrLog.e("onViewRecycled");
    }
}
