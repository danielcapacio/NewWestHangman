package a00957203.comp3717.bcit.ca.new_west_hangman;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WinnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        getWindow().getDecorView().setBackgroundColor(Color.GRAY);
    }
}
