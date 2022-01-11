package me.soungho.openmonitoring.RESTController;

import lombok.extern.slf4j.Slf4j;
import me.soungho.openmonitoring.feignApi.PrometheusApi;
import me.soungho.openmonitoring.utils.validation.annotation.HTTPEndpoint;
import me.soungho.openmonitoring.yamlEditor.prometheus.PrometheusYAMLConfig;
import me.soungho.openmonitoring.yamlEditor.prometheus.PrometheusYAMLEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/***
 * prometheus의 엔드포인트 설정 중계 컨트롤러
 */
@RestController
@RequestMapping("/restAPI/prometheus/endpoint")
@Slf4j
@Validated
public class PrometheusEndpointRestController {

    private PrometheusYAMLEditor prometheusYAMLEditor;
    private PrometheusApi prometheusApi;

    @Autowired
    public PrometheusEndpointRestController(
            PrometheusYAMLEditor prometheusYAMLEditor,
            PrometheusApi prometheusApi
    ){
        this.prometheusYAMLEditor = prometheusYAMLEditor;
        this.prometheusApi = prometheusApi;
    }

    //엔드포인트 추가 api
    @PostMapping("/add")
    public ResponseEntity<?> applyHTTPEndPoint(@RequestParam("endpoint") @HTTPEndpoint String endpoint) throws IOException {
        PrometheusYAMLConfig prometheusYAMLConfig = prometheusYAMLEditor.addHTTPEndPoint(endpoint);
        prometheusApi.reloadConfig();
        return ResponseEntity.ok(prometheusYAMLConfig);
    }

    //엔드포인트 삭제 api
    @PostMapping("/delete")
    public ResponseEntity<?>  deleteHTTPEndPoint(@RequestParam("endpoint") @HTTPEndpoint String endpoint) throws IOException {
        PrometheusYAMLConfig prometheusYAMLConfig = prometheusYAMLEditor.deleteHTTPEndPoint(endpoint);
        prometheusApi.reloadConfig();
        return ResponseEntity.ok(prometheusYAMLConfig);
    }
}
