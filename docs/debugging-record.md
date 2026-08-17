# デバッグ記録

## 最初に観測した事実

2021年12月31日を暦年で表示したいのに、`YYYY-MM-dd` は `2022-12-31` を返す。

## 再現手順

コミット `e49f028` で `mvn --batch-mode test` を実行すると、`[evidence] label=2022-12-31`、`expected: <2021-12-31> but was: <2022-12-31>` となる。

## 観測

Locale.USを固定しても結果は変わらない。問題はタイムゾーンではなく、年パターンの意味にある。

## 仮説比較

| 仮説 | 実験 | 結果 |
| --- | --- | --- |
| 実行環境のタイムゾーンが日付をずらした | LocaleとLocalDateを固定する | 年だけがずれるため棄却 |
| `YYYY`が暦年を意味する | DateTimeFormatterのパターン定義を確認する | 棄却。大文字Yはweek-based-year |
| 暦年には`uuuu`を使う | 同じ日付を`uuuu`で表示する | `2021-12-31`となり採用 |

## 原因

DateTimeFormatterのパターンで大文字`Y`はweek-based-year、小文字`y`はyear-of-era、`u`はyearを表す。[1] 暦年の帳票キーに`YYYY`を使ったため、週基準年2022へ分類された。

## 修正

`DateTimeFormatter.ofPattern("YYYY-MM-dd", Locale.US)` を `DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.US)` へ変更した。修正コミットは `be6493f` である。

## 再発防止テスト

元のテストを残し、`label=2021-12-31` を確認する。修正後は `Tests run: 1, Failures: 0, Errors: 0`、`BUILD SUCCESS` となる。

## References

[1] [Java SE 21 API — DateTimeFormatter](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/time/format/DateTimeFormatter.html)
