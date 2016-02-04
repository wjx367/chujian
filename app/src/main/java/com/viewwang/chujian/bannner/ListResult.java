package com.viewwang.chujian.bannner;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yult on 2015/11/25.
 */
public class ListResult<T> implements Serializable {

    public ListResult() {
    }
    private static final long serialVersionUID = 1L;
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
