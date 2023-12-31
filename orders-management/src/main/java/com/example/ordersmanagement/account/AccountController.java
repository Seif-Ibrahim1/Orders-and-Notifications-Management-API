package com.example.ordersmanagement.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    AccountService AccountServiceInterface;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Account account) {

        Boolean isSignedUp = AccountServiceInterface.signUp(account);
        if (isSignedUp)
        {
            return ResponseEntity.ok("Signup successful!");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Inputs");

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        Account account = AccountServiceInterface.login(username, password);
        if (account != null) {
            return ResponseEntity.ok("Login successful!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
    }

    @GetMapping({"/customers"})
    public Account[] getAll() {
        return this.AccountServiceInterface.getAllAccounts();
    }

    @GetMapping("/customers/{id}")
    public Account getAccount(@PathVariable("id") int id) {
        System.out.println("in get with id:"+id);
        return this.AccountServiceInterface.getAccount(id);
    }
}
