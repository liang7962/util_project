package com.example.demo.controller;

import com.example.demo.constant.Constant;
import com.example.demo.excel.ExportExcelByPoiUtil;
import com.example.demo.util.MapUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("/")
public class Conroller {

//    @RequestMapping("/exportSafeConfess")
//    public String exportSafeConfess(HttpServletRequest request, HttpServletResponse response) {
//
//        String json="{\"code\":200,\"succ\":true,\"data\":[{\"cols\":[{\"k\":\"viw_bikyn_dim_date_yy\",\"v\":\"2019\"},{\"k\":\"viw_bikyn_dim_date_yy_desc\",\"v\":\"2019年\"},{\"k\":\"viw_bikyn_dim_date_ss\",\"v\":\"20193\"},{\"k\":\"viw_bikyn_dim_date_ss_desc\",\"v\":\"第3季度\"},{\"k\":\"viw_bikyn_dim_date_mon\",\"v\":\"20199\"},{\"k\":\"viw_bikyn_dim_date_mon_desc\",\"v\":\"9月\"},{\"k\":\"viw_bikyn_dim_trans_chnl_trans_chnl\",\"v\":\"0\"},{\"k\":\"viw_bikyn_dim_trans_chnl_trans_chnl_nm\",\"v\":\"0-其它\"},{\"k\":\"viw_bikyn_dim_card_attr_card_attr\",\"v\":\"01\"},{\"k\":\"viw_bikyn_dim_card_attr_card_attr_nm\",\"v\":\"储蓄卡\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_id3\",\"v\":\"1\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_desc3\",\"v\":\"01标准类\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_id2\",\"v\":\"1\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_desc2\",\"v\":\"01宾馆餐娱类\"},{\"k\":\"total_trans_num\",\"v\":\"32.00\"},{\"k\":\"achis_succ_trans_num\",\"v\":\"32.00\"}]},{\"cols\":[{\"k\":\"viw_bikyn_dim_date_yy\",\"v\":\"2019\"},{\"k\":\"viw_bikyn_dim_date_yy_desc\",\"v\":\"2019年\"},{\"k\":\"viw_bikyn_dim_date_ss\",\"v\":\"20194\"},{\"k\":\"viw_bikyn_dim_date_ss_desc\",\"v\":\"第4季度\"},{\"k\":\"viw_bikyn_dim_date_mon\",\"v\":\"201910\"},{\"k\":\"viw_bikyn_dim_date_mon_desc\",\"v\":\"10月\"},{\"k\":\"viw_bikyn_dim_trans_chnl_trans_chnl\",\"v\":\"0\"},{\"k\":\"viw_bikyn_dim_trans_chnl_trans_chnl_nm\",\"v\":\"0-其它\"},{\"k\":\"viw_bikyn_dim_card_attr_card_attr\",\"v\":\"01\"},{\"k\":\"viw_bikyn_dim_card_attr_card_attr_nm\",\"v\":\"储蓄卡\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_id3\",\"v\":\"1\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_desc3\",\"v\":\"01标准类\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_id2\",\"v\":\"1\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_desc2\",\"v\":\"01宾馆餐娱类\"},{\"k\":\"total_trans_num\",\"v\":\"32.00\"},{\"k\":\"achis_succ_trans_num\",\"v\":\"32.00\"}]},{\"cols\":[{\"k\":\"viw_bikyn_dim_date_yy\",\"v\":\"2019\"},{\"k\":\"viw_bikyn_dim_date_yy_desc\",\"v\":\"2019年\"},{\"k\":\"viw_bikyn_dim_date_ss\",\"v\":\"20194\"},{\"k\":\"viw_bikyn_dim_date_ss_desc\",\"v\":\"第4季度\"},{\"k\":\"viw_bikyn_dim_date_mon\",\"v\":\"201910\"},{\"k\":\"viw_bikyn_dim_date_mon_desc\",\"v\":\"10月\"},{\"k\":\"viw_bikyn_dim_trans_chnl_trans_chnl\",\"v\":\"0\"},{\"k\":\"viw_bikyn_dim_trans_chnl_trans_chnl_nm\",\"v\":\"0-其它\"},{\"k\":\"viw_bikyn_dim_card_attr_card_attr\",\"v\":\"01\"},{\"k\":\"viw_bikyn_dim_card_attr_card_attr_nm\",\"v\":\"借记卡\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_id3\",\"v\":\"1\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_desc3\",\"v\":\"01标准类\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_id2\",\"v\":\"1\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_desc2\",\"v\":\"01宾馆餐娱类\"},{\"k\":\"total_trans_num\",\"v\":\"32.00\"},{\"k\":\"achis_succ_trans_num\",\"v\":\"32.00\"}]},{\"cols\":[{\"k\":\"viw_bikyn_dim_date_yy\",\"v\":\"2019\"},{\"k\":\"viw_bikyn_dim_date_yy_desc\",\"v\":\"2019年\"},{\"k\":\"viw_bikyn_dim_date_ss\",\"v\":\"20194\"},{\"k\":\"viw_bikyn_dim_date_ss_desc\",\"v\":\"第4季度\"},{\"k\":\"viw_bikyn_dim_date_mon\",\"v\":\"201910\"},{\"k\":\"viw_bikyn_dim_date_mon_desc\",\"v\":\"10月\"},{\"k\":\"viw_bikyn_dim_trans_chnl_trans_chnl\",\"v\":\"0\"},{\"k\":\"viw_bikyn_dim_trans_chnl_trans_chnl_nm\",\"v\":\"0-其它\"},{\"k\":\"viw_bikyn_dim_card_attr_card_attr\",\"v\":\"01\"},{\"k\":\"viw_bikyn_dim_card_attr_card_attr_nm\",\"v\":\"借记卡\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_id3\",\"v\":\"1\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_desc3\",\"v\":\"01标准类\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_id2\",\"v\":\"2\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_desc2\",\"v\":\"02房产汽车类\"},{\"k\":\"total_trans_num\",\"v\":\"88.00\"},{\"k\":\"achis_succ_trans_num\",\"v\":\"40.00\"}]},{\"cols\":[{\"k\":\"viw_bikyn_dim_date_yy\",\"v\":\"2019\"},{\"k\":\"viw_bikyn_dim_date_yy_desc\",\"v\":\"2019年\"},{\"k\":\"viw_bikyn_dim_date_ss\",\"v\":\"20194\"},{\"k\":\"viw_bikyn_dim_date_ss_desc\",\"v\":\"第4季度\"},{\"k\":\"viw_bikyn_dim_date_mon\",\"v\":\"201910\"},{\"k\":\"viw_bikyn_dim_date_mon_desc\",\"v\":\"10月\"},{\"k\":\"viw_bikyn_dim_trans_chnl_trans_chnl\",\"v\":\"0\"},{\"k\":\"viw_bikyn_dim_trans_chnl_trans_chnl_nm\",\"v\":\"0-其它\"},{\"k\":\"viw_bikyn_dim_card_attr_card_attr\",\"v\":\"01\"},{\"k\":\"viw_bikyn_dim_card_attr_card_attr_nm\",\"v\":\"借记卡\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_id3\",\"v\":\"1\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_desc3\",\"v\":\"01标准类\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_id2\",\"v\":\"3\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_desc2\",\"v\":\"03百货一般类\"},{\"k\":\"total_trans_num\",\"v\":\"40.00\"},{\"k\":\"achis_succ_trans_num\",\"v\":\"16.00\"}]},{\"cols\":[{\"k\":\"viw_bikyn_dim_date_yy\",\"v\":\"2019\"},{\"k\":\"viw_bikyn_dim_date_yy_desc\",\"v\":\"2019年\"},{\"k\":\"viw_bikyn_dim_date_ss\",\"v\":\"20194\"},{\"k\":\"viw_bikyn_dim_date_ss_desc\",\"v\":\"第4季度\"},{\"k\":\"viw_bikyn_dim_date_mon\",\"v\":\"201910\"},{\"k\":\"viw_bikyn_dim_date_mon_desc\",\"v\":\"10月\"},{\"k\":\"viw_bikyn_dim_trans_chnl_trans_chnl\",\"v\":\"0\"},{\"k\":\"viw_bikyn_dim_trans_chnl_trans_chnl_nm\",\"v\":\"0-其它\"},{\"k\":\"viw_bikyn_dim_card_attr_card_attr\",\"v\":\"01\"},{\"k\":\"viw_bikyn_dim_card_attr_card_attr_nm\",\"v\":\"借记卡\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_id3\",\"v\":\"1\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_desc3\",\"v\":\"01标准类\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_id2\",\"v\":\"5\"},{\"k\":\"viw_bikyn_dim_mcc_new2_mcc_grp_desc2\",\"v\":\"05便民类\"},{\"k\":\"total_trans_num\",\"v\":\"83.00\"},{\"k\":\"achis_succ_trans_num\",\"v\":\"76.00\"}]}],\"msg\":\"\\nServer: 02c29c46-12b8-454f-9d25-66b59da5caff\\nQueryId: wg85128:7070\\nQueryEngine: CUBE[name=viw_bikyn_achis_trans_day_v3]\\nSQL: select viw_bikyn_dim_date.yy as viw_bikyn_dim_date_yy\\n\\t\\t,viw_bikyn_dim_date.yy_desc as viw_bikyn_dim_date_yy_desc\\n\\t\\t,viw_bikyn_dim_date.ss as viw_bikyn_dim_date_ss\\n\\t\\t,viw_bikyn_dim_date.ss_desc as viw_bikyn_dim_date_ss_desc\\n\\t\\t,viw_bikyn_dim_date.mon as viw_bikyn_dim_date_mon\\n\\t\\t,viw_bikyn_dim_date.mon_desc as viw_bikyn_dim_date_mon_desc\\n\\t\\t,viw_bikyn_dim_trans_chnl.trans_chnl as viw_bikyn_dim_trans_chnl_trans_chnl\\n\\t\\t,viw_bikyn_dim_trans_chnl.trans_chnl_nm as viw_bikyn_dim_trans_chnl_trans_chnl_nm\\n\\t\\t,viw_bikyn_dim_card_attr.card_attr as viw_bikyn_dim_card_attr_card_attr\\n\\t\\t,viw_bikyn_dim_card_attr.card_attr_nm as viw_bikyn_dim_card_attr_card_attr_nm\\n\\t\\t,viw_bikyn_dim_mcc_new2.mcc_grp_id3 as viw_bikyn_dim_mcc_new2_mcc_grp_id3\\n\\t\\t,viw_bikyn_dim_mcc_new2.mcc_grp_desc3 as viw_bikyn_dim_mcc_new2_mcc_grp_desc3\\n\\t\\t,viw_bikyn_dim_mcc_new2.mcc_grp_id2 as viw_bikyn_dim_mcc_new2_mcc_grp_id2\\n\\t\\t,viw_bikyn_dim_mcc_new2.mcc_grp_desc2 as viw_bikyn_dim_mcc_new2_mcc_grp_desc2\\n\\t\\t,sum(m.total_trans_num)*1.00 as total_trans_num\\n\\t\\t,sum(m.achis_succ_trans_num)*1.00 as achis_succ_trans_num\\nfrom viw_bikyn_achis_trans_day m\\nleft outer join viw_bikyn_dim_date as viw_bikyn_dim_date on m.hp_settle_dt = viw_bikyn_dim_date.dt\\nleft outer join viw_bikyn_dim_trans_chnl as viw_bikyn_dim_trans_chnl on m.trans_chnl = viw_bikyn_dim_trans_chnl.trans_chnl\\nleft outer join viw_bikyn_dim_card_attr as viw_bikyn_dim_card_attr on m.card_attr = viw_bikyn_dim_card_attr.card_attr\\nleft outer join viw_bikyn_dim_mcc_new2 as viw_bikyn_dim_mcc_new2 on m.mcc = viw_bikyn_dim_mcc_new2.mcc_cd\\nwhere 1=1   \\nand (viw_bikyn_dim_trans_chnl.trans_chnl in ('0'))\\nand (viw_bikyn_dim_mcc_new2.mcc_grp_id3 in ('1'))\\nand (viw_bikyn_dim_card_attr.card_attr in ('01'))\\nand (viw_bikyn_dim_date.yy in ('2019'))\\ngroup by viw_bikyn_dim_date.yy\\n\\t\\t,viw_bikyn_dim_date.yy_desc\\n\\t\\t,viw_bikyn_dim_date.ss\\n\\t\\t,viw_bikyn_dim_date.ss_desc\\n\\t\\t,viw_bikyn_dim_date.mon\\n\\t\\t,viw_bikyn_dim_date.mon_desc\\n\\t\\t,viw_bikyn_dim_trans_chnl.trans_chnl\\n\\t\\t,viw_bikyn_dim_trans_chnl.trans_chnl_nm\\n\\t\\t,viw_bikyn_dim_card_attr.card_attr\\n\\t\\t,viw_bikyn_dim_card_attr.card_attr_nm\\n\\t\\t,viw_bikyn_dim_mcc_new2.mcc_grp_id3\\n\\t\\t,viw_bikyn_dim_mcc_new2.mcc_grp_desc3\\n\\t\\t,viw_bikyn_dim_mcc_new2.mcc_grp_id2\\n\\t\\t,viw_bikyn_dim_mcc_new2.mcc_grp_desc2 \",\"isMore\":false}";
//        String[] columnParams={"viw_bikyn_dim_date_ss_desc","viw_bikyn_dim_date_mon_desc"};//列条件
//        String[] rowParams={"viw_bikyn_dim_trans_chnl_trans_chnl_nm","viw_bikyn_dim_card_attr_card_attr_nm","viw_bikyn_dim_mcc_new2_mcc_grp_desc2"};//行条件
//        String[] measureParams={"total_trans_num","achis_succ_trans_num"};//度条件
//
//        String[] measureParamsStr={"总交易笔数","成功交易笔数"};//度条件解释--取值map
//
//        JSONObject jsonObject = JSONObject.parseObject(json);
//        String code=jsonObject.getString("code");
//        Boolean succ=jsonObject.getBoolean("succ");
//        if (null!=code && code.equals("200") && succ){
//            JSONArray jsonArray = jsonObject.getJSONArray("data");
//
//            String titleHead = "全渠道主题（日）";
//            List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
//
//            for(int i=0;i<jsonArray.size();i++) {
//                Map<String, String> temp = new HashMap<>();
//                JSONObject jsonObject1 = JSONObject.parseObject(jsonArray.get(i).toString());
//                JSONArray jsonArray1 = jsonObject1.getJSONArray("cols");
//
//                for(int j=0;j<jsonArray1.size();j++) {
//                    JSONObject jsonObject2 = JSONObject.parseObject(jsonArray1.get(j).toString());
//                    temp.put(jsonObject2.getString("k"),jsonObject2.getString("v"));
//                }
//                //动态赋值
//                String columnStr="";
//                for (String column:columnParams){
//                    columnStr+=temp.get(column)+ Constant.CONSTANT_STR;
//                }
//                for (int j=0;j<measureParams.length;j++){
//                    temp.put(columnStr+measureParamsStr[j],temp.get(measureParams[j]));
//                }
//                dataList.add(temp);
//            }
//
//            //先查询出有多少列数据，除了行占的条件列
//            Set<String> columnSet=new HashSet<String>();
//            for (Map<String, String> map:dataList){
//                String columnStr="";
//                for (String column:columnParams){
//                    columnStr+=map.get(column)+Constant.CONSTANT_STR;
//                }
//                columnSet.add(columnStr.substring(0,columnStr.length()-Constant.CONSTANT_STR.length()));
//            }
//
//            List<String> columnList=new ArrayList<String>();
//            for (String column:columnSet){
//                for (String measure:measureParamsStr){
//                    columnList.add(column+Constant.CONSTANT_STR+measure);
//                }
//            }
//
//            //查询出有多少种行数据
//            Set<String> rowSet=new HashSet<String>();
//            for (Map<String, String> map:dataList){
//                String rowStr="";
//                for (String row:rowParams){
//                    rowStr+=map.get(row);
//                }
//                rowSet.add(rowStr);
//            }
//
//            String[] titleAttr=new String[rowParams.length+columnSet.size()*measureParams.length];
//            int widthAttr[] = new int[rowParams.length+columnSet.size()*measureParams.length];
//            for (int i=0;i<widthAttr.length;i++){
//                widthAttr[i]=30;
//            }
//            for (int i=0;i<rowParams.length;i++){
//                titleAttr[i]=rowParams[i];
//            }
//            for (int i=0;i<columnList.size();i++){
//                titleAttr[i+rowParams.length]=columnList.get(i);
//            }
//
//            Map<String, List<Map<String, String>>> map = Maps.newHashMap();
//            map.put("全渠道主题（日）", dataList);
//            /* 此处数组为需要合并的列 和需要合并的行，可能有的需求是只需要某些列里面相同内容合并 */
//            //response,列取得字段，标题名词，宽度、数据集合、参与自动合并的行、参与自动合并的列、展示的列头部
//            //行
//            ExportExcelByPoiUtil.createExcel( response, titleAttr, titleHead, widthAttr, map, rowParams.length, columnParams.length+1,columnList,measureParams.length);
//        }
//        return  "success";
//    }

