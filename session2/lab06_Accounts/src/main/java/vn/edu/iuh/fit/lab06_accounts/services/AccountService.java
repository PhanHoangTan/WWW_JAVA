package vn.edu.iuh.fit.lab06_accounts.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.lab06_accounts.models.Account;
import vn.edu.iuh.fit.lab06_accounts.repositories.AccountRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public Page<Account> getAccounts(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    public Account updateAccount(Long id, Account accountDetails) {
        return accountRepository.findById(id).map(account -> {
            account.setOwnerName(accountDetails.getOwnerName());
            account.setBalance(accountDetails.getBalance());
            return accountRepository.save(account);
        }).orElseThrow(() -> new RuntimeException("Account not found with id " + id));
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public List<Account> findAccountsByBalanceGreaterThan(double amount, Pageable pageable) {
        return accountRepository.findByBalanceGreaterThan(amount, pageable);
    }
}