package me.soungho.openmonitoring.Controller;

import lombok.extern.slf4j.Slf4j;
import me.soungho.openmonitoring.feignApi.PrometheusApi;
import me.soungho.openmonitoring.yamlEditor.prometheus.PrometheusYAMLEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/prometheus")
@Slf4j
public class PrometheusController {

    private PrometheusYAMLEditor prometheusYAMLEditor;
    private PrometheusApi prometheusApi;

    @Autowired
    public PrometheusController(
            PrometheusYAMLEditor prometheusYAMLEditor,
            PrometheusApi prometheusApi
    ){
        this.prometheusYAMLEditor = prometheusYAMLEditor;
        this.prometheusApi = prometheusApi;
    }

    @GetMapping("/form")
    public String index(){
        return "prometheus/inputForm";
    }

    @PostMapping("/endpoint/add")
    public String applyHTTPEndPoint(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String endpoint = request.getParameter("endpoint");
        prometheusYAMLEditor.addHTTPEndPoint(endpoint);
        prometheusApi.reloadConfig();
        return "redirect:/";
    }
}
