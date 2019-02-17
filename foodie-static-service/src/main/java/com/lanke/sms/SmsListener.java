package com.lanke.sms;


import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class SmsListener {

    @JmsListener(destination="statics")
    public void sendSms(String username){

        log.info("接收到消息***********************："+username);

    }
}
