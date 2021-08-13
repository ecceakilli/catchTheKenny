package com.eceakilli.carththekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView scoreText;
    TextView timeText;
    int score;
    ImageView imageView, imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         timeText=(TextView) findViewById(R.id.timeText);
         scoreText=(TextView) findViewById(R.id.scoreText);
         imageView=findViewById(R.id.imageView);
        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);

        imageArray=new ImageView[]{imageView,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8};

            hideImage();

            score=0;

         //geri sayım
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time:"+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                timeText.setText("Time OFF");
                //runnableyi durdurdum.
                handler.removeCallbacks(runnable);
                //zaman bitnice kenny görüntülenmesi durduruldu
                for (ImageView image:imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                //oyub bittiğinde uyarı mesajı ver,
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restrat?");
                alert.setMessage("Are you sure to restrat game?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //yeniden baslatacagız!!! Güncel activity bitirilip (finish) Aynı aktivity yeniden başlatılıyor.
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"GAME OVER!",Toast.LENGTH_LONG).show();
                    }
                });
                    alert.show();
            }
        }.start();
    }

    public void scoreArttir(View view){
        score++;
        scoreText.setText("Score:"+score);
    }
    public void hideImage(){
        handler=new Handler();

        runnable=new Runnable() {
            @Override
            public void run() {
                for (ImageView image:imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                //0-8 arası rasgele sayı getirir
                Random random=new Random();
                int i=random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                //postDelayed devamlı çalıştır fakat benim söylediğim periyodda çalıstır.
                handler.postDelayed(this,350);
            }
        };
        handler.post(runnable);


}
}