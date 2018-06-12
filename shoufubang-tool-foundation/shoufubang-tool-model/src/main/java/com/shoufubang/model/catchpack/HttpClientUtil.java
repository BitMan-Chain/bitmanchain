package com.shoufubang.model.catchpack;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.ParseException;
import org.apache.http.ProtocolException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EntityUtils;

/**
 * HttpClient工具封装类，Post，Get请求，代理请求
 * 作者：<br>
 * 版本：1.0<br>
 * 创建日期：下午9:21:00<br>
 */
public class HttpClientUtil {

    /***连接超时时间*/
    private Integer connectTimeout =60*1000;

    private Integer socketTimeout =180*1000;
    private CloseableHttpClient httpClient = null;

    private CookieStore cookieStore = new  BasicCookieStore();


    /** 代理请求 */
    public HttpClientUtil(String proxyHost, int proxyPort) {
        this(proxyHost, proxyPort, -1, -1, 0, 0, true);
    }

    /** 默认*/
    public HttpClientUtil() {
        this(null, 0, -1, -1, 0, 0, true);
    }

    /** 进行请求无代理设置连接时间  */
    public HttpClientUtil(int socketTimeout, int connectTimeout) {
        this(null, 0, socketTimeout, connectTimeout, 0, 0, true);
    }


    /**
     * 
     * @param proxyHost  代理主机地址
     * @param proxyPort  代理端口
     * @param socketTimeout
     * @param connectTimeout
     * @param route  
     * @param maxTotal
     * @param followRedirect
     */
    public HttpClientUtil(String proxyHost, int proxyPort, int socketTimeout, int connectTimeout, int route, int maxTotal, boolean followRedirect){
        Builder builder = RequestConfig.custom();
        builder.setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY);
        if (followRedirect) {
            builder.setCircularRedirectsAllowed(true);
            builder.setMaxRedirects(100);
        }
        if (StringUtils.isNotBlank(proxyHost) && proxyPort > 0) {
            builder.setProxy(new HttpHost(proxyHost, proxyPort));
        }

        //设置连接时间
        if (socketTimeout != -1) {
            this.socketTimeout = socketTimeout;
        }
        builder.setSocketTimeout(this.socketTimeout);

        if (connectTimeout != -1) {
            this.connectTimeout = connectTimeout;
        }
        builder.setConnectTimeout(this.connectTimeout);
        builder.setConnectionRequestTimeout(this.connectTimeout);

