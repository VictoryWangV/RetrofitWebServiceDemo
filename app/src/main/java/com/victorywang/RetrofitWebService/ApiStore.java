package com.victorywang.RetrofitWebService;

import com.victorywang.RetrofitWebService.request.RequestEnvelope;
import com.victorywang.RetrofitWebService.response.AssetResponseEnvelope;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface ApiStore {

    /*
     * 指定请求头:
     * "Content-Type: text/xml; charset=utf-8"指定文本格式,及编码格式
     * SOAPAction的值为
     * 分解为http://tempuri.org/ + AssetMaterialInfo,其实就是命名空间+接口名
     */
    @Headers({
            "Content-Type: text/xml; charset=utf-8",
            "SOAPAction: http://tempuri.org/AssetMaterialInfo"
    })
    @POST("GetService.asmx")
    Call<AssetResponseEnvelope> getAssetInfo(@Body RequestEnvelope requestEnvelope);

}
