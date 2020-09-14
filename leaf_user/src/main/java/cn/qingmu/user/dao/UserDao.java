package cn.qingmu.user.dao;

import cn.qingmu.user.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Zongmin
 * @date 2020-9-13 11:18:12
 * 用户表持久层接口
 */
@Repository
@Mapper
public interface UserDao {
    /**
     * 通过手机号查询用户
     * @param mobile 待查询的手机号
     * @return 查询到的用户
     */
    @Select("Select * from user where mobile = #{mobile}")
    User findByMobile(String mobile);

    /**
     * 插入一个用户
     * @param user 待插入的用户
     * @return 影响的行数>0表示插入成功
     */
    @Insert("INSERT INTO user(`id`, `mobile`, `password`, `nickname`, " +
            "`sex`, `birthday`, `avatar`, `email`, " +
            "`regdate`, `updatedate`, `lastdate`, `online`, " +
            "`interest`, `personality`, `fanscount`, `followcount`) " +
            "VALUES (#{id}, #{mobile}, #{password}, #{nickname}, " +
            "#{sex}, #{birthday}, #{avatar}, #{email}, " +
            "#{regdate} , #{updatedate}, #{lastdate}, #{online}, " +
            "#{interest}, #{personality}, #{fanscount}, #{followcount})")
    Integer save(User user);

    /**
     * 通过id查询用户
     * @param id 待查询的id
     * @return 查询到的用户
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(String id);

    /**
     * 查询用户表的总记录数
     * @return Total 总记录数
     */
    @Select("SELECT COUNT(*) FROM user")
    Long getTotal();

    /**
     * 查询用户表所有用户
     * @return 用户列表
     */
    @Select("SELECT * FROM user")
    List<User> findAll();

    /**
     * 条件在UserDao.xml中配置
     * @param searchMap 查询条件Map
     * @return 查询到的用户对象列表
     */
    List<User> findBySearchMap(Map searchMap);

    /**
     * 通过id删除用户，危险操作
     * @param id 待删除用户的id
     * @return 影响行数>0表示删除成功
     */
    @Delete("DELETE FROM user WHERE id = #{id}")
    Integer deleteById(String id);
}
