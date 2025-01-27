package com.example.propuesta8_2;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Adapter extends FragmentStatePagerAdapter {
    int numTab;

    public Adapter(FragmentManager fm, int numTab) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.numTab = numTab;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Primero();
            case 1:
                return new Segundo();
            case 2:
                return new Tercero();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTab;
    }
}
