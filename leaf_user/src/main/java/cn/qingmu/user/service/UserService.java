package cn.qingmu.user.service;

import cn.qingmu.user.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    public User login(String mobile, String password) {
        User user = new User();
        user.setMobile("13877089820");
        user.setId("123456");
        user.setNickname("张三");
        return user;
    }
}
