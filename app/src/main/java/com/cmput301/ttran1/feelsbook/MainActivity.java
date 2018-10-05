package com.cmput301.ttran1.feelsbook;

import android.app.DialogFragment;
import android.content.Context;
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class MainActivity extends AppCompatActivity
        implements AddEmotionDialogFragment.AddEmotionDialogListener,
        EditEmotionDialogFragment.EditEmotionDialogListener{

    private RecyclerView recyclerView;
    private EmotionsHistoryAdapter rvAdapter;

    private TextView loveCountTextView;
    private TextView joyCountTextView;
    private TextView surpriseCountTextView;
    private TextView angerCountTextView;
    private TextView sadnessCountTextView;
    private TextView fearCountTextView;

    private static final String FILENAME = "SavedEmotions.sav";

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

        recyclerView = (RecyclerView) findViewById(R.id.emotionsHistoryView);
        recyclerView.setHasFixedSize(true);     // improves performance

        ItemClickSupport.addTo(recyclerView).setOnItemLongClickListener(
                new ItemClickSupport.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClicked(RecyclerView recyclerView, int position,
                                                     View v) {
                        EditEmotionDialogFragment editEmotionDialogFragment =
                                new EditEmotionDialogFragment();
                        editEmotionDialogFragment.setEmotionIndex(position);
                        editEmotionDialogFragment.show(getFragmentManager(), "editEmotion");
                        return false;
                    }
                }
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        /* Try block taken from
        https://androidresearch.wordpress.com/2013/04/07/
        aching-objects-in-android-internal-storage/
         2018-10-04
         */
        try {
            // Retrieve the list from internal storage
            EmotionsHistory.getSavedEmotions(this);
        } catch (IOException e) {
            Log.e("debug", e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("debug", e.getMessage());
        }
        rvAdapter = new EmotionsHistoryAdapter(EmotionsHistory.getEmotions());
        recyclerView.setAdapter(rvAdapter);
        // use a linear layout manager (similar to a ListView)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter.notifyDataSetChanged();
        updateCountText();
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

    // Called when the DialogFragment buttons are pressed.
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        updateCountText();
        rvAdapter.notifyDataSetChanged();
    }

    // Called when the DialogFragment buttons are pressed.
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