        RequestConfig requestConfig = builder.build();
        init(requestConfig, route, maxTotal);
    }

    private void init(RequestConfig requestConfig, int route, int maxTotal) {
        X509TrustManager x509mgr = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] xcs, String string) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] xcs, String string) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }

        try {
            sslContext.init(null, new TrustManager[] { x509mgr }, null);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if (executionCount >= 5) {// 如果已经重试了5次，就放弃
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                    return true;
                }
                if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                    return false;
                }
                if (exception instanceof InterruptedIOException) {// 超时
                    return false;
                }
                if (exception instanceof UnknownHostException) {// 目标服务器不可达
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
                    return false;
                }
                if (exception instanceof SSLException) {// ssl握手异常
                    return false;
                }
                // 2016-03-09针对broken pipe的问题做处理
                if (exception instanceof SocketException) {
                    return true;
                }

                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                // 如果请求是幂等的，就再次尝试
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };
        DefaultRedirectStrategy redirectStrategy = new DefaultRedirectStrategy() {
            public boolean isRedirected(HttpRequest request, HttpResponse response, HttpContext context) {
                boolean isRedirect = false;
                try {
                    isRedirect = super.isRedirected(request, response, context);
                } catch (ProtocolException e) {
                    e.printStackTrace();
                }
                if (!isRedirect) {
                    int responseCode = response.getStatusLine().getStatusCode();
                    if (responseCode == 301 || responseCode == 302) {
                        return true;
                    }
                }
                return isRedirect;
            }

            @Override
            protected URI createLocationURI(String location) throws ProtocolException {
                location = location.replace("|", "%7C");
                return super.createLocationURI(location);
            }
        };

        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setDefaultRequestConfig(requestConfig);
        if (route > 0 && maxTotal > 0) {
            PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
            connManager.setDefaultMaxPerRoute(route);
            connManager.setMaxTotal(maxTotal);
            httpClientBuilder.setConnectionManager(connManager);
        }

        httpClientBuilder.setSSLSocketFactory(sslsf);
        httpClientBuilder.setDefaultCookieStore(cookieStore);
        httpClientBuilder.setRedirectStrategy(redirectStrategy);
        httpClientBuilder.setRetryHandler(httpRequestRetryHandler);
        httpClient = httpClientBuilder.build();
    }
    public Response sendRequest(Request request) throws Exception {
        // request.setHeader("Accept",
        // "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        // request.setHeader("Accept-Encoding", "gzip, deflate");
        if (request.getHeader("User-Agent") == null) {
            request.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:24.0) Gecko/20100101 Firefox/24.0");
        }
        request.setHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
        request.setHeader("Connection", "keep-alive");

        // logger.debug("发送请求:" + request);

        String method = request.getProperty("method").toLowerCase();
        String url = request.getProperty("url");
        Map<String, String> headers = request.getHeaders();
        Map<String, String> params = request.getParams();

        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        if (params != null && params.size() != 0) {
            formParams = new ArrayList<NameValuePair>();
            for (Entry<String, String> entry : params.entrySet()) {
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        Response response = null;
        if ("post".equals(method)) {
            byte[] postData = request.getPostData();
            if (postData != null) {
                response = post(url, postData, headers);
            } else {
                response = post(url, formParams, headers);
            }
        } else if ("get".equals(method)) {
            response = get(url, formParams, headers);
        }
        return response;
    }

    /**
     * Get请求
     * 
     * @param url
     * @param params
     * @return
     */
    public Response get(String url, List<NameValuePair> params, Map<String, String> headers) {
        Response response = new Response();
        try {
            // Get请求
            HttpGet httpGet = new HttpGet(url);

            String encoding = "utf-8";

            // 设置头
            if (headers != null && headers.size() != 0) {
                for (Entry<String, String> entry : headers.entrySet()) {
                    httpGet.setHeader(entry.getKey(), entry.getValue());
                }

                String contentType = headers.get("Content-Type");
                if (StringUtils.isNotBlank(contentType)) {
                    if (matcher(contentType, "(charset)\\s?=\\s?(gbk)")) {
                        encoding = "gbk";
                    } else if (matcher(contentType, "(charset)\\s?=\\s?(gb2312)")) {
                        encoding = "gb2312";
                    }
                }
            }

            // 设置参数,如果url上已经有了问号，就不附加参数
            if (params != null && params.size() > 0) {
                if (httpGet.getURI().toString().indexOf("?") == -1) {
                    String str = EntityUtils.toString(new UrlEncodedFormEntity(params, encoding));
                    httpGet.setURI(new URI(httpGet.getURI().toString() + "?" + str));
                } else {
                    String str = EntityUtils.toString(new UrlEncodedFormEntity(params, encoding));
                    httpGet.setURI(new URI(httpGet.getURI().toString() + "&" + str));
                }
            }

            // 发送请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

            try {
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                response.setStatusCode(statusCode);
                ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                // 获取返回数据
                HttpEntity entity = httpResponse.getEntity();

                Header[] responseHeaders = httpResponse.getAllHeaders();
                for (Header header : responseHeaders) {
                    response.setHeader(header.getName(), header.getValue());
                }

                Header header = entity.getContentEncoding();
                if (header != null && header.getValue().toLowerCase().equals("gzip")) {
                    byte[] bytes = IOUtils.toByteArray(new GZIPInputStream(entity.getContent()));
                    response.setContent(bytes);
                } else {
                    byte[] bytes = getData(entity);
                    response.setContent(bytes);
                }
                return response;
            } finally {
                httpResponse.close();
            }
        } catch (ConnectTimeoutException e) {
        } catch (HttpHostConnectException e) {
        } catch (ParseException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (IOException e) {
        } catch (URISyntaxException e) {
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * // Post请求
     * 
     * @param url
     * @param params
     * @return
     */
    public Response post(String url, byte[] data, Map<String, String> headers) {
        Response response = new Response();
        try {
            // Post请求
            HttpPost httpPost = new HttpPost(url);
            // 设置头
            if (headers != null && headers.size() != 0) {
                for (Entry<String, String> entry : headers.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }

            // 设置参数
            httpPost.setEntity(new ByteArrayEntity(data));

            // 发送请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

            try {
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                response.setStatusCode(statusCode);

                // 获取返回数据
                HttpEntity entity = httpResponse.getEntity();

                Header header = entity.getContentEncoding();
                if (header != null && header.getValue().toLowerCase().equals("gzip")) {
                    byte[] bytes = IOUtils.toByteArray(new GZIPInputStream(entity.getContent()));
                    response.setContent(bytes);
                } else {
                    byte[] bytes = EntityUtils.toByteArray(entity);
                    response.setContent(bytes);
                }

                return response;
            } finally {
                httpResponse.close();
            }
        } catch (ConnectTimeoutException e) {
        } catch (HttpHostConnectException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (ClientProtocolException e) {
        } catch (ParseException e) {
        } catch (IOException e) {
        } catch (Exception e) {
        }
        return null;
    }
    private byte[] getData(HttpEntity entity) throws IOException {
        if (entity == null) {
            throw new IllegalArgumentException("HTTP entity may not be null");
        }
        InputStream inputStream = entity.getContent();
        if (inputStream == null) {
            return null;
        }
        try {
            if (entity.getContentLength() > Integer.MAX_VALUE) {
                throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
            }
            int i = (int) entity.getContentLength();
            if (i < 0) {
                i = 4096;
            }

            ByteArrayBuffer buffer = new ByteArrayBuffer(i);
            byte[] tmp = new byte[1024];
            int l = -1;
            try {
                while ((l = inputStream.read(tmp)) != -1) {
                    buffer.append(tmp, 0, l);
                }
            } catch (EOFException e) {
                buffer.clear();
                // 针对于以没有结束符的做fix处理,减小缓存,并进行异常处理,忽略最后不能获取的数据
                tmp = new byte[32];
                try {
                    while ((l = inputStream.read(tmp)) != 1) {
                        buffer.append(tmp, 0, l);
                    }
                } catch (EOFException e2) {
                }

            }
            // TODO 查明具体没有返回的原因
            byte[] byteArray = buffer.toByteArray();
            if (byteArray == null || byteArray.length == 0) {
                return buffer.buffer();
            }
            return byteArray;
        } finally {
            inputStream.close();
        }
    }
    /**
     * // Post请求
     * 
     * @param url
     * @param params
     * @return
     */
    public Response post(String url, List<NameValuePair> params, Map<String, String> headers) {
        Response response = new Response();
        try {
            // Post请求
            HttpPost httpPost = new HttpPost(url);

            String encoding = "utf-8";

            // 设置头
            if (headers != null && headers.size() != 0) {
                for (Entry<String, String> entry : headers.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }

                String contentType = headers.get("Content-Type");
                if (StringUtils.isNotBlank(contentType)) {
                    if (matcher(contentType, "(charset)\\s?=\\s?(gbk)")) {
                        encoding = "gbk";
                    } else if (matcher(contentType, "(charset)\\s?=\\s?(gb2312)")) {
                        encoding = "gb2312";
                    }
                }
            }

            // 设置参数
            if (params != null && params.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(params, encoding));
            }

            // 发送请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

            try {
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                response.setStatusCode(statusCode);

                // 获取返回数据
                HttpEntity entity = httpResponse.getEntity();

                Header header = entity.getContentEncoding();
                if (header != null && header.getValue().toLowerCase().equals("gzip")) {
                    byte[] data = IOUtils.toByteArray(new GZIPInputStream(entity.getContent()));
                    response.setContent(data);
                } else {
                    byte[] data = getData(entity);
                    response.setContent(data);
                }

                return response;
            } finally {
                httpResponse.close();
            }
        } catch (ConnectTimeoutException e) {
        } catch (HttpHostConnectException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (ClientProtocolException e) {
        } catch (ParseException e) {
        } catch (IOException e) {
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 获取Response内容字符集
     * 
     * @param response
     * @return
     */
    public String getContentCharset(HttpResponse response) {
        String charset = "ISO_8859-1";
        Header header = response.getEntity().getContentType();
        if (header != null) {
            String s = header.getValue();
            if (matcher(s, "(charset)\\s?=\\s?(utf-?8)")) {
                charset = "utf-8";
            } else if (matcher(s, "(charset)\\s?=\\s?(gbk)")) {
                charset = "gbk";
            } else if (matcher(s, "(charset)\\s?=\\s?(gb2312)")) {
                charset = "gb2312";
            }
        }

        Header encoding = response.getEntity().getContentEncoding();

        return charset;
    }

    /**
     * 正则匹配
     * 
     * @param s
     * @param pattern
     * @return
     */
    private boolean matcher(String s, String pattern) {
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE + Pattern.UNICODE_CASE);
        Matcher matcher = p.matcher(s);
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public CookieStore getCookieStore() {
        return cookieStore;
    }

    public void setCookieStore(CookieStore cookieStore) {
        for(Cookie cookie:cookieStore.getCookies()){
            this.cookieStore.addCookie(cookie);
        }
    }
	public static void main(String[] args) {
		HttpClientUtil hc = new HttpClientUtil();
		Response response = new Response();
		response=hc.get("https://etherscan.io/token/0x1e797ce986c3cff4472f7d38d5c4aba55dfefe40?a=0x05ee546c1a62f90d7acbffd6d846c9c54c7cf94c", null, null);
		String s = new String(response.getContent());
		s = s.substring(s.indexOf("Token Balance"), s.indexOf("Token Value:"));
		System.out.println("开始："+s.indexOf("<td>"));
		int index = s.indexOf("</td>");
		index = s.indexOf("</td>", index + 1);
	    s =s.substring(s.indexOf("<td>") + 4,index);
		System.out.println(s);
	}
}