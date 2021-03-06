package com.km2labs.android.spendview.expensebook;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.km2labs.android.spendview.core.view.CircleImageView;
import com.km2labs.shubh.expensemanager.R;
import com.km2labs.android.spendview.database.content.ExpenseBook;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Subham Tyagi,
 * on 10/Sep/2015,
 * 12:22 AM
 * TODO:Add class comment.
 */
public class ExpenseBookGridAdapter extends RecyclerView.Adapter<ExpenseBookGridAdapter.ExpenseBookViewHolder> {


    List<ExpenseBook> mExpenseBooks;
    private int mMode;

    public ExpenseBookGridAdapter() {
        mExpenseBooks = new ArrayList<>();
    }

    @Override
    public ExpenseBookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.grid_item_expense_book, parent, false);
        return new ExpenseBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExpenseBookViewHolder holder, int position) {
        holder.bindView(mExpenseBooks.get(position));
    }

    @Override
    public int getItemCount() {
        return mExpenseBooks.size();
    }

    @Override
    public long getItemId(int position) {
        return mExpenseBooks.get(position).getId();
    }

    public ExpenseBook getItem(int position) {
        return mExpenseBooks.get(position);
    }

    public void addData(List<ExpenseBook> expenseBooks) {
        mExpenseBooks.clear();
        mExpenseBooks.addAll(expenseBooks);
        notifyDataSetChanged();
    }

    public void setMode(int mode) {
        mMode = mode;
    }

    public void clear() {
        mExpenseBooks.clear();
    }

    class ExpenseBookViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.expense_book_title)
        TextView TitleTextView;

        @BindView(R.id.image)
        CircleImageView mProfileImage;


        public ExpenseBookViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(ExpenseBook expenseBook) {
            TitleTextView.setText(expenseBook.getName());
            ColorGenerator generator = ColorGenerator.MATERIAL;
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound(String.valueOf(expenseBook.getName().charAt(0)), generator.getRandomColor());
//            mProfileImage.setImageDrawable(drawable);
            if (!TextUtils.isEmpty(expenseBook.getProfileImagePath())) {
                mProfileImage.loadImage(expenseBook.getProfileImagePath());
            }

            TitleTextView.setTypeface(null, Typeface.NORMAL);
        }

    }

}
