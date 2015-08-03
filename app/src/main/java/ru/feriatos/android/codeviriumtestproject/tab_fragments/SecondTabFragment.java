package ru.feriatos.android.codeviriumtestproject.tab_fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.feriatos.android.codeviriumtestproject.R;

/**
 * Created by hp1 on 21-01-2015.
 */
public class SecondTabFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_second_tab, container, false);
        return v;
    }
}