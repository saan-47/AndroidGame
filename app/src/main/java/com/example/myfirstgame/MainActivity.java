package com.example.myfirstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Trace;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activeplayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningConditions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void PlayAgain(View view){
        TextView tx = findViewById(R.id.textView);
        tx.setText("");
        Button btnPlay = findViewById(R.id.button3);
        btnPlay.setEnabled(false);
        activeplayer = 0;
        Isgameactive = true;
        for(int i = 0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        //ImageView img = findViewById(R.id.imageView1);

        int[] images = {R.id.imageView1, R.id.imageView2, R.id.imageView3, R.id.imageView4, R.id.imageView5, R.id.imageView6, R.id.imageView7, R.id.imageView8, R.id.imageView9};
        for(int i = 0; i<images.length; i++){
            ImageView img = (ImageView) findViewById(images[i]);
            img.setImageDrawable(null);
        }
    }

    public void Draw() {
        for (int i = 0; i < gameState.length; i++) {
            if (gameState[i] != 2) {
              //Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show();
            }
            else {
                Button btn = findViewById(R.id.button3);
                btn.setEnabled(true);
            }
        }
    }
    boolean Isgameactive = true;
    public void displayImage(View view) {
        //ImageView im1 = (ImageView) findViewById(R.id.imageView1);
        ImageView im1 = (ImageView) view;
        int tapcounter = Integer.parseInt(im1.getTag().toString());


        if (gameState[tapcounter] == 2 && Isgameactive) {
            gameState[tapcounter] = activeplayer;
            if (activeplayer == 0) {
                im1.setImageResource(R.drawable.circle);
                activeplayer = 1;
            } else {
                im1.setImageResource(R.drawable.corss);
                activeplayer = 0;
            }
            for (int[] winning : winningConditions) {
                if (gameState[winning[0]] == gameState[winning[1]]
                        && gameState[winning[1]] == gameState[winning[2]]
                        && gameState[winning[0]] != 2) {
                    String winner = "";
                    if (activeplayer == 1) {
                        winner = "Right";
                    }
                    else {
                        winner = "Cross";
                    }
                    TextView txt = findViewById(R.id.textView);
                    txt.setText(winner + " is Winner");
                    Toast.makeText(this, winner + " is Winner", Toast.LENGTH_SHORT).show();
                    Isgameactive = false;
                    Button btnPlay = findViewById(R.id.button3);
                    btnPlay.setEnabled(true);
                }
            }
            Draw();
        }
    }
}