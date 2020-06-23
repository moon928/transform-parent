package cn.trasen.mcpc.transform.util;

import cn.trasen.mcpc.transform.dto.UscDrgSpecsDto;
import cn.trasen.mcpc.transform.model.UscDrgSpecs;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;

import java.io.FileInputStream;
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

                cell = row.getCell(1);
                uscDrgSpecs.setWbCode(cell.getStringCellValue());

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
                uscDrgSpecs.setVersionId(110L);
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

    public static void main(String[] args) {
        String path = "D:/Trasen/统一标准目录/药物监管局药品数据/jkyp/details/jkyp.xlsx";
        Map<String, List<UscDrgSpecsDto>> map = readXlsxForjkyp(path);

        System.out.println(map);
    }
}
