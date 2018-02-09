package com.example.demo.conf;

import com.example.common.CommonBaseConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;
/**
 * Created by Jopa on 10/23/2017.
 */
@Configuration
@Import({CommonBaseConfig.class})
public class BaseConfig {}

