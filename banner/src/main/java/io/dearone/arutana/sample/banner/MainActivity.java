package io.dearone.arutana.sample.banner;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.dearone.arutana.Arutana;
import io.dearone.arutana.ArutanaListener;
import io.dearone.arutana.sample.banner.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements ArutanaListener {
    private static final String LOGTAG = "MainActivity";

    private ActivityMainBinding binding;
    private Arutana arutana = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = this.binding.getRoot();
        this.setContentView(view);

        this.arutana = new Arutana(this);
        this.arutana.setLocationId("00000");
        this.arutana.setAdFrameSize(Arutana.AdFrameSize.SP);
        this.arutana.setBackgroundColor(Color.BLUE);
        this.arutana.setEnableTestMode(true);
        this.arutana.setAdListener(this);

//        ViewGroup.LayoutParams initParams = this.binding.adContainer.getLayoutParams();
//        initParams.width
//        this.binding.adContainer.setLayoutParams(initParams);
        this.binding.adContainer.addView(this.arutana);
    }

    @Override
    public void onReceiveAd() {
        Log.d(MainActivity.LOGTAG, "Received an ad.");
    }

    @Override
    public void onFailedToReceiveAd(ArutanaErrorCode arutanaErrorCode) {
        Log.d(MainActivity.LOGTAG, "Failed to receive an ad:$code");
        switch (arutanaErrorCode) {
            case EXCEED_LIMIT:      // エラー多発
            case NEED_CONNECTION:   // ネットワーク不通
            case NO_AD:             // 広告レスポンスなし
                break;
            default:
                break;
        }
    }

    @Override
    public void onClickAd() {
        Log.d(MainActivity.LOGTAG, "Did click ad.");
    }
}
