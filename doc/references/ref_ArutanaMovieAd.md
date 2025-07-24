# クラス: `ArutanaMovieAd`
`ArutanaMovieAd`は、動画広告の表示と制御を行うクラスであり、`LifecycleEventObserver`を実装しています。

## パッケージ
`io.dearone.arutana.video`

## コンストラクタ

### `ArutanaMovieAd(Activity activity)`
- **説明**: `ArutanaMovieAd`クラスのインスタンスを初期化します。
- **引数**
  - `activity`: `Activity`オブジェクト

---

## メソッド

### `void setLocationId(String locationId)`
- **説明**: 広告枠IDを設定します。
- **引数**
  - `locationId`: 設定する広告枠ID（`String`）

### `void setAdListener(ArutanaMovieListener listener)`
- **説明**: 広告のリスナーを設定します。
- **引数**
  - `listener`: 設定する`ArutanaMovieListener`オブジェクト

### `void setUserId(String userId)`
- **説明**: ログイン中のユーザーの会員IDを設定します。
- **引数**
  - `userId`: 設定するユーザーID（`String`）

### `void setTopMargin(int marginDp)`
- **説明**: 広告表示ビューの上部マージンを設定します。
- **引数**
  - `marginDp`: 上部マージン（dp単位で指定）

### `void setBottomMargin(int marginDp)`
- **説明**: 広告表示ビューの下部マージンを設定します。
- **引数**
  - `marginDp`: 下部マージン（dp単位で指定）

### `void setTextColor(int color)`
- **説明**: リンクボタンのテキストカラーを設定します。
- **引数**
  - `color`: 設定するテキストカラー（`int`）

### `void setEnableTestMode(boolean enableTestMode)`
- **説明**: テストモードを有効または無効にします。
- **引数**
  - `enableTestMode`: テストモードを有効にする場合は`true`、無効にする場合は`false`（`boolean`）

### `void dismiss()`
- **説明**: 広告を閉じて、リソースを解放します。

### `boolean isShowing()`
- **説明**: 現在広告が表示されているかどうかを返します。
- **戻り値**: 表示されている場合は`true`、そうでない場合は`false`（`boolean`）

### `boolean isPlaying()`
- **説明**: 現在動画が再生中であるかどうかを返します。
- **戻り値**: 再生中であれば`true`、そうでなければ`false`（`boolean`）

### `void show()`
- **説明**: 広告を表示します。事前にロードされた広告を表示する必要があります。`preload`メソッドを実行し、広告の読み込みが完了していることを確認してください。

### `void preload()`
- **説明**: 広告の事前ロードを開始します。

### `void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event)`
- **説明**: アプリケーションのライフサイクルイベントに基づいて処理を行います。`ON_PAUSE`イベントの場合、広告を閉じます。
- **引数**
  - `lifecycleOwner`: ライフサイクルオーナー
  - `event`: 発生したライフサイクルイベント
