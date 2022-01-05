package me.soungho.openmonitoring.yamlEditor.prometheus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrometheusYAMLConfig {

    Global global;
    List<ScrapeConfigs> scrape_configs;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Global{
        String scrape_interval;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ScrapeConfigs{
        String job_name;
        String scrape_interval;
        List<StaticConfigs> static_configs;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StaticConfigs{
        Set<String> targets;
    }

}
