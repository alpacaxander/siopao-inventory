package com.siopao.inventory.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ImageDataHandler {

    RestTemplate restTemplate;

    String filesUrl;

    ImageDataHandler(@Value("${siopao.services.files.url}") String filesUrl) {
        this.restTemplate = new RestTemplate();
        this.filesUrl = filesUrl + "/file";
    }

    public String get(String id) {
        return restTemplate.getForObject(this.filesUrl + "/" + id, String.class);
    }
}
