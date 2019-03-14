package camel.conf;

import camel.common.CommonBaseConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Jopa on 10/23/2017.
 */
@Configuration
@Import({CommonBaseConfig.class})
public class BaseConfig { }

