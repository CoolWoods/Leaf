package cn.qingmu.blog.dao;

import cn.qingmu.blog.pojo.Channel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Zongmin
 * @date 2020-9-15 10:43:56
 * Article持久层接口
 */
public interface ChannelDao {
    /**
     * 查询所有
     * @return
     */
    @Select("SELECT * FROM channel")
    public List<Channel> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Select("SELECT * FROM channel LIMIT 1")
    public Channel findById(String id);

    /**
     * 根据ID删除
     * @param id
     */
    @Delete("DELETE FROM channel WHERE id = #{id}")
    public void deleteById(String id);

    /**
     * 更新状态
     * @param state
     * @param id
     */
    @Update("UPDATE channel SET state = #{state} WHERE id = #{id}")
    public void updateState(Integer state, String id);

    /**
     * 修改Channel名字
     * @param name
     * @param id
     */
    @Update("UPDATE channel SET name = #{name} WHERE id = #{id}")
    public void updateName(String name, String id);

    /**
     * 插入一个channel
     * @param channel 待插入的频道
     */
    @Insert("INSERT INTO `channel`(`id`, `name`, `state`) VALUES (#{id}, #{name}, #{sate})")
    public void saveChannel(Channel channel);
}
