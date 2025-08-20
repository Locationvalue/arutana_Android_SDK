# ArutanaMovieListener インターフェース リファレンスマニュアル

## パッケージ
`io.dearone.arutana.video`

## インターフェース概要
`ArutanaMovieListener` インターフェースは、動画広告に関連する各種イベントをリスニングするためのインターフェースです。広告の取得、表示、クリック、クローズ、全画面表示の開始および終了など、動画広告に関するイベントが発生した際にコールバックメソッドが呼び出されます。

## 継承
このインターフェースは他のインターフェースを継承していません。

## コンストラクタ
インターフェースのため、コンストラクタは存在しません。

## パブリックフィールド
- なし

## パブリックメソッド

### `void arutanaMovieReceiveAd()`
動画広告の取得が完了した際に呼び出されるメソッドです。

### `void arutanaMovieShowAd()`
動画広告が表示された際に呼び出されるメソッドです。

### `void arutanaMovieDidTapAd()`
動画広告がクリックされた際に呼び出されるメソッドです。

### `void arutanaMovieClose()`
動画広告がクローズされた際に呼び出されるメソッドです。

### `void arutanaMovieStartFull()`
動画広告の全画面表示が開始された際に呼び出されるメソッドです。

### `void arutanaMovieStart()`
  動画広告が再生された際に呼び出されるメソッドです。

### `void arutanaMovieFirstQuartile()`
  動画広告が25%再生された際に呼び出されるメソッドです。

### `void arutanaMovieMidQuartile()`
  動画広告が50%再生された際に呼び出されるメソッドです。

### `void arutanaMovieThirdQuartile()`
  動画広告が75%再生された際に呼び出されるメソッドです。

### `void arutanaMovieComplete()`
  動画広告が再生完了された際に呼び出されるメソッドです。

### `void arutanaMovieEndFull()`
  動画広告が全画面終了された際に呼び出されるメソッドです。

### `void arutanaMovieSoundMuted()`
  動画広告がミュートされた際に呼び出されるメソッドです。

### `void arutanaMovieSoundUnmuted()`
  動画広告がミュート解除された際に呼び出されるメソッドです。

### `void arutanaMovieFailedToReceiveAd(ArutanaErrorCode code)`
動画広告の取得に失敗した際に呼び出されるメソッドです。

- **パラメータ**
  - `code`: 失敗の理由を表す `ArutanaErrorCode` インスタンス

## 内部クラス
- このインターフェースにはパブリックな内部クラスはありません。
