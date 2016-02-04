package com.viewwang.chujian.util;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取最新短信内容
 * @author wangwenhao
 *
 */
public class SmsReciver extends BroadcastReceiver {
	private String smsContent;
	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	@SuppressLint("SimpleDateFormat") @Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		SmsMessage msg = null;
		if (null != bundle) {
			Object[] smsObj = (Object[]) bundle.get("pdus");
			for (Object object : smsObj) {
				msg = SmsMessage.createFromPdu((byte[]) object);
    		Date date = new Date(msg.getTimestampMillis());//时间
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String receiveTime = format.format(date);
				System.out.println("number:" + msg.getOriginatingAddress()
				+ "   body:" + msg.getDisplayMessageBody() + "  time:"
						+ msg.getTimestampMillis());
				
				//在这里写自己的逻辑
				if (msg.getOriginatingAddress().equals("10086")) {
					Pattern pattern= Pattern.compile(" [a-zA-Z0-9]{10}");
					Matcher matcher=pattern.matcher(msg.getDisplayMessageBody());
					if(matcher.find()){
						this.setSmsContent(matcher.group().trim().substring(0,5));
					}else{
						this.setSmsContent("");
					}
					//TODO
					this.setSmsContent(msg.getDisplayMessageBody());
				}
				
			}
		}
	}


}