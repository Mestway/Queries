data = [{"owner":"gavinski91@gmail.com","date":"10\/10\/2012 6:20:00 PM","query":"select * from [gavinski91@gmail.com].[table_icm_sequim_h1_chlorophyll_30d_excel.csv]","id":"252635"},{"owner":"gavinski91@gmail.com","date":"10\/12\/2012 5:51:53 PM","query":"select * from [gavinski91@gmail.com].[table_sequim_7d_chlorophyll.csv]","id":"256215"},{"owner":"gavinski91@gmail.com","date":"10\/12\/2012 5:52:56 PM","query":"select * from [gavinski91@gmail.com].[table_sequim_7d_oxygen.csv]","id":"256305"},{"owner":"gavinski91@gmail.com","date":"10\/12\/2012 6:09:14 PM","query":"SELECT Date_Time FROM [gavinski91@gmail.com].[table_Sequim_7D_Oxygen.csv]","id":"10258"},{"owner":"gavinski91@gmail.com","date":"10\/12\/2012 6:09:29 PM","query":"SELECT Date_Time AS \"Date & Time\" FROM [gavinski91@gmail.com].[table_Sequim_7D_Oxygen.csv]","id":"10260"},{"owner":"gavinski91@gmail.com","date":"10\/12\/2012 6:09:58 PM","query":"SELECT Date_Time, O2_Concentration FROM [gavinski91@gmail.com].[table_Sequim_7D_Oxygen.csv]","id":"10263"},{"owner":"gavinski91@gmail.com","date":"10\/12\/2012 6:19:36 PM","query":"SELECT Date_Time, O2_Level FROM [gavinski91@gmail.com].[table_Sequim_7D_Oxygen.csv] WHERE O2_Concentration > 6.5","id":"10289"},{"owner":"gavinski91@gmail.com","date":"10\/12\/2012 6:28:12 PM","query":"SELECT SUM(\"O2_Concentration\") AS Sum_O2_Concentration FROM [gavinski91@gmail.com].[table_Sequim_7D_Oxygen.csv]","id":"10314"},{"owner":"gavinski91@gmail.com","date":"10\/12\/2012 6:29:38 PM","query":"SELECT COUNT(\"O2_Concentration\") AS Sum_O2_Concentration FROM [gavinski91@gmail.com].[table_Sequim_7D_Oxygen.csv] WHERE O2_Concentration > 6.5 GROUP BY O2_Concentration","id":"10319"},{"owner":"gavinski91@gmail.com","date":"10\/12\/2012 6:30:09 PM","query":"SELECT COUNT(\"O2_Concentration\") AS Sum_O2_Concentration FROM [gavinski91@gmail.com].[table_Sequim_7D_Oxygen.csv] GROUP BY O2_Level","id":"10320"},{"owner":"gavinski91@gmail.com","date":"10\/12\/2012 6:31:13 PM","query":"SELECT O2_Level AS \"O2 Level\", COUNT(\"O2_Concentration\") AS \"O2 Level Counts\" FROM [gavinski91@gmail.com].[table_Sequim_7D_Oxygen.csv] GROUP BY O2_Level","id":"10323"},{"owner":"gavinski91@gmail.com","date":"10\/12\/2012 6:31:54 PM","query":"SELECT SUM(\"O2_Concentration\") AS Sum_O2_Concentration FROM [gavinski91@gmail.com].[table_Sequim_7D_Oxygen.csv] GROUP BY O2_Level","id":"10325"},{"owner":"gavinski91@gmail.com","date":"10\/23\/2012 10:20:55 PM","query":"SELECT * FROM [gavinski91@gmail.com].[table_Sequim_7D_Chlorophyll.csv] C   JOIN [gavinski91@gmail.com].[table_Sequim_7D_Oxygen.csv] O    ON C.\"Date_Time\" = O.\"Date_Time\"","id":"13206"},{"owner":"gavinski91@gmail.com","date":"10\/23\/2012 10:22:59 PM","query":"SELECT C.\"Date_Time\", C.\"Depth\", C.\"Chlorophyll_Concentration\", C.\"Chlorophyll_Level\", O.\"O2_Concentration\", O.\"O2_Level\"   FROM [gavinski91@gmail.com].[table_Sequim_7D_Chlorophyll.csv] C   JOIN [gavinski91@gmail.com].[table_Sequim_7D_Oxygen.csv] O    ON C.\"Date_Time\" = O.\"Date_Time\"","id":"13207"},{"owner":"gavinski91@gmail.com","date":"10\/23\/2012 10:25:26 PM","query":"SELECT * FROM [gavinski91@gmail.com].[table_Sequim_7D_Chlorophyll.csv] C   JOIN [gavinski91@gmail.com].[table_Sequim_7D_Oxygen.csv] O    ON C.Date_Time = O.Date_Time","id":"13210"},{"owner":"gavinski91@gmail.com","date":"10\/23\/2012 10:25:42 PM","query":"SELECT C.\"Date_Time\", C.\"Depth\", C.\"Chlorophyll_Concentration\", C.\"Chlorophyll_Level\", O.\"O2_Concentration\", O.\"O2_Level\"   FROM [gavinski91@gmail.com].[table_Sequim_7D_Chlorophyll.csv] C   JOIN [gavinski91@gmail.com].[table_Sequim_7D_Oxygen.csv] O    ON C.Date_Time = O.Date_Time","id":"13211"},{"owner":"gavinski91@gmail.com","date":"10\/23\/2012 10:34:20 PM","query":"select c.\"date_time\", c.\"depth\", c.\"chlorophyll_concentration\", c.\"chlorophyll_level\", o.\"o2_concentration\", o.\"o2_level\"   from [gavinski91@gmail.com].[table_sequim_7d_chlorophyll.csv] c   join [gavinski91@gmail.com].[table_sequim_7d_oxygen.csv] o    on c.date_time = o.date_time","id":"252966"},{"owner":"gavinski91@gmail.com","date":"10\/23\/2012 10:37:04 PM","query":"SELECT COUNT(\"Date_Time\") FROM [gavinski91@gmail.com].[Sequim_7D_Chlorophyll_Oxygen]","id":"13233"},{"owner":"gavinski91@gmail.com","date":"10\/23\/2012 10:40:30 PM","query":"SELECT COUNT(\"Date_Time\") AS \"Occurences\" FROM [gavinski91@gmail.com].[Sequim_7D_Chlorophyll_Oxygen]","id":"13251"},{"owner":"gavinski91@gmail.com","date":"10\/23\/2012 10:41:47 PM","query":"SELECT \"Chlorophyll_Level\", COUNT(\"Date_Time\") AS \"Occurences\" FROM [gavinski91@gmail.com].[Sequim_7D_Chlorophyll_Oxygen] GROUP BY \"Chlorophyll_Level\"","id":"13257"},{"owner":"gavinski91@gmail.com","date":"10\/24\/2012 4:34:00 AM","query":"SELECT AVG(\"Chlorophyll_Concentration\") FROM [gavinski91@gmail.com].[Sequim_7D_Chlorophyll_Oxygen] WHERE \"Chlorophyll_Level\"='High'","id":"14166"},{"owner":"gavinski91@gmail.com","date":"10\/24\/2012 4:35:19 AM","query":"SELECT \"Chlorophyll_Level\", AVG(\"Chlorophyll_Concentration\")    AS \"Average\"    FROM [gavinski91@gmail.com].[Sequim_7D_Chlorophyll_Oxygen]    GROUP BY \"Chlorophyll_Level\"","id":"14175"},{"owner":"gavinski91@gmail.com","date":"10\/24\/2012 5:51:29 AM","query":"SELECT COUNT(\"Chlorophyll_Level\")      FROM [gavinski91@gmail.com].[Sequim_7D_Chlorophyll_Oxygen]","id":"14347"},{"owner":"gavinski91@gmail.com","date":"10\/24\/2012 5:52:05 AM","query":"SELECT COUNT(\"Chlorophyll_Level\")      FROM [gavinski91@gmail.com].[Sequim_7D_Chlorophyll_Oxygen]     WHERE \"Chlorophyll_Level\"='High'","id":"14348"},{"owner":"gavinski91@gmail.com","date":"10\/24\/2012 5:52:34 AM","query":"SELECT COUNT(\"Chlorophyll_Level\")      FROM [gavinski91@gmail.com].[Sequim_7D_Chlorophyll_Oxygen]   WHERE \"Chlorophyll_Level\"='High'","id":"14350"},{"owner":"gavinski91@gmail.com","date":"10\/24\/2012 6:02:43 AM","query":"SELECT SUM(\"Chlorophyll_Concentration\")\/COUNT(\"Chlorophyll_Concentration\") FROM [gavinski91@gmail.com].[table_Sequim_7D_Chlorophyll.csv]","id":"14367"},{"owner":"gavinski91@gmail.com","date":"10\/24\/2012 6:03:24 AM","query":"SELECT SUM(\"Chlorophyll_Concentration\")\/COUNT(\"Chlorophyll_Concentration\"), AVG(\"Chlorophyll_Concentration\") FROM [gavinski91@gmail.com].[table_Sequim_7D_Chlorophyll.csv]","id":"14369"},{"owner":"gavinski91@gmail.com","date":"10\/24\/2012 6:04:03 AM","query":"SELECT (SUM(\"Chlorophyll_Concentration\")\/COUNT(\"Chlorophyll_Concentration\"))\/2, AVG(\"Chlorophyll_Concentration\") FROM [gavinski91@gmail.com].[table_Sequim_7D_Chlorophyll.csv]","id":"14370"},{"owner":"gavinski91@gmail.com","date":"10\/24\/2012 6:09:35 AM","query":"SELECT \"Chlorophyll_Concentration\"   FROM [gavinski91@gmail.com].[Sequim_7D_Chlorophyll_Oxygen]   WHERE \"Chlorophyll_Level\"='High'   ORDER BY \"Chlorophyll_Concentration\" ASC","id":"14376"},{"owner":"gavinski91@gmail.com","date":"10\/24\/2012 6:20:55 AM","query":"SELECT \"O2_Level\", AVG(\"O2_Concentration\")   FROM [gavinski91@gmail.com].[Sequim_7D_Chlorophyll_Oxygen]   GROUP BY \"O2_Level\"","id":"14398"}];