package cn.trasen.mcpc.transform.util;

import com.alibaba.fastjson.JSONObject;
import com.netflix.discovery.util.StringUtil;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import sun.awt.SunHints;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @program: project-parent
 * @description: 汉字转五笔
 * @author: yan_zt
 * @create: 2020-06-22 14:15
 */
public class WuBiUtils {


    private static Properties wb86;

//    static{
//        // 五笔数据
//        wb86 = new Properties();
//        try {
//            wb86.load(new BufferedInputStream(StringUtil.class
//                    .getResourceAsStream("wb86.properties")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 字符转化为五笔(86),无法转化返回null
     *
     * @param c
     * @return
     */
    public static String[] charToWubi(char c) {
        if (c < 0x4E00 || c > 0x9FA5) {// GBK字库在unicode中的起始和结束位置
            return null;
        }
        String result = wb86.getProperty(Integer.toHexString(c).toUpperCase());
        if (result == null) {
            return null;
        }
        if (result.contains(",")) {
            return result.split(",");
        } else {
            return new String[] { result };
        }
    }

    /**
     * 汉字转五笔
     * @param str
     */
    public static String  getWuBiCode(String str){
        String requestUrl = "https://tool.lu/py5bconvert/ajax.html";
        Map<String, String> paramsMap = new HashMap<String, String>(1);
        paramsMap.put("code", str);
        // 发送post请求并接收返回结果
        JSONArray jsonArray = HttpClientUtil.httpPostWithForm(requestUrl, paramsMap);
        String text = jsonArray.getJSONObject(0).get("text").toString().toUpperCase();
        text = StringUtils.deleteWhitespace(text);
        return text;
    }




    public static void main(String[] args) {

//        String[] 测s = charToWubi('测');
        System.out.println(Arrays.deepToString(charToWubi('测')));
    }
}
