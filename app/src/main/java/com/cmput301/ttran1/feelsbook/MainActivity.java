package com.cmput301.ttran1.feelsbook;

import android.os.Bundle;
import android.os.RecoverySystem;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private EmotionsHistory emotionsHistory;

    private RecyclerView recyclerView;
    private EmotionsHistoryAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emotionsHistory = new EmotionsHistory();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddEmotionDialogFragment addEmotionDialogFragment = new AddEmotionDialogFragment();
                addEmotionDialogFragment.show(getFragmentManager(), "addEmotion");
            }
        });

        this.addEmotions();

        for (int i = 0; i < emotionsHistory.getEmotions().size(); i++) {
            Log.d("debug", emotionsHistory.getEmotions().get(i).getEmotion());
        }

        recyclerView = (RecyclerView) findViewById(R.id.emotionsHistoryView);
//        recyclerView.setHasFixedSize(true);     // improves performance
        rvAdapter = new EmotionsHistoryAdapter(emotionsHistory.getEmotions());
        recyclerView.setAdapter(rvAdapter);
        // use a linear layout manager (similar to a ListView)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        rvAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addEmotions() {
        this.emotionsHistory.addEmotion(new Love());
        this.emotionsHistory.addEmotion(new Joy());
        this.emotionsHistory.addEmotion(new Surprise());
    }
}
