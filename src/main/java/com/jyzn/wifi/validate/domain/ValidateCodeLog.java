/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.validate.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author zyt
 */
@Entity
@Table(name = "validatecodelog")
public class ValidateCodeLog extends StringIdEntity {

    private String vcode;
    private ValidateLog vlog;

    public ValidateCodeLog() {

    }

    public ValidateCodeLog(String id) {
        this.id = id;
    }

    public ValidateCodeLog(String vcode, ValidateLog vlog) {
        this.vcode = vcode;
        this.vlog = vlog;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    @JoinColumn(name = "log_id")
    @OneToOne
    public ValidateLog getVlog() {
        return vlog;
    }

    public void setVlog(ValidateLog vlog) {
        this.vlog = vlog;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
