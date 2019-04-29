package com.zhang.rxjavademo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        RxJava01();

//        RxJava02();

//        RxJava03();

//        RxJava04();

        RxJava05();


    }

    private void RxJava05() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                log("上游 发射--a");
                emitter.onNext("上游 发射--a");

                log("上游 发射--b");
                emitter.onNext("上游 发射--b");

                log("上游 发射--c");
                emitter.onNext("上游 发射--c");

                log("上游 发射--d");
                emitter.onNext("上游 发射--d");
            }
        })
                .subscribe(new Observer<String>() {Disposable mDisposable;

                    @Override
                    public void onSubscribe(Disposable d) {

                        mDisposable = d;
                        log("下游 onSubscribe");

                    }

                    @Override
                    public void onNext(String s) {

                        if (s.equals("上游 发射--c")) {
                            mDisposable.dispose();
                        }

                        log("下游 onNext ：" + s);

                    }

                    @Override
                    public void onError(Throwable e) {

                        log("下游 onError");

                    }

                    @Override
                    public void onComplete() {

                        log("下游 onComplete");

                    }
                });

        //程序执行结果
//        2019-04-29 22:48:39.534 11100-11100/com.zhang.rxjavademo1 E/MainActivity: 下游 onSubscribe
//        2019-04-29 22:48:39.534 11100-11100/com.zhang.rxjavademo1 E/MainActivity: 上游 发射--a
//        2019-04-29 22:48:39.537 11100-11100/com.zhang.rxjavademo1 E/MainActivity: 下游 onNext ：上游 发射--a
//        2019-04-29 22:48:39.537 11100-11100/com.zhang.rxjavademo1 E/MainActivity: 上游 发射--b
//        2019-04-29 22:48:39.537 11100-11100/com.zhang.rxjavademo1 E/MainActivity: 下游 onNext ：上游 发射--b
//        2019-04-29 22:48:39.537 11100-11100/com.zhang.rxjavademo1 E/MainActivity: 上游 发射--c
//        2019-04-29 22:48:39.537 11100-11100/com.zhang.rxjavademo1 E/MainActivity: 下游 onNext ：上游 发射--c
//        2019-04-29 22:48:39.537 11100-11100/com.zhang.rxjavademo1 E/MainActivity: 上游 发射--d

    }

    private void RxJava04() {

        //创建上游Observable
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                log("上游 发射--a");
                emitter.onNext("上游 发射--a");

                log("上游 发射--b");
                emitter.onNext("上游 发射--b");

                log("上游 发射--c");
                emitter.onNext("上游 发射--c");

                log("上游 发射--d");
                emitter.onNext("上游 发射--d");

            }
        });

        //创建下游Observer
        Observer<String> observer = new Observer<String>() {

            Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {

                mDisposable = d;
                log("下游 onSubscribe");

            }

            @Override
            public void onNext(String s) {

                if (s.equals("上游 发射--c")) {
                    mDisposable.dispose();
                }

                log("下游 onNext ：" + s);

            }

            @Override
            public void onError(Throwable e) {

                log("下游 onError");

            }

            @Override
            public void onComplete() {

                log("下游 onComplete");

            }
        };

        //连接上下游
        observable.subscribe(observer);

        //程序执行结果
//        2019-04-29 22:45:20.821 10975-10975/com.zhang.rxjavademo1 E/MainActivity: 下游 onSubscribe
//        2019-04-29 22:45:20.821 10975-10975/com.zhang.rxjavademo1 E/MainActivity: 上游 发射--a
//        2019-04-29 22:45:20.823 10975-10975/com.zhang.rxjavademo1 E/MainActivity: 下游 onNext ：上游 发射--a
//        2019-04-29 22:45:20.823 10975-10975/com.zhang.rxjavademo1 E/MainActivity: 上游 发射--b
//        2019-04-29 22:45:20.823 10975-10975/com.zhang.rxjavademo1 E/MainActivity: 下游 onNext ：上游 发射--b
//        2019-04-29 22:45:20.823 10975-10975/com.zhang.rxjavademo1 E/MainActivity: 上游 发射--c
//        2019-04-29 22:45:20.824 10975-10975/com.zhang.rxjavademo1 E/MainActivity: 下游 onNext ：上游 发射--c
//        2019-04-29 22:45:20.824 10975-10975/com.zhang.rxjavademo1 E/MainActivity: 上游 发射--d


    }

    private void RxJava03() {

        //创建上游Observable
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                log("上游 发射--a");
                emitter.onNext("上游 发射--a");

                log("上游 发射--b");
                emitter.onNext("上游 发射--b");

                emitter.onError(new NullPointerException());

                log("上游 发射--c");
                emitter.onNext("上游 发射--c");

                log("上游 发射--d");
                emitter.onNext("上游 发射--d");

            }
        });

        //创建下游Observer
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

                log("下游 onSubscribe");

            }

            @Override
            public void onNext(String s) {

                log("下游 onNext ：" + s);

            }

            @Override
            public void onError(Throwable e) {

                log("下游 onError");

            }

            @Override
            public void onComplete() {

                log("下游 onComplete");

            }
        };

        //连接上下游
        observable.subscribe(observer);


        //程序执行结果
