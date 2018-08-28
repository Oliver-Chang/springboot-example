package com.oliver.ElectronicCommerce.authorization;


import com.oliver.ElectronicCommerce.common.result.Result;
import com.oliver.ElectronicCommerce.common.result.ResultCode;
import com.oliver.ElectronicCommerce.user.User;
import com.oliver.ElectronicCommerce.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthorizationService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public Result authorization(User user) {
        if (user == null) {
            return Result.failure(ResultCode.PARAM_IS_BLANK);
        }
        String username = user.getUsername();
        User findedUser = repository.findUserByUsernameOrTelephoneOrMail(username, username, username);
        if (findedUser == null) {
            return Result.failure(ResultCode.USER_NOT_EXIST);
        }
        if (!findedUser.getPassword().equals(user.getPassword())) {
            return Result.failure(ResultCode.USER_LOGIN_ERROR);
        }
        Map<String, Object> map = new HashMap<>();
        String token = jwtTokenUtil.createToken(findedUser);
        map.put("token", token);
        map.put("user", findedUser);
        System.out.println(findedUser.getOrders().toString());
        return Result.success(map);
    }


}
