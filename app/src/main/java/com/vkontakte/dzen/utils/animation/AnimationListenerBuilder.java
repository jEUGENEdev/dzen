package com.vkontakte.dzen.utils.animation;

import android.view.View;
import android.view.animation.Animation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class AnimationListenerBuilder {
    private final List<Consumer<View>> posts = new ArrayList<>();
    private final List<Consumer<View>> pres = new ArrayList<>();
    private final Animation animation;

    public AnimationListenerBuilder(Animation animation) {
        this.animation = animation;
    }

    public void addPostExecutor(Consumer<View> consumer) {
        posts.add(consumer);
    }

    public void addPreExecutor(Consumer<View> consumer) {
        pres.add(consumer);
    }

    protected Animation build(View view) {
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                for(Consumer<View> consumer : pres) {
                    consumer.accept(view);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                for(Consumer<View> consumer : posts) {
                    consumer.accept(view);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        return animation;
    }
}
