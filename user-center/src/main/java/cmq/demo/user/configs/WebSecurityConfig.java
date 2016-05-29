package cmq.demo.user.configs;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by cuimingqiang on 16/5/28.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    private static Logger logger = Logger.getLogger(WebSecurityConfigurerAdapter.class);

    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        logger.info("---------------from http");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.inMemoryAuthentication()
                .withUser("cmq")
                .password("cmq");

        logger.info("---------------from auth");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        logger.info("---------------from web");
    }
}
