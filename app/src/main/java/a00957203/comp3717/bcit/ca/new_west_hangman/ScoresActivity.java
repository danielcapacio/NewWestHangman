package a00957203.comp3717.bcit.ca.new_west_hangman;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class ScoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        getWindow().getDecorView().setBackgroundColor(Color.GRAY);

        SharedPreferences preferences = getSharedPreferences("MYPREF", MODE_PRIVATE);
        String scores = preferences.getString("SCORES","NO SCORES");
        TextView textViewScores = (TextView) findViewById(R.id.textViewScores);
        textViewScores.setText(scores);
    }
}
