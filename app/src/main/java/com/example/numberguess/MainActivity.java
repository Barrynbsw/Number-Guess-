package com.example.numberguess;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button startBtn;
    private RadioButton radio1,radio2,radio3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    startBtn=findViewById(R.id.btnstart);
    radio1=findViewById(R.id.radioButton);
    radio2=findViewById(R.id.radioButton2);
    radio3=findViewById(R.id.radioButton3);
    startBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(MainActivity.this,GameActivity.class);
            if(!radio1.isChecked()&&!radio2.isChecked()&&!radio3.isChecked()){
                Toast toast=Toast.makeText(MainActivity.this,"请选择一个位数",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
                toast.show();
            }
            else {
                if(radio1.isChecked())
                    intent.putExtra("two",true);
                if(radio2.isChecked())
                    intent.putExtra("three",true);
                if(radio3.isChecked())
                    intent.putExtra("four",true);
                startActivity(intent);

            }

        }
    });
    }
}