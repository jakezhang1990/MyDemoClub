package jake.freedev.com.demo1;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
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
import jake.freedev.com.volley_offical.MainActivityOffical;

public class MainActivity extends BaseDemo1Activity {
    String TAG=this.getClass().getSimpleName();
    AppCompatButton mButton;
    AppCompatTextView mTextView;
    RequestQueue requestQueue;
    StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                startActivity(new Intent(MainActivity.this,MainActivityOffical.class));
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
