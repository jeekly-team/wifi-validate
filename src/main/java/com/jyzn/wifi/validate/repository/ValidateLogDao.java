/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jyzn.wifi.validate.repository;


import com.jyzn.wifi.validate.domain.ValidateLog;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ValidateLogDao extends CrudRepository<ValidateLog, String>,JpaSpecificationExecutor<ValidateLog>{
    
}
