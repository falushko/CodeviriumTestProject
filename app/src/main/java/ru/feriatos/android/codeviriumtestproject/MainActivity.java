package ru.feriatos.android.codeviriumtestproject;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ru.feriatos.android.codeviriumtestproject.sliding_tabs.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence titles[] = {"select", "insert"};
    int numberOfTabs = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // toolbar creation
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        // create view pager
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), titles, numberOfTabs);

        // assigning ViewPager view and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // assiging the sliding tab layout view
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // to make the tabs fixed set this true, this makes the tabs space evenly in available width

        // setting custom color for the scroll bar indicator of the tab view
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // setting the ViewPager for the SlidingTabLayout
        tabs.setViewPager(pager);

    }

}