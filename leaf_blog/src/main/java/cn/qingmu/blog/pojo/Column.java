package cn.qingmu.blog.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Zongmin
 * @date 2020-9-15 10:27:23
 * 专栏
 */
public class Column implements Serializable {

    private String id; //专栏ID

    private String name; //专栏名称
    private String userId;  //所有者id
    private String summary;  //专栏简介
    private Date createTime;  //专栏创建时间
    private Date checkTime;  //专栏审核时间
    private Date updateTime;  //专栏最后更新时间
    private Integer state;  //专栏状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Column{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", summary='" + summary + '\'' +
                ", createTime=" + createTime +
                ", checkTime=" + checkTime +
                ", updateTime=" + updateTime +
                ", state=" + state +
                '}';
    }
}
