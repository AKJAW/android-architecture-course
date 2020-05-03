package com.techyourchance.mvc.screens.common.main;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.techyourchance.mvc.screens.common.controllers.BackPressedDispatcher;
import com.techyourchance.mvc.screens.common.controllers.BackPressedListener;
import com.techyourchance.mvc.screens.common.controllers.BaseActivity;
import com.techyourchance.mvc.screens.common.fragmenthelper.FragmentContainerWrapper;
import com.techyourchance.mvc.screens.common.navigator.ScreenNavigator;
import com.techyourchance.mvc.screens.common.view.drawer.NavDrawerHelper;
import com.techyourchance.mvc.screens.common.view.drawer.NavDrawerViewMvc;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends BaseActivity implements
        BackPressedDispatcher,
        FragmentContainerWrapper,
        NavDrawerViewMvc.Listener,
        NavDrawerHelper {

    private ScreenNavigator screenNavigator;
    private final Set<BackPressedListener> backPressedListeners = new HashSet<>();

    private NavDrawerViewMvc viewMvc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        screenNavigator = getCompositionRoot().getScreenNavigator();
        viewMvc = getCompositionRoot().getViewMvcFactory().getNavDrawerViewMvc(null);

        if(savedInstanceState == null){
            screenNavigator.toQuestionsList();
        }

        setContentView(viewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();

        viewMvc.registerListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        viewMvc.unregisterListener(this);
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
        return viewMvc.getFragmentFrame();
    }

    @Override
    public void onQuestionsClicked() {
        screenNavigator.toQuestionsList();
    }

    @Override
    public boolean isDrawerOpen() {
        return viewMvc.isDrawerOpen();
    }

    @Override
    public void openDrawer() {
        viewMvc.openDrawer();
    }

    @Override
    public void closeDrawer() {
        viewMvc.closeDrawer();
    }
}
