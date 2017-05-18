package com.android.tv.androidtvapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tv.androidtvapp.nsd.ChatConnection;
import com.android.tv.androidtvapp.nsd.NsdHelper;

import static com.android.tv.androidtvapp.nsd.NsdHelper.TAG;

public class MainActivity extends Activity {

    ChatConnection mConnection;
    NsdHelper mNsdHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View rootView = findViewById(android.R.id.content);
        final Button buttontop = (Button) rootView.findViewById(R.id.buttontop);
        final Button buttonbottom = (Button) rootView.findViewById(R.id.buttonbottom);
        final Button buttonleft = (Button) findViewById(R.id.buttonleft);
        final Button buttonright = (Button) findViewById(R.id.buttonright);

        buttonbottom.requestFocus();
        buttontop.setNextFocusDownId(R.id.buttonbottom);
        buttontop.setNextFocusRightId(R.id.buttontop);

        Handler mUpdateHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String chatLine = msg.getData().getString("msg");
                Log.v(TAG, "got msg: " + chatLine);
                Toast toast = Toast.makeText(MainActivity.this, chatLine, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.START, 200, 100);
                toast.show();
                if (chatLine.equals("back")) {
                    buttonleft.requestFocus();
                } else if (chatLine.equals("forward")) {
                    buttonright.requestFocus();
                } else if (chatLine.equals("up")) {
                    buttontop.requestFocus();
                } else if (chatLine.equals("down")) {
                    buttonbottom.requestFocus();
                }
            }
        };

        mConnection = new ChatConnection(mUpdateHandler, this);

        mNsdHelper = new NsdHelper(this);
        mNsdHelper.initializeNsd();

        if(mConnection.getLocalPort() > -1) {
            mNsdHelper.registerService(mConnection.getLocalPort());
        } else {
            Log.d(TAG, "ServerSocket isn't bound.");
        }
    }

    public void onConnected() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.v(TAG, "Mobile device connected");
                ImageView mobile = (ImageView) findViewById(R.id.mobile);
                mobile.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mNsdHelper.tearDown();
        if (mConnection != null)
            mConnection.tearDown();
    }
}
