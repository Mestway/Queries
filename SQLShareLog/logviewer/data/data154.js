data = [{"owner":"maier@pdx.edu","date":"2\/10\/2014 5:17:12 PM","query":"SELECT * FROM [billhowe].[categorized_fat_with_calories]SELECT Date, [Total Calories], [Total Fat] FROM [billhowe].[categorized_fat_with_calories]   WHERE seafood_calories > nut_calories   AND vegetable_calories > chocolate_calories","id":"35816"},{"owner":"maier@pdx.edu","date":"2\/10\/2014 5:27:08 PM","query":"SELECT [Date]   , [Total Fat]   FROM [billhowe].[total_fat_6_month_projection] now   where [Date] > '8\/16\/2011'","id":"35819"},{"owner":"maier@pdx.edu","date":"2\/10\/2014 5:28:21 PM","query":"SELECT [Date]   , [Total Fat], (   SELECT avg([Total Fat])    FROM [billhowe].[total_fat_6_month_projection] past   WHERE past.[Date] < cur.[Date] ) as running_average_fat_grams   FROM [billhowe].[total_fat_6_month_projection] cur   where [Date] > '8\/16\/2011'","id":"35820"},{"owner":"maier@pdx.edu","date":"2\/10\/2014 5:29:13 PM","query":"SELECT [Date]   , [Total Fat]   FROM [billhowe].[total_fat_6_month_projection] cur   where [Date] > '8\/16\/2011'","id":"35821"},{"owner":"maier@pdx.edu","date":"2\/10\/2014 5:31:46 PM","query":"SELECT [Date], [Total Fat]   FROM [billhowe].[total_fat_6_month_projection] cur   where [Total Fat] > 69.44 and [Total Fat] < 69.45","id":"35822"},{"owner":"maier@pdx.edu","date":"2\/10\/2014 5:35:14 PM","query":"SELECT [Date]   , [Total Fat], (   SELECT count([Total Fat])    FROM [billhowe].[total_fat_6_month_projection] past   WHERE past.[Date] < now.[Date] ) as running_average_fat_grams   FROM [billhowe].[total_fat_6_month_projection] now   where [Date] > '8\/16\/2011'","id":"35823"},{"owner":"maier@pdx.edu","date":"2\/10\/2014 5:36:42 PM","query":"SELECT [Date]   , [Total Fat], (     SELECT sum([Total Fat])   FROM [billhowe].[total_fat_6_month_projection] past   WHERE past.[Date] < now.[Date] ) as running_average_fat_grams   FROM [billhowe].[total_fat_6_month_projection] now   where [Date] > '8\/16\/2011'","id":"35824"},{"owner":"maier@pdx.edu","date":"2\/12\/2014 5:59:02 AM","query":"SELECT * FROM [tim.f.reilly@gmail.com].[Quinton Hoover Card Prices]   ORDER BY FPrice DESC","id":"35987"},{"owner":"maier@pdx.edu","date":"2\/12\/2014 6:12:19 AM","query":"SELECT   AVG(b.price) as average_2013_price   FROM (select price         from[emumm@pdx.edu].[00396_200303_transactions.csv]         where price > 0) as b","id":"35988"},{"owner":"maier@pdx.edu","date":"2\/18\/2014 12:10:30 AM","query":"(SELECT name, mfr   FROM [purva@pdx.edu].[cereals_nutrient_stats_1.csv]) EXCEPT   (SELECT name, mfr FROM [purva@pdx.edu].[Derived_Cereals_Nutrients])","id":"36095"},{"owner":"maier@pdx.edu","date":"2\/18\/2014 12:11:54 AM","query":"SELECT * FROM [purva@pdx.edu].[cereals_3.csv]   WHERE mfr='Dates'","id":"36097"},{"owner":"maier@pdx.edu","date":"2\/20\/2014 12:02:36 AM","query":"SELECT DISTINCT VE_TOTAL FROM [anknaidu@pdx.edu].[Accident_data_2clusters.csv]","id":"36124"},{"owner":"maier@pdx.edu","date":"2\/20\/2014 12:04:30 AM","query":"SELECT COUNT(*) FROM [anknaidu@pdx.edu].[Accident_data_2clusters.csv]   WHERE VE_TOTAL > 2","id":"36125"}];