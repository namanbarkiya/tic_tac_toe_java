package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0; // Represents the active player's turn: 0 for 'X' player, 1 for 'O' player

    // Represents the state of each cell on the board: -1 for empty, 0 for 'X', 1 for 'O'
    int[] states = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

    // Defines the winning combinations as indices of states array
    int[][] winBlocks = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int flag = -1; // Indicates the current match state: -1 for ongoing match, 0 for 'X' win, 1 for 'O' win
    char winner = 'X'; // Represents the winner of the match: 'X' for 'X' player, 'O' for 'O' player (default value is 'X')


    // Reset the match and clear the board
    public void resetMatch(View view) {
        TextView winnerText = findViewById(R.id.winnerText);
        winnerText.setText("");

        Arrays.fill(states, -1);
        activePlayer = 0;
        flag = -1;

        // Reset image resources for all ImageViews
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
    }

    // Check if there is a winning match
    public void checkMatch() {
        TextView winnerText = findViewById(R.id.winnerText);

        for (int i = 0; i < 8; i++) {
            int[] arr = {-1, -1, -1};
            for (int j = 0; j < 3; j++) {
                arr[j] = states[winBlocks[i][j]];
            }

            if(arr[0] == arr[1] && arr[0] == arr[2]) {
                flag = arr[0];
                break;
            }
        }

        if(flag != -1) {
            if(flag == 1) {
                winner = 'O';
            }
            winnerText.setText("Winner is: " + winner);
        }
    }

    // Handle click event on each ImageView
    public void onTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImg = Integer.parseInt(view.getTag().toString());

        if(flag == -1) {
            if (states[tappedImg] == -1) {
                states[tappedImg] = activePlayer;
                if (activePlayer == 0) {
                    activePlayer = 1;
                    img.setImageResource((R.drawable.x));
                } else {
                    activePlayer = 0;
                    img.setImageResource((R.drawable.o));
                }
            }
            checkMatch();
        } else {
            Toast.makeText(this, "Match has already ended! " + winner + " won!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
