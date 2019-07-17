#!/usr/bin/env bash

##
#dw_ods.ods_mid_debt

cmd="$1 day ago"
dayago1="1 day ago"
dayago2="2 day ago"
dayago30="30 day ago"
dayago60="60 day ago"
today=`date +%F -d "${cmd}"`
today_num=`date +%Y%m%d -d "${cmd}"`
today_ago1=`date +%F -d "${today} ${dayago1}"`
today_ago2=`date +%F -d "${today} ${dayago2}"`
today_ago30=`date +%F -d "${today} ${dayago30}"`
today_ago60=`date +%F -d "${today} ${dayago60}"`

export SPARK_HOME=/opt/core/spark-2.3.1-bin-hadoop2.6
export JAVA_HOME=/usr/local/jdk1.8.0_161
export PATH=${SPARK_HOME}/bin:${JAVA_HOME}/bin:$PATH

spark-sql --master yarn \
--deploy-mode client \
--name "ads_channel_active" \
--conf "spark.executorEnv.JAVA_HOME=/usr/local/jdk1.8.0_161" \
--conf "spark.yarn.appMasterEnv.JAVA_HOME=/usr/local/jdk1.8.0_161" \
--queue root.line.ploan \
-e "
set hive.exec.dynamic.partition.mode=nonstrict;
truncate table dw_dwd.fact_prod_debt_detail;
insert overwrite table dw_dwd.fact_prod_debt_detail partition(dt)
select
to_date(create_time) deal_dt
,uuid debt_uuid
,ly_product_uuid
,product_source prod_src_cd

,case when a.create_type=2 then 2
when a.create_type=1 then 6
else a.product_type end prod_type_cd  --原始标的类型 展期 债转 特殊处理

--,product_type prod_type_cd
,case when create_type in(2,1) then product_type else null end ext_prod_type_cd
,debt_status
,isoverdue
,overdue_days
,to_date(end_time) debt_end_time
,rate
,fee_rate
,iou_fee as dim_iou_fee

,case when origin_principal <= 10000 then cast(origin_principal/100 as int)
when origin_principal > 10000 and origin_principal <= 100000 then cast(origin_principal/1000 as int)*10
when origin_principal > 100000 and origin_principal <= 1000000 then cast(origin_principal/10000 as int)*100
when origin_principal > 1000000 and origin_principal <= 10000000 then cast(origin_principal/1000000 as int)*10000
else cast(origin_principal/1000000 as int)*10000
end origin_principal_range
,term
,case when debt_type=5 then 1 else 0 end isterm
,ptp_sign isptp
,depository_bank bank_type
,origin_principal deal_amt
,origin_interest
,interest
,repay_interest
,repay_overdue_interest
,fee deal_fee
,creditor
,debtor
,'${today}' dt
from dw_ods.ods_mid_debt a
where dt='${today}';
exit;
"

# build kylin
sh /data00/dwd/warehouse/etl/ploan/common/kylin_rebuild.sh cube_fact_prod_debt_detail
