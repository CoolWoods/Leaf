package cn.qingmu.blog.test;

import cn.qingmu.blog.BlogApplication;
import cn.qingmu.blog.dao.ArticleDao;
import cn.qingmu.blog.pojo.Article;
import cn.qingmu.util.IdWorker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = BlogApplication.class)
class TestBlog {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private IdWorker idWorker;
    @Test
    void saveTest(){
        long startTimeMillis = System.currentTimeMillis();

        int i = 0;
        for (; i < 1; i++) {
            save();
        }
        long endTimeMillis = System.currentTimeMillis();
        System.out.println("插入" + i*10000 + "共使用" + (endTimeMillis - startTimeMillis)/1000 + "s");
    }

    @Test
    void testArticleFindAll(){
        List<Article> allArticle = articleDao.findAll();
        int i = 0;
        for (Article article : allArticle) {
            System.out.println("第"+ ++i + "篇"+ article);
        }
    }

    @Test
    void testFindByTitle(){
        long startTimeMillis = System.currentTimeMillis();

        List<Article> byTitle = articleDao.findByTitle("lo");
        int i = 0;
        for (Article article : byTitle) {
            System.out.println("第"+ ++i + "篇"+ article);
        }

        long endTimeMillis = System.currentTimeMillis();

        System.out.println("查询" + i + "篇文章共使用" + (endTimeMillis - startTimeMillis)/1000/60.0 + "分钟");
    }

    void save(){


        int i = 0;
        List<Article> articleList = new ArrayList<Article>();
        for (; i < 10000; i++) {
            Article article = new Article();
            String articleId = idWorker.nextId() + "";

            article.setId(articleId);
            article.setUserId(idWorker.nextId() + "");
            article.setChannelId(idWorker.nextId() + "");
            article.setColumnId(idWorker.nextId() + "");
            article.setTitle("启动tomcat报错");
            article.setContent("/article/content_"+articleId);
            article.setImage("/article/image_"+articleId);
            article.setCreateTime(new Date());
            article.setUpdateTime(new Date());
            article.setIsPublic(0);
            article.setIsTop(0);
            article.setVisits(0);
            article.setThumbUp(0);
            article.setThumbDown(0);
            article.setComment(0);
            article.setState(0);
            article.setUrl("/article/" + articleId);
            article.setLabel(null);

            articleList.add(article);
        }

        articleDao.saveList(articleList);

    }

    @Test
    void deleteTest(){
        long startTimeMillis = System.currentTimeMillis();
        int i = 0;
        int length = 10000;
        for (; i < 600; i++) {
            String[] limitIds = articleDao.findLimitIds(0, length);
            articleDao.deleteByIds(limitIds);
            System.out.println(new Date());
        }
        long endTimeMillis = System.currentTimeMillis();
        System.out.println("删除" + i*length + "篇文章共使用" + (endTimeMillis - startTimeMillis)/1000/60.0 + "分钟");
    }
}
