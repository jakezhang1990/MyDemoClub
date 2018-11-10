package jake.freedev.com.recyclerview_test.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import jake.freedev.com.R;

/**
 * author: yujie.zhang
 * date: 2018/11/10 13:49
 * content: //瀑布流效果适配器
 */
public class WaterfallAdapter extends RecyclerView.Adapter<WaterfallAdapter.MyViewHolder> {

    private ArrayList<String> list;
    private ArrayList<Integer> heights;
    public WaterfallAdapter(ArrayList<String> list){
        this.list=list;
        heights=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            int random=50+(int)(Math.random()*100);
            heights.add(random);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        MyViewHolder holder=new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.simple_item_layout,viewGroup,false));
//        MyViewHolder holder=new MyViewHolder(View.inflate(viewGroup.getContext(),R.layout.simple_item_layout,null));
        MyViewHolder holder=new MyViewHolder(View.inflate(viewGroup.getContext(),android.R.layout.simple_list_item_1,null));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        //在这里设置item布局的宽高，达到瀑布流效果
//        ViewGroup.LayoutParams params= myViewHolder.textView.getLayoutParams();
//        params.height=heights.get(i);
//        myViewHolder.textView.setLayoutParams(params);
        myViewHolder.textView.setBackgroundColor(Color.rgb(100,(int)(Math.random()*255),(int)(Math.random()*200)));
        //TODO 如果item布局外层没有LinearLayout或其他的容器，获取params就会为null，设置高度只需要直接设置即可，不需要通过params就可以设置了。
        myViewHolder.textView.setHeight(heights.get(i));

        myViewHolder.textView.setText(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            textView=itemView.findViewById(R.id.textView);
            textView=itemView.findViewById(android.R.id.text1);
        }
    }
}
