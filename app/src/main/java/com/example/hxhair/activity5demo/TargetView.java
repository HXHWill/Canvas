package com.example.hxhair.activity5demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.sip.SipAudioCall;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by HXHAIR on 2017/3/20.
 */

public class TargetView extends View implements View.OnTouchListener, Runnable {


    float tt=100.0f;
    float yt=0.0f;
    float xt=0.0f;
    float canvasY;
    int scoreNum=0;
    Handler timer;
    Bitmap myImage;
    float xLimit;
    boolean movingRight=true;


    public TargetView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setOnTouchListener(this);
        timer=new Handler();
        timer.postDelayed((Runnable) this,10);


        //text.setText(""+scoreNum);
        //myImage= BitmapFactory.decodeResource(getResources(),R.drawable.blob);

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p=new Paint();
        p.setColor(Color.BLUE);
        //p.setStrokeWidth(10.0f);
        //canvas.drawLine(canvas.getWidth()/2.0f,0.0f,100.0f,100.0f,p);
        //canvas.drawLine(xt,yt,100.0f,100.0f,p);
        //canvas.drawLine(canvas.getWidth()/2.0f,0.0f,xt,yt,p);
        //canvas.drawBitmap(myImage,xt,yt,p);

        //canvas.drawCircle(canvas.getWidth()/2.0f,canvas.getHeight()/2.0f,50,p);
        canvas.drawCircle(tt,canvas.getHeight()/2.0f,100,p);
        xLimit=canvas.getWidth();
        p.setColor(Color.BLACK);
        canvas.drawCircle(xt,yt,50,p);
        canvasY=canvas.getHeight()/2.0f;
        p.setTextSize(100);
        canvas.drawText(""+scoreNum,100,100,p);
        //canvas.drawCircle((float)canvas.getWidth()/2,(float)canvas.getHeight()/2,50,p);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        xt=event.getX();
        yt=event.getY();
        float dx=(xt-tt);
        float dy=yt-canvasY;
        if(Math.sqrt(dx*dx+dy*dy)<150)
            scoreNum+=1;

        this.invalidate();//redraw

        return false;
    }

    @Override
    public void run() {
        if(movingRight==true)
            tt+=5.0f;
        else
            tt-=5.0f;
        if(tt>=xLimit-50||tt<=50)
            movingRight=!movingRight;

        this.invalidate();
        timer.postDelayed(this,10);
    }
}

