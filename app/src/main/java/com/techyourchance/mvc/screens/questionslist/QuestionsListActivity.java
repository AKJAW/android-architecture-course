package com.techyourchance.mvc.screens.questionslist;

import android.os.Bundle;
import android.widget.Toast;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.questions.FetchLastActiveQuestionsUseCase;
import com.techyourchance.mvc.questions.Question;
import com.techyourchance.mvc.screens.common.BaseActivity;
import com.techyourchance.mvc.screens.questiondetails.QuestionDetailsActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class QuestionsListActivity extends BaseActivity
        implements QuestionsListViewMvc.Listener, FetchLastActiveQuestionsUseCase.Listener {

    private FetchLastActiveQuestionsUseCase fetchLastActiveQuestionsUseCase;

    private QuestionsListViewMvc viewMvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewMvc = getCompositionRoot().getViewMvcFactory().getQuestionsListViewMvc(null);
        viewMvc.registerListener(this);

        fetchLastActiveQuestionsUseCase = getCompositionRoot().getFetchLastActiveQuestionsUseCase();

        setContentView(viewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();

        fetchLastActiveQuestionsUseCase.registerListener(this);
        fetchLastActiveQuestionsUseCase.fetchLastActiveQuestionsAndNotify();
    }

    @Override
    protected void onStop() {
        super.onStop();

        fetchLastActiveQuestionsUseCase.unregisterListener(this);
    }

    @Override
    public void onQuestionClicked(Question question) {
        QuestionDetailsActivity.Companion.start(this, question.getId());
    }


    @Override
    public void fetchLastActiveQuestionsSuccess(@NotNull List<? extends Question> questions) {
        bindQuestions(questions);
    }

    private void bindQuestions(List<? extends Question> questions) {
        viewMvc.bindQuestions(questions);
    }

    @Override
    public void fetchLastActiveQuestionsFailed() {
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show();
    }
}
