package com.cmput301.ttran1.feelsbook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

/*
Class: EditEmotionDialogFragment

Handles the popup for editing an emotion.
Manages the popup view.
Saves the replaces the edited emotion in memory.

Code referenced from
https://developer.android.com/guide/topics/ui/dialogs#AlertDialog
https://developer.android.com/guide/topics/ui/controls/spinner
 */
public class EditEmotionDialogFragment extends DialogFragment
        implements AdapterView.OnItemSelectedListener {
    private View view;
    private Spinner emotionSelect;
    private String selectedEmotion;

    private int emotionIndex;

    private static final String FILENAME = "SavedEmotions.sav";

    public interface EditEmotionDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    EditEmotionDialogFragment.EditEmotionDialogListener dialogListener;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedEmotion = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            dialogListener = (EditEmotionDialogFragment.EditEmotionDialogListener) context;
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
        ArrayAdapter<CharSequence> emotionsAdapter = ArrayAdapter.createFromResource(
                view.getContext(), R.array.emotions_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        emotionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        emotionSelect.setAdapter(emotionsAdapter);

        emotionSelect.setOnItemSelectedListener(this);

        builder.setView(this.view)
                .setPositiveButton(R.string.dialog_add, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        replaceEmotion();
                        dialogListener.onDialogPositiveClick(EditEmotionDialogFragment.this);
                    }
                })
                .setNegativeButton(R.string.dialog_discard, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialogListener.onDialogNegativeClick(EditEmotionDialogFragment.this);
                    }
                })
                .setTitle(R.string.dialog_edit_emotion_title);

        // Create the AlertDialog object and return it
        return builder.create();
    }

    // Sets the index of the emotion to edit.
    public void setEmotionIndex(int ind) {
        this.emotionIndex = ind;
    }

    private void replaceEmotion() {
        try {
            EditText commentTextView = view.findViewById(R.id.comment);
            String comment = commentTextView.getText().toString();
            Emotion replaceEmo = EmotionFactory.makeEmotion(selectedEmotion);
            replaceEmo.setComment(comment);
            EmotionsHistory.setEmotionByIndex(emotionIndex, replaceEmo);
            InternalStorage.writeObject(getActivity(), FILENAME,
                    EmotionsHistory.getEmotions());
        } catch (NullPointerException e) {
            Log.e("debug", "Nullpointer when rewriting emotion. " +
                    "Was the emotion index properly assigned?");
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
