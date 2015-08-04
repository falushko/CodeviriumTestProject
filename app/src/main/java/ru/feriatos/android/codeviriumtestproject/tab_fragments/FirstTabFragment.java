package ru.feriatos.android.codeviriumtestproject.tab_fragments;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ru.feriatos.android.codeviriumtestproject.DetailsActivity;
import ru.feriatos.android.codeviriumtestproject.R;
import ru.feriatos.android.codeviriumtestproject.SqlDataManager;

public class FirstTabFragment extends Fragment {

    ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first_tab, container, false);

        mListView = (ListView)v.findViewById(R.id.listView);

        // get readable DB
        SQLiteDatabase db = new SqlDataManager(getActivity()).getReadableDatabase();

        // define needed columns
        String[] projection = {
                SqlDataManager.ID,
                SqlDataManager.NAME,
                SqlDataManager.LAST_NAME,

        };

        // get all users
        Cursor cursor = db.query(
                SqlDataManager.TABLE_NAME,          // The table to query
                projection,                         // The columns to return
                null,                               // The columns for the WHERE clause
                null,                               // The values for the WHERE clause
                null,                               // don't group the rows
                null,                               // don't filter by row groups
                null                                // The sort order
        );


        ArrayList<String> usersList = new ArrayList<String>();

        // fill array list with users
        while (cursor.moveToNext()) {
            usersList.add(cursor.getString(cursor.getColumnIndex(SqlDataManager.ID)) + ". "
                    + cursor.getString(cursor.getColumnIndex(SqlDataManager.NAME))  + " "
                    + cursor.getString(cursor.getColumnIndex(SqlDataManager.LAST_NAME)));
        }

        cursor.close();
        usersList.toArray();

        // create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, usersList);
        mListView.setAdapter(adapter);

        // list item click listener
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {

                // getting ID of pressed element
                String userId = ((TextView) itemClicked)
                        .getText()
                        .toString()
                        .split("\\.")[0];

                //start details activity
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("USER_ID", userId);
                startActivity(intent);

            }
        });

        return v;
    }
}