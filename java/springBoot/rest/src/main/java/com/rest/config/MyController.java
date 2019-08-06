package com.rest.config;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.ServletContextResource;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class MyController {

    @Autowired
    ServletContext servletContext;

    @RequestMapping("/hi")
    public String greeting() {
        return "hello";
    }

    @RequestMapping(value = "/img",produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage() throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/FAiryLantern1200-200x300.jpg");
        return IOUtils.toByteArray(in);
    }
}
