package a00957203.comp3717.bcit.ca.new_west_hangman;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameMultiActivity extends AppCompatActivity {
    String mWord;
    int mFailCounter = 0, mGuessedLetter = 0, mPoints = 0, lives = 8;
    ArrayList<Character> guessedLetters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_game);
        getWindow().getDecorView().setBackgroundColor(Color.GRAY);

        String wordSent = getIntent().getStringExtra("word");
        String hintSent = getIntent().getStringExtra("hint");

        createTextViews(wordSent);
        guessedLetters = new ArrayList<>();
        mWord = wordSent.toUpperCase();

        // Initialize number of lives
        TextView userTries = (TextView) findViewById(R.id.tries);
        userTries.setText("Guesses left: " + lives);

        TextView hintView = (TextView) findViewById(R.id.hintMulti);
        if (!hintSent.equals("")) {
            hintView.setText(hintSent);
        } else {
            hintView.setText("No hint provided");
        }
    }

    /**
     * Retrieving the letter introduced on editTextLetter
     */
    public void introduceLetter(View v) {
        EditText myEditText = (EditText) findViewById(R.id.editTextLetter);
        String letter = myEditText.getText().toString();
        myEditText.setText("");

        if(letter.length() == 1){
            checkLetter(letter.toUpperCase());
        } else {
            Toast.makeText(this, "Please enter a letter!",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Checking if the letter introduced matches any letter in the word
     */
    public void checkLetter(String introducedLetter){
        char charIntroduced = introducedLetter.charAt(0);

        for (Character c : guessedLetters)
            if (c == charIntroduced)
                return;

        guessedLetters.add (charIntroduced);

        boolean letterGuessed = false;

        for (int i = 0; i < mWord.length(); i++) {
            char charFromTheWord = mWord.charAt(i);
            if (charFromTheWord == charIntroduced) {
                showLettersAtIndex(i, charIntroduced);
                letterGuessed = true;
                mGuessedLetter++;
            }
        }
        if (!letterGuessed) {
            letterFailed(Character.toString(charIntroduced));
        }
        if (mGuessedLetter == mWord.length()) {
            Intent intent = new Intent(this, WinnerActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * Dynamically creates layout for given word
     */
    public void createTextViews(String word) {
        LinearLayout layoutLetters = (LinearLayout) findViewById(R.id.layoutLetters);
        for (int i = 0; i < word.length(); i++) {
            TextView newTextView = (TextView) getLayoutInflater().inflate(R.layout.textview, null);
            layoutLetters.addView(newTextView);
        }
    }

    /**
     * Displaying a letter guessed by the user
     */
    public void showLettersAtIndex(int position, char letterGuessed) {
        LinearLayout layoutLetter = (LinearLayout) findViewById(R.id.layoutLetters);
        TextView textView = (TextView) layoutLetter.getChildAt(position);
        textView.setText(Character.toString(letterGuessed));
    }

    /**
     * Display failed letters, change the image view, new intent when the game is over
     */
    public void letterFailed(String letterFailed){
        TextView textView = (TextView) findViewById(R.id.failedLetterText);
        String prevFail = textView.getText().toString();
        textView.setText(prevFail + letterFailed);
        mFailCounter++;

        ImageView imageView = (ImageView) findViewById(R.id.imageView3);
        TextView userTries = (TextView) findViewById(R.id.tries);

        if (mFailCounter == 1) {
            imageView.setImageResource(R.drawable.stage2);
            lives--;
            userTries.setText("Guesses left: " + lives);
        } else if (mFailCounter == 2) {
            imageView.setImageResource(R.drawable.stage3);
            lives--;
            userTries.setText("Guesses left: " + lives);
        } else if (mFailCounter == 3) {
            imageView.setImageResource(R.drawable.stage4);
            lives--;
            userTries.setText("Guesses left: " + lives);
        } else if (mFailCounter == 4) {
            imageView.setImageResource(R.drawable.stage5);
            lives--;
            userTries.setText("Guesses left: " + lives);
        } else if (mFailCounter == 5) {
            imageView.setImageResource(R.drawable.stage6);
            lives--;
            userTries.setText("Guesses left: " + lives);
        } else if (mFailCounter == 6) {
            imageView.setImageResource(R.drawable.stage7);
            lives--;
            userTries.setText("Guesses left: " + lives);
        } else if (mFailCounter == 7) {
            imageView.setImageResource(R.drawable.stage8);
            lives--;
            userTries.setText("Last guess!");
            userTries.setTextColor(Color.RED);
        } else if (mFailCounter == 8) {
            Intent gameOverIntent = new Intent(this,GameOverActivity.class);
            gameOverIntent.putExtra("points_id", mPoints);
            gameOverIntent.putExtra("word_id", mWord);
            startActivity(gameOverIntent);
            finish();
        }
    }
}
