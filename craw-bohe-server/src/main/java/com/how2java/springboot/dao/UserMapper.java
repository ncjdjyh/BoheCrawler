package com.how2java.springboot.dao;

import com.how2java.springboot.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
   /* @Insert(" insert into user ( name ) values (#{name}) ")
    public int add(User user);

    @Delete(" delete from user where id= #{id} ")
    public void delete(int id);

    @Select("select * from user where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "favoriteItems", javaType = List.class, column = "id",
                    many = @Many(select = "com.cun.security3.mapper.FavoriteItemMapper.get"))
    })
    public User get(int id);

    @Select("select * from user where username = #{username}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "favoriteItems", javaType = List.class, column = "id",
                    many = @Many(select = "com.cun.security3.mapper.FavoriteItemMapper.get"))
    })
    public User get(String username);

    @Update("update user set name=#{name} where id=#{id} ")
    public int update(User user);

    @Select("select * from user ")
    public List<User> list();

    @Select("select password from user where username=#{name}")
    public String getPassword(String name);

    @Select("select * from food_ where id in (SELECT fid from favorite_item where uid = #{id}) ")
    public List<Food> favoriteList(User user);

    @Insert("insert into favorite_item ( uid, fid) values (  #{id}, #{fid} ) ")
    public void insert(User user, @Param("fid") int fid);*/

    List<User> getAll();

    User getOne(Long id);

    void insert(User user);

    void update(User user);

    void delete(int id);
}
