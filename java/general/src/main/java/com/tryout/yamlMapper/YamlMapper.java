package com.tryout.yamlMapper;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class YamlMapper {

    public void execute() {
        System.out.println("starting yaml mapper");

        Yaml yaml = new Yaml();
        InputStream inputStream = null;
        try {
            inputStream = Files.newInputStream(Paths.get("D:\\work\\tryout\\java\\general\\src\\main\\resources\\foobar.yaml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FooBar fooBar = yaml.loadAs( inputStream, FooBar.class );

        System.out.println(fooBar.getBar());
        System.out.println(fooBar.getAddress().get("city"));
        System.out.println(fooBar.getRoles()[0]);

        System.out.println(fooBar.getF1().get("K1"));
        System.out.println(fooBar.getB1().get("K2"));

    }

}
