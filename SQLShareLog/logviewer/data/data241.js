data = [{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 10:23:42 PM","query":"select * from [vsailaja@pdx.edu].[table_birds_1.csv]","id":"250930"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 10:32:13 PM","query":"select * from [vsailaja@pdx.edu].[table_do.csv]","id":"251357"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 10:41:06 PM","query":"SELECT Species, Count(*) FROM [vsailaja@pdx.edu].[birds_1.csv] GROUP BY Species","id":"31767"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 10:42:44 PM","query":"SELECT Species, Count(*) FROM [vsailaja@pdx.edu].[birds_1.csv]   where Condition='Dead'   GROUP BY Species","id":"31776"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 11:02:38 PM","query":"SELECT Species, Count(*) as dead_count FROM [vsailaja@pdx.edu].[birds_1.csv]   WHERE Condition='Dead'   GROUP BY Species","id":"31825"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 11:04:35 PM","query":"select species, count(*) as dead_count from [vsailaja@pdx.edu].[birds_1.csv]   where condition='dead'   group by species","id":"252395"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 11:05:47 PM","query":"SELECT Species, Count(*) as total_count FROM [vsailaja@pdx.edu].[birds_1.csv]   GROUP BY Species","id":"31835"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 11:06:53 PM","query":"select species, count(*) as total_count from [vsailaja@pdx.edu].[birds_1.csv]   group by species","id":"252522"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 11:15:14 PM","query":"SELECT *   FROM [vsailaja@pdx.edu].[dead_count_by_bird_species],        [vsailaja@pdx.edu].[count_by_bird_species]","id":"31876"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 11:15:53 PM","query":"SELECT *   FROM [vsailaja@pdx.edu].[dead_count_by_bird_species] as dead,        [vsailaja@pdx.edu].[count_by_bird_species] as total   WHERE dead.Species=total.Species","id":"31878"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 11:16:43 PM","query":"SELECT total.Species, dead_count   FROM [vsailaja@pdx.edu].[dead_count_by_bird_species] as dead,        [vsailaja@pdx.edu].[count_by_bird_species] as total   WHERE dead.Species=total.Species","id":"31881"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 11:17:00 PM","query":"SELECT total.Species, dead_count, total_count   FROM [vsailaja@pdx.edu].[dead_count_by_bird_species] as dead,        [vsailaja@pdx.edu].[count_by_bird_species] as total   WHERE dead.Species=total.Species","id":"31883"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 11:17:47 PM","query":"SELECT total.Species, dead_count\/total_count as frac_dead   FROM [vsailaja@pdx.edu].[dead_count_by_bird_species] as dead,        [vsailaja@pdx.edu].[count_by_bird_species] as total   WHERE dead.Species=total.Species","id":"31884"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 11:27:48 PM","query":"SELECT total.Species, CAST([dead_count] as float)\/CAST([total_count] as float)  as frac_dead   FROM [vsailaja@pdx.edu].[dead_count_by_bird_species] as dead,        [vsailaja@pdx.edu].[count_by_bird_species] as total   WHERE dead.Species=total.Species","id":"31902"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 11:29:08 PM","query":"select total.species, cast([dead_count] as float)\/cast([total_count] as float)  as frac_dead   from [vsailaja@pdx.edu].[dead_count_by_bird_species] as dead,        [vsailaja@pdx.edu].[count_by_bird_species] as total   where dead.species=total.species","id":"252907"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 11:31:25 PM","query":"select * from [vsailaja@pdx.edu].[birds_1.csv]","id":"31913"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 11:33:57 PM","query":"select * from [vsailaja@pdx.edu].[Do.csv]   where [Clements name] != [IOC name]","id":"31918"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 11:35:26 PM","query":"select * from [vsailaja@pdx.edu].[birds_1.csv]   WHERE Species in( select [Clements name] from [vsailaja@pdx.edu].[Do.csv]     where [Clements name] != [IOC name])","id":"31924"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 11:35:56 PM","query":"select * from [vsailaja@pdx.edu].[birds_1.csv]   WHERE Species in( select [IOC name] from [vsailaja@pdx.edu].[Do.csv]     where [Clements name] != [IOC name])","id":"31927"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 11:41:43 PM","query":"select [Clements name] as Species, [IOC name]    from [vsailaja@pdx.edu].[Do.csv]     where [Clements name] != [IOC name]","id":"31949"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 11:42:11 PM","query":"select [Clements name], [IOC name]    from [vsailaja@pdx.edu].[Do.csv]     where [Clements name] != [IOC name]","id":"31951"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 11:43:38 PM","query":"select *    from [vsailaja@pdx.edu].[Do.csv]     where [Clements name] != [IOC name]","id":"31952"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 11:45:16 PM","query":"select *    from [vsailaja@pdx.edu].[do.csv]     where [clements name] != [ioc name]","id":"252999"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 11:52:05 PM","query":"SELECT *   FROM [vsailaja@pdx.edu].[fraction_of_birds_dead_by_species]","id":"31967"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 11:53:01 PM","query":"SELECT *   FROM [vsailaja@pdx.edu].[fraction_of_birds_dead_by_species]    WHERE frac_dead = (SELECT MAX(frac_dead) from [vsailaja@pdx.edu].[fraction_of_birds_dead_by_species])","id":"31973"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 11:55:26 PM","query":"SELECT *   FROM [vsailaja@pdx.edu].[fraction_of_birds_dead_by_species],        [vsailaja@pdx.edu].[birds_different_by_clements_and_ioc_names]   WHERE frac_dead = (SELECT MAX(frac_dead) from [vsailaja@pdx.edu].[fraction_of_birds_dead_by_species])   AND Species = [Clements name]","id":"31983"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 11:56:07 PM","query":"SELECT Species, [IOC name], frac_dead   FROM [vsailaja@pdx.edu].[fraction_of_birds_dead_by_species],        [vsailaja@pdx.edu].[birds_different_by_clements_and_ioc_names]   WHERE frac_dead = (SELECT MAX(frac_dead) from [vsailaja@pdx.edu].[fraction_of_birds_dead_by_species])   AND Species = [Clements name]","id":"31984"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 9:21:57 PM","query":"SELECT * FROM [billhowe].[categorized fat percents]","id":"31636"},{"owner":"vsailaja@pdx.edu","date":"1\/22\/2014 9:26:05 PM","query":"SELECT * FROM [billhowe].[fat_gram_intake_projection]","id":"31650"},{"owner":"vsailaja@pdx.edu","date":"1\/23\/2014 12:03:39 AM","query":"SELECT * FROM [vsailaja@pdx.edu].[table_birds_1.csv]","id":"31999"},{"owner":"vsailaja@pdx.edu","date":"1\/23\/2014 12:09:13 AM","query":"SELECT *    FROM [vsailaja@pdx.edu].[birds_different_by_clements_and_ioc_names]","id":"32011"},{"owner":"vsailaja@pdx.edu","date":"1\/23\/2014 12:09:53 AM","query":"SELECT *    FROM [vsailaja@pdx.edu].[birds_different_by_clements_and_ioc_names],        [vsailaja@pdx.edu].[fraction_of_birds_dead_by_species]   WHERE Species=[Clements name]","id":"32013"},{"owner":"vsailaja@pdx.edu","date":"1\/23\/2014 12:10:19 AM","query":"SELECT [Clements name], [IOC name]   FROM [vsailaja@pdx.edu].[birds_different_by_clements_and_ioc_names],        [vsailaja@pdx.edu].[fraction_of_birds_dead_by_species]   WHERE Species=[Clements name]","id":"32015"},{"owner":"vsailaja@pdx.edu","date":"1\/23\/2014 12:10:29 AM","query":"SELECT [Clements name], [IOC name], frac_dead   FROM [vsailaja@pdx.edu].[birds_different_by_clements_and_ioc_names],        [vsailaja@pdx.edu].[fraction_of_birds_dead_by_species]   WHERE Species=[Clements name]","id":"32016"},{"owner":"vsailaja@pdx.edu","date":"1\/23\/2014 12:11:47 AM","query":"SELECT [Clements name], [IOC name], frac_dead   FROM [vsailaja@pdx.edu].[birds_different_by_clements_and_ioc_names],        [vsailaja@pdx.edu].[fraction_of_birds_dead_by_species]   WHERE Species=[Clements name]   ORDER BY frac_dead desc","id":"32019"},{"owner":"vsailaja@pdx.edu","date":"1\/23\/2014 12:16:05 AM","query":"select [clements name], [ioc name], frac_dead   from [vsailaja@pdx.edu].[birds_different_by_clements_and_ioc_names],        [vsailaja@pdx.edu].[fraction_of_birds_dead_by_species]   where species=[clements name]","id":"253466"}];