package cn.joylau.code.joylau.test;

import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TransportClient transportClient;

    @RequestMapping("/test")
    public String test(){
        return transportClient.nodeName();
    }
}
