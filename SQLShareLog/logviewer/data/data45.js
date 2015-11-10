data = [{"owner":"armbrustlab","date":"10\/12\/2010 5:12:09 PM","query":"select * from [table_coastal_cogs_mbari_count.txt]","id":"255465"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:10 PM","query":"select * from [table_coastal_mbari_kogs_counts.txt]","id":"255506"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:11 PM","query":"select * from [table_cog_descriptions.txt1]","id":"255553"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:12 PM","query":"select * from [table_cog_functional_categories.txt]","id":"255595"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:13 PM","query":"select * from [table_cog1005.hma.clean.fasta.txt]","id":"255638"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:14 PM","query":"select * from [table_cog1006.hma.clean.fasta.txt]","id":"255682"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:15 PM","query":"select * from [table_cog1007.hma.clean.fasta.txt]","id":"255730"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:16 PM","query":"select * from [table_cog1008.hma.clean.fasta.txt]","id":"255775"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:18 PM","query":"select * from [table_cog1009.hma.clean.fasta.txt0]","id":"255823"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:19 PM","query":"select * from [table_cog1005.hma.clean.fasta.txt]  inner join [table_cog1006.hma.clean.fasta.txt] on [table_cog1005.hma.clean.fasta.txt].name = [table_cog1006.hma.clean.fasta.txt].name  inner join [table_cog1007.hma.clean.fasta.txt] on [table_cog1005.hma.clean.fasta.txt].name = [table_cog1007.hma.clean.fasta.txt].name  inner join [table_cog1008.hma.clean.fasta.txt] on [table_cog1005.hma.clean.fasta.txt].name = [table_cog1008.hma.clean.fasta.txt].name  inner join [table_cog1009.hma.clean.fasta.txt0] on [table_cog1005.hma.clean.fasta.txt].name = [table_cog1009.hma.clean.fasta.txt0].name","id":"255864"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:20 PM","query":"select * from [table_dcm_cogs_mbari_counts.txt]","id":"255913"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:20 PM","query":"select * from [table_dcm_mbari_kogs_counts.txt]","id":"255958"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:21 PM","query":"select col0, (t20174 + t20185) as addition from [join all tigr day results]","id":"256006"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:21 PM","query":"select col0, (t20174 + t20185) as addition from [join all tigr day results]","id":"256050"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:22 PM","query":"select col0, count(col0) as cnt from ( select col0 from [refseq_hma_fasta_tgirfam_refs] union all  select col0 from [est_hma_fasta_tgirfam_refs] union all  select col0 from [combo_hma_fasta_tgirfam_refs] ) blah group by col0","id":"256102"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:24 PM","query":"select col0, abs(t20174 - t20185) as addition from [join all tigr day results]","id":"256150"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:24 PM","query":"select sum(t20174), sum(t20176), (sum(t20174) + sum(t20176)) as [sum 2 columns] from [tigr_comparison_all]","id":"256200"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:25 PM","query":"select col0, t20174, t20185 ,t20186 ,t20189,t20190, t20193, t20194, t20198, t20201, t20202 from [join all tigr results]","id":"256250"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:26 PM","query":"select * from tigr_comparison_all  where t34837 > 0","id":"256294"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:28 PM","query":"select *  from [te_20189_tigrfam_num], [te_20190_tigrfam_num], [te_20191_tigrfam_num], [te_20192_tigrfam_num], [te_20193_tigrfam_num], [te_20194_tigrfam_num]  where [te_20189_tigrfam_num].col0 = [te_20190_tigrfam_num].col0 and [te_20190_tigrfam_num].col0 = [te_20191_tigrfam_num].col0  and [te_20191_tigrfam_num].col0 = [te_20192_tigrfam_num].col0 and [te_20192_tigrfam_num].col0 = [te_20193_tigrfam_num].col0 and [te_20193_tigrfam_num].col0 = [te_20194_tigrfam_num].col0","id":"256339"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:29 PM","query":"select * from [table_kog_function_categories.txt]","id":"256380"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:30 PM","query":"select * from [table_kog_ref_taxa_count.txt]","id":"250045"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:30 PM","query":"select * from [table_kog_functional_description.txt]","id":"256430"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:31 PM","query":"select [table_dcm_mbari_kogs_counts.txt].kog,    hit_count_coasta, hit_count_dcm, hit_count_surface, hit_count_upwelling   from [table_coastal_mbari_kogs_counts.txt]   inner join [table_dcm_mbari_kogs_counts.txt] on [table_coastal_mbari_kogs_counts.txt].kog = [table_dcm_mbari_kogs_counts.txt].kog   inner join [table_surface_mbari_kogs_counts.txt] on [table_coastal_mbari_kogs_counts.txt].kog = [table_surface_mbari_kogs_counts.txt].kog   inner join [table_upwelling_mbari_kogs_counts.txt] on [table_coastal_mbari_kogs_counts.txt].kog = [table_upwelling_mbari_kogs_counts.txt].kog","id":"250094"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:33 PM","query":"select * from [table_surface_cogs_mbari_counts.txt]","id":"250145"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:33 PM","query":"select * from [table_surface_mbari_kogs_counts.txt]","id":"250196"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:37 PM","query":"select 't20174', * from [te_20174_tigrfam_num] union all select 't20176', * from [te_20176_tigrfam_num] union all select 't20185', * from [te_20185_tigrfam_num] union all select 't20186', * from [te_20186_tigrfam_num] union all select 't20187', * from [te_20187_tigrfam_num] union all select 't20188', * from [te_20188_tigrfam_num] union all select 't20189', * from [te_20189_tigrfam_num] union all select 't20190', * from [te_20190_tigrfam_num] union all select 't20191', * from [te_20191_tigrfam_num] union all select 't20192', * from [te_20192_tigrfam_num] union all select 't20193', * from [te_20193_tigrfam_num] union all select 't20194', * from [te_20194_tigrfam_num] union all select 't20195', * from [te_20195_tigrfam_num] union all select 't20196', * from [te_20196_tigrfam_num] union all select 't20198', * from [te_20198_tigrfam_num] union all select 't20200', * from [te_20200_tigrfam_num] union all select 't20201', * from [te_20201_tigrfam_num] union all select 't20202', * from [te_20202_tigrfam_num] union all select 't20203', * from [te_20203_tigrfam_num]","id":"250236"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:41 PM","query":"-- the union of all three datasets, -- less the intersection of all three  select col0 from [refseq_hma_fasta_tgirfam_refs] union select col0 from [est_hma_fasta_tgirfam_refs] union select col0 from [combo_hma_fasta_tgirfam_refs]  except  select col0 from [refseq_hma_fasta_tgirfam_refs] intersect select col0 from [est_hma_fasta_tgirfam_refs] intersect select col0 from [combo_hma_fasta_tgirfam_refs]","id":"250289"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:41 PM","query":"-- the union of all three datasets, -- less the intersection of all three  select substring(col0, 0, 9) from [refseq_hma_fasta_tgirfam_refs] union select substring(col0, 0, 9) from [est_hma_fasta_tgirfam_refs] union select substring(col0, 0, 9) from [combo_hma_fasta_tgirfam_refs]  except  select substring(col0, 0, 9) from [refseq_hma_fasta_tgirfam_refs] intersect select substring(col0, 0, 9) from [est_hma_fasta_tgirfam_refs] intersect select substring(col0, 0, 9) from [combo_hma_fasta_tgirfam_refs]","id":"250340"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:42 PM","query":"select * from [table_coastal_cogs_mbari_count.txt]   inner join [table_dcm_cogs_mbari_counts.txt]  on [table_coastal_cogs_mbari_count.txt].cog = [table_dcm_cogs_mbari_counts.txt].cog   inner join [table_surface_cogs_mbari_counts.txt] on [table_coastal_cogs_mbari_count.txt].cog = [table_surface_cogs_mbari_counts.txt].cog   inner join [table_upwelling_cogs_mbari_count.txt] on [table_coastal_cogs_mbari_count.txt].cog = [table_upwelling_cogs_mbari_count.txt].cog","id":"250389"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:44 PM","query":"select * from [table_coastal_cogs_mbari_count.txt]   inner join [table_dcm_cogs_mbari_counts.txt]  on [table_coastal_cogs_mbari_count.txt].cog = [table_dcm_cogs_mbari_counts.txt].cog   inner join [table_surface_cogs_mbari_counts.txt] on [table_coastal_cogs_mbari_count.txt].cog = [table_surface_cogs_mbari_counts.txt].cog   inner join [table_upwelling_cogs_mbari_count.txt] on [table_coastal_cogs_mbari_count.txt].cog = [table_upwelling_cogs_mbari_count.txt].cog   inner join [cog_descriptions.txt1] on [table_coastal_cogs_mbari_count.txt].cog = [cog_descriptions.txt1].cog   inner join [cog_functional_categories.txt] on [cog_descriptions.txt1].\"functional category\" = [cog_functional_categories.txt].\"functional category\"","id":"250436"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:46 PM","query":"select * from [table_upwelling_cogs_mbari_count.txt]","id":"250484"},{"owner":"armbrustlab","date":"10\/12\/2010 5:12:46 PM","query":"select * from [table_upwelling_mbari_kogs_counts.txt]","id":"250535"},{"owner":"armbrustlab","date":"3\/1\/2011 10:06:49 PM","query":"select * from [armbrustlab].[table_all_te_20174_blast_nr]","id":"251904"},{"owner":"armbrustlab","date":"3\/1\/2011 10:06:50 PM","query":"select * from [armbrustlab].[table_all_tigrfams]","id":"251963"},{"owner":"armbrustlab","date":"3\/1\/2011 10:07:13 PM","query":"select * from [armbrustlab].[table_amazon_normalized_to_sequencing_effort_day]","id":"252016"},{"owner":"armbrustlab","date":"3\/1\/2011 10:07:14 PM","query":"select * from [armbrustlab].[table_blast_results_cyanothe_1000]","id":"252079"},{"owner":"armbrustlab","date":"3\/1\/2011 10:07:15 PM","query":"select * from [armbrustlab].[table_blast_results_euk_psba_tblastn]","id":"252130"},{"owner":"armbrustlab","date":"3\/1\/2011 10:07:17 PM","query":"select * from [armbrustlab].[table_blast_results_pro_psba_tblastn]","id":"252232"},{"owner":"armbrustlab","date":"3\/1\/2011 10:07:19 PM","query":"select * from [armbrustlab].[table_coastal_p1_p8_summary]","id":"252337"},{"owner":"armbrustlab","date":"3\/1\/2011 10:07:20 PM","query":"select * from [armbrustlab].[table_psba_cyanallreads_s]","id":"252389"},{"owner":"armbrustlab","date":"3\/1\/2011 10:07:21 PM","query":"select * from [armbrustlab].[table_psba_eukallreads_s]","id":"252430"},{"owner":"armbrustlab","date":"3\/1\/2011 10:07:22 PM","query":"select * from [armbrustlab].[table_te_20174_diatom_nuc_blast]","id":"252478"},{"owner":"armbrustlab","date":"3\/1\/2011 10:07:23 PM","query":"select * from [armbrustlab].[table_te_20174_est_blast]","id":"252518"},{"owner":"armbrustlab","date":"3\/1\/2011 10:07:24 PM","query":"select * from [armbrustlab].[table_te_34837_tigrfam_num]","id":"252566"},{"owner":"armbrustlab","date":"3\/1\/2011 10:07:26 PM","query":"select * from [armbrustlab].[table_thaps3_bd_unmapped_assembly_scaffolds.fasta]","id":"252605"},{"owner":"armbrustlab","date":"3\/1\/2011 10:07:27 PM","query":"select * from [armbrustlab].[table_thaps3_bd_unmapped_assembly_scaffolds_repeatmasked.fasta]","id":"252660"},{"owner":"armbrustlab","date":"3\/1\/2011 10:07:28 PM","query":"select * from [armbrustlab].[table_thaps3_bd_unmapped_genemodels_filteredmodels1_aa.fasta]","id":"252702"},{"owner":"armbrustlab","date":"3\/1\/2011 10:07:29 PM","query":"select * from [armbrustlab].[table_thaps3_bd_unmapped_genemodels_filteredmodels1_nt.fasta]","id":"252756"},{"owner":"armbrustlab","date":"3\/1\/2011 10:07:30 PM","query":"select * from [armbrustlab].[table_thaps3_chromosomes_assembly_organelle.fasta]","id":"252801"},{"owner":"armbrustlab","date":"3\/1\/2011 10:07:30 PM","query":"select * from [armbrustlab].[table_thaps3_chromosomes_genemodels_filteredmodels2_aa.fasta]","id":"252849"},{"owner":"armbrustlab","date":"3\/1\/2011 10:07:31 PM","query":"select * from [armbrustlab].[table_thaps3_chromosomes_genemodels_filteredmodels2_nt.fasta]","id":"252900"},{"owner":"armbrustlab","date":"3\/1\/2011 10:07:32 PM","query":"select * from [armbrustlab].[table_tigr_comparison]","id":"252949"},{"owner":"armbrustlab","date":"3\/1\/2011 10:07:33 PM","query":"select * from [armbrustlab].[table_tigr_comparison_2]","id":"252992"},{"owner":"armbrustlab","date":"3\/1\/2011 10:07:34 PM","query":"select * from [armbrustlab].[table_tigr_comparison_all_orderby34837]","id":"253029"},{"owner":"armbrustlab","date":"3\/1\/2011 10:07:35 PM","query":"select * from [armbrustlab].[table_tigrfams_amazon_ranked]","id":"253069"},{"owner":"armbrustlab","date":"3\/1\/2011 11:00:20 PM","query":"select * from [armbrustlab].[table_test]","id":"253717"},{"owner":"armbrustlab","date":"5\/27\/2011 9:02:56 PM","query":"select * from blueplacer.tigr_db","id":"251182"}];