package a00957203.comp3717.bcit.ca.new_west_hangman;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {
    int mPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        getWindow().getDecorView().setBackgroundColor(Color.GRAY);

        int points = getIntent().getIntExtra("points_id", 0);
        String word = getIntent().getStringExtra("word_id");

        TextView textViewPoints = (TextView) findViewById(R.id.textViewPoints);
        textViewPoints.setText(String.valueOf(points));
        TextView textViewAnswer = (TextView) findViewById(R.id.wordAnswer);
        textViewAnswer.setText(word);

        mPoints = points;
    }

    /**
     * Saving user scores through shared preferences
     */
    public void saveScore(View v){
        SharedPreferences preferences = getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        EditText editText = (EditText) findViewById(R.id.addName);
        String name = editText.getText().toString();
        SharedPreferences.Editor editor = preferences.edit();
        String previousPoints = preferences.getString("SCORES","");
        editor.putString("SCORES", name + "     ---      " + mPoints + " STREAK\n" + previousPoints);
        editor.commit();
        finish();
    }
}
