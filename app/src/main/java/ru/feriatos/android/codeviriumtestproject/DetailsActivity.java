package ru.feriatos.android.codeviriumtestproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity{

    Toolbar toolbar;
    TextView mFirstName;
    TextView mLastName;
    TextView mPhone;
    TextView mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        toolbar = (Toolbar) findViewById(R.id.tool_bar_details);
        setSupportActionBar(toolbar);

        mFirstName = (TextView) findViewById(R.id.detailFirstName);
        mLastName = (TextView) findViewById(R.id.detailLastName);
        mPhone = (TextView) findViewById(R.id.detailPhone);
        mEmail = (TextView) findViewById(R.id.detailEmail);

        // user ID from intent
        String[] selectionArgs = {getIntent().getStringExtra("USER_ID")};

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
                SqlDataManager.ID + " = ?",                  // The columns for the WHERE clause
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
