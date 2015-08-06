package ru.feriatos.android.codeviriumtestproject.tab_fragments;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ru.feriatos.android.codeviriumtestproject.R;
import ru.feriatos.android.codeviriumtestproject.SqlDataManager;

public class SecondTabFragment extends Fragment {

    EditText mFirstName;
    EditText mLastName;
    EditText mPhone;
    EditText mEmail;
    Button mSendButton;
    String[] mUserData = new String[4];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_second_tab, container, false);

        mFirstName = (EditText) v.findViewById(R.id.firstName);
        mLastName = (EditText) v.findViewById(R.id.lastName);
        mPhone = (EditText) v.findViewById(R.id.phone);
        mEmail = (EditText) v.findViewById(R.id.eMail);
        mSendButton = (Button) v.findViewById(R.id.send);

        // gather entered data
        mSendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mUserData[0] = String.valueOf(mFirstName.getText());
                mUserData[1] = String.valueOf(mLastName.getText());
                mUserData[2] = String.valueOf(mPhone.getText());
                mUserData[3] = String.valueOf(mEmail.getText());

                // if user skipped some field
                for (String str : mUserData){
                    if(str.isEmpty()){
                        Toast.makeText(getActivity(), "Fill all fields", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                Pattern pattern = Pattern.compile("\\w{2,}@\\w{2,}\\.\\w{2,}");
                Matcher matcher = pattern.matcher(mUserData[3]);
                if(!matcher.find()){
                    Toast.makeText(getActivity(), "Enter correct email", Toast.LENGTH_SHORT).show();
                    return;
                }

                // get writable DB
                SQLiteDatabase db = new SqlDataManager(getActivity()).getWritableDatabase();

                // prepare values for input
                ContentValues values = new ContentValues();
                values.put(SqlDataManager.NAME, mUserData[0]);
                values.put(SqlDataManager.LAST_NAME, mUserData[1]);
                values.put(SqlDataManager.PHONE, Integer.parseInt(mUserData[2]));
                values.put(SqlDataManager.EMAIL, mUserData[3]);

                // insert data into DB
                db.insert(SqlDataManager.TABLE_NAME, null, values);

                // clear fields
                mFirstName.setText("");
                mLastName.setText("");
                mPhone.setText("");
                mEmail.setText("");

                // update select tab
                updateListView();
                Toast.makeText(getActivity(), "Data have been sent", Toast.LENGTH_SHORT).show();

            }
        });

        return v;
    }

    private void updateListView(){
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
        FirstTabFragment.mListView.setAdapter(adapter);
    }

}