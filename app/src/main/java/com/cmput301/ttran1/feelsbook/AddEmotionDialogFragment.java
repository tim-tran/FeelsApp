package com.cmput301.ttran1.feelsbook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class AddEmotionDialogFragment extends DialogFragment {

    View view;
    Spinner emotionSelect;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        this.view = inflater.inflate(R.layout.dialog_add_emotion, null);

        Spinner emotionSelect = (Spinner) this.view.findViewById(R.id.emotionSelect);
        // Create an ArrayAdapter using the emotions array and a default spinner layout
        ArrayAdapter<CharSequence> emotionsAdapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.emotions_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        emotionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        emotionSelect.setAdapter(emotionsAdapter);

        builder.setView(this.view)
                .setPositiveButton(R.string.dialog_add, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast toast = Toast.makeText(getActivity(), "Adding Emotion", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                })
                .setNegativeButton(R.string.dialog_discard, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast toast = Toast.makeText(getActivity(), "Discarding Emotion", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                })
                .setTitle(R.string.dialog_add_emotion_title);

        // Create the AlertDialog object and return it
        return builder.create();
    }

}