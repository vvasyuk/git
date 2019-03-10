package com.ignite.config;


import com.ignite.util.StandaloneClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

@Profile("standalone")
@Component
public class TestStandaloneClient {

    @Autowired
    public TestStandaloneClient(Environment e) throws Exception {
        //StandaloneClient sc = new StandaloneClient();
        //System.out.println(sc.getIgnite().name());
        String methodName = System.getProperty("methodName");
        TestStandaloneClient.class.getMethod(methodName).invoke(this);
    }

    public void methodNameA(){
        System.out.println("methodNameA");
    }

    public void methodNameB(){
        System.out.println("methodNameB");
    }
}
