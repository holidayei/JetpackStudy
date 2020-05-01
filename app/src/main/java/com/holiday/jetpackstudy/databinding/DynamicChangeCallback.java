package com.holiday.jetpackstudy.databinding;


import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 监听list变动内容，数据变化时，调用adapter跟新UI，这样只需手动更新数据源
 *
 * @param <T>
 */
public class DynamicChangeCallback<T> extends ObservableList.OnListChangedCallback<ObservableList<T>> {

    private RecyclerView.Adapter adapter;

    public DynamicChangeCallback(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onChanged(ObservableList<T> sender) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeChanged(ObservableList<T> sender, int positionStart, int itemCount) {
        adapter.notifyItemRangeChanged(positionStart, itemCount);
    }

    @Override
    public void onItemRangeInserted(ObservableList<T> sender, int positionStart, int itemCount) {
        adapter.notifyItemRangeInserted(positionStart, itemCount);
    }

    @Override
    public void onItemRangeMoved(ObservableList<T> sender, int fromPosition, int toPosition, int itemCount) {
        adapter.notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemRangeRemoved(ObservableList<T> sender, int positionStart, int itemCount) {
        adapter.notifyItemRangeRemoved(positionStart, itemCount);
    }

}
