package me.soungho.openmonitoring.yamlEditor.prometheus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
@Data
public class PrometheusYAMLEditor {

    @Value("${prometheus.config.path}")
    private String prometheusYAMLConfigPath;

    public void addHTTPEndPoint(String endpoint) throws IOException {
        ObjectMapper objectMapper = new YAMLMapper();

        PrometheusYAMLConfig prometheusConfig = objectMapper.readValue(
                new File(prometheusYAMLConfigPath),
                PrometheusYAMLConfig.class);

        if(prometheusConfig.getScrape_configs().get(0).static_configs.get(0).targets == null)
            prometheusConfig.getScrape_configs().get(0).static_configs.get(0).targets = new HashSet();
        prometheusConfig.getScrape_configs().get(0).static_configs.get(0).targets.add(endpoint);
        objectMapper.writeValue(new File(prometheusYAMLConfigPath), prometheusConfig);
    }

    public void deleteHTTPEndPoint(String endpoint) throws IOException {
        ObjectMapper objectMapper = new YAMLMapper();

        PrometheusYAMLConfig prometheusConfig = objectMapper.readValue(
                new File(prometheusYAMLConfigPath),
                PrometheusYAMLConfig.class);

        if(prometheusConfig.getScrape_configs().get(0).static_configs.get(0).targets == null)
            return;

        Set targets = prometheusConfig.getScrape_configs().get(0).static_configs.get(0).targets;
        if(targets.contains(endpoint))
            targets.remove(endpoint);

        objectMapper.writeValue(new File(prometheusYAMLConfigPath), prometheusConfig);
    }

    public Set getHTTPEndPoints() throws IOException {
        ObjectMapper objectMapper = new YAMLMapper();

        PrometheusYAMLConfig prometheusConfig = objectMapper.readValue(
                new File(prometheusYAMLConfigPath),
                PrometheusYAMLConfig.class);

        return prometheusConfig.getScrape_configs().get(0).static_configs.get(0).targets;
    }


}
