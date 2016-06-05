package io.lf.pagers;

/**
 * Created by adly on 2016/6/5.
 */
public class Page {
    private int resId;
    private String info;

    public Page(int resId, String info) {
        this.resId = resId;
        this.info = info;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
