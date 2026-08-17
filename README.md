# DateTimeFormatterのYYYYで暦年が週基準年になる

本ラボは、暦年を表示したい処理で大文字の `YYYY` を使うと、年末の日付が週基準年へ分類される問題を再現します。

## 実行

```bash
mvn --batch-mode test
```

バグコミットは `e49f028`、修正コミットは `be6493f` です。修正後は暦年を表す `uuuu` を使います。

## 学習の流れ

| 段階 | 観測 |
| --- | --- |
| 再現 | 2021-12-31が `2022-12-31` と表示される |
| 仮説 | タイムゾーンかLocaleの不具合 |
| 切り分け | Locale.US固定後も大文字Yの結果を確認 |
| 修正 | `YYYY`を`uuuu`へ変更 |

## References

[1] [Java SE 21 API — DateTimeFormatter](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/time/format/DateTimeFormatter.html)
