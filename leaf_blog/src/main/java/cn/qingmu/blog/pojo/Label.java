package cn.qingmu.blog.pojo;

import java.io.Serializable;

/**
 * @author Zongmin
 * @date 2020-9-15 10:22:41
 * 标签实体
 */
public class Label implements Serializable {

    private String id; //标签的id

    private String name; //标签名
    private String describe;  //标签的描述

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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Label{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }
}
