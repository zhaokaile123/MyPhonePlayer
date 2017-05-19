package atguigu.com.mysjyy.fragmentPager;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import atguigu.com.mysjyy.R;
import atguigu.com.mysjyy.activity.SystemVideoPlayerActivity;
import atguigu.com.mysjyy.adapter.LocalVideoAdapter;
import atguigu.com.mysjyy.domain.LocalVideoInfo;
import atguigu.com.mysjyy.fragment.BaseFragment;

/**
 * Created by ASUS on 2017/5/19.
 */

public class LocalVideoFragment extends BaseFragment {


    private TextView tv_nodata;
    private ListView listView;
    private ArrayList<LocalVideoInfo> videos;
    private LocalVideoAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragemt_localvideo,null);
        listView = (ListView) view.findViewById(R.id.lv);
        tv_nodata = (TextView) view.findViewById(R.id.tv_nodata);

        //listview 的点击事件，播放视频
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LocalVideoInfo item = adapter.getItem(position);  //adapter 得到 该条点击的对象；
                Intent intent = new Intent(context, SystemVideoPlayerActivity.class);
                intent.setDataAndType(Uri.parse(item.getUrl()),"video");
                startActivity(intent);
            }
        });
        return view ;
    }

    @Override
    public void initData() {
        super.initData();
        getData();
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(videos != null && videos.size() > 0) {
                tv_nodata.setVisibility(View.GONE);
                adapter = new LocalVideoAdapter(context,videos);
                listView.setAdapter(adapter);
            }else{
                tv_nodata.setVisibility(View.VISIBLE);
            }
        }
    };

    private void getData() {
        new Thread(){
            @Override
            public void run() {
                videos = new ArrayList<LocalVideoInfo>();
                ContentResolver resolver  = context.getContentResolver();
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                String[] objs = {
                        MediaStore.Video.Media.DISPLAY_NAME,//视频在sdcard上的名称
                        MediaStore.Video.Media.DURATION,//视频时长
                        MediaStore.Video.Media.SIZE,//视频文件的大小
                        MediaStore.Video.Media.DATA//视频播放地址
                };
                Cursor cursor = resolver.query(uri,objs,null,null,null);
                if(cursor != null ) {
                    while (cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
                        long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media.DURATION));
                        long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media.SIZE));
                        String url = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));

                        videos.add(new LocalVideoInfo(name,duration,size,url));

                        handler.sendEmptyMessage(0);
                    }
                    cursor.close();
                }
            }
        }.start();
    }
}
