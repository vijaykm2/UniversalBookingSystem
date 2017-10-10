package com.ubs.main;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.ubs.dto.AccountDTO;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/account")
public class AccountController {

    @RequestMapping(method = RequestMethod.OPTIONS)
    public void manageOptions(HttpServletResponse response)
    {
        //do things
    }
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public AccountDTO createAccount(@RequestBody AccountDTO accountDTO){
        System.out.println(accountDTO.toString());
        return accountDTO;
    }
    @RequestMapping("/get" )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public AccountDTO getAccount(@RequestParam("id") Long id) {
        AccountDTO accountDTO = new AccountDTO(id,
                "vijay",
                "km",
                "vijay38576@outlook.com",
                null,
                null);
        return accountDTO;
    }


}
