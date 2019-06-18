package com.example.huangkai.customimageloader;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
 ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pic = findViewById(R.id.pic);
    }

    public void click(View view) {
      ConstraintLayout.LayoutParams par = (ConstraintLayout.LayoutParams) pic.getLayoutParams();
        Log.i("hked","height = "+par.height +" getHeight = "+pic.getHeight()+" width = "+par.width+" getWidth = "+pic.getWidth());
    }
}
