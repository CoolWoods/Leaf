package cn.qingmu.user.service;

import cn.qingmu.entity.Result;
import cn.qingmu.user.pojo.User;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author Zongmin
 * @date 2020-9-13 11:19:7
 * 用户业务层接口
 */
public interface UserService {

    /**
     * 手机号/密码登录接口的实现
     * @param mobile 手机号
     * @param password  用户输入的密码
     * @return User完整的用户信息
     */
    public User login(String mobile, String password);


    /**
     * 向用户发送验证码（仅实现发送消息）
     * 真正实现需要注册第三方短信平台
     * @param mobile 输入的手机号
     */
    void sendSms(String mobile);

    /**
     * 注册
     * @param user 待注册的用户对象
     * @return Result对象
     */
    Result register(User user);

    /**
     * id查询用户接口
     * @param id 用户的id
     * @return User 一个用户
     */
    User findById(String id);

    /**
     * 查询所有用户
     * @return List<User>
     */
    List<User> findAll();

    /**
     * 添加用户
     * @param user 使用者提交的用户
     * @return flag 添加成功返回true，否则返回false
     */
    boolean add(User user);

    /**
     * 修改用户
     * @param user 待更新的用户对象
     * @return
     */
    boolean update(User user);

    /**
     * 删除用户，需要Admin权限
     * @param id 待删除用户的id
     * @return 是否删除成功
     */
    Boolean deleteById(String id);

    /**
     * 分页查询所有用户
     * @param pageNum 当前页面
     * @param pageSize 页面的大小
     * @return PageInfo<User> 包含当前页面的用户列表以及其它信息
     */
    PageInfo<User> findAllUserByPage(int pageNum, int pageSize);

    /**
     * 分页条件查询用户
     * @param searchMap 查询条件的Map集合
     * @param pageNum  当前页面
     * @param pageSize  页面的大小
     * @return PageInfo<User> 包含当前页面的用户列表以及其它信息
     */
    PageInfo<User> findUserBySearchMap(Map searchMap, int pageNum, int pageSize);
}
