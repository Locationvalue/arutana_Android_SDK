package io.dearone.arutana.sample.interstitial;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.dearone.arutana.ArutanaErrorCode;
import io.dearone.arutana.interstitial.ArutanaInterstitial;
import io.dearone.arutana.interstitial.ArutanaInterstitialListener;
import io.dearone.arutana.sample.interstitial.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements ArutanaInterstitialListener {
    private static final String LOGTAG = "MainActivity";

    private ActivityMainBinding binding;
    private ArutanaInterstitial arutanaInterstitial = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = this.binding.getRoot();
        this.setContentView(view);

        this.arutanaInterstitial = new ArutanaInterstitial(this);
        this.arutanaInterstitial.setLocationId("4"); // 管理画面から払い出された広告枠ID
        this.arutanaInterstitial.setUserId("xxxx"); // ユーザーがログイン中の場合、会員ID
        this.arutanaInterstitial.setEnableTestMode(true); // テストモードを有効化
        this.arutanaInterstitial.setAdListener(this);
        this.arutanaInterstitial.preload(); // 広告表示準備を開始

        this.binding.btnPreload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 広告リクエスト
                arutanaInterstitial.preload();
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();

        // ActivityがPauseされた場合、広告を初期化
        if (this.arutanaInterstitial != null) {
            this.arutanaInterstitial.dismiss();
        }
    }

    // ArutanaInterstitialListener
    @Override
    public void arutanaInterstitialReceiveAd(ArutanaInterstitial arutanaInterstitial) {
        Log.d(MainActivity.LOGTAG, "Received an ad.");
        // 広告の表示準備に成功した場合、広告を表示する
        this.arutanaInterstitial.show();
    }

    @Override
    public void arutanaInterstitialShowAd(ArutanaInterstitial arutanaInterstitial) {
        Log.d(MainActivity.LOGTAG, "Show ad.");
    }

    @Override
    public void arutanaInterstitialDidTapAd(ArutanaInterstitial arutanaInterstitial) {
        Log.d(MainActivity.LOGTAG, "Did click ad.");
    }

    @Override
    public void arutanaInterstitialClose(ArutanaInterstitial arutanaInterstitial) {
        Log.d(MainActivity.LOGTAG, "Did close interstitial ads.");
    }

    @Override
    public void arutanaInterstitialFailedToReceiveAd(ArutanaInterstitial arutanaInterstitial, ArutanaErrorCode arutanaErrorCode) {
        Log.d(MainActivity.LOGTAG, "Failed to receive an ad.");
    }
}
