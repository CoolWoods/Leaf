package cn.qingmu.user.service;

import cn.qingmu.user.pojo.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author Zongmin
 * @datte 2020-9-13 20:44:2
 * admin业务层接口
 */
public interface AdminService {

    /**
     * 管理员登录
     * @param admin 待登录的管理员对象
     * @return 登录后的管理员对象
     */
    Admin login(Admin admin);

    /**
     * 查找所有管理员
     * @return
     */
    List<Admin> findAll();

    /**
     * 根据id查找管理员
     * @param id 待查找的管理员的id
     * @return 查找到的管理员
     */
    Admin findById(String id);

    /**
     * 添加一个管理员
     * @param admin 待添加的admin
     * @return 是否添加成功
     */
    Boolean add(Admin admin);

    /**
     * 更新一个管理员信息
     * @param admin 待更新的管理员对象
     * @return 是否更新成功
     */
    Boolean update(Admin admin);

    /**
     * 删除一个管理员
     * @param id 待删除的管理员的id
     * @return 是否删除成功
     */
    Boolean deleteById(String id);

    /**
     * 分页+条件查询管理员
     * @param searchMap 查找的条件
     * @param page 当前页面
     * @param size 页面大小
     * @return PageInfo<Admin>
     */
    PageInfo<Admin> findAdminBySearchMap(Map searchMap, int page, int size);
}
