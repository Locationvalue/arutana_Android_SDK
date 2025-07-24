# Arutana 広告SDK バナー広告導入手順

このマニュアルでは、Arutana 広告SDKを使用した広告バナーの実装手順を説明します。以下の手順に従って、広告バナーをアプリケーションに統合してください。
開発環境に Android Studio を使用することを前提とします。

## 必要条件

- [SDKインストール手順](./sdk_install.md)を参考に、Arutana SDK がプロジェクトにインストールされていること。
- Android Studio 開発環境が設定されていること。

## 実装手順

### 1. `MainActivity.java` の設定

`MainActivity.java` ファイルに広告バナーのコードを追加します。以下のコードは Arutana 広告SDK を使ってバナー広告を表示するための実装例です。

### コード例

```java
package io.dearone.arutana.sample.banner;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.dearone.arutana.Arutana;
import io.dearone.arutana.ArutanaErrorCode;
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
        this.arutana.setLocationId("1"); // 広告枠ID
        this.arutana.setUserId("xxxx"); // ユーザーがログイン中の場合、会員ID
        this.arutana.setAdFrameSize(Arutana.AdFrameSize.SP);
        this.arutana.setEnableTestMode(true);
        this.arutana.setAdListener(this);
        this.binding.adContainer.addView(this.arutana);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // ActivityがResumeした場合、広告を再取得して表示する
        this.arutana.start();
    }

    @Override
    protected void onPause() {
        // ActivityがPauseした場合、広告を破棄する
        this.arutana.stop();

        super.onPause();
    }

    @Override
    public void onReceiveAd() {
        Log.d(MainActivity.LOGTAG, "Received an ad.");
    }

    @Override
    public void onFailedToReceiveAd(ArutanaErrorCode arutanaErrorCode) {
        Log.d(MainActivity.LOGTAG, "Failed to receive an ad:" + arutanaErrorCode);
    }

    @Override
    public void onClickAd() {
        Log.d(MainActivity.LOGTAG, "Did click ad.");
    }
}
```

### 2. レイアウトファイルの設定

次に、XMLレイアウトファイルに広告を表示するコンテナとなる`ViewGroup`を追加します。このコンテナに広告バナーが表示されます。

```xml
<LinearLayout
    android:id="@+id/adContainer"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:gravity="center"
    android:background="@android:color/white" />
```

### 3. 広告表示の動作確認

広告の動作を確認するために、以下の条件でアプリを実行してください。

1. **テストモードを有効化**  
   テストモードが有効化されていることを確認します。  
   ```java
   this.arutana.setEnableTestMode(true);
   ```
   **注意**: 本番環境でリリースする際は、必ずテストモードの設定を削除してください。

2. **ユーザーIDを設定**  
   ログイン中のユーザーIDを `setUserId` メソッドで設定します。
   ```java
   this.arutana.setUserId("xxxx");
   ```

### 4. 広告イベントのハンドリング

Arutana SDK のリスナーインターフェースを使用して、広告の受信やタップに対する処理を実装します。以下のメソッドを適宜実装してください。

- **`onClickAd`**  
  ユーザーが広告をタップした際に呼び出されます。
  
- **`onReceiveAd`**  
  広告が正常に受信された際に呼び出されます。
  
- **`onFailedToReceiveAd`**  
  広告の受信に失敗した際に呼び出されます。

### 5. テストモードの解除（本番リリース時）

アプリを本番リリースする前に、テストモードを無効にする必要があります。以下の行を削除またはコメントアウトしてください。

```java
this.arutana.setEnableTestMode(true);
```

これで、本番環境で広告が正しく表示されます。

## まとめ

以上で、Arutana 広告SDK を使用した広告バナーの実装は完了です。このマニュアルに従って、正しく広告を表示できることを確認してください。
