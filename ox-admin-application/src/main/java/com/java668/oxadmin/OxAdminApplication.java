package com.java668.oxadmin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Jerry.chen
 * @desc ox-admin启动类
 * @date 2023/03/29 18:05
 **/
@Slf4j
@SpringBootApplication
public class OxAdminApplication {

    public static void main(String[] args) throws UnknownHostException {
        ApplicationContext context = SpringApplication.run(OxAdminApplication.class, args);
        Environment env = context.getEnvironment();
        String host = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        log.info(
                "\n----------------------------------------------------------\n\t"
                        + "Application '{}' is running! Access URLs:\n\t"
                        + "Local: \t\thttp://localhost:{}\n\t"
                        + "External: \thttp://{}:{}\n\t"
                        + "Doc: \thttp://localhost:{}/doc.html\n\t"
                        + "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                host,
                port,
                port);
    }
}
