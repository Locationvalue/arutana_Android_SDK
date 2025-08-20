# ArutanaInterstitial クラス リファレンスマニュアル

## パッケージ
`io.dearone.arutana.interstitial`

## クラス概要
`ArutanaInterstitial` クラスは、アプリケーション内でインタースティシャル広告を表示するためのクラスです。指定された `Activity` 上にポップアップウィンドウとして広告を表示し、広告の読み込みや表示に関連するさまざまな設定をサポートします。

## 継承
このクラスは他のクラスを継承していません。

## コンストラクタ
### `ArutanaInterstitial(@NonNull Activity activity)`
指定された `Activity` インスタンスを使用して `ArutanaInterstitial` を初期化します。広告ビューの各種コンポーネントを設定し、広告表示に必要なレイアウトを初期化します。

- **パラメータ**
  - `activity`: 広告を表示する `Activity` インスタンス。

## パブリックフィールド
- なし

## パブリックメソッド

### `void setLocationId(String locationId)`
広告枠IDを設定します。

- **パラメータ**
  - `locationId`: 広告枠ID

### `void setUserId(String userId)`
ログイン中のユーザーの会員IDを設定します。

- **パラメータ**
  - `userId`: ユーザーID

### `void setTopMargin(int marginDp)`
広告の上部マージンを設定します。単位はDPです。

- **パラメータ**
  - `marginDp`: 上部マージンの値

### `void setWidth(float per)`
広告の幅を設定します。パーセンテージで指定します。

- **パラメータ**
  - `per`: 幅のパーセンテージ（100が元の幅）

### `void setAdTextColor(int color)`
広告のテキストカラーを設定します。

- **パラメータ**
  - `color`: テキストカラー（`int` 型）

### `void setAdBackgroundColor(int color)`
広告の背景色を設定します。

- **パラメータ**
  - `color`: 背景色（`int` 型）

### `void setAdListener(ArutanaInterstitialListener listener)`
広告イベントのリスナーを設定します。

- **パラメータ**
  - `listener`: `ArutanaInterstitialListener` インターフェースを実装したリスナー

### `void setEnableTestMode(boolean enableTestMode)`
テストモードを有効にします。

- **パラメータ**
  - `enableTestMode`: テストモードを有効にする場合は `true`、無効にする場合は `false`

### `void preload()`
非同期で広告を事前に読み込みます。

### `boolean show()`
広告を同期的に表示します。広告が事前にロードされていない場合、表示できません。

- **戻り値**
  - 広告の表示に成功した場合は `true`、失敗した場合は `false`

### ``void showAsync()``
このメソッドは非推奨であり、今後削除される予定です。今後は利用しないことを推奨します。

### `void dismiss()`
表示中の広告を閉じます。

### `void onResume()`
広告のイベント制御に必要なため、モーダル広告を表示するアクティビティのonResumeイベント時に呼び出してください。

### `void onPause()`
広告のイベント制御に必要なため、モーダル広告を表示するアクティビティのonPauseイベント時に呼び出してください。

## 内部クラス
- このクラスにはパブリックな内部クラスはありません。
