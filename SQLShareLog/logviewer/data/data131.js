data = [{"owner":"jsdcosta@washington.edu","date":"5\/4\/2012 4:16:47 AM","query":"select * from [jsdcosta@washington.edu].[table_hadoop_jobs.tsv]","id":"254349"},{"owner":"jsdcosta@washington.edu","date":"5\/4\/2012 5:09:18 AM","query":"select * from [jsdcosta@washington.edu].[table_hadoop_tasks.tsv]","id":"254947"},{"owner":"jsdcosta@washington.edu","date":"5\/7\/2012 7:08:54 PM","query":"SELECT * FROM [jsdcosta@washington.edu].[table_hadoop_jobs.tsv] a, [jsdcosta@washington.edu].[table_hadoop_tasks.tsv] b where a.jobid= b.jobid","id":"8102"},{"owner":"jsdcosta@washington.edu","date":"5\/7\/2012 7:09:25 PM","query":"SELECT * FROM [jsdcosta@washington.edu].[table_hadoop_jobs.tsv] a, [jsdcosta@washington.edu].[table_hadoop_tasks.tsv] b    where a.jobid= b.jobid   AND b.tasktype = 'M'   AND a.status = '0'","id":"8103"},{"owner":"jsdcosta@washington.edu","date":"5\/7\/2012 7:12:54 PM","query":"SELECT distinct a.jobid, a.status FROM [jsdcosta@washington.edu].[table_hadoop_jobs.tsv] a, [jsdcosta@washington.edu].[table_hadoop_tasks.tsv] b    where a.jobid= b.jobid   AND b.tasktype = 'M'   AND a.status = '0'   GROUP By a.jobid, a.status","id":"8107"},{"owner":"jsdcosta@washington.edu","date":"5\/7\/2012 7:13:38 PM","query":"SELECT distinct a.jobid, a.status, count(a.numMaps) FROM [jsdcosta@washington.edu].[table_hadoop_jobs.tsv] a, [jsdcosta@washington.edu].[table_hadoop_tasks.tsv] b    where a.jobid= b.jobid   AND b.tasktype = 'M'   AND a.status = '0'   GROUP By a.jobid, a.status","id":"8108"},{"owner":"jsdcosta@washington.edu","date":"5\/7\/2012 7:14:26 PM","query":"SELECT distinct a.jobid, a.status, count(a.numMaps) FROM [jsdcosta@washington.edu].[table_hadoop_jobs.tsv] a, [jsdcosta@washington.edu].[table_hadoop_tasks.tsv] b    where a.jobid= b.jobid   AND b.tasktype = 'M'   AND a.status = '0'   GROUP By a.jobid, a.status   ORDER BY a.jobid","id":"8109"},{"owner":"jsdcosta@washington.edu","date":"5\/7\/2012 7:18:55 PM","query":"SELECT distinct a.jobid, count(a.finMaps),count(a.finMaps),count(a.failMaps),count(a.finReduces) FROM [jsdcosta@washington.edu].[table_hadoop_jobs.tsv] a, [jsdcosta@washington.edu].[table_hadoop_tasks.tsv] b    where a.jobid= b.jobid   AND b.tasktype = 'M'   AND a.status = '0'   GROUP By a.jobid   ORDER BY a.jobid","id":"8110"},{"owner":"jsdcosta@washington.edu","date":"5\/7\/2012 7:19:12 PM","query":"SELECT distinct a.jobid, count(a.finMaps),count(a.finReduces),count(a.failMaps),count(a.finReduces) FROM [jsdcosta@washington.edu].[table_hadoop_jobs.tsv] a, [jsdcosta@washington.edu].[table_hadoop_tasks.tsv] b    where a.jobid= b.jobid   AND b.tasktype = 'M'   AND a.status = '0'   GROUP By a.jobid   ORDER BY a.jobid","id":"8111"},{"owner":"jsdcosta@washington.edu","date":"5\/7\/2012 8:23:21 PM","query":"SELECT count(*)    FROM [jsdcosta@washington.edu].[table_hadoop_jobs.tsv] a, [jsdcosta@washington.edu].[table_hadoop_tasks.tsv] b    where a.jobid= b.jobid AND b.tasktype = 'M'    AND a.status = '0' and b.status = '0'","id":"8120"},{"owner":"jsdcosta@washington.edu","date":"5\/7\/2012 8:25:06 PM","query":"SELECT count(*)    FROM [jsdcosta@washington.edu].[table_hadoop_jobs.tsv] a, [jsdcosta@washington.edu].[table_hadoop_tasks.tsv] b    where a.jobid= b.jobid AND b.tasktype = 'R'    AND a.status = '0' and b.status = '0'","id":"8121"},{"owner":"jsdcosta@washington.edu","date":"5\/7\/2012 8:27:46 PM","query":"SELECT avg(finReduces)    FROM [jsdcosta@washington.edu].[table_hadoop_jobs.tsv] a, [jsdcosta@washington.edu].[table_hadoop_tasks.tsv] b    where a.jobid= b.jobid AND b.tasktype = 'R'    AND a.status = '0' and b.status = '0'","id":"8122"},{"owner":"jsdcosta@washington.edu","date":"5\/7\/2012 8:29:10 PM","query":"SELECT avg(finReduces), avg(failReduces)    FROM [jsdcosta@washington.edu].[table_hadoop_jobs.tsv] a, [jsdcosta@washington.edu].[table_hadoop_tasks.tsv] b    where a.jobid= b.jobid AND b.tasktype = 'R'    AND a.status = '0' and b.status = '0'","id":"8123"},{"owner":"jsdcosta@washington.edu","date":"5\/7\/2012 8:30:25 PM","query":"SELECT avg(finReduces), avg(failReduces)    FROM [jsdcosta@washington.edu].[table_hadoop_jobs.tsv] a, [jsdcosta@washington.edu].[table_hadoop_tasks.tsv] b    where a.jobid= b.jobid AND b.tasktype = 'M'    AND a.status = '0' and b.status = '0'","id":"8124"},{"owner":"jsdcosta@washington.edu","date":"5\/7\/2012 8:32:32 PM","query":"SELECT avg(finReduces), avg(failReduces)    FROM [jsdcosta@washington.edu].[table_hadoop_jobs.tsv] a, [jsdcosta@washington.edu].[table_hadoop_tasks.tsv] b    where a.jobid= b.jobid AND b.tasktype = 'M'    AND a.status != '0' and b.status != '0'","id":"8125"},{"owner":"jsdcosta@washington.edu","date":"5\/7\/2012 8:33:11 PM","query":"SELECT avg(finReduces), avg(failReduces)    FROM [jsdcosta@washington.edu].[table_hadoop_jobs.tsv] a, [jsdcosta@washington.edu].[table_hadoop_tasks.tsv] b    where a.jobid= b.jobid AND b.tasktype = 'R'    AND a.status != '0' and b.status != '0'","id":"8126"},{"owner":"jsdcosta@washington.edu","date":"5\/7\/2012 8:34:52 PM","query":"SELECT count(*)    FROM [jsdcosta@washington.edu].[table_hadoop_jobs.tsv] a, [jsdcosta@washington.edu].[table_hadoop_tasks.tsv] b    where a.jobid= b.jobid AND b.tasktype = 'M'    AND a.status != '0' and b.status != '0'","id":"8127"},{"owner":"jsdcosta@washington.edu","date":"5\/7\/2012 8:35:50 PM","query":"SELECT count(*)    FROM [jsdcosta@washington.edu].[table_hadoop_jobs.tsv] a, [jsdcosta@washington.edu].[table_hadoop_tasks.tsv] b    where a.jobid= b.jobid AND b.tasktype = 'M'    AND a.status = '0' and b.status = '0'","id":"8128"},{"owner":"jsdcosta@washington.edu","date":"5\/7\/2012 8:37:30 PM","query":"SELECT count(*)    FROM [jsdcosta@washington.edu].[table_hadoop_jobs.tsv] a, [jsdcosta@washington.edu].[table_hadoop_tasks.tsv] b    where a.jobid= b.jobid AND b.tasktype != 'M'    AND a.status != '0' and b.status != '0'","id":"8129"}];