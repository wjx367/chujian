package com.viewwang.chujian.util;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by wang on 2015/12/24.
 */
public class MyCountTimer extends CountDownTimer {

    public static final int TIME_COUNT = 61000;//时间防止从119s开始显示（以倒计时120s为例子）
    private TextView btn;
    private int endStrRid;  //倒计时结束后，按钮对应显示的文字
    private int normalColor, timingColor;//未计时的文字颜色，计时期间的文字颜色

    public MyCountTimer(long millisInFuture, long countDownInterval, TextView btn, int endStrRid) {
        super(millisInFuture, countDownInterval);
        this.btn=btn;
        this.endStrRid=endStrRid;
    }

    public MyCountTimer(TextView btn, int endStrRid, int normalColor, int timingColor) {
        super(TIME_COUNT, 1000);
        this.btn = btn;
        this.endStrRid = endStrRid;
        this.normalColor=normalColor;
        this.timingColor=timingColor;
    }
    public MyCountTimer(TextView btn) {
        super(TIME_COUNT, 1000);
        this.btn = btn;
    }

    @Override
    public void onTick(long l) {
        if(timingColor > 0){
            btn.setTextColor(timingColor);
        }
        btn.setEnabled(false);
        btn.setText(l / 1000 + "s后重新获取");
    }

    @Override
    public void onFinish() {
        if(normalColor > 0){
            btn.setTextColor(normalColor);
        }
        btn.setText(endStrRid);
        btn.setEnabled(true);
    }
}
