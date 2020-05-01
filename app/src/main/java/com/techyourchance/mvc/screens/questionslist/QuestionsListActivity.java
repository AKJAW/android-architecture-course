package com.techyourchance.mvc.screens.questionslist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.techyourchance.mvc.screens.common.controllers.BaseActivity;

import org.jetbrains.annotations.NotNull;

public class QuestionsListActivity extends BaseActivity {

    public static void startClearTop(@NotNull Context context) {
        Intent intent = new Intent(context, QuestionsListActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    private QuestionsListController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        QuestionsListViewMvc viewMvc
                = getCompositionRoot().getViewMvcFactory().getQuestionsListViewMvc(null);

        controller = getCompositionRoot().getQuestionsListController();
        controller.bindView(viewMvc);

        setContentView(viewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();

        controller.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();

        controller.onStop();
    }

    @Override
    public void onBackPressed() {
        boolean wasHandled = controller.onBackPressed();
        if(!wasHandled){
            super.onBackPressed();
        }
    }
}
