data = [{"owner":"shuaibm@gmail.com","date":"5\/15\/2013 6:08:22 PM","query":"select * from [shuaibm@gmail.com].[table_data.csv]","id":"252625"},{"owner":"shuaibm@gmail.com","date":"5\/15\/2013 6:10:05 PM","query":"select a.docid,b.docid db, sum(a.count*b.count) value from [shuaibm@gmail.com].[table_data.csv] a, [shuaibm@gmail.com].[table_data.csv] b where a.term = b.term and a.docid < b.docid group by a.docid,b.docid having a.docid = '10080_txt_crude' and b.docid = '17035_txt_earn'","id":"23577"},{"owner":"shuaibm@gmail.com","date":"5\/15\/2013 6:12:07 PM","query":"select a.docid,b.docid db, sum(a.count*b.count) value from [shuaibm@gmail.com].[table_data.csv] a, [shuaibm@gmail.com].[table_data.csv] b where a.term = b.term and a.docid < b.docid and a.docid = '10080_txt_crude' and b.docid = '17035_txt_earn' group by a.docid,b.docid","id":"23579"},{"owner":"shuaibm@gmail.com","date":"5\/15\/2013 6:14:56 PM","query":"SELECT * FROM [shuaibm@gmail.com].[frequency] UNION SELECT 'q' as docid, 'washington' as term, 1 as count  UNION SELECT 'q' as docid, 'taxes' as term, 1 as count UNION  SELECT 'q' as docid, 'treasury' as term, 1 as count","id":"23580"},{"owner":"shuaibm@gmail.com","date":"5\/15\/2013 6:15:13 PM","query":"select * from [shuaibm@gmail.com].[frequency] union select 'q' as docid, 'washington' as term, 1 as count  union select 'q' as docid, 'taxes' as term, 1 as count union  select 'q' as docid, 'treasury' as term, 1 as count","id":"252670"},{"owner":"shuaibm@gmail.com","date":"5\/15\/2013 6:15:42 PM","query":"SELECT * FROM [shuaibm@gmail.com].[frequency] where docid = 'q'","id":"23581"},{"owner":"shuaibm@gmail.com","date":"5\/15\/2013 6:16:13 PM","query":"SELECT * FROM [shuaibm@gmail.com].[freq2] where docid = 'q'","id":"23582"},{"owner":"shuaibm@gmail.com","date":"5\/15\/2013 6:17:33 PM","query":"select a.docid,b.docid db, sum(a.count*b.count) value from [shuaibm@gmail.com].[freq2] a, [shuaibm@gmail.com].[freq2] b where a.term = b.term and a.docid < b.docid group by a.docid,b.docid having a.docid = 'q'","id":"23583"},{"owner":"shuaibm@gmail.com","date":"5\/15\/2013 6:17:45 PM","query":"select a.docid,b.docid db, sum(a.count*b.count) value from [shuaibm@gmail.com].[freq2] a, [shuaibm@gmail.com].[freq2] b where a.term = b.term and a.docid < b.docid group by a.docid,b.docid","id":"23584"},{"owner":"shuaibm@gmail.com","date":"5\/15\/2013 6:18:26 PM","query":"select a.docid,b.docid db, sum(a.count*b.count) value from [shuaibm@gmail.com].[freq2] a, [shuaibm@gmail.com].[freq2] b where a.term = b.term and a.docid < b.docid group by a.docid,b.docid having a.docid ='q'","id":"23586"},{"owner":"shuaibm@gmail.com","date":"5\/15\/2013 6:18:31 PM","query":"select a.docid,b.docid db, sum(a.count*b.count) value from [shuaibm@gmail.com].[freq2] a, [shuaibm@gmail.com].[freq2] b where a.term = b.term and a.docid < b.docid group by a.docid,b.docid having b.docid ='q'","id":"23587"},{"owner":"shuaibm@gmail.com","date":"5\/15\/2013 6:18:44 PM","query":"select a.docid,b.docid db, sum(a.count*b.count) value from [shuaibm@gmail.com].[freq2] a, [shuaibm@gmail.com].[freq2] b where a.term = b.term and a.docid < b.docid group by a.docid,b.docid having b.docid ='q' order by value desc","id":"23589"}];