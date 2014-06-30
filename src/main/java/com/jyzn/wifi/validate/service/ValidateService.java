/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.validate.service;

import com.google.common.collect.Maps;
import com.jyzn.wifi.validate.domain.ValidateCodeLog;
import com.jyzn.wifi.validate.domain.ValidateLog;
import com.jyzn.wifi.validate.domain.WifiUser;
import com.jyzn.wifi.validate.repository.ValidateCodeLogDao;
import com.jyzn.wifi.validate.repository.ValidateLogDao;
import com.jyzn.wifi.validate.repository.WifiUserDao;
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

    private final ValidateLogDao validatelogdao;
    private final WifiUserDao wifiuserdao;
    private final ValidateCodeLogDao vcdao;
    /*
     setValidateLogDao 是无效的! 必须使用以下方式
     */

    @Autowired
    public ValidateService(ValidateLogDao validatelogdao, WifiUserDao wifiuserdao, ValidateCodeLogDao vcdao) {
        this.validatelogdao = validatelogdao;
        this.wifiuserdao = wifiuserdao;
        this.vcdao = vcdao;

    }

    /*根据WifiuserId查找ValidateLog*/
    public List<ValidateLog> getValidateLogByWifiuserId(String wifiuserId) {
        Map<String, SearchFilter> filters = Maps.newHashMap();
        filters.put("wifiuser.id", new SearchFilter("wifiuser.id", SearchFilter.Operator.EQ, wifiuserId));
        Specification<ValidateLog> spec = DynamicSpecifications.bySearchFilter(filters.values(), ValidateLog.class);
        return this.validatelogdao.findAll(spec);
    }

    /*根据 Map<String, Object> searchParams 查找ValidateLog*/
    public List<ValidateLog> getValidateLogByFilters(Map<String, Object> searchParams) {
        Specification<ValidateLog> spec = buildSpecification(searchParams);
        return this.validatelogdao.findAll(spec);
    }

    /*根据 Map<String, Object> searchParams 统计ValidateLog*/
    public int countValidateLogByFilters(Map<String, Object> searchParams) {
        Specification<ValidateLog> spec = buildSpecification(searchParams);
        return (int) this.validatelogdao.count(spec);
    }

    /*构建查询条件*/
    private Specification<ValidateLog> buildSpecification(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<ValidateLog> spec = DynamicSpecifications.bySearchFilter(filters.values(), ValidateLog.class);
        return spec;
    }

    public String getValidateCodeLogByValidateLogId(String id) {
        return this.validatelogdao.findOne(id).getValidateCodeLog().getVcode();
    }

    public WifiUser findWifiUserByName(String Name) {
        return this.wifiuserdao.findByName(Name);
    }

    public void saveWifiUser(WifiUser entity) {
        this.wifiuserdao.save(entity);
    }

    public void saveValidateLog(ValidateLog entity) {
        validatelogdao.save(entity);
    }

    public void saveValidateCodeLog(ValidateCodeLog entity) {
        vcdao.save(entity);
    }

    public List<ValidateLog> getAllvalidatelogdao() {
        return (List<ValidateLog>) this.validatelogdao.findAll();
    }

}
