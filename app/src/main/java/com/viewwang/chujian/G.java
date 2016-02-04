package com.viewwang.chujian;

import android.graphics.Color;

public final class G {

	private G() {
	}

	/**
	 * banner页面的宽高比
	 */
	public static final double BANNER_SIZE_RATE = 720.0/276.0;

	public static final int B_COLOR_UNSELECT= Color.rgb(0x66, 0x66, 0x66);
	
	public static final int B_COLOR_SELECT= Color.rgb(0x66, 0x66, 0x66);

	public static final boolean isActivityAnimationEnabled = true;//页面切换动画开关
	// 积分兑换列表图片
//	public static final Integer[] SCORE_TO_HONGBAO_PIC = {
//			R.drawable.score_to_hongbao_pic5,
//			R.drawable.score_to_hongbao_pic10,
//			R.drawable.score_to_hongbao_pic20 };
	public static final int BANNER_WIDTH=700;
	public static final int BANNER_HEIGHT=300;

	public static final String[] SCORENEED = { "500", "1000", "2000" };
	public static final String[] SCORETYPE = { "five", "ten", "twenty" };
	public static final String APP_ID = "wx7cb048ab5a4f4915";
	
	public static final String WEB_FAILED_ERROR = "连接失败，请检查网络或稍后重试！";
	
	// 个人中心九宫格
	public static final String[] MY_CENTER_STRING = { "资金明细","投资管理", "借款管理","我要提现", "我要充值","我的红包" };
	// 对应图片
//	public static final Integer[] MY_CENTER_PIC = {R.drawable.self_tran_detail,R.drawable.self_my_invests,R.drawable.self_debt_invests,
//			R.drawable.self_go_withdraw, R.drawable.self_go_recharge, R.drawable.self_my_hongbao};
	
	// 关于我们九宫格
	public static final String[] ABOUTUS_STRING = { "集团简介","荣誉资质", "合作伙伴","帮助中心", "安全保障",""};
	// 对应图片
//	public static final Integer[] ABOUTUS_PIC = {R.drawable.about_us_introduce,R.drawable.about_us_honor,R.drawable.about_us_honor2,
//			R.drawable.about_us_help, R.drawable.about_us_safe,R.drawable.blank_grid};
	
	// Banner配置文件格式版本号
	public static final int BANNER_CONFIG_FORMAT_VERSION = 1;

	// 系统API版本号
	public static int SYSTEM_SDK_API = 0;

	public static final String USERICON_PATH="/usericon.jpg";

	public static final String USERIMG_PATH="/xjly/userimg.jpg";

	// 资源加载线程池大小
	public static final int RESOURCE_THREAD_POOL_SIZE = 4;

	public static String APPVERSION = "1.0.0";
	
	public static boolean VERSION_CHECK_FLAG = false;//只在开启软件时检测
	
	public static final int CHOOSEBANK = 11;

	//banner图前缀
	public static final String BANNER_URL_PREFIX = "http://192.168.8.14:8080";
	// 接口地址公共头
//	public static final String URL_PREFIX = "http://192.168.8.14:8080/xjly/";
//	public static final String USERICON_URL_PREFIX = "http://192.168.8.14:8080/xjly";

	public static final String URL_PREFIX = "http://123.56.106.181/";
	public static final String USERICON_URL_PREFIX = "http://123.56.106.181";
	/**
	 * 第三方返回页面 截取url
	 */
	public static final String YBRETURNURL = URL_PREFIX + "return_app.html";

	// 实名认证返回
	public static final String REALNAME_RETURN = URL_PREFIX
			+ "return_app.html?realname";
	// 绑卡返回
	public static final String BINDCARD_RETURN = URL_PREFIX
			+ "return_app.html?bindcard";
	// 充值返回
	public static final String RECHARGE_RETURN = URL_PREFIX
			+ "return_app.html?recharge";
	// 提现返回
	public static final String WITHDRAW_RETURN = URL_PREFIX
			+ "return_app.html?withdraw";
	// 投标返回
	public static final String INVEST_RETURN = URL_PREFIX
			+ "return_app.html?tender";
	// public static final String DEFAULT_RETURN=URL_PREFIX+"return_app.html?";
	/**
	 * **************************************首页+列表页接口***************************
	 * ************* 首页标、banner接口、标列表、标详情、产品介绍、风险控制、投资记录
	 */
	// 获取首页标信息
	// app/index.html
//	public static final String URL_GET_HOME_BID_INFO = "https://api.duc365.com/product/getRecommendedBorrowList";
	public static final String URL_GET_HOME_BID_INFO = URL_PREFIX
			+ "product/homeIndex.html";

