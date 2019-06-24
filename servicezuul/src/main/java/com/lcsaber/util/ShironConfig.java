package com.lcsaber.util;

import com.lcsaber.Service.MyRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Map;
import java.util.HashMap;
/**
 * @Author: LiChao
 * @Date: 2019/6/17 14:29
 */
@Configuration
public class ShironConfig {

    @Bean("securityManager")
    public DefaultWebSecurityManager getManager(MyRealm realm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm);
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator evaluator = new DefaultSessionStorageEvaluator();
        evaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(evaluator);
        manager.setSubjectDAO(subjectDAO);
        SecurityUtils.setSecurityManager(manager);
        return manager;
    }

    @Bean
    public Queue createQueue(){
        return new Queue("log-queue");
    }

}
