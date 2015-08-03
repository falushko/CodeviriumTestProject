package ru.feriatos.android.codeviriumtestproject.tab_fragments;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.feriatos.android.codeviriumtestproject.R;
import ru.feriatos.android.codeviriumtestproject.SqlDataManager;

/**
 * Created by hp1 on 21-01-2015.
 */
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

        mSendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mUserData[0] = String.valueOf(mFirstName.getText());
                mUserData[1] = String.valueOf(mLastName.getText());
                mUserData[2] = String.valueOf(mPhone.getText());
                mUserData[3] = String.valueOf(mEmail.getText());

                for (String str : mUserData){
                    if(str.isEmpty()){
                        Toast toast = Toast.makeText(getActivity(), "Fill all fields", Toast.LENGTH_SHORT);
                        toast.show();
                        return;
                    }
                }

                SqlDataManager sqlManager = new SqlDataManager(getActivity());
                // Gets the data repository in write mode
                SQLiteDatabase db = sqlManager.getWritableDatabase();

                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(SqlDataManager.NAME, mUserData[0]);
                values.put(SqlDataManager.LAST_NAME, mUserData[1]);
                values.put(SqlDataManager.PHONE, Integer.parseInt(mUserData[2]));
                values.put(SqlDataManager.EMAIL, mUserData[3]);

                db.insert(SqlDataManager.TABLE_NAME, null, values);

                Toast toast = Toast.makeText(getActivity(), "Data have been sent", Toast.LENGTH_SHORT);
                toast.show();

            }
        });

        return v;
    }
}