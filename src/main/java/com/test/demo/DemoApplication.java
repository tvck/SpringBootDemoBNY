package com.test.demo;

import com.test.demo.model.User;
import com.test.demo.repository.UserRepository;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootApplication
public class DemoApplication {
    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserRepository userRepository;
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() throws Exception{
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            ResponseEntity<List<User>> userResponse =
                    restTemplate.exchange("https://jsonplaceholder.typicode.com/users",
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                            });
            List<User> users = userResponse.getBody();
            log.info("Creating tables");
            List<Object[]> usersList=users.stream().map(user->new Object[]{user.getId(),user.getName(),user.getUsername(),user.getEmail(),user.getAddress().toString(),user.getPhone(),user.getWebsite(),user.getCompany().toString()}).collect(Collectors.toList());
//            jdbcTemplate.execute("CREATE TABLE users11(" +
//                    "id VARCHAR2(255), name VARCHAR2(255),  username VARCHAR2(255), email VARCHAR2(255), address VARCHAR2(255), phone VARCHAR2(255), website VARCHAR2(255),  company VARCHAR2(255))");
//            jdbcTemplate.batchUpdate("INSERT INTO users11(id, name, username, email, address, phone, website, company) VALUES (?,?,?,?,?,?,?,?)", usersList);
//
            for(User user:users) {
                log.info(user.toString());
                userRepository.save(user);
            }


        };
    }


}
