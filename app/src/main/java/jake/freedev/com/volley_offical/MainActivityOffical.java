package jake.freedev.com.volley_offical;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import jake.freedev.com.HTTPurl;
import jake.freedev.com.R;

/**
 * author: yujie.zhang
 * date: 2018/10/26 18:56
 * content: //官方volley网络请求测试
 * volley支持，字符串.Json.图像
 */
public class MainActivityOffical extends AppCompatActivity {
    String TAG=this.getClass().getSimpleName();
    AppCompatButton mButton;
    AppCompatTextView mTextView_String,mTextView_jsonObject,mTextView_jsonArray;
    NetworkImageView mNetworkImageView;
//    RequestQueue requestQueue;
    ImageLoader imageLoader;
    StringRequest stringRequest;
    JsonObjectRequest jsonObjectRequest;
    JsonArrayRequest jsonArrayRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_offical);

        initComponents();
        MySingleton mySingleton= MySingleton.getInstance(this);
//        requestQueue=mySingleton.getRequestQueue();
        imageLoader=mySingleton.getImageLoader();

        //字符串请求
        stringRequest=new StringRequest(Request.Method.GET, HTTPurl.url_string_path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mTextView_String.setText("String Response is: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView_String.setText("StringRequest That didn't work!"+error.toString());
            }
        });
        //jsonObject请求
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, HTTPurl.url_jsonobject_path+"hah", null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                mTextView_jsonObject.setText("jsonObject Response:"+response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView_jsonObject.setText("JsonObjectRequest That didn't work!"+error.toString());
            }
        });

        //jsonArray请求
        jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, HTTPurl.url_jsonArray_path, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                mTextView_jsonArray.setText("jsonArray Response:"+response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView_jsonObject.setText("JsonArray That didn't work!"+error.toString());
            }
        });

        //NetworkImageView展示图片
        mNetworkImageView.setImageUrl(HTTPurl.url_img_path,imageLoader);

        mySingleton.addRequestToRequestQueue(stringRequest);
        mySingleton.addRequestToRequestQueue(jsonObjectRequest);
        mySingleton.addRequestToRequestQueue(jsonArrayRequest);
    }

    /**
     * 实例化组件
     */
    private void initComponents() {
        mButton=findViewById(R.id.mButton);
        mTextView_String=findViewById(R.id.mTextView_String);
        mTextView_jsonObject=findViewById(R.id.mTextView_jsonObject);
        mTextView_jsonArray=findViewById(R.id.mTextView_jsonArray);
        mNetworkImageView=findViewById(R.id.mNetworkImageView);
    }
}
