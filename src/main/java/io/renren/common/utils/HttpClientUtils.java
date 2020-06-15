package io.renren.common.utils;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtils {

    private static ClientConnectionManager connectionManager = null;
    private static HttpParams httpParams = null;

    static {
        httpParams = new BasicHttpParams();
        ConnManagerParams.setMaxTotalConnections(httpParams, 100);
        ConnManagerParams.setMaxConnectionsPerRoute(httpParams, new ConnPerRouteBean(50));
        HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
        HttpConnectionParams.setSoTimeout(httpParams, 300000);
        SchemeRegistry schreg = new SchemeRegistry();
        schreg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schreg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        connectionManager = new ThreadSafeClientConnManager(httpParams, schreg);
    }

    /**
     * Reset connection timeout and socket timeout for new http connections.
     *
     * @param timeout the timeout value in millisecond.
     */
    public static void setTimeout(int timeout) {
        HttpConnectionParams.setConnectionTimeout(httpParams, timeout);
        HttpConnectionParams.setSoTimeout(httpParams, timeout);
    }

    /**
     * Return a new httpClient using default configuration parameters.
     *
     * @return A new HttpClient object.
     */
    public static HttpClient getHttpClient() {
        return new DefaultHttpClient(connectionManager, httpParams);
    }

    public static String getResponseByGet(final String url, int time) {
        HttpClient httpClient = getHttpClient();
        httpClient.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, time);
        return getResponseByGet(httpClient, url);
    }

    /**
     * Send a http 'get' request to given url and return the response content.
     *
     * @param url the url that the request will be sent to.
     * @return the response content got from the specified url.
     */
    public static String getResponseByGet(String url) {
        return getResponseByGet(new DefaultHttpClient(connectionManager, httpParams), url);
    }

    /**
     * Send a http 'get' request to given url via the given httpClient object, and
     * return the response content.
     *
     * @param httpClient the apache httpClient object that manages the request.
     * @param url        the url that the request will be sent to.
     * @return the response content got from the specified url.
     */
    private static String getResponseByGet(HttpClient httpClient, String url) {
        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String output = "";
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                if (null != entity) {
                    output = EntityUtils.toString(entity);
                }
            }
            if (null != entity) {
                // call the HttpEntity#consumeContent() method to consume any available content on the stream. HttpClient
                // will automatically release the underlying connection back to the connection manager as soon as it
                // detects that the end of the content stream has been reached.
                entity.consumeContent();
            }
            return output;
        } catch (ClientProtocolException e) {
            if (httpGet != null) {
                httpGet.abort();
            }
            throw new RuntimeException("Get URL content HTTP error", e);
        } catch (IOException e) {
            if (httpGet != null) {
                httpGet.abort();
            }
            throw new RuntimeException("Get URL content IO error", e);
        } finally {
            //httpClient.getConnectionManager().shutdown();
        }
    }

    public static byte[] getResponseBytesByGet(String url) {
        HttpClient httpClient = null;
        HttpGet httpGet = null;
        try {
            httpClient = new DefaultHttpClient(connectionManager, httpParams);
            httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            byte[] output = null;
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                if (null != entity) {
                    output = EntityUtils.toByteArray(entity);
                }
            }
            if (null != entity) {
                // call the HttpEntity#consumeContent() method to consume any available content on the stream. HttpClient
                // will automatically release the underlying connection back to the connection manager as soon as it
                // detects that the end of the content stream has been reached.
                entity.consumeContent();
            }
            return output;
        } catch (ClientProtocolException e) {
            if (httpGet != null) {
                httpGet.abort();
            }
        } catch (IOException e) {
            if (httpGet != null) {
                httpGet.abort();
            }
        } finally {
            //httpClient.getConnectionManager().shutdown();
        }
        return null;
    }

    /**
     * Send a http 'post' request to given url and return the response content.
     *
     * @param url       the url that the request will be sent to.
     * @param paramsMap the parameters will be sent to given url.
     * @return the response content got from the specified url.
     */
    public static String getResponseByPost(String url, Map<String, String> paramsMap) {
        if (null == paramsMap || paramsMap.size() == 0) {
            return getResponseByGet(getHttpClient(), url);
        } else {
            return getResponseByPost(getHttpClient(), url, paramsMap, null, null);
        }
    }

    /**
     * get response by post method
     *
     * @param httpClient
     * @param url
     * @param paramsMap
     * @return
     */
    private static String getResponseByPost(HttpClient httpClient, String url, Map<String, String> paramsMap,
                                            String requestXML, Map<String, String> headerMap) {
        HttpPost httpPost = new HttpPost(url);
        try {
            //add params
            if (null != paramsMap && paramsMap.size() > 0) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                for (String name : paramsMap.keySet()) {
                    nvps.add(new BasicNameValuePair(name, paramsMap.get(name)));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            } else {
                StringEntity reqEntity = new StringEntity(requestXML);
                httpPost.setEntity(reqEntity);
            }

            //add http header
            if (null != headerMap && headerMap.size() > 0) {
                List<Header> headers = new ArrayList<Header>();
                for (String name : headerMap.keySet()) {
                    Header header = new BasicHeader(name, headerMap.get(name));
                    headers.add(header);
                }
                httpPost.setHeaders(headers.toArray(headers.toArray(new Header[headers.size()])));
            }

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String output = "";
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                if (null != entity) {
                    output = EntityUtils.toString(entity);
                }
            }
            if (null != entity) {
                // call the HttpEntity#consumeContent() method to consume any available content on the stream. HttpClient
                // will automatically release the underlying connection back to the connection manager as soon as it
                // detects that the end of the content stream has been reached.
                entity.consumeContent();
            }
            return output;
        } catch (Exception e) {
            httpPost.abort();
            return null;
        }
    }

    /**
     * Get https request result
     *
     * @param httpsUrl
     * @return
     */
    public static byte[] getHttpsResult(String httpsUrl) {
        HttpClient httpClient = null;
        HttpGet httpGet = null;
        try {
            HostnameVerifier hostnameVerifier = SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
            DefaultHttpClient client = new DefaultHttpClient();
            SchemeRegistry registry = new SchemeRegistry();
            SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
            socketFactory.setHostnameVerifier((X509HostnameVerifier) hostnameVerifier);
            registry.register(new Scheme("https", socketFactory, 443));
            SingleClientConnManager mgr = new SingleClientConnManager(client.getParams(), registry);
            httpClient = new DefaultHttpClient(mgr, client.getParams());
            // Set verifier
            HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
            httpGet = new HttpGet(httpsUrl);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            byte[] output = null;
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                if (null != entity) {
                    output = EntityUtils.toByteArray(entity);
                }
            }
            if (null != entity) {
                // call the HttpEntity#consumeContent() method to consume any available content on the stream. HttpClient
                // will automatically release the underlying connection back to the connection manager as soon as it
                // detects that the end of the content stream has been reached.
                entity.consumeContent();
            }
            return output;
        } catch (ClientProtocolException e) {
            if (httpGet != null) {
                httpGet.abort();
            }
        } catch (IOException e) {
            if (httpGet != null) {
                httpGet.abort();
            }
        } finally {
            //httpClient.getConnectionManager().shutdown();
        }
        return null;
    }

}
