package com.vkontakte.dzen.utils;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class SetAnimation {
    private final View onObject;
    private final Animation baseAnimation;
    private Animation animation;
    private PostEditable postEditable;

    public SetAnimation(View onObject) {
        this.onObject = onObject;
        this.baseAnimation = new AlphaAnimation(onObject.getAlpha(), onObject.getAlpha());
        this.baseAnimation.setDuration(0);
    }

    public void addAnimation(Animation anim) {
        Animation.AnimationListener listener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                onObject.startAnimation(anim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        };
        if(animation == null) {
            baseAnimation.setAnimationListener(listener);
        }
        else {
            animation.setAnimationListener(listener);
        }
        animation = anim;
    }

    public void addAnimation(Animation animation, PostEditable postEditable) {
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                postEditable.edit(onObject);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        addAnimation(animation);
    }

    public void startAnimation() {
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                if(postEditable != null) postEditable.edit(SetAnimation.this.onObject);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        onObject.startAnimation(baseAnimation);
    }

    public void setPostEditable(PostEditable postEditable) {
        this.postEditable = postEditable;
    }

    public interface PostEditable {
        void edit(View onObject);
    }
}
