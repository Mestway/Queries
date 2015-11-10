data = [{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 6:56:26 AM","query":"SELECT TOP 10 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p WHERE a.fullname = b.fullname and b.pubID = p.id   and p.booktitle='SIGCOMM' GROUP BY a.fullname ORDER BY c DESC;","id":"24733"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 6:57:27 AM","query":"SELECT TOP 10 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p WHERE a.fullname = b.fullname and b.pubID = p.id   and (p.booktitle='STOC' or p.booktitle='FOCS') GROUP BY a.fullname ORDER BY c DESC;","id":"24735"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 6:58:34 AM","query":"SELECT TOP 20 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p WHERE a.fullname = b.fullname and b.pubID = p.id   and (p.booktitle='STOC' or p.booktitle='FOCS') GROUP BY a.fullname ORDER BY c DESC;","id":"24736"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 7:13:13 AM","query":"SELECT TOP 50 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p WHERE a.fullname = b.fullname and b.pubID = p.id   and (p.booktitle='STOC' or p.booktitle='FOCS') GROUP BY a.fullname ORDER BY c DESC;","id":"24746"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 7:22:43 AM","query":"SELECT * FROM [suciu@washington.edu].[inproceedings]","id":"24749"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 7:30:59 AM","query":"SELECT * FROM [suciu@washington.edu].[author]","id":"24757"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 7:34:58 AM","query":"SELECT TOP 10 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p WHERE a.fullname = b.fullname and b.pubID = p.id   and p.booktitle='STOC' GROUP BY a.fullname ORDER BY c DESC;","id":"24759"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 7:40:15 AM","query":"SELECT TOP 10 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p,       [suciu@washington.edu].[Publication] q WHERE a.fullname = b.fullname and b.pubID = p.id and q.id = p.id   and p.booktitle='STOC' GROUP BY a.fullname ORDER BY c DESC;","id":"24765"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 7:40:42 AM","query":"SELECT TOP 10 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p,       [suciu@washington.edu].[Publication] q WHERE a.fullname = b.fullname and b.pubID = p.id and q.id = p.id   and p.booktitle='STOC'   and q.year='2002' GROUP BY a.fullname ORDER BY c DESC;","id":"24766"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 7:41:12 AM","query":"SELECT TOP 10 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p,       [suciu@washington.edu].[Publication] q WHERE a.fullname = b.fullname and b.pubID = p.id and q.id = p.id   and (p.booktitle='STOC' or p.booktitle='FOCS')   and q.year='2002' GROUP BY a.fullname ORDER BY c DESC;","id":"24767"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 7:41:38 AM","query":"SELECT TOP 10 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p,       [suciu@washington.edu].[Publication] q WHERE a.fullname = b.fullname and b.pubID = p.id and q.id = p.id   and (p.booktitle='STOC' or p.booktitle='FOCS')   and q.year >= 2003 GROUP BY a.fullname ORDER BY c DESC;","id":"24768"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 7:41:58 AM","query":"SELECT TOP 20 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p,       [suciu@washington.edu].[Publication] q WHERE a.fullname = b.fullname and b.pubID = p.id and q.id = p.id   and (p.booktitle='STOC' or p.booktitle='FOCS')   and q.year >= 2003 GROUP BY a.fullname ORDER BY c DESC;","id":"24769"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 7:43:02 AM","query":"SELECT TOP 20 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p,       [suciu@washington.edu].[Publication] q WHERE a.fullname = b.fullname and b.pubID = p.id and q.id = p.id   and (p.booktitle='STOC' or p.booktitle='FOCS')   and q.year >= 2004 GROUP BY a.fullname ORDER BY c DESC;","id":"24770"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 7:43:20 AM","query":"SELECT TOP 20 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p,       [suciu@washington.edu].[Publication] q WHERE a.fullname = b.fullname and b.pubID = p.id and q.id = p.id   and (p.booktitle='STOC' or p.booktitle='FOCS')   and q.year >= 2005 GROUP BY a.fullname ORDER BY c DESC;","id":"24771"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 7:44:03 AM","query":"SELECT TOP 20 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p,       [suciu@washington.edu].[Publication] q WHERE a.fullname = b.fullname and b.pubID = p.id and q.id = p.id   and (p.booktitle='STOC' or p.booktitle='FOCS')   and q.year >= 2008 GROUP BY a.fullname ORDER BY c DESC;","id":"24772"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 7:44:28 AM","query":"SELECT TOP 30 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p,       [suciu@washington.edu].[Publication] q WHERE a.fullname = b.fullname and b.pubID = p.id and q.id = p.id   and (p.booktitle='STOC' or p.booktitle='FOCS')   and q.year >= 2008 GROUP BY a.fullname ORDER BY c DESC;","id":"24773"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 7:45:05 AM","query":"SELECT TOP 30 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p,       [suciu@washington.edu].[Publication] q WHERE a.fullname = b.fullname and b.pubID = p.id and q.id = p.id   and (p.booktitle='STOC' or p.booktitle='FOCS')   and q.year >= 2003 GROUP BY a.fullname ORDER BY c DESC;","id":"24774"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 7:47:14 AM","query":"SELECT TOP 25 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p,       [suciu@washington.edu].[Publication] q WHERE a.fullname = b.fullname and b.pubID = p.id and q.id = p.id   and (p.booktitle='STOC' or p.booktitle='FOCS')   and q.year >= 2008 GROUP BY a.fullname ORDER BY c DESC;","id":"24777"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 7:47:43 AM","query":"SELECT TOP 25 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p,       [suciu@washington.edu].[Publication] q WHERE a.fullname = b.fullname and b.pubID = p.id and q.id = p.id   and (p.booktitle='STOC' or p.booktitle='FOCS')   and q.year >= 2003 GROUP BY a.fullname ORDER BY c DESC;","id":"24778"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 7:49:23 AM","query":"SELECT TOP 25 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p,       [suciu@washington.edu].[Publication] q WHERE a.fullname = b.fullname and b.pubID = p.id and q.id = p.id   and (p.booktitle='STOC' or p.booktitle='FOCS' or p.booktitle='SODA')   and q.year >= 2003 GROUP BY a.fullname ORDER BY c DESC;","id":"24779"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 7:50:00 AM","query":"SELECT TOP 25 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p,       [suciu@washington.edu].[Publication] q WHERE a.fullname = b.fullname and b.pubID = p.id and q.id = p.id   and (p.booktitle='STOC' or p.booktitle='FOCS' or p.booktitle='SODA' or p.booktitle='CCC')   and q.year >= 2003 GROUP BY a.fullname ORDER BY c DESC;","id":"24780"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 7:50:34 AM","query":"SELECT TOP 25 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p,       [suciu@washington.edu].[Publication] q WHERE a.fullname = b.fullname and b.pubID = p.id and q.id = p.id   and (p.booktitle='CCC')   and q.year >= 2003 GROUP BY a.fullname ORDER BY c DESC;","id":"24781"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 7:50:43 AM","query":"SELECT TOP 25 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p,       [suciu@washington.edu].[Publication] q WHERE a.fullname = b.fullname and b.pubID = p.id and q.id = p.id   and (p.booktitle='SODA')   and q.year >= 2003 GROUP BY a.fullname ORDER BY c DESC;","id":"24782"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 7:51:11 AM","query":"SELECT TOP 25 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p,       [suciu@washington.edu].[Publication] q WHERE a.fullname = b.fullname and b.pubID = p.id and q.id = p.id   and (p.booktitle='SOCG')   and q.year >= 2003 GROUP BY a.fullname ORDER BY c DESC;","id":"24783"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 7:51:51 AM","query":"SELECT TOP 25 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p,       [suciu@washington.edu].[Publication] q WHERE a.fullname = b.fullname and b.pubID = p.id and q.id = p.id   and (p.booktitle='STOC')   and q.year >= 2003 GROUP BY a.fullname ORDER BY c DESC;","id":"24785"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 7:52:15 AM","query":"SELECT TOP 20 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p,       [suciu@washington.edu].[Publication] q WHERE a.fullname = b.fullname and b.pubID = p.id and q.id = p.id   and (p.booktitle='STOC')   and q.year >= 2003 GROUP BY a.fullname ORDER BY c DESC;","id":"24786"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 7:56:17 AM","query":"SELECT TOP 25 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p,       [suciu@washington.edu].[Publication] q WHERE a.fullname = b.fullname and b.pubID = p.id and q.id = p.id   and (p.booktitle='STOC' or p.booktitle='FOCS')   and q.year >= 2010 GROUP BY a.fullname ORDER BY c DESC;","id":"24789"},{"owner":"jrlcs@washington.edu","date":"6\/3\/2013 8:05:03 AM","query":"SELECT TOP 25 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p,       [suciu@washington.edu].[Publication] q WHERE a.fullname = b.fullname and b.pubID = p.id and q.id = p.id   and (p.booktitle='STOC' or p.booktitle='FOCS')   and q.year >= 1998 GROUP BY a.fullname ORDER BY c DESC;","id":"24794"},{"owner":"jrlcs@washington.edu","date":"8\/31\/2013 10:15:16 PM","query":"SELECT TOP 25 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p,       [suciu@washington.edu].[Publication] q WHERE a.fullname = b.fullname and b.pubID = p.id and q.id = p.id   and (p.booktitle='STOC' or p.booktitle='FOCS')   and q.year >= 1993 GROUP BY a.fullname ORDER BY c DESC;","id":"28031"},{"owner":"jrlcs@washington.edu","date":"8\/31\/2013 10:15:44 PM","query":"SELECT TOP 25 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p,       [suciu@washington.edu].[Publication] q WHERE a.fullname = b.fullname and b.pubID = p.id and q.id = p.id   and (p.booktitle='STOC' or p.booktitle='FOCS')   and q.year >= 2004 GROUP BY a.fullname ORDER BY c DESC;","id":"28032"},{"owner":"jrlcs@washington.edu","date":"9\/1\/2013 5:17:14 AM","query":"SELECT TOP 30 a.fullname, count(*) as c FROM  [suciu@washington.edu].[author] a,       [suciu@washington.edu].[authored] b,       [suciu@washington.edu].[inproceedings] p,       [suciu@washington.edu].[Publication] q WHERE a.fullname = b.fullname and b.pubID = p.id and q.id = p.id   and (p.booktitle='STOC' or p.booktitle='FOCS')   and q.year >= 2004 GROUP BY a.fullname ORDER BY c DESC;","id":"28033"}];