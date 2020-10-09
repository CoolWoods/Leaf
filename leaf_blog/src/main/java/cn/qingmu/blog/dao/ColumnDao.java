package cn.qingmu.blog.dao;

import cn.qingmu.blog.pojo.Column;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Zongmin
 * @date 2020-9-15 10:44:2
 * Article持久层接口
 */
public interface ColumnDao {
    /**
     * 查询所有
     * @return
     */
    @Select("SELECT * FROM column")
    public List<Column> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Select("SELECT * FROM column LIMIT 1")
    public Column findById(String id);

    /**
     * 根据ID删除
     * @param id
     */
    @Delete("DELETE FROM column WHERE id = #{id}")
    public void deleteById(String id);

    /**
     * 更新状态
     * @param state
     * @param id
     */
    @Update("UPDATE column SET state = #{state} WHERE id = #{id}")
    public void updateState(Integer state, String id);

    /**
     * 修改Column名字
     * @param name
     * @param id
     */
    @Update("UPDATE column SET name = #{name} WHERE id = #{id}")
    public void updateName(String name, String id);


    /**
     * 插入一个column
     * @param column 待插入的频道
     */
    @Insert("INSERT INTO `column`(`id`, `name`, `userId`, `summary`, " +
            "`createTime`, `checkTime`, `updateTime`, `state`) " +
            "VALUES (#{id}, #{name}, #{userId}, #{summary}, " +
            "#{createTime}, #{checkTime}, #{updateTime}, #{state})")
    public void saveChannel(Column column);
}
