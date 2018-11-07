package jake.freedev.com.volley_test.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import jake.freedev.com.HTTPurl;
import jake.freedev.com.R;
import jake.freedev.com.basic_ui.BaseActivity;

/**
 * volley,最简单的使用
 * simpleTest
 */
public class VolleyTestAct0 extends BaseActivity {
    String TAG=this.getClass().getSimpleName();
    AppCompatButton mButton;
    AppCompatTextView mTextView;
    RequestQueue requestQueue;
    StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_test0);
        mButton=findViewById(R.id.mButton);
        mTextView=findViewById(R.id.mTextView);
        requestQueue= Volley.newRequestQueue(this);

        stringRequest=new StringRequest(Request.Method.GET, HTTPurl.url_string_path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mTextView.setText("Response is: "+ response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });
        stringRequest.setTag(TAG);
        requestQueue.add(stringRequest);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VolleyTestAct0.this,VolleyTestAct1.class));
            }
        });





    }


    @Override
    protected void onStop() {
        super.onStop();
        if(requestQueue!=null){
            requestQueue.cancelAll(TAG);
        }
    }
}
