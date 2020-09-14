package cn.qingmu.user.service.impl;

import cn.qingmu.entity.Result;
import cn.qingmu.entity.StatusCode;
import cn.qingmu.user.dao.UserDao;
import cn.qingmu.user.pojo.User;
import cn.qingmu.user.service.UserService;
import cn.qingmu.util.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Zongmin
 * @date 2020-9-13 11:19:26
 * 用户业务层实现
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    /**
     * userDao对象，用户操作数据库
     */
    @Autowired
    private UserDao userDao;

    /**
     * SpringSecurity提供的密码加密工具
     */
    @Autowired
    private BCryptPasswordEncoder encoder;

    /**
     * 生成唯一id的工具类
     */
    @Autowired
    private IdWorker idWorker;

    /**
     * RabbitMQ操作工具类
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * Redis操作工具类
     */
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * HttpServletRequest请求对象
     */
    @Autowired
    private HttpServletRequest request;

    /**
     * 手机号/密码登录接口的实现
     * @param mobile 手机号
     * @param password  用户输入的密码
     * @return User完整的用户信息
     */
    public User login(String mobile, String password) {
        User user = userDao.findByMobile(mobile);
        if(user != null && encoder.matches(password,user.getPassword())) {
            return user;
        }
        return  null;
    }

    /**
     * 注册接口的实现
     * @param user 待注册的用户对象
     * @return Result对象
     */
    @Override
    public Result register(User user) {
        User byMobile = userDao.findByMobile(user.getMobile());
        if (byMobile != null) {
            return new Result(false, StatusCode.ERROR,"手机号已经被注册");
        }
        boolean add = this.add(user);
        if (!add){
            return new Result(true, StatusCode.OK,"注册失败，请重试");
        }
        return new Result(true, StatusCode.ERROR,"注册成功");
    }

    /**
     * id查询用户接口的实现
     * @param id 用户的id
     * @return User 一个用户
     */
    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    /**
     * 查询所有用户接口的实现
     * @return  List<User>
     */
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }


    /**
     * 添加一个用户
     * @param user 使用者提交的用户
     * @return flag 添加成功返回true，否则返回false
     */
    @Override
    public boolean add(User user) {
        user.setId( idWorker.nextId()+"" );
        //密码加密
        user.setPassword(encoder.encode(user.getPassword()));
        user.setFollowcount(0);//关注数
        user.setFanscount(0);//粉丝数
        user.setOnline(0L);//在线时长
        user.setRegdate(new Date());//注册日期
        user.setUpdatedate(new Date());//更新日期
        user.setLastdate(new Date());//最后登陆日期
        Integer add = userDao.save(user);
        return add > 0;
    }

    /**
     * 更新用户信息
     * @param user 待更新的用户对象
     * @return boolean 是否更新成功
     */
    @Override
    public boolean update(User user) {
        Integer update = userDao.save(user);
        return update > 0;
    }

    /**
     * 删除用户
     * @param id 待删除用户的id
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteById(String id) {
        String token = (String) request.getAttribute("claims_admin");
        if(token == null || "".equals(token)) {
            throw new RuntimeException("权限不足！");
        }
        Integer deleteById = userDao.deleteById(id);
        return deleteById > 0;
    }

    /**
     * 向用户发送验证码
     * @param mobile 待发送的手机号
     */
    @Override
    public void sendSms(String mobile) {
        //生成一个六位数随机数--使用了commons-lang3包
        String checkCode = RandomStringUtils.randomNumeric(6);
        //向缓存中放一份,有效期为30分钟
        redisTemplate.opsForValue().set("checkCode_" + mobile, checkCode,30, TimeUnit.MINUTES);
        Map<String,String> map = new HashMap<>();
        map.put("mobile",mobile);
        map.put("checkCode",checkCode);
        //给用户发一份
        rabbitTemplate.convertAndSend("sms",map);
        //在控制台显示一份[方便测试]
        System.out.println("验证码为: " + checkCode + " 有效期30分钟，请勿向他人泄露！");
    }

    /**
     * 分页查询所有用户， 使用pageHelp插件提供支持
     * @param pageNum 当前页面
     * @param pageSize 页面的大小
     * @return  PageInfo<User>
     */
    @Override
    public PageInfo<User> findAllUserByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> lists = userDao.findAll();
        return new PageInfo<User>(lists);
    }

    /**
     * 分页条件查询用户
     * @param searchMap 查询条件的Map集合
     * @param pageNum  当前页面
     * @param pageSize  页面的大小
     * @return PageInfo<User>
     */
    @Override
    public PageInfo<User> findUserBySearchMap(Map searchMap, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> lists = userDao.findBySearchMap(searchMap);
        return new PageInfo<User>(lists);
    }

}
