package com.cmput301.ttran1.feelsbook;

import android.app.DialogFragment;
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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements AddEmotionDialogFragment.AddEmotionDialogListener {

    private RecyclerView recyclerView;
    private EmotionsHistoryAdapter rvAdapter;

    private TextView loveCountTextView;
    private TextView joyCountTextView;
    private TextView surpriseCountTextView;
    private TextView angerCountTextView;
    private TextView sadnessCountTextView;
    private TextView fearCountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loveCountTextView = (TextView) findViewById(R.id.loveCountText);
        joyCountTextView = (TextView) findViewById(R.id.joyCountText);
        surpriseCountTextView = (TextView) findViewById(R.id.surpriseCountText);
        angerCountTextView = (TextView) findViewById(R.id.angerCountText);
        sadnessCountTextView = (TextView) findViewById(R.id.sadnessCountText);
        fearCountTextView = (TextView) findViewById(R.id.fearCountText);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddEmotionDialogFragment addEmotionDialogFragment = new AddEmotionDialogFragment();
                addEmotionDialogFragment.show(getFragmentManager(), "addEmotion");
            }
        });

        for (int i = 0; i < EmotionsHistory.getEmotions().size(); i++) {
            Log.d("debug", EmotionsHistory.getEmotions().get(i).getEmotion());
        }

        recyclerView = (RecyclerView) findViewById(R.id.emotionsHistoryView);
        recyclerView.setHasFixedSize(true);     // improves performance
        rvAdapter = new EmotionsHistoryAdapter(EmotionsHistory.getEmotions());
        recyclerView.setAdapter(rvAdapter);
        // use a linear layout manager (similar to a ListView)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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

    // Used by the AddEmotionDialogFragment
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        updateCountText();
        rvAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
    }

    public void updateCountText() {
        EmotionsHistory.count();
        loveCountTextView.setText("Love: " + Integer.toString(EmotionsHistory.getLoveCount()));
        joyCountTextView.setText("Joy: " + Integer.toString(EmotionsHistory.getJoyCount()));
        surpriseCountTextView.setText("Surprise: " + Integer.toString(EmotionsHistory.getSurpriseCount()));
        angerCountTextView.setText("Anger: " + Integer.toString(EmotionsHistory.getAngerCount()));
        sadnessCountTextView.setText("Sadness: " + Integer.toString(EmotionsHistory.getSadnessCount()));
        fearCountTextView.setText("Fear: " + Integer.toString(EmotionsHistory.getFearCount()));
    }
}
