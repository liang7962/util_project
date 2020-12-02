package com.example.demo.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import com.example.demo.util.StrUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



/**
 * 导出  动态合并单元格
 * @ClassName:ExportExcelByPoiUtil
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: ZHOUPAN
 * @date 2019年1月25日 下午3:22:27
 *
 * @Copyright: 2018 www.zsplat.com Inc. All rights reserved.
 */
public class ExportExcelByPoiUtil {


    /**
     *
     * @Title: createExcel
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param request
     * @param @param response
     * @param @param title 标题数组
     * @param @param titleHead  Excel标题
     * @param @param widthAttr  单元格宽度
     * @param @param maps  数据
     * @param @param mergeIndex  要合并的列   数组
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    @SuppressWarnings("rawtypes")
    public static String createExcel( HttpServletResponse response,String[] title,String titleHead ,int[] widthAttr,Map<String, List<Map<String, String>>> maps, int rowMergeIndex, int columnMergeIndex, List<String> columnList, int measureLength,Map<String,List<String>> keyMap){
        /*初始化excel模板*/
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = null;
        int n = 0;
        /*循环sheet页*/
        for(Map.Entry<String, List<Map<String, String>>> entry : maps.entrySet()){
            /*实例化sheet对象并且设置sheet名称，book对象*/
            try {
                sheet = workbook.createSheet();
                workbook.setSheetName(n, entry.getKey());
                workbook.setSelectedTab(0);
            }catch (Exception e){
                e.printStackTrace();
            }
            List<String[]> columnLists=new ArrayList<>();
            //列数据
            for (String column:columnList) {
                List<String> stringList = keyMap.get(column);
                columnLists.add(stringList.toArray(new String[0]));
            }
            CellStyle cellStyle_export = style(3, workbook);
            // 正文
            CellStyle cellStyle = style(2, workbook);
            // 设置列宽
            for (int i = 0; i < widthAttr.length; i++) {
                sheet.setColumnWidth((short) i, (short) widthAttr[i] * 100);
            }

            //定义每一列 起始行坐标、结束坐标
            int[] columns=new int[columnMergeIndex*measureLength];
            for (int k=0;k<columns.length;k++){
                columns[k]=rowMergeIndex+1;
            }

            //自动合并列单元格处理  i--列数，从第一列开始
            for (int i=0;i<columnMergeIndex;i++){
                Row row = sheet.createRow(i);
                // 设置单元格内容
                for (int j=0;j<columnLists.size();j++){
                    // 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
                    Cell cell1 = row.createCell(j+columnMergeIndex);

                    cell1.setCellValue(columnLists.get(j)[i]);
                    cell1.setCellStyle(cellStyle_export);

                    //当前列合并结束行坐标
                    //如果不是最后一列，且当前列和下一列不相等时，则需要合并
                    if (j<columnLists.size()-1 && i<columnMergeIndex-1){
                        String columnStr=columnLists.get(j)[i];
                        String columnStrNext=columnLists.get(j+1)[i];
//                        System.out.println("columnStr>>>>>"+columnStr);
//                        System.out.println("columnStrNext>>>>>"+columnStrNext);
                        if(columnStr.equals(columnStrNext)){
                            columns[measureLength*i+1]++;
                        }else {
                            if (columns[measureLength*i+1]> columns[measureLength*i]){
                                CellRangeAddress cra=new CellRangeAddress(i, i, columns[measureLength*i]-1, columns[measureLength*i+1]-1);
                                sheet.addMergedRegion(cra);
                                columns[measureLength*i+1]++;
                                columns[measureLength*i]=columns[measureLength*i+1];
                            }
                        }
                    }
                    if(j==columnLists.size()-1 ){
                        //当取到最后一列的时候，判断和前一列是否相等，如果相等，则进行一次合并操作，不相等则不处理
                        if (columns[measureLength*i]<columns[measureLength*i+1]){
                            CellRangeAddress cra=new CellRangeAddress(i, i, columns[measureLength*i]-1, columns[measureLength*i+1]-1);
                            sheet.addMergedRegion(cra);
                        }
                    }
                }
            }
            /*得到当前sheet下的数据集合*/
            List<Map<String/*对应title的值*/, String>> list = entry.getValue();

            if(null!=workbook){
                int index = rowMergeIndex;
                int[] rolls=new int[rowMergeIndex*measureLength];
                for (int k=0;k<rolls.length;k++){
                    rolls[k]=rowMergeIndex+1;
                }
                //定义一批合并的初始值，根据需要合并的列数决定--目前合并的列数为3列

                for(int k = 0; k<list.size(); k++){
                    Row row = sheet.createRow(index);
                    Map<String, String> map=list.get(k);
                    /*循环列数，给当前行塞值*/
                    for(int i = 0; i<title.length; i++){
                        //列合并规则，如果 i=0，则判断当前行是否与下一行相同，如果相同，则继续找下一列，直到找到不同的则合并--
                        if (i==0){
                            //前后两列相同，且不是最后一列
                            if (k<list.size()-1){
                                String str1=list.get(k).get(title[i]);
                                String str2=list.get(k+1).get(title[i]);
                                if (!str1.equals(str2)){
                                    if(rolls[0]<rolls[1]){
                                        CellRangeAddress cra=new CellRangeAddress(rolls[0]-1, rolls[1]-1, 0, 0);
                                        //在sheet里增加合并单元格
                                        sheet.addMergedRegion(cra);
                                    }
                                    //执行合并，同时rolls[0] 为rolls[1]
                                    rolls[0]=rolls[1]; 
                                }
                                rolls[1]++;
                            }else {
                                if (rolls[i*measureLength+1]-1>rolls[i*measureLength]){
                                    //因为是跟下一行比较，所以不需要rolls[i*measureLength+1]-1
                                    CellRangeAddress cra=new CellRangeAddress(rolls[i*measureLength], rolls[i*measureLength+1]-1, i, i);
                                    //在sheet里增加合并单元格
                                    sheet.addMergedRegion(cra);
                                }
                            }
                        }else if (i<columnMergeIndex && i>0 && i<2){
                            /*
                                列合并规则，如果 i!=0，则判断当前行前一行是否数据是否相同
                                根据前一行是否相同来判断 合并范围
                                如果其前一列相同，再判断下一列
                            */
                            if (k<list.size()-1){
                                String str=list.get(k).get(title[i]);
                                String nextStr=list.get(k+1).get(title[i]);
//                                System.out.println("str>>>>>"+str);
//                                System.out.println("nextStr>>>>>"+nextStr);
                                if (!str.equals(nextStr)){
                                    if(rolls[i*measureLength]<rolls[i*measureLength+1]){
                                        //如果不相同，判断其前一列是否相同，起始位为当前起点，尾点为当前不同的数据节点
                                        for (int h=rolls[i*measureLength]-1;h<=rolls[i*measureLength+1]-1;h++){
                                            String preStr=list.get(h-rowMergeIndex).get(title[i-1]);
                                            String preNextStr=list.get(h-rowMergeIndex+1).get(title[i-1]);
                                            //如果前一列不相同，则取当前点为合并数据点，同时下一个合并起始点为当前点，且不为最后一个节点
                                            if(!preStr.equals(preNextStr) && rolls[i*measureLength]-1<h  && h<rolls[i*measureLength+1]-1){
                                                CellRangeAddress cra=new CellRangeAddress(rolls[i*measureLength]-1, h, i, i);
                                                //在sheet里增加合并单元格
                                                sheet.addMergedRegion(cra);
                                                rolls[i*measureLength]=rolls[i*measureLength+1];//加1因为不需要下一个重复
                                            }

                                            //如果为最后一个节点，则直接判断是否可以合并，如果可以，则直接合并
                                            if (h==rolls[i*measureLength+1]-1&& h>rolls[i*measureLength] && rolls[i*measureLength]<rolls[i*measureLength+1] ){
                                                CellRangeAddress cra;
                                                if (rolls[i*measureLength]==rowMergeIndex+1){
                                                     cra=new CellRangeAddress(rolls[i*measureLength]-1, h, i, i);
                                                }else {
                                                     cra=new CellRangeAddress(rolls[i*measureLength], h, i, i);
                                                }

                                                //在sheet里增加合并单元格
                                                sheet.addMergedRegion(cra);
                                                rolls[i*measureLength]=rolls[i*measureLength+1];
                                            }
                                        }
                                    }
                                }
                                rolls[i*measureLength+1]++;
                            }else {
                                if (rolls[i*measureLength+1]-1>rolls[i*measureLength]){
                                    //因为是跟下一行比较，所以不需要rolls[i*measureLength+1]-1
                                    CellRangeAddress cra=new CellRangeAddress(rolls[i*measureLength], rolls[i*measureLength+1]-1, i, i);
                                    //在sheet里增加合并单元格
                                    sheet.addMergedRegion(cra);
                                }
                            }
                        }
                        Cell cell = row.createCell(i, Cell.CELL_TYPE_STRING);
                        //对取得数据判断一下，看是否后面数据大于0
                        if (StringUtils.isBlank(map.get(title[i]))){
                            cell.setCellValue("0");
                        }else {
                            String value=map.get(title[i]);
                            String[] strs=value.split("\\.");
                            if (null!=strs &&strs.length>1 &&StrUtils.isNumeric(value)){
                                if ( Integer.parseInt(strs[strs.length-1])>0){
                                    cell.setCellValue(map.get(title[i]));
                                }else {
                                    cell.setCellValue(strs[0]);
                                }
                            }else {
                                cell.setCellValue(map.get(title[i]));
                            }

                        }
//                        cell.setCellValue(StringUtils.isBlank(map.get(title[i]))?"0":map.get(title[i]));
                        cell.setCellStyle(cellStyle);

                    }
                    index++;
                }
            }
            n++;
        }

