package ru.feriatos.android.codeviriumtestproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import ru.feriatos.android.codeviriumtestproject.tab_fragments.FirstTabFragment;
import ru.feriatos.android.codeviriumtestproject.tab_fragments.SecondTabFragment;

/**
 * Created by hp1 on 21-01-2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence mTitles[]; // titles of tabs
    int mNumbOfTabs; // number of tabs

    public ViewPagerAdapter(FragmentManager fm, CharSequence titles[], int NumbOfTabs) {
        super(fm);
        this.mTitles = titles;
        this.mNumbOfTabs = NumbOfTabs;
    }

    // return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        // return tab corresponding to the position
        if (position == 0) return new FirstTabFragment();
        else return new SecondTabFragment();

    }

    // return the titles for the tabs in the tab strip
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    // return the number of tabs for the tabs strip
    @Override
    public int getCount() {
        return mNumbOfTabs;
    }

}