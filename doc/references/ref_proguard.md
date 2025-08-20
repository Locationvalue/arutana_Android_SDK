# Proguardの設定について
SDK を導入したアプリを難読化する際は以下の設定値を proguard の設定ファイルに追加してください。

```
-keep public class io.dearone.arutana.** { *; }
-keep public interface io.dearone.arutana.** { *; }
```
