data = [{"owner":"walke827@umn.edu","date":"5\/21\/2012 2:41:23 AM","query":"SELECT [Date], TOD, Waypoint, AVG(Range_m) AS Avg_Range_m, MAX(Range_m) AS Max_Range_m, MIN(Range_m) AS Min_Range_m, COUNT(Waypoint) AS Ship_Number   FROM [walke827@umn.edu].[CaliAIS_SQl.txt]   GROUP BY [Date], TOD, Waypoint","id":"8252"},{"owner":"walke827@umn.edu","date":"5\/21\/2012 2:48:14 AM","query":"SELECT [Date], TOD, Waypoint, AVG(Range_m) AS Avg_Range_m, MAX(Range_m)AS Max_Range_m, MIN(Range_m) AS Min_Range_m, COUNT(Waypoint) AS Ship_Number   FROM [walke827@umn.edu].[CaliAIS_SQl.txt]   GROUP BY [Date], TOD, Waypoint","id":"8253"},{"owner":"walke827@umn.edu","date":"5\/23\/2012 2:44:54 AM","query":"SELECT [Date], TOD, Waypoint, AVG(Range_m) AS Avg_Range_m, MAX(Range_m)AS Max_Range_m, MIN(Range_m) AS Min_Range_m, COUNT(Waypoint) AS Ship_Number   FROM [walke827@umn.edu].[CaliAIS_SQl.txt]   GROUP BY [Date], TOD, Waypoint","id":"8331"},{"owner":"walke827@umn.edu","date":"5\/23\/2012 3:50:39 AM","query":"select * from [walke827@umn.edu].[table_caliais_sql.txt]","id":"253912"},{"owner":"walke827@umn.edu","date":"5\/23\/2012 3:50:49 AM","query":"SELECT [Date], TOD, Waypoint, AVG(Range_m) AS Avg_Range_m, MAX(Range_m) AS Max_Range_m, MIN(Range_m) AS Min_Range_m, AVG(SoG_m_hr) AS Avg_SoG_m_hr, MAX(SoG_m_hr) AS Max_SoG_m_hr, MIN(SoG_m_hr) AS Min_SoG_m_hr, COUNT(Waypoint) AS Ship_Number   FROM [walke827@umn.edu].[CaliAIS_SQl.txt]   GROUP BY [Date], TOD, Waypoint","id":"8337"},{"owner":"walke827@umn.edu","date":"5\/23\/2012 3:52:41 AM","query":"select [date], tod, waypoint, rl_db, avg(range_m) as avg_range_m, sum(range_m) as sum_range_m, max(range_m) as max_range_m, min(range_m) as min_range_m, avg(sog_m_hr) as avg_sog_m_hr, max(sog_m_hr) as max_sog_m_hr, min(sog_m_hr) as min_sog_m_hr, count(waypoint) as ship_number   from [walke827@umn.edu].[table_caliais_sql.txt]   group by [date], tod, waypoint, rl_db","id":"253965"},{"owner":"walke827@umn.edu","date":"5\/23\/2012 4:20:38 AM","query":"SELECT [Date], TOD, Waypoint, RL_dB, AVG(Range_m) AS Avg_Range_m, MAX(Range_m) AS Max_Range_m, MIN(Range_m) AS Min_Range_m, AVG(SoG_m_hr) AS Avg_SoG_m_hr, MAX(SoG_m_hr) AS Max_SoG_m_hr, MIN(SoG_m_hr) AS Min_SoG_m_hr, COUNT(Waypoint) AS Ship_Number   FROM [walke827@umn.edu].[table_CaliAIS_SQl.txt]   GROUP BY [Date], TOD, Waypoint, RL_dB","id":"8349"},{"owner":"walke827@umn.edu","date":"5\/24\/2012 10:16:56 PM","query":"SELECT [Date], TOD, Waypoint, RL_dB, AVG(Range_m) AS Avg_Range_m, SUM(Range_m) AS Sum_Range_m, MAX(Range_m) AS Max_Range_m, MIN(Range_m) AS Min_Range_m, AVG(SoG_m_hr) AS Avg_SoG_m_hr, MAX(SoG_m_hr) AS Max_SoG_m_hr, MIN(SoG_m_hr) AS Min_SoG_m_hr, COUNT(Waypoint) AS Ship_Number   FROM [walke827@umn.edu].[table_CaliAIS_SQl.txt]   GROUP BY [Date], TOD, Waypoint, RL_dB","id":"8427"},{"owner":"walke827@umn.edu","date":"5\/24\/2012 10:52:32 PM","query":"SELECT * FROM [walke827@umn.edu].[table_CaliAIS_SQl.txt]   WHERE Range_m < 1000","id":"8430"},{"owner":"walke827@umn.edu","date":"5\/24\/2012 10:52:45 PM","query":"SELECT * FROM [walke827@umn.edu].[table_CaliAIS_SQl.txt]   WHERE Range_m < 2000","id":"8431"},{"owner":"walke827@umn.edu","date":"5\/24\/2012 10:54:13 PM","query":"select * from [walke827@umn.edu].[table_caliais_sql.txt]   where range_m < 5000","id":"254618"},{"owner":"walke827@umn.edu","date":"5\/24\/2012 10:55:36 PM","query":"SELECT [Date], TOD, Waypoint, RL_dB, AVG(Range_m) AS Avg_Range_m, SUM(Range_m) AS Sum_Range_m, MAX(Range_m) AS Max_Range_m, MIN(Range_m) AS Min_Range_m, AVG(SoG_m_hr) AS Avg_SoG_m_hr, MAX(SoG_m_hr) AS Max_SoG_m_hr, MIN(SoG_m_hr) AS Min_SoG_m_hr, COUNT(Waypoint) AS Ship_Number   FROM [walke827@umn.edu].[CaliAIS_SQl_lessthan5.txt]   GROUP BY [Date], TOD, Waypoint, RL_dB","id":"8433"},{"owner":"walke827@umn.edu","date":"5\/24\/2012 10:56:52 PM","query":"SELECT [Date], RL_dB, AVG(Range_m) AS Avg_Range_m, SUM(Range_m) AS Sum_Range_m, MAX(Range_m) AS Max_Range_m, MIN(Range_m) AS Min_Range_m, AVG(SoG_m_hr) AS Avg_SoG_m_hr, MAX(SoG_m_hr) AS Max_SoG_m_hr, MIN(SoG_m_hr) AS Min_SoG_m_hr, COUNT(Waypoint) AS Ship_Number   FROM [walke827@umn.edu].[CaliAIS_SQl_lessthan5.txt]   GROUP BY [Date], RL_dB","id":"8434"},{"owner":"walke827@umn.edu","date":"5\/24\/2012 10:59:05 PM","query":"select [date], rl_db, avg(range_m) as avg_range_m, sum(range_m) as sum_range_m, max(range_m) as max_range_m, min(range_m) as min_range_m, avg(sog_m_hr) as avg_sog_m_hr, max(sog_m_hr) as max_sog_m_hr, min(sog_m_hr) as min_sog_m_hr, count(waypoint) as ship_number   from [walke827@umn.edu].[caliais_sql_lessthan5.txt]   group by [date], rl_db","id":"254666"},{"owner":"walke827@umn.edu","date":"5\/25\/2012 5:19:45 PM","query":"SELECT * FROM [walke827@umn.edu].[table_CaliAIS_SQl.txt]   WHERE Range_m < 5000","id":"8437"},{"owner":"walke827@umn.edu","date":"5\/25\/2012 5:19:57 PM","query":"SELECT * FROM [walke827@umn.edu].[table_CaliAIS_SQl.txt]   WHERE Range_m < 10000","id":"8438"},{"owner":"walke827@umn.edu","date":"5\/25\/2012 5:20:18 PM","query":"select * from [walke827@umn.edu].[table_caliais_sql.txt]   where range_m < 10000","id":"254713"},{"owner":"walke827@umn.edu","date":"5\/25\/2012 5:24:06 PM","query":"SELECT * FROM [walke827@umn.edu].[CaliAIS_SQl_lessthan_10000]SELECT [Date], RL_dB, AVG(Range_m) AS Avg_Range_m, SUM(Range_m) AS Sum_Range_m, MAX(Range_m) AS Max_Range_m, MIN(Range_m) AS Min_Range_m, AVG(SoG_m_hr) AS Avg_SoG_m_hr, MAX(SoG_m_hr) AS Max_SoG_m_hr, MIN(SoG_m_hr) AS Min_SoG_m_hr, COUNT(Waypoint) AS Ship_Number   FROM [walke827@umn.edu].[CaliAIS_SQl_lessthan5.txt]   GROUP BY [Date], RL_dB","id":"8439"},{"owner":"walke827@umn.edu","date":"5\/25\/2012 5:42:59 PM","query":"SELECT * FROM [walke827@umn.edu].[table_CaliAIS_SQl.txt]   WHERE Range_m < 8000","id":"8446"},{"owner":"walke827@umn.edu","date":"5\/25\/2012 5:43:53 PM","query":"select * from [walke827@umn.edu].[table_caliais_sql.txt]   where range_m < 8000","id":"254842"},{"owner":"walke827@umn.edu","date":"5\/25\/2012 5:46:42 PM","query":"SELECT [Date], RL_dB, AVG(Range_m) AS Avg_Range_m, SUM(Range_m) AS Sum_Range_m, MAX(Range_m) AS Max_Range_m, MIN(Range_m) AS Min_Range_m, AVG(SoG_m_hr) AS Avg_SoG_m_hr, MAX(SoG_m_hr) AS Max_SoG_m_hr, MIN(SoG_m_hr) AS Min_SoG_m_hr, COUNT(Waypoint) AS Ship_Number   FROM [walke827@umn.edu].[Cali_AIS_SQl_lessthan_8000]   GROUP BY [Date], RL_dB","id":"8449"},{"owner":"walke827@umn.edu","date":"5\/25\/2012 5:47:24 PM","query":"select [date], rl_db, avg(range_m) as avg_range_m, sum(range_m) as sum_range_m, max(range_m) as max_range_m, min(range_m) as min_range_m, avg(sog_m_hr) as avg_sog_m_hr, max(sog_m_hr) as max_sog_m_hr, min(sog_m_hr) as min_sog_m_hr, count(waypoint) as ship_number   from [walke827@umn.edu].[cali_ais_sql_lessthan_8000]   group by [date], rl_db","id":"254889"},{"owner":"walke827@umn.edu","date":"5\/25\/2012 7:07:19 PM","query":"SELECT * FROM [walke827@umn.edu].[table_CaliAIS_SQl.txt]   WHERE Range_m < 6500","id":"8458"},{"owner":"walke827@umn.edu","date":"5\/25\/2012 7:07:32 PM","query":"select * from [walke827@umn.edu].[table_caliais_sql.txt]   where range_m < 6500","id":"255177"},{"owner":"walke827@umn.edu","date":"5\/25\/2012 7:08:49 PM","query":"select * from [walke827@umn.edu].[table_caliais_sql.txt]   where range_m < 6500","id":"255219"},{"owner":"walke827@umn.edu","date":"5\/25\/2012 7:09:34 PM","query":"SELECT [Date], RL_dB, AVG(Range_m) AS Avg_Range_m, SUM(Range_m) AS Sum_Range_m, MAX(Range_m) AS Max_Range_m, MIN(Range_m) AS Min_Range_m, AVG(SoG_m_hr) AS Avg_SoG_m_hr, MAX(SoG_m_hr) AS Max_SoG_m_hr, MIN(SoG_m_hr) AS Min_SoG_m_hr, COUNT(Waypoint) AS Ship_Number   FROM [walke827@umn.edu].[Cali_AIS_SQl_lessthan_6500]   GROUP BY [Date], RL_dB","id":"8460"},{"owner":"walke827@umn.edu","date":"5\/25\/2012 7:10:12 PM","query":"select [date], rl_db, avg(range_m) as avg_range_m, sum(range_m) as sum_range_m, max(range_m) as max_range_m, min(range_m) as min_range_m, avg(sog_m_hr) as avg_sog_m_hr, max(sog_m_hr) as max_sog_m_hr, min(sog_m_hr) as min_sog_m_hr, count(waypoint) as ship_number   from [walke827@umn.edu].[cali_ais_sql_lessthan_6500]   group by [date], rl_db","id":"255263"},{"owner":"walke827@umn.edu","date":"5\/25\/2012 8:33:58 PM","query":"select [date], rl_db, avg(range_m) as avg_range_m, sum(range_m) as sum_range_m, max(range_m) as max_range_m, min(range_m) as min_range_m, avg(sog_m_hr) as avg_sog_m_hr, max(sog_m_hr) as max_sog_m_hr, min(sog_m_hr) as min_sog_m_hr, count(waypoint) as ship_number   from [walke827@umn.edu].[cali_ais_sql_lessthan_6500]   group by [date], rl_db","id":"255310"},{"owner":"walke827@umn.edu","date":"5\/25\/2012 8:52:40 PM","query":"SELECT * FROM [walke827@umn.edu].[table_CaliAIS_SQl.txt]   WHERE Range_m < 20000","id":"8463"},{"owner":"walke827@umn.edu","date":"5\/25\/2012 8:53:09 PM","query":"select * from [walke827@umn.edu].[table_caliais_sql.txt]   where range_m < 20000","id":"255354"},{"owner":"walke827@umn.edu","date":"5\/25\/2012 8:54:23 PM","query":"SELECT [Date], RL_dB, AVG(Range_m) AS Avg_Range_m, SUM(Range_m) AS Sum_Range_m, MAX(Range_m) AS Max_Range_m, MIN(Range_m) AS Min_Range_m, AVG(SoG_m_hr) AS Avg_SoG_m_hr, MAX(SoG_m_hr) AS Max_SoG_m_hr, MIN(SoG_m_hr) AS Min_SoG_m_hr, COUNT(Waypoint) AS Ship_Number   FROM [walke827@umn.edu].[CaliAIS_SQl_lessthan_20]   GROUP BY [Date], RL_dB","id":"8464"},{"owner":"walke827@umn.edu","date":"5\/25\/2012 8:54:43 PM","query":"select [date], rl_db, avg(range_m) as avg_range_m, sum(range_m) as sum_range_m, max(range_m) as max_range_m, min(range_m) as min_range_m, avg(sog_m_hr) as avg_sog_m_hr, max(sog_m_hr) as max_sog_m_hr, min(sog_m_hr) as min_sog_m_hr, count(waypoint) as ship_number   from [walke827@umn.edu].[caliais_sql_lessthan_20]   group by [date], rl_db","id":"255407"},{"owner":"walke827@umn.edu","date":"5\/29\/2012 5:03:06 PM","query":"select * from [walke827@umn.edu].[table_master_ais.txt]","id":"256045"},{"owner":"walke827@umn.edu","date":"5\/29\/2012 5:06:25 PM","query":"SELECT month,day,[year],hour,minute, AVG(Range_m) AS Avg_Range_m, SUM(Range_m) AS Sum_Range_m, MAX(Range_m) AS Max_Range_m, MIN(Range_m) AS Min_Range_m, AVG(SoG_m_hr) AS Avg_SoG_m_hr, MAX(SoG_m_hr) AS Max_SoG_m_hr, MIN(SoG_m_hr) AS Min_SoG_m_hr, COUNT(minute) AS Ship_Number   FROM [walke827@umn.edu].[MASTER_AIS.txt]   GROUP BY month,day,[year], hour, minute","id":"8472"},{"owner":"walke827@umn.edu","date":"5\/29\/2012 5:07:40 PM","query":"select month,day,[year],hour, avg(range_m) as avg_range_m, sum(range_m) as sum_range_m, max(range_m) as max_range_m, min(range_m) as min_range_m, avg(sog_m_hr) as avg_sog_m_hr, max(sog_m_hr) as max_sog_m_hr, min(sog_m_hr) as min_sog_m_hr, count(minute) as ship_number   from [walke827@umn.edu].[master_ais.txt]   group by month,day,[year], hour","id":"256092"},{"owner":"walke827@umn.edu","date":"5\/29\/2012 5:12:02 PM","query":"select * from [walke827@umn.edu].[table_master_clicks_minute.txt]","id":"256191"},{"owner":"walke827@umn.edu","date":"5\/29\/2012 5:45:59 PM","query":"SELECT month,day,[year],hour, AVG(Range_m) AS Avg_Range_m, SUM(Range_m) AS Sum_Range_m, MAX(Range_m) AS Max_Range_m, MIN(Range_m) AS Min_Range_m, AVG(SoG_m_hr) AS Avg_SoG_m_hr, MAX(SoG_m_hr) AS Max_SoG_m_hr, MIN(SoG_m_hr) AS Min_SoG_m_hr, COUNT(minute) AS Ship_Number   FROM [walke827@umn.edu].[MASTER_AIS.txt]   GROUP BY month,day,[year], hour","id":"8487"},{"owner":"walke827@umn.edu","date":"5\/29\/2012 5:48:03 PM","query":"SELECT month, day, [year], hour, Avg(clicks_minute) AS Avg_clicks_minute   FROM [walke827@umn.edu].[Master_Clicks_Minute.txt]   GROUP BY month, day, [year], hour","id":"8488"},{"owner":"walke827@umn.edu","date":"5\/29\/2012 5:48:21 PM","query":"select month, day, [year], hour, avg(clicks_minute) as avg_clicks_minute   from [walke827@umn.edu].[master_clicks_minute.txt]   group by month, day, [year], hour","id":"256242"},{"owner":"walke827@umn.edu","date":"5\/30\/2012 3:52:32 AM","query":"SELECT month, day, [year], hour, minutes, half_hour_periods, Avg(clicks_minute) AS Avg_clicks_minute  FROM [walke827@umn.edu].[30_minute_Master_clicks.txt]   GROUP BY month, day, [year], hour, minutes, half_hour_periods","id":"8491"},{"owner":"walke827@umn.edu","date":"5\/30\/2012 3:55:46 AM","query":"SELECT month,day,[year],hour, minute, AVG(Range_m) AS Avg_Range_m, SUM(Range_m) AS Sum_Range_m, MAX(Range_m) AS Max_Range_m, MIN(Range_m) AS Min_Range_m, AVG(SoG_m_hr) AS Avg_SoG_m_hr, MAX(SoG_m_hr) AS Max_SoG_m_hr, MIN(SoG_m_hr) AS Min_SoG_m_hr, COUNT(minute) AS Ship_Number   FROM [walke827@umn.edu].[MASTER_AIS.txt]   GROUP BY month,day,[year], hour, minute","id":"8493"},{"owner":"walke827@umn.edu","date":"5\/30\/2012 4:03:10 AM","query":"SELECT month,day,[year],hour, minutes, half_hour_periods, AVG(clicks_minute)   FROM [walke827@umn.edu].[30_minute_Master_clicks.txt]   GROUP BY month,day,[year], hour, minutes, half_hour_periods","id":"8497"},{"owner":"walke827@umn.edu","date":"5\/30\/2012 4:03:31 AM","query":"SELECT month,day,[year],hour, minutes, half_hour_periods, AVG(clicks_minute)   FROM [walke827@umn.edu].[30_minute_Master_clicks.txt]   GROUP BY month, day,[year], hour, minutes, half_hour_periods","id":"8498"},{"owner":"walke827@umn.edu","date":"5\/30\/2012 4:04:15 AM","query":"SELECT month,day,[year],hour, half_hour_periods, AVG(clicks_minute) AS Avg_clicks_minute   FROM [walke827@umn.edu].[30_minute_Master_clicks.txt]   GROUP BY month, day,[year], hour, half_hour_periods","id":"8499"},{"owner":"walke827@umn.edu","date":"5\/30\/2012 4:09:07 AM","query":"SELECT month, day, [year], hour, minutes, half_hour_periods, Avg(clicks_minute) AS Avg_clicks_minute  FROM [walke827@umn.edu].[30_minute_Master_clicks.txt]   GROUP BY month, day, [year], hour, minutes, half_hour_periods","id":"8502"},{"owner":"walke827@umn.edu","date":"5\/30\/2012 4:09:23 AM","query":"SELECT month, day, [year], hour, half_hour_periods, Avg(clicks_minute) AS Avg_clicks_minute  FROM [walke827@umn.edu].[30_minute_Master_clicks.txt]   GROUP BY month, day, [year], hour, half_hour_periods","id":"8503"},{"owner":"walke827@umn.edu","date":"5\/30\/2012 4:11:35 AM","query":"SELECT month, day, [year], half_hour_periods, Avg(clicks_minute) AS Avg_clicks_minute  FROM [walke827@umn.edu].[30_minute_Master_clicks.txt]   GROUP BY month, day, [year], half_hour_periods","id":"8504"},{"owner":"walke827@umn.edu","date":"5\/30\/2012 4:46:10 AM","query":"select * from [walke827@umn.edu].[table_sql_master_clicks.txt]","id":"250474"},{"owner":"walke827@umn.edu","date":"5\/30\/2012 4:47:17 AM","query":"SELECT month, day, [year], half_hour_periods, Avg(clicks_minute) AS Avg_clicks_minute     FROM [walke827@umn.edu].[SQL_Master_clicks.txt]   GROUP BY month, day, [year], half_hour_periods","id":"8505"},{"owner":"walke827@umn.edu","date":"5\/30\/2012 4:47:58 AM","query":"select month, day, [year], half_hour_periods, avg(clicks_minute) as avg_clicks_minute     from [walke827@umn.edu].[sql_master_clicks.txt]   group by month, day, [year], half_hour_periods","id":"250524"},{"owner":"walke827@umn.edu","date":"5\/30\/2012 4:55:49 AM","query":"SELECT month, day, [year], hour, minute, AVG(Range_m) AS Avg_Range_m, SUM(Range_m) AS Sum_Range_m, MAX(Range_m) AS Max_Range_m, MIN(Range_m) AS Min_Range_m, AVG(SoG_m_hr) AS Avg_SoG_m_hr, MAX(SoG_m_hr) AS Max_SoG_m_hr, MIN(SoG_m_hr) AS Min_SoG_m_hr, COUNT(minute) AS Ship_Number   FROM [walke827@umn.edu].[table_MASTER_AIS.txt]   GROUP BY month, day, [year], hour, minute","id":"8506"},{"owner":"walke827@umn.edu","date":"5\/30\/2012 4:56:39 AM","query":"select month, day, [year], hour, minute, avg(range_m) as avg_range_m, sum(range_m) as sum_range_m, max(range_m) as max_range_m, min(range_m) as min_range_m, avg(sog_m_hr) as avg_sog_m_hr, max(sog_m_hr) as max_sog_m_hr, min(sog_m_hr) as min_sog_m_hr, count(minute) as ship_number   from [walke827@umn.edu].[table_master_ais.txt]   group by month, day, [year], hour, minute","id":"250578"}];