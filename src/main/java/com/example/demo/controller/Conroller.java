package com.example.demo.controller;

import com.example.demo.excel.ExportExcelByPoiUtil;
import com.example.demo.util.MapUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("/")
public class Conroller {

    @Value("classpath:ResponseData.json")
    private Resource responseRes;

    @Value("classpath:resJson.json")
    private Resource resJsonRes;

    @RequestMapping("/exportSafeConfess")
    public void exportSafeConfess( HttpServletResponse response) throws IOException {

        String[] columnParams={"viw_bikyn_dim_date_ss_desc","viw_bikyn_dim_date_mon_desc"};//列条件
        String[] rowParams={"viw_bikyn_dim_trans_chnl_trans_chnl_nm","viw_bikyn_dim_card_attr_card_attr_nm","viw_bikyn_dim_mcc_new2_mcc_grp_desc2"};//行条件
        String[] measureParams={"total_trans_num","achis_succ_trans_num"};//度条件
        String[] measureParamsStr={"总交易笔数","成功交易笔数"};//度条件解释--取值map

        ObjectMapper objMap = new ObjectMapper();

        JsonNode root = objMap.readTree(responseRes.getInputStream());
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
                //动态赋值 -
                String columnStr="";
                List<String> list=new ArrayList<>();
                for (String column:columnParams){
                    columnStr+=temp.get(column);
                    list.add(temp.get(column));
                }
                for (int j=0;j<measureParams.length;j++){
                    List<String> list2=new ArrayList<>();
                    list2.addAll(list);
                    temp.put(columnStr+measureParamsStr[j],temp.get(measureParams[j]));
                    list2.add(measureParamsStr[j]);
                    keyMap.put(columnStr+measureParamsStr[j],list2);
                }
                dataList.add(temp);
            }

            //先查询出有多少列数据，排除行占的条件列
            Set<String> columnSet=new HashSet<String>();
            for (Map<String, String> map:dataList){
                String columnStr="";
                for (String column:columnParams){
                    columnStr+=map.get(column);
                }
                columnSet.add(columnStr);
            }

            List<String> columnList=new ArrayList<String>();
            for (String column:columnSet){
                for (String measure:measureParamsStr){
                    columnList.add(column+measure);
                }
            }

            //对于相同行的数据需要合并--dataList
            List<Integer> delIndex=new ArrayList<>();//需要删除的下标，需要修改的目标下标

            List<String[]> rowList=new ArrayList<>();
            for (int i=0;i<dataList.size();i++){
                String rowStr="";
                for (String row:rowParams){
                    rowStr+=dataList.get(i).get(row);
                }
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


    @RequestMapping("/exportTwo")
    public void exportTwo( HttpServletResponse response) throws IOException {

        String[] columnParams={"viw_bikyn_dim_date_yy_desc","viw_bikyn_dim_code_buss_chnl_buss_chnl_desc"};//列条件
        String[] rowParams={"viw_bikyn_dim_code_trans_media_trans_media_desc","viw_bikyn_dim_ins_user_intnl_user_intnl_ins_cn_nm","viw_bikyn_dim_code_pay_method_pay_method_desc"};//行条件
        String[] measureParams={"total_trans_num","achis_succ_trans_num"};//度条件

        String[] measureParamsStr={"总交易笔数","成功交易笔数"};//度条件解释--取值map

        ObjectMapper objMap = new ObjectMapper();

        JsonNode root = objMap.readTree(resJsonRes.getInputStream());
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

                //动态赋值 -
                String columnStr="";
                List<String> list=new ArrayList<>();
                for (String column:columnParams){
                    columnStr+=temp.get(column);
                    list.add(temp.get(column));
                }
                for (int j=0;j<measureParams.length;j++){
                    List<String> list2=new ArrayList<>();
                    list2.addAll(list);
                    temp.put(columnStr+measureParamsStr[j],temp.get(measureParams[j]));
                    list2.add(measureParamsStr[j]);
                    keyMap.put(columnStr+measureParamsStr[j],list2);
                }
                dataList.add(temp);
            }

            //先查询出有多少列数据，排除行占的条件列
            Set<String> columnSet=new HashSet<String>();
            for (Map<String, String> map:dataList){
                String columnStr="";
                for (String column:columnParams){
                    columnStr+=map.get(column);
                }
                columnSet.add(columnStr);
            }

            List<String> columnList=new ArrayList<String>();
            for (String column:columnSet){
                for (String measure:measureParamsStr){
                    columnList.add(column+measure);
                }
            }

            //对于相同行的数据需要合并--dataList
            List<Integer> delIndex=new ArrayList<>();//需要删除的下标，需要修改的目标下标
            List<String[]> rowList=new ArrayList<>();
            for (int i=0;i<dataList.size();i++){
                String rowStr="";
                for (String row:rowParams){
                    rowStr+=dataList.get(i).get(row);
                }
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
            /*
                此处数组为需要合并的列 和需要合并的行，可能有的需求是只需要某些列里面相同内容合并
                response,列取得字段，标题名词，高度、数据集合、参与自动合并的行、参与自动合并的列、展示的列头部
                行
            */
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
