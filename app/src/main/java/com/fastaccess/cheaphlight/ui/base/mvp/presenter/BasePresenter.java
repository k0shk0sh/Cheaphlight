package com.fastaccess.cheaphlight.ui.base.mvp.presenter;

import com.fastaccess.cheaphlight.ui.base.mvp.BaseMvp;

/**
 * Created by Kosh on 25 May 2016, 9:12 PM
 */

public class BasePresenter<V> implements BaseMvp.Presenter<V> {

    private V view;

    private BasePresenter() {throw new IllegalStateException("Cant not be initialized");}

    protected BasePresenter(V view) {
        attachView(view);
    }

    @Override public void attachView(V view) {
        this.view = view;
    }

    @Override public void detachView() {
        view = null;
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public V getView() {
        checkViewAttached();//ensure checking, avoid doing it everytime.
        return view;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new NullPointerException("View is not injected to presenter");
    }

}