	// 标列表接口
	public static final String URL_BORROW_LIST = URL_PREFIX
			+ "product/getBorrowList.html";

	// 标详情
	public static final String URL_BORROW_DETAIL = URL_PREFIX
			+ "product/getBorrowDetail.html";

	public static final String URL_BORROW_RECORD = URL_PREFIX
			+ "product/getInvestRecordList.html";

	public static final String URL_BORROW_DETAIL_INFO = URL_PREFIX
			+ "product/getBorrowInfo.html";
	
	// 债权专区
	public static final String URL_DEBT_LIST = URL_PREFIX
			+ "app/market/search.html";
	
	// 项目介绍与安全保障接口 type: 1 项目介绍 2安全保障
	public static final String URL_PROGRAMINFO_DETAIL = URL_PREFIX
			+ "app/borrow/info.html?appSource=android&appVersion=" + APPVERSION;
	// 获取投标记录接口
	public static final String URL_GET_INVEST_RECORD = URL_PREFIX
			+ "app/borrow/tenderList.html";

	// 进入购买页面
	public static final String URL_GO_TENDER = URL_PREFIX
			+ "app/user/toTender.html";
	
	//债权购买
	public static final String URL_DEBT_PAY = URL_PREFIX
			+ "app/dept/acpmarket.html";

	/**
	 * *********************************个人中心相关接口********************************
	 * ********* 获取验证码，校验验证码、注册、登录、获取个人账户信息 退出等
	 */

	// 获取注册验证码
	public static final String URL_SEND_VERIFY_CODE = URL_PREFIX
			+ "user/getVerifyCode.html";

	// 注册用户协议
	public static final String URL_REGISTER_PROTOCOL = URL_PREFIX
			+ "app/agreement.html?appSource=android&appVersion=" + APPVERSION;

	// 注册
	public static final String URL_REGISTER = URL_PREFIX
			+ "user/registerUser.html";
	
	// 忘记密码
	public static final String URL_FORGETPWD = URL_PREFIX + "user/forgetLoginPassword.html";

	// 登录接口
	public static final String URL_LOGIN = URL_PREFIX + "user/loginUser.html";

	// 登出接口
	public static final String URL_LOGOUT = URL_PREFIX + "user/logoutUser.html";

	// 获取用户信息接口
	public static final String URL_GET_USER_INFO = URL_PREFIX
			+ "user/getUserInfo.html";
	
	// 获取个人中心
	public static final String URL_GET_SELFCENTER_INFO = URL_PREFIX
			+ "app/myhome/safe1.html";
	
	// 获取个人持有资产
	public static final String URL_GET_HOLDASSETS = URL_PREFIX
			+ "account/getUserAssetInfo.html";

	// 获取我的投资记录接口
	public static final String URL_GET_INVESTRECORD = URL_PREFIX
			+ "product/getApiTenderLog.html";
	
	// 投资管理-待收款、已收款
	public static final String URL_INVEST_MANAGE_STATE = URL_PREFIX
			+ "app/myhome/wsklist.html";
	
	// 投资管理-正在回款
	public static final String URL_INVEST_MANAGE_EXCHANGE = URL_PREFIX
			+ "app/myhome/sklist.html";
	
	// 还款管理
	public static final String URL_LOAN_MANAGE = URL_PREFIX
			+ "app/myhome/borrow-list.html";
	
	// 还款
	public static final String URL_PAY_LOAN = URL_PREFIX
			+ "app/myhome/repayment.html";
	
	// 还款明细
	public static final String URL_LOAN_DETAIL = URL_PREFIX
			+ "app/myhome/repayment-list.html";
	
	// 放入转让市场
	public static final String URL_EXCHANGE_MARKET = URL_PREFIX
			+ "app/myhome/addmarket.html";
	
	// 投资明细
	public static final String URL_FUNDDETAIL = URL_PREFIX
			+ "app/moneyLog.html";
	
	// 获取充值记录
	public static final String URL_GET_RECHARGERECORD = URL_PREFIX
			+ "account/getUserRechargeList.html";

	// 获取提现记录
	public static final String URL_GET_WITHDRAWRECORD = URL_PREFIX
			+ "account/getUserWithdrawList.html";

