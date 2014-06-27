/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jyzn.wifi.validate.repository;

import com.jyzn.wifi.validate.domain.ValidateCodeLog;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author zyt
 */
public interface ValidateCodeLogDao extends CrudRepository<ValidateCodeLog, String>,JpaSpecificationExecutor<ValidateCodeLog>{
    
}