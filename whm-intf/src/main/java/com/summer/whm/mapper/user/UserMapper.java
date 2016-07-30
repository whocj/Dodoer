package com.summer.whm.mapper.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.user.User;
import com.summer.whm.mapper.BaseMapper;

public interface UserMapper extends BaseMapper {
    User loadByName(@Param("username") String username);
    
    User loadByNameAndPass(@Param("username") String username, @Param("password") String password);
    
    User loadByAuthTypeAndKey(@Param("thirdAuthType") String thirdAuthType, @Param("thirdAuthKey") String thirdAuthKey);

    User loadByAuthTypeAndToken(@Param("thirdAuthType") String thirdAuthType, @Param("thirdAuthToken") String thirdAuthToken);
    
    List<User> queryBlogByTopNum(@Param("num") Integer num);
}
