package cn.qingmu.user.service.impl;


import cn.qingmu.user.dao.AdminDao;
import cn.qingmu.user.pojo.Admin;
import cn.qingmu.user.service.AdminService;
import cn.qingmu.util.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    /**
     * 生成唯一id的工具类
     */
    @Autowired
    private IdWorker idWorker;

    /**
     * SpringSecurity提供的密码加密工具
     */
    @Autowired
    private BCryptPasswordEncoder encoder;

    /**
     * 管理员用户名和密码登录方法
     * @param admin 待登录的管理员对象
     * @return 登录后的管理员对象
     */
    @Override
    public Admin login(Admin admin) {
        //根据用户名查找admin对象
        Admin loginAdmin = adminDao.findByLoginName(admin.getLoginName());
        //将从数据查找对象的密码与用户输入的密码匹配，成功说明一致，允许登录
        if (loginAdmin !=null && encoder.matches(admin.getPassword(), loginAdmin.getPassword())){
            return loginAdmin;
        }
        return null;
    }

    /**
     * 查找所有管理员
     * @return 所有管理员列表
     */
    @Override
    public List<Admin> findAll() {
        return adminDao.findAll();
    }

    /**
     * 根据id查找管理员
     * @param id 待查找的管理员的id
     * @return 查询结果
     */
    @Override
    public Admin findById(String id) {
        return adminDao.findById();
    }

    /**
     * 添加一个管理员
     * @param admin 待添加的admin
     * @return 是否添加成功
     */
    @Override
    public Boolean add(Admin admin) {
        admin.setId(idWorker.nextId() + "");
        admin.setPassword(encoder.encode(admin.getPassword()));
        Integer add = adminDao.save(admin);
        return add > 0;
    }

    /**
     * 更新管理员信息
     * @param admin 待更新的管理员对象
     * @return 是否更新成功
     */
    @Override
    public Boolean update(Admin admin) {
        return adminDao.save(admin) > 0;
    }

    /**
     * 删除管理员
     * @param id 待删除的管理员的id
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteById(String id) {
        return adminDao.deleteById(id) > 0;
    }

    /**
     * 分页+条件查询管理员
     * @param searchMap 查找的条件
     * @param page 当前页面
     * @param size 页面大小
     * @return 分页查询结果
     */
    @Override
    public PageInfo<Admin> findAdminBySearchMap(Map searchMap, int page, int size) {
        PageHelper.startPage(page, size);
        List<Admin> lists = adminDao.findBySearchMap(searchMap);
        return new PageInfo<Admin>(lists);
    }
}
