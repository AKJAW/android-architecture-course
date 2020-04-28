package com.techyourchance.mvc.screens.questionslist;

import android.os.Bundle;

import com.techyourchance.mvc.screens.common.BaseActivity;

public class QuestionsListActivity extends BaseActivity {

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

}
