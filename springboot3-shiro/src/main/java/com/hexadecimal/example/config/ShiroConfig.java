package com.hexadecimal.example.config;

import com.hexadecimal.example.filter.AuthorizationFilter;
import com.hexadecimal.example.realm.UserRealm;
import jakarta.servlet.Filter;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * 配置安全管理器
     * @param userRealm
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 配置Shiro过滤器工厂
     * @param securityManager 安全管理器
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 注册安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 当用户访问认证资源的时候，如果用户没有登录，那么就会跳转到该属性指定的页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");

        // 定义资源访问规则
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/", "authc");
        map.put("/html/*", "authc");
        map.put("/index.html", "authc");
        // 登录接口不需要鉴权
        map.put("/user/login", "anon");
        map.put("/**", "authorization");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("authorization", new AuthorizationFilter());
        shiroFilterFactoryBean.setFilters(filters);

        return shiroFilterFactoryBean;
    }

}