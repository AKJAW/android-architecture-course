package com.techyourchance.mvc.screens.common.main;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.screens.common.controllers.BackPressedDispatcher;
import com.techyourchance.mvc.screens.common.controllers.BackPressedListener;
import com.techyourchance.mvc.screens.common.controllers.BaseActivity;
import com.techyourchance.mvc.screens.common.fragmenthelper.FragmentContainerWrapper;
import com.techyourchance.mvc.screens.common.navigator.ScreenNavigator;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends BaseActivity implements BackPressedDispatcher, FragmentContainerWrapper {

    private ScreenNavigator screenNavigator;
    private final Set<BackPressedListener> backPressedListeners = new HashSet<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_fragment_frame);

        screenNavigator = getCompositionRoot().getScreenNavigator();

        if(savedInstanceState == null){
            screenNavigator.toQuestionsList();
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
    public ViewGroup getFragmentContainer() {
        return findViewById(R.id.placeholder_frame_layout);
    }
}
