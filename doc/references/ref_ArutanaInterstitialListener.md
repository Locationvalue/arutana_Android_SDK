# ArutanaInterstitialListener インターフェース リファレンスマニュアル

## パッケージ
`io.dearone.arutana.interstitial`

## インターフェース概要
`ArutanaInterstitialListener` インターフェースは、`ArutanaInterstitial` クラスによって発生する各種イベントをリスニングするためのインターフェースです。広告の受信、表示、クリック、クローズなどのイベントが発生した際に、該当するメソッドがコールバックとして呼び出されます。

## 継承
このインターフェースは他のインターフェースを継承していません。

## コンストラクタ
インターフェースのため、コンストラクタは存在しません。

## パブリックフィールド
- なし

## パブリックメソッド

### `void arutanaInterstitialReceiveAd(ArutanaInterstitial arutanaInterstitial)`
広告の取得が完了した際に呼び出されるメソッドです。

- **パラメータ**
  - `arutanaInterstitial`: イベントが発生した `ArutanaInterstitial` インスタンス

### `void arutanaInterstitialShowAd(ArutanaInterstitial arutanaInterstitial)`
広告が表示された際に呼び出されるメソッドです。

- **パラメータ**
  - `arutanaInterstitial`: イベントが発生した `ArutanaInterstitial` インスタンス

### `void arutanaInterstitialDidTapAd(ArutanaInterstitial arutanaInterstitial)`
広告がクリックされた際に呼び出されるメソッドです。

- **パラメータ**
  - `arutanaInterstitial`: イベントが発生した `ArutanaInterstitial` インスタンス

### `void arutanaInterstitialClose(ArutanaInterstitial arutanaInterstitial)`
広告がクローズされた際に呼び出されるメソッドです。

- **パラメータ**
  - `arutanaInterstitial`: イベントが発生した `ArutanaInterstitial` インスタンス

### `void arutanaInterstitialFailedToReceiveAd(ArutanaInterstitial arutanaInterstitial, ArutanaErrorCode code)`
広告の取得に失敗した際に呼び出されるメソッドです。

- **パラメータ**
  - `arutanaInterstitial`: イベントが発生した `ArutanaInterstitial` インスタンス
  - `code`: 失敗の理由を表す `ArutanaErrorCode`

## 内部クラス
- このインターフェースにはパブリックな内部クラスはありません。
