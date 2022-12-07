package com.vkontakte.dzen;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.vkontakte.dzen.category.list.CategoryList;
import com.vkontakte.dzen.db.Database;
import com.vkontakte.dzen.di.ActivityInjection;

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