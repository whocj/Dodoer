package com.summer.whm.service.user;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.common.utils.MD5;
import com.summer.whm.entiry.user.User;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.user.UserMapper;
import com.summer.whm.service.BaseService;

@Service
public class UserService extends BaseService {

    @Autowired
    private UserMapper userMapper;

    public User saveOrUpdate(User user) {
        if (user.isNew()) {
            int id = userMapper.insert(user);
            user.setId(id);
        } else {
            userMapper.update(user);
        }

        return user;
    }

    public PageModel<User> list(int pageIndex, int pageSize) {
        PageModel<User> page = new PageModel<User>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

    public List<User> queryBlogByTopNum(int num) {
        return userMapper.queryBlogByTopNum(num);
    }

    public User login(String username, String password) {
        String p = MD5.encode(password);
        User u = userMapper.loadByNameAndPass(username, p);
        if (u != null) {
            u.setPassword(null);
        }

        return u;
    }

    public User loadByAuthTypeAndKey(String thirdAuthType, String thirdAuthKey) {
        // String p = MD5.encode(thirdAuthKey);
        User u = userMapper.loadByAuthTypeAndKey(thirdAuthType, thirdAuthKey);
        if (u != null) {
            u.setPassword(null);
        }
        return u;
    }

    public User loadByAuthTypeAndToken(String thirdAuthType, String thirdAuthToken) {
        // String p = MD5.encode(thirdAuthKey);
        User u = userMapper.loadByAuthTypeAndToken(thirdAuthType, thirdAuthToken);
        if (u != null) {
            u.setPassword(null);
        }
        return u;
    }

    public String randomUsername(String str) {
        String username = str;
        int count = 20;
        String temp = str;
        while (count-- > 0) {
            if (loadByName(temp) == null) {
                return temp;
            }
            String uid = UUID.randomUUID().toString();

            temp = username + uid.substring(0, 4);
        }

        return username + System.currentTimeMillis();
    }

    public User loadByName(String username) {
        User u = userMapper.loadByName(username);
        if (u != null) {
            u.setPassword(null);
        }
        return u;
    }

    @Override
    protected BaseMapper getMapper() {
        return userMapper;
    }

}
