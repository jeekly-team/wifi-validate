/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.validate.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "validatelog")
public class ValidateLog extends IdEntity {

    private Date dt;     //提交日期

    private WifiUser wifiuser;

    private String type; //本次验证类型如MSG,WX...

    private String sid; //商户ID
    
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }
    // JPA 基于USER_ID列的多对一关系定义
    @ManyToOne
    @JoinColumn(name = "wifiuser_id")
    public WifiUser getWifiuser() {
        return wifiuser;
    }

    public void setWifiuser(WifiUser wifiuser) {
        this.wifiuser = wifiuser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
