
set hive.exec.parallel=true;
set hive.exec.parallel.thread.number=32;
set hive.auto.convert.join=true;
set hive.hadoop.supports.splittable.combineinputformat=true;
set mapreduce.job.priority=VERY_HIGH;


with temp1 as(
select

media_id,
keyword_id,
sum(pv) as pv,
sum(adpv) as adpv,
sum(cash) as cash, -- 收入
sum(cost) as cost, -- 消耗
sum(resume_number) as resume_number_sum,
sum(zpjx_cash) as zpjx_cash_sum,
sum(click0) as clk00,
sum(click1) as click1,
sum(disp) as disp

from hdp_lbg_ectech_lmdb.lm_dws_sem_keyword_day where dt='${dateSuffix}' and bg_id='3' group by media_id,keyword_id
having cost > 0
),
temp2 as(
select
media_id,
keyword_id,
sum(cash) as cash_sum,
sum(cost) as cost_sum,
sum(resume_number) as resume_number_sum
from hdp_lbg_ectech_lmdb.lm_dws_sem_keyword_day where dt='${dateSuffix}' and bg_id='3' group by media_id,keyword_id
),
temp3 as (
select
media_id,
keyword_id,
count(distinct(flow_user_id)) as uv
from hdp_lbg_ectech_lmdb.lm_dws_sem_in_station_day where dt='${dateSuffix}' and bg_id='3' group by media_id,keyword_id
),
temp4 as (
 select
 keyword_id,
 media_id,
 sum(zpjx_cash) as zpjx_cash_sum,
 sum(resume_number) as resume_number_sum,
 sum(resume_count) as resume_count_sum,
 sum(resume_post) as resume_post_sum
 from(
    select
   	regexp_replace(regexp_replace(if(split(spm,'\\.')[0]='-',split(spm,'\\.')[1],split(spm,'\\.')[0]), '%22', ''),'"','') as keyword_id,
    case
        when utm_source like 'sem%' and utm_source like '%baidu%' then '3'
        when utm_source like 'sem%' and utm_source like '%-360-%' then '4'
        when utm_source like 'sem%' and utm_source like '%sg%' then '5'
        when utm_source like 'sem%' and utm_source like '%sm%' then '6'
        when utm_source like 'sem%' and utm_source like '%toutiao%' then '7'
        else '0'
    end as media_id,
    sum(if(isnotnull(cash_sum) and cash_sum<>'-' and inneradtype='28',cash_sum,0)) as zpjx_cash,
   	sum(if(isnotnull(resumedelivernum) and resumedelivernum<>'-' and inneradtype<>'0',resumedelivernum,0)) as resume_number,
    sum(if(isnotnull(resumedeliverid) and resumedeliverid<>'-' and inneradtype<>'0',resumedeliverid,0)) as resume_count,
    sum(if(isnotnull(resumecreatenum) and resumecreatenum<>'-' and inneradtype<>'0',resumecreatenum,0)) as resume_post
   	from hdp_lbg_ectech_lmdb.t_lm_adx_other_effect_dwd_detail where dt='${dateSuffix}' and utm_source like 'sem%'
   	group by
   	regexp_replace(regexp_replace(if(split(spm,'\\.')[0]='-',split(spm,'\\.')[1],split(spm,'\\.')[0]), '%22', ''),'"',''),
   	case
       when utm_source like 'sem%' and utm_source like '%baidu%' then '3'
       when utm_source like 'sem%' and utm_source like '%-360-%' then '4'
       when utm_source like 'sem%' and utm_source like '%sg%' then '5'
       when utm_source like 'sem%' and utm_source like '%sm%' then '6'
       when utm_source like 'sem%' and utm_source like '%toutiao%' then '7'
       else '0'
    end

   	union all
   	select
   	regexp_replace(regexp_replace(if(split(spm,'\\.')[0]='-',split(spm,'\\.')[1],split(spm,'\\.')[0]), '%22', ''),'"','') as keyword_id,
   	case
       when utm_source like 'sem%' and utm_source like '%baidu%' then '3'
       when utm_source like 'sem%' and utm_source like '%-360-%' then '4'
       when utm_source like 'sem%' and utm_source like '%sg%' then '5'
       when utm_source like 'sem%' and utm_source like '%sm%' then '6'
       when utm_source like 'sem%' and utm_source like '%toutiao%' then '7'
       else '0'
    end as media_id,
    sum(if(isnotnull(cash_sum) and cash_sum<>'-' and inneradtype='28',cash_sum,0)) as zpjx_cash,
    sum(if(isnotnull(resumedelivernum) and resumedelivernum<>'-' and inneradtype<>'0',resumedelivernum,0)) as resume_number,
    sum(if(isnotnull(resumedeliverid) and resumedeliverid<>'-' and inneradtype<>'0',resumedeliverid,0)) as resume_count,
    sum(if(isnotnull(resumecreatenum) and resumecreatenum<>'-' and inneradtype<>'0',resumecreatenum,0)) as resume_post
   	from hdp_lbg_ectech_lmdb.t_lm_adx_effect_dwd_detail where dt='${dateSuffix}' and utm_source like 'sem%'
   	group by
   	regexp_replace(regexp_replace(if(split(spm,'\\.')[0]='-',split(spm,'\\.')[1],split(spm,'\\.')[0]), '%22', ''),'"',''),
   	case
       when utm_source like 'sem%' and utm_source like '%baidu%' then '3'
       when utm_source like 'sem%' and utm_source like '%-360-%' then '4'
       when utm_source like 'sem%' and utm_source like '%sg%' then '5'
       when utm_source like 'sem%' and utm_source like '%sm%' then '6'
       when utm_source like 'sem%' and utm_source like '%toutiao%' then '7'
       else '0'
    end
 ) temp41 group by
 keyword_id,
 media_id
),

