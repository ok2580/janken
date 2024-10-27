package oit.is.z2303.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
  @Insert("INSERT INTO users (userName) VALUES (#{userName});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertUser(User users);

  @Select("SELECT * from users where id = #{userId}")
  User selectAllById(int userId);

  @Select("SELECT * from users where userName = #{userName}")
  User selectAllByuserName(String userName);


  @Select("SELECT * from users")
  ArrayList<User> selectAll();
}
