package jake.freedev.com.recyclerview_test.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import jake.freedev.com.R;
import jake.freedev.com.recyclerview_test.adapter.SimpleAdapter;

/**
 * recyclerView的简单使用
 *
 */
public class SimpleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_simple);
        RecyclerView recycleView = findViewById(R.id.recycleView);
        ArrayList<String> list=new ArrayList<>();
        for (int i=0;i<15;i++){
            list.add("item"+i);
        }
        SimpleAdapter adapter=new SimpleAdapter(list);
        //要在setAdapter之前设置这个LayoutManager，默认是垂直的
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        //网格，类似GridView效果
//        recycleView.setLayoutManager(new GridLayoutManager(this,3));
        recycleView.setAdapter(adapter);

        Button rvButton1=findViewById(R.id.rvButton1);
        rvButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //瀑布流效果
                startActivity(new Intent(SimpleActivity.this,WaterfallActivity.class));
            }
        });
    }
}