	// 获取积分列表
	public static final String URL_GET_JIFENRECORD = URL_PREFIX
			+ "app/user/credit.html";

	// 积分兑换接口
	public static final String URL_SCORETOHB = URL_PREFIX
			+ "app/user/exchange.html";

	// 获取红包列表接口
	public static final String URL_GET_MYHONGBAO_LIST = URL_PREFIX
			+ "account/getUserBonusList.html";
	// 获取奖券列表接口
	public static final String URL_GET_TICKETS_LIST = URL_PREFIX
			+ "app/user/award.html";
	//获取交易记录接口
	public static final String URL_GET_DEALRECORD = URL_PREFIX
			+ "user/apiMoneyLog.html";
	// 实名认证
	public static final String URL_USER_REALNAME = URL_PREFIX
			+ "user/realNameAuth.html";
	
	// 绑定银行卡
	public static final String URL_USER_BANKCARD = URL_PREFIX
			+ "app/bankBind.html";

	// 修改登录密码
	public static final String URL_CHANGE_PWD = URL_PREFIX
			+ "user/modifyLoginPassword.html";
	// 修改支付密码
	public static final String URL_CHANGE_TRAN_PWD = URL_PREFIX
			+ "app/myhome/updatePayPassword.html";
	// 提现
	public static final String URL_USER_WITHDRAW = URL_PREFIX
			+ "trade/withdraw.html";
	
	//提现银行卡选择
	public static final String URL_WITHDRAW_BANK = URL_PREFIX + "user/getBankList.html";

	// 充值接口
	public static final String URL_USER_RECHARGE = URL_PREFIX
			+ "trade/recharge.html";

	// 投标接口
	public static final String URL_INVEST = URL_PREFIX + "trade/invest.html";

	// 签到
	public static final String URL_SIGN = URL_PREFIX + "app/user/signin.html";

	//邮箱认证
	public static final String URL_MAIL_CONFIRM = URL_PREFIX +"user/sendEmail.html";

	// 头像上传
	public static final String URL_USERICON_UPLOAD = URL_PREFIX +"user/apiUploadPic.html";
	/**
	 * *************************************************************************
	 * ******************************
	 */

	/***
	 * ****************************************************更多页面*****************
	 * **********
	 * 
	 */
	// 获取首页banner接口
//	public static final String URL_GET_BANNER = "https://www.duc365.com/"
//			+ "app/banner.html?appSource=android&appVersion=" + APPVERSION;
			//http://www.ruiyinlc.com/api/getBannerConfig.html
			//https://api.duc365.com/common/getBannerList?bannerType=2
//	public static final String URL_GET_BANNER ="https://api.duc365.com/common/getBannerList?bannerType=2";
	public static final String URL_GET_BANNER =URL_PREFIX+"commom/getBannerList.html";
	// 帮助中心
	public static final String URL_HELPCENTER = URL_PREFIX
			+ "app/help-center/site.html";

	// 帮助中心详细
	public static final String URL_HELPCENTER_DRTAIL = URL_PREFIX
			+ "app/help-center.html?appSource=android&appVersion=" + APPVERSION;

	// 积分规则
	public static final String URL_JIFEN_RULE = URL_PREFIX
			+ "app/help-center.html?appSource=android&appVersion=" + APPVERSION
			+ "&siteType=credit";

	// 公司新闻
	public static final String URL_NOTICE_CENTER = URL_PREFIX
			+ "app/help/redirect.html";
	// 活动公告
	public static final String URL_ACTIVE_CENTER = URL_PREFIX
			+ "commom/getBannerList.html";
	// 平台简介
	public static final String URL_PTJJ_DETAIL = URL_PREFIX
			+ "app/platform-info.html?appSource=android&appVersion="
			+ APPVERSION;

	// 获取app最新信息 version版本号 url下载地址
	public static final String URL_CHECKVERSION = URL_PREFIX
			+ "commom/checkVersion.html";

	// 获取邀请信息接口
	public static final String URL_GET_INVITEINFO = URL_PREFIX
			+ "app/user/invite.html";
	
	// 关于我们
	public static final String URL_ABOUT_US = URL_PREFIX
			+ "app/help/aboutus.html";

	//公司简介
	public static final String URL_COMPANY_DETAIL=URL_PREFIX+"app/help/aboutus.html?type=gsjj";

	//管理团队
	public static final String URL_MANAGER_TEAM=URL_PREFIX+"app/help/aboutus.html?type=team";

