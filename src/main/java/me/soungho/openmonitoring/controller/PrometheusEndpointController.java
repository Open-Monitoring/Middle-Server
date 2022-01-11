package me.soungho.openmonitoring.controller;

import lombok.extern.slf4j.Slf4j;
import me.soungho.openmonitoring.feignApi.PrometheusApi;
import me.soungho.openmonitoring.utils.validation.annotation.HTTPEndpoint;
import me.soungho.openmonitoring.yamlEditor.prometheus.PrometheusYAMLEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

/***
 * prometheus의 엔드포인트 설정 중계 컨트롤러
 */
@Controller
@RequestMapping("/prometheus/endpoint")
@Slf4j
@Validated
public class PrometheusEndpointController {

    private PrometheusYAMLEditor prometheusYAMLEditor;
    private PrometheusApi prometheusApi;

    @Autowired
    public PrometheusEndpointController(
            PrometheusYAMLEditor prometheusYAMLEditor,
            PrometheusApi prometheusApi
    ){
        this.prometheusYAMLEditor = prometheusYAMLEditor;
        this.prometheusApi = prometheusApi;
    }

    /***
     * 엔드포인트 추가 폼 화면
     * @return
     */
    @GetMapping("/form/add")
    public String addForm(){
        return "prometheus/addForm";
    }

    /***
     * 엔드포인트 삭제 폼 화면
     * @return
     */
    @GetMapping("/form/delete")
    public String deleteForm(){
        return "prometheus/deleteForm";
    }

    //엔드포인트 추가 api
    @PostMapping("/add")
    public String applyHTTPEndPoint(@RequestParam("endpoint") @HTTPEndpoint String endpoint) throws IOException {
        prometheusYAMLEditor.addHTTPEndPoint(endpoint);
        prometheusApi.reloadConfig();
        return "redirect:/";
    }

    //엔드포인트 삭제 api
    @PostMapping("/delete")
    public String deleteHTTPEndPoint(@RequestParam("endpoint") @HTTPEndpoint String endpoint) throws IOException {
        prometheusYAMLEditor.deleteHTTPEndPoint(endpoint);
        prometheusApi.reloadConfig();
        return "redirect:/";
    }
}
