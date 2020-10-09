package cn.qingmu.blog.dao;

import cn.qingmu.blog.pojo.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Zongmin
 * @date 2020-9-15 10:43:15
 * Article持久层接口
 */
@Repository
@Mapper
public interface ArticleDao {

    /**
     * 插入一篇文章
     * @param article 待插入的article
     * @return 是否插入成功
     */
    @Insert("INSERT INTO article(`id`, `columnId`, `userId`, `channelId`, " +
            "`title`, `content`, `image`, `createTime`, " +
            "`updateTime`, `isPublic`, `isTop`, `visits`, " +
            "`thumbUp`, `thumbDown`, `comment`, `state`, " +
            "`url`, `type`) " +
            "VALUES ( #{id}, #{columnId}, #{userId}, #{channelId}, " +
            "#{title}, #{content}, #{image}, #{createTime}, " +
            "#{updateTime}, #{isPublic}, #{isTop}, #{visits}, " +
            "#{thumbUp}, #{thumbDown}, #{comment}, #{state}, " +
            "#{url}, #{type} )")
    public Integer save(Article article);

    /**
     * 批量插入
     * @param articleList
     * @return
     */

    public Integer saveList(List<Article> articleList);
    /**
     * 不对外提供
     * 查询所有的文章
     * @return 文章列表
     */
    @Select("SELECT * FROM article")
    List<Article> findAll();

    @Select("SELECT id FROM article LIMIT #{start}, #{end}")
    public String[] findLimitIds(Integer start, Integer end);

    /**
     * 根据id查询文章
     * @param id 待查询文章的id
     * @return id对应的文章
     */
    @Select("SELECT * FROM article WHERE id = #{id}")
    public Article findById(String id);

    /**
     * 条件查询文章
     * @param  title 查询条件
     * @return 查找到的文章列表
     */
    public List<Article> findByTitle(String title);

    /**
     * 查找某个用户的文章
     * @param uid
     * @return
     */
    @Select("SELECT * FROM article WHERE userId = #{uid}")
    public List<Article> findArticleUid(String uid);

    /**
     * 查找某个频道的文章
     * @param channelId
     * @return
     */
    @Select("SELECT * FROM article WHERE channelId = #{channelId}")
    public List<Article> findByChannel(String channelId);

    /**
     * 查找某个专栏的文章
     * @param columnId
     * @return
     */
    @Select("SELECT * FROM article WHERE columnId = #{columnId}")
    public List<Article> findByColumn(String columnId);

    /**
     * 联合条件查询，条件在ArticleDao.xml中配置
     * @param searchMap 待查询的条件
     * @return 查询到的文章列表
     */
    public List<Article> findBySearchMap(Map searchMap);

    /**
     * 更新文件审核状态
     * @param state
     * @param id
     */
    @Update("update article SET state = #{state} WHERE id = #{id}")
    public void updateState(Integer state, String id);

    /**
     * 更新文章是否公开的状态
     * @param isPublic
     * @param id
     */
    @Update("update article SET isPublic = #{isPublic} WHERE id = #{id}")
    public void updateIsPublic(Integer isPublic, String id);

    /**
     * 点赞+1
     * @param id
     */
    @Update("update article SET thumbUp = thumbUp + 1 WHERE id = #{id}")
    public void updateThumbUp(String id);

    /**
     * 点踩+1
     * @param id
     */
    @Update("update article SET thumbDown = thumbDown + 1 WHERE id = #{id}")
    public void updateThumbDown(String id);

    /**
     * 删除一篇文章
     * @param id
     */
    @Delete("DELETE FROM article WHERE id = #{id}")
    public void deleteById(String id);

    /**
     * 批量删除篇文章
     * @param ids
     */
    public void deleteByIds(String[] ids);
}
