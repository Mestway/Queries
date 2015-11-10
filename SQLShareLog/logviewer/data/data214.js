data = [{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 10:58:00 PM","query":"--SELECT*FROM [table_experiment.csv]; --SELECT gene, experiment_type FROM [table_experiment.csv]; --COUNT: return the number of rows SELECT COUNT(*) FROM [table_experiment.csv] WHERE expression_level>5","id":"21477"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 10:58:27 PM","query":"--SELECT*FROM [table_experiment.csv]; --SELECT gene, experiment_type FROM [table_experiment.csv]; --COUNT: return the number of rows SELECT COUNT(*) FROM [table_experiment.csv] WHERE experiment_type='microarray'","id":"21478"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 10:58:56 PM","query":"--SELECT*FROM [table_experiment.csv]; --SELECT gene, experiment_type FROM [table_experiment.csv]; --COUNT: return the number of rows SELECT * FROM [table_experiment.csv] WHERE experiment_type = 'microarray';","id":"21480"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 11:00:18 PM","query":"SELECT gene, COUNT(experiment_type) FROM [table_experiment.csv] GROUP BY gene","id":"21481"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 11:01:12 PM","query":"SELECT MAX(expression_level) FROM [table_experiment.csv]","id":"21482"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 11:02:22 PM","query":"SELECT gene FROM [table_experiment.csv] WHERE expression_level=10","id":"21483"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 11:02:47 PM","query":"SELECT count(*) FROM [table_experiment.csv] WHERE expression_level=10","id":"21484"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 11:03:29 PM","query":"SELECT COUNT(*) FROM [table_experiment.csv] WHERE expression_level= 10","id":"21488"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 11:04:14 PM","query":"SELECT MAX(expression_level) FROM [table_experiment.csv]","id":"21489"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 11:04:20 PM","query":"SELECT MIN(expression_level) FROM [table_experiment.csv]","id":"21490"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 11:05:04 PM","query":"SELECT gene, expression_level FROM [experiment.csv]","id":"21492"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 11:05:32 PM","query":"SELECT gene, expression_level FROM [experiment.csv]   UNION   SELECT gene, media_id FROM [experiment.csv]","id":"21493"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 11:09:07 PM","query":"SELECT gene, expression_level FROM [experiment.csv]   EXCEPT   SELECT gene, media_id FROM [experiment.csv]","id":"21494"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 11:12:02 PM","query":"SELECT gene, count(*) as \"exptype\" FROM [experiment.csv] GROUP BY gene","id":"21498"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 11:12:20 PM","query":"SELECT experiment_type, count(*) as \"exptype\" FROM [experiment.csv] GROUP BY experiment_type","id":"21499"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 11:12:54 PM","query":"SELECT experiment_type, count(*) as \"exptype\" FROM [experiment.csv] GROUP BY experiment_type HAVING count(*)>1","id":"21500"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 11:13:16 PM","query":"SELECT experiment_type, count(gene) as \"exptype\" FROM [experiment.csv] GROUP BY experiment_type HAVING count(gene)>1","id":"21501"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 11:17:54 PM","query":"SELECT * FROM [experiment.csv] WHERE expression_level =5","id":"21502"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 11:19:02 PM","query":"SELECT * FROM [experiment.csv] WHERE expression_level =5 AND experiment_type = 'microarray'","id":"21503"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 11:20:11 PM","query":"SELECT * FROM [experiment.csv] WHERE expression_level between 4 and 7","id":"21504"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 11:24:08 PM","query":"INSERT INTO [experiment.csv] (id, gene, experiment_type, media_id) VALUES (101, 'TEST', 'microarray', 1);","id":"21508"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 11:45:19 PM","query":"SELECT COUNT(*) FROM [experiment.csv]","id":"21510"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 6:05:01 PM","query":"SELECT * FROM [isaphan@washington.edu].experiment","id":"21393"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:17:01 PM","query":"SELECT gene,expression_level   FROM [isaphan@washington.edu].experiment","id":"21409"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:18:59 PM","query":"SELECT gene,expression_level   FROM [isaphan@washington.edu].experiment ORDER BY expression_level","id":"21413"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:19:15 PM","query":"SELECT gene,expression_level   FROM [isaphan@washington.edu].experiment ORDER BY expression_level DESC","id":"21414"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:23:35 PM","query":"SELECT COUNT(experiment_type) FROM [isaphan@washington.edu].experiment","id":"21418"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:23:49 PM","query":"SELECT COUNT(id) FROM [isaphan@washington.edu].experiment","id":"21419"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:24:01 PM","query":"SELECT COUNT(expression_level) FROM [isaphan@washington.edu].experiment","id":"21420"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:24:15 PM","query":"SELECT COUNT(DISTINCT expression_level) FROM [isaphan@washington.edu].experiment","id":"21421"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:24:24 PM","query":"SELECT COUNT(DISTINCT id) FROM [isaphan@washington.edu].experiment","id":"21422"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:25:04 PM","query":"SELECT COUNT(DISTINCT id) FROM [isaphan@washington.edu].experiment  SELECT COUNT(DISTINCT expression_level) FROM [isaphan@washington.edu].experiment","id":"21423"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:25:47 PM","query":"SELECT COUNT(DISTINCT id) FROM [isaphan@washington.edu].experiment  SELECT experiment_type FROM [isaphan@washington.edu].experiment","id":"21424"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:25:54 PM","query":"SELECT experiment_type FROM [isaphan@washington.edu].experiment","id":"21425"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:29:09 PM","query":"SELECT experiment_type, COUNT(gene) AS gene_count FROM [isaphan@washington.edu].experiment GROUP BY experiment_type;","id":"21427"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:29:43 PM","query":"SELECT COUNT(gene)FROM [isaphan@washington.edu].experiment SELECT experiment_type, COUNT(gene) AS gene_count FROM [isaphan@washington.edu].experiment GROUP BY experiment_type;","id":"21428"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:30:03 PM","query":"SELECT experiment_type, COUNT(gene) AS gene_count FROM [isaphan@washington.edu].experiment GROUP BY experiment_type;","id":"21430"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:30:56 PM","query":"SELECT experiment_type, COUNT(gene) AS gene_count FROM [isaphan@washington.edu].experiment GROUP BY experiment_type HAVING COUNT(gene) > 3","id":"21431"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:31:14 PM","query":"SELECT MAX(expression_level) FROM [isaphan@washington.edu].experiment","id":"21432"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:31:59 PM","query":"SELECT * FROM [isaphan@washington.edu].experiment WHERE gene = 'MCTL3';","id":"21433"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:32:09 PM","query":"SELECT * FROM [isaphan@washington.edu].experiment WHERE gene <> 'MCTL3';","id":"21434"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:32:25 PM","query":"SELECT * FROM [isaphan@washington.edu].experiment WHERE expression_level > 4;","id":"21435"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:34:09 PM","query":"SELECT * FROM [isaphan@washington.edu].experiment WHERE gene LIKE 'MCT%';","id":"21436"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:34:46 PM","query":"SELECT * FROM [isaphan@washington.edu].experiment WHERE gene = 'MCTL3' AND expression_level > 4;","id":"21437"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:34:58 PM","query":"SELECT * FROM [isaphan@washington.edu].experiment WHERE gene = 'MCTL3' OR gene = 'OGEE2';","id":"21438"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:35:09 PM","query":"SELECT * FROM [isaphan@washington.edu].experiment WHERE gene  IN ('MCTL3', 'OGEE2');","id":"21439"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:35:19 PM","query":"SELECT * FROM [isaphan@washington.edu].experiment WHERE gene NOT IN ('MCTL3', 'OGEE2');","id":"21440"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:35:29 PM","query":"SELECT * FROM [isaphan@washington.edu].experiment WHERE experiment_type IS NULL;","id":"21441"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 7:35:37 PM","query":"SELECT * FROM [isaphan@washington.edu].experiment WHERE experiment_type = '';","id":"21442"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 8:44:39 PM","query":"SELECT * FROM [isaphan@washington.edu].experiment WHERE experiment_type = 'microarray';","id":"21443"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 8:45:25 PM","query":"SELECT COUNT(experiment_type) FROM [isaphan@washington.edu].experiment;  SELECT * FROM [isaphan@washington.edu].experiment WHERE experiment_type = 'microarray';","id":"21444"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 8:46:20 PM","query":"SELECT COUNT(experiment_type) FROM [isaphan@washington.edu].experiment;  SELECT * FROM [isaphan@washington.edu].experiment WHERE experiment_type = '';","id":"21446"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 8:46:55 PM","query":"SELECT * FROM [isaphan@washington.edu].experiment WHERE experiment_type = 'microarray';","id":"21448"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 8:51:23 PM","query":"SELECT experiment_type, COUNT(gene)AS gene_count FROM [isaphan@washington.edu].experiment  GROUP BY experiment_type","id":"21451"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 8:51:37 PM","query":"SELECT experiment_type, COUNT(gene) FROM [isaphan@washington.edu].experiment  GROUP BY experiment_type","id":"21452"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 9:44:56 PM","query":"select * from [sophiafy@washington.edu].[table_experiment.csv]","id":"255005"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 9:45:33 PM","query":"SELECT * FROM [sophiafy@washington.edu].[table_experiment.csv]","id":"21453"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 9:46:03 PM","query":"SELECT * FROM [table_experiment.csv]","id":"21457"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 9:46:55 PM","query":"SELECT gene FROM [table_experiment.csv]","id":"21458"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 9:47:05 PM","query":"SELECT*FROM [table_experiment.csv]","id":"21459"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 9:47:18 PM","query":"SELECT*FROM [table_experiment.csv]; SELECT gene FROM [table_experiment.csv];","id":"21460"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 9:48:09 PM","query":"--SELECT*FROM [table_experiment.csv]; SELECT gene, experiment_type FROM [table_experiment.csv];","id":"21461"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 9:48:21 PM","query":"--SELECT*FROM [table_experiment.csv]; SELECT gene, experiment_type AS temp FROM [table_experiment.csv];","id":"21462"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 9:48:30 PM","query":"--SELECT*FROM [table_experiment.csv]; SELECT gene AS temp1, experiment_type AS temp FROM [table_experiment.csv];","id":"21463"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 9:51:29 PM","query":"--SELECT*FROM [table_experiment.csv]; --SELECT gene, experiment_type FROM [table_experiment.csv]; SELECT COUNT(experiment_type)  FROM [table_experiment.csv]","id":"21465"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 9:52:08 PM","query":"--SELECT*FROM [table_experiment.csv]; --SELECT gene, experiment_type FROM [table_experiment.csv]; SELECT COUNT(DISTINCT experiment_type)  FROM [table_experiment.csv]","id":"21467"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 9:53:17 PM","query":"--SELECT*FROM [table_experiment.csv]; --SELECT gene, experiment_type FROM [table_experiment.csv]; SELECT COUNT(gene)  FROM [table_experiment.csv] WHERE experiment_type = 'microarray\"'","id":"21469"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 9:55:17 PM","query":"--SELECT*FROM [table_experiment.csv]; --SELECT gene, experiment_type FROM [table_experiment.csv]; SELECT experiment_type, COUNT(gene) AS gene_count FROM [table_experiment.csv] GROUP BY experiment_type","id":"21474"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 9:55:26 PM","query":"--SELECT*FROM [table_experiment.csv]; --SELECT gene, experiment_type FROM [table_experiment.csv]; SELECT  COUNT(gene) AS gene_count FROM [table_experiment.csv] GROUP BY experiment_type","id":"21475"},{"owner":"sophiafy@washington.edu","date":"3\/7\/2013 9:55:59 PM","query":"--SELECT*FROM [table_experiment.csv]; --SELECT gene, experiment_type FROM [table_experiment.csv]; SELECT COUNT(gene) FROM [table_experiment.csv]","id":"21476"}];