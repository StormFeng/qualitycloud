package com.midian.qualitycloud.retrofit;

/**
 * Created by Administrator on 2016/11/23 0023.
 */

public class HttpResult<T> {
    private String ret;
    private T content;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
