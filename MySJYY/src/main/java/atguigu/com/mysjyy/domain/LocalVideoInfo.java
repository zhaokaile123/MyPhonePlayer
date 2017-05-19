package atguigu.com.mysjyy.domain;

/**
 * Created by ASUS on 2017/5/19.
 */

public class LocalVideoInfo {
    private String name ;
    private long duration;
    private long size;
    private String url;

    public LocalVideoInfo(String name, long duration, long size, String url) {
        this.name = name;
        this.duration = duration;
        this.size = size;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "LocalVideoInfo{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", size=" + size +
                ", url='" + url + '\'' +
                '}';
    }
}
