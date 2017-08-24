package com.example.shubbh.practice.database;

/**
 * Created by Shubbh on 19/10/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.shubbh.practice.R;

public class ModifyItem extends Activity implements OnClickListener {

    private EditText mnameText, mgstText, mppuText, mqtyText;
    private Button updateBtn, deleteBtn;

    private long itemname;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Modify Record");

        setContentView(R.layout.activity_modify_record);

        dbManager = new DBManager(this);
        dbManager.open();

        mnameText = (EditText) findViewById(R.id.nameText);
        mgstText = (EditText) findViewById(R.id.gstText);
        mppuText = (EditText) findViewById(R.id.ppuText);
        mqtyText = (EditText) findViewById(R.id.qtyText);

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String item = intent.getStringExtra("name");
        String gst = intent.getStringExtra("gst");
        String ppu = intent.getStringExtra("ppu");
        String qty = intent.getStringExtra("qty");

        itemname = Long.parseLong(item);

        mnameText.setText(item);
        mgstText.setText(gst);
        mppuText.setText(ppu);
        mqtyText.setText(qty);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String item = mnameText.getText().toString();
                String gst = mgstText.getText().toString();
                String ppu = mppuText.getText().toString();
                String qty = mqtyText.getText().toString();

                dbManager.update(item, gst, ppu, qty);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbManager.delete(itemname);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), ItemList.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
