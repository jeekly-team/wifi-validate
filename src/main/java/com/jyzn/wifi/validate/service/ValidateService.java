/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.validate.service;

import com.google.common.collect.Maps;
import com.jyzn.wifi.validate.domain.ValidateLog;
import com.jyzn.wifi.validate.repository.ValidateLogDao;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

@Component("validateService")
@Transactional
public class ValidateService {

    private ValidateLogDao validatelogdao;

    public List<ValidateLog> getWifiuserValidateLog(String wifiuserId) {
        Specification<ValidateLog> spec = buildSpecification(wifiuserId);
        return validatelogdao.findAll(spec);
    }
    /**
     * 创建动态查询条件组合.如Task的名为"user.name"的filedName, 转换为Task.user.name属性
     * SearchFilter 使用com.google.guava（com.google.common.collect.Maps;）
     */
    private Specification<ValidateLog> buildSpecification(String wifiuserId) {
        Map<String,SearchFilter> filters = Maps.newHashMap();
        filters.put("wifiuser.id", new SearchFilter("wifiuser.id", SearchFilter.Operator.EQ, wifiuserId));
        Specification<ValidateLog> spec = DynamicSpecifications.bySearchFilter(filters.values(), ValidateLog.class);
        return spec;
    }

    public List<ValidateLog> getAllvalidatelogdao() {
        return (List<ValidateLog>) validatelogdao.findAll();
    }
    @Autowired
    public void setValidatelogdao(ValidateLogDao validatelogdao) {
        this.validatelogdao = validatelogdao;
    }

}
