package com.idealcn.recyclerView.activity.stagger;

import java.io.Serializable;
import java.util.List;

/**
 * @author: guoning
 * @date: 2018/12/11 17:53
 * @description:
 */
public class BaseResponseBean<T> implements Serializable {
    private boolean error;
    private List<T> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
