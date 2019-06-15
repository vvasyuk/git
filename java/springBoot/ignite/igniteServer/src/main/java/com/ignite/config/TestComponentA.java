package com.ignite.config;

import org.apache.ignite.Ignite;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Component
@Profile("testA")
public class TestComponentA {

    static Ignite ignite;

    @Autowired
    public void TestComponent(Ignite ign) {
        System.out.println("running TestComponentA");
        ignite = ign;
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        resultReport(junit.run(TestComponentA.class));
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void doSomethingAfterStartup() {
//        System.out.println("running TestComponentA init");
//        JUnitCore junit = new JUnitCore();
//        junit.addListener(new TextListener(System.out));
//        resultReport(junit.run(TestClient.class));
//    }

    public static void resultReport(Result result) {
        System.out.println("Finished. Result: Failures: " +
                result.getFailureCount() + ". Ignored: " +
                result.getIgnoreCount() + ". Tests run: " +
                result.getRunCount() + ". Time: " +
                result.getRunTime() + "ms.");
    }

    @Test
    public void ignite_cache_test_ok() {
        System.out.println(TestComponentA.ignite);
        assertTrue("one".equals(TestComponentA.ignite.cache("test").get(1)));
    }

    @Test
    public void ignite_cache_test_nok() {
        System.out.println(TestComponentA.ignite);
        assertFalse("two".equals(TestComponentA.ignite.cache("test").get(1)));
    }
}
