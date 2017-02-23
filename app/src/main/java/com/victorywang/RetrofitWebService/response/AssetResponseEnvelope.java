package com.victorywang.RetrofitWebService.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

/**
 * Created by Tom on 2017-2-23.
 */
// 指定节点名称,soap:可以省略,即@Root(name = "Envelope")
// 当类名与节点名相同时,name属性可以省略,即@Root
@Root(name = "soap:Envelope")
// 指定命名空间集合,内部可以嵌套多个@Namespace注解
@NamespaceList({
        /*
         *  指定命名空间,reference属性指定命名空间名称,prefix属性指定前缀.
         *  例如:
         *      命名空间为:xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         *      则用注解表示为@Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance/", prefix = "xsi")
         */
        @Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance/", prefix = "xsi"),
        @Namespace(reference = "http://www.w3.org/2001/XMLSchema/", prefix = "xsd"),
        @Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soap")
})
public class AssetResponseEnvelope {
    // 指定子节点的节点名,应与AssetResponseBody的@Root注解对应
    @Element(name = "Body", required = false)
    public AssetResponseBody responseBody;
}
