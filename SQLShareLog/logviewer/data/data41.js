data = [{"owner":"anilvoid@gmail.com","date":"10\/31\/2012 3:45:12 AM","query":"SELECT term_id, count(*) FROM [billhowe].[reuters_terms.csv] group by term_id","id":"14642"},{"owner":"anilvoid@gmail.com","date":"10\/31\/2012 3:46:04 AM","query":"SELECT term_id, sum(frequency) FROM [billhowe].[reuters_terms.csv] group by term_id","id":"14643"},{"owner":"anilvoid@gmail.com","date":"10\/31\/2012 3:47:59 AM","query":"--SELECT term_id, sum(frequency) FROM [billhowe].[reuters_terms.csv] group by term_id  select term_id,count(term_id) from [billhowe].[reuters_terms.csv] group by term_id","id":"14644"},{"owner":"anilvoid@gmail.com","date":"10\/31\/2012 3:49:44 AM","query":"--SELECT term_id, sum(frequency) FROM [billhowe].[reuters_terms.csv] group by term_id  --select term_id,count(term_id) from [billhowe].[reuters_terms.csv] group by term_id select distinct doc_id from [billhowe].[reuters_terms.csv]","id":"14646"},{"owner":"anilvoid@gmail.com","date":"10\/31\/2012 3:51:05 AM","query":"--SELECT term_id, sum(frequency) FROM [billhowe].[reuters_terms.csv] group by term_id  --select term_id,count(term_id) from [billhowe].[reuters_terms.csv] group by term_id --select count(*) from    (select distinct doc_id from [billhowe].[reuters_terms.csv])","id":"14650"},{"owner":"anilvoid@gmail.com","date":"10\/31\/2012 3:51:26 AM","query":"--SELECT term_id, sum(frequency) FROM [billhowe].[reuters_terms.csv] group by term_id  --select term_id,count(term_id) from [billhowe].[reuters_terms.csv] group by term_id select count(*) from    (select distinct doc_id from [billhowe].[reuters_terms.csv]) A","id":"14652"},{"owner":"anilvoid@gmail.com","date":"10\/31\/2012 3:53:39 AM","query":"--SELECT term_id, sum(frequency) FROM [billhowe].[reuters_terms.csv] group by term_id  -- A table of number of documents   select term_id,count(doc_id) from [billhowe].[reuters_terms.csv] group by term_id -- Total number of documents --select count(*) from (select distinct doc_id from [billhowe].[reuters_terms.csv]) A","id":"14653"},{"owner":"anilvoid@gmail.com","date":"10\/31\/2012 3:54:06 AM","query":"--SELECT term_id, sum(frequency) FROM [billhowe].[reuters_terms.csv] group by term_id  -- A table of number of documents containing the term  --select term_id,count(doc_id) from [billhowe].[reuters_terms.csv] group by term_id -- Total number of documents select count(*) as tot_docs from (select distinct doc_id from [billhowe].[reuters_terms.csv]) A","id":"14654"},{"owner":"anilvoid@gmail.com","date":"11\/4\/2012 4:46:57 AM","query":"--SELECT term_id, doc_id, tfidf FROM [billhowe].[reuters_terms.csv] T1, (select term_id,count(doc_id) from [billhowe].[reuters_terms.csv] group by term_id) T2    -- A table of number of documents containing the term   select term_id,count(doc_id) from [billhowe].[reuters_terms.csv] group by term_id   -- Total number of documents   --select count(*) as tot_docs from (select distinct doc_id from [billhowe].[reuters_terms.csv])","id":"14883"},{"owner":"anilvoid@gmail.com","date":"11\/4\/2012 4:47:41 AM","query":"--SELECT term_id, doc_id, tfidf FROM [billhowe].[reuters_terms.csv] T1, (select term_id,count(doc_id) from [billhowe].[reuters_terms.csv] group by term_id) T2    -- A table of number of documents containing the term   select term_id,count(doc_id)as number_of_docs from [billhowe].[reuters_terms.csv] group by term_id   -- Total number of documents   --select count(*) as tot_docs from (select distinct doc_id from [billhowe].[reuters_terms.csv])","id":"14884"},{"owner":"anilvoid@gmail.com","date":"11\/4\/2012 4:48:44 AM","query":"--SELECT term_id, doc_id, tfidf FROM [billhowe].[reuters_terms.csv] T1, (select term_id,count(doc_id) from [billhowe].[reuters_terms.csv] group by term_id) T2    -- A table of number of documents containing the term   --select term_id,count(doc_id)as number_of_docs from [billhowe].[reuters_terms.csv] group by term_id   -- Total number of documents   select count(*) as tot_docs from (select distinct doc_id from [billhowe].[reuters_terms.csv]) x","id":"14886"},{"owner":"anilvoid@gmail.com","date":"11\/4\/2012 4:56:48 AM","query":"--SELECT * FROM [billhowe].[reuters_terms.csv] select 5\/4","id":"14888"},{"owner":"anilvoid@gmail.com","date":"11\/4\/2012 5:03:49 AM","query":"select term_id,count(doc_id) from [billhowe].[reuters_terms.csv] group by term_id","id":"14891"},{"owner":"anilvoid@gmail.com","date":"11\/4\/2012 5:24:06 AM","query":"--select log((select count(*) from ( select distinct doc_id from [billhowe].[reuters_terms.csv])) \/ (1+ 1)))  select count(*) from ( select distinct doc_id from [billhowe].[reuters_terms.csv]) as x","id":"14914"},{"owner":"anilvoid@gmail.com","date":"11\/4\/2012 5:25:51 AM","query":"--select log((select count(*)\/ (1+ 1) from ( select distinct doc_id from [billhowe].[reuters_terms.csv]) as x) ))  --select count(*) from ( select distinct doc_id from [billhowe].[reuters_terms.csv]) as x  (select count(*)\/ (1+ 1) from ( select distinct doc_id from [billhowe].[reuters_terms.csv]) as x)","id":"14918"},{"owner":"anilvoid@gmail.com","date":"11\/4\/2012 5:26:05 AM","query":"--select log((select count(*)\/ (1+ 1) from ( select distinct doc_id from [billhowe].[reuters_terms.csv]) as x) ))  --select count(*) from ( select distinct doc_id from [billhowe].[reuters_terms.csv]) as x  (select log(count(*)\/ (1+ 1)) from ( select distinct doc_id from [billhowe].[reuters_terms.csv]) as x)","id":"14919"},{"owner":"anilvoid@gmail.com","date":"11\/4\/2012 5:34:51 AM","query":"--select log((select count(*)\/ (1+ 1) from ( select distinct doc_id from [billhowe].[reuters_terms.csv]) as x) ))  --select count(*) from ( select distinct doc_id from [billhowe].[reuters_terms.csv]) as x  --(select log(count(*)\/ (1+ 1)) from ( select distinct doc_id from [billhowe].[reuters_terms.csv]) as x) (select (13 * (log(count(*) \/ (1+ 15) ))) as tfidf          from ( select distinct doc_id                from [billhowe].[reuters_terms.csv]             ) as x         )","id":"14925"},{"owner":"anilvoid@gmail.com","date":"11\/4\/2012 5:36:11 AM","query":"--select log((select count(*)\/ (1+ 1) from ( select distinct doc_id from [billhowe].[reuters_terms.csv]) as x) ))  --select count(*) from ( select distinct doc_id from [billhowe].[reuters_terms.csv]) as x  --(select log(count(*)\/ (1+ 1)) from ( select distinct doc_id from [billhowe].[reuters_terms.csv]) as x) select term_id, count(doc_id) as term_doc_count                   from [billhowe].[reuters_terms.csv]                    group by term_id","id":"14928"},{"owner":"anilvoid@gmail.com","date":"11\/4\/2012 5:37:07 AM","query":"--select log((select count(*)\/ (1+ 1) from ( select distinct doc_id from [billhowe].[reuters_terms.csv]) as x) ))  --select count(*) from ( select distinct doc_id from [billhowe].[reuters_terms.csv]) as x  --(select log(count(*)\/ (1+ 1)) from ( select distinct doc_id from [billhowe].[reuters_terms.csv]) as x) select T1.term_id,         doc_id,        (select (T1.frequency * (log(count(*) \/ (1+ T2.term_doc_count) ))) as tfidf          from ( select distinct doc_id                from [billhowe].[reuters_terms.csv]             ) as x         )          from          [billhowe].[reuters_terms.csv] T1,         ( select term_id, count(doc_id) as term_doc_count                   from [billhowe].[reuters_terms.csv]                    group by term_id ) as T2                where T1.term_id = T2.term_id","id":"14931"}];
