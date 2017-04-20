package com.gwell.view.candel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.gwell.view.library.ArcAngleView;

public class MainActivity extends AppCompatActivity {

    private  ArcAngleView view;
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (ArcAngleView)findViewById(R.id.view);
        text = (EditText)findViewById(R.id.text);
        //view.setAngle(0.7f);//参数为0-1 float类型小数，即可根据百分比转到相应角度,带动画
        //view.setCurrentAngle(0.7f);//参数为0-1 float类型小数，即可根据百分比转到相应角度,不带动画
    }

    public void click(View v) {
        String num =text.getText().toString();
        view.setCurrentAngle(Float.valueOf(num));//参数为0-1 float类型小数，即可根据百分比转到相应角度,不带动画
    }
}
