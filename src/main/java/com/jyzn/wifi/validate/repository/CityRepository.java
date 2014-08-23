/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jyzn.wifi.validate.repository;

import com.jyzn.wifi.validate.domain.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;


/**
 *
 * @author zyt 
 * Repository： 仅仅是一个标识，表明任何继承它的均为仓库接口类，方便Spring自动扫描识别
 * CrudRepository： 继承Repository，实现了一组CRUD相关的方法 PagingAndSortingRepository：
 * 继承CrudRepository，实现了一组分页排序相关的方法 JpaRepository：
 * 继承PagingAndSortingRepository，实现一组JPA规范相关的方法 JpaSpecificationExecutor：
 * 比较特殊，不属于Repository体系，实现一组JPA Criteria查询相关的方法
 */
public interface CityRepository extends Repository<City, Long> {

    Page<City> findAll(Pageable pageable);

    Page<City> findByNameContainingAndCountryContainingAllIgnoringCase(String name,String country, Pageable pageable);

    City findByNameAndCountryAllIgnoringCase(String name, String country);

}
