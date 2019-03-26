package com.example.csdc.myrxjavatestapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.observers.ConsumerSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity {

    private Disposable mDisposable;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d("RxJava2.0","isDisposed : " + emitter.isDisposed());
                emitter.onNext(1);
                Log.d("RxJava2.0","emitter : " + 1);

                emitter.onComplete(); //虽然调用了complete，但是事件还是在发送

                Log.d("RxJava2.0","isDisposed : " + emitter.isDisposed());
                emitter.onNext(2);
                Log.d("RxJava2.0","emitter : " + 2);
            }
        }).subscribe(new Observer<Integer>() {
            private Disposable mDisposable;
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(Integer value) {
                if(value == 2){ //丢弃了，因此不会处理
                    mDisposable.dispose();
                }

                Log.d("RxJava2.0","Observer : " + value);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });*/
       /* Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d("RxJava2.0","isDisposed : " + emitter.isDisposed());
                emitter.onNext(1);
                Log.d("RxJava2.0","emitter : " + 1);

                Log.d("RxJava2.0","isDisposed : " + emitter.isDisposed());
                emitter.onNext(2);
                Log.d("RxJava2.0","emitter : " + 2);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "String : " + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("RxJava2.0","Consumer : " + s);
            }
        });*/
        /*Observable.zip(Observable.create(new ObservableOnSubscribe<Integer>() {

                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        Log.d("RxJava2.0","emitter1 isDisposed : " + emitter.isDisposed());
                        emitter.onNext(1);

                        Log.d("RxJava2.0","emitter1 isDisposed : " + emitter.isDisposed());
                        emitter.onNext(2);
                    }
                }), Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        Log.d("RxJava2.0","emitter2 isDisposed : " + emitter.isDisposed());
                        emitter.onNext("A");

                        Log.d("RxJava2.0","emitter2 isDisposed : " + emitter.isDisposed());
                        emitter.onNext("B");

                        Log.d("RxJava2.0","emitter2 isDisposed : " + emitter.isDisposed());
                        emitter.onNext("C");
                    }
                }),
                new BiFunction<Integer, String, String>() {
                    @Override
                    public String apply(Integer integer, String s) throws Exception {
                         //组合，只会得到少的那个
                         //一个时间只能被组合一次
                        return  s + integer;
                    }
                }
        ).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("RxJava2.0","zip : " + s);
            }
        });*/
        /*Observable.concat(Observable.just(1,"s",3),Observable.just(4,5,6))
                .subscribe(new Consumer<Serializable>() {
                    @Override
                    //这里的Serializable数据类型会根据上面的组合的数据类型来看，如果单纯的是Int只会显示Integer，混合就是Serializable
                    public void accept(Serializable serializable) throws Exception {
                            if(serializable instanceof Integer){ //这里可以直接进行强转使用
                                Log.d("RxJava2.0","Serializable : " + (int) serializable);
                                //Log.d()int) serializable
                            }else if (serializable instanceof  String){
                                Log.d("RxJava2.0","Serializable : " + serializable);
                            }
                    }
                });
*/
       //FlatMap --无序
        /*Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                String[] s = new String[3];
                for (int i = 0; i < 3 ; i++){
                    s[i] = "" + integer;
                }
                int delay = (int)(1 + Math.random() * 10);
                return Observable.fromArray(s).delay(delay, TimeUnit.MILLISECONDS);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("RxJava2.0","flatMap : " + s);
                    }
                });*/
        //ConcatMap --有序
        /*Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                String[] s = new String[3];
                for (int i = 0; i < 3 ; i++){
                    s[i] = "" + integer;
                }
                int delay = (int)(1 + Math.random() * 10); //时间间隔大一点才有顺序明显
                return Observable.fromArray(s).delay(delay, TimeUnit.MILLISECONDS);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("RxJava2.0","flatMap : " + s);
                    }
                });*/

       //distinct --去重,在原来的顺序基础上
       /* Observable.just(1,23,4,5,6,123,23)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("RxJava2.0","distinct : " + integer);
                    }
                });*/

        //Fliter --过滤器
        /*Observable.just(1, 2, 3, 4, 5)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer > 3;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d("RxJava2.0", "filter : " + integer);
            }
        });*/

       //Buffer -- 分割
        //buffer(count,skip) 分成以不超过count大小，步长为skip的list集合
        /*Observable.just(1,2,3,4,5,6,7,8,9)
                .buffer(3,8)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        for(Integer i : integers){
                            Log.d("RxJava2.0", "buffer : " + i);
                        }
                    }
                });*/

       //Timer --定时任务，默认在新线程
        //延迟两秒执行
        /*Log.d("RxJava2.0", "timer : " + System.currentTimeMillis());
        Observable.timer(2,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d("RxJava2.0", "timer : " + System.currentTimeMillis());
                    }
                });*/

       //interval
        //三个参数：初始延时时间，时间间隔，单位，相当于一个定时任务
        //activity销毁时，仍在执行
        //可以通过返回的Disposable对象控制断开执行
       /* Log.d("RxJava2.0", "timer : " + System.currentTimeMillis());
        mDisposable = Observable.interval(3,2,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d("RxJava2.0", "timer : " + System.currentTimeMillis());
                    }
                });*/

        //doOnNext --在订阅者收到数据之前做一些事，不会对数据造成影响,例如保存等
      /*  Observable.just(1,2,3,4)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                         //接收前做的事
                        //这里无返回值
                        integer = integer + 1;
                        Log.d("RxJava2.0", "doOnNext : " + integer * 10);
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                //前面的操作对此没有影响
                //从数据可以看到，是操作一个doOnNext，执行一个onNetx操作
                Log.d("RxJava2.0", "doOnNext : " + integer );
            }
        });*/

        //skip --跳过skip个步长接收数据
        /*Observable.just(1,2,3,4,5)
                .skip(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("RxJava2.0", "skip : " + integer );
                    }
                });*/
        //take --接受一个long型数据，至多接收count个数据
       /* Observable.just(1,2,3,4,5,6,7,8,9)
                .take(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("RxJava2.0", "take : " + integer );
                    }
                });*/

        //just
        //just就是一个简单的发射器依次调用 onNext() 方法,接收的数据的长度最大为10

        //single --只会接收一个参数，SingleObserver只会调用一次onError或者onSuccess
        /*Single.just(1)
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer value) {
                        Log.d("RxJava2.0", "Single : " + value );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("RxJava2.0", "Single : " + e.toString() );
                    }
                });*/
        //debounce --除去发送频率过快的项,及接收时间间隔大于给定值的项
        //该时间间隔是以发出到下一次的发出为时间来进行区分的
        /*Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                Thread.sleep(100);
                emitter.onNext(2);
                Thread.sleep(200);
                emitter.onNext(3);
                Thread.sleep(300);
                emitter.onNext(4);
                Thread.sleep(400);
            }
        }).debounce(200,TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("RxJava2.0", "debounce : " + integer );
                    }
                });*/

        //defer,每一次订阅都会产生一个Observable,无订阅就不产生(有点没理解这里新产生的Observable是在哪产生的)
       /* final Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> call() throws Exception {
                return Observable.just(1,2,3);
            }
        });
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                Log.d("RxJava2.0", "debounce : " + observable.toString());
                Log.d("RxJava2.0", "debounce : " + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });*/

        //last --取出观察到的最后一项的值,last传入的参数的值为出错时或观测值为空时的默认值
        /*Observable.just(1,12,3,4)
                .last(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("RxJava2.0", "debounce : " + integer);
                    }
                });
*/
        //merge --交叉合并，即并非等第一个发射器发射完再发射第二个
        //但是例子的得到的结果显示的并不是交叉的结果，我甚至还做了一个Thread.sleep方法来延迟一个的发射，但是就是没有成功得到交叉的结果
       /* final String[] aStrings = {"A1", "A2", "A3", "A4"};
        final String[] bStrings = {"B1", "B2", "B3"};

        final Observable<String> aObservable = Observable.fromArray(aStrings);
        final Observable<String> bObservable = Observable.fromArray(bStrings);
        Observable.merge(aObservable,bObservable).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("RxJava2.0", "debounce : " + s);
            }
        });*/

        //reduce --每次用一个方法处理一个值，可以有一个seed作为初始值
        /*Observable.just(2,2,3)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        return integer  * integer2;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d("RxJava2.0", "debounce : " + integer);
            }
        });*/

       //scan --和reduce的实现的功能差不多，区别是scan会将中间的过程显示出来
       /* Observable.just(1,2,3)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d("RxJava2.0", "debounce : " + integer);
            }
        });*/

        //window --按照实际情况划分窗口，将数据发送给不同的Observable

        /*Observable.interval(1,TimeUnit.SECONDS)
                .take(15)
                .window(4, TimeUnit.SECONDS) //按时间间隔来划分
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Observable<Long>>() {
                    @Override
                    public void accept(Observable<Long> longObservable) throws Exception {
                        Log.d("RxJava2.0", "window begin div ");
                        longObservable.subscribeOn(Schedulers.io()) //每三个分配一个window
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Long>() {
                                    @Override
                                    public void accept(Long aLong) throws Exception {
                                        Log.d("RxJava2.0", "window : " + aLong);
                                    }
                                });
                    }
                });*/

        //AsyncSubject --观察者只会收到调用onComplete()前的一个值
        /*AsyncSubject asyncSubject = AsyncSubject.create();
        asyncSubject.onNext("1");
        asyncSubject.onNext("2");

        asyncSubject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("RxJava2.0", "AsyncSubject : " + s);
            }
        });

        asyncSubject.onNext("3");
        asyncSubject.onComplete(); //发射完成
        asyncSubject.onNext("4");

        asyncSubject.onNext("5");


        asyncSubject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("RxJava2.0", "AsyncSubject : " + s);
            }
        });*/

        //BehaviorSubject --在发送数据之前会先收到一个默认值,并且BehaviorSubject只接收在订阅之前最近的一个数据以及订阅之后的
        //数据
        /*BehaviorSubject behaviorSubject = BehaviorSubject.createDefault("Default");
        behaviorSubject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("RxJava2.0", "BehaviorSubject1 : " + s);
            }
        });
        behaviorSubject.onNext("1");
        behaviorSubject.onNext("2");
        behaviorSubject.onNext("3");
        behaviorSubject.onNext("4");

        behaviorSubject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("RxJava2.0", "BehaviorSubject2 : " + s);
            }
        });*/

        //PublishSubject --观察者只能接收到订阅之后的数据
       /* PublishSubject publishSubject = PublishSubject.create();
        publishSubject.onNext("1");
        publishSubject.onNext("2");
        publishSubject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("RxJava2.0", "PublishSubject : " + s);
            }
        });
        publishSubject.onNext("3");*/

        //ReplaySubject --无论什么时候订阅，都会将所有数据发送给订阅者
        ReplaySubject replaySubject = ReplaySubject.create();

        replaySubject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("RxJava2.0", "PublishSubject : " + s);
            }
        });
        replaySubject.onNext("1");
        replaySubject.onNext("2");
        replaySubject.onNext("3");
        replaySubject.onNext("4");

        replaySubject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("RxJava2.0", "PublishSubject : " + s);
            }
        });




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mDisposable != null && !mDisposable.isDisposed()){
            mDisposable.dispose();
        }
    }
}
