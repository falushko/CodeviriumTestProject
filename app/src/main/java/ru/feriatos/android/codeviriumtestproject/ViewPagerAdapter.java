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

    CharSequence mTitles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int mNumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm, CharSequence titles[], int NumbOfTabs) {
        super(fm);
        this.mTitles = titles;
        this.mNumbOfTabs = NumbOfTabs;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        // return tab corresponding to the position
        if (position == 0) return new FirstTabFragment();
        else return new SecondTabFragment();

    }

    // This method return the titles for the Tabs in the Tab Strip
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    // This method return the Number of tabs for the tabs Strip
    @Override
    public int getCount() {
        return mNumbOfTabs;
    }

    public void startUpdate(){

    }
}