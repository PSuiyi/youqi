package com.mzk.compass.youqi.api;

import android.content.Context;

import com.znz.compass.znzlibray.base_znz.BaseModel;
import com.znz.compass.znzlibray.base_znz.IView;
import com.znz.compass.znzlibray.common.ZnzConstants;
import com.znz.compass.znzlibray.network.retorfit.ZnzRetrofitUtil;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import top.zibin.luban.Luban;

/**
 * Date： 2017/12/11 2017
 * User： PSuiyi
 * Description：
 */

public class ApiModel extends BaseModel {
    private ApiService apiService;

    public ApiModel(Context context, IView mView) {
        super(context, mView);
        apiService = ZnzRetrofitUtil.getInstance().createService(ApiService.class);
    }

    public void checkPhone(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "00011");
        request(apiService.post(params), znzHttpListener);
    }

    public Observable<ResponseBody> requestVideoList(Map<String, String> params) {
        params.put("request_code", "30000");
        return apiService.post(params);
    }

    public void requestVideoDetail(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "30001");
        request(apiService.post(params), znzHttpListener, LODING_LODING);
    }

    /**
     * 获取验证码
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestCode(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestCode(params), znzHttpListener);
    }

    /**
     * 忘记密码
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestFindPassWord(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "10021");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 注册
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestRegister(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestRegister(params), znzHttpListener);
    }

    /**
     * 登陆
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestLogin(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestLogin(params), znzHttpListener);
    }

    /**
     * 用户详情
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestUserDetail(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestUserDetail(params), znzHttpListener);
    }

    /**
     * 完善用户资料
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestUpdateUserInfo(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestUpdateUserInfo(params), znzHttpListener);
    }

    /**
     * 修改手机号
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestChangePhoneNum(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "30002");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 项目列表
     * @param params
     * @return
     */
    public Observable<ResponseBody> requestProjectList(Map<String, String> params) {
        return apiService.requestProjectList(params);
    }
    /**
     * 订单列表
     * @param params
     * @return
     */
    public Observable<ResponseBody> requestOrderList(Map<String, String> params) {
        return apiService.requestOrderList(params);
    }

    /**
     * 单张图片上传
     *
     * @param url
     * @param znzHttpListener
     */
    public void uploadImageSingle(String url, ZnzHttpListener znzHttpListener) {
        Map<String, String> params = new HashMap();
        params.put("request_code", "93333");
        if (ZnzConstants.APP_DEBUG) {
            params.put("bucket", "imgtest");
        } else {
            params.put("bucket", "imgtest");
        }
        File file = new File(url);
        Luban.get(context)
                .load(file)
                .putGear(Luban.THIRD_GEAR)
                .asObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> throwable.printStackTrace())
                .onErrorResumeNext(throwable -> Observable.empty())
                .subscribe(file1 -> {
                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file1);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("imgFile", file.getName(), requestBody);
                    request(apiService.uploadImageSingle(params, body), znzHttpListener, LODING_PD);
                });
    }

    /**
     * 多张图片上传
     *
     * @param url
     * @param znzHttpListener
     */
    public void uploadImageMulti(List<File> url, ZnzHttpListener znzHttpListener) {
        Map<String, String> params = new HashMap();
        params.put("request_code", "93333");
        if (ZnzConstants.APP_DEBUG) {
            params.put("bucket", "imgtest");
        } else {
            params.put("bucket", "imgtest");
        }
        List<MultipartBody.Part> partLlist = new ArrayList<>();
        for (File file : url) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("imgFile", file.getName() + ".jpg", requestBody);
            partLlist.add(body);
        }
        request(apiService.uploadImageMulti(params, partLlist), znzHttpListener);
    }

}
