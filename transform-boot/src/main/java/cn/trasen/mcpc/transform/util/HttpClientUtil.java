package cn.trasen.mcpc.transform.util;

import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSONArray;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @program: readEcel
 * @description: 网页http连接工具类
 * @author: yan_zt
 * @create: 2020-06-10 13:43
 */
public class HttpClientUtil {
    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, JSONObject param){
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-type", "application/json; charset=utf-8");
            conn.setRequestProperty("Cookie","slim_session=%7B%22slim.flash%22%3A%5B%5D%7D");
            conn.setRequestProperty("Host","<calculated when request is sent>");
//            conn.setRequestProperty("user-agent",
//                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("user-agent",
                    "PostmanRuntime/7.25.0");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 以form表单形式提交数据，发送post请求
     * @explain
     *   1.请求头：httppost.setHeader("Content-Type","application/x-www-form-urlencoded")
     *   2.提交的数据格式：key1=value1&key2=value2...
     * @param url 请求地址
     * @param paramsMap 具体数据
     * @return 服务器返回数据
     */
    public static JSONArray httpPostWithForm(String url,Map<String, String> paramsMap){
        // 用于接收返回的结果
        String resultData ="[";
        try {
            HttpPost post = new HttpPost(url);
            List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
            // 迭代Map-->取出key,value放到BasicNameValuePair对象中-->添加到list中
            for (String key : paramsMap.keySet()) {
                pairList.add(new BasicNameValuePair(key, paramsMap.get(key)));
            }
            UrlEncodedFormEntity uefe = new UrlEncodedFormEntity(pairList, "utf-8");
            post.setEntity(uefe);
            // 创建一个http客户端
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            // 发送post请求
            HttpResponse response = httpClient.execute(post);

            // 状态码为：200
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                // 返回数据：
                resultData += EntityUtils.toString(response.getEntity(),"UTF-8") + "]";
            }else{
                throw new RuntimeException("接口连接失败！");
            }
        } catch (Exception e) {
            throw new RuntimeException("接口连接失败！");
        }
        return JSONArray.fromObject(resultData);
    }
}
