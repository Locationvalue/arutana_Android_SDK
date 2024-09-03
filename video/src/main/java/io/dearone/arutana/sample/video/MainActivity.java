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
        this.movieAd.setLocationId("6");

        this.movieAd.setAdListener(this);

        this.movieAd.setUserId("1");
        this.movieAd.setTopMargin(10);

        // テストモードを有効化
        this.movieAd.setEnableTestMode(true);

        this.binding.btnStopstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieAd.show();
            }
        });

        this.movieAd.preload();
    }

    @Override
    public void arutanaMovieNonad() {
        Log.d(MainActivity.LOGTAG, "No ad.");
    }

    @Override
    public void arutanaMovieReceiveAd() {
        Log.d(MainActivity.LOGTAG, "Received an ad.");
        this.movieAd.show();
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
        Log.d(MainActivity.LOGTAG, "Movie start.");
    }

    @Override
    public void arutanaMovieEndFull() {
        Log.d(MainActivity.LOGTAG, "Movie end.");
    }

    @Override
    public void arutanaMovieFailedToReceiveAd(ArutanaErrorCode arutanaErrorCode) {
        Log.d(MainActivity.LOGTAG, "Failed to receive an ad.");
        // ネットワーク不通/エラー多発/広告レスポンスなし 以外はリトライしてください
        switch (arutanaErrorCode) {
            case EXCEED_LIMIT:      // エラー多発
            case NEED_CONNECTION:   // ネットワーク不通
            case NO_AD:             // 広告レスポンスなし
                break;
            default:
                break;
        }
    }
}
