package com.holiday.jetpackstudy.paging.network;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.holiday.jetpackstudy.R;
import com.holiday.jetpackstudy.databinding.RvItemPagingNetworkBinding;
import com.holiday.jetpackstudy.utils.QrToast;

/**
 * PagedListAdapter
 */
public class NetworkListAdapter2 extends PagedListAdapter<ArticleBean.DataBean.Article, NetworkListAdapter2.UserAdapterHolder> {

    protected NetworkListAdapter2() {
        super(new DiffUtil.ItemCallback<ArticleBean.DataBean.Article>() {
            @Override
            public boolean areItemsTheSame(@NonNull ArticleBean.DataBean.Article oldItem, @NonNull ArticleBean.DataBean.Article newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull ArticleBean.DataBean.Article oldItem, @NonNull ArticleBean.DataBean.Article newItem) {
                return oldItem.equals(newItem);
            }
        });
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
        final ArticleBean.DataBean.Article bean = getItem(position);
        holder.getBinding().setArticle(bean);
        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QrToast.show(bean.getTitle());
            }
        });
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
