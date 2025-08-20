# ArutanaErrorCode 列挙型 リファレンスマニュアル

## パッケージ
`io.dearone.arutana`

## クラス概要
`ArutanaErrorCode` は、広告処理中に発生する可能性のあるエラーコードを定義した列挙型です。広告の取得や通信に関連する様々なエラー状態を表します。

## 継承
`java.lang.Enum<ArutanaErrorCode>`

## コンストラクタ
### `ArutanaErrorCode(String name)`
指定されたエラー名を持つ `ArutanaErrorCode` を初期化します。

- **パラメータ**
  - `name`: エラーコードの名前

## パブリックフィールド
列挙型として以下の定数が定義されています。

### `UNKNOWN`
不明なエラーを表します。

### `COMMUNICATION_ERROR`
サーバー間通信エラーを表します。

### `RECEIVED_FILLER`
在庫検知によるNO_AD判定（広告在庫が埋められている状態）を表します。

### `NO_AD`
サーバー通信での明確な在庫切れを表します。

### `NEED_CONNECTION`
通信不通の状態を表します。

### `EXCEED_LIMIT`
エラー回数上限到達を表します。

### `TEMPLATE_FAILED`
テンプレート作成失敗を表します。

## パブリックメソッド

### `@NonNull String toString()`
エラーコードの名前を文字列として返します。

- **戻り値**
  - エラーコードの名前を表す `String` インスタンス

## 内部クラス
- この列挙型にはパブリックな内部クラスはありません。
