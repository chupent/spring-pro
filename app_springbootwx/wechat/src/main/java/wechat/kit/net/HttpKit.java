package wechat.kit.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import wechat.bean.Attachment;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import wechat.kit.UploadEntityWriter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName HttpKit
 * @Description TODO HTTP 网络请求工具包
 * @createdate 2019/4/5 星期五 12:56
 */
public class HttpKit {
    private static final String DEFAULT_CHARSET = "UTF-8";//默认输出类型
    private static Gson gson =  new GsonBuilder().setPrettyPrinting().create();
    public static final int POST = 1;
    public static final int GET = 0;


    /**
     * 发送 HTTP 请求
     * @param url 路径
     * @param requestType 请求类型 默认为GET方式，1为post发送方式
     * @param params 参数
     * @param headers 头部信息
     * @return 响应
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static <T> ResposeBody<T> sendRequest(String url,int requestType, Map<String,String> params,Map<String,String> headers,Class<T> t) {
        AsyncHttpClient http = new AsyncHttpClient();
        try{
            AsyncHttpClient.BoundRequestBuilder builder = null;
            if(POST==requestType){
                builder = http.preparePost(url);
            }else{
                builder = http.prepareGet(url);
            }
            builder.setBodyEncoding(DEFAULT_CHARSET);//设置字符集
            //添加参数
            if(params!=null && !params.isEmpty()){
                Set<String> keys = params.keySet();
                for (String key: keys) {
                    String value = params.get(key);
                    builder.addQueryParameter(key,value);
                }
            }
            //添加头部信息
            if(headers!=null && !headers.isEmpty()){
                Set<String> keys = headers.keySet();
                for (String key: keys) {
                    String value = headers.get(key);
                    builder.addHeader(key,value);
                }
            }
            Future<Response> future =  builder.execute();
            String json =  future.get().getResponseBody();
            if(null==json) return null;

            if(null==t||json.contains("errcode")){
                return gson.fromJson(json,ResposeBody.class);
            }else{
                return new ResposeBody<>(gson.fromJson(json,t));
            }


        }catch (ExecutionException e1){
            e1.printStackTrace();
        }catch (InterruptedException e2){
            e2.printStackTrace();
        }catch (IOException e3){
            e3.printStackTrace();
        }finally {
            if(null!=http) http.close();
        }

        return null;
    }
    /**
     * 发送GET HTTP 请求
     * @param url
     * @param params
     * @param headers
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static <T> ResposeBody<T>  get(String url, Map<String,String> params,Map<String,String> headers,Class<T> t)  {
        return sendRequest(url,GET,params,headers,t);
    }
    /**
     * 发送 GET HTTP 请求
     * @param url 路径
     * @param params 参数
     * @return 响应
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static <T> ResposeBody<T> get(String url, Map<String,String> params,Class<T> t)  {
        return get(url,params,null,t);
    }
    public static <T> ResposeBody<T> get(String url, Map<String,String> params)  {
        return get(url,params,null,null);
    }
    /**
     * 发送 GET HTTP 请求
     * @param url 路径
     * @return 响应
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static  <T> ResposeBody<T> get(String url,Class<T> t)  {
        return get(url,null,null,t);
    }

    /**
     * 发送GET HTTP 请求
     * @param url
     * @param params
     * @param headers
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static <T> ResposeBody<T> post(String url, Map<String,String> params,Map<String,String> headers,Class<T> t) {
        return sendRequest(url,POST,params,headers,t);
    }
    /**
     * 发送 GET HTTP 请求
     * @param url 路径
     * @param params 参数
     * @return 响应
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static <T> ResposeBody<T> post(String url, Map<String,String> params,Class<T> t) {
        return post(url,params,null,t);
    }
    /**
     * 发送 GET HTTP 请求
     * @param url 路径
     * @return 响应
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static <T> ResposeBody<T> post(String url,Class<T> t) {
        return post(url,null,null,t);
    }







    /**
     * 上传媒体文件
     * @param url
     * @param file
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static String upload(String url, File file)  {
        AsyncHttpClient http = new AsyncHttpClient();
        try{
            AsyncHttpClient.BoundRequestBuilder builder = http.preparePost(url);
            builder.setBodyEncoding(DEFAULT_CHARSET);
            String BOUNDARY = "----WebKitFormBoundaryiDGnV9zdZA1eM1yL"; // 定义数据分隔线
            builder.setHeader("connection", "Keep-Alive");
            builder.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.107 Safari/537.36");
            builder.setHeader("Charsert", "UTF-8");
            builder.setHeader("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();// 定义最后数据分隔线
            builder.setBody(new UploadEntityWriter(end_data, file));

            Future<Response> future = builder.execute();
            return future.get().getResponseBody(DEFAULT_CHARSET);
        }catch (ExecutionException e1){
            e1.printStackTrace();
        }catch (InterruptedException e2){
            e2.printStackTrace();
        }catch (IOException e3){
            e3.printStackTrace();
        }finally {
            if(null!=http) http.close();
        }
        return null;
    }
    /**
     * 下载资源
     * @param url
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static Attachment download(String url) {
        Attachment att = new Attachment();
        AsyncHttpClient http = new AsyncHttpClient();
        try{
            AsyncHttpClient.BoundRequestBuilder builder = http.prepareGet(url);
            builder.setBodyEncoding(DEFAULT_CHARSET);
            Future<Response> future = builder.execute();
            if (future.get().getContentType().equalsIgnoreCase("text/plain")) {
                att.setError(future.get().getResponseBody(DEFAULT_CHARSET));
            } else {
                BufferedInputStream bis = new BufferedInputStream(future.get().getResponseBodyAsStream());
                String ds = future.get().getHeader("Content-disposition");
                String fullName = ds.substring(ds.indexOf("filename=\"") + 10, ds.length() - 1);
                String relName = fullName.substring(0, fullName.lastIndexOf("."));
                String suffix = fullName.substring(relName.length() + 1);
                att.setFullName(fullName);
                att.setFileName(relName);
                att.setSuffix(suffix);
                att.setContentLength(future.get().getHeader("Content-Length"));
                att.setContentType(future.get().getContentType());
                att.setFileStream(bis);
            }
        }catch (ExecutionException e1){
            e1.printStackTrace();
        }catch (InterruptedException e2){
            e2.printStackTrace();
        }catch (IOException e3){
            e3.printStackTrace();
        }finally {
            if(null!=http) http.close();
        }
        return att;
    }
}
