# Advertising IDを利用した配信実施時の実装方法について

こちらはARUTANA配信でAdvertising IDを利用した配信を実施する場合に必須となる工程です。  
広告の読み込みのために実行していたコードを、**Advertising ID取得方法**に沿って変更してください。  
また、項目1〜3の設定についても確認をお願いいたします。

下記のサンプルコードはモーダル広告の際の処理を記載していますが、同様に**バナー広告や動画広告**でも**Advertising IDを取得するための処理**を追加してください。

---

## Advertising IDの取得方法

`AdIdUtil` クラスを用いて、従来、広告の読み込みのために実行していたコードを、以下の通り変更ください。  
当項目は、ARUTANA配信でMAIDを利用した配信を実施する場合に必要になる工程となります。  
下記のサンプルコードはモーダル広告の際の処理を記載しておりますが、同様にバナー、動画広告でもIDFAを取得するための処理を追加してください。

```java
// 従来
arutanaInterstitial.preload();

// Advertising IDを利用する場合
AdIdUtil.requestAdIdAsync(getApplicationContext(), new AdIdUtil.AdIdCallback() {
    @Override
    public void onAdIdReady(String adId) {
        // 取得した広告IDをセット
        arutanaInterstitial.setAdId(adId);
        // 広告を呼び出し
        arutanaInterstitial.preload();
    }
});
```

---

以下は、`AdIdUtil` をプロジェクトに組み込む際に必要となる**パーミッション**と**依存関係**についての簡潔な説明です。

### 1. 必要な依存関係

- Google Play ServicesのAdvertising ID API（`play-services-ads-identifier`）  
- `com.google.android.gms:play-services-ads-identifier:18.2.0` などのバージョンをGradleに追加します。

```groovy
dependencies {
    implementation 'com.google.android.gms:play-services-ads-identifier:18.2.0'
}
```

- 軽量版であり、`play-services-ads` 本体を含まないため、アプリサイズへの影響を抑えつつ広告IDの取得のみを行えます。  
- （任意）Google Play Services ベースモジュール（`play-services-basement`）  
  すでに含んでいるプロジェクトでは不要ですが、エラーが出る場合は以下を追加してください：

```groovy
implementation 'com.google.android.gms:play-services-basement:18.2.0'
```

---

### 2. 必要なパーミッション

#### Android 13（API レベル 33）以降向け：`com.google.android.gms.permission.AD_ID`

Android 13 でアプリがGoogle Play Services 20.3.0 以下を使用している場合、マニフェストに以下を追加しないと、広告IDが自動的にゼロ値にリセットされます：

```xml
<uses-permission android:name="com.google.android.gms.permission.AD_ID"/>
```

- Google Mobile Ads SDK 20.4.0 以降では、自動的にマニフェストにマージされるため**明示的に追加は不要です**。

#### Android 12（API レベル 31）以下／Android 13+ かつ `play-services-ads-identifier:17.1.0` 以上

- 権限設定は不要です
- AdvertisingIdClientを呼び出すだけで広告IDを返しますが、ユーザーが「広告トラッキングを制限」している場合はSDKが自動的にゼロIDを返すため、アプリ側では特に追加パーミッションを要求する必要がありません

---

### 3. まとめ

- **Gradle依存関係**  
```groovy
implementation 'com.google.android.gms:play-services-ads-identifier:18.2.0' 
// 必要に応じて
implementation 'com.google.android.gms:play-services-basement:18.2.0'
```

- **AndroidManifest.xml に追加（Android 13+ 向け）**  
```xml
<uses-permission android:name="com.google.android.gms.permission.AD_ID"/>
```

以上の設定を行うことで、`AdIdUtil` は**依存・権限の有無にかかわらず安全に動作**し、必要時にゼロUUIDを返すようになります。
