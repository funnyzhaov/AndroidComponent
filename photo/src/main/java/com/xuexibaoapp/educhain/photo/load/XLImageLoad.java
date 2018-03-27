package com.xuexibaoapp.educhain.photo.load;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;


/**
 * Created by zhaowenjie on 2018/3/26.
 *
 * @desc
 */

public class XLImageLoad {
		//通过volatile关键字来确保安全
		private volatile static XLImageLoad xlImageLoad;
		
		private XLImageLoad(){}
		
		public static XLImageLoad getInstance(){
			if(xlImageLoad == null){
				synchronized (XLImageLoad.class){
					if(xlImageLoad == null){
						xlImageLoad = new XLImageLoad();
					}
				}
			}
			return xlImageLoad;
		}
	
	/**
	 * 加载gif图片
 	 * @param activity
	 * @param d
	 * @param v
	 */
	public void loadGif(Activity activity,int d, ImageView v){
		GlideApp.with(activity).asGif().load(d)
				.into(v);
	}
	
	/**
	 * 加载网络图片
	 * @param activity
	 * @param url
	 * @param v
	 */
	public void loadImage(Activity activity,String url,ImageView v){
		GlideApp.with(activity).load(url).centerCrop().into(v);
	}
}