        OutputStream out = null;
        String localPath = null;
        try {
            Calendar calendar1 = Calendar.getInstance();
            String cal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar1.getTime());
            out = response.getOutputStream();
            response.reset();//清空输出流
            response.setHeader("Content-disposition", "attachment;filename=" + new String(titleHead.getBytes("gbk"), "iso8859-1") + cal + ".xlsx");// 设定输出文件头
            response.setContentType("application/vnd.ms-excel;charset=GBK");// 定义输出类型
            workbook.write(out);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                out.flush();
                out.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return localPath;
    }

    /**
     *
     * @Title: style
     * @Description: TODO(样式)
     * @author: GMY
     * @date: 2018年5月9日 下午5:16:48
     * @param @return    设定文件  index 0:头 1：标题 2：正文
     * @return HSSFCellStyle    返回类型
     * @throws
     */
    public static CellStyle style(int index, Workbook workbook) {
        CellStyle cellStyle = null;
        if (index == 0) {
            // 设置头部样式
            cellStyle = workbook.createCellStyle();
            // 设置字体大小 位置
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            // 生成一个字体
            Font font = workbook.createFont();
            //设置字体
            font.setFontName("微软雅黑");
            //字体颜色
            font.setColor(HSSFColor.BLACK.index);// HSSFColor.VIOLET.index

            font.setFontHeightInPoints((short) 12);
//            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体增粗
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
            cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);//背景白色
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyle.setFont(font);
        }
        //正文
        if (index == 1 ||index == 2 ||index == 3) {
            // 设置样式
            cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            // 生成一个字体
            Font font_title = workbook.createFont();
            //设置字体
            font_title.setFontName("微软雅黑");
            cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
            cellStyle.setWrapText(true); // 自动换行
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);//背景白色
            cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        }
        //时间
        if (index == 4) {
            // 设置样式
            cellStyle = workbook.createCellStyle();
            // 居中
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
            // 生成一个字体
            Font font_title = workbook.createFont();
            //设置字体
            font_title.setFontName("微软雅黑");
            font_title.setColor(HSSFColor.BLACK.index);// HSSFColor.VIOLET.index
            // //字体颜色
            font_title.setFontHeightInPoints((short) 10);
            font_title.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
            cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            cellStyle.setFont(font_title);

        }
        if (index == 4) {
            // 设置样式
            cellStyle = workbook.createCellStyle();
            // 居中
//            cellStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        }
        return cellStyle;
    }

}