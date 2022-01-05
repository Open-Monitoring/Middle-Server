package me.soungho.openmonitoring.feignApi;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "feignApi.PrometheusApi", url = "${prometheus.api.url}", decode404 = true)
public interface PrometheusApi {

    @PostMapping("/-/reload")
    public Response reloadConfig();
}
