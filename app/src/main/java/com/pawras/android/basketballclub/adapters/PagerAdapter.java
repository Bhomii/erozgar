package com.pawras.android.basketballclub.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pawras.android.basketballclub.fragment.Coaches;
import com.pawras.android.basketballclub.fragment.Forum;
import com.pawras.android.basketballclub.fragment.Players;
import com.pawras.android.basketballclub.fragment.Teams;
import com.pawras.android.basketballclub.models.Coach;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Forum tab1 = new Forum();
                return tab1;
            case 1:
                Coaches tab2 = new Coaches();
                return tab2;
            case 2:
                Players tab3 = new Players();
                return tab3;
            case 3:
                Teams tab4=new Teams();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}