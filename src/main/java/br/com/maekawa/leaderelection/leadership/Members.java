package br.com.maekawa.leaderelection.leadership;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties("members")
public class Members {
    private List<String> url;

    public List<String> getUrl() { return url; }

    public void setUrl(List<String> url) {
        this.url = url;
    }
}
