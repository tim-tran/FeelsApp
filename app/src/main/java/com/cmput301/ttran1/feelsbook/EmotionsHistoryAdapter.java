package com.cmput301.ttran1.feelsbook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class EmotionsHistoryAdapter extends
        RecyclerView.Adapter<EmotionsHistoryAdapter.ViewHolder> {
    private ArrayList<Emotion> emotions;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView emotionTextView;
        public TextView commentTextView;
        public TextView dateTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            emotionTextView = (TextView) itemView.findViewById(R.id.emotionTypeText);
            commentTextView = (TextView) itemView.findViewById(R.id.commentText);
            dateTextView = (TextView) itemView.findViewById(R.id.dateText);
            Log.d("debug", emotionTextView.toString());
            Log.d("debug", commentTextView.toString());
        }
    }

    public EmotionsHistoryAdapter(ArrayList<Emotion> data) {
        this.emotions = data;
        Log.d("debug", Integer.toString(emotions.size()));
    }

    // Creates new views (invoked by the layout manager)
    @Override
    public EmotionsHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View emotionView = inflater.inflate(R.layout.emotion_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(emotionView);
        return viewHolder;
    }

    // Replaces the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewholder, int position) {
        // - get element from your dataset at this position
        Emotion emotion = emotions.get(position);

        // - replace the contents of the view with that element
        viewholder.emotionTextView.setText(emotion.getEmotion());
        viewholder.commentTextView.setText(emotion.getComment());
        viewholder.dateTextView.setText(emotion.getTimestamp().toString());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return emotions.size();
    }
}