	//安全保障
	public static final String URL_SAFETY = URL_PREFIX+"commom/safetyAssurance.html?appSource=android&appVersion="+G.APPVERSION;

	//新手指引
	public static final String URL_NEWUSER = URL_PREFIX+"common/getHelpCenter.html?type=4&appSource=android&appVersion="+G.APPVERSION;

	//平台规则
	public static final String URL_PRODUCT_RULE = URL_PREFIX+"common/getHelpCenter.html?type=6&appSource=android&appVersion="+G.APPVERSION;

	//平台公告
	public static final String URL_PLATFORM_NOTICE = URL_PREFIX+"common/getHelpCenter.html";
	/**
	 * *************************************************************************
	 * *****************
	 */

	// 全局配置存储文件名
	public static final String GLOBAL_CONFIG_FILENAME = "global.cfg";

	// Banner配置存储文件名
	public static final String BANNER_CONFIG_FILENAME = "banner.cfg";

	// Banner图片文件名前缀
	public static final String BANNER_FILENAME_PREFIX = "banner_";
	
	// 首页切换界面广播
	public static final String SWITCHFRAGMENT = "switchfragment";

	// 使用前缀
	public static final String PREFS_LOGIN_NAME = "UserAccountInfo";
	// Banner configuration key
	public static final int BCK_BANNER_WIDTH = 1;
	public static final int BCK_BANNER_HEIGHT = 2;
	public static final int BCK_BANNER_DATA = 3;

	// Session Key
	public static final String SK_BANNER_CONFIG_FORMAT_VERSION = "banner_config_format_version";

	// 网络请求标志
	public static final int MSG_SUCCESS = 0;// 从网络获取数据成功的标识
	public static final int MSG_FAILURE = 1;// 从网络获取数据失败的标识
	public static final int MSG_NO_DATA = 2;
	public static final int MSG_NO_CONNECTED = 404; // 无网络服务
	public static final int MSG_NOT_LOGIN = 4;

	// 待还款页面使用
	public static final int MSG_REPAY_SUCCESS = 5;
	public static final int MSG_REPAY_FAILURE = 6;

	public static final int MSG_LOGIN_SUCCESS = 0;
	public static final int MSG_LOGIN_FAILURE = 1;
	public static final int MSG_LOGIN_NO_CONNECT = 2;
	public static final int MSG_LOGIN_WRONG_ACCOUNT = 3;
	// 个人中心使用
	public static final int MSG_SELF_LOGIN_OUT_SUCCESS = 0;
	public static final int MSG_SELF_LOGIN_OUT_FAILURE = 6;
	public static final int MSG_SELF_GET_DETAIL_SUCCESS = 2;
	public static final int MSG_SELF_NOT_LOGIN = 5;
	public static final int MSG_SELF_GET_DETAIL_FAILURE = 4;
	public static final int MSG_SELF_STILL_LOGIN = 7;
	// 提现使用
	public static final int MSG_WITHDRAW_SUCCESS = 5;
	public static final int MSG_WITHDRAW_FAILURE = 6;
	public static final int MSG_WITHDRAW_WRONG_PASSWORD = 7;
	public static final int MSG_WITHDRAW_FEE_SUCCESS = 8;
	public static final int MSG_WITHDRAW_FEE_FAILURE = 9;
	public static final int MSG_NOT_CERTIFICATED = 10;
	public static final int MSG_NO_BANK = 11;

	// 手势密码调用的类型，分为开启，设置，修改
	public static int MSG_OPEN_SET_CHANGE_GPW = 0;
	public static int MSG_ISAUTO_GPW = 1;
	// 注册使用
	public static final int MSG_SEND_CODE_SUCCESS = 5;
	public static final int MSG_SEND_CODE_FAILED = 6;
	public static final int MSG_CHECK_CODE_SUCCESS = 7;
	public static final int MSG_CHECK_CODE_FAILED = 8;
	public static final int MSG_CHECK_CODE_WRONG = 9;
	public static final int MSG_REG_SUCCESS = 10;
	public static final int MSG_REG_FAILED = 11;

	public static final String SPLASHFILEDIR = "/dingyoucai";

	/**
	 * 生成tag
	 */
	public static final String tag() {
		return new Throwable().getStackTrace()[1].getClassName();
	}

	/**
	 * 根据前缀生成tag
	 */
	public static final String tag(String prefix) {
		return prefix + "-" + new Throwable().getStackTrace()[1].getClassName();
	}
}
