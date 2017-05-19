package atguigu.com.mysjyy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import java.util.ArrayList;

import atguigu.com.mysjyy.fragment.BaseFragment;
import atguigu.com.mysjyy.fragmentPager.LocalMusicFragment;
import atguigu.com.mysjyy.fragmentPager.LocalVideoFragment;
import atguigu.com.mysjyy.fragmentPager.NetMusicFragment;
import atguigu.com.mysjyy.fragmentPager.NetVideoFrament;

public class MainActivity extends AppCompatActivity{

    private RadioGroup rg_main;
    private ArrayList<BaseFragment> fragments;
    private int position;
    private Fragment tempFragment; // 缓存的fragement
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        initFragment();//

        //RadioButton 的监听事件
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_local_vido:
                        position = 0;
                        break;
                    case R.id.rb_local_music:
                        position = 1;
                        break;
                    case R.id.rb_net_music:
                        position = 2;
                        break;
                    case R.id.rb_net_vido:
                        position = 3;
                        break;
                }
                BaseFragment currentFragment = fragments.get(position);//记录fragment的实时下标
                addFragment(currentFragment); //添加
            }
        });
    }

    //
    private void addFragment(BaseFragment currentFragment) {

        if(tempFragment != currentFragment) {  // 说明点击的不是正在显示的

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();//开启事务
            //如果以前没有添加过，表示现在点击的这个 fragment  没有缓存
            if(!currentFragment.isAdded()) {
                //上一个显示的  fragment 隐藏
                if(tempFragment != null) {
                    ft.hide(tempFragment);
                }
                //添加现在点击 的 fragment  提交
                ft.add(R.id.fl_content,currentFragment).commit();
            }else{  //已经添加了，有缓存了
                if(tempFragment != null) {
                    ft.hide(tempFragment); // 隐藏
                }
                 //直接显示添加过的
                ft.show(currentFragment).commit();
            }

            tempFragment = currentFragment; // 添加到缓存中
        }
    }

    //添加fragment到集合中
    private void initFragment(){
        fragments = new ArrayList<>();

        fragments.add(new LocalVideoFragment());
        fragments.add(new LocalMusicFragment());
        fragments.add(new NetMusicFragment());
        fragments.add(new NetVideoFrament());

    }


}
