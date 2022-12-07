package com.vkontakte.dzen.category;

import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.vkontakte.dzen.R;
import com.vkontakte.dzen.SelectionActivity;
import com.vkontakte.dzen.di.ActivityInjection;
import com.vkontakte.dzen.utils.animation.AnimationListenerBuilder;
import com.vkontakte.dzen.utils.animation.SetAnimation;

public class Category {
    private final int id;
    private String name;
    private boolean pick;
    private View.OnClickListener onClickListener = v -> {
        SelectionActivity selectionActivity = (SelectionActivity) ActivityInjection.getInstance().get(SelectionActivity.class);
        FrameLayout divider = v.findViewById(R.id.category_divider);
        ImageView indicator = v.findViewById(R.id.category_pick_image);

        SetAnimation dividerAnimation = new SetAnimation(divider), indicatorAnimation = new SetAnimation(indicator);
        if(pick) {
            AlphaAnimation visibleDividerAnimation = new AlphaAnimation(0, 1f);
            visibleDividerAnimation.setDuration(400);
            AnimationListenerBuilder visibleDividerBuilder = new AnimationListenerBuilder(visibleDividerAnimation);
            visibleDividerBuilder.addPostExecutor(onObject -> divider.setVisibility(View.VISIBLE));
            dividerAnimation.addAnimation(visibleDividerBuilder);

            AlphaAnimation invisibleIndicatorAnimation = new AlphaAnimation(1f, 0);
            invisibleIndicatorAnimation.setDuration(200);
            AlphaAnimation visibleIndicatorAnimation = new AlphaAnimation(0, 1f);
            visibleIndicatorAnimation.setDuration(200);
            AnimationListenerBuilder invisibleIndicatorBuilder = new AnimationListenerBuilder(invisibleIndicatorAnimation);
            invisibleIndicatorBuilder.addPostExecutor(onObject -> indicator.setImageResource(R.drawable.plus));
            indicatorAnimation.addAnimation(invisibleIndicatorBuilder);
            AnimationListenerBuilder visibleIndicatorBuilder = new AnimationListenerBuilder(visibleIndicatorAnimation);
            indicatorAnimation.addAnimation(visibleIndicatorBuilder);
        }
        else {
            AlphaAnimation invisibleDividerAnimation = new AlphaAnimation(1f, 0);
            invisibleDividerAnimation.setDuration(400);
            AnimationListenerBuilder invisibleDividerBuilder = new AnimationListenerBuilder(invisibleDividerAnimation);
            invisibleDividerBuilder.addPostExecutor(onObject -> divider.setVisibility(View.INVISIBLE));
            dividerAnimation.addAnimation(invisibleDividerBuilder);

            AlphaAnimation invisibleIndicatorAnimation = new AlphaAnimation(1f, 0);
            invisibleIndicatorAnimation.setDuration(200);
            AlphaAnimation visibleIndicatorAnimation = new AlphaAnimation(0, 1f);
            visibleIndicatorAnimation.setDuration(200);
            AnimationListenerBuilder invisibleIndicatorBuilder = new AnimationListenerBuilder(invisibleIndicatorAnimation);
            invisibleIndicatorBuilder.addPostExecutor(onObject -> indicator.setImageResource(R.drawable.check_mark));
            indicatorAnimation.addAnimation(invisibleIndicatorBuilder);
            AnimationListenerBuilder visibleIndicatorBuilder = new AnimationListenerBuilder(visibleIndicatorAnimation);
            indicatorAnimation.addAnimation(visibleIndicatorBuilder);
        }
        TransitionDrawable transitionDrawable = (TransitionDrawable) v.getBackground();
        if(pick) transitionDrawable.reverseTransition(400); else transitionDrawable.startTransition(400);
        dividerAnimation.startAnimation();

        indicatorAnimation.getLastAnimation().addPreExecutor(onObject -> {
            int pickedSize = selectionActivity.getCategories().getPickedCategories().size();
            SetAnimation nextButtonAnimation = new SetAnimation(selectionActivity.getNextButton());
            if(pickedSize == 0) {
                // INVISIBLE
                AlphaAnimation invisibleNextButtonAnimation = new AlphaAnimation(1f, 0);
                invisibleNextButtonAnimation.setDuration(700);
                AnimationListenerBuilder invisibleNextButtonBuilder = new AnimationListenerBuilder(invisibleNextButtonAnimation);
                invisibleNextButtonBuilder.addPostExecutor(view -> selectionActivity.getNextButton().setVisibility(View.INVISIBLE));
                nextButtonAnimation.addAnimation(invisibleNextButtonBuilder);
                nextButtonAnimation.startAnimation();
            }
            else if(pickedSize == 1 && pick) {
                // VISIBLE
                AlphaAnimation visibleNextButtonAnimation = new AlphaAnimation(0, 1f);
                visibleNextButtonAnimation.setDuration(700);
                AnimationListenerBuilder visibleNextButtonBuilder = new AnimationListenerBuilder(visibleNextButtonAnimation);
                visibleNextButtonBuilder.addPostExecutor(view -> selectionActivity.getNextButton().setVisibility(View.VISIBLE));
                nextButtonAnimation.addAnimation(visibleNextButtonBuilder);
                nextButtonAnimation.startAnimation();
            }
        });

        indicatorAnimation.startAnimation();

        pick = !pick;
    };

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPick() {
        return pick;
    }

    public void setPick(boolean pick) {
        this.pick = pick;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}