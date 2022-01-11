package me.soungho.openmonitoring.restController;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import me.soungho.openmonitoring.feignApi.PrometheusApi;
import me.soungho.openmonitoring.utils.validation.annotation.HTTPEndpoint;
import me.soungho.openmonitoring.yamlEditor.prometheus.PrometheusYAMLConfig;
import me.soungho.openmonitoring.yamlEditor.prometheus.PrometheusYAMLEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/***
 *
 */
@Api(tags = {"1. prometheus의 엔드포인트 설정 중계 REST 컨트롤러"})
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
            PrometheusApi prometheusApi){
        this.prometheusYAMLEditor = prometheusYAMLEditor;
        this.prometheusApi = prometheusApi;
    }

    @PostMapping("/add")
    @ApiOperation(value = "엔드포인트 추가", notes = "prometheus의 HTTP endpoint를 추가합니다.")
    public ResponseEntity<?> applyHTTPEndPoint(
            @ApiParam(value = "추가 할 엔드포인트") @RequestParam("endpoint") @HTTPEndpoint String endpoint) throws IOException {
        PrometheusYAMLConfig prometheusYAMLConfig = prometheusYAMLEditor.addHTTPEndPoint(endpoint);
        prometheusApi.reloadConfig();
        return ResponseEntity.ok(prometheusYAMLConfig);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "엔드포인트 삭제", notes = "prometheus의 HTTP endpoint를 삭제합니다.")
    public ResponseEntity<?>  deleteHTTPEndPoint(
            @ApiParam(value = "삭제 할 엔드포인트") @RequestParam("endpoint") @HTTPEndpoint String endpoint) throws IOException {
        PrometheusYAMLConfig prometheusYAMLConfig = prometheusYAMLEditor.deleteHTTPEndPoint(endpoint);
        prometheusApi.reloadConfig();
        return ResponseEntity.ok(prometheusYAMLConfig);
    }
}
