package com.rtmillerprojects.fragmentpractice;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    Button btn_frag1;
    Button btn_frag2;
    ProgressBar pb;
    Handler handler;
    MyTask task;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final FragmentManager fm = getSupportFragmentManager();




        btn_frag1 = (Button) findViewById(R.id.button);
        btn_frag2 = (Button) findViewById(R.id.button2);
        pb = (ProgressBar) findViewById(R.id.progress_bar);
        btn_frag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction()
                        .setCustomAnimations(R.anim.slide_out_left,
                                R.anim.slide_in_left, 0, 0)
                        .replace(R.id.frame_container, new Fragment1(),Fragment1.class.getName()/*TAG OF FRAGMENT WITH A NAME FOR FUTURE LOOKUP*/)
                        .commit();

            }
        });
        btn_frag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left,
                                R.anim.slide_out_right, 0, 0)
                        .replace(R.id.frame_container, new Fragment2(),Fragment2.class.getName()/*TAG OF FRAGMENT WITH A NAME FOR FUTURE LOOKUP*/)
                        .commit();

            }
        });

        pd = new ProgressDialog(this);
        pd.show();
        pd.setMessage("Waiting for result of asyncTask");
        task = new MyTask();
        task.execute();


    }

    class MyTask extends AsyncTask<Void, String, Void>{


        @Override
        protected void onProgressUpdate(String[] values) {
            pd.setMessage(values[0]);
            if(values[0].equals("99")){
                pd.cancel();
            }
        }


        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(100);
                    publishProgress(""+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
