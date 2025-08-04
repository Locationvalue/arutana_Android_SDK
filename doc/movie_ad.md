# Arutana 広告SDK 動画広告導入手順

このマニュアルでは、Arutana 広告SDKを使用した動画広告の実装手順を説明します。以下の手順に従って、動画広告をアプリケーションに統合してください。
開発環境に Android Studio を使用することを前提とします。

## 必要条件

- [SDKインストール手順](./sdk_install.md)を参考に、Arutana SDK がプロジェクトにインストールされていること。
- Android Studio 開発環境が設定されていること。

## 実装手順

### 1. `MainActivity.java` の設定

`MainActivity.java` ファイルに動画広告のコードを追加します。以下のコードは Arutana 広告SDK を使って動画広告を表示するための実装例です。

### コード例

```java
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

        // 動画広告の初期化
        this.movieAd = new ArutanaMovieAd(this);
        this.movieAd.setLocationId("6"); // 広告枠ID
        this.movieAd.setUserId("xxxx"); // ユーザーがログインしている場合、会員ID
        this.movieAd.setAdListener(this);
        this.movieAd.setEnableTestMode(true); // テストモードを有効化. 本番リリース時は削除する
        this.movieAd.preload(); // 広告の表示準備を開始
    }

    @Override
    public void arutanaMovieReceiveAd() {
        Log.d(MainActivity.LOGTAG, "Received an ad.");
        // 広告の表示準備が完了したら広告を表示する
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
```

### 2. 広告表示の動作確認

広告の動作を確認するために、以下の条件でアプリを実行してください。

1. **テストモードを有効化**  
   テストモードが有効化されていることを確認します。  
   ```java
   this.movieAd.setEnableTestMode(true);
   ```
   **注意**: 本番環境でリリースする際は、必ずテストモードの設定を削除してください。

2. **ユーザーIDを設定**  
   ログイン中のユーザーIDを `setUserId` メソッドで設定します。
   ```java
   this.movieAd.setUserId("xxxx");
   ```

3. **広告の事前読み込みと表示**  
   `preload` メソッドを使用して広告を事前に読み込みます。広告の準備が完了すると、`arutanaMovieReceiveAd` メソッドが呼び出され、その中で **show** メソッドを使って広告を表示します。

### 3. 広告イベントのハンドリング

Arutana SDK のリスナーインターフェースを使用して、広告の受信やタップに対する処理を実装します。以下のメソッドを適宜実装してください。

- **`arutanaMovieReceiveAd`**  
  広告の受信に成功した際に呼び出され、広告が表示されます。
  
- **`arutanaMovieDidTapAd`**  
  ユーザーが広告をタップした際に呼び出されます。
  
- **`arutanaMovieFailedToReceiveAd`**  
  広告の受信に失敗した際に呼び出されます。

- **`arutanaMovieStart`**  
  動画広告が再生された際に呼び出されます。

- **`arutanaMovieFirstQuartile`**  
  動画広告が25%再生された際に呼び出されます。

- **`arutanaMovieMidQuartile`**  
  動画広告が50%再生された際に呼び出されます。

- **`arutanaMovieThirdQuartile`**  
  動画広告が75%再生された際に呼び出されます。

- **`arutanaMovieComplete`**  
  動画広告が再生完了された際に呼び出されます。

- **`arutanaMovieEndFull`**  
  動画広告が全画面終了された際に呼び出されます。

- **`arutanaMovieSoundMuted`**  
  動画広告がミュートされた際に呼び出されます。

- **`arutanaMovieSoundUnmuted`**  
  動画広告がミュート解除された際に呼び出されます。

### 4. テストモードの解除（本番リリース時）

アプリを本番リリースする前に、テストモードを無効にする必要があります。以下の行を削除またはコメントアウトしてください。

```java
this.movieAd.setEnableTestMode(true);
```

これで、本番環境で広告が正しく表示されます。

## まとめ

以上で、Arutana 広告SDK を使用した動画広告の実装は完了です。このマニュアルに従って、正しく広告を表示できることを確認してください。

## 注意事項

### 広告枠IDについて

テスト、本番で広告枠IDが違います。

IDの間違いによる表示間違い事故が発生していますので、IDコピーの際お間違いがないように注意をお願いします。