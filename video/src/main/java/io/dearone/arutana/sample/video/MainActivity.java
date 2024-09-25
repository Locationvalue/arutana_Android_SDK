package io.dearone.arutana.sample.video;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.dearone.arutana.ArutanaErrorCode;
import io.dearone.arutana.video.ArutanaMovieAd;
import io.dearone.arutana.sample.video.databinding.ActivityMainBinding;
import io.dearone.arutana.video.ArutanaMovieListener;

public class MainActivity extends AppCompatActivity implements ArutanaMovieListener {
    private ActivityMainBinding binding;
    private ArutanaMovieAd movieAd;

    private static final String LOGTAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = this.binding.getRoot();
        this.setContentView(view);

        this.movieAd = new ArutanaMovieAd(this);
        this.movieAd.setLocationId("6"); // 広告枠ID
        this.movieAd.setUserId("xxxx"); // ユーザーがログインしている場合、会員ID
        this.movieAd.setAdListener(this);
        this.movieAd.setEnableTestMode(true); // テストモードを有効化. 本番リリース時は削除する
        this.movieAd.preload(); // 広告の表示準備を開始

        this.binding.btnStopstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieAd.preload();
            }
        });


    }

    @Override
    public void arutanaMovieReceiveAd() {
        Log.d(MainActivity.LOGTAG, "Received an ad.");
        this.movieAd.show(); // 広告の表示準備が完了したら広告を表示する
    }

    @Override
    public void arutanaMovieShowAd() {
        Log.d(MainActivity.LOGTAG, "Show ad.");
    }

    @Override
    public void arutanaMovieDidTapAd() {
        Log.d(MainActivity.LOGTAG, "Did click ad.");
    }

    @Override
    public void arutanaMovieClose() {
        Log.d(MainActivity.LOGTAG, "Did close interstitial ads.");
    }

    @Override
    public void arutanaMovieStartFull() {
        Log.d(MainActivity.LOGTAG, "Fullscreen start.");
    }

    @Override
    public void arutanaMovieEndFull() {
        Log.d(MainActivity.LOGTAG, "Fullscreen end.");
    }

    @Override
    public void arutanaMovieFailedToReceiveAd(ArutanaErrorCode arutanaErrorCode) {
        Log.d(MainActivity.LOGTAG, "Failed to receive an ad.:" + arutanaErrorCode);
    }
}
