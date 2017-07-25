package com.logcat.anilreddy.tic_tac_toegame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //If activePlayer = 0 then cross and activePlayer= 1 then circle
    int activePlayer = 0;

    boolean gameIsActive = true;

    // where 2 for UnPalying
    int[] gameStatus ={2,2,2,2,2,2,2,2,2};

    int[][] winningChances = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8}, {0,4,8},{2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCount = Integer.parseInt(counter.getTag().toString());

        if (gameStatus[tappedCount] == 2 && gameIsActive) {

            gameStatus[tappedCount] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.cross);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.circle);

                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for(int[] winning : winningChances) {

                if (gameStatus[winning[0] ] == gameStatus[winning[1]] &&
                        gameStatus[winning[1]] == gameStatus[winning[2]] &&
                        gameStatus[winning[0]] == 2 ) {

                    gameIsActive = false;

                    String winner = "cross ";

                    if ((gameStatus[winning[0]]) == 0) {

                        winner = "circle";
                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                    winnerMessage.setText(winner + " Player is the winner");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);

                    layout.setVisibility(View.VISIBLE);
                } else  {

                    boolean gameIsOver = true;

                    for(int counterState : gameStatus) {

                        if(counterState == 2) gameIsOver =false;

                    }

                    if(gameIsOver) {
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                        winnerMessage.setText( "It's a draw");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);

                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view)
    {

        gameIsActive = true;

        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i = 0; i< gameStatus.length;i++)
        {

            gameStatus[i] = 2;

            GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

            for(i = 0;i<gridLayout.getColumnCount();i++)
            {
                ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
            }

        }
    }
}
