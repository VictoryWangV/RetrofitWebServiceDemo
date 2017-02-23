package com.victorywang.RetrofitWebService.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root(name = "soap:Body", strict = false)
public class RequestBody {
    @Element(name = "AssetMaterialInfo", required = false)
    public RequestModel AssetMaterialInfo;

    public RequestBody(RequestModel assetMaterialInfo) {
        AssetMaterialInfo = assetMaterialInfo;
    }
}
