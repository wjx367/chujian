package com.viewwang.chujian.bannner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.viewwang.chujian.R;
import com.viewwang.chujian.cache.DataFileTimeExpiredCache;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class BannerView extends LinearLayout {

	public BannerView(Context context) {
		super(context);
		initView(context);
	}

	public BannerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}
	
	private final static long TIME_INTERVAL = 15000;
	
	private ViewPager viewPager;
	private LinearLayout dotLayout;
	private List<View> dotViewsList;
	private DataFileTimeExpiredCache<ListBanner> cache;
	private BannerPagerAdapter adapter;
	private BannerPageChangeListener listener;
	private int currentItem = 0;
	private BannerHandler handler;
	private ListBanner listBanner = new ListBanner();
	private List<BannerItem> bannerItems = new ArrayList<BannerItem>();
	private int resId[] = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img1,
			R.drawable.img2, R.drawable.img3};
	private void initView(Context context) {
		if (isInEditMode()) {
			return;
		}
		View layout = LayoutInflater.from(context).inflate(R.layout.layout_banner_view, this, true);
		viewPager = (ViewPager) layout.findViewById(R.id.view_pager);
		dotLayout = (LinearLayout) layout.findViewById(R.id.dot_layout);

		adapter = new BannerPagerAdapter(context);
		viewPager.setAdapter(adapter);
		listener = new BannerPageChangeListener();
		viewPager.setOnPageChangeListener(listener);


		handler = new BannerHandler(this);


	}
	private void initData() {
		ListBanner result = cache.getData();
		if (result != null && result.getData() != null) {
			setResult(result.getData());
			if (!cache.isExpired()) {
				return;
			}
		}
		getBannerList();
	}

	public void getBannerList() {
		for (int i = 0; i < 6; i++) {
			BannerItem item=new BannerItem();
			item.setResId(resId[i]);
			bannerItems.add(item);
		}


	}
	
	private void setResult(List<BannerItem> itemList) {
		dotLayout.removeAllViews();
		
		int total = itemList.size();
		if (currentItem >= total) {
			currentItem = 0;
		}
		dotViewsList = new ArrayList<View>(total);
		for (int i=0;i<total;i++) {
			ImageView dotView = new ImageView(getContext());
			LayoutParams params = new LayoutParams(15, 15);
			params.leftMargin = 4;
			params.rightMargin = 4;
			dotLayout.addView(dotView, params);
			dotViewsList.add(dotView);
		}
		adapter.setBannerList(itemList);
		
		viewPager.setCurrentItem(currentItem);
		listener.onPageSelected(currentItem);
		
		handler.sendMessageDelayed(handler.obtainMessage(0, currentItem), TIME_INTERVAL);
	}
	
	static class BannerHandler extends Handler {
		WeakReference<BannerView> mReference;
		public BannerHandler(BannerView view) {
			mReference = new WeakReference<BannerView>(view);
		}
		
		@Override
		public void handleMessage(Message msg) {
			BannerView view = mReference.get();
			if (view == null) {
				return;
			}
			BannerPagerAdapter adapter = view.adapter;
			if (adapter ==  null) {
				return;
			}

			sendMessageDelayed(obtainMessage(0, view.currentItem), TIME_INTERVAL);
		}
	}
	
	private class BannerPageChangeListener implements OnPageChangeListener {
		boolean isAutoPlay = false;
		@Override
		public void onPageScrollStateChanged(int arg0) {
			switch (arg0) {
			case 1:// 手势滑动，空闲中
				isAutoPlay = false;
				break;
			case 2:// 界面切换中
				isAutoPlay = true;
				break;
			case 0:// 滑动结束，即切换完毕或者加载完毕
					// 当前为最后一张，此时从右向左滑，则切换到第一张
				if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
					viewPager.setCurrentItem(0);
				}
				// 当前为第一张，此时从左向右滑，则切换到最后一张
				else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
					viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
				}
				break;
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int pos) {
			currentItem = pos;
			if (dotViewsList != null) {
				for (int i=0;i<dotViewsList.size();i++) {
					if (i == pos) {
//						dotViewsList.get(pos).setBackgroundResource(R.drawable.guide_dot_focus);
					}
					else {
//						dotViewsList.get(i).setBackgroundResource(R.drawable.guide_dot_blur);
					}
				}
			}
			
		}

	}


	public class BannerPagerAdapter extends PagerAdapter implements OnClickListener {
		private List<BannerItem> bannerList;
		private LayoutInflater inflater;

		public BannerPagerAdapter(Context context) {
			this.inflater = LayoutInflater.from(context);
		}

		public void setBannerList(List<BannerItem> bannerList) {
			this.bannerList = bannerList;

		}
		
		public BannerItem getBannerItem(int index) {
			if (bannerList != null && index > -1 && index < bannerList.size()) {
				return bannerList.get(index);
			}
			return null;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			if (object != null) {
				View rootView = (View) object;
				ImageView imageView = (ImageView) rootView.findViewById(R.id.image_view);
				BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
				if (drawable != null) {
					Bitmap bitmap = drawable.getBitmap();
					if (bitmap != null && !bitmap.isRecycled()) {
						bitmap=null;
					}
				}
				container.removeView(rootView);
			}
		}

		@Override
		public void finishUpdate(View container) {
		}

		@Override
		public int getCount() {
			return bannerList != null ? bannerList.size() : 0;
		}

		@Override
		public Object instantiateItem(ViewGroup view, int position) {

			BannerItem item = getBannerItem(position);
			if (item == null) {
				return null;
			}
			
			final View imageLayout = inflater.inflate(R.layout.layout_banner_item, view, false);
			final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image_view);

			imageView.setTag(R.id.image_view, item);
			imageView.setOnClickListener(this);
			
			view.addView(imageLayout, 0);
			return imageLayout;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view.equals(object);
		}

		@Override
		public void onClick(View v) {
			BannerItem item = (BannerItem) v.getTag(R.id.image_view);
			if (item != null) {
//				if(StrUtils.notEmpty(item.getBannerUrl())){
//
//				}
			}
		}


	}

	private boolean initSize = false;
	private boolean isShown = true;
	private boolean isDestroy = false;
	public void onPause() {
		isShown = false;
	}
	public void onShow() {
		isShown = true;
	}
	public void onDestroy() {
		isDestroy = true;
	}

}
