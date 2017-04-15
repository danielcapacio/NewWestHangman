package a00957203.comp3717.bcit.ca.new_west_hangman;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MultiplayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);
        getWindow().getDecorView().setBackgroundColor(Color.GRAY);
    }

    public void sendWordAndHint(View v) {
        EditText myEditText = (EditText) findViewById(R.id.editTextWord);
        EditText myEditHint = (EditText) findViewById(R.id.editTextHint);

        String word = myEditText.getText().toString();
        String hint = myEditHint.getText().toString();
        myEditText.setText("");
        myEditText.setText("");
        Intent gameMultiIntent = new Intent(this,GameMultiActivity.class);

        gameMultiIntent.putExtra("word", word);
        gameMultiIntent.putExtra("hint", hint);
        startActivity(gameMultiIntent);
    }
}
