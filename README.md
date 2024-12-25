# Arutana 広告SDK Android

## Release Notes

### 2024-12-26: 0.0.24

- モーダル広告にて、広告タップ時にarutanaInterstitialCloseイベントが呼ばれるタイミングを修正. 従来タップ時に呼ばれていたが、内部ブラウザを閉じた際に呼ばれるように変更.
- 特定の条件下でフリークエンシーコントロールが正常に動作しない不具合を修正.
- 上記の修正に伴い、ArutanaIntersitialAdクラスにonResume, onPauseメソッドを追加し、実装マニュアルを更新.

### 2024-10-29: 0.0.22

- モーダル広告の `setWidth()` が効かない問題を修正