    @RequestMapping("/exportTwo")
    public void exportTwo( HttpServletResponse response) throws IOException {

        String[] columnParams={"viw_bikyn_dim_date_yy_desc","viw_bikyn_dim_code_buss_chnl_buss_chnl_desc"};//列条件
        String[] rowParams={"viw_bikyn_dim_code_trans_media_trans_media_desc","viw_bikyn_dim_ins_user_intnl_user_intnl_ins_cn_nm","viw_bikyn_dim_code_pay_method_pay_method_desc"};//行条件
        String[] measureParams={"total_trans_num","achis_succ_trans_num"};//度条件

        String[] measureParamsStr={"总交易笔数","成功交易笔数"};//度条件解释--取值map

        ObjectMapper objMap = new ObjectMapper();
        JsonNode root = objMap.readTree(new File("C:\\Users\\EDZ\\Desktop\\工作\\resJson.json"));
        Map<String, Object> souceDataMap = objMap.convertValue(root, Map.class);
        if (null!=souceDataMap.get("code") && (int)souceDataMap.get("code")==200 && (Boolean) souceDataMap.get("succ") ){
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
                    columnStr+=temp.get(column)+Constant.CONSTANT_STR;
                    list.add(temp.get(column));
                }
                for (int j=0;j<measureParams.length;j++){
                    temp.put(columnStr+measureParamsStr[j],temp.get(measureParams[j]));
                    list.add(temp.get(measureParamsStr[j]));
                }
                dataList.add(temp);
                keyMap.put(columnStr,list);
            }

