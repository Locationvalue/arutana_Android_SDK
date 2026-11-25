# Android SDK Fluct メディエーション

# はじめに

Arutana の広告がない広告枠では、Fluct の広告を代わりに表示できます（メディエーション）。
本項では、Fluct の広告を表示するためにアプリに追加するもの（Fluct アダプターと FluctSDK）と、その設定を示します。

開発環境として Android Studio を利用し、インストール後の各種設定は準備されていることを前提にした手順となります。

### 対応バージョン

Android 8.0 以降(API Level 26)
Arutana SDK 0.1.7 以降
Fluct アダプター 0.0.1
FluctSDK 9.17.1 以降（動作を確認した版: 9.17.1）

FluctSDK 9.17.0 以前では、インタースティシャル広告で Fluct の広告を表示するとアプリが強制終了します。必ず 9.17.1 以降を使ってください。

Google Mobile Services のライセンスがない端末（Fire OS 搭載端末など）と、ブラウザアプリが入っていない端末は、FluctSDK が対応していません。

### Fluct の広告を表示する条件

次の条件をすべて満たす場合に、Fluct の広告を表示します。

- 広告枠に、Arutana の担当者が Fluct の設定をしている
- Arutana から配信する広告がない
- アプリに Fluct アダプターと FluctSDK が組み込まれている

次の場合は Fluct の広告を表示せず、これまでどおりの通知をします。

- 通信エラー・オフラインなど、Arutana の広告を取得できなかった場合
- 表示回数の上限（フリークエンシーキャップ）に達している場合

### 対応する広告

| 広告 | Fluct の広告 |
|---|---|
| バナー広告 | 320×100 のバナー |
| インタースティシャル広告 | 300×250 のバナーを、Arutana のインタースティシャル（モーダル）の中に表示 |
| 動画広告 | 対象外 |

### 導入の流れ

1\. Fluct アダプターを追加します
<br>↓<br>
2\. FluctSDK を追加します
<br>↓<br>
3\. AndroidManifest.xml を修正します

広告表示の実装（コード）と proguard の設定の変更は不要です。

# 1. Fluct アダプターを追加する

Arutana SDK はこれまでどおり追加してください。

### ダウンロードする

配布リポジトリの libs から `arutana-fluct-adapter-0.0.1.aar` をダウンロードしてください。

### プロジェクトに追加する

1\. ダウンロードした `arutana-fluct-adapter-0.0.1.aar` を、アプリモジュールの libs フォルダに置きます。

2\. アプリレベルの build.gradle ファイルを開き、dependencies 配下に以下のステートメントを追加します。

```
dependencies {
    // 以下を追加
    implementation files('libs/arutana-fluct-adapter-0.0.1.aar')
}
```

# 2. FluctSDK を追加する

FluctSDK は、Fluct アダプターには含まれていません。アプリに追加してください。

1\. FluctSDK の Maven リポジトリを追加します。プロジェクトレベルの build.gradle ファイル（または settings.gradle ファイル）の repositories 配下に、以下のステートメントを追加します。

```
repositories {
    google()
    // 以下を追加
    maven { url 'https://voyagegroup.github.io/FluctSDK-Android/m2/repository' }
}
```

2\. アプリレベルの build.gradle ファイルを開き、dependencies 配下に以下のステートメントを追加します。

```
dependencies {
    // 以下を追加
    implementation 'jp.fluct:FluctSDK:9.17.1'
}
```

詳しくは、Fluct のスタートガイドを御覧ください。
https://voyagegroup.github.io/FluctSDK-Doc/#/fluctsdk-android/start

# 3. AndroidManifest.xml を修正する

FluctSDK が使う権限を追加してください。

```
<manifest ...>
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <!-- Android 12（API Level 31）以降をサポートする場合に強く推奨されています -->
    <uses-permission android:name="com.google.android.gms.permission.AD_ID"/>
</manifest>
```

Google Play のデータセーフティーの申告内容は、Fluct の次のページを御覧ください。
https://voyagegroup.github.io/FluctSDK-Doc/#/fluctsdk-android/others/data-safety

### proguard について

Arutana SDK と Fluct アダプターには、必要な proguard の設定を同梱しています。アプリ側での追加の設定は不要です。

# 広告の表示について

### バナー広告

