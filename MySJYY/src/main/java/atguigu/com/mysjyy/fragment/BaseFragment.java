package atguigu.com.mysjyy.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ASUS on 2017/5/19.
 */


//创建一个Fragment的基类
public abstract class BaseFragment extends Fragment {

    public Context context;

    @Override  //当创建的时候回调
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override //当创建View  之后会调用此方法
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    public abstract View initView();  //创建一个抽象方法，当子类调用的时候去实现各自需要的

    @Override //创建Activity成功的时候会回调此方法   在这里面初始化，绑定数据
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    //当子类需要的时候可以初始化信息  比如联网请求  ····
    public void initData() {

    }


    //布局加载完成后  会 创建 Activity  同时在此方法中初始化配置的数据就可以了
}
