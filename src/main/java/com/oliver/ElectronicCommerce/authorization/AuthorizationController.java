package com.oliver.ElectronicCommerce.authorization;

import com.oliver.ElectronicCommerce.common.result.Result;
import com.oliver.ElectronicCommerce.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authorization")
public class AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping("")
    public Result authorization(@RequestBody User user) {
        return authorizationService.authorization(user);
    }
}
