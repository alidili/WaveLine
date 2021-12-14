package com.waveline.exm;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yl.waveline.WaveLineView;

public class MainActivity extends Activity {

    private WaveLineView waveLineView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        waveLineView = (WaveLineView) findViewById(R.id.waveLineView);
        waveLineView.startAnim();

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            new RxPermissions(MainActivity.this)
                    .requestEach(RECORD_AUDIO, WRITE_EXTERNAL_STORAGE)
                    .subscribe(permission -> {

                    });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        waveLineView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        waveLineView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        waveLineView.release();
    }
}
