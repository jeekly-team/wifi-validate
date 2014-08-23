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

package com.jyzn.wifi.validate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
/*
EnableJpaRepositories 注解，表明Spring Data JPA遍历任何实现了org.springframework.data.repository.Repository接口的接口，然后自动生成它的实现类
*/
@EnableJpaRepositories
@EnableAutoConfiguration
public class SampleDataJpaApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleDataJpaApplication.class, args);
	}

}
