package com.vkontakte.dzen.utils;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class SetAnimation {
    private final View onObject;
    private PostEditable firstPostEditable;
    private Animation baseAnimation;
    private Animation animation;

    public SetAnimation(View onObject) {
        this.onObject = onObject;
    }

    private void addAnim(Animation anim, PostEditable postEditable) {
        Animation.AnimationListener listener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                if(postEditable != null) postEditable.edit(onObject);
                onObject.startAnimation(anim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        };
        if(animation == null) {
            baseAnimation = new AlphaAnimation(onObject.getAlpha(), onObject.getAlpha());
            baseAnimation.setDuration(0);
            baseAnimation.setAnimationListener(listener);
        }
        else {
            animation.setAnimationListener(listener);
        }
        animation = anim;
    }

    public void addAnimation(Animation anim) {
        addAnim(anim, null);
    }

    public void addAnimation(Animation animation, PostEditable postEditable) {
        addAnim(animation, postEditable);
    }

    public void startAnimation() {
        onObject.startAnimation(baseAnimation);
    }

    public interface PostEditable {
        void edit(View onObject);
    }
}
