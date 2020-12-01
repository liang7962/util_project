package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
/*
		String json="{\"code\":200,\"succ\":true,\"data\":[{\"cols\":[{\"k\":\"viw_bikyn_dim_date_yy\",\"v\":\"2019\"},{\"k\":\"viw_bikyn_dim_date_yy_desc\",\"v\":\"2019年\"},{\"k\":\"viw_bikyn_dim_date_ss\",\"v\":\"20194\"},{\"k\":\"viw_bikyn_dim_date_ss_desc\",\"v\":\"第4季度\"},{\"k\":\"viw_bikyn_dim_date_mon\",\"v\":\"201910\"},{\"k\":\"viw_bikyn_dim_date_mon_desc\",\"v\":\"10月\"},{\"k\":\"viw_bikyn_dim_trans_chnl_trans_chnl\",\"v\":\"0\"},{\"k\":\"viw_bikyn_dim_trans_chnl_trans_chnl_nm\",\"v\":\"0-其它\"},{\"k\":\"viw_bikyn_dim_card_attr_card_attr\",\"v\":\"01\"},{\"k\":\"viw_bikyn_dim_card_attr_card_attr_nm\",\"v\":\"借记卡\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_id3\",\"v\":\"1\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_desc3\",\"v\":\"01标准类\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_id2\",\"v\":\"1\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_desc2\",\"v\":\"01宾馆餐娱类\"},{\"k\":\"total_trans_num\",\"v\":\"32.00\"},{\"k\":\"achis_succ_trans_num\",\"v\":\"32.00\"}]},{\"cols\":[{\"k\":\"viw_bikyn_dim_date_yy\",\"v\":\"2019\"},{\"k\":\"viw_bikyn_dim_date_yy_desc\",\"v\":\"2019年\"},{\"k\":\"viw_bikyn_dim_date_ss\",\"v\":\"20194\"},{\"k\":\"viw_bikyn_dim_date_ss_desc\",\"v\":\"第4季度\"},{\"k\":\"viw_bikyn_dim_date_mon\",\"v\":\"201910\"},{\"k\":\"viw_bikyn_dim_date_mon_desc\",\"v\":\"10月\"},{\"k\":\"viw_bikyn_dim_trans_chnl_trans_chnl\",\"v\":\"0\"},{\"k\":\"viw_bikyn_dim_trans_chnl_trans_chnl_nm\",\"v\":\"0-其它\"},{\"k\":\"viw_bikyn_dim_card_attr_card_attr\",\"v\":\"01\"},{\"k\":\"viw_bikyn_dim_card_attr_card_attr_nm\",\"v\":\"借记卡\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_id3\",\"v\":\"1\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_desc3\",\"v\":\"01标准类\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_id2\",\"v\":\"2\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_desc2\",\"v\":\"02房产汽车类\"},{\"k\":\"total_trans_num\",\"v\":\"88.00\"},{\"k\":\"achis_succ_trans_num\",\"v\":\"40.00\"}]},{\"cols\":[{\"k\":\"viw_bikyn_dim_date_yy\",\"v\":\"2019\"},{\"k\":\"viw_bikyn_dim_date_yy_desc\",\"v\":\"2019年\"},{\"k\":\"viw_bikyn_dim_date_ss\",\"v\":\"20194\"},{\"k\":\"viw_bikyn_dim_date_ss_desc\",\"v\":\"第4季度\"},{\"k\":\"viw_bikyn_dim_date_mon\",\"v\":\"201910\"},{\"k\":\"viw_bikyn_dim_date_mon_desc\",\"v\":\"10月\"},{\"k\":\"viw_bikyn_dim_trans_chnl_trans_chnl\",\"v\":\"0\"},{\"k\":\"viw_bikyn_dim_trans_chnl_trans_chnl_nm\",\"v\":\"0-其它\"},{\"k\":\"viw_bikyn_dim_card_attr_card_attr\",\"v\":\"01\"},{\"k\":\"viw_bikyn_dim_card_attr_card_attr_nm\",\"v\":\"借记卡\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_id3\",\"v\":\"1\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_desc3\",\"v\":\"01标准类\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_id2\",\"v\":\"3\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_desc2\",\"v\":\"03百货一般类\"},{\"k\":\"total_trans_num\",\"v\":\"40.00\"},{\"k\":\"achis_succ_trans_num\",\"v\":\"16.00\"}]},{\"cols\":[{\"k\":\"viw_bikyn_dim_date_yy\",\"v\":\"2019\"},{\"k\":\"viw_bikyn_dim_date_yy_desc\",\"v\":\"2019年\"},{\"k\":\"viw_bikyn_dim_date_ss\",\"v\":\"20194\"},{\"k\":\"viw_bikyn_dim_date_ss_desc\",\"v\":\"第4季度\"},{\"k\":\"viw_bikyn_dim_date_mon\",\"v\":\"201910\"},{\"k\":\"viw_bikyn_dim_date_mon_desc\",\"v\":\"10月\"},{\"k\":\"viw_bikyn_dim_trans_chnl_trans_chnl\",\"v\":\"0\"},{\"k\":\"viw_bikyn_dim_trans_chnl_trans_chnl_nm\",\"v\":\"0-其它\"},{\"k\":\"viw_bikyn_dim_card_attr_card_attr\",\"v\":\"01\"},{\"k\":\"viw_bikyn_dim_card_attr_card_attr_nm\",\"v\":\"借记卡\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_id3\",\"v\":\"1\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_desc3\",\"v\":\"01标准类\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_id2\",\"v\":\"5\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_desc2\",\"v\":\"05便民类\"},{\"k\":\"total_trans_num\",\"v\":\"83.00\"},{\"k\":\"achis_succ_trans_num\",\"v\":\"76.00\"}]}],\"msg\":\"\\nServer: 02c29c46-12b8-454f-9d25-66b59da5caff\\nQueryId: wg85128:7070\\nQueryEngine: CUBE[name=viw_bikyn_achis_trans_day_v3]\\nSQL: select viw_bikyn_dim_date.yy as viw_bikyn_dim_date_yy\\n\\t\\t,viw_bikyn_dim_date.yy_desc as viw_bikyn_dim_date_yy_desc\\n\\t\\t,viw_bikyn_dim_date.ss as viw_bikyn_dim_date_ss\\n\\t\\t,viw_bikyn_dim_date.ss_desc as viw_bikyn_dim_date_ss_desc\\n\\t\\t,viw_bikyn_dim_date.mon as viw_bikyn_dim_date_mon\\n\\t\\t,viw_bikyn_dim_date.mon_desc as viw_bikyn_dim_date_mon_desc\\n\\t\\t,viw_bikyn_dim_trans_chnl.trans_chnl as viw_bikyn_dim_trans_chnl_trans_chnl\\n\\t\\t,viw_bikyn_dim_trans_chnl.trans_chnl_nm as viw_bikyn_dim_trans_chnl_trans_chnl_nm\\n\\t\\t,viw_bikyn_dim_card_attr.card_attr as viw_bikyn_dim_card_attr_card_attr\\n\\t\\t,viw_bikyn_dim_card_attr.card_attr_nm as viw_bikyn_dim_card_attr_card_attr_nm\\n\\t\\t,viw_bikyn_dim_mcc_new2.mcc_grp_id3 as viw_bikyn_dim_mcc_new2_mcc_grp_id3\\n\\t\\t,viw_bikyn_dim_mcc_new2.mcc_grp_desc3 as viw_bikyn_dim_mcc_new2_mcc_grp_desc3\\n\\t\\t,viw_bikyn_dim_mcc_new2.mcc_grp_id2 as viw_bikyn_dim_mcc_new2_mcc_grp_id2\\n\\t\\t,viw_bikyn_dim_mcc_new2.mcc_grp_desc2 as viw_bikyn_dim_mcc_new2_mcc_grp_desc2\\n\\t\\t,sum(m.total_trans_num)*1.00 as total_trans_num\\n\\t\\t,sum(m.achis_succ_trans_num)*1.00 as achis_succ_trans_num\\nfrom viw_bikyn_achis_trans_day m\\nleft outer join viw_bikyn_dim_date as viw_bikyn_dim_date on m.hp_settle_dt = viw_bikyn_dim_date.dt\\nleft outer join viw_bikyn_dim_trans_chnl as viw_bikyn_dim_trans_chnl on m.trans_chnl = viw_bikyn_dim_trans_chnl.trans_chnl\\nleft outer join viw_bikyn_dim_card_attr as viw_bikyn_dim_card_attr on m.card_attr = viw_bikyn_dim_card_attr.card_attr\\nleft outer join viw_bikyn_dim_mcc_new2 as viw_bikyn_dim_mcc_new2 on m.mcc = viw_bikyn_dim_mcc_new2.mcc_cd\\nwhere 1=1   \\nand (viw_bikyn_dim_trans_chnl.trans_chnl in ('0'))\\nand (viw_bikyn_dim_mcc_new2.mcc_grp_id3 in ('1'))\\nand (viw_bikyn_dim_card_attr.card_attr in ('01'))\\nand (viw_bikyn_dim_date.yy in ('2019'))\\ngroup by viw_bikyn_dim_date.yy\\n\\t\\t,viw_bikyn_dim_date.yy_desc\\n\\t\\t,viw_bikyn_dim_date.ss\\n\\t\\t,viw_bikyn_dim_date.ss_desc\\n\\t\\t,viw_bikyn_dim_date.mon\\n\\t\\t,viw_bikyn_dim_date.mon_desc\\n\\t\\t,viw_bikyn_dim_trans_chnl.trans_chnl\\n\\t\\t,viw_bikyn_dim_trans_chnl.trans_chnl_nm\\n\\t\\t,viw_bikyn_dim_card_attr.card_attr\\n\\t\\t,viw_bikyn_dim_card_attr.card_attr_nm\\n\\t\\t,viw_bikyn_dim_mcc_new2.mcc_grp_id3\\n\\t\\t,viw_bikyn_dim_mcc_new2.mcc_grp_desc3\\n\\t\\t,viw_bikyn_dim_mcc_new2.mcc_grp_id2\\n\\t\\t,viw_bikyn_dim_mcc_new2.mcc_grp_desc2 \",\"isMore\":false}";
		JSONObject jsonObject = JSONObject.parseObject(json);
		String code=jsonObject.getString("code");
		Boolean succ=jsonObject.getBoolean("succ");
		//查询出需要合并单位格 数据的合并个数，需要合并的 每个年度对应的季度有多少数据-例如，key:20194, value:2
		Map<String,Integer> map=new HashMap<>();
		//年度和月份
		if (null!=code && code.equals("200") && succ){
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			for(int i=0;i<jsonArray.size();i++) {
				//如果不合并的话决定excel总条数
//				System.out.println(jsonArray.get(i));
//				Object o = jsonArray.get(i);
				JSONObject jsonObject1 = JSONObject.parseObject(jsonArray.get(i).toString());
				JSONArray jsonArray1 = jsonObject1.getJSONArray("cols");
				String viw_bikyn_dim_date_ss=null;
				for(int j=0;j<jsonArray1.size();j++) {
					JSONObject jsonObject2 = JSONObject.parseObject(jsonArray1.get(j).toString());
					System.out.println(jsonObject2);
//					System.out.println(jsonObject2.getString("v"));
//					System.out.println(jsonObject2.getString("viw_bikyn_dim_date_ss"));
					//先取有几个月
					if (null!=jsonObject2.getString("k")&&"viw_bikyn_dim_date_ss".equals(jsonObject2.getString("k"))){
						viw_bikyn_dim_date_ss=jsonObject2.getString("v");
//						break;
					}
				}
				if (map.containsKey(viw_bikyn_dim_date_ss)){
					map.put(viw_bikyn_dim_date_ss,map.get(viw_bikyn_dim_date_ss)+2);
				}else {
					map.put(viw_bikyn_dim_date_ss,2);
				}
			}
		}
		System.out.println(map);*/
	}

}
