package com.midian.qualitycloud;

import midian.baselib.base.BaseActivity;
import midian.baselib.utils.UIHelper;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;

import com.midian.qualitycloud.ui.main.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class WelActivity extends BaseActivity implements OnClickListener{

	private ImageView iv;
	private AlphaAnimation alphaAnimation;
	private Button button;
	private Timer timer;
	private TimerTask timerTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wel);
		 iv = (ImageView) findViewById(R.id.imageView1);
		button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(this);
		// 一秒
		alphaAnimation = new AlphaAnimation(0f, 1);
		alphaAnimation.setDuration(1000);
		iv.setAnimation(alphaAnimation);
		timer=new Timer();
		timerTask=new TimerTask() {
			@Override
			public void run() {
				UIHelper.jump(WelActivity.this, MainActivity.class);
				finish();
			}
		};
		timer.schedule(timerTask,5000);
	}

	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		super.onClick(arg0);
		arg0.setEnabled(false);
		timer.cancel();
		UIHelper.jump(WelActivity.this, MainActivity.class);
		finish();
	}
}
