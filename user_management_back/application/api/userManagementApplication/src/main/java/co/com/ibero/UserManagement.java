package co.com.ibero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "co.com.ibero")
public class UserManagement {
    public static void main(String[] args) {
        SpringApplication.run(UserManagement.class);
    }
}