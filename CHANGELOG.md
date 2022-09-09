# CHANGELOG

## Unreleased
* ./competitions を問答無用で上書きしているのはどうか？ --no-update オプションとかは？
* --example オプション。嘘の .competitions と比較する。
* --dry-run オプション。~/.competitions をアップデートしない。
* --version オプション。
* diff が見つかったら自動的にダウンロードしてくるとか。
* diff のノイズ削減。下の行番号 68a69 はなくても問題ない。
```
68a69
> 1068,World Cup Buenos Aires 2012,100
```
* 一般化。compare-json
* nil はなに？1a2 は消すはず。
```
% ./compare_ijf.clj
nil
1a2
> 1001,World Cup Belo Horizonte 2010,112
```

## 0.3.3 - 2022-09-09
* compare_ijf --version でバージョン表示。
* compare_ijf --update で比較の後にキープするデータをアップデート。
* compare_ijf 比較するが、キープするデータはそのまま。


## 0.3.1 - 2022-09-05
### Changed
* file-a, file-b を orig, download に名称変更。
* --update オプションがあり、かつ、diff が見つかった時だけ、
  orig を download で置き換える。

## 0.3.2 - 2022-09-05
### Added
- bump-version.sh やっぱあったほうがいい。

## 0.3.1 - 2022-08-19
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
