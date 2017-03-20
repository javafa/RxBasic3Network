package com.veryworks.android.rxbasic3network;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    TextView text1,text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);

        Observable<String> naverObservable =
            Observable.create(emitter -> {
                emitter.onNext(Remote.getUrlByGet("naver.com"));
            }
            );

        Observable<String> cnetObservable =
            Observable.create(emitter -> {
                emitter.onNext( Remote.getUrlByGet("www.cnet.co.kr") );
            }
            );

        cnetObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (result) -> text1.setText(result)
                );

        naverObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (result) -> text2.setText(result)
                );
    }
}
