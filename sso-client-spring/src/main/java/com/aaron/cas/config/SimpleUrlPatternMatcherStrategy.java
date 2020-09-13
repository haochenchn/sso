package com.aaron.cas.config;

import org.jasig.cas.client.authentication.UrlPatternMatcherStrategy;

import java.util.regex.Pattern;

/**
 * @author Aaron
 * @description 自定义鉴权路径
 * @date 2020/9/13
 */
public class SimpleUrlPatternMatcherStrategy implements UrlPatternMatcherStrategy {

    private Pattern pattern;

    @Override
    public boolean matches(String url) {
        //在此可以做额外的扩展,比如判断是 api 也放行，可以通过查询数据库来进行动态判断
        if(url.contains("/api")){
            return true;
        }

        //默认是根据cas.ignore-pattern来判断是否否满足过滤
        return this.pattern.matcher(url).find();
    }

    /**
     * 这个pattern就是 在application.properties中配置的cas.ignore-pattern
     * @param pattern
     */
    @Override
    public void setPattern(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }
}
