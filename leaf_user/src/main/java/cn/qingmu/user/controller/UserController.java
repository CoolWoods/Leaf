package cn.qingmu.user.controller;

import cn.qingmu.entity.Result;
import cn.qingmu.user.pojo.User;
import cn.qingmu.user.service.UserService;
import cn.qingmu.util.JwtUtil;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zm
 * @date 2020-9-3 10:19:5
 * CrossOrigin注解用来支持跨域
 */

@CrossOrigin
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private HttpServletRequest request;

    /**
     * ResponseBody用来返回Json格式字符串
     * RequestBody注解要接收的是Json字符串格式的数据，而不是表单默认提交的application/x-www-form-urlencoded
     * @return Result
     * @param user
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(User user){
        user = userService.login(user.getMobile(), user.getPassword());
        if (user == null){
            return new Result(false, StatusCode.LOGINERROR, "登录失败");
        }
        String token = jwtUtil.createJWT(user.getMobile(), user.getMobile(), "user");
        Map<String, Object> reMap = new HashMap<String, Object>();
        reMap.put("token", token);
        reMap.put("roles", "user");
        return new Result(true, StatusCode.OK, "登录成功", reMap);
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public Result test(){
        Claims claims = jwtUtil.parseJWT((String) request.getAttribute("claims_user"));
        if(claims == null || "".equals(claims)) {
            //说明当前用户没有user角色
            return new Result(false,StatusCode.LOGINERROR,"权限不足，请登录！");
        }
        //得到当前登录的用户Id
        String userid = claims.getId();
        Map<String, Object> reMap = new HashMap<String, Object>();
        reMap.put("userid", userid);
        reMap.put("roles", "user");
        return new Result(true, StatusCode.OK, "success", reMap);
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    @ResponseBody
    public Result test2(){
        return new Result(true, StatusCode.OK, "success");
    }
}
