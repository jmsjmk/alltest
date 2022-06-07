package com.middleserver;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HttpModuleUtil {
    private static final Logger log = LogManager
            .getLogger(HttpModuleUtil.class);

    public static String httpSendPost(String url, String text) throws Exception {
        String responseText = "";
        try {
            log.info("url=" + url);
            log.info("text=" + text);

            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(url);

            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(30000).setConnectionRequestTimeout(30000)
                    .setSocketTimeout(30000).build();
            httppost.setConfig(requestConfig);
            httppost.setHeader("content-type", "application");
            HttpEntity reqEntity = new StringEntity(text, "UTF-8");
            httppost.setEntity(reqEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                log.info("----------------------------------------");
                log.info("response.getStatusLine()=" + response.getStatusLine());

                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    log.error("Response content length: "
                            + resEntity.getContentLength());
                }

                BufferedReader bf = new BufferedReader(new InputStreamReader(
                        resEntity.getContent(), "UTF-8"));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = bf.readLine()) != null) {
                    buffer.append(line);
                }

                log.info("buffer.toString()=" + buffer.toString());
                EntityUtils.consume(resEntity);
                responseText = buffer.toString();
            } finally {
                response.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return responseText;

    }

    //承保和第三方的时候，调用此方法
    public static String seefeeHttpSendPost(String url, String text) throws Exception {
        String responseText = "";
        try {
            log.info("url=" + url);
            log.info("text=" + text);

            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(url);

            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(30000).setConnectionRequestTimeout(30000)
                    .setSocketTimeout(30000).build();
            httppost.setConfig(requestConfig);
            httppost.setHeader("content-type", "application");
            HttpEntity reqEntity = new StringEntity(text, "UTF-8");
            httppost.setEntity(reqEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                log.info("----------------------------------------");
                log.info("response.getStatusLine()=" + response.getStatusLine());

                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    log.error("Response content length: "
                            + resEntity.getContentLength());
                }

                BufferedReader bf = new BufferedReader(new InputStreamReader(
                        resEntity.getContent(), "UTF-8"));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = bf.readLine()) != null) {
                    buffer.append(line);
                }

                log.info("buffer.toString()=" + buffer.toString());
                EntityUtils.consume(resEntity);
                responseText = buffer.toString();
            } finally {
                response.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
        return responseText;

    }

    public static String httpSendGet(String urltext) throws Exception {
        String responseText = "";
        try {
            log.info("urltext=" + urltext);

            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(urltext);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(30000).setConnectionRequestTimeout(30000)
                    .setSocketTimeout(30000).build();
            httpget.setConfig(requestConfig);
            httpget.setHeader("content-type", "application");

            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                log.info("----------------------------------------");
                log.info("response.getStatusLine()=" + response.getStatusLine());


                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    log.error("Response content length: "
                            + resEntity.getContentLength());
                }

                BufferedReader bf = new BufferedReader(new InputStreamReader(
                        resEntity.getContent(), "UTF-8"));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = bf.readLine()) != null) {
                    buffer.append(line);

                }

                log.info("buffer.toString()=" + buffer.toString());
                EntityUtils.consume(resEntity);
                responseText = buffer.toString();
            } finally {
                response.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
        return responseText;

    }


    public static String httpSendPostXML(String url, String text) throws Exception {
        String responseText = "";
        try {
            log.info("url=" + url);
            log.info("text=" + text);

            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(30000).setConnectionRequestTimeout(30000)
                    .setSocketTimeout(30000).build();
            httppost.setConfig(requestConfig);
            httppost.setHeader("content-type", "text/plain; charset=UTF-8");
            HttpEntity reqEntity = new StringEntity(text, "UTF-8");
            httppost.setEntity(reqEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                log.info("----------------------------------------");
                log.info("response.getStatusLine()=" + response.getStatusLine());

                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    log.error("Response content length: "
                            + resEntity.getContentLength());
                }

                BufferedReader bf = new BufferedReader(new InputStreamReader(
                        resEntity.getContent(), "UTF-8"));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = bf.readLine()) != null) {
                    buffer.append(line);
                }

                log.info("buffer.toString()=" + buffer.toString());
                EntityUtils.consume(resEntity);
                responseText = buffer.toString();
            } finally {
                response.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return responseText;

    }


    public static String unirestPost(String url, String text) throws Exception {
        String result = "";
        log.info("url:" + url + "\t" + text);
        try {
            String response = httpSendPost(url, text);
//					.post(url)
//					.header("content-type", "application")
//					.body("message=" + text).asString();
//			if (response.getStatus() == HttpStatus.SC_OK) {
//				result = response.getBody();
//				System.out.println(result);
//			} else {
//				log.info("调用失败!");
//				throw new Exception("调用失败!");
//			}
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return result;
    }

    public static String postFromServer(String url, String text) {
        String responseText = "";
        ClientConnectionManager connManager = new PoolingClientConnectionManager();
        DefaultHttpClient client = new DefaultHttpClient(connManager);

        HttpPost post = new HttpPost(url);
        HttpEntity reqEntity = new StringEntity(text, "UTF-8");
        post.setEntity(reqEntity);
        try {
            CloseableHttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                log.error("Response content length: "
                        + entity.getContentLength());
            }

            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    entity.getContent(), "UTF-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = bf.readLine()) != null) {
                buffer.append(line);
            }

            log.info("buffer.toString()=" + buffer.toString());
            EntityUtils.consume(entity);
            responseText = buffer.toString();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            System.out.println(url);
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return responseText;
    }

    // --add by  mingku.jia
    // ---------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------
    public static void main(String[] args) {
        try {
            String url = "https://test-paynotify.01zhuanche.com/tianan/notify/order";
            Map<String, Object> params = new HashMap<>();
            params.put("applyNo", "9527");
            params.put("driverId", 10000029 + "");
            params.put("replayStatus", "success");
            params.put("nonce", "044f44af-0cc7-4ece-8023-3caf73625a4f");
            params.put("timestamp", "1629277504");
            params.put("sign", "6d2b328fcc743cf7d60f4378508e635bedc54a25");
            String res = post(url, params);
            System.out.println(res);

            // unirestPost("https://test-paynotify.01zhuanche.com/tianan/notify/order?applyNo=test121241434234&driverId=2312&replayStatus=success&nonce=12312&timestamp=wwe&sign=343453","");
            // httpSendGet("https://test-paynotify.01zhuanche.com/tianan/notify/order");
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String post(String url, Map<String, ?> params) {
        return post(url, params, null, "UTF-8");
    }

    public static String post(String url, Map<String, ?> params, Map<String, String> headers, String code) {
        List<NameValuePair> nvps = null;
        if (params != null && params.size() > 0) {
            nvps = new ArrayList<>();
            for (Map.Entry<String, ?> entry : params.entrySet()) {
                String value = entry.getValue() == null ? "" : entry.getValue().toString();
                nvps.add(new BasicNameValuePair(entry.getKey(), value));
            }
        }
        return post(url, nvps, headers, code);
    }

    private static String post(String url, List<NameValuePair> params, Map<String, String> headers, String code) {
        String res = null;
        CloseableHttpResponse response = null;
        HttpEntity entity2 = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    if (entry != null) {
                        httpPost.addHeader(entry.getKey(), entry.getValue());
                    }
                }
            }
            if (params != null) {
                httpPost.setEntity(new UrlEncodedFormEntity(params, code));
            }

            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(30000).setConnectionRequestTimeout(30000)
                    .setSocketTimeout(30000).build();
            httppost.setConfig(requestConfig);

            response = httpclient.execute(httpPost);
            entity2 = response.getEntity();
            res = EntityUtils.toString(entity2, code);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                EntityUtils.consume(entity2);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                }
            }
        }
        return res;
    }


}
