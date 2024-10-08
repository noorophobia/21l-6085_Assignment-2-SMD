package com.example.a2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {

   //hooks
    ImageView ivLogo;
Animation translate,scale;
TextView tvLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();



        // Start the animation on the view
        ivLogo.startAnimation(translate);
        scale.setStartOffset(4000);
      tvLogo.startAnimation(scale);
         new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               Intent i = new Intent(SplashScreen.this,MainActivity.class);
               startActivity(i);
               finish();
            }
        }, 6000);
    }

    private void init(){
        //initializing hooks
        ivLogo=findViewById(R.id.ivLogo);
        tvLogo=findViewById(R.id.tvLogo);

        // Load the animation
        translate = AnimationUtils.loadAnimation(this, R.anim.translate);
        scale = AnimationUtils.loadAnimation(this, R.anim.scale);

    }
}

//Intent intent = new Intent(this, SecondActivity.class);
//startActivityForResult(intent, REQUEST_CODE);
/*@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
        String result = data.getStringExtra("result_key");
        // Handle the result here
    }
}
*/