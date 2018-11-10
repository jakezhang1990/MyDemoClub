package jake.freedev.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import jake.freedev.com.recyclerview_test.ui.SimpleActivity;
import jake.freedev.com.volley_test.ui.VolleyTestAct0;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button volleyTest,OKhttpTest,recycleViewTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        volleyTest=findViewById(R.id.volleyTest);
        OKhttpTest=findViewById(R.id.OKhttpTest);
        recycleViewTest=findViewById(R.id.recycleViewTest);
    }

    @Override
    protected void onStart() {
        super.onStart();
        volleyTest.setOnClickListener(this);
        OKhttpTest.setOnClickListener(this);
        recycleViewTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(volleyTest)){
            //volley测试
            startActivity(new Intent(this, VolleyTestAct0.class));
        }else if(v.equals(OKhttpTest)){
            //OKhttp测试
            Toast.makeText(this, "OKhttp测试", Toast.LENGTH_SHORT).show();
        }else if(v.equals(recycleViewTest)){
            //recycleView
            startActivity(new Intent(this, SimpleActivity.class));
        }
    }
}
