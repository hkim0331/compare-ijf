# README

Find newly created contests results at https://data.ijf.org.

## Require

* macOS or Linux. I'm using macOS to develop this script,
maybe Windows are also ok.v

* babashka
https://github.com/babashka/babashka

* `diff` command installed.
The diff command is usually found at /usr/bin/diff in macos and Linux.

## Install
compare_ijf.clj を PATH の通ったフォルダに置く。

    $ cp compare_ijf.clj ~/bin/compare_ijf
    $ chmod +x ~/bin/compare_ijf

## Usage

Output of compare_ijf is just the diff's output.
Lines start from `>` sign show the contests which results are newly added on
https://data.ijf.org.

```
$ compare_ijf
> 1068,World Cup Buenos Aires 2012,100
> 1104,Panamerican Championships Seniors 2013,108
> 1106,African Championships Seniors 2013,97
> 1184,Oceanian Open Wollongong 2014,85
> 1185,Grand Prix Qingdao 2014,109
> 1186,Grand Prix Jeju 2014,112
```

## TODO

see CHANGELOG.md

## License

Copyright &copy; 2022 Hiroshi Kimura
