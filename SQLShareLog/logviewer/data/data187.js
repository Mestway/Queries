data = [{"owner":"rai.shoma@gmail.com","date":"1\/20\/2014 6:26:44 PM","query":"SELECT * FROM [rai.shoma@gmail.com].[routes.csv] where SrcAirportID = 'PDX' and DestAirportID = 'SFO' and Stops = 0","id":"30720"},{"owner":"rai.shoma@gmail.com","date":"1\/20\/2014 6:27:00 PM","query":"SELECT * FROM [rai.shoma@gmail.com].[routes.csv] where SrcAirportID = 'PDX' and DestAirportID = 'SFO'","id":"30721"},{"owner":"rai.shoma@gmail.com","date":"1\/20\/2014 6:27:09 PM","query":"SELECT * FROM [rai.shoma@gmail.com].[routes.csv] where SrcAirportID = 'PDX'","id":"30722"},{"owner":"rai.shoma@gmail.com","date":"1\/20\/2014 6:27:38 PM","query":"SELECT * FROM [rai.shoma@gmail.com].[routes.csv] where SrcAirport = 'PDX' and DestAirport = 'SFO'","id":"30723"},{"owner":"rai.shoma@gmail.com","date":"1\/20\/2014 6:28:00 PM","query":"SELECT * FROM [rai.shoma@gmail.com].[routes.csv] where SrcAirport = 'PDX' and DestAirport = 'SFO' and Stops=0","id":"30724"},{"owner":"rai.shoma@gmail.com","date":"1\/20\/2014 6:32:53 PM","query":"select * from [rai.shoma@gmail.com].[routes.csv] where srcairport = 'pdx' and destairport = 'sfo' and stops=0","id":"255342"},{"owner":"rai.shoma@gmail.com","date":"1\/20\/2014 6:36:28 PM","query":"SELECT AirlineID,count(AirlineID) FROM [rai.shoma@gmail.com].[table_routes.csv] group by AirlineID","id":"30731"},{"owner":"rai.shoma@gmail.com","date":"1\/20\/2014 6:41:39 PM","query":"select airlineid,count(airlineid) from [rai.shoma@gmail.com].[table_routes.csv] group by airlineid","id":"255447"},{"owner":"rai.shoma@gmail.com","date":"1\/20\/2014 6:44:51 PM","query":"SELECT * FROM [rai.shoma@gmail.com].[table_routes.csv] where SrcAirport = 'PDX'","id":"30732"},{"owner":"rai.shoma@gmail.com","date":"1\/20\/2014 6:46:23 PM","query":"select * from [rai.shoma@gmail.com].[table_routes.csv] where srcairport = 'pdx'","id":"255488"},{"owner":"rai.shoma@gmail.com","date":"1\/20\/2014 6:53:01 AM","query":"select * from [rai.shoma@gmail.com].[table_routes.csv]","id":"255066"},{"owner":"rai.shoma@gmail.com","date":"1\/20\/2014 6:54:05 AM","query":"select * from [rai.shoma@gmail.com].[table_airports.csv]","id":"255164"},{"owner":"rai.shoma@gmail.com","date":"1\/20\/2014 7:03:11 PM","query":"SELECT * FROM [rai.shoma@gmail.com].[airports.csv] where country = 'USA'","id":"30735"},{"owner":"rai.shoma@gmail.com","date":"1\/20\/2014 7:03:35 PM","query":"SELECT * FROM [rai.shoma@gmail.com].[airports.csv] where Country = 'India'","id":"30737"},{"owner":"rai.shoma@gmail.com","date":"1\/20\/2014 7:03:49 PM","query":"SELECT * FROM [rai.shoma@gmail.com].[airports.csv] where Country = 'United States of America'","id":"30738"},{"owner":"rai.shoma@gmail.com","date":"1\/20\/2014 7:04:43 PM","query":"SELECT * FROM [rai.shoma@gmail.com].[airports.csv] where Country = 'United States'","id":"30741"},{"owner":"rai.shoma@gmail.com","date":"1\/20\/2014 7:10:07 PM","query":"delete FROM [rai.shoma@gmail.com].[table_routes.csv] where Equipment = 'EMB'","id":"30754"},{"owner":"rai.shoma@gmail.com","date":"1\/20\/2014 7:10:48 PM","query":"SELECT * FROM [rai.shoma@gmail.com].[table_routes.csv] where equipment = 'EMB'","id":"30756"},{"owner":"rai.shoma@gmail.com","date":"1\/20\/2014 7:14:25 PM","query":"DELETE  FROM [rai.shoma@gmail.com].[routes.csv]   where AirlineID = '\\N'","id":"30766"},{"owner":"rai.shoma@gmail.com","date":"1\/20\/2014 7:17:43 PM","query":"SELECT * FROM [rai.shoma@gmail.com].[airports.csv] a,               [rai.shoma@gmail.com].[routes.csv] r   where a.Country = 'United States'   and a.AirportID = r.SrcAirportID   and r.SrcAirportID not in('\\N')","id":"30768"},{"owner":"rai.shoma@gmail.com","date":"1\/20\/2014 7:20:54 PM","query":"SELECT * FROM [rai.shoma@gmail.com].[airports.csv] a,               [rai.shoma@gmail.com].[routes.csv] r   where a.Country = 'United States'   and a.AirportID = r.SrcAirportID   and r.DestAirportID in(select b.AirportID                         from [rai.shoma@gmail.com].[airports.csv] b     where b.country = 'India')   and r.SrcAirportID NOT IN ('\\N')   and r.DestAirportID NOT IN ('\\N')","id":"30773"},{"owner":"rai.shoma@gmail.com","date":"1\/20\/2014 7:26:50 PM","query":"select * from [rai.shoma@gmail.com].[airports.csv] a,               [rai.shoma@gmail.com].[routes.csv] r   where a.country = 'united states'   and a.airportid = r.srcairportid   and r.destairportid in(select b.airportid                         from [rai.shoma@gmail.com].[airports.csv] b     where b.country = 'india')   and r.srcairportid not in ('\\n')   and r.destairportid not in ('\\n')","id":"255534"}];