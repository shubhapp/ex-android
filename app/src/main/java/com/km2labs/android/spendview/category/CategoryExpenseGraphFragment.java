package com.km2labs.android.spendview.category;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.km2labs.shubh.expensemanager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryExpenseGraphFragment extends Fragment {


    public CategoryExpenseGraphFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_expense_graph, container, false);
    }


}
