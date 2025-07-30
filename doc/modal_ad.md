# Arutana 広告SDK モーダル広告導入手順

このマニュアルでは、Arutana 広告SDKを使用したモーダル広告の実装手順を説明します。以下の手順に従って、モーダル広告をアプリケーションに統合してください。
開発環境に Android Studio を使用することを前提とします。

## 必要条件

- [SDKインストール手順](./sdk_install.md)を参考に、Arutana SDK がプロジェクトにインストールされていること。
- Android Studio 開発環境が設定されていること。

## 実装手順

### 1. `MainActivity.java` の設定

`MainActivity.java` ファイルにモーダル広告のコードを追加します。以下のコードは Arutana 広告SDK を使ってモーダル広告を表示するための実装例です。

### コード例

```java
package io.dearone.arutana.sample.interstitial;

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

        // モーダル広告の初期化
        this.arutanaInterstitial = new ArutanaInterstitial(this);
        this.arutanaInterstitial.setLocationId("4"); // 管理画面から払い出された広告枠ID
        this.arutanaInterstitial.setUserId("xxxx"); // ユーザーがログイン中の場合、会員ID
        this.arutanaInterstitial.setEnableTestMode(true); // テストモードを有効化
        this.arutanaInterstitial.setAdListener(this);
        this.arutanaInterstitial.preload(); // 広告表示準備を開始
    }

    @Override
    protected void onPause() {
        super.onPause();

        // ActivityがPauseされた場合、広告に通知
        if (this.arutanaInterstitial != null) {
            this.arutanaInterstitial.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // ActivityがResumeした場合、広告に通知
        if (this.arutanaInterstitial != null) {
            this.arutanaInterstitial.onResume();
        }
    }

    // ArutanaInterstitialListener
    @Override
    public void arutanaInterstitialReceiveAd(ArutanaInterstitial arutanaInterstitial) {
        Log.d(MainActivity.LOGTAG, "Received an ad.");
        // 広告の準備が整ったら、広告を表示する
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
```

### 2. 広告表示の動作確認

広告の動作を確認するために、以下の条件でアプリを実行してください。

1. **テストモードを有効化**  
   テストモードが有効化されていることを確認します。  
   ```java
   this.arutanaInterstitial.setEnableTestMode(true);
   ```
   **注意**: 本番環境でリリースする際は、必ずテストモードの設定を削除してください。

2. **ユーザーIDを設定**  
   ログイン中のユーザーIDを `setUserId` メソッドで設定します。
   ```java
   this.arutanaInterstitial.setUserId("xxxx");
   ```

3. **広告の事前読み込みと表示**  
   `preload` メソッドを使用して広告を事前に読み込みます。広告の準備が完了すると、`arutanaInterstitialReceiveAd` メソッドが呼び出され、その中で **show** メソッドを使って広告を表示します。

**`注意事項`**

モーダル配置デフォルト値を設定しない場合、中央に表示されます。

設定については[reference](./references/ref_ArutanaInterstitialAd.md)をご参照ください。

### 3. 広告イベントのハンドリング

Arutana SDK のリスナーインターフェースを使用して、広告の受信やタップに対する処理を実装します。以下のメソッドを適宜実装してください。

- **`arutanaInterstitialReceiveAd`**  
  広告の受信に成功した際に呼び出され、広告が表示されます。
  
- **`arutanaInterstitialDidTapAd`**  
  ユーザーが広告をタップした際に呼び出されます。
  
- **`arutanaInterstitialFailedToReceiveAd`**  
  広告の受信に失敗した際に呼び出されます。

### 4. テストモードの解除（本番リリース時）

アプリを本番リリースする前に、テストモードを無効にする必要があります。以下の行を削除またはコメントアウトしてください。

```java
this.arutanaInterstitial.setEnableTestMode(true);
```

これで、本番環境で広告が正しく表示されます。

## まとめ

以上で、Arutana 広告SDK を使用したモーダル広告の実装は完了です。このマニュアルに従って、正しく広告を表示できることを確認してください。

## 注意事項

### 広告枠IDについて

テスト、本番で広告枠IDが違います。

IDの間違いによる表示間違い事故が発生していますので、IDコピーの際お間違いがないように注意をお願いします。