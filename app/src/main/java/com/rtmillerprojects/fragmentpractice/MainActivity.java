package com.rtmillerprojects.fragmentpractice;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_frag1;
    Button btn_frag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentManager fm = getSupportFragmentManager();


        btn_frag1 = (Button) findViewById(R.id.button);
        btn_frag2 = (Button) findViewById(R.id.button2);
        btn_frag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction()
                        .replace(R.id.frame_container, new Fragment1(),Fragment1.class.getName()/*TAG OF FRAGMENT WITH A NAME FOR FUTURE LOOKUP*/)
                        .commit();
            }
        });
        btn_frag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction()
                        .replace(R.id.frame_container, new Fragment2(),Fragment2.class.getName()/*TAG OF FRAGMENT WITH A NAME FOR FUTURE LOOKUP*/)
                        .commit();
            }
        });

    }
}
