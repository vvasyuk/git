package com.ignite.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.apache.ignite.services.Service;
import org.apache.ignite.services.ServiceContext;

public class ServiceProxy implements Service {
    @IgniteInstanceResource
    private Ignite ignite;

    @Override
    public void cancel(ServiceContext ctx) {
        System.out.println("proxy cancel");
    }

    @Override
    public void init(ServiceContext ctx) throws Exception {
        System.out.println("proxy init");
    }

    @Override
    public void execute(ServiceContext ctx) throws Exception {
        ignite.compute().execute("service.GarExample", "a b c d e f");
    }
}
