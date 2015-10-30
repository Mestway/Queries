#!/bin/sh
I=0
while read p; do
  I=$((I+1))
  echo "sqllog"$I
  sqlite3 sqlshare.sqlite "SELECT sl.id, sl.owner, sl.time_start, sl.query FROM sqlshare_logs as sl WHERE sl.owner='$p' ORDER BY sl.time_start" > sqllog
  java -jar DBQExampleFormatting.jar sqllog data$I.js
done < namelist
