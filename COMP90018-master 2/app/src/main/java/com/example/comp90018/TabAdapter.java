package com.example.comp90018;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {
    public TabAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new SetUpTab();
            case 1:
                return new AlarmClock();
            case 2:
                return new OtherTab2();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return "SetUp";
            case 1:
                return "AlarmClock";
            case 2:
                return "other2";
            default:
                return null;

        }
    }
}
