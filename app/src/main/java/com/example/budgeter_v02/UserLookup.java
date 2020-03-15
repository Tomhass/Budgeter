package com.example.budgeter_v02;

public class UserLookup {
    private int currentTotal;
    private String name;
    private boolean setupComplete = false;

    public UserLookup() {

    }


    public boolean isSetupComplete() {
        return setupComplete;
    }

    public void setSetupComplete(boolean setupComplete) {
        this.setupComplete = setupComplete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentTotal() {
        return currentTotal;
    }

    public void setCurrentTotal(int currentTotal) {
        this.currentTotal = currentTotal;
    }
}

