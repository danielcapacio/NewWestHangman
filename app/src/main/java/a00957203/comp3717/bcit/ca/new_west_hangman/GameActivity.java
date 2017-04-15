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

import java.util.Random;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    String mWord, type;
    int mFailCounter = 0, mGuessedLetter = 0, mPoints = 0, lives = 8;
    ArrayList<Character> guessedLetters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getWindow().getDecorView().setBackgroundColor(Color.GRAY);
        setRandomWord();
        createTextViews(mWord);
        guessedLetters = new ArrayList<>();

        // Setting hint category on textview
        TextView hintCat = (TextView) findViewById(R.id.hint);
        hintCat.setText("Hint: " + type);
        // Initialize number of lives
        TextView userTries = (TextView) findViewById(R.id.tries);
        userTries.setText("Guesses left: " + lives);
    }

    /**
     * Retrieving the letter introduced on the editTextLetter
     */
    public void introduceLetter(View v) {
        EditText myEditText = (EditText) findViewById(R.id.editTextLetter);
        String letter = myEditText.getText().toString().toLowerCase ();
        myEditText.setText("");

        if (letter.length() == 1) {
            checkLetter(letter);
        } else {
            Toast.makeText(this, "Please enter a letter",Toast.LENGTH_SHORT).show();
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
            if(charFromTheWord == charIntroduced) {
                showLettersAtIndex(i, charIntroduced);
                letterGuessed = true;
                ++mGuessedLetter;
            }
        }
        if (letterGuessed == false) {
            letterFailed(Character.toString(charIntroduced));
        }
        if (mGuessedLetter == mWord.length()) {
            mPoints++;
            clearScreen();
            setRandomWord();
            createTextViews(mWord);
        }
    }

    /**
     * Generating random from the 3 chosen datasets
     */
    public void setRandomWord() {
        int selection = new Random().nextInt(3), randomNumber;
        if (selection == 0) {
            randomNumber = (int) (Math.random() * MainActivity.sports_fields.size());
            type         = "Sports Field";
            mWord        = MainActivity.sports_fields.get(randomNumber);
        } else if (selection == 1) {
            randomNumber = (int) (Math.random() * MainActivity.skytrain_stations.size());
            type         = "Skytrain Station";
            mWord        = MainActivity.skytrain_stations.get(randomNumber);
        } else {
            randomNumber = (int) (Math.random() * MainActivity.public_art.size());
            type         = "Public Art";
            mWord        = MainActivity.public_art.get(randomNumber);
        }
    }

    /**
     * Dynamically creates layout for given word
     */
    public void createTextViews(String word) {
        LinearLayout layoutLetters = (LinearLayout) findViewById(R.id.layoutLetters);
        for (int i = 0; i < word.length(); ++i) {
            TextView newTextView = (TextView) getLayoutInflater().inflate(R.layout.textview, null);
            layoutLetters.addView(newTextView);
        }
    }

    /**
     * Clear the screen and reset counters
     */
    public void clearScreen() {
        TextView textView = (TextView) findViewById(R.id.failedLetterText);
        textView.setText("");

        mFailCounter   = 0;
        mGuessedLetter = 0;
        guessedLetters = new ArrayList<>();
        LinearLayout layoutLetters = (LinearLayout) findViewById(R.id.layoutLetters);
        layoutLetters.removeAllViewsInLayout();
        ImageView imageView = (ImageView) findViewById(R.id.imageView3);
        imageView.setImageResource(R.drawable.stage1);
    }

    /**
     * Displaying a letter guessed by the user
     */
    public void showLettersAtIndex(int position, char letterGuessed){
        LinearLayout layoutLetter = (LinearLayout) findViewById(R.id.layoutLetters);
        TextView textView = (TextView) layoutLetter.getChildAt(position);
        textView.setText(Character.toString(letterGuessed));
        textView.setTextSize(20);
    }

    /**
     * Display failed letters, change the image view, new intent when the game is over
     */
    public void letterFailed(String letterFailed) {
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
