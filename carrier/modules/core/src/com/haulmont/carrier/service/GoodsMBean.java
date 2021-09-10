package com.haulmont.carrier.service;

import org.springframework.jmx.export.annotation.ManagedResource;
import com.haulmont.cuba.core.sys.jmx.JmxBean;

@JmxBean(module = "carrier", alias = "goods")
@ManagedResource(description = "JMX bean for some settings")
public interface GoodsMBean {

   void removeExpiredFoodStuffs();
}