#!/bin/sh
if [ -z "$1" ]; then
    echo "usage: $0 <version>"
    exit
fi

# use  extended regular expressions in the script
if [ -x "${HOMEBREW_PREFIX}/bin/gsed" ]; then
    SED="${HOMEBREW_PREFIX}/bin/gsed -E"
else
    SED="/usr/bin/sed -E"
fi

# clj
${SED} -i "s/(def \^:private version) .+/\1 \"$1\")/" compare-ijf.clj
