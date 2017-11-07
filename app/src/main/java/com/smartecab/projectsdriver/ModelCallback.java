package com.smartecab.projectsdriver;

/**
 * Created by rahul.sharma01 on 11/6/2017.
 */
public interface ModelCallback<T> {
    void onSuccess(T response);

    void onError(String networkError);
}
