package com.example.tictactoe;
import android.media.Image;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    int activePlayer=0;
    boolean gameActive=true;
    public void dropIn(View view) {
            ImageView counter = (ImageView) view;
            int tappedCounter = Integer.parseInt(counter.getTag().toString());
            if(gameState[tappedCounter]==2 && gameActive) {
                gameState[tappedCounter] = activePlayer;
                counter.setTranslationY(-1500);
                if (activePlayer == 0) {
                    counter.setImageResource(R.drawable.yellow);
                    counter.animate().translationYBy(1500).setDuration(300).rotation(1800);
                    activePlayer = 1;
                } else {
                    counter.setImageResource(R.drawable.red);
                    counter.animate().translationYBy(1500).setDuration(300).rotation(1800);
                    activePlayer = 0;
                }
                for (int[] winningPosition : winningPositions) {

                    if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                        gameActive = false;
                        String message = "";
                        if (activePlayer == 1) {
                            message = "Yellow";
                        } else {
                            message = "Red";
                        }
                        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                        winnerTextView.setText(message + " has won");
                        winnerTextView.setVisibility(View.VISIBLE);
                        playAgainButton.setVisibility(View.VISIBLE);

                    }
                }
            }
    }
    public void playAgain(View view)
    {
        Button playAgainButton =  (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView =  (TextView)findViewById(R.id.winnerTextView);
        winnerTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);

        for(int i=0;i<gridLayout.getChildCount();i++) {
            ImageView counter=(ImageView) gridLayout.getChildAt(i);
          counter.setImageDrawable(null);
        }
        for(int j=0;j<gameState.length;j++) {
         gameState[j]=2;
        }
        activePlayer=0;
        gameActive=true;
    }


}