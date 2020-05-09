package com.holiday.jetpackstudy.paging.network;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.holiday.jetpackstudy.R;
import com.holiday.jetpackstudy.databinding.RvItemPagingNetworkBinding;
import com.holiday.jetpackstudy.utils.QrToast;

import java.util.ArrayList;
import java.util.List;

/**
 * 非paging方案，用来对比
 */
public class NetworkListAdapter extends RecyclerView.Adapter<NetworkListAdapter.UserAdapterHolder> {
    private List<ArticleBean.DataBean.Article> mData = new ArrayList<>();

    public void addData(List<ArticleBean.DataBean.Article> data) {
        mData.addAll(data);
        notifyItemRangeInserted(mData.size(), data.size());
    }

    public void clearData() {
        mData.clear();
    }

    @NonNull
    @Override
    public UserAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        QrLog.e("onCreateViewHolder");
        RvItemPagingNetworkBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.rv_item_paging_network, parent, false);
        return new UserAdapterHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterHolder holder, int position) {
//        QrLog.e("onBindViewHolder = " + position);
        final ArticleBean.DataBean.Article bean = mData.get(position);
        holder.getBinding().setArticle(bean);
        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QrToast.show(bean.getTitle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class UserAdapterHolder extends RecyclerView.ViewHolder {

        private RvItemPagingNetworkBinding mBinding;

        UserAdapterHolder(RvItemPagingNetworkBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public RvItemPagingNetworkBinding getBinding() {
            return mBinding;
        }
    }
}
