package com.lding.pmbok.common.shiro;

import com.lding.pmbok.common.prop.PmsProperties;
import com.lding.pmbok.filter.ErrorFilter;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroCfg {
    @Bean
    public Realm realm() { // 自定义Realm（相当于数据源，可以用于获取主题的权限信息
        return new TokenRealm(new TokenMatcher());
    }

    /**
     * ShiroFilterFactoryBean用来告诉Shiro如何进行拦截
     * 1.拦截哪些URL
     * 2.每个URL需要进行哪些filter
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(Realm realm, PmsProperties properties) {
        ShiroFilterFactoryBean filterBean = new ShiroFilterFactoryBean();

        // 安全管理器
        filterBean.setSecurityManager(new DefaultWebSecurityManager(realm));

        // 添加一些自定义Filter
        Map<String, Filter> filters = new HashMap<>();
        filters.put("token", new TokenFilter());
        filterBean.setFilters(filters);

        // 设置URL如何拦截
        Map<String, String> urlMap = new LinkedHashMap<>();
        // 用户登录
        urlMap.put("/sysUsers/login", "anon");
        // 全局Filter的异常处理
        urlMap.put(ErrorFilter.ERROR_URI, "anon");
        // 上传的内容(/upload/**) TODO
        // urlMap.put("/" + properties.getUpload().getUploadPath() + "**", "anon");
        // 其他
        urlMap.put("/**", "token");
        filterBean.setFilterChainDefinitionMap(urlMap);

        return filterBean;
    }

    /**
     * 解决：@RequiresPermissions导致控制器接口404
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator proxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setUsePrefix(true);
        return proxyCreator;
    }
}
