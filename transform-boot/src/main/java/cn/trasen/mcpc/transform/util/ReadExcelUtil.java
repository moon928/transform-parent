package cn.trasen.mcpc.transform.util;

import cn.trasen.mcpc.framework.util.IdWorker;
import cn.trasen.mcpc.transform.dto.UscDrgSpecsDto;
import cn.trasen.mcpc.transform.model.TmpYjjData;
import cn.trasen.mcpc.transform.model.UscDrgSpecs;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: project-parent
 * @description: 读取excel工具类
 * @author: yan_zt
 * @create: 2020-06-23 18:17
 */
public class ReadExcelUtil {



    public static String readXls(String path){
        String text="";
        try
        {
            FileInputStream is =  new FileInputStream(path);
            HSSFWorkbook excel=new HSSFWorkbook(is);
            //获取第一个sheet
            HSSFSheet sheet0=excel.getSheetAt(0);
            for (Iterator rowIterator = sheet0.iterator(); rowIterator.hasNext();)
            {
                HSSFRow row=(HSSFRow) rowIterator.next();
                for (Iterator iterator=row.cellIterator();iterator.hasNext();)
                {
                    HSSFCell cell=(HSSFCell) iterator.next();
                    //根据单元的的类型 读取相应的结果
                    if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING){
                        text+=cell.getStringCellValue()+"\t";
                    }
                    else if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
                        text+=cell.getNumericCellValue()+"\t";
                    }
                    else if(cell.getCellType()==HSSFCell.CELL_TYPE_FORMULA){
                        text+=cell.getCellFormula()+"\t";
                    }
                }
                text+="\n";
            }
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return text;
    }

    /**
     * 读取进口药品excel数据
     * @param path
     * @return
     */
    public static Map<String,List<UscDrgSpecsDto>> readXlsxForjkyp(String path) {
        Map<String,List<UscDrgSpecsDto>> map = new HashMap<String,List<UscDrgSpecsDto>>();
        List<UscDrgSpecsDto> success = new ArrayList<UscDrgSpecsDto>();
        List<UscDrgSpecsDto> failure = new ArrayList<UscDrgSpecsDto>();
        String text="";
        try{
            OPCPackage pkg=OPCPackage.open(path);
            XSSFWorkbook excel=new XSSFWorkbook(pkg);
            //获取第一个sheet
            XSSFSheet sheet0=excel.getSheetAt(0);
            //总条数
            int lastRowNum = sheet0.getLastRowNum();

            for (int i=1;i<=lastRowNum;i++){
                //标识 0-无错误发生 1-有错误发什么
                int flag = 0;
                //错误信息
                String readError = "";
                UscDrgSpecsDto uscDrgSpecs = new UscDrgSpecsDto();
                String str = "";
                XSSFRow row = sheet0.getRow(i);
                Cell cell = row.getCell(0);
                if (StringUtils.isNotBlank(cell.getStringCellValue())){
                    uscDrgSpecs.setChemicalName(cell.getStringCellValue());
                }else{
                    cell = row.getCell(1);
                    uscDrgSpecs.setChemicalName(cell.getStringCellValue());
                }

                //商品名
                cell = row.getCell(2);
                if (StringUtils.isNotBlank(cell.getStringCellValue())){
                    uscDrgSpecs.setComName(cell.getStringCellValue());
                }else {
                    cell = row.getCell(3);
                    uscDrgSpecs.setComName(cell.getStringCellValue());
                }

                cell = row.getCell(4);
                if (StringUtils.isNotBlank(cell.getStringCellValue())){
                    uscDrgSpecs.setDrugDosformCode(cell.getStringCellValue());
                }else {
                    uscDrgSpecs.setReadError("剂型为空");
                    failure.add(uscDrgSpecs);
                    continue;
                }

                cell = row.getCell(5);
                if (StringUtils.isNotBlank(cell.getStringCellValue())){
                    uscDrgSpecs.setSpecDesc(cell.getStringCellValue());
                }

                cell = row.getCell(6);
                if (StringUtils.isNotBlank(cell.getStringCellValue())){
                    uscDrgSpecs.setDrugType(cell.getStringCellValue());
                }else {
                    uscDrgSpecs.setReadError("产品类别为空");
                    failure.add(uscDrgSpecs);
                    continue;
                }

                //生产厂家
                cell = row.getCell(7);
                if (StringUtils.isNotBlank(cell.getStringCellValue())){
                    uscDrgSpecs.setFactName(cell.getStringCellValue());
                }else {
                    cell = row.getCell(8);
                    uscDrgSpecs.setFactName(cell.getStringCellValue());
                }

                //厂商地址 ----> 联系地址
                cell = row.getCell(9);
                if (StringUtils.isNotBlank(cell.getStringCellValue())){
                    uscDrgSpecs.setRelAddr(cell.getStringCellValue());
                }else {
                    cell = row.getCell(10);
                    uscDrgSpecs.setRelAddr(cell.getStringCellValue());
                }

                //生产厂家
                cell = row.getCell(9);
                if (StringUtils.isNotBlank(cell.getStringCellValue())){
                    uscDrgSpecs.setRelAddr(cell.getStringCellValue());
                }
                uscDrgSpecs.setVersionId(511098310758469632L);
                success.add(uscDrgSpecs);
            }
            map.put("success",success);
            map.put("failure",failure);
        }
        catch (Exception e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 读取进口药品数据到数据库临时表
     * @param path
     * @return
     */
    public static List<TmpYjjData> readXlsxToDbForjkyp(String path) {
        List<TmpYjjData> success = new ArrayList<TmpYjjData>();
        try{
            OPCPackage pkg=OPCPackage.open(path);
            XSSFWorkbook excel=new XSSFWorkbook(pkg);
            //获取第一个sheet
            XSSFSheet sheet0=excel.getSheetAt(0);
            //总条数
            int lastRowNum = sheet0.getLastRowNum();

            for (int i=1;i<=lastRowNum;i++){
//                UscDrgSpecsDto uscDrgSpecs = new UscDrgSpecsDto();
                TmpYjjData data = new TmpYjjData();
                data.setId(IdWorker.getInstance().getId());
                data.setImported(1);
                XSSFRow row = sheet0.getRow(i);
                //产品名称
                Cell cell = row.getCell(0);
                if (StringUtils.isNotBlank(cell.getStringCellValue())){

                    data.setChemicalName(cell.getStringCellValue());
                }else{
                    cell = row.getCell(1);
                    data.setChemicalName(cell.getStringCellValue());
                }
                //英文名
                cell = row.getCell(1);
                if (StringUtils.isNotBlank(cell.getStringCellValue())){
                    data.setEngName(cell.getStringCellValue());
                }
                //商品名
                cell = row.getCell(2);
                if (StringUtils.isNotBlank(cell.getStringCellValue())){
                    data.setComname(cell.getStringCellValue());
                }else {
                    cell = row.getCell(3);
                    data.setComname(cell.getStringCellValue());
                }
                //剂型
                cell = row.getCell(4);
                if (StringUtils.isNotBlank(cell.getStringCellValue())){
                    data.setDrugDosformCode(cell.getStringCellValue());

                }
                //规格
                cell = row.getCell(5);
                if (StringUtils.isNotBlank(cell.getStringCellValue())){
                    data.setSpecDesc(cell.getStringCellValue());
                }
                //药品类型
                cell = row.getCell(6);
                if (StringUtils.isNotBlank(cell.getStringCellValue())){
                    data.setDrugType(cell.getStringCellValue());
                }
                //生产厂家
                cell = row.getCell(7);
                if (StringUtils.isNotBlank(cell.getStringCellValue())){
                    data.setFactName(cell.getStringCellValue());
                }else {
                    cell = row.getCell(8);
                    data.setFactName(cell.getStringCellValue());
                }

                //厂商地址 ----> 联系地址
                cell = row.getCell(9);
                if (StringUtils.isNotBlank(cell.getStringCellValue())){
                    data.setRelAddr(cell.getStringCellValue());
                }else {
                    cell = row.getCell(10);
                    data.setRelAddr(cell.getStringCellValue());
                }

                //药品本位码
                cell = row.getCell(11);
                if (StringUtils.isNotBlank(cell.getStringCellValue())){
                    data.setDrugcodeSpda(cell.getStringCellValue());
                }

                //批准文号
                cell = row.getCell(17);
                if (StringUtils.isNotBlank(cell.getStringCellValue())){
                    data.setAppCode(cell.getStringCellValue());
                }

                data.setVersionId(511098310758469632L);
                success.add(data);
            }
        }
        catch (Exception e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return success;
    }

//    public static void writeExcel(String ){
//
//    }

    public static void main(String[] args) {
        String path = "D:/Trasen/统一标准目录/药物监管局药品数据/jkyp/details/jkyp.xlsx";
        Map<String, List<UscDrgSpecsDto>> map = readXlsxForjkyp(path);

        System.out.println(map);
    }

    /**
     * 创建Excel文件
     * @param filepath filepath 文件全路径
     * @param sheetName 新Sheet页的名字
     * @param titles 表头
     * @param values 每行的单元格
     */
    public static boolean writeExcel(String filepath, String sheetName, List<String> titles,
                                     List<Map<String, Object>> values) throws IOException {
        boolean success = false;
        OutputStream outputStream = null;
        if (StringUtils.isBlank(filepath)) {
            throw new IllegalArgumentException("文件路径不能为空");
        } else {
            String suffiex = getSuffiex(filepath);
            if (StringUtils.isBlank(suffiex)) {
                throw new IllegalArgumentException("文件后缀不能为空");
            }
            Workbook workbook;
            if ("xls".equals(suffiex.toLowerCase())) {
                workbook = new HSSFWorkbook();
            } else {
                workbook = new XSSFWorkbook();
            }
            // 生成一个表格
            Sheet sheet;
            if (StringUtils.isBlank(sheetName)) {
                // name 为空则使用默认值
                sheet = workbook.createSheet();
            } else {
                sheet = workbook.createSheet(sheetName);
            }
            // 设置表格默认列宽度为15个字节
            sheet.setDefaultColumnWidth((short) 15);
            // 生成样式
            Map<String, CellStyle> styles = createStyles(workbook);
            // 创建标题行
            Row row = sheet.createRow(0);
            // 存储标题在Excel文件中的序号
            Map<String, Integer> titleOrder = Maps.newHashMap();
            for (int i = 0; i < titles.size(); i++) {
                Cell cell = row.createCell(i);
                cell.setCellStyle(styles.get("header"));
                String title = titles.get(i);
                cell.setCellValue(title);
                titleOrder.put(title, i);
            }
            // 写入正文
            Iterator<Map<String, Object>> iterator = values.iterator();
            // 行号
            int index = 1;
            while (iterator.hasNext()) {
                row = sheet.createRow(index);
                Map<String, Object> value = iterator.next();
                for (Map.Entry<String, Object> map : value.entrySet()) {
                    // 获取列名
                    String title = map.getKey();
                    // 根据列名获取序号
                    int i = titleOrder.get(title);
                    // 在指定序号处创建cell
                    Cell cell = row.createCell(i);
                    // 设置cell的样式
                    if (index % 2 == 1) {
                        cell.setCellStyle(styles.get("cellA"));
                    } else {
                        cell.setCellStyle(styles.get("cellB"));
                    }
                    // 获取列的值
                    Object object = map.getValue();
                    // 判断object的类型
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if (object instanceof Double) {
                        cell.setCellValue((Double) object);
                    } else if (object instanceof Date) {
                        String time = simpleDateFormat.format((Date) object);
                        cell.setCellValue(time);
                    } else if (object instanceof Calendar) {
                        Calendar calendar = (Calendar) object;
                        String time = simpleDateFormat.format(calendar.getTime());
                        cell.setCellValue(time);
                    } else if (object instanceof Boolean) {
                        cell.setCellValue((Boolean) object);
                    } else {
                        if (object != null) {
                            cell.setCellValue(object.toString());
                        }
                    }
                }
                index++;
            }

            try {
                outputStream = new FileOutputStream(filepath);
                workbook.write(outputStream);
                success = true;
            } finally {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (workbook != null) {
                    workbook.close();
                }
            }
            return success;
        }
    }

    /**
     * 获取后缀
     * @param filepath filepath 文件全路径
     */
    private static String getSuffiex(String filepath) {
        if (StringUtils.isBlank(filepath)) {
            return "";
        }
        int index = filepath.lastIndexOf(".");
        if (index == -1) {
            return "";
        }
        return filepath.substring(index + 1, filepath.length());
    }

    /**
     * 设置格式
     */
    private static Map<String, CellStyle> createStyles(Workbook wb) {
        Map<String, CellStyle> styles = Maps.newHashMap();

        // 标题样式
        XSSFCellStyle titleStyle = (XSSFCellStyle) wb.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER); // 水平对齐
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 垂直对齐
        titleStyle.setLocked(true); // 样式锁定
        titleStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) 16);
        titleFont.setBold(true);
        titleFont.setFontName("微软雅黑");
        titleStyle.setFont(titleFont);
        styles.put("title", titleStyle);

        // 文件头样式
        XSSFCellStyle headerStyle = (XSSFCellStyle) wb.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex()); // 前景色
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND); // 颜色填充方式
        headerStyle.setWrapText(true);
        headerStyle.setBorderRight(BorderStyle.THIN); // 设置边界
        headerStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        Font headerFont = wb.createFont();
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        titleFont.setFontName("微软雅黑");
        headerStyle.setFont(headerFont);
        styles.put("header", headerStyle);

        Font cellStyleFont = wb.createFont();
        cellStyleFont.setFontHeightInPoints((short) 12);
        cellStyleFont.setColor(IndexedColors.BLUE_GREY.getIndex());
        cellStyleFont.setFontName("微软雅黑");

        // 正文样式A
        XSSFCellStyle cellStyleA = (XSSFCellStyle) wb.createCellStyle();
        cellStyleA.setAlignment(HorizontalAlignment.CENTER); // 居中设置
        cellStyleA.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyleA.setWrapText(true);
        cellStyleA.setBorderRight(BorderStyle.THIN);
        cellStyleA.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleA.setBorderLeft(BorderStyle.THIN);
        cellStyleA.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleA.setBorderTop(BorderStyle.THIN);
        cellStyleA.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleA.setBorderBottom(BorderStyle.THIN);
        cellStyleA.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleA.setFont(cellStyleFont);
        styles.put("cellA", cellStyleA);

        // 正文样式B:添加前景色为浅黄色
        XSSFCellStyle cellStyleB = (XSSFCellStyle) wb.createCellStyle();
        cellStyleB.setAlignment(HorizontalAlignment.CENTER);
        cellStyleB.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyleB.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        cellStyleB.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyleB.setWrapText(true);
        cellStyleB.setBorderRight(BorderStyle.THIN);
        cellStyleB.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleB.setBorderLeft(BorderStyle.THIN);
        cellStyleB.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleB.setBorderTop(BorderStyle.THIN);
        cellStyleB.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleB.setBorderBottom(BorderStyle.THIN);
        cellStyleB.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleB.setFont(cellStyleFont);
        styles.put("cellB", cellStyleB);

        return styles;
    }
}
