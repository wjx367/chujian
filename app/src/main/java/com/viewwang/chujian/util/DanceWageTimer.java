package com.viewwang.chujian.util;

import android.os.CountDownTimer;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * 
 * @author wangwenhao
 *	textview数字跳动效果
 */
public class DanceWageTimer extends CountDownTimer {

	public static final int INTERVAL_ONE = 20;
	public static final int INTERVAL_TWO = 40;

	private TextView textView;
	private double totalWage;
	private int startNum = 0;//从多少开始累加
	private int increased;//每次加多少
	private int decimals;
	private int decimalFlag = 0;//记录小数部分的累加
	private long totalExecuteTime;
	private long interval;
	public DanceWageTimer(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
	}

	public DanceWageTimer(long millisInFuture, long countDownInterval, TextView textView, double totalWage) {
		super(millisInFuture, countDownInterval);
		this.textView = textView;
		this.totalWage = totalWage;
		this.totalExecuteTime = millisInFuture;
		this.interval = countDownInterval;
//		startNum = DanceWageTimer.getStartNum(totalWage);
//		decimals = (int) ((totalWage - getIntegerOfWage(totalWage)) * 100);
		startNum = 0;
		decimals = 99;
		increased = DanceWageTimer.getIncreased(getIntegerOfWage(totalWage));
	}

	@Override
	public void onFinish() {
		DecimalFormat decFormat = new DecimalFormat("##0.00");
		String result = decFormat.format(totalWage);
		textView.setText(result);
	}

	@Override
	public void onTick(long arg0) {
		startNum += increased;
		if (decimalFlag < decimals) {
			if (totalExecuteTime / interval < decimals) {
				decimalFlag += (99/20);
			} else {
				decimalFlag++;
			}
		}
		if (decimalFlag < 10) {
			textView.setText(startNum + ".0" + decimalFlag);
		} else {
			textView.setText(startNum + "." + decimalFlag);
		}

	}

	/**
	 * @Title getTotalExecuteTime
	 * @Description 得到总共执行的时间
	 * @param totalWage
	 * @return
	 */
	public static int getTotalExecuteTime(double totalWage, int interval) {
		int wage = getIntegerOfWage(totalWage);
		int startNum = getStartNum(totalWage);
		int increased = getIncreased(startNum);
		int result = (wage - startNum) / increased * interval;
		return result;
	}

	/**
	 * @Title getStartNum
	 * @Description 得到从多少开始累加
	 * @param totalWage
	 * @return
	 */
	public static int getStartNum(double totalWage) {
		int wage = getIntegerOfWage(totalWage);
		if (wage/100000>=1) {
			return 100000;
		}
		if (wage / 10000 >= 1) {
			return 10000;
		} else if (wage / 1000 >= 1) {
			return 1000;
		} else if (wage / 100 >= 1) {
			return 100;
		} else if (wage / 10 >= 1) {
			return 10;
		} else {
			return 0;
		}
	}

	/**
	 * @Title getIncreased
	 * @Description 得到每次加多少
	 * @param start
	 * @return
	 */
	private static  int getIncreased(int total) {
		
		return total/20;
	}

	public static int getIntegerOfWage(double totalWage) {
		return (int) totalWage;
	}

}
