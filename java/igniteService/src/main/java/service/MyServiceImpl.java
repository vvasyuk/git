package service;

import org.apache.ignite.services.Service;
import org.apache.ignite.services.ServiceContext;

public class MyServiceImpl implements Service {
    @Override
    public void cancel(ServiceContext ctx) {
        System.out.println("Service was cancelled: ");
    }

    @Override
    public void init(ServiceContext ctx) throws Exception {
        System.out.println("Initing distributed service: ");
    }

    @Override
    public void execute(ServiceContext ctx) throws Exception {
        System.out.println("Executing distributed service: ");
    }
}
