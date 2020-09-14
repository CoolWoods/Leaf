package cn.qingmu.user.dao;

import cn.qingmu.user.pojo.Admin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Zongmin
 * @date 2020-9-13 20:45:3
 * admin持久层接口
 */
@Repository
@Mapper
public interface AdminDao {
    /**
     * 插入一个admin
     * @param admin 待插入的admin
     * @return 影响的行数
     */
    @Insert("INSERT INTO admin(`id`, `loginName`, `password`, `state`) " +
            "VALUES (#{id}, #{loginName}, #{password}, #{state})")
    Integer save(Admin admin);

    /**
     * 根据id删除admin
     * @param id 待删除admin的id
     * @return 影响的行数
     */
    @Delete("DELETE FROM　admin WHERE id = #{id}")
    Integer deleteById(String id);

    /**
     * 根据searchMap查询admin，条件在AdminDao.xml中配置
     * @param searchMap 查找条件的map
     * @return 查询到的admin对象列表
     */
    List<Admin> findBySearchMap(Map searchMap);

    /**
     * 根据loginName查找admin
     * @param loginName 待查找admin的登录名
     * @return 查找到的对象
     */
    @Select("SELECT * FROM admin WHERE loginName = #{loginName}")
    Admin findByLoginName(String loginName);

    /**
     *
     * @return
     */
    @Select("SELECT * FROM admin")
    List<Admin> findAll();

    /**
     *
     * @return
     */
    @Select("SELECT * FROM admin WHERE id = #{id}")
    Admin findById();
}