            //先查询出有多少列数据，除了行占的条件列
            Set<String> columnSet=new HashSet<String>();
            for (Map<String, String> map:dataList){
                String columnStr="";
                for (String column:columnParams){
                    columnStr+=map.get(column)+Constant.CONSTANT_STR;
                }
                columnSet.add(columnStr.substring(0,columnStr.length()-Constant.CONSTANT_STR.length()));
            }

            List<String> columnList=new ArrayList<String>();
            for (String column:columnSet){
                for (String measure:measureParamsStr){
                    columnList.add(column+Constant.CONSTANT_STR+measure);
                }
            }

            //对于相同行的数据需要合并--dataList
            List<Integer> delIndex=new ArrayList<>();//需要删除的下标，需要修改的目标下标

            List<String[]> rowList=new ArrayList<>();
            for (int i=0;i<dataList.size();i++){
                String rowStr="";
                for (String row:rowParams){
                    rowStr+=dataList.get(i).get(row)+Constant.CONSTANT_STR;
                }
                rowStr=rowStr.substring(0,rowStr.length()-Constant.CONSTANT_STR.length());
                boolean flag=true;
                if (null!=rowList && rowList.size()>0){
                    for (int j=0;j<rowList.size();j++){
                        if (rowStr.equals(rowList.get(j)[0])){
                            //如果存在，则将当前的原始map新增当前map不存在的key值 i是需要删除的map下标，j为需要保留的map下标
                            Map<String, String> paramsMap=dataList.get(i);
                            Integer index=Integer.parseInt(rowList.get(j)[1]);

                            Map<String, String> souceMap=dataList.get(index);
                            MapUtil.mapUpdCopy(paramsMap,souceMap);
                            dataList.set(index,souceMap);
                            delIndex.add(i);
                            flag=false;
                            //如果相同则需要处理数据，找到之前map的数据，在之前数据上增加 一条数据，key为列数据拼接的值，vaule为展示的值
                        }
                    }
                }
                if (flag){
                    String[] row=new String[]{rowStr,Integer.toString(i)};
                    rowList.add(row);
                }
            }

