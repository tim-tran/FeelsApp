package com.cmput301.ttran1.feelsbook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/*
Class: AddEmotionDialogFragment

Handles the popup for adding an emotion.
Manages the popup view.
Saves the added emotion to memory.

Code referenced from
https://developer.android.com/guide/topics/ui/dialogs#AlertDialog
https://developer.android.com/guide/topics/ui/controls/spinner
 */
public class AddEmotionDialogFragment extends DialogFragment
        implements AdapterView.OnItemSelectedListener {

    private View view;
    private Spinner emotionSelect;
    private String selectedEmotion;

    private static final String FILENAME = "SavedEmotions.sav";

    // Calling activity must implement this to know when dialog buttons are pressed.
    public interface AddEmotionDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    AddEmotionDialogListener dialogListener;

    // For the spinner selection
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedEmotion = parent.getItemAtPosition(position).toString();
    }

    // For the spinner selection
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    // Forces the calling activity to implement the listener
    // interface so it knows when dialog buttons are pressed.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            // If the calling activity didn't implement dialog listener interface,
            // this will throw an exception.
            dialogListener = (AddEmotionDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement Notice Dialog Listener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        this.view = inflater.inflate(R.layout.dialog_add_emotion, null);

        emotionSelect = (Spinner) this.view.findViewById(R.id.emotionSelect);
        // Create an ArrayAdapter using the emotions array and a default spinner layout
        ArrayAdapter<CharSequence> emotionsAdapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.emotions_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        emotionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        emotionSelect.setAdapter(emotionsAdapter);

        emotionSelect.setOnItemSelectedListener(this);

        builder.setView(this.view)
                .setPositiveButton(R.string.dialog_add, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        saveEmotion();
                        dialogListener.onDialogPositiveClick(AddEmotionDialogFragment.this);
                    }
                })
                .setNegativeButton(R.string.dialog_discard, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialogListener.onDialogNegativeClick(AddEmotionDialogFragment.this);
                    }
                })
                .setTitle(R.string.dialog_add_emotion_title);

        // Create the AlertDialog object and return it
        return builder.create();
    }

    private void saveEmotion() {
        try {
            EditText commentTextView = view.findViewById(R.id.comment);
            String comment = commentTextView.getText().toString();
            Emotion newEmo = EmotionFactory.makeEmotion(selectedEmotion);
            newEmo.setComment(comment);
            EmotionsHistory.addEmotion(newEmo);
            InternalStorage.writeObject(getActivity(), FILENAME,
                    EmotionsHistory.getEmotions());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}