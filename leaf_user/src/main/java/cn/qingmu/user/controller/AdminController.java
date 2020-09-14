package cn.qingmu.user.controller;

import cn.qingmu.entity.Result;
import cn.qingmu.entity.StatusCode;
import cn.qingmu.user.pojo.Admin;
import cn.qingmu.user.service.AdminService;
import cn.qingmu.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zongmin
 * @date 2020-9-13 21:0:33
 * admin控制器层
 * CrossOrigin注解用于解决跨域问题
 * RestController 在Controller的基础上进行了增强
 */

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
    private JwtUtil jwtUtil;

	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public Result login(@RequestBody Admin admin) {
        Admin loginAdmin = adminService.login(admin);
        if(loginAdmin == null) {
            return new Result(false, StatusCode.LOGINERROR,"登录失败");
        }
        //生成Jwt令牌
        String token = jwtUtil.createJWT(loginAdmin.getId(), loginAdmin.getLoginName(), "admin");
        Map<String,Object> map = new HashMap<>();
        map.put("token", token);
        map.put("role", "admin");
        return new Result(true, StatusCode.OK, "登录成功", map);
    }
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",adminService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",adminService.findById(id));
	}

	/**
	 * 分页+条件查询
	 * @param searchMap 查询条件的Map
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页条件查询结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method= RequestMethod.POST)
	public Result findBySearchMap(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		return new Result(true,StatusCode.OK,"查询成功", adminService.findAdminBySearchMap(searchMap, page, size));
	}
	
	/**
	 * 增加一个管理员，暂不对外开放
	 * @param admin 待添加的admin
	 */
	@RequestMapping(method= RequestMethod.POST)
	public Result add(@RequestBody Admin admin  ){
		adminService.add(admin);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param admin 待修改的admin
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Admin admin, @PathVariable String id ){
		admin.setId(id);
		Boolean update = adminService.update(admin);
		if (!update) {
			return new Result(true,StatusCode.ERROR,"修改失败");
		}
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id 待删除admin的id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		Boolean deleteById = adminService.deleteById(id);
		if (!deleteById){
			return new Result(true,StatusCode.ERROR,"删除失败");
		}
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
