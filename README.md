# README

Find newly created contests results at https://data.ijf.org.

## Require

* macOS or Linux.
Maybe Windows are also ok if babashka and diff are properly installed.
I'm using macOS to develop this script.

* babashka
https://github.com/babashka/babashka

* diff command.
The diff command is usually found at `/usr/bin/diff` in macos and Linux.

## Install
`compare_ijf.clj` を PATH の通ったフォルダに置き、実行可能ビットを立てておく。
名前は変えても大丈夫。以下は .clj を剥ぎ取った名前にセーブしてます。

    $ cp compare_ijf.clj ~/bin/compare_ijf
    $ chmod +x ~/bin/compare_ijf

## Usage

Output of compare_ijf is just the diff's output.
Each line shows the `id_completition`, `name` and `has_results` values
which are newly added on
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

For example, the first line tells that IJF updates the 100 results about the
competition whose id_competition is 1068, and whose name is 'World Cup Buenos
Aires 2012'.

## TODO

see CHANGELOG.md

## See also,

Followings are cousin projects of this. They are also under construction.

* fights https://github.com/hkim0331/fights
* fetch-ijf https://github.com/hkim0331/fetch-ijf

## License

Copyright &copy; 2022 Hiroshi Kimura
