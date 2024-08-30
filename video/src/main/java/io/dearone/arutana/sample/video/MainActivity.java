package io.dearone.arutana.sample.video;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.dearone.arutana.video.ArutanaMovieAd;
import io.dearone.arutana.sample.video.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArutanaMovieAd movieAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = this.binding.getRoot();
        this.setContentView(view);

        this.movieAd = new ArutanaMovieAd(this);
        this.movieAd.setLocationId("6");

        this.movieAd.setUserId("1");
        this.movieAd.setPositionY(-100);

        // テストモードを有効化
        this.movieAd.setEnableTestMode(true);

        this.binding.btnStopstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieAd.show();
            }
        });
    }
}
