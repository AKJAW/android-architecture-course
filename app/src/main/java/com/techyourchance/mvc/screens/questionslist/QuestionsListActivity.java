package com.techyourchance.mvc.screens.questionslist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.screens.common.controllers.BaseActivity;
import com.techyourchance.mvc.screens.common.navigator.BackPressedListener;

import org.jetbrains.annotations.NotNull;

public class QuestionsListActivity extends BaseActivity {

    public static void startClearTop(@NotNull Context context) {
        Intent intent = new Intent(context, QuestionsListActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    BackPressedListener backPressedListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_fragment_placeholder);

        QuestionsListFragment fragment;
        if(savedInstanceState == null){
            fragment = new QuestionsListFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.placeholder_frame_layout, fragment).commit();
        } else {
            fragment = (QuestionsListFragment) getSupportFragmentManager().findFragmentById(R.id.placeholder_frame_layout);
        }

        backPressedListener = fragment;
    }

    @Override
    public void onBackPressed() {
        if(!backPressedListener.onBackPressed()){
            super.onBackPressed();
        }
    }
}
