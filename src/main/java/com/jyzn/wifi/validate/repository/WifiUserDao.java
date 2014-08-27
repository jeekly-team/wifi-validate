/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jyzn.wifi.validate.repository;

import com.jyzn.wifi.validate.domain.WifiUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface WifiUserDao extends PagingAndSortingRepository<WifiUser, String>,JpaSpecificationExecutor<WifiUser>{
    
}
