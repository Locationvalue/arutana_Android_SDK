# Listenerについて

# 広告受信

SDK からの広告リクエストが失敗した場合に呼び出されます。

エラーコードは下記の通りです。

- UNKNOWN……不明なエラーが発生しました。
- COMMUNICATION_ERROR……アドサーバー間通信/連携しているアドネットワーク SDK との接続等でエラーが発生しました。
- RECEIVED_FILLER……白板検知されました。
- NO_AD……接続先アドネットワークすべて広告在庫切れが返却されました。
- NEED_CONNECTION……デバイスがネットワークに接続されていません。
- EXCEED_LIMIT……エラー回数が上限に達しました。
- TEMPLATE_FAILED……サイズ指定の誤りなどの要因により広告の形成に失敗しました。

## Java

```
public void onFailedToReceiveAd(ArutanaErrorCode code)
```

# 広告タップ

広告がタップされた際に呼び出されます。
（ブラウザやストア起動の成否は問いません）

## Java

```
public void onClickAd()
```


# 広告表示終了

広告を閉じたタイミングで呼び出されます。

## Java

```
public void onCloseInterstitial()
```