//        2019-04-29 22:40:52.290 10822-10822/? E/MainActivity: 下游 onSubscribe
//        2019-04-29 22:40:52.290 10822-10822/? E/MainActivity: 上游 发射--a
//        2019-04-29 22:40:52.292 10822-10822/? E/MainActivity: 下游 onNext ：上游 发射--a
//        2019-04-29 22:40:52.292 10822-10822/? E/MainActivity: 上游 发射--b
//        2019-04-29 22:40:52.292 10822-10822/? E/MainActivity: 下游 onNext ：上游 发射--b
//        2019-04-29 22:40:52.292 10822-10822/? E/MainActivity: 下游 onError
//        2019-04-29 22:40:52.293 10822-10822/? E/MainActivity: 上游 发射--c
//        2019-04-29 22:40:52.293 10822-10822/? E/MainActivity: 上游 发射--d



    }

    private void RxJava02() {

        //创建上游Observable
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                log("上游 发射--a");
                emitter.onNext("上游 发射--a");

                log("上游 发射--b");
                emitter.onNext("上游 发射--b");

                emitter.onComplete();

                log("上游 发射--c");
                emitter.onNext("上游 发射--c");

                log("上游 发射--d");
                emitter.onNext("上游 发射--d");

            }
        });

        //创建下游Observer
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

                log("下游 onSubscribe");

            }

            @Override
            public void onNext(String s) {

                log("下游 onNext ：" + s);

            }

            @Override
            public void onError(Throwable e) {

                log("下游 onError");

            }

            @Override
            public void onComplete() {

                log("下游 onComplete");

            }
        };

        //连接上下游
        observable.subscribe(observer);


        //程序执行结果

//        2019-04-29 22:34:16.577 10486-10486/com.zhang.rxjavademo1 E/MainActivity: 下游 onSubscribe
//        2019-04-29 22:34:16.577 10486-10486/com.zhang.rxjavademo1 E/MainActivity: 上游 发射--a
//        2019-04-29 22:34:16.580 10486-10486/com.zhang.rxjavademo1 E/MainActivity: 下游 onNext ：上游 发射--a
//        2019-04-29 22:34:16.580 10486-10486/com.zhang.rxjavademo1 E/MainActivity: 上游 发射--b
//        2019-04-29 22:34:16.580 10486-10486/com.zhang.rxjavademo1 E/MainActivity: 下游 onNext ：上游 发射--b
//        2019-04-29 22:34:16.582 10486-10486/com.zhang.rxjavademo1 E/MainActivity: 下游 onComplete
//        2019-04-29 22:34:16.582 10486-10486/com.zhang.rxjavademo1 E/MainActivity: 上游 发射--c
//        2019-04-29 22:34:16.582 10486-10486/com.zhang.rxjavademo1 E/MainActivity: 上游 发射--d

    }

    private void RxJava01() {
        //创建上游的Observable
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Hello");
                emitter.onNext("World");
                emitter.onNext("!!!!!!");

                emitter.onComplete();
            }
        });

        //创建下游的Observer
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

                Log.e(TAG,"onSubscribe");

            }

            @Override
            public void onNext(String s) {

                Log.e(TAG,"onNext : " + s);

            }

            @Override
            public void onError(Throwable e) {

                Log.e(TAG,"onError");

            }

            @Override
            public void onComplete() {

                Log.e(TAG,"onComplete");

            }
        };

        //连接上下游
        observable.subscribe(observer);


        //程序执行结果

//        2019-04-29 22:23:12.222 10151-10151/com.zhang.rxjavademo1 E/MainActivity: onSubscribe
//        2019-04-29 22:23:12.224 10151-10151/com.zhang.rxjavademo1 E/MainActivity: onNext : Hello
//        2019-04-29 22:23:12.224 10151-10151/com.zhang.rxjavademo1 E/MainActivity: onNext : World
//        2019-04-29 22:23:12.224 10151-10151/com.zhang.rxjavademo1 E/MainActivity: onNext : !!!!!!
//        2019-04-29 22:23:12.224 10151-10151/com.zhang.rxjavademo1 E/MainActivity: onComplete

    }

    private void log(String msg) {
        Log.e(TAG,msg);
    }

}
