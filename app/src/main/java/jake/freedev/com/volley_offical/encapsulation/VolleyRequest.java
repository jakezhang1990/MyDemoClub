package jake.freedev.com.volley_offical.encapsulation;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import jake.freedev.com.volley_offical.encapsulation.listener.RequestErrorResolver;
import jake.freedev.com.volley_offical.encapsulation.listener.TextResponseListener;

/**
 * author: yujie.zhang
 * date: 2018/11/2 10:52
 * content: //volley Request请求封装
 */
public class VolleyRequest extends Request<String> {
    public static final int DEFAULT_MAX_RETRIES = 3;

    private RequestErrorResolver errorLst;
    private TextResponseListener successLst;
    private int code;

    private String domain;
    private String charset = "UTF-8";

    private Map<String, ? extends Object> params;

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public RequestErrorResolver getErrorLst() {
        return errorLst;
    }

    public void setErrorLst(RequestErrorResolver errorLst) {
        this.errorLst = errorLst;
    }

    public TextResponseListener getSuccessLst() {
        return successLst;
    }

    public void setSuccessLst(TextResponseListener successLst) {
        this.successLst = successLst;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }


    public Map<String, ? extends Object> getParameters() {
        return params;
    }

    public void setParameters(Map<String, ? extends Object> params) {
        this.params = params;
    }

    /**
     *
     * @param method 方法是post还是get
     * @param url 请求地址
     * @param params 请求参数
     * @param listener 请求成功的响应
     * @param errorLst 请求失败的响应
     * @param code 请求标记
     */
    private VolleyRequest(int method, String url, Map<String, ? extends Object> params,TextResponseListener listener,RequestErrorResolver errorLst,int code) {
        super(method, url, null);
        setRetryPolicy(new DefaultRetryPolicy());//设置请求的重试策略，这么说来，都是从缓存中获取的请求，在super已经发起了请求并且缓存了，重试策略先去缓存找，找不到再排队进入请求队列
        this.params=params;
        this.successLst=listener;
        this.errorLst=errorLst;
        this.code=code;
    }

    /**
     * 参数最全的，发起 get 请求
     * @param url
     * @param params
     * @param listener
     * @param errorLst
     * @param code
     * @return
     */
    public static VolleyRequest get(String url,Map<String, ? extends Object> params, TextResponseListener listener,RequestErrorResolver errorLst,int code){
        return new VolleyRequest(Method.GET,url,params,listener,errorLst,code);
    }

    /**
     *
     * @param url
     * @param params
     * @param listener
     * @param errorLst
     * @return
     */
    public static VolleyRequest get(String url,Map<String, ? extends Object> params, TextResponseListener listener,RequestErrorResolver errorLst){
        return get(url,params,listener,errorLst,0);
    }

    /**
     *
     * @param url
     * @param listener
     * @param errorLst
     * @param code
     * @return
     */
    public static VolleyRequest get(String url,TextResponseListener listener,RequestErrorResolver errorLst,int code){
        return get(url,null,listener,errorLst,code);
    }

    /**
     *
     * @param url
     * @param listener
     * @param errorLst
     * @return
     */
    public static VolleyRequest get(String url,TextResponseListener listener , RequestErrorResolver errorLst ){
        return get(url,listener,errorLst,0);
    }

    /**
     * 参数最全的 发起 post 请求
     * @param url
     * @param params
     * @param listener
     * @param errorLst
     * @param code
     * @return
     */
    public static VolleyRequest post(String url,Map<String, ? extends Object> params,TextResponseListener listener,RequestErrorResolver errorLst,int code){
        return new VolleyRequest(Method.POST,url,params,listener,errorLst,code);
    }

    /**
     *
     * @param url
     * @param listener
     * @param errorLst
     * @param code
     * @return
     */
    public static VolleyRequest post(String url, TextResponseListener listener,RequestErrorResolver errorLst,int code){
        return post(url,null,listener,errorLst,code);
    }

    /**
     *
     * @param url
     * @param params
     * @param listener
     * @param errorLst
     * @return
     */
    public static  VolleyRequest post(String url,Map<String, ? extends Object> params,TextResponseListener listener,RequestErrorResolver errorLst){
        return post(url,params,listener,errorLst,0);
    }

    /**
     *
     * @param url
     * @param params
     * @param listener
     * @return
     */
    public static VolleyRequest post(String url,Map<String, ? extends Object> params,TextResponseListener listener){
        return post(url,params,listener,null,0);
    }



    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed=new String(response.data, HttpHeaderParser.parseCharset(response.headers, getCharset()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            parsed=new String(response.data);
        }

        return Response.success(parsed,HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(String response) {
        TextResponseListener lst=getSuccessLst();
        if(lst!=null){
            lst.onResponse(response,code);
        }
    }

    @Override
    public void deliverError(VolleyError error) {
        RequestErrorResolver lst=getErrorLst();
        if(lst!=null){
            lst.resolveError(RequestErrorResolver.WHEN_UNKNOWN,error,code);
        }
    }


    public byte[] getBody() throws AuthFailureError {
        if (needBody()) {
            String ps = encodeParameters();
            if (ps != null) {
                return ps.getBytes();
            }
        }
        return null;
    }

    protected boolean needBody() {
        return Method.POST == getMethod() || Method.DEPRECATED_GET_OR_POST == getMethod() || Method.PUT == getMethod()
                || Method.PATCH == getMethod();
    }

    /**
	 * Converts <code>params</code> into an application/x-www-form-urlencoded
	 * encoded string.
     */
    protected String encodeParameters() {
        StringBuilder encodedParams = new StringBuilder();
        Map<String, ? extends Object> params = getParameters();
        if (params == null || params.isEmpty())
            return null;
        String paramsEncoding = getParamsEncoding();
        try {
            Object v;
            for (Map.Entry<String, ? extends Object> entry : params.entrySet()) {
                v = entry.getValue();
                if (v != null) {
                    encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                    encodedParams.append('=');
                    encodedParams.append(URLEncoder.encode(v.toString(), paramsEncoding));
                    encodedParams.append('&');
                }
            }
            return encodedParams.toString();
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
        }
    }
}
