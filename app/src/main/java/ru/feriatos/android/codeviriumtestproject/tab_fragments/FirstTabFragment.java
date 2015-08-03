package ru.feriatos.android.codeviriumtestproject.tab_fragments;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import ru.feriatos.android.codeviriumtestproject.R;

public class FirstTabFragment extends Fragment {

    ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first_tab, container, false);

        mListView = (ListView)v.findViewById(R.id.listView);

        final String[] catnames = new String[] {
                "Рыжик", "gkgfhjg", "Мурзик", "Мурка", "Васька",
                "Томасина", "Кристина", "Пушок", "Дымка", "Кузя",
                "Китти", "Масяня", "Симба"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),	android.R.layout.simple_list_item_1, catnames);

        mListView.setAdapter(adapter);

        return v;
    }
}