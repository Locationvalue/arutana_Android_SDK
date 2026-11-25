package io.dearone.arutana.sample.interstitial;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

import io.dearone.arutana.ArutanaErrorCode;
import io.dearone.arutana.interstitial.ArutanaInterstitial;
import io.dearone.arutana.interstitial.ArutanaInterstitialListener;
import io.dearone.arutana.sample.interstitial.databinding.ActivityMainBinding;
import io.dearone.arutana.utils.LogUtils;
import io.dearone.arutana.utils.AdIdUtil;

public class MainActivity extends AppCompatActivity implements ArutanaInterstitialListener {
    private static final String LOGTAG = "MainActivity";

    private ActivityMainBinding binding;
    private ArutanaInterstitial arutanaInterstitial = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = this.binding.getRoot();
        view.setBackgroundColor(Color.WHITE);
        this.setContentView(view);

        this.arutanaInterstitial = new ArutanaInterstitial(this);

        this.arutanaInterstitial.setLocationId("4"); // 管理画面から払い出された広告枠ID
        this.arutanaInterstitial.setUserId("xxxx"); // ユーザーがログイン中の場合、会員ID
        this.arutanaInterstitial.setEnableTestMode(true); // テストモードを有効化
        this.arutanaInterstitial.setAdListener(this);
        // 広告表示準備を開始(下記1か2の処理どちらかを選択してください)
        // 1. Advertising IDを使った配信をしない場合
        this.arutanaInterstitial.preload();
        // 2. Advertising IDを使った配信をする場合(サンプルコード上はコメントアウトしています)
        //AdIdUtil.requestAdIdAsync(getApplicationContext(), new AdIdUtil.AdIdCallback() {
        //    @Override
        //    public void onAdIdReady(String adId) {
        //        // 取得した広告IDをセット
        //        arutanaInterstitial.setAdId(adId);
        //        arutanaInterstitial.preload();
        //    }
        //});

        // 管理画面から払い出された広告枠ID
        this.arutanaInterstitial.setLocationId("4");
        this.arutanaInterstitial.setUserId("3287417794815");
        this.arutanaInterstitial.setAdListener(this);
        this.arutanaInterstitial.setTopMargin(120);
//        this.arutanaInterstitial.setWidth(50);
//        this.arutanaInterstitial.setAdTextColor(Color.YELLOW);
//        this.arutanaInterstitial.setAdBackgroundColor(Color.GREEN);

        // テストモードを有効化
        this.arutanaInterstitial.setEnableTestMode(true);
        this.requestWithAdTracking();

        this.binding.btnPreload.setOnClickListener(v -> {
            // 広告リロード
            this.requestWithAdTracking();
        });
    }

    @Override
    protected void onResume() {
        this.arutanaInterstitial.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        // 広告非表示
        this.arutanaInterstitial.onPause();
        super.onPause();
    }

    protected void requestWithAdTracking() {
        AdIdUtil.requestAdIdAsync(getApplicationContext(), new AdIdUtil.AdIdCallback() {
            @Override
            public void onAdIdReady(String adId, boolean isLimitAdTracking) {
                Log.d(MainActivity.LOGTAG, adId);
                arutanaInterstitial.setAdId(adId);
                arutanaInterstitial.preload();
            }
        });
    }

    // ArutanaInterstitialListener
    @Override
    public void arutanaInterstitialReceiveAd(ArutanaInterstitial arutanaInterstitial) {
        Log.d(MainActivity.LOGTAG, "Received an ad.");
        this.arutanaInterstitial.showAsync();
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
        Log.d(MainActivity.LOGTAG, "Failed to receive an ad. " + arutanaErrorCode);
    }
}
