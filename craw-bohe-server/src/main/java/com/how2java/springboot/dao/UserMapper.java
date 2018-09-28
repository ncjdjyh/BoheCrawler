package com.cun.security3.mapper;


import com.cun.security3.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert(" insert into user ( name ) values (#{name}) ")
    public int add(User user);

    @Delete(" delete from user where id= #{id} ")
    public void delete(int id);

    @Select("select * from user where id= #{id} ")
    public User get(int id);

    @Update("update user set name=#{name} where id=#{id} ")
    public int update(User user);

    @Select(" select * from user ")
    public List<User> list();
}
