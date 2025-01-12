package com.example.numberguess;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    private TextView tx1,tx2,tx3;
    private EditText ed1;
    private Button btn1;
    boolean twoDigits,threeDigits,fourDigits;
    String guess;

    Random r=new Random();
    int random=0;
    int remain=10;
    ArrayList<Integer> guessList=new ArrayList<>();
    int userAttempts=0;

    private Context mContext=GameActivity.this;
    private boolean[] checkItems;

    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tx1=findViewById(R.id.textView2);
        tx2=findViewById(R.id.textView3);
        tx3=findViewById(R.id.textView4);
        ed1=findViewById(R.id.editTextNumber);
        btn1=findViewById(R.id.buttonconfirm);
        twoDigits=getIntent().getBooleanExtra("two",false);
        threeDigits=getIntent().getBooleanExtra("three",false);
        fourDigits=getIntent().getBooleanExtra("four",false);
        if(twoDigits)
            random=r.nextInt(90)+10;
        if(threeDigits)
            random=r.nextInt(900)+100;
        if(fourDigits)
            random =r.nextInt(9000)+1000;
        Toast.makeText(mContext, ""+random, Toast.LENGTH_SHORT).show();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 guess=ed1.getText().toString();
                if(guess.equals("")){
                    Toast toast=Toast.makeText(GameActivity.this,"请输入你猜的数",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
                    toast.show();
                }
                else {
                    tx1.setVisibility(View.VISIBLE);
                    tx2.setVisibility(View.VISIBLE);
                    tx3.setVisibility(View.VISIBLE);
                    userAttempts++;
                    remain--;
                    int userGuess=Integer.parseInt(guess);
                    int guess2=Integer.parseInt(guess);
                    guessList.add(guess2);
                    if(random==userGuess){
                        alert = null;
                        builder = new AlertDialog.Builder(mContext);
                        alert = builder.setIcon(R.mipmap.alertmap)
                                .setTitle("系统提示：")
                                .setMessage("6666太有实力了牢弟。我的数是"+random+ "\n\n" +
                                        "你猜测的次数是"+userAttempts+ "次。\n\n"+
                                        "你的猜测："+guessList+"\n\n"+
                                        "再来一次嘛？")
                                .setNegativeButton("不了牢弟", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(mContext, "你点击了取消按钮~", Toast.LENGTH_SHORT).show();
                                        moveTaskToBack(true);
                                        android.os.Process.killProcess(android.os.Process.myPid());
                                        System.exit(1);
                                    }
                                })
                                .setPositiveButton("好", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(mContext, "你点击了确定按钮~", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(GameActivity.this,MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }).create();             //创建AlertDialog对象
                        alert.show();                    //显示对话框


                    }
                    if(random<userGuess){
                        tx1.setText("减小你的猜测");
                        tx2.setText("你上次的猜测： "+guess);
                        tx3.setText("你的剩余次数："+remain);
                    }
                    if(random>userGuess){
                        tx1.setText("增大你的猜测");
                        tx2.setText("你上次的猜测： "+guess);
                        tx3.setText("你的剩余次数："+remain);
                    }
                    if(remain==0){
                        alert = null;
                        builder = new AlertDialog.Builder(mContext);
                        alert = builder.setIcon(R.mipmap.alertmap)
                                .setTitle("系统提示：")
                                .setMessage(" 不是说白了牢弟你有啥实力啊，直直直直直接给我坐下\n\n"+
                                        "你猜测的次数是"+userAttempts+ "次。\n\n"+
                                        "你的猜测："+guessList+"\n\n"+
                                        "再来一次嘛？")
                                .setNegativeButton("不了牢弟", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(mContext, "你点击了取消按钮~", Toast.LENGTH_SHORT).show();
                                        moveTaskToBack(true);
                                        android.os.Process.killProcess(android.os.Process.myPid());
                                        System.exit(1);
                                    }
                                })
                                .setPositiveButton("好", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(mContext, "你点击了确定按钮~", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(GameActivity.this,MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }).create();             //创建AlertDialog对象
                        alert.show();                    //显示对话框

                    }
                ed1.setText("");

                }
            }
        });
    }
    private void bindView() {

    }

}