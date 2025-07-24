# クラス: `Arutana`
`Arutana`は、広告表示に関する処理を担当する`RelativeLayout`クラスのサブクラスです。

## パッケージ
`io.dearone.arutana`

## インポート
以下のクラスやライブラリを使用しています。

- `android.content.Context`
- `android.graphics.Color`
- `android.graphics.Point`
- `android.os.Handler`
- `android.os.Looper`
- `android.view.View`
- `android.widget.FrameLayout`
- `android.widget.ImageView`
- `android.widget.RelativeLayout`
- `org.json.JSONException`
- `java.lang.ref.WeakReference`
- `java.util.ArrayList`
- `java.util.Timer`
- `java.util.TimerTask`
- カスタムクラスおよびユーティリティ (`ArutanaAPIClient`, `ArutanaUtils`, `DataManager`, `LogUtils`, `TimerUtils`, `HttpBody`, `Ad`, `AdResponse`, `ArutanaImageView`)

## コンストラクタ

### `Arutana(Context context)`
- **説明**: `Arutana`クラスのインスタンスを初期化します。
- **引数**
  - `context`: `Context`オブジェクト

---

## メソッド

### `String getLocationId()`
- **説明**: `locationId`を取得します。
- **戻り値**: 現在の`locationId`（`String`）

### `void setLocationId(String locationId)`
- **説明**: `locationId`を設定します。
- **引数**
  - `locationId`: 設定する`locationId`（`String`）

### `void setUserId(String userId)`
- **説明**: `userId`を設定します。
- **引数**
  - `userId`: 設定する`userId`（`String`）

### `boolean isEnableTestMode()`
- **説明**: テストモードが有効かどうかを返します。
- **戻り値**: テストモードが有効であれば`true`、無効であれば`false`（`boolean`）

### `void setEnableTestMode(boolean enableTestMode)`
- **説明**: テストモードを設定します。
- **引数**
  - `enableTestMode`: テストモードを有効にする場合は`true`、無効にする場合は`false`（`boolean`）

### `ArutanaListener getAdListener()`
- **説明**: 現在の`ArutanaListener`を取得します。
- **戻り値**: 現在の`ArutanaListener`オブジェクト

### `void setAdListener(ArutanaListener listener)`
- **説明**: `ArutanaListener`を設定します。
- **引数**
  - `listener`: 設定する`ArutanaListener`オブジェクト

### `void setAdBackGroundColor(int color)`
- **説明**: 広告ビューの背景色を設定します。
- **引数**
  - `color`: 背景色（`int`）

### `void setAdFrameSize(AdFrameSize adFrameSize)`
- **説明**: 広告フレームのサイズを設定します。
- **引数**
  - `adFrameSize`: `AdFrameSize`列挙型の値

### `void start()`
- **説明**: 広告表示を開始します。

### `void stop()`
- **説明**: 広告表示を停止します。

---

## 内部クラス

### `enum AdFrameSize`
- 広告フレームのサイズを定義する列挙型です。

#### 列挙定数

- `SP(320, 100)`: 幅320、高さ100のサイズ

#### メソッド
- `int getWidth()`: フレームの幅を取得します。
- `int getHeight()`: フレームの高さを取得します。
- `Point getPoint()`: フレームの幅と高さを含む`Point`オブジェクトを取得します。
