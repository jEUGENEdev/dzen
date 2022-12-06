package com.vkontakte.dzen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.flexbox.FlexboxLayout;
import com.vkontakte.dzen.category.Category;
import com.vkontakte.dzen.category.list.CategoryList;
import com.vkontakte.dzen.db.Database;
import com.vkontakte.dzen.di.ActivityInjection;
import com.vkontakte.dzen.utils.SetAnimation;

public class SelectionActivity extends AppCompatActivity {
    private CategoryList categories;
    private Button nextButton, laterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        ActivityInjection.getInstance().add(getClass(), this);

        nextButton = findViewById(R.id.selection_next_button);
        laterButton = findViewById(R.id.selection_later_button);

        Database database = new Database(this);
        categories = new CategoryList(this, findViewById(R.id.categories), R.layout.category_view);
        categories.addCategories(database.getAllCategories());
    }

    public CategoryList getCategories() {
        return categories;
    }

    public Button getNextButton() {
        return nextButton;
    }

    public Button getLaterButton() {
        return laterButton;
    }
}