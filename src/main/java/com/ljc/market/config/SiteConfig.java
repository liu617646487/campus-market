package com.ljc.market.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 * 网站基本信息配置类
 */


@PropertySource(value = "classpath:/site.properties")
@Component
public class SiteConfig {

    @Value("${ljc.site.name}")
    private String name;
    @Value("${ljc.site.url}")
    private String url;
    @Value("${ljc.site.email}")
    private String email;
    @Value("${ljc.site.university.name}")
    private String university_name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniversity_name() {
        return university_name;
    }

    public void setUniversity_name(String university_name) {
        this.university_name = university_name;
    }
}
