package jake.freedev.com.volley_offical.encapsulation;

import android.support.annotation.Nullable;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * author: yujie.zhang
 * date: 2018/10/31 14:45
 * content: //TODO
 */
public class VolleyRequest extends Request<String> {
    private RequestErrorResolver errorLst;
    private TextResponseListener successLst;
    private int code;

    private String domain;
    private String charset = "UTF-8";

    private Map<String, ? extends Object> params;

    public VolleyRequest(int method, String url, Map<String, ? extends Object> params, TextResponseListener listener,RequestErrorResolver errorListener,int code) {
        super(method, url, null);

        setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        this.params=params;
        this.successLst=listener;
        this.errorLst=errorListener;
        this.code=code;
    }

    /**
     * 带请求参数，带请求标识的 get 请求
     * @param url
     * @param params
     * @param listener
     * @param errorListener
     * @param code
     * @return
     */
    public static VolleyRequest get(String url,Map<String,?> params,TextResponseListener listener,RequestErrorResolver errorListener,int code){
        return new VolleyRequest(Method.GET,url,params,listener,errorListener,code);
    }

    /**
     * 带请求参数，不带请求标识的 get 请求
     * @param url
     * @param params
     * @param listener
     * @param errorLst
     * @return
     */
    public static VolleyRequest get(String url,Map<String,?> params,TextResponseListener listener,RequestErrorResolver errorLst){
        return get(url,params,listener,errorLst,0);
    }

    /**
     * 不带请求参数的 get 请求
     * @param url
     * @param listener
     * @param errorListener
     * @param code
     * @return
     */
    public static VolleyRequest get(String url,TextResponseListener listener,RequestErrorResolver errorListener,int code){
        return get(url,null,listener,errorListener,code);
    }

    /**
     * 不带请求参数，不带请求标识的 get 请求
     * @param url
     * @param listener
     * @param errorLst
     * @return
     */
    public static VolleyRequest get(String url,TextResponseListener listener,RequestErrorResolver errorLst){
        return get(url,listener,errorLst,0);
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

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    /*public Map<String, ? extends Object> getParams() {
        return params;
    }

    public void setParams(Map<String, ? extends Object> params) {
        this.params = params;
    }*/

    @Override
    public String getUrl() {
        String u = super.getUrl();
        if (domain != null && !"".equals(domain))
            u = domain + u;

        if (!needBody()) {
            String ps = encodeParameters();
            if (ps != null) {
                u = u + (u.indexOf('?') == -1 ? "?" : "&") + ps;
            }
        }
        return u;
    }

    /**
     * Converts <code>params</code> into an application/x-www-form-urlencoded
     * encoded string.
     */
    protected String encodeParameters() {
        StringBuilder encodedParams = new StringBuilder();
//        Map<String, ? extends Object> params = getParams();
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

    protected boolean needBody() {
        return Method.POST == getMethod() || Method.DEPRECATED_GET_OR_POST == getMethod() || Method.PUT == getMethod()
                || Method.PATCH == getMethod();
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        return null;
    }

    @Override
    protected void deliverResponse(String response) {

    }
}
