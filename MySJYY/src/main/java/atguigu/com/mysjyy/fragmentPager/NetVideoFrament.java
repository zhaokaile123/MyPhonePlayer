package atguigu.com.mysjyy.fragmentPager;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import atguigu.com.mysjyy.fragment.BaseFragment;

/**
 * Created by ASUS on 2017/5/19.
 */

public class NetVideoFrament extends BaseFragment {

    private TextView textView;
    @Override
    public View initView() {
        textView = new TextView(context);

        textView.setTextSize(40);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {

        textView.setText("网络视频出现了");
    }
}
