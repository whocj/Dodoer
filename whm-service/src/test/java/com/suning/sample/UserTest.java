///**
// * SUNING APPLIANCE CHAINS.
// * Copyright (c) 2012-2012 All Rights Reserved.
// */
//package com.suning.sample;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.testng.annotations.Test;
//
//import com.suning.framework.page.QueryParam;
//import com.suning.framework.page.QueryResult;
//import com.suning.sample.user.User;
//import com.suning.sample.user.UserInfo;
//import com.suning.sample.user.UserQueryParam;
//import com.suning.sample.user.UserService;
//
///**
// * 
// * 用户CRUD操作testNG测试类，测试业务逻辑各方法。
// * @author 作者 Yangdc@cnsuning.com
// * @version 1.0.0
// */
//public class UserTest extends AppBaseTest {
//    /**
//     * DAL组件对象
//     */
//    @Autowired
//    UserService userService;
//
//    @Test
//    public void insertUser() {
//        // 创建用户对象
//        User user = new User();
//        user.setManager(true);
//        user.setCode("code1");
//        user.setMobileNo("13333333333");
//        user.setJoinDate(new Date());
//        user.setName("name1");
//        user.setTitle("title1");
//        // 向DB中插入该用户对象
//        userService.saveUser(user);
//        
//        // 取得刚才插入的用户对象
//        User resultUser = userService.getUser(user.getCode());
//        
//        // 判断插入对象的值正确无误
//        if (resultUser != null) {
//            assert (user != null);
//            assert (resultUser.getCode().equals(user.getCode()));
//            assert (resultUser.getName().equals(user.getName()));
//            assert (resultUser.getTitle()== null);
//            assert(resultUser.getGender());
//        }
//    }
//
//    @Test(dependsOnMethods="insertUser")
//    public void updateUser() {
//        // 设置需要修改的用户对象的数据
////        User user = new User();
////        user.setManager(true);
////        user.setCode("code1");
////        user.setMobileNo("13333333333");
////        user.setJoinDate(new Date());
////        
////        // 更新该用户对象
////        userService.saveUser(user);
//
//        // 取得更新后的用户对象
//        String ucode = "code1";
//        User resultUser = userService.getUser(ucode);
//        
//        // 判断更新的数据正确无误
//        if (resultUser != null) {
//            assert (ucode.equals(resultUser.getCode()));
//            assert (resultUser.getMobileNo() != null && resultUser.getMobileNo().equals("13333333333"));
//        }
//    }
//
////    @Test
////    public void errorUpdate() {
////        User user = new User();
////        user.setManager(true);
////        user.setCode("code1");
////        user.setName("name2");
////        user.setTitle("title2");
////        user.setLevel(999888);
////        user.setGender(true);
////
////        try {
////            userService.saveUser(user);
////        } catch (RuntimeException ex) {
////            throw ex;
////        }
////    }
//    
////    @Test(dependsOnMethods="errorUpdate")
////    public void validateErrorUpdate() {
////        User user = new User();
////        user.setManager(true);
////        user.setCode("code1");
////        user.setName("name2");
////        user.setTitle("title2");
////        user.setLevel(999888);
////        user.setGender(true);
////        
////        User resultUser = userService.getUser(user.getCode());
////        if (resultUser != null) {
////            assert (user != null);
////            assert (resultUser.getCode().equals(user.getCode()));
////            assert (!resultUser.getName().equals(user.getName()));
////            assert (!resultUser.getGender() == user.getGender());
////            assert (!resultUser.getTitle().equals(user.getTitle()));
////        }
////    }
//    
//    @Test(dependsOnMethods="updateUser")
//    public void deleteUser() {
//        // 设置需要删除的用户的code
//        User user = new User();
//        user.setCode("code1");
//        
//        // 删除该用户
//        userService.saveUser(user);
//
//        // 判断该用户在DB中已经被删除
//        User resultUser = userService.getUser(user.getCode());
//        assert(resultUser.getCode() == null);
//    }
//
//    @Test
//    public void searchUser() {
//        // 检索用户
//        UserQueryParam param = new UserQueryParam();
//        QueryResult<UserInfo> userInfo = userService.searchUser(param);
//        
//        // 判断用户列表不为空
//        assert (userInfo != null);
//        assert (userInfo.getDatas() != null);
//        assert (userInfo.getDatas().size() > 0);
//    }
//
//    @Test
//    public void getUser() {
//        // 根据code取得用户
//        String code = "11111111";
//        User user = userService.getUser(code);
//        
//        // 判断取得的用户的code正确无误
//        if (user != null) {
//            assert (user != null);
//            assert (code.equals(user.getCode()));
//        }
//    }
//    
//    @Test
//    public void pageQuery() {
//        QueryParam<Map<String,Object>> param = new QueryParam<Map<String,Object>>();
//        param.setPageNumber(7);
//        param.setPageSize(5);
//        //查询参数
//        
//        HashMap<String,Object> map =  new HashMap<String,Object>();
//        map.put("code", "999999999");
//        param.setQueryParam(map);
//      //  param.getQueryParam().put("code", "999999999");
//        
//        QueryResult<UserInfo> result = userService.pageQuery(param);
//        System.out.println("pageNumber:\t" + result.getPageNumber());
//        System.out.println("pageSize:\t" + result.getPageSize());
//        System.out.println("pageCount:\t" + result.getPageCount());
//        System.out.println("totalDataCount:\t" + result.getTotalDataCount());
//        System.out.println("isLastPage:\t" + result.getIsLastPage());
//        System.out.println("indexNumber:\t" + result.getIndexNumber());
//        
//        for(UserInfo info : result.getDatas()){
//            System.out.println(info.getName());
//        }
//        
//    }
//}
