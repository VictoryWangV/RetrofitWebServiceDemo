package com.victorywang.RetrofitWebService;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.victorywang.RetrofitWebService.request.RequestBody;
import com.victorywang.RetrofitWebService.request.RequestEnvelope;
import com.victorywang.RetrofitWebService.request.RequestModel;
import com.victorywang.RetrofitWebService.response.AssetResponseBody;
import com.victorywang.RetrofitWebService.response.AssetResponseEnvelope;
import com.victorywang.RetrofitWebService.response.AssetResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    EditText etUrl;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        etUrl = (EditText) findViewById(R.id.et_url);
        tvResult = (TextView) findViewById(R.id.tv_result);
        findViewById(R.id.btn_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });
    }

    /**
     * 去服务端请求数据
     */
    private void request() {
        String url = etUrl.getText().toString().trim();
        // 初始化Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(SimpleXmlConverterFactory.create()) // 返回数据为xml,因此要加入xml解析
                .build();
        ApiStore apiStore = retrofit.create(ApiStore.class);
        // 初始化请求体
        RequestModel requestModel = new RequestModel("2012-01-01", 0);
        RequestBody requestBody = new RequestBody(requestModel);
        RequestEnvelope requestEnvelope = new RequestEnvelope(requestBody);
        // 开始请求
        Call<AssetResponseEnvelope> call = apiStore.getAssetInfo(requestEnvelope);
        call.enqueue(new Callback<AssetResponseEnvelope>() {
            @Override
            public void onResponse(Call<AssetResponseEnvelope> call, Response<AssetResponseEnvelope> response) {
                // 处理响应体
                AssetResponseEnvelope responseEnvelope = response.body();
                if (responseEnvelope == null) {
                    Log.d(TAG, "onResponse: responseEnvelope == null");
                    return;
                }
                AssetResponseBody responseBody = responseEnvelope.responseBody;
                if (responseBody == null) {
                    Log.d(TAG, "onResponse: responseBody == null");
                    return;
                }
                AssetResponseModel responseModel = responseBody.responseModel;
                if (responseModel == null) {
                    Log.d(TAG, "onResponse: responseModel == null");
                    return;
                }
                String result = responseModel.result;
                Log.d(TAG, "onResponse: result : " + result);
                showResult(result);
            }

            @Override
            public void onFailure(Call<AssetResponseEnvelope> call, Throwable t) {

            }
        });
    }

    /**
     * 将解析完的数据显示到界面上
     * @param result 解析的数据
     */
    private void showResult(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvResult.setText(result);
            }
        });
    }
}
