/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.validate.platforminterface;

import java.io.Serializable;
import java.util.Map;

public interface SmsInterface extends Serializable {

   public Map HttpSendSms(String postUrl, String postData);
    
}