            //删除重复的数据
            //先把delIndex 从小到到大排序
            Collections.sort(delIndex);
            List<Map<String, String>> souceList = new ArrayList<Map<String, String>>();
            for (int j=0;j<dataList.size();j++){
                boolean flag=true;
                for (int i=delIndex.size()-1;i>=0;i--){
                    if (delIndex.get(i)==j){
                        flag=false;
                    }
                }
                if (flag){
                    souceList.add(dataList.get(j));
                }
            }


            int widthAttr[] = new int[rowParams.length+columnSet.size()*measureParams.length];
            for (int i=0;i<widthAttr.length;i++){
                widthAttr[i]=30;
            }

            String[] titleAttr=new String[rowParams.length+columnSet.size()*measureParams.length];
            for (int i=0;i<rowParams.length;i++){
                titleAttr[i]=rowParams[i];
            }
            //排序
            Collections.sort(columnList);

            for (int i=0;i<columnList.size();i++){
                titleAttr[i+rowParams.length]=columnList.get(i);
            }


            //souceList需要排序，按照行参数排序
            int orderType=1;//1升序，2降序
            for (int j=rowParams.length-1;j>=0;j--){
                int k=j;
                Collections.sort(souceList, new Comparator<Map<String, String>>() {
                    @Override
                    public int compare(Map<String, String> o1, Map<String, String> o2) {
                        String str1=o1.get(rowParams[k]);
                        String str2=o2.get(rowParams[k]);
                        if (orderType==1){
                            return str1.compareTo(str2);
                        }else {
                            return str2.compareTo(str1);
                        }
                    }
                });
            }


            Map<String, List<Map<String, String>>> map = Maps.newHashMap();
            map.put("全渠道主题（日）", souceList);
//             此处数组为需要合并的列 和需要合并的行，可能有的需求是只需要某些列里面相同内容合并
            //response,列取得字段，标题名词，宽度、数据集合、参与自动合并的行、参与自动合并的列、展示的列头部
            //行
            ExportExcelByPoiUtil.createExcel( response, titleAttr, titleHead, widthAttr, map, rowParams.length, columnParams.length+1,columnList,measureParams.length,keyMap);
        }
    }


    //读取json文件
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
