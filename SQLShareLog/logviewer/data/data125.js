data = [{"owner":"johnzeng@gmail.com","date":"9\/7\/2013 10:48:05 PM","query":"select * from [johnzeng@gmail.com].[tf.csv]","id":"28057"},{"owner":"johnzeng@gmail.com","date":"9\/7\/2013 10:55:10 PM","query":"select * from [johnzeng@gmail.com].[table_tf.csv]","id":"254664"},{"owner":"johnzeng@gmail.com","date":"9\/7\/2013 10:55:36 PM","query":"SELECT distinct docid FROM [johnzeng@gmail.com].[table_tf.csv]","id":"28061"},{"owner":"johnzeng@gmail.com","date":"9\/7\/2013 10:58:44 PM","query":"SELECT count (distinct docid) FROM [johnzeng@gmail.com].[table_tf.csv]","id":"28067"},{"owner":"johnzeng@gmail.com","date":"9\/7\/2013 10:59:35 PM","query":"SELECT term, count (distinct docid)    FROM [johnzeng@gmail.com].[table_tf.csv]   GROUP BY term","id":"28068"},{"owner":"johnzeng@gmail.com","date":"9\/7\/2013 11:00:27 PM","query":"SELECT term, count (docid)    FROM [johnzeng@gmail.com].[table_tf.csv]   GROUP BY term","id":"28069"},{"owner":"johnzeng@gmail.com","date":"9\/7\/2013 11:01:10 PM","query":"SELECT term, 6\/count (docid)    FROM [johnzeng@gmail.com].[table_tf.csv]   GROUP BY term","id":"28070"},{"owner":"johnzeng@gmail.com","date":"9\/7\/2013 11:01:21 PM","query":"SELECT term, 6\/count (docid) as idf   FROM [johnzeng@gmail.com].[table_tf.csv]   GROUP BY term","id":"28071"},{"owner":"johnzeng@gmail.com","date":"9\/7\/2013 11:01:45 PM","query":"SELECT term, log (6\/count (docid)) as idf   FROM [johnzeng@gmail.com].[table_tf.csv]   GROUP BY term","id":"28072"},{"owner":"johnzeng@gmail.com","date":"9\/7\/2013 11:03:39 PM","query":"select term, log (6\/count (docid)) as idf   from [johnzeng@gmail.com].[table_tf.csv]   group by term","id":"254708"},{"owner":"johnzeng@gmail.com","date":"9\/7\/2013 11:07:13 PM","query":"SELECT tf.term, count*idf   FROM [johnzeng@gmail.com].[tf.csv] tf   INNER JOIN [johnzeng@gmail.com].[idf] idf   ON tf.term = idf.term","id":"28076"},{"owner":"johnzeng@gmail.com","date":"9\/7\/2013 11:09:50 PM","query":"SELECT tf.term, sum(count*idf)   FROM [johnzeng@gmail.com].[tf.csv] tf   INNER JOIN [johnzeng@gmail.com].[idf] idf   ON tf.term = idf.term   GROUP BY tf.term","id":"28080"},{"owner":"johnzeng@gmail.com","date":"9\/7\/2013 11:10:22 PM","query":"select tf.term, sum(count*idf)   from [johnzeng@gmail.com].[tf.csv] tf   inner join [johnzeng@gmail.com].[idf] idf   on tf.term = idf.term   group by tf.term","id":"254753"}];