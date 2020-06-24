package cn.trasen.mcpc.transform.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @program: project-parent
 * @description: 写入文件
 * @author: yan_zt
 * @create: 2020-06-24 08:29
 */
public class WriteFile {
    /**
     * 写入文件
     * @param filePath 文件路劲
     * @param dataStr 数据String
     * @return
     */
    public static boolean writeToFile(String filePath,String dataStr){
        try{
            File file = new File(filePath);
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter writer=new FileWriter(filePath,true);
            writer.write(dataStr);
            writer.close();
            System.out.println("ok");
            return true;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
