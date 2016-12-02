package com.midian.qualitycloud.ui.common;

import u.aly.ap;

import com.google.zxing.Result;
import com.midian.qualitycloud.bean.FacilityDetailBean;
import com.midian.qualitycloud.utils.AppUtil;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import midian.baselib.api.ApiCallback;
import midian.baselib.bean.NetResult;
import midian.baselib.common.MipcaActivityCapture;
import midian.baselib.utils.UIHelper;

/**
 * 二维码扫描
 * 
 * @author MIDIAN
 * 
 */
public class ScanActivity extends MipcaActivityCapture {

	private String types;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
	}

	@Override
	public void handleDecode(Result result, Bitmap barcode) {
		super.handleDecode(result, barcode);
		// UIHelper.t(_activity, result.getText());
		String resultStr = result.getText();
		System.out.println("::resultStr::"+resultStr);
		if (resultStr.contains("http://app.gzqts.gov.cn/QCApp/facility/")) {
			resultStr = resultStr.replace(
					"http://app.gzqts.gov.cn/QCApp/facility/", "");
			AppUtil.getQualityCloudApiClient(ac).getScanFacility(resultStr,
					this);
			finish();
		}
		else{
			UIHelper.t(_activity,"请扫描正确的电梯二维码");
		}
	}

	@Override
	public void onApiSuccess(NetResult res, String tag) {
		// TODO Auto-generated method stub
		super.onApiSuccess(res, tag);
		if (res.isOK()) {
			if ("getScanFacility".equals(tag)) {
				FacilityDetailBean mFacilityDetailBean = (FacilityDetailBean) res;
				types=mFacilityDetailBean.getContent().getBase().getType();
				Bundle b = new Bundle();
				Log.e("wqf","Scan:"+types);
				b.putString("types",types);
				b.putSerializable("detail", mFacilityDetailBean);
				UIHelper.jump(_activity, ScanResultActivity.class, b);
			} else {

			}

		} else {
			ac.handleErrorCode(_activity, res.error_code);
		}

	}
}
