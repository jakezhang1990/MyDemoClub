package jake.freedev.com.volley_test.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
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
import jake.freedev.com.volley_test.encapsulation.QueueInstanceManager;
import jake.freedev.com.volley_test.encapsulation.listener.RequestErrorResolver;
import jake.freedev.com.volley_test.encapsulation.listener.TextResponseListener;
import jake.freedev.com.volley_test.encapsulation.VolleyRequest;

/**
 * author: yujie.zhang
 * date: 2018/10/26 18:56
 * content: //官方volley网络请求测试
 * volley支持，字符串.Json.图像
 */
public class VolleyTestAct1 extends AppCompatActivity implements TextResponseListener , RequestErrorResolver, View.OnClickListener {
    String TAG=this.getClass().getSimpleName();
    AppCompatButton mButton;
    AppCompatTextView mTextView_String,mTextView_jsonObject,mTextView_jsonArray;
    AppCompatTextView mTextView_customVolley;
    NetworkImageView mNetworkImageView;
    RequestQueue requestQueue;
    ImageLoader imageLoader;
    StringRequest stringRequest;
    JsonObjectRequest jsonObjectRequest;
    JsonArrayRequest jsonArrayRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_test1);

        initComponents();
        QueueInstanceManager requestManager = QueueInstanceManager.getInstance(this);
        requestQueue= requestManager.getRequestQueue();
        imageLoader= requestManager.getImageLoader();

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

        requestManager.addRequestToRequestQueue(stringRequest);
        requestManager.addRequestToRequestQueue(jsonObjectRequest);
        requestManager.addRequestToRequestQueue(jsonArrayRequest);

        //不彻底的封装
        requestManager.getRequestQueue().add(VolleyRequest.get(HTTPurl.url_string_path,this,this,100));



    }

    @Override
    protected void onStart() {
        super.onStart();
        mButton.setOnClickListener(this);
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

        mTextView_customVolley=findViewById(R.id.mTextView_customVolley);
    }

    @Override
    public void resolveError(int when, Throwable throwable, int code) {
        mTextView_customVolley.setText(when+" ====="+throwable.toString()+"===="+code);
    }

    @Override
    public void onResponse(String response, int code) {
        mTextView_customVolley.append("增加=+++"+response+"+++"+code);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mButton)){
            startActivity(new Intent(this,VolleyTestAct2.class));
        }
    }
}
