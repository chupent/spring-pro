package com.cp.shanghai.dao;

import com.cp.shanghai.model.UserModel;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    Integer updateToken(@Param("account") String account,
                        @Param("password") String password,
                        @Param("token") String token);

    UserModel queryUserInfo(@Param("token") String token);
}
