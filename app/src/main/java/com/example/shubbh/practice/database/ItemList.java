package com.example.shubbh.practice.database;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shubbh.practice.R;

/**
 * Created by Shubbh on 8/22/2017.
 */

public class ItemList extends AppCompatActivity {

    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper.COL_22, DatabaseHelper.COL_23, DatabaseHelper.COL_24,
            DatabaseHelper.COL_25, DatabaseHelper.COL_26};

    final int[] to = new int[] { R.id.inameTV, R.id.igstTV, R.id.ippuTV, R.id.iqtyTV, R.id.itotalTV, };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice__list);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.view_record, cursor, from, to, 0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView nameTv = (TextView) view.findViewById(R.id.inameTV);
                TextView gstTv = (TextView) view.findViewById(R.id.igstTV);
                TextView ppuTv = (TextView) view.findViewById(R.id.ippuTV);
                TextView qtyTv = (TextView) view.findViewById(R.id.iqtyTV);
                TextView totalTv = (TextView) view.findViewById(R.id.itotalTV);

                String name = nameTv.getText().toString();
                String gst = gstTv.getText().toString();
                String ppu = ppuTv.getText().toString();
                String qty = qtyTv.getText().toString();
                String total = totalTv.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModifyItem.class);
                modify_intent.putExtra("name", name);
                modify_intent.putExtra("gst", gst);
                modify_intent.putExtra("ppu", ppu);
                modify_intent.putExtra("qty", qty);
                modify_intent.putExtra("total", total);

                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {

            Intent add_mem = new Intent(this, AddRecord.class);
            startActivity(add_mem);
        }
        return super.onOptionsItemSelected(item);
    }
}
