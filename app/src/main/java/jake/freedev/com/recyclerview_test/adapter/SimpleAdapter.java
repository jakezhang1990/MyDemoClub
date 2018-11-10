package jake.freedev.com.recyclerview_test.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * author: yujie.zhang
 * date: 2018/11/10 10:09
 * content: //简单使用RecycleView的适配器
 */
public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.MyViewHolder> {

    private ArrayList<String> list;
    public SimpleAdapter(ArrayList<String> list){
        this.list=list;
    }

    /**
     * onCreateViewHolder这里进行itemview的创建渲染；
     * @param viewGroup 父容器
     * @param position item的脚标
     * @return
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        MyViewHolder holder=new MyViewHolder(View.inflate(viewGroup.getContext(),android.R.layout.simple_list_item_1,null));
        return holder;
    }

    /**
     * onBindViewHolder这里进行item数据的绑定
     * @param myViewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * viewholder继承recyclerView的viewholder；
     * 与listview中的viewholder功能一样，这里进行控件的findViewById绑定
     */
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(android.R.id.text1);
        }
    }
}
