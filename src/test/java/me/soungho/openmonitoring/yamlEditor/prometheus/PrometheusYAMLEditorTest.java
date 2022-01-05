package me.soungho.openmonitoring.yamlEditor.prometheus;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class PrometheusYAMLEditorTest {

    @Autowired
    private PrometheusYAMLEditor prometheusYAMLEditor;

    @Test
    void addHTTPEndPointTest() throws IOException {
        log.info("prometheusYAMLConfigPath: {}", prometheusYAMLEditor.getPrometheusYAMLConfigPath());
        prometheusYAMLEditor.addHTTPEndPoint("localhost:9999");
        prometheusYAMLEditor.addHTTPEndPoint("localhost:9999");
        Set httpEndpoints = prometheusYAMLEditor.getHTTPEndPoints();
        assertTrue(httpEndpoints.contains("localhost:9999"));
    }
}