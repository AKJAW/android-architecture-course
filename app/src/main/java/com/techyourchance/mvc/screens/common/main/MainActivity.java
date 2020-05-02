package com.techyourchance.mvc.screens.common.main;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.screens.common.controllers.BackPressedDispatcher;
import com.techyourchance.mvc.screens.common.controllers.BackPressedListener;
import com.techyourchance.mvc.screens.common.controllers.BaseActivity;
import com.techyourchance.mvc.screens.common.controllers.FragmentFrameWrapper;
import com.techyourchance.mvc.screens.questionslist.QuestionsListFragment;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends BaseActivity implements BackPressedDispatcher, FragmentFrameWrapper {

    private final Set<BackPressedListener> backPressedListeners = new HashSet<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_fragment_frame);

        if(savedInstanceState == null){
            QuestionsListFragment fragment = new QuestionsListFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.placeholder_frame_layout, fragment).commit();
        }
    }

    @Override
    public void registerListener(@NotNull BackPressedListener listener) {
        backPressedListeners.add(listener);
    }

    @Override
    public void unregisterListener(@NotNull BackPressedListener listener) {
        backPressedListeners.remove(listener);
    }

    @Override
    public void onBackPressed() {
        boolean wasBackPressHandled = false;
        for(BackPressedListener listener: backPressedListeners) {
            if(listener.onBackPressed()){
                wasBackPressHandled = true;
            }
        }
        if(!wasBackPressHandled){
            super.onBackPressed();
        }
    }

    @NotNull
    @Override
    public FrameLayout getFragmentFrame() {
        return findViewById(R.id.placeholder_frame_layout);
    }
}
