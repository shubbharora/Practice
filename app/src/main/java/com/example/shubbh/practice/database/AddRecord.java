package com.example.shubbh.practice.database;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shubbh.practice.R;

/**
 * Created by Shubbh on 8/22/2017.
 */

public class AddRecord extends Activity implements View.OnClickListener {
    private Button mAddRecord;
    private EditText mItemName, mGst, mPpu, mQty;
    private TextView mTotal;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Add Record");

        setContentView(R.layout.addinvoiceproduct);

        mItemName = (EditText) findViewById(R.id.nameText);
        mGst = (EditText) findViewById(R.id.gstText);
        mPpu = (EditText) findViewById(R.id.ppuText);
        mQty = (EditText) findViewById(R.id.qtyText);
        mTotal = (TextView) findViewById(R.id.totalText);

        mAddRecord = (Button) findViewById(R.id.add_record);

        dbManager = new DBManager(this);
        dbManager.open();
        mAddRecord.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_record:

                final String item = mItemName.getText().toString();
                final String gst = mGst.getText().toString();
                final String ppu = mPpu.getText().toString();
                final String qty = mQty.getText().toString();
                final String total = mTotal.getText().toString();

                dbManager.insert(item, gst, ppu, qty, total);

                Intent main = new Intent(AddRecord.this, ItemList.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }
}
