package com.holiday.jetpackstudy.paging.network;

import com.google.gson.Gson;
import com.holiday.jetpackstudy.utils.QrLog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class Api {
    private static final String wan_article = "https://www.wanandroid.com/article/list/%s/json";

    public static void getArticle(String page, ArticleCallback callback) {
        OkHttpUtils.get().url(String.format(wan_article, page)).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.onFail();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ArticleBean articleBean = new Gson().fromJson(response, ArticleBean.class);
                        QrLog.e("玩安卓文章列表：页码 = " + articleBean.getData().getCurPage() +
                                "，数量 = " + articleBean.getData().getDatas().size());
                        callback.onSuccess(articleBean.getData());
                    }
                });
    }

    public static List<ArticleBean.DataBean.Article> getArticle(String page) {
        try {
            Response response = OkHttpUtils.get().url(String.format(wan_article, page)).build().execute();
            ArticleBean articleBean = new Gson().fromJson(response.body().string(), ArticleBean.class);
            QrLog.e("玩安卓文章列表：页码 = " + articleBean.getData().getCurPage() +
                    "，数量 = " + articleBean.getData().getDatas().size());
            return articleBean.getData().getDatas();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    interface ArticleCallback {
        void onSuccess(ArticleBean.DataBean data);

        void onFail();
    }
}
