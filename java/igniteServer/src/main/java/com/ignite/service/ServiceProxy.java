package com.ignite.service;

import org.apache.ignite.Ignite;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.apache.ignite.services.Service;
import org.apache.ignite.services.ServiceContext;

public class ServiceProxy implements Service, MyService {
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
        System.out.println("proxy execute");
    }

    @Override
    public void doStuff() {
        ignite.compute().execute("service.InitCounterTask", "a b c d e f");
    }
}
