package com.holiday.jetpackstudy.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.holiday.jetpackstudy.utils.QrLog;

public class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QrLog.e(getClass().getSimpleName() + " - onCreate - " + hashCode());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        QrLog.e(getClass().getSimpleName() + " - onViewCreated");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
//        QrLog.e(getClass().getSimpleName() + " - onAttach");
    }

    @Override
    public void onStart() {
        super.onStart();
        QrLog.e(getClass().getSimpleName() + " - onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        QrLog.e(getClass().getSimpleName() + " - onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        QrLog.e(getClass().getSimpleName() + " - onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        QrLog.e(getClass().getSimpleName() + " - onStop");
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        QrLog.e(getClass().getSimpleName() + " - onDetach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        QrLog.e(getClass().getSimpleName() + " - onDestroy");
    }

}
