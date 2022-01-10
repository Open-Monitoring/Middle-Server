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

/***
 * prometheus의 엔드포인트 설정 중계 컨트롤러
 */
@Controller
@RequestMapping("/prometheus/endpoint")
@Slf4j
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

    /***
     * 엔드포인트 추가 api
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @PostMapping("/add")
    public String applyHTTPEndPoint(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String endpoint = request.getParameter("endpoint");
        prometheusYAMLEditor.addHTTPEndPoint(endpoint);
        prometheusApi.reloadConfig();
        return "redirect:/";
    }

    /***
     * 엔드포인트 삭제 api
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @PostMapping("/delete")
    public String deleteHTTPEndPoint(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String endpoint = request.getParameter("endpoint");
        prometheusYAMLEditor.deleteHTTPEndPoint(endpoint);
        prometheusApi.reloadConfig();
        return "redirect:/";
    }
}
