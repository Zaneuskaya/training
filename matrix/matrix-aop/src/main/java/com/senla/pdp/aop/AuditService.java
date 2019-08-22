package com.senla.pdp.aop;

import java.sql.Date;
import java.time.Instant;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditService {

    @Autowired
    JmsTemplate jmsTemplate;

    @After("@annotation(Audit)")
    public void logMethodCall(JoinPoint jp) {
        Event event = new Event(Date.from(Instant.now()),jp.getSignature().toLongString(), "SUCCESS", "User");
        jmsTemplate.convertAndSend("inbound.queue",event);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(jp.getSignature() + " executed ");
    }

}
