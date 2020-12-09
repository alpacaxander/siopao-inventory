package com.siopao.inventory.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ImageDataHandler {

    RestTemplate restTemplate;

    String filesUrl;

    ImageDataHandler(
            @Value("siopao.gatewayUrl") String gateway,
            @Value("siopao.services.files.route") String filesRoute
    ) {
        this.restTemplate = new RestTemplate();
        this.filesUrl = gateway + filesRoute;
    }

    public String get(String id) {
        return restTemplate.getForObject(this.filesUrl + id, String.class);
    }
}
