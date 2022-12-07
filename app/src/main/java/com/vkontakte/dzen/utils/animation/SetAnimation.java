package com.vkontakte.dzen.utils.animation;

import android.view.View;
import android.view.animation.AlphaAnimation;

public class SetAnimation {
    private final View onObject;
    private AnimationListenerBuilder baseAnimation;
    private AnimationListenerBuilder animation;

    public SetAnimation(View onObject) {
        this.onObject = onObject;
    }

    private void addAnim(AnimationListenerBuilder animationListenerBuilder) {
        if(baseAnimation == null) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(onObject.getAlpha(), onObject.getAlpha());
            alphaAnimation.setDuration(0);
            baseAnimation = new AnimationListenerBuilder(alphaAnimation);
            baseAnimation.addPostExecutor(onObject -> onObject.startAnimation(animationListenerBuilder.build(onObject)));
            animation = animationListenerBuilder;
            return;
        }
        animation.addPostExecutor(onObject -> onObject.startAnimation(animationListenerBuilder.build(onObject)));
        animation = animationListenerBuilder;
    }

    public void addAnimation(AnimationListenerBuilder animationListenerBuilder) {
        addAnim(animationListenerBuilder);
    }

    public void startAnimation() {
        onObject.startAnimation(baseAnimation.build(onObject));
    }

    public AnimationListenerBuilder getLastAnimation() {
        return this.animation;
    }
}
