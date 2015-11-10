data = [{"owner":"sophieseptember@gmail.com","date":"10\/15\/2012 11:35:13 PM","query":"select * from [sophieseptember@gmail.com].[table_airtemp.csv]","id":"255908"},{"owner":"sophieseptember@gmail.com","date":"10\/15\/2012 11:39:55 PM","query":"SELECT * FROM [sophieseptember@gmail.com].[AirTemp.csv]","id":"11595"},{"owner":"sophieseptember@gmail.com","date":"10\/15\/2012 11:43:45 PM","query":"SELECT Depth, Air_Temperature FROM [sophieseptember@gmail.com].[table_AirTemp.csv]","id":"11601"},{"owner":"sophieseptember@gmail.com","date":"10\/15\/2012 11:44:22 PM","query":"SELECT Depth, Air_Temperature AS 'Air Temperature' FROM [sophieseptember@gmail.com].[table_AirTemp.csv]","id":"11602"},{"owner":"sophieseptember@gmail.com","date":"10\/15\/2012 11:47:58 PM","query":"SELECT * FROM [sophieseptember@gmail.com].[table_AirTemp.csv] WHERE Air_Temperature<50","id":"11607"},{"owner":"sophieseptember@gmail.com","date":"10\/15\/2012 11:54:10 PM","query":"SELECT SUM (\"Air_Temperature\") AS sum_numeric_variable FROM [sophieseptember@gmail.com].[table_AirTemp.csv] GROUP BY Depth","id":"11615"},{"owner":"sophieseptember@gmail.com","date":"10\/15\/2012 11:54:29 PM","query":"SELECT COUNT (\"Air_Temperature\") AS sum_numeric_variable FROM [sophieseptember@gmail.com].[table_AirTemp.csv] GROUP BY Depth","id":"11616"},{"owner":"sophieseptember@gmail.com","date":"10\/24\/2012 4:45:13 AM","query":"select * from [sophieseptember@gmail.com].[table_rain.csv]","id":"253468"},{"owner":"sophieseptember@gmail.com","date":"10\/24\/2012 4:47:13 AM","query":"select * from [sophieseptember@gmail.com].[table_windspeed.csv]","id":"253704"},{"owner":"sophieseptember@gmail.com","date":"10\/24\/2012 4:47:52 AM","query":"SELECT * FROM [sophieseptember@gmail.com].[table_Rain.csv]","id":"14213"},{"owner":"sophieseptember@gmail.com","date":"10\/24\/2012 4:48:02 AM","query":"SELECT * FROM [sophieseptember@gmail.com].[table_Windspeed.csv]","id":"14214"},{"owner":"sophieseptember@gmail.com","date":"10\/24\/2012 4:54:19 AM","query":"select * from [sophieseptember@gmail.com].[table_Windspeed.csv] x join [sophieseptember@gmail.com].[table_Rain.csv] y on x.\"Date_and_Time\"=y.\"Date_and_Time\"","id":"14231"},{"owner":"sophieseptember@gmail.com","date":"10\/24\/2012 4:54:43 AM","query":"select * from [sophieseptember@gmail.com].[table_Windspeed.csv]x join [sophieseptember@gmail.com].[table_Rain.csv] y on x.\"Date_and_Time\"=y.\"Date_and_Time\"","id":"14232"},{"owner":"sophieseptember@gmail.com","date":"10\/24\/2012 5:04:38 AM","query":"select * from [sophieseptember@gmail.com].[table_Windspeed.csv] x join [sophieseptember@gmail.com].[table_Rain.csv] y on x.\"Date_and_Time\"=y.\"Date_and_Time\"","id":"14260"},{"owner":"sophieseptember@gmail.com","date":"10\/24\/2012 5:04:47 AM","query":"select * from [sophieseptember@gmail.com].[table_windspeed.csv] x join [sophieseptember@gmail.com].[table_rain.csv] y on x.\"date_and_time\"=y.\"date_and_time\"","id":"254022"},{"owner":"sophieseptember@gmail.com","date":"10\/24\/2012 5:05:57 AM","query":"select * from Combined","id":"14269"},{"owner":"sophieseptember@gmail.com","date":"10\/24\/2012 5:07:10 AM","query":"select count (\"precipitation\") as precipitation_count from Combined","id":"14271"},{"owner":"sophieseptember@gmail.com","date":"10\/24\/2012 5:09:22 AM","query":"select count (\"Date_and_Time\") as DateAndTime_count from Combined","id":"14275"},{"owner":"sophieseptember@gmail.com","date":"10\/24\/2012 5:10:48 AM","query":"select count (\"Wind_speed\") as DateAndTime_count from Combined","id":"14277"},{"owner":"sophieseptember@gmail.com","date":"10\/24\/2012 5:11:55 AM","query":"select count (\"Rain_rate\") as rainrate_count from Combined","id":"14280"},{"owner":"sophieseptember@gmail.com","date":"10\/24\/2012 5:26:50 AM","query":"select count (\"precipitation\") as precipitation_count from Combined where \"precipitation\" like 'no'","id":"14296"},{"owner":"sophieseptember@gmail.com","date":"10\/24\/2012 5:28:08 AM","query":"select count (\"precipitation\") as precipitation_count from Combined where \"precipitation\" like 'yes'","id":"14298"},{"owner":"sophieseptember@gmail.com","date":"10\/24\/2012 5:35:38 AM","query":"select * from Combined where \"precipitation\" like 'yes'","id":"14314"},{"owner":"sophieseptember@gmail.com","date":"10\/24\/2012 5:38:38 AM","query":"select avg (\"Wind_speed\") as wind_avg from Combined where \"precipitation\" like 'yes'","id":"14322"},{"owner":"sophieseptember@gmail.com","date":"10\/24\/2012 5:40:22 AM","query":"select avg (\"Rain_rate\") as rain_avg from Combined where \"precipitation\" like 'yes'","id":"14326"},{"owner":"sophieseptember@gmail.com","date":"10\/24\/2012 5:56:04 AM","query":"select (\"Rain_rate\") as rain_avg from Combined where \"precipitation\" like 'yes'","id":"14355"},{"owner":"sophieseptember@gmail.com","date":"10\/24\/2012 5:56:39 AM","query":"select (\"Rain_rate\") as rain_yes from Combined where \"precipitation\" like 'yes'","id":"14357"},{"owner":"sophieseptember@gmail.com","date":"10\/24\/2012 5:56:58 AM","query":"select (\"rain_rate\") as rain_yes from combined where \"precipitation\" like 'yes'","id":"254251"},{"owner":"sophieseptember@gmail.com","date":"10\/24\/2012 5:57:57 AM","query":"select (\"Rain_rate\") as rain_yes from Combined where \"precipitation\" like 'yes'","id":"14358"},{"owner":"sophieseptember@gmail.com","date":"10\/24\/2012 5:58:54 AM","query":"select (\"Rain_rate\") as rain_no from Combined where \"precipitation\" like 'no'","id":"14359"},{"owner":"sophieseptember@gmail.com","date":"10\/24\/2012 6:00:16 AM","query":"select (\"Wind_speed\") as wind_no from Combined where \"precipitation\" like 'no'","id":"14360"}];