package com.itj.bootcamp.taskflow.internals;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * A BeanPostProcessor runs for EVERY bean, twice (before & after the bean's
 * initialization callbacks like @PostConstruct). This is the exact extension
 * point Spring itself uses to implement @Autowired, @Transactional proxies,
 * @Async, validation, and much more.
 *
 * Here we just log our own beans to make the lifecycle visible.
 */
@Component
public class LoggingBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().getName().startsWith("com.itj.bootcamp.taskflow")) {
            System.out.println(">> [BeanPostProcessor] before-init: " + beanName
                    + " (" + bean.getClass().getSimpleName() + ")");
        }
        return bean;
    }
}
