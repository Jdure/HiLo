package com.algonquincollege.dure0018.hilo;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

/**
 * Hilo Guessing Game: Android guessing game
 *
 * @author Jonathan Dure (Dure0018@algonquinlive.com)
 */

public class MainActivity extends Activity {

    private static final String ABOUT_DIALOG_TAG = "About Dialog";

    public Button guess_btn;
    public Button reset_btn;
    public EditText guess_num;

    //Set number range
    final int MIN = 1;
    final int MAX = 1000;
    int theNumber;

    //Count the guess from the user
    int guessCounter;
    final int maxCount = 10;

    //TODO: use strings.xml for these
    //Strings
    String correct = "Congratulation You Guessed Correctly";
    String tooLow = "Your Guess Was Too Low";
    String tooHigh = "Your Guess Was Too High";
    String superior = "Superior win!";
    String excellent = "Excellent win!";
    String reset = "Please Reset!";

    //Number guessed by the user
    int userGuess;

    //Random Number Generator
    Random random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        guess_num = findViewById(R.id.guess_num);
        guess_btn = findViewById(R.id.guess_btn);
        guess_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: use strings.xml for these too
                //Validate that text field is not empty
                if (guess_num.getText().toString().matches("")) {
                    guess_num.setError("Invalid Number");
                    Toast.makeText(getApplicationContext(), "Please input a number between 1 and 1000", Toast.LENGTH_SHORT).show();
                    return;
                }


                //User guess Input
                userGuess = Integer.parseInt(guess_num.getText().toString());

                //Check is number is no lower than 1 or higher than 1000
                if (userGuess < MIN || userGuess > MAX) {
                    guess_num.setText("");
                    guess_num.setError("Invalid Number");
                    Toast.makeText(getApplicationContext(), "Please input a number between 1 and 1000", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Count the user guesses
                guessCounter++;

                //Game Logic
                if (userGuess == theNumber && guessCounter <= 5) {
                    Toast.makeText(getApplicationContext(), correct, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), superior + " You took " + (guessCounter) + " guesses", Toast.LENGTH_SHORT).show();

                } else if (userGuess == theNumber && guessCounter <= maxCount) {
                    Toast.makeText(getApplicationContext(), correct, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), excellent + " You took " + (guessCounter) + " guesses", Toast.LENGTH_SHORT).show();
                } else if (userGuess < theNumber && guessCounter < maxCount) {

                    Toast.makeText(getApplicationContext(), tooLow, Toast.LENGTH_SHORT).show();
                } else if (userGuess > theNumber && guessCounter < maxCount) {

                    Toast.makeText(getApplicationContext(), tooHigh, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), reset, Toast.LENGTH_SHORT).show();
                }
            }

        });

        //Reset On Click
        reset_btn = findViewById(R.id.reset_btn);
        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Reset a new Number
                theNumber = random.nextInt(MAX) + MIN;
                guessCounter = 0;
                Toast.makeText(getApplicationContext(), "Game has been reset", Toast.LENGTH_SHORT).show();
            }
        });

        //Show Answer and Reset Long Click
        reset_btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //Release answer
                Toast.makeText(getApplicationContext(), "Answer is " + theNumber + " game has been reset", Toast.LENGTH_SHORT).show();
                theNumber = random.nextInt(MAX) + MIN;
                guessCounter = 0;
                return true;
            }
        });

        //Initialize random number
        random = new Random();

        //The secret Number
        theNumber = random.nextInt(MAX) + MIN;


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
