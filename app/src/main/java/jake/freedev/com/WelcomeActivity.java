package jake.freedev.com;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;

import jake.freedev.com.volley_test.ui.VolleyTestAct0;

public class WelcomeActivity extends AppCompatActivity {

    private AppCompatImageView appCompatImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        appCompatImageView=findViewById(R.id.appCompatImageView);
        //logo逐渐显示的动画
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(appCompatImageView,"alpha",0f,1f);
        objectAnimator.setDuration(2000);
        objectAnimator.start();
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //logo完全显示后，设置放大动画
                ObjectAnimator objectAnimatorX=ObjectAnimator.ofFloat(appCompatImageView,"scaleX",1.0f,3.5f);
                ObjectAnimator objectAnimatorY=ObjectAnimator.ofFloat(appCompatImageView,"scaleY",1.0f,3.5f);
                //通过动画集合组合执行x，y两个动画
                AnimatorSet animatorSet=new AnimatorSet();
                animatorSet.setDuration(3000);
                animatorSet.play(objectAnimatorX).with(objectAnimatorY);
                animatorSet.start();
                objectAnimatorX.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                    }
                });
            }
        });
    }
}
