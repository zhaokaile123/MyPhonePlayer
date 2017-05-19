package atguigu.com.mysjyy.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import atguigu.com.mysjyy.R;

public class SystemVideoPlayerActivity extends AppCompatActivity {

    private VideoView vv;
    private Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_video_player2);

        vv = (VideoView) findViewById(R.id.vv);
        uri = getIntent().getData();

        //设置播放器三个监听：播放准备好的监听，播放完成的监听，播放出错的监听
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override // 准备播放工作完成  的时候回掉  此方法
            public void onPrepared(MediaPlayer mp) {
                vv.start();
            }
        });

        vv.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override  //播放失败的时候灰调此方法
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(SystemVideoPlayerActivity.this, "播放失败", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override  //播放完成的时候回调
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(SystemVideoPlayerActivity.this, "播放完成", Toast.LENGTH_SHORT).show();
                finish();
            }

        });
        //设置播放地址
        vv.setVideoURI(uri);
       //设置控制面板
        vv.setMediaController(new MediaController(this));

    }
}