temp5 as(
  select
  keyword_id,
  keyword
  from(
    select
    keyword_id,
    keyword,
    row_number() over(partition by keyword_id) as rn
    from hdp_lbg_ectech_lmdb.lm_sem_cost_keyword_day where dt='${dateSuffix}' and bg_id='3'
   )temp51 where temp51.rn = 1
)

INSERT OVERWRITE TABLE hdp_lbg_ectech_lmdb.lm_dws_adx_link_quality_sem_aggr PARTITION (dt = '${dateSuffix}',busline=3)
select

  t11.media_id as media_id,
    case
       when t11.media_id like '3' then 'baidu'
       when t11.media_id like '4' then '360'
       when t11.media_id like '5' then 'sg'
       when t11.media_id like '6' then 'sm'
       when t11.media_id like '7' then 'toutiao'
       else '其他'
    end as media, -- 媒体
    t11.keyword_id as keyword_id,
    t51.keyword as keyword, -- 关键词
    t31.uv as uv,
    t11.pv as pv,
    t11.adpv as ad_pv,
    t11.cash as cash,
    t11.cost as cost,
    t11.resume_number_sum as resume_number_sum,
    t41.resume_number_sum as sy_resume_number_sum,
    0 as call_success_sum,
    0 as hy_jz_call_success,
    0 as hy_zd_call_success,
    t11.clk00,
    t11.click1,
    t11.disp,
    t11.zpjx_cash_sum as zpjx_cash_sum,
    0 as hy_jz_cash,
    0 as hy_zd_cash

from(
    select
    media_id,
       keyword_id,
       pv,
       adpv,
       cash,
       cost,
       clk00,
       click1,
       disp,
       resume_number_sum,
       zpjx_cash_sum
    from temp1
) t11
left join (
  select
  media_id,
  keyword_id,
  uv
  from temp3
)t31 on (t11.keyword_id=t31.keyword_id and t11.media_id=t31.media_id)
left join(
  select
  keyword_id,
  media_id,
  zpjx_cash_sum,
  resume_number_sum,
  resume_count_sum,
  resume_post_sum
  from temp4
) t41 on (t11.keyword_id=t41.keyword_id and t11.media_id=t41.media_id)
left join(
  select
  keyword_id,
  keyword
  from temp5
) t51 on (t11.keyword_id=t51.keyword_id)