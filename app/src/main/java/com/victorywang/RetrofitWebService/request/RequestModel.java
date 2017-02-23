package com.victorywang.RetrofitWebService.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;


@Root(name = "AssetMaterialInfo", strict = false)
@Namespace(reference = "http://tempuri.org/")
public class RequestModel {
    @Element(name = "date", required = false)
    public String date;
    @Element(name = "page", required = false)
    public int page;

    public RequestModel(String date, int page) {
        this.date = date;
        this.page = page;
    }
}