- Fluct の広告は、Arutana のバナーの View の上端中央に、320×100dp で表示します。広告を配置する View は、幅 320dp・高さ 100dp 以上にしてください。高さが足りない場合、広告の下側が欠けます。
- Fluct は、320×100 の枠にも 320×50 の広告を返すことがあります（Fluct の仕様）。その場合は、320×100dp の領域の上下の中央に表示します。
- Fluct の広告は自動で切り替わりません（ローテーションしません）。広告を更新する場合は、Arutana の広告と同じく `start()` を呼び直してください。

### インタースティシャル広告

- Fluct の 300×250 の広告を、Arutana のインタースティシャルの中に表示します。画面の幅に収まらない場合は、縦横の比率を保って縮小します（拡大はしません）。
- Fluct を設定した広告枠では、広告の視認性を確保するため、`setWidth` で既定値（80）より狭くしないでください。
- Fluct の広告を表示している間は、「詳細はこちら」ボタンと注意書きを表示しません。
- `preload()` / `show()` の呼び出しはこれまでどおりです。Fluct の広告を表示した場合も、`show()` は true を返します。

### Listener の通知

Fluct の広告も、Arutana の広告と同じ Listener メソッドで通知します。

| 状況 | バナー広告 | インタースティシャル広告 |
|---|---|---|
| Fluct の広告を受信した | onReceiveAd | arutanaInterstitialReceiveAd |
| Fluct の広告もなかった | onFailedToReceiveAd（ArutanaErrorCode.NO_AD） | arutanaInterstitialFailedToReceiveAd（ArutanaErrorCode.NO_AD） |
| Fluct の広告がタップされた | onClickAd | arutanaInterstitialDidTapAd |
| 受信した Fluct の広告が、後から表示できなくなった | onFailedToReceiveAd（ArutanaErrorCode.NO_AD） | arutanaInterstitialFailedToReceiveAd（ArutanaErrorCode.NO_AD）※ |

Fluct の広告の受信を 5 秒待っても完了しない場合は、Fluct の広告もなかったものとして通知します。

Fluct の広告は、受信を通知した後でも、Fluct 側のエラーで表示できなくなることがあります。その場合は、受信の通知の後に失敗を通知します。バナー広告では枠が空になるので、必要に応じて広告を取り直すか、枠を隠してください。

※ インタースティシャル広告では、まだ表示していない広告だけが対象です。この通知の後に `show()` を呼ぶと false が返り、その広告は表示されません。表示中の広告で起きた場合は、空のモーダルを残さないようモーダルを閉じ、arutanaInterstitialClose を通知します。

### Fluct の設定について

Fluct アダプターは、fluct の指定により、広告を読み込む前に `FluctExperimentalSettings.setBannerOmsdkSignalDisabled(true)` を呼びます。この設定は FluctSDK 全体に効くため、アプリで Fluct のバナー広告を直接使っている場合は、そのバナーにも適用されます。

### テストモードについて

Fluct のバナー広告にはテストモードがありません。`setEnableTestMode(true)` を指定しても、Fluct からは通常の広告が返ります。
テストモードを有効にすると、Fluct アダプターの詳細なログを出力します。

# うまく表示されない場合

まず、ログを出力して状況を確認してください。ログは Logcat に `ArutanaSDK` のタグで出力されます。

## Code {.tabset}

### Kotlin

```
// 確認が済んだら削除してください
LogUtils.setLogLevel(Log.DEBUG) // io.dearone.arutana.utils.LogUtils
arutana.setEnableTestMode(true) // Fluct アダプターの詳細なログも出力します（インタースティシャル広告も同じメソッドです）
```

### Java

```
// 確認が済んだら削除してください
LogUtils.setLogLevel(Log.DEBUG); // io.dearone.arutana.utils.LogUtils
arutana.setEnableTestMode(true); // Fluct アダプターの詳細なログも出力します（インタースティシャル広告も同じメソッドです）
```

## {-}

| ログ | 原因と対処 |
|---|---|
| `Mediation adapter is not available.` | Fluct アダプターまたは FluctSDK が見つかりません。`arutana-fluct-adapter-0.0.1.aar` と FluctSDK の dependencies を確認してください |
| `Mediation failed.` | Fluct の広告を受信できませんでした（エラーが `type=TIMEOUT` の場合は、5 秒以内に受信できなかったことを示します）。Fluct がエラーを返した場合は、直前の `onFailedToLoad` のログに Fluct のエラーコードが出力されます |
| `Mediation start.` が出ない | Arutana の広告を受信したか、広告枠に Fluct の設定がありません。広告枠の設定は Arutana の担当者にお問い合わせください |
