
package jake.freedev.com.recyclerview_test.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import jake.freedev.com.R;
import jake.freedev.com.recyclerview_test.adapter.WaterfallAdapter;

/**
 * recyclerView的瀑布流效果
 */
public class WaterfallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_waterfall);
        RecyclerView recyclerView=findViewById(R.id.waterfallRV);
        Button rvButton2=findViewById(R.id.rvButton2);
        ArrayList<String> list=new ArrayList<>();
        for (int i=0;i<25;i++){
            list.add("item"+i);
        }
        WaterfallAdapter adapter=new WaterfallAdapter(list);
        //瀑布流效果设置。
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        //底部按钮
        rvButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
