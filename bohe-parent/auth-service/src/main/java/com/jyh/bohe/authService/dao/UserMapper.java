package com.jyh.bohe.authService.dao;


import com.jyh.bohe.authService.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    User get(@Param(value = "username") String username);
}
