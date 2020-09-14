package cn.qingmu.test;

import cn.qingmu.UserApplication;
import cn.qingmu.user.dao.UserDao;
import cn.qingmu.user.pojo.User;
import cn.qingmu.user.service.UserService;
import cn.qingmu.util.IdWorker;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

//@RunWith(SpringRunner.class) 最新的springboot中取消了该注解
@SpringBootTest(classes = UserApplication.class)
public class TestUser {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private IdWorker idWorker;
    @Test
    public void test(){
        for (int i = 0; i < 100; i++) {
            System.out.println(idWorker.nextId());
        }
    }

    @Test
    public void testSendSms(){
        userService.sendSms("13877089820");
    }

    @Test
    void testFindById(){
        // User byId = userDao.findById("1304288154651594752");
         User byId = userService.findById("1304288154651594752");
        if (byId == null){
            System.out.println("查询的用户不存在");
        } else {
            System.out.println(byId);
        }
    }


    @Test
    void testFindAllUserByPageS(){
        int pageNum = 1;
        int pageSize = 5;
        PageInfo<User> allUserByPageS = userService.findAllUserByPage(pageNum, pageSize);
        int i = 0;
        System.out.println(allUserByPageS);
    }

    @Test
    void testFindSearchBySearchMap(){
        Map<String, String> map = new HashMap<String, String>();
        int pageNum = 1;
        int pageSize = 5;
        map.put("nickname", "三");
        PageInfo<User> searchBySearchMap = userService.findUserBySearchMap(map, pageNum, pageSize);
        System.out.println(searchBySearchMap);
    }
}
