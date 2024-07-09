package io.dearone.arutana.sample.interstitial;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.dearone.arutana.interstitial.ArutanaInterstitial;
import io.dearone.arutana.interstitial.ArutanaInterstitialListener;
import io.dearone.arutana.sample.interstitial.databinding.ActivityMainBinding;

//import io.dearone.arutana.sample.interstitial.

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

        // 管理画面から払い出された広告枠ID
        this.arutanaInterstitial.setLocationId("48549");

        // テストモードを有効化
        this.arutanaInterstitial.setEnableTestMode(true);

        this.binding.btnPreload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 広告リクエスト
                arutanaInterstitial.preload();
            }
        });

        this.binding.btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 広告表示
                arutanaInterstitial.show();

                // showボタンを非活性にしておく
                binding.btnShow.setEnabled(false);
            }
        });
    }

    @Override
    protected void onStop() {
        // 広告非表示
        this.arutanaInterstitial.dismiss();

        super.onStop();
    }

    //ArutanaInterstitialListener

    @Override
    public void onCloseInterstitial() {
        Log.d(MainActivity.LOGTAG, "Did close interstitial ads.");
    }

    @Override
    public void onReceiveAd() {
        Log.d(MainActivity.LOGTAG, "Received an ad.");
        this.binding.btnShow.setEnabled(true);
    }

    @Override
    public void onFailedToReceiveAd(ArutanaErrorCode arutanaErrorCode) {
        Log.d(MainActivity.LOGTAG, "Failed to receive an ad.");
        // ネットワーク不通/エラー多発/広告レスポンスなし 以外はリトライしてください
        switch (arutanaErrorCode) {
            case EXCEED_LIMIT:      // エラー多発
            case NEED_CONNECTION:   // ネットワーク不通
            case NO_AD:             // 広告レスポンスなし
                break;
            default:
                if (this.arutanaInterstitial != null) {
                    this.arutanaInterstitial.preload();
                }
                break;
        }
    }

    @Override
    public void onClickAd() {
        Log.d(MainActivity.LOGTAG, "Did click ad.");
    }
}
