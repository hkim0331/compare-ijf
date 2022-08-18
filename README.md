# README

Find newly created contests results at https://data.ijf.org.

## Require

* macOS or Linux. I'm using macOS to develop this script,
maybe Windows are also ok.

* babashka
https://github.com/babashka/babashka

* `diff` command installed.
The diff command is usually found at /usr/bin/diff in macos and Linux.

## Install
compare-ijf.clj を PATH の通ったフォルダに置く。

    $ cp compare-ijf.clj ~/bin/compare-ijf
    $ chmod +x ~/bin/compare-ijf

## Usage

Output of compare-ijf is just the diff's output.
Lines start from `+` sign show the contests which results are newly added on
https://data.ijf.org.

```
$ compare-ijf
*** /tmp/competitions-A  2022-08-18 21:35:09.000000000 +0900
--- /tmp/competitions-B  2022-08-18 21:35:11.000000000 +0900
***************
*** 9,22 ****
--- 9,32 ----
  1008 World Championships Tokyo 2010 128
  1009 World Cup Prague 2010 56
  1010 World Cup Tashkent 2010 56
+ 1011 World Cup Alamata 2010 56
+ 1012 World Cup Birmingham 2010 55
+ 1013 World Cup Rome 2010 56
+ 1014 World Cup Baku 2010 56
  1015 World Cup Minsk 2010 56
  1016 World Cup Suwon 2010 112
  1017 Grand Slam Tokyo 2009 112
```

## TODO

see CHANGELOG.md

## License

Copyright &copy; 2022 Hiroshi Kimura
