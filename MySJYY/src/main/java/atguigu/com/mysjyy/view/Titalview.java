package atguigu.com.mysjyy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import atguigu.com.mysjyy.R;

/**
 * Created by ASUS on 2017/5/18.
 */
//自定义的tital 标题栏   拷贝全类名，替换<include layout="@layout/tital"/> 点进去的 layout/tital中的LinearLayout
public class Titalview extends LinearLayout implements View.OnClickListener {

    private final Context context;
    private TextView tv_sousuo;
    private RelativeLayout rl;
    private ImageView jilu;

    //可以绘制视图代码
    public Titalview(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

    }

    //当布局加载完成的时候回调
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tv_sousuo = (TextView) getChildAt(1);
        rl = (RelativeLayout) getChildAt(2);
        jilu = (ImageView) getChildAt(3);

        tv_sousuo.setOnClickListener(this);
        rl.setOnClickListener(this);
        jilu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_sousuo:
                Toast.makeText(context, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl:
                Toast.makeText(context, "游戏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.jilu:
                Toast.makeText(context, "播放记录", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
