package org.bookStore;

import com.alibaba.druid.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@Slf4j
@EnableTransactionManagement
public class WebApplication {
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(WebApplication.class, args);
        System.out.println(
                "  ____  __  __    ___    ___     __    ____    ____  \n" +
                        " /',__\\/\\ \\/\\ \\  /'___\\ /'___\\ /'__`\\ /',__\\  /',__\\ \n" +
                        "/\\__, `\\ \\ \\_\\ \\/\\ \\__//\\ \\__//\\  __//\\__, `\\/\\__, `\\\n" +
                        "\\/\\____/\\ \\____/\\ \\____\\ \\____\\ \\____\\/\\____/\\/\\____/\n" +
                        " \\/___/  \\/___/  \\/____/\\/____/\\/____/\\/___/  \\/___/ \n");
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("local.server.port");
        String path = env.getProperty("local.server.servlet.context-path");
        if (StringUtils.isEmpty(path)) {
            path = "";
        }
        log.info("\n----------------------------------------------------------\n\t" +
                "Application  is running! Access URLs:\n\t" +
                "Local address: \t\thttp://localhost:" + port + path + "\n\t" +
                "External address: \thttp://" + ip + ":" + port + path + "\n\t" +
                "----------------------------------------------------------");
        String jvmName = ManagementFactory.getRuntimeMXBean().getName();
        log.info("Current project thread number：" + jvmName.split("@")[0]);

    }
}