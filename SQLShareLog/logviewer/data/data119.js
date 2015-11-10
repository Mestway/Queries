data = [{"owner":"jeff.otis@gmail.com","date":"1\/16\/2014 2:10:29 AM","query":"SELECT Date, [Total Calories], [Total Fat] FROM [billhowe].[categorized_fat_with_calories]   WHERE seafood_calories > nut_calories   AND vegetable_calories > chocolate_calories","id":"30129"},{"owner":"jeff.otis@gmail.com","date":"1\/22\/2014 12:33:25 AM","query":"select * from [jeff.otis@gmail.com].[table_marst_codes.txt]","id":"254416"},{"owner":"jeff.otis@gmail.com","date":"1\/22\/2014 12:36:01 AM","query":"select * from [jeff.otis@gmail.com].[table_census_marriage.csv]","id":"254506"},{"owner":"jeff.otis@gmail.com","date":"1\/22\/2014 12:37:25 AM","query":"SELECT *       FROM [jeff.otis@gmail.com].[census_marriage.csv]   join [jeff.otis@gmail.com].[table_marst_codes.txt]   on marst = Column1","id":"30949"},{"owner":"jeff.otis@gmail.com","date":"1\/22\/2014 12:37:50 AM","query":"SELECT [jeff.otis@gmail.com].[census_marriage.csv].*       FROM [jeff.otis@gmail.com].[census_marriage.csv]   join [jeff.otis@gmail.com].[table_marst_codes.txt]   on marst = Column1","id":"30950"},{"owner":"jeff.otis@gmail.com","date":"1\/22\/2014 12:38:29 AM","query":"select year, column2 as [maritalstatus], sum(people) [sumofpeople]      from [jeff.otis@gmail.com].[census_marriage.csv]   join [jeff.otis@gmail.com].[table_marst_codes.txt]   on marst = column1   group by year, column2","id":"254552"},{"owner":"jeff.otis@gmail.com","date":"1\/22\/2014 12:43:35 AM","query":"SELECT count(distinct year)   FROM [jeff.otis@gmail.com].[census_marriage.csv]   group by year","id":"30952"},{"owner":"jeff.otis@gmail.com","date":"1\/22\/2014 12:43:44 AM","query":"SELECT count(distinct year)   FROM [jeff.otis@gmail.com].[census_marriage.csv]","id":"30953"},{"owner":"jeff.otis@gmail.com","date":"1\/22\/2014 12:45:07 AM","query":"SELECT count(distinct year) [CensusCount]   FROM [jeff.otis@gmail.com].[census_marriage.csv]","id":"30954"},{"owner":"jeff.otis@gmail.com","date":"1\/22\/2014 12:45:19 AM","query":"select count(distinct year) [censuscount]   from [jeff.otis@gmail.com].[census_marriage.csv]","id":"254591"},{"owner":"jeff.otis@gmail.com","date":"1\/22\/2014 12:47:27 AM","query":"SELECT year, sum(people)   FROM [jeff.otis@gmail.com].[census_marriage.csv] group by year","id":"30955"},{"owner":"jeff.otis@gmail.com","date":"1\/22\/2014 12:47:45 AM","query":"SELECT year, sum(people) [sumOfPeople]   FROM [jeff.otis@gmail.com].[census_marriage.csv] group by year","id":"30956"},{"owner":"jeff.otis@gmail.com","date":"1\/22\/2014 12:48:22 AM","query":"select year, sum(people) [sumofpeople]   from [jeff.otis@gmail.com].[census_marriage.csv] group by year","id":"254643"},{"owner":"jeff.otis@gmail.com","date":"1\/22\/2014 12:49:00 AM","query":"SELECT *   FROM [jeff.otis@gmail.com].[census_marriage.csv]","id":"30958"},{"owner":"jeff.otis@gmail.com","date":"1\/22\/2014 12:49:25 AM","query":"SELECT year,age, sum(people)   FROM [jeff.otis@gmail.com].[census_marriage.csv]   group by year, age","id":"30959"},{"owner":"jeff.otis@gmail.com","date":"1\/22\/2014 12:49:43 AM","query":"SELECT year,age, sum(people)   FROM [jeff.otis@gmail.com].[census_marriage.csv]   group by year, age    order by year, age","id":"30960"},{"owner":"jeff.otis@gmail.com","date":"1\/22\/2014 12:50:02 AM","query":"SELECT year,age, sum(people) [sumOfPeople]   FROM [jeff.otis@gmail.com].[census_marriage.csv]   group by year, age    order by year, age","id":"30961"},{"owner":"jeff.otis@gmail.com","date":"1\/22\/2014 12:50:18 AM","query":"select year, age, sum(people)[sumofpeople]      from [jeff.otis@gmail.com].[census_marriage.csv]    group by year, age","id":"254686"},{"owner":"jeff.otis@gmail.com","date":"1\/22\/2014 12:55:10 AM","query":"SELECT *      FROM [jeff.otis@gmail.com].[census_marriage.csv]   join [jeff.otis@gmail.com].[table_marst_codes.txt]   on marst = Column1","id":"30962"},{"owner":"jeff.otis@gmail.com","date":"1\/22\/2014 12:55:33 AM","query":"SELECT year, Column2, sum(people)      FROM [jeff.otis@gmail.com].[census_marriage.csv]   join [jeff.otis@gmail.com].[table_marst_codes.txt]   on marst = Column1      group by year, Column2","id":"30963"},{"owner":"jeff.otis@gmail.com","date":"1\/22\/2014 12:55:48 AM","query":"SELECT year, Column2, sum(people)[sumOfPeople]      FROM [jeff.otis@gmail.com].[census_marriage.csv]   join [jeff.otis@gmail.com].[table_marst_codes.txt]   on marst = Column1      group by year, Column2","id":"30964"},{"owner":"jeff.otis@gmail.com","date":"1\/22\/2014 12:55:56 AM","query":"SELECT year, Column2, sum(people)[sumOfPeople]      FROM [jeff.otis@gmail.com].[census_marriage.csv]   join [jeff.otis@gmail.com].[table_marst_codes.txt]   on marst = Column1      group by year, Column2 order by year","id":"30965"},{"owner":"jeff.otis@gmail.com","date":"1\/22\/2014 12:56:53 AM","query":"SELECT year, Column2, sum(people) [SumOfPeople]      FROM [jeff.otis@gmail.com].[census_marriage.csv]   join [jeff.otis@gmail.com].[table_marst_codes.txt]   on marst = Column1   group by year, Column2","id":"30967"},{"owner":"jeff.otis@gmail.com","date":"1\/22\/2014 12:57:06 AM","query":"SELECT year, Column2, sum(people) [SumOfPeople]      FROM [jeff.otis@gmail.com].[census_marriage.csv]   join [jeff.otis@gmail.com].[table_marst_codes.txt]   on marst = Column1   group by year, Column2   Order by year","id":"30968"},{"owner":"jeff.otis@gmail.com","date":"1\/22\/2014 12:57:52 AM","query":"SELECT year, age, sum(people)[sumOfPeople]      FROM [jeff.otis@gmail.com].[census_marriage.csv]    group by year, age order by year","id":"30969"},{"owner":"jeff.otis@gmail.com","date":"1\/22\/2014 3:50:48 PM","query":"SELECT year, Column2 as [MaritalStatus], sum(people) [SumOfPeople]      FROM [jeff.otis@gmail.com].[census_marriage.csv]   join [jeff.otis@gmail.com].[table_marst_codes.txt]   on marst = Column1   group by year, Column2   Order by year","id":"31424"}];