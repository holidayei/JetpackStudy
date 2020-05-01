package com.holiday.jetpackstudy.databinding;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.holiday.jetpackstudy.R;
import com.holiday.jetpackstudy.model.Goods;
import com.holiday.jetpackstudy.model.ObservableGoods;
import com.holiday.jetpackstudy.model.User;

/**
 * 数据绑定(data binding)是双向。双向绑定：当数据改变时同时使视图刷新，而视图改变时也可以同时改变数据。
 * 视图绑定(view binding)是单向，如果只是想取代findViewById，可以使用，貌似是AS 3.6才支持。
 * <p>
 * 使用1：https://www.jianshu.com/p/bd9016418af2
 * 使用2：https://www.jianshu.com/p/2c4ac24761f5
 * <p>
 * 优点：DataBinding会对绑定的数据进行判空、省去了找id操作、不会再出现id找不着的情况。
 * 缺点：生成多余的类增大包体积、增加构建时长。
 */
public class DataBindingActivity extends AppCompatActivity {

    //activity_data_binding.xml -> ActivityDataBindingBinding，xml文件名决定了生成的binding类名，xml+Binding
    private ActivityDataBindingBinding mBinding;
    private Goods mGoods;
    private ObservableGoods mObservableGoods;
    private ObservableList<String> mObservableList;

    private static final String TAG = "DataBindingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        User user = new User("姓名", "密码");
        //全字段都会更新
        mBinding.setUser(user);
        //通过binding访问view，使用kotlin+插件的话甚至可以直接访问view
        mBinding.tvName.setTextColor(getResources().getColor(R.color.colorPrimary));

        mGoods = new Goods("食物名字", "食物详情", 123f);
        mBinding.setGoods(mGoods);
        mBinding.setHandler(new Handler());
        //可以监听属性变化
        mGoods.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (propertyId == com.holiday.jetpackstudy.BR.name) {
                    Log.e(TAG, "BR.name");
                } else if (propertyId == com.holiday.jetpackstudy.BR.details) {
                    Log.e(TAG, "BR.details");
                } else if (propertyId == com.holiday.jetpackstudy.BR._all) {
                    Log.e(TAG, "BR._all");
                } else {
                    Log.e(TAG, "other");
                }
                //全字段更新时，收到的是BR._all，而不是每个字段挨个收到通知
            }
        });

        mObservableGoods = new ObservableGoods();
        mObservableGoods.setName(new ObservableField<String>("可观察域-名字"));
        mBinding.setObservableGoods(mObservableGoods);

        mObservableList = new ObservableArrayList<>();
        mObservableList.add("ObservableList第一个元素");
        mBinding.setObservableList(mObservableList);

        mBinding.setImgUrl("https://www.wanandroid.com/resources/image/pc/logo.png");
//        mBinding.setImgUrl(DataUtil.getImgByIdx(0));
    }

    public class Handler {
        public void changePart() {
            mGoods.setName("食物名字-部分改变");
            mGoods.setPrice(456f);//价格是不会更新的
        }

        public void changeAll() {
            mGoods.setName("食物名字-全部改变");
            mGoods.setPrice(789f);
            mGoods.setDetails("食物详情-全部改变");
        }

        public void changeObservable() {
            mObservableGoods.getName().set("可观察域-新名字");
        }

        public void afterTextChanged(Editable s) {
            Log.e(TAG, "afterTextChanged = " + s);
        }

        public void gotoRecyclerView() {
            Intent intent = new Intent(DataBindingActivity.this, DbRecyclerViewActivity.class);
            startActivity(intent);
        }
    }
}
