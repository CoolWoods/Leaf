package cn.qingmu.user.controller;
import cn.qingmu.entity.Result;
import cn.qingmu.entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Zongmin
 * @date 2020-9-13 20:38:31
 * 统一异常处理类
 * 统一处理出现异常时，向客户端的返回结果，避免用户直接看到异常信息
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();        
        return new Result(false, StatusCode.ERROR, "执行出错");
    }
}
