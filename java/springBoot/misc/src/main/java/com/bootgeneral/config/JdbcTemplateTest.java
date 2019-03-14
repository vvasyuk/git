package com.bootgeneral.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

//--spring.profiles.active=jdbc
@Component
@Profile("jdbc")
public class JdbcTemplateTest implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running JdbcTemplateTest");

        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT col1, col2 FROM test");
        for (Map row : rows) {
            System.out.println(row.get("col1") + ":" + row.get("col2"));
        }
        System.out.println("outside thread getById: " + getById(jdbcTemplate, "12"));
//        insert(jdbcTemplate, "10", "12");
//        System.out.println("getById: " + getById(jdbcTemplate, "12"));

        Thread t1 = new Thread(() -> {
            System.out.println("Lambda Runnable running t1");
            insert(jdbcTemplate, "10", "12");
            System.out.println("inside thread getById: " + getById(jdbcTemplate, "12"));
        });
        t1.start();
        //t1.join();
        System.out.println("outside thread getById: " + getById(jdbcTemplate, "12"));
        System.out.println("t1 is finished");
    }

    @Transactional
    public void insert(JdbcTemplate template, String col1, String col2){
        template.update(
                "INSERT INTO test (col1, col2) VALUES (?, ?)",
                col1, col2
        );
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getById(JdbcTemplate template, String id){
        String res = "";
        try{
            res = (String) jdbcTemplate.queryForObject("select col2 from test where col2=?", new Object[] { id }, String.class);
        } catch (Exception e){
            //System.out.println("getById query returned no data");
        }
        return res;
    }
}
