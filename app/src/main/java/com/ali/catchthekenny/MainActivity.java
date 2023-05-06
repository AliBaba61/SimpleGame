package com.ali.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView textView2;
    ImageView imageView;
    int minX;
    int maxX;
    int minY;
    int maxY;
    int randomIntX;
    int randomIntY;
    Runnable runnable;
    Handler handler;
    int clickTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
        imageView=findViewById(R.id.imageView2);

        Toast.makeText(this, "The Game is Started", Toast.LENGTH_SHORT).show();

        startGame();

    }

    public void startGame(){
        new CountDownTimer(10000,1000){


            @Override
            public void onTick(long l) {

                textView.setText("Time: "+l/1000);

            }

            @Override
            public void onFinish() {

                handler.removeCallbacks(runnable);
                clickTime=0;

                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart?");
                alert.setMessage("Are you sure to restart game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startGame();

                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {




                    }
                });

                alert.show();

            }
        }.start();



        handler = new Handler();
        runnable= new Runnable() {
            @Override
            public void run() {

                minX=-10;
                maxX=500;
                minY=0;
                maxY=500;

                randomIntX=(int)Math.floor(Math.random()*(maxX-minX+1)+minX);
                randomIntY=(int)Math.floor(Math.random()*(maxY-minY+1)+minY);

                imageView.setX(randomIntX);
                imageView.setY(randomIntY);

                handler.postDelayed(runnable,300);




            }
        };

        handler.post(runnable);

    }


    public void image(View view){

        clickTime=clickTime+1;
        textView2.setText("Score: "+clickTime);





    }

}