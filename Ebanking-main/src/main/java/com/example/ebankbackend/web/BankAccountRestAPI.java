package com.example.ebankbackend.web;

import com.example.ebankbackend.dtos.AccountHistoryDTO;
import com.example.ebankbackend.dtos.AccountOperationDTO;
import com.example.ebankbackend.dtos.BankAccountDTO;
import com.example.ebankbackend.entities.AccountOperation;
import com.example.ebankbackend.exceptions.BankAccountNotFoundException;
import com.example.ebankbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class BankAccountRestAPI {
    private BankAccountService bankAccountService;

    public BankAccountRestAPI(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

//    @GetMapping("/accounts/{accountId}")
//    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
//        return bankAccountService.getBankAccount(accountId);
//    }
//    @GetMapping("/accounts")
//    public List<BankAccountDTO> listAccounts(){
//        return bankAccountService.bankAccountList();
//    }
    @GetMapping("/account/{accountId}")
    public BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }
    @GetMapping("/accounts")
    public List<BankAccountDTO> listAccounts(){
        return bankAccountService.bankAccountList();
    }

    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(String accountId){
        return bankAccountService.accountHistory(accountId);
    }
    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(
            @PathVariable String accountId,
            @RequestParam(name="page",defaultValue = "0") int page,
            @RequestParam(name="size",defaultValue = "5") int size) throws BankAccountNotFoundException {
        return bankAccountService.getAccountHistory(accountId,page,size);
    }
}

