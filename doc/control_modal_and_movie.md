# モーダル広告および動画広告の表示順序制御について

Arutana広告を実装する際のルールと手順を以下に示します。本マニュアルでは、モーダル広告と動画広告の2種類の広告を適切に表示する方法について説明します。実装ルールに従い、ユーザー体験を損なうことなく、効果的に広告を表示することを目的としています。

## 広告フォーマットの概要

Arutana広告は、以下の2種類のフォーマットを提供します：

1. **モーダル広告**  
   画面中央に表示され、簡単に閉じることができる広告。
2. **動画広告**  
   フルスクリーン形式で表示される動画型の広告。

## 実装ルール

モーダル広告は、動画広告よりも先に表示されます。また、モーダル広告の読み込みが失敗した場合や閉じられた場合に限り、動画広告を表示してください。

1. **モーダル広告の優先表示**  
   - モーダル広告が読み込まれていれば、必ず最初に表示します。

2. **動画広告の表示条件**  
   - モーダル広告の読み込みに失敗した場合。
   - モーダル広告が閉じられた場合。

## 実装例

```java
// MainActivity.java (広告表示の管理)
package io.dearone.arutana.sample;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.dearone.arutana.ArutanaErrorCode;
import io.dearone.arutana.interstitial.ArutanaInterstitial;
import io.dearone.arutana.interstitial.ArutanaInterstitialListener;
import io.dearone.arutana.video.ArutanaMovieAd;
import io.dearone.arutana.video.ArutanaMovieListener;

public class MainActivity extends AppCompatActivity implements ArutanaInterstitialListener, ArutanaMovieListener {
    private static final String LOGTAG = "MainActivity";

    private ArutanaInterstitial interstitialAd; // モーダル広告
    private ArutanaMovieAd movieAd;            // 動画広告

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // モーダル広告の初期化
        interstitialAd = new ArutanaInterstitial(this);
        interstitialAd.setLocationId("4"); // 管理画面で取得したID
        interstitialAd.setUserId("1");
        interstitialAd.setAdListener(this);
        interstitialAd.setEnableTestMode(true);
        interstitialAd.preload(); // プリロード開始

        // 動画広告の初期化
        movieAd = new ArutanaMovieAd(this);
        movieAd.setLocationId("6"); // 管理画面で取得したID
        movieAd.setAdListener(this);
        movieAd.setUserId("1");
        movieAd.setEnableTestMode(true);
    }

    // モーダル広告のイベント処理
    @Override
    public void arutanaInterstitialReceiveAd(ArutanaInterstitial arutanaInterstitial) {
        Log.d(LOGTAG, "モーダル広告を受信しました");
        interstitialAd.show();
    }

    @Override
    public void arutanaInterstitialClose(ArutanaInterstitial arutanaInterstitial) {
        Log.d(LOGTAG, "モーダル広告が閉じられました");
        movieAd.preload(); // 動画広告の準備
    }

    @Override
    public void arutanaInterstitialFailedToReceiveAd(ArutanaInterstitial arutanaInterstitial, ArutanaErrorCode errorCode) {
        Log.d(LOGTAG, "モーダル広告の取得に失敗しました: " + errorCode);
        movieAd.preload(); // 動画広告の準備
    }

    // 動画広告のイベント処理
    @Override
    public void arutanaMovieReceiveAd() {
        Log.d(LOGTAG, "動画広告を受信しました");
        movieAd.show();
    }

    @Override
    public void arutanaMovieClose() {
        Log.d(LOGTAG, "動画広告が閉じられました");
    }

    @Override
    public void arutanaMovieFailedToReceiveAd(ArutanaErrorCode errorCode) {
        Log.d(LOGTAG, "動画広告の取得に失敗しました: " + errorCode);
    }
}
```

## 実装のポイント

1. **広告の初期化とプリロード**
   - アプリ起動時に `interstitialAd.preload()` と `movieAd.preload()` を呼び出し、広告の準備を行います。
   - モーダル広告と動画広告それぞれの `setLocationId` や `setUserId` を正しく設定してください。

2. **モーダル広告のイベント管理**
   - モーダル広告の取得成功時 (`aratunaInterstitialReceiveAd`) に広告を表示します。
   - モーダル広告が閉じられた際 (`aratunaInterstitialClose`) には、動画広告をプリロードします。
   - モーダル広告が取得できなかった場合 (`aratunaInterstitialFailedToReceiveAd`)、すぐに動画広告をプリロードしてください。

3. **動画広告の表示制御**
   - 動画広告は `aratunaMovieReceiveAd` で受信した後に表示を開始します。
   - 動画広告が閉じられた (`aratunaMovieClose`) 後の処理やログ出力を行い、広告サイクルを管理します。

4. **エラーハンドリング**
   - 取得失敗時 (`aratunaInterstitialFailedToReceiveAd` または `aratunaMovieFailedToReceiveAd`) のエラーコードをチェックし、ネットワークや他の障害状況をログに記録します。
   - 必要に応じてリトライを実装してください。

5. **ユーザー体験の最適化**
   - モーダル広告を優先的に表示することで、ユーザーへの負担を軽減します。
   - 動画広告はモーダル広告の次に表示されるように制御し、広告効果を最大化します。

6. **フローティング広告との順番待ち処理(ModuleAppsご利用のお客様向け)**
   - ModuleApps 1.0の場合はフローティング広告 → ARUTANAモーダルの表示順になるように処理追加が必要です。

7. **特定条件下でのポップアップ待ち合わせ処理(ModuleAppsご利用のお客様向け)**
   - ModuleApps 1.0、ModuleApps 2.0、ARUTANA SDKを導入されている場合、それぞれ待ち合わせ処理が必要になります。
   - ARUTANA SDKの実装により特定のお客様のアプリにはホーム画面に表示するポップアップ型のコンテンツが合計3つ存在することになります。
     - ①フローティング広告（MA1.0）
     - ②アプリ内メッセージモーダル（MA2.0）
     - ③ARUTANA広告モーダル、動画
   - 上記3点が①フローティング→②アプリ内メッセージ→③ARUTANAモーダルの順に、かつ、1つ表示→ホームを離脱→ホームを再表示→次を表示となるように待ち合わせ処理を入れる必要がありますのでご注意ください。
