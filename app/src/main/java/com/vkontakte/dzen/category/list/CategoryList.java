package com.vkontakte.dzen.category.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.vkontakte.dzen.R;
import com.vkontakte.dzen.category.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryList {
    private final List<Category> categories = new ArrayList<>();
    private final List<View> categoriesView = new ArrayList<>();
    private final int resElementLayoutId;
    private final Context ctx;
    private final FlexboxLayout flexboxLayout;
    private ChangeListener changeListener;

    public CategoryList(Context context, FlexboxLayout layout, int resElementLayoutId) {
        this.resElementLayoutId = resElementLayoutId;
        this.ctx = context;
        this.flexboxLayout = layout;
    }

    private View addEl(Category category) {
        View categoryView = LayoutInflater.from(ctx).inflate(resElementLayoutId, flexboxLayout, false);
        categories.add(category);
        categoriesView.add(categoryView);
        if(category.getOnClickListener() != null) {
            categoryView.setOnClickListener(category.getOnClickListener());
        }
        TextView categoryName = categoryView.findViewById(R.id.category_name);
        categoryName.setText(category.getName());
        flexboxLayout.addView(categoryView);
        if(changeListener != null) changeListener.apply(category, categoryView, false);
        return categoryView;
    }

    public View addCategory(Category category) {
        return addEl(category);
    }

    public List<View> addCategories(List<Category> categories) {
        List<View> categoriesView = new ArrayList<>();
        for(Category category : categories) {
            categoriesView.add(addCategory(category));
        }
        return categoriesView;
    }

    public void removeCategory(View categoryView) {
        int index = this.categoriesView.indexOf(categoryView);
        if(index == -1) return;
        if(changeListener != null) changeListener.apply(this.categories.get(index), categoryView, true);
        ((ViewGroup) categoryView.getParent()).removeView(categoryView);
        this.categoriesView.remove(index);
        this.categories.remove(index);
    }

    public void addChangeListener(ChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    public List<Category> getPickedCategories() {
        List<Category> categories = new ArrayList<>();
        for(Category category : this.categories) {
            if(category.isPick()) {
                categories.add(category);
            }
        }
        return categories;
    }

    public static interface ChangeListener {
        void apply(Category category, View view, boolean delete);
    }
}
