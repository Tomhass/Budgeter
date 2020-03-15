package com.example.budgeter_v02;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    private String name;
    private int total;
    private boolean finishedSetup;
    private String uId;
    DatabaseReference mDatabase;

    public User(String name, int total, boolean setup) {
        this.name = name;
        this.total = total;
        this.finishedSetup = setup;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isFinishedSetup() {
        return finishedSetup;
    }

    public void setFinishedSetup(boolean finishedSetup) {
        this.finishedSetup = finishedSetup;
    }

    public void writeNewUser(String name, int total, boolean finishedSetup) {
        User user = new User(name, total, finishedSetup);

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mDatabase.getReference("users").child(uId);

        myRef.setValue(user);
    }


    public String getId() {
        return uId;
    }

    public void setId(String id) {
        this.uId = id;
    }
}
