package com.example.connect3app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView connect3, gameStatus;
    private ImageView board, board1, board2, board3, board4, board5, board6, board7, board8, board9;
    private Button startGame, restart;

    private int boardNumber, playerTurn;
    private boolean gameStarted = false;
    private int boardChoosed = 0;
    private List<Integer> player1Choosed = new ArrayList<>();
    private List<Integer> player2Choosed = new ArrayList<>();
    private ArrayList<List<Integer>> winScenarios = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        clicked();
    }

    private void clicked() {
        startGame.setOnClickListener(this);
        board1.setOnClickListener(this);
        board2.setOnClickListener(this);
        board3.setOnClickListener(this);
        board4.setOnClickListener(this);
        board5.setOnClickListener(this);
        board6.setOnClickListener(this);
        board7.setOnClickListener(this);
        board8.setOnClickListener(this);
        board9.setOnClickListener(this);
        restart.setOnClickListener(this);
    }

    private void init() {
        connect3 = findViewById(R.id.connect3);
        board = findViewById(R.id.board);
        startGame = findViewById(R.id.startGame);
        board1 = findViewById(R.id.board1);
        board2 = findViewById(R.id.board2);
        board3 = findViewById(R.id.board3);
        board4 = findViewById(R.id.board4);
        board5 = findViewById(R.id.board5);
        board6 = findViewById(R.id.board6);
        board7 = findViewById(R.id.board7);
        board8 = findViewById(R.id.board8);
        board9 = findViewById(R.id.board9);
        gameStatus = findViewById(R.id.gameStatus);
        restart = findViewById(R.id.btnRestart);

        board.setImageResource(R.drawable.tictactoe_board);
        board.setBackgroundColor(80000000);
        board.setAlpha(0f);

        playerTurn = 1;
        winScenarios.add(Arrays.asList(1, 2, 3));
        winScenarios.add(Arrays.asList(4, 5, 6));
        winScenarios.add(Arrays.asList(7, 8, 9));
        winScenarios.add(Arrays.asList(1, 4, 7));
        winScenarios.add(Arrays.asList(2, 5, 8));
        winScenarios.add(Arrays.asList(3, 6, 9));
        winScenarios.add(Arrays.asList(1, 5, 9));
        winScenarios.add(Arrays.asList(3, 5, 7));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.startGame :
                startGame.animate().alpha(0f).setDuration(500);
                connect3.animate().translationYBy(-1000f).setDuration(800);
                gameStarted = true;
                board.animate().alpha(1f).setDuration(1000);

                return;
            case R.id.board1 :
                boardNumber = 1;
                choosed(boardNumber, board1);

                return;
            case R.id.board2 :
                boardNumber = 2;
                choosed(boardNumber, board2);

                return;
            case R.id.board3 :
                boardNumber = 3;
                choosed(boardNumber, board3);

                return;
            case R.id.board4 :
                boardNumber = 4;
                choosed(boardNumber, board4);

                return;
            case R.id.board5 :
                boardNumber = 5;
                choosed(boardNumber, board5);

                return;
            case R.id.board6 :
                boardNumber = 6;
                choosed(boardNumber, board6);

                return;
            case R.id.board7 :
                boardNumber = 7;
                choosed(boardNumber, board7);

                return;
            case R.id.board8 :
                boardNumber = 8;
                choosed(boardNumber, board8);

                return;
            case R.id.board9 :
                boardNumber = 9;
                choosed(boardNumber, board9);

                return;
            case R.id.btnRestart :
                restartGame();

                return;
        }
    }

    private void restartGame() {
        board1.setImageDrawable(null);
        board2.setImageDrawable(null);
        board3.setImageDrawable(null);
        board4.setImageDrawable(null);
        board5.setImageDrawable(null);
        board6.setImageDrawable(null);
        board7.setImageDrawable(null);
        board8.setImageDrawable(null);
        board9.setImageDrawable(null);

        boardButtons(true);

        gameStatus.setVisibility(View.GONE);
        restart.setVisibility(View.GONE);
        playerTurn = 1;
        gameStarted = true;
        boardChoosed = 0;
        player1Choosed.clear();
        player2Choosed.clear();
    }

    private void choosed(int boardNumber, ImageView boardSection) {
        boardSection.setEnabled(false);
        boardSection.setTranslationY(-1000f);

        checkPlayerTurn(boardNumber, boardSection);

        boardSection.animate().translationYBy(1000f).setDuration(1000);

        boardChoosed+=1;

        checkGameStatus();
    }

    private void checkGameStatus() {
        if (boardChoosed == 9) {
            gameStarted = false;
        }

        for (int x = 0; x < winScenarios.size(); x++) {
            if (player1Choosed.containsAll(winScenarios.get(x))) {
                gameStarted = false;
                gameStatus.setText("Player 1 Wins!!!");
            } else if (player2Choosed.containsAll(winScenarios.get(x))) {
                gameStarted = false;
                gameStatus.setText("Player 2 Wins!!!");
            }
        }

        if (!gameStarted) {
            boardButtons(false);
            gameStatus.setVisibility(View.VISIBLE);
            restart.setVisibility(View.VISIBLE);
        }
    }

    private void boardButtons(boolean b) {
        board1.setEnabled(b);
        board2.setEnabled(b);
        board3.setEnabled(b);
        board4.setEnabled(b);
        board5.setEnabled(b);
        board6.setEnabled(b);
        board7.setEnabled(b);
        board8.setEnabled(b);
        board9.setEnabled(b);
    }

    private void checkPlayerTurn(int boardNumber, ImageView boardSection) {
        if (playerTurn == 1) {
            boardSection.setImageResource(R.drawable.yellow_chip);
            player1Choosed.add(boardNumber);
            playerTurn = 2;
        } else {
            boardSection.setImageResource(R.drawable.red_chip);
            player2Choosed.add(boardNumber);
            playerTurn = 1;
        }
    }
}
