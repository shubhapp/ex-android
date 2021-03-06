package com.km2labs.android.spendview.database.content;

import org.parceler.Parcel;

/**
 * Created by Subham Tyagi,
 * on 10/Jul/2015,
 * 1:54 AM
 * TODO:Add class comment.
 */
@Parcel(value = Parcel.Serialization.BEAN)
public class Transaction {

    public static final String TYPE_CREDIT = "credit";
    public static final String TYPE_DEBIT = "debit";

    private long id;
    private String name;
    private double amount;
    private long date;
    private String type;
    private long accountKey;

    public Transaction() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public Transaction setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getAccountKey() {
        return accountKey;
    }

    public Transaction setAccountKey(long accountKey) {
        this.accountKey = accountKey;
        return this;
    }
}
