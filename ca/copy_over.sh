#!/bin/bash

# shell script to copy contents of local clone of synthea-international over clone of synthea.
# assume current working directory is common parent of both local clones

GIT=$(pwd)

die(){
	echo error $0: $* 1>&2
	exit 2
}

SYNTHEA=synthea/src
SYNTHEA_INTL=synthea-international/ca/src

# --update might be an idea to stop overwriting files that were edited in the synthea tree
RSYNC="rsync -v -a $SYNTHEA_INTL/ $SYNTHEA"  #watch the  darn  trailing / on source.

test -d $SYNTHEA || die "no local clone of $SYNTHEA in current working directory. Are you in the parent dir of both $SYNTHEA and $SYNTHEA_INTL ?"
test -d $SYNTHEA_INTL || die "no local clone of $SYNTHEA_INTL in current working directory. Are you in the parent dir of both $SYNTHEA and $SYNTHEA_INTL ?"

echo '***** dry-run  ' ``$RSYNC --dry-run''. this is what rsync says it will do:

$RSYNC --dry-run

read -p 'continue with actual rsync command? hit enter to continue > ' JUNK

$RSYNC 
#rsync -v -a  $SYNTHEA_INTL $SYNTHEA/src/
