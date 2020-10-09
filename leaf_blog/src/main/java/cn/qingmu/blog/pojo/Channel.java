package cn.qingmu.blog.pojo;

import java.io.Serializable;

/**
 * @author Zongmin
 * @date 2020-9-15 10:25:6
 * 频道
 */
public class Channel implements Serializable {

    private String id; //频道的id

    private String name; //频道的名字
    private Integer state; //频道的状态

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", state=" + state +
                '}';
    }
}
