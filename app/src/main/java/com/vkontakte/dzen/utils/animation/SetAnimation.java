package com.vkontakte.dzen.utils.animation;

import android.view.View;
import android.view.animation.RotateAnimation;

import com.vkontakte.dzen.SelectionActivity;

public class SetAnimation {
    private final View onObject;
    private AnimationListenerBuilder baseAnimation;
    private AnimationListenerBuilder animation;

    public SetAnimation(View onObject) {
        this.onObject = onObject;
    }

    private void addAnim(AnimationListenerBuilder animationListenerBuilder) {
        if(baseAnimation == null) {
            RotateAnimation rotateAnimation = new RotateAnimation(0, 0);
            rotateAnimation.setDuration(0);
            baseAnimation = new AnimationListenerBuilder(rotateAnimation);

            /* НАЧАЛО БАГА С АНИМАЦИЕЙ, далее в AnimationListenerBuilder.class - onAnimationEnd */

            baseAnimation.addPostExecutor(onObject -> onObject.startAnimation(animationListenerBuilder.build(SetAnimation.this.onObject)));
            animation = animationListenerBuilder;
            return;
        }
        animation.addPostExecutor(onObject -> onObject.startAnimation(animationListenerBuilder.build(SetAnimation.this.onObject)));
        animation = animationListenerBuilder;
    }

    public void addAnimation(AnimationListenerBuilder animationListenerBuilder) {
        addAnim(animationListenerBuilder);
    }

    public void startAnimation() {
        onObject.startAnimation(baseAnimation.build(SetAnimation.this.onObject));
    }

    public AnimationListenerBuilder getLastAnimation() {
        return this.animation;
    }
}
