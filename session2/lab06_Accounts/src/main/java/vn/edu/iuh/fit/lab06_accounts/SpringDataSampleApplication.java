package vn.edu.iuh.fit.lab06_accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import vn.edu.iuh.fit.lab06_accounts.models.Account;
import vn.edu.iuh.fit.lab06_accounts.repositories.AccountRepository;

import java.util.Random;

@SpringBootApplication
public class SpringDataSampleApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringDataSampleApplication.class, args);
    }

    @Autowired
    private AccountRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Random random = new Random();
//        for (long i = 1; i <= 100; i++) {
//            Account account = new Account(i, "Owner " + i, random.nextDouble() * 1000);
//            repository.save(account);
//        }
//        repository.findAll().forEach(System.out::println);
        PageRequest pageRequest = PageRequest.of(0, 10);
//        repository.findAll(pageRequest).forEach(System.out::println);

        repository.findByBalanceGreaterThan(50000, pageRequest).forEach(System.out::println);
    }
}