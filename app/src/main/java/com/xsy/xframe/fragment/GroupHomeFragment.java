/*
package com.xsy.xframe.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xsy.base.ui.BaseFragment;
import com.xsy.xframe.R;
import com.xsy.xframe.adapter.GroupAdapter;
import com.xsy.xframe.utils.LogUtils;


import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.tbruyelle.rxpermissions.RxPermissions.TAG;

*/
/**
 * @Description描述:
 * @Author作者: xuesanyang
 * @Date日期: 2016/10/10
 *//*

public class GroupHomeFragment extends BaseFragment{
    private static Subscription mSubscription;
    @BindView(R.id.lv)
    ListView lv;
    private List<String> data=new ArrayList();
    private static String TAG="Rxjava";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_group;
    }

    @Override
    public void afterBindView(Bundle savedInstanceState) {
        initView();
    }

    private void initView( ) {
        learn();
        testFlowable();
    }

    private void learn() {
        //创建上游
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        });
        //创建下游
        Observer<Integer> observer= new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
              data.add("subscribe");
            }

            @Override
            public void onNext(Integer value) {
                data.add(value+"");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                data.add("complete");
            }
        };

        //dingyue
        observable.subscribe(observer);

        GroupAdapter adapter = new GroupAdapter(getActivity(), data);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.tv0,R.id.tv1,R.id.tv2})
    public void onClickListen(View v){
        switch (v.getId()){
            case R.id.tv0:
                reduceNumSolve();
                break;
            case R.id.tv1:
                solveBySpeed();
                break;
            case R.id.tv2:
               request(1); //在外部调用request请求上游
                break;
            default:
                break;
        }
    }

    private void solveBySpeed() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; ; i++) {
                    emitter.onNext(i);
                    Thread.sleep(2000);  //发送事件之后延时2秒
                }
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("A");
            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                LogUtils.d(TAG, s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                LogUtils.d(TAG, throwable+"");
            }
        });

    }

    private void reduceNumSolve() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; ; i++) {
                    emitter.onNext(i);
                }
            }
        }).subscribeOn(Schedulers.io()).sample(2, TimeUnit.SECONDS); //进行sample采样

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("A");
            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                LogUtils.d(TAG, s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                LogUtils.d(TAG, throwable+"");
            }
        });


    }


    public static void request(long n) {
        mSubscription.request(n); //在外部调用request请求上游
    }

    public static void testFlowable() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
               */
/* LogUtils.i(TAG, "emit 1");
                emitter.onNext(1);
                LogUtils.i(TAG, "emit 2");
                emitter.onNext(2);
                LogUtils.i(TAG, "emit 3");
                emitter.onNext(3);
                LogUtils.i(TAG, "emit complete");
                emitter.onComplete();*//*

                for (int i = 0; i < 128; i++) { //buffersize的大小
                    LogUtils.i(TAG, "emit " + i);
                    emitter.onNext(i);
                }
            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        LogUtils.i(TAG, "onSubscribe");
                        mSubscription = s;  //把Subscription保存起来
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.i(TAG, "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.w(TAG, "onError: ", t);
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.i(TAG, "onComplete");
                    }
                });
    }

    public static void practice1() {
        Flowable
                .create(new FlowableOnSubscribe<String>() {
                    @Override
                    public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                        try {
                            FileReader reader = new FileReader("test.txt");
                            BufferedReader br = new BufferedReader(reader);

                            String str;

                            while ((str = br.readLine()) != null && !emitter.isCancelled()) {
                                while (emitter.requested() == 0) {
                                    if (emitter.isCancelled()) {
                                        break;
                                    }
                                }
                                emitter.onNext(str);
                            }

                            br.close();
                            reader.close();

                            emitter.onComplete();
                        } catch (Exception e) {
                            emitter.onError(e);
                        }
                    }
                }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        mSubscription = s;
                        s.request(1);
                    }

                    @Override
                    public void onNext(String string) {
                        System.out.println(string);
                        try {
                            Thread.sleep(2000);
                            mSubscription.request(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println(t);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //EventBus.getDefault().unregister(this);
    }
}
*/
