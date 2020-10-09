package cn.qingmu.blog.pojo;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
	private Integer isPublic;	//是否公开
	private Integer isTop;	//是否置顶
	private Integer visits;	//浏览量
	private Integer thumbUp;	//点赞数
	private Integer thumbDown;	//点踩数
	private Integer comment;	//评论数
	private Integer state;	//审核状态
	private String url;	//文章链接
	private String type;	//类型

	private List<Label> label = new ArrayList<Label>();	//文章标签

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	public Integer getVisits() {
		return visits;
	}

	public void setVisits(Integer visits) {
		this.visits = visits;
	}

	public Integer getThumbUp() {
		return thumbUp;
	}

	public void setThumbUp(Integer thumbUp) {
		this.thumbUp = thumbUp;
	}

	public Integer getThumbDown() {
		return this.thumbDown;
	}

	public void setThumbDown(Integer thumbDown) {
		this.thumbDown = thumbDown;
	}

	public Integer getComment() {
		return comment;
	}

	public void setComment(Integer comment) {
		this.comment = comment;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Label> getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label.add(label);
	}

	@Override
	public String toString() {
		return "Article{" +
				"id='" + id + '\'' +
				", columnId='" + columnId + '\'' +
				", userId='" + userId + '\'' +
				", channelId='" + channelId + '\'' +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", image='" + image + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", isPublic='" + isPublic + '\'' +
				", isTop='" + isTop + '\'' +
				", visits=" + visits +
				", thumbUp=" + thumbUp +
				", thumbDown=" + thumbDown +
				", comment=" + comment +
				", state='" + state + '\'' +
				", url='" + url + '\'' +
				", type='" + type + '\'' +
				", label=" + label +
				'}';
	}
}
