package com.example.game3connect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    boolean gameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void dropin(View view) {
        ImageView image = (ImageView) view;

        int tapcounter = Integer.parseInt(image.getTag().toString());

        if (gameState[tapcounter] == 2 && gameActive) {
            gameState[tapcounter] = activePlayer;
            if (activePlayer == 0) {

                image.setTranslationY(-1500);
                image.setImageResource(R.drawable.gold);
                image.animate().translationYBy(1500).rotation(3600).setDuration(400);
                activePlayer = 1;
            } else {
                image.setTranslationY(-1500);
                image.setImageResource(R.drawable.black);
                image.animate().translationYBy(1500).rotation(3600).setDuration(400);
                activePlayer = 0;
            }

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    String winner = "";
                    gameActive = false;
                    if (activePlayer == 1)
                        winner = "Gold";
                    else if (activePlayer == 0)
                        winner = "Black";

                    TextView winnerText = (TextView) findViewById(R.id.winnerText);
                    Button bt_playAgain = findViewById(R.id.bt_playAgain);
                    winnerText.setText(String.format("%s won", winner));
                    winnerText.setVisibility(View.VISIBLE);
                    bt_playAgain.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view) {

        activePlayer = 0;
        gameState = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};
        gameActive = true;
        TextView winnerText = (TextView) findViewById(R.id.winnerText);
        Button bt_playAgain = findViewById(R.id.bt_playAgain);
        winnerText.setVisibility(View.INVISIBLE);
        bt_playAgain.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for(int i=0; i < gridLayout.getChildCount(); i++){
           ImageView image = (ImageView) gridLayout.getChildAt(i);
           image.setImageDrawable(null);
        }

    }
}
