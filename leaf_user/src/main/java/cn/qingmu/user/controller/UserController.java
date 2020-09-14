package cn.qingmu.user.controller;

import cn.qingmu.entity.Result;
import cn.qingmu.entity.StatusCode;
import cn.qingmu.user.pojo.User;
import cn.qingmu.user.service.UserService;
import cn.qingmu.util.JwtUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zongmin
 * @date 2020-9-3 10:19:5
 * CrossOrigin注解用来支持跨域
 */

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    /**
     * 业务层接口对象
     */
    @Autowired
    private UserService userService;

    /**
     * 封装的Jwt工具类
     */
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Redis操作工具类
     */
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * user登录功能
     * ResponseBody用来返回Json格式字符串
     * RequestBody注解要接收的是Json字符串格式的数据，而不是表单默认提交的application/x-www-form-urlencoded
     * @return Result
     * @param user
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody User user){
        User loginUser = userService.login(user.getMobile(), user.getPassword());
        if (loginUser == null){
            return new Result(false, StatusCode.LOGINERROR, "登录失败");
        }
        //生成Jwt令牌
        String token = jwtUtil.createJWT(loginUser.getMobile(), loginUser.getMobile(), "user");
        Map<String, Object> reMap = new HashMap<String, Object>();
        reMap.put("token", token);
        reMap.put("roles", "user");
        return new Result(true, StatusCode.OK, "登录成功", reMap);
    }


    /**
     * 注册
     * code是用户通过发送验证码得到的6位数的代码
     * @param code
     * @param user
     * @return
     */
    @RequestMapping(value = "/register/{code}", method = RequestMethod.POST)
    @ResponseBody
    public Result register(@PathVariable("code") String code, @RequestBody User user) {
        //得到缓存中的验证码
        String checkCodeRedis = (String) redisTemplate.opsForValue().get("checkCode_" + user.getMobile());
        if(checkCodeRedis.isEmpty()) {
            return new Result(false,StatusCode.ERROR,"请先获取手机验证码");
        }
        if(!checkCodeRedis.equals(code)) {
            return new Result(false,StatusCode.ERROR,"请输出正确的验证码");
        }
        System.out.println("register execute");
        return userService.register(user);
    }


    /**
     * 发送短信验证码
     */
    @RequestMapping(value = "/sendSms/{mobile}", method = RequestMethod.POST)
    @ResponseBody
    public Result sendSms(@PathVariable String mobile) {
        userService.sendSms(mobile);
        return new Result(true,StatusCode.OK,"发送成功");
    }

    /**
     * 查询全部数据
     * @return
     */
    @RequestMapping(method= RequestMethod.GET)
    public Result findAll(){
        return new Result(true,StatusCode.OK,"查询成功",userService.findAll());
    }

    /**
     * 根据ID查询
     * 如果找到了返回一个user对象
     * 如果没有找到则返回的data为null
     * @param id ID
     * @return
     */
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable("id") String id){
        return new Result(true,StatusCode.OK,"查询成功",userService.findById(id));
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @RequestMapping(value="/search/{page}/{size}", method=RequestMethod.GET)
    public Result findBySearchMap(@PathVariable int page, @PathVariable int size){
        return new Result(true, StatusCode.OK, "查询成功", userService.findAllUserByPage(page, size));
    }

    /**
     * 条件+分页查询
     * @param searchMap 查询条件的Map
     * @param page 页码
     * @param size 页大小
     * @return 分页条件查询结果
     */
    @RequestMapping(value="/search/{page}/{size}", method = RequestMethod.POST)
    public Result findBySearchMap(@RequestBody Map searchMap, @PathVariable Integer page, @PathVariable Integer size){
        return new Result(true,StatusCode.OK,"查询成功",userService.findUserBySearchMap(searchMap, page, size));
    }

    /**
     * 增加
     * @param user 待添加的用户
     */
    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody User user){
        userService.add(user);
        return new Result(true, StatusCode.OK,"增加成功");
    }

    /**
     * 修改
     * @param user 待更新的用户
     * @param id 待更新用户的id
     */
    @RequestMapping(value="/{id}",method= RequestMethod.PUT)
    public Result update(@RequestBody User user, @PathVariable String id ){
        user.setId(id);
        userService.update(user);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 删除  必须有admin角色才能删除
     * @param id 待删除用户的id
     */
    @RequestMapping(value="/{id}",method= RequestMethod.DELETE)
    public Result delete(@PathVariable String id ){
        userService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}
