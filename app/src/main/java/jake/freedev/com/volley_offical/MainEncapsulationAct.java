package jake.freedev.com.volley_offical;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

import java.util.List;

import jake.freedev.com.R;
import jake.freedev.com.entity.CourseCardBean;
import jake.freedev.com.volley_offical.encapsulation.listener.JsonResolver;
import jake.freedev.com.volley_offical.encapsulation.listener.RequestErrorResolver;

/**
 * author: yujie.zhang
 * date: 2018/11/6 11:26
 * content: //比较彻底的封装，封装后的网络请求
 */
public class MainEncapsulationAct extends AppCompatActivity implements JsonResolver<Object>,RequestErrorResolver {

    AppCompatTextView mTextView1,mTextView2;
    RequestQueue requestQueue;
    ImageLoader imageLoader;

    final int CODE_CARDS=0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_offical2);

        mTextView1=findViewById(R.id.mTextView1);
        mTextView2=findViewById(R.id.mTextView2);

        MySingleton mySingleton= MySingleton.getInstance(this);
        requestQueue=mySingleton.getRequestQueue();
        imageLoader=mySingleton.getImageLoader();

        //封装后的发起请求
        int cardType=1;
        String cityCode="0571";
        int areaId=1;
        HttpUtilsVolley.getCourseCard(requestQueue,cardType,cityCode,areaId,this,this,CODE_CARDS);


    }

    @Override
    public void resolve(Object response, int code) {
        if (code == CODE_CARDS) {
            List<CourseCardBean> list = (List<CourseCardBean>) response;
            mTextView1.setText("完整封装请求&完整解析json：" +
                    "explanation=" + list.get(0).getExplanation() + "\n" +
                    "name=" + list.get(0).getName() + "\n" +
                    "useCity=" + list.get(0).getCourseCardLimit().getUseCity());
        }
    }

    @Override
    public void resolveError(int when, Throwable throwable, int code) {
        Toast.makeText(this,"亲，网络塞车了，稍后再试",Toast.LENGTH_SHORT).show();;
    }
}
