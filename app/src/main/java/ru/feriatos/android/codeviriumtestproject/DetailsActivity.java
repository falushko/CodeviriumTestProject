package ru.feriatos.android.codeviriumtestproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ru.feriatos.android.codeviriumtestproject.tab_fragments.FirstTabFragment;

public class DetailsActivity extends AppCompatActivity{

    Toolbar toolbar;
    EditText mFirstName;
    EditText mLastName;
    EditText mPhone;
    EditText mEmail;
    Button mEditButton;
    String[] mUserData = new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        toolbar = (Toolbar) findViewById(R.id.tool_bar_details);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // user ID from intent
        final String[] selectionArgs = {getIntent().getStringExtra("USER_ID")};

        mFirstName = (EditText) findViewById(R.id.detailFirstName);
        mLastName = (EditText) findViewById(R.id.detailLastName);
        mPhone = (EditText) findViewById(R.id.detailPhone);
        mEmail = (EditText) findViewById(R.id.detailEmail);
        mEditButton = (Button) findViewById(R.id.edit_information);

        // this click listener has a lot of codebase similar
        // to SecondTabFragment button click listener.
        // they should be merged to throw out repeated code
        mEditButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mUserData[0] = String.valueOf(mFirstName.getText());
                mUserData[1] = String.valueOf(mLastName.getText());
                mUserData[2] = String.valueOf(mPhone.getText());
                mUserData[3] = String.valueOf(mEmail.getText());

                // if user skipped some field
                for (String str : mUserData){
                    if(str.isEmpty()){
                        Toast.makeText(DetailsActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                Pattern pattern = Pattern.compile("\\w{2,}@\\w{2,}\\.\\w{2,}");
                Matcher matcher = pattern.matcher(mUserData[3]);
                if(!matcher.find()){
                    Toast.makeText(DetailsActivity.this, "Enter correct email", Toast.LENGTH_SHORT).show();
                    return;
                }

                // get writable DB
                SQLiteDatabase db = new SqlDataManager(DetailsActivity.this).getWritableDatabase();

                // prepare values for input
                ContentValues values = new ContentValues();
                values.put(SqlDataManager.NAME, mUserData[0]);
                values.put(SqlDataManager.LAST_NAME, mUserData[1]);
                values.put(SqlDataManager.PHONE, Integer.parseInt(mUserData[2]));
                values.put(SqlDataManager.EMAIL, mUserData[3]);


                String where = SqlDataManager.ID + "=" + selectionArgs[0];

                // insert data into DB
                db.update(SqlDataManager.TABLE_NAME, values, where, null);

                Toast.makeText(DetailsActivity.this, "Data updated", Toast.LENGTH_SHORT).show();

            }
        });



        // get readable DB
        SQLiteDatabase db = new SqlDataManager(this).getReadableDatabase();

        // define needed columns
        String[] projection = {
                SqlDataManager.NAME,
                SqlDataManager.LAST_NAME,
                SqlDataManager.PHONE,
                SqlDataManager.EMAIL,

        };

        // get row by user ID
        Cursor cursor = db.query(
                SqlDataManager.TABLE_NAME,          // The table to query
                projection,                         // The columns to return
                SqlDataManager.ID + " = ?",         // The columns for the WHERE clause
                selectionArgs,                      // The values for the WHERE clause
                null,                               // don't group the rows
                null,                               // don't filter by row groups
                null                                // The sort order
        );

        cursor.moveToFirst();

        //fill text views with user info
        mFirstName.setText(cursor.getString(cursor.getColumnIndex(SqlDataManager.NAME)));
        mLastName.setText(cursor.getString(cursor.getColumnIndex(SqlDataManager.LAST_NAME)));
        mPhone.setText(cursor.getString(cursor.getColumnIndex(SqlDataManager.PHONE)));
        mEmail.setText(cursor.getString(cursor.getColumnIndex(SqlDataManager.EMAIL)));

        cursor.close();

    }

}
