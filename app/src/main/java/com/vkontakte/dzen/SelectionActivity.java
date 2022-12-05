package com.vkontakte.dzen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.flexbox.FlexboxLayout;
import com.vkontakte.dzen.category.Category;
import com.vkontakte.dzen.category.list.CategoryList;
import com.vkontakte.dzen.db.Database;

public class SelectionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        Database database = new Database(this);
        CategoryList categories = new CategoryList(this, findViewById(R.id.categories), R.layout.category_view);
        categories.addCategories(database.getAllCategories());
    }
}