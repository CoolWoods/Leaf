package cn.qingmu.blog.pojo;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

/**
 * article实体类
 * @author Administrator
 *
 */
public class Article implements Serializable{

	@Id
	private String id;	//文章的ID
	
	private String columnId;	//专栏ID
	private String userId;	//所属用户ID
	private String channelId;	//所属频道
	private String title;	//标题
	private String content;	//文章正文
	private String image;	//文章封面
	private java.util.Date createTime;	//发表日期
	private java.util.Date updateTime;	//修改日期
	private String isPublic;	//是否公开
	private String isTop;	//是否置顶
	private Integer visits;	//浏览量
	private Integer thumbUp;	//点赞数
	private Integer ThumbsDown;	//点踩数
	private Integer comment;	//评论数
	private String state;	//审核状态
	private String url;	//文章链接
	private String type;	//类型
	private List<String> label;	//文章标签

}
