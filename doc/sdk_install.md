# 広告SDK導入手順（Android版）

---

## はじめに

このドキュメントでは、Android向けの広告SDKを導入するための手順を説明します。本手順は、Android Studioがすでにインストールされており、基本的な設定が完了している環境を前提としています。以下の対応バージョンに基づいてSDKを導入してください。

---

## 対応バージョン

- **Android**：8.0 以降（API Level 26）

---

## 導入の流れ

### 1. SDKのバイナリファイルをダウンロード

[こちらのリンク](https://github.com/Locationvalue/arutana_Android_SDK/raw/refs/heads/main/libs/arutana-latest.aar)からSDKのバイナリファイル（`arutana-latest.aar`）をダウンロードしてください。

### 2. プロジェクトにSDKを追加

1. Android Studioで、`File > New > New Module > Import.JAR/.AAR Package` を選択します。
2. ダウンロードした`arutana-x.x.x.aar`ファイルを選択し、プロジェクトに追加します。
3. プロジェクトツールウィンドウ（Projectビュー）で、追加されたモジュールを選択し、右クリック > `Open Module Settings` を選択します。
4. 「アプリモジュール」を選択し、`Dependencies` タブを開き、「+」ボタンをクリックします。
5. `Module Dependency` を選択し、リストから `:arutana-x.x.x` を選択します。

---

## 3. AndroidManifest.xmlを修正する

SDKを正しく動作させるために、ハードウェアアクセラレーションを有効にする必要があります。これを行うためには、`AndroidManifest.xml`に以下の設定を追加してください。

```xml
<application
  android:hardwareAccelerated="true">
    <!-- その他の設定を省略 -->
</application>
```

これにより、アプリケーション全体でハードウェアアクセラレーションが有効になります。

---

## 4. 依存するライブラリを追加する

広告SDKには以下のライブラリが必要です。これらのライブラリを`build.gradle`ファイルに追加してください。

### 必要なライブラリ

- **libs.browser**：広告をクリックした際に、アプリ内ブラウザを起動するために必要です。
- **libs.androidx.lifecycle.process**：SDKの制御に必要なライブラリです。

```groovy
dependencies {
    implementation 'libs.browser' // 広告クリック時のブラウザ起動に必要
    implementation 'libs.androidx.lifecycle.process' // SDKのライフサイクル管理に必要
}
```

`build.gradle`に依存関係を追加した後、プロジェクトを同期して必要なライブラリをインストールしてください。

---

## 注意事項

- 依存するライブラリが正しく設定されていない場合、SDKの機能が正しく動作しない可能性があります。導入後は必ずビルドし、動作確認を行ってください。
- AndroidManifest.xmlの設定が誤っていると、アプリケーションが正しく動作しない可能性があります。設定後は必ずテストを行ってください。

---

これでSDKの導入手順は完了です。引き続き、アプリケーションの開発を進めてください。
