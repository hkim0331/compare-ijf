# CHANGELOG

## Unreleased
* ./competitions を問答無用で上書きしているのはどうか？ --no-update オプションとかは？
* --example オプション。
* diff が見つかったら自動的にダウンロードしてくるとか。
* --dry-run オプション。~/.competitions をアップデートしない。
* diff のノイズ削減。下の行番号 68a69 はなくても問題ない。
```
68a69
> 1068,World Cup Buenos Aires 2012,100
```

## 0.3.0 - 2022-08-19
### Changed
* namespaced. これに伴い、compare-ijf を compare_ijf に名称変更。
* updated README.md
### Fixed
* save_as_text appends "\n" at the end of file. This fixed 0.2.0 issue.

## 0.2.0 - 2022-08-18
* this is not a bug
```
! 999 World Cup Budapest 2010 56
--- 676,679 ----
! 999 World Cup Budapest 2010 56
\ No newline at end of file
```
emacs/vi appends a newline at the EOF if the file does not terminate
with newline.
### Changed
* save-as-text はコンマ区切りがお好みか？
* prefer `--normal` rather than `--context`?


## 0.1.0 - 2022-08-18
* project started.
* can display diffs.
