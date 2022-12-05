package com.vkontakte.dzen.category;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.vkontakte.dzen.R;
import com.vkontakte.dzen.utils.SetAnimation;

public class Category {
    private final int id;
    private String name;
    private boolean pick;
    private View.OnClickListener onClickListener = v -> {
        SetAnimation animation = new SetAnimation(v);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0.4f);
        alphaAnimation.setDuration(200);
        animation.addAnimation(alphaAnimation);
        alphaAnimation = new AlphaAnimation(0.4f, 1f);
        alphaAnimation.setDuration(50);
        animation.addAnimation(alphaAnimation);
        animation.setPostEditable(view -> {
            v.setBackgroundResource(pick ? R.drawable.category_view_style : R.drawable.category_view_picked_style);
            ((ImageView) v.findViewById(R.id.category_pick_image)).setImageResource(pick ? R.drawable.plus : R.drawable.check_mark);
            v.findViewById(R.id.category_divider).setVisibility(pick ? View.VISIBLE : View.INVISIBLE);
            pick = !pick;
        });
        animation.startAnimation();
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