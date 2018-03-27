package com.funnyzhao.easycomponent.network.apiservice;

import com.funnyzhao.easycomponent.network.response.BaseResponse;
import com.funnyzhao.easycomponent.network.response.BaseResult;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * @author zhaowenjie
 * @time 2018/3/17 19:53
 * @desc 项目api统一管理
 **/

public interface BaseApiService {
    String DEBUG_BASE_URL = "http://educhain.xuexibaoapp.com/";
    String RELEASE_BASE_URL = "http://educhain.xuexibaoapp.com/";
//    http://test.educhain.xuexibaoapp.com/
    
    /**
     * POST
     *
     * @param url  url
     * @param maps 参数
     * @return
     */
    @GET("{url}")
    Observable<BaseResponse<BaseResult>> executeGet(@Path("{url}") String url, @QueryMap Map<String, String> maps);
    
    /**
     * POST
     *
     * @param url
     * @param maps
     * @return
     */
    @POST("{url}")
    Observable<BaseResponse<BaseResult>> executePost(@Path("url") String url, @QueryMap Map<String, String> maps);
    
    /**
     * POST
     *
     * @param url
     * @param jsonbody 请求体
     * @return
     */
    @POST("{url}")
    Observable<BaseResponse<BaseResult>> jsonRequest(@Path("url") String url, @Body RequestBody jsonbody);
    
    /**
     * POST
     *
     * @param url
     * @param maps 表单提交
     * @return
     */
    @FormUrlEncoded
    @POST("{url}")
    Observable<BaseResponse<BaseResult>> filedRequest(@Path("url") String url, @FieldMap Map<String, String> maps);
    
    /**
     * 单文件上传
     *
     * @param url
     * @param requestBody
     * @return
     */
    @Multipart
    @POST("{url}")
    Observable<ResponseBody> upLoadFile(@Path("url") String url, @Part("image\";filename=\"image.jpg") RequestBody requestBody);
    
    /**
     * 多文件上传
     *
     * @param url
     * @param description
     * @param maps
     * @return
     */
    @POST("{url}")
    Observable<ResponseBody> uploadFiles(
		    @Path("url") String url,
		    @Part("userName") String description,
		    @PartMap() Map<String, RequestBody> maps);
    
    
}
