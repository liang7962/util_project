package com.example.demo;

import com.example.demo.constant.Constant;
import com.example.demo.excel.ExportExcelByPoiUtil;
import com.example.demo.util.MapUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.*;


@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	public static void main(String[] args) throws IOException {
//		String str="{\"key\":\"120%\"}";
//		ObjectMapper mapper = new ObjectMapper();
//		Map<String,String> readValue = mapper.readValue(str, Map.class);
//		readValue.entrySet().forEach(e->{
//			String substring1 = e.getValue().substring(e.getValue().length() - 1, e.getValue().length());
//			if (substring1.equals("%")) {
//					System.out.println("%");
//				}else {
//					String substring = e.getValue().substring(0, e.getValue().length() - 1);
//					Integer.parseInt(substring);
//				}
//			}
//		);
//		Map<String,String> map1=map;

//        ObjectMapper objMap = new ObjectMapper();
//        JsonNode jsonNode = objMap.readTree(new File("C:\\Users\\EDZ\\Desktop\\工作\\resJson.json"));
//        Map<String, Object> map = objMap.convertValue(jsonNode, Map.class);
//
//        Integer code=(Integer)map.get("code");
//        List<Map<String,Map<String,String>>> list=(List<Map<String,Map<String,String>>>)map.get("data");
//        System.out.println(list.get(0));

        {

            String[] columnParams={"viw_bikyn_dim_date_yy_desc","viw_bikyn_dim_code_buss_chnl_buss_chnl_desc"};//列条件
            String[] rowParams={"viw_bikyn_dim_code_trans_media_trans_media_desc","viw_bikyn_dim_ins_user_intnl_user_intnl_ins_cn_nm","viw_bikyn_dim_code_pay_method_pay_method_desc"};//行条件
            String[] measureParams={"total_trans_num","achis_succ_trans_num"};//度条件

            String[] measureParamsStr={"总交易笔数","成功交易笔数"};//度条件解释--取值map

            ObjectMapper objMap = new ObjectMapper();
            JsonNode root = objMap.readTree(new File("C:\\Users\\EDZ\\Desktop\\工作\\resJson.json"));
            Map<String, Object> souceDataMap = objMap.convertValue(root, Map.class);
            if (null!=souceDataMap.get("code") && (Integer)souceDataMap.get("code")==200 && (Boolean) souceDataMap.get("succ") ){
                Map<String,List<String>> keyMap=new HashMap();

                List<Map<String,List<Map<String,String>>>> souceDataList=(List<Map<String,List<Map<String,String>>>>)souceDataMap.get("data");
                String titleHead = "全渠道主题（日）";
                List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

                for(int i=0;i<souceDataList.size();i++) {
                    Map<String, String> temp = new HashMap<>();
                    List<Map<String, String>> list1 = souceDataList.get(i).get("cols");
                    for (Map<String, String> map1:list1){
                        temp.put(map1.get("k"),map1.get("v"));
                    }

                    //动态赋值
                    String columnStr="";
                    List<String> list=new ArrayList<>();
                    for (String column:columnParams){
                        columnStr+=temp.get(column)+ Constant.CONSTANT_STR;
                        list.add(temp.get(column));
                    }
                    for (int j=0;j<measureParams.length;j++){
                        temp.put(columnStr+measureParamsStr[j],temp.get(measureParams[j]));
                        list.add(temp.get(measureParamsStr[j]));
                    }
                    dataList.add(temp);
                    keyMap.put(columnStr,list);
                }
                System.out.println(dataList.toString());
            }

        }
	}
}
