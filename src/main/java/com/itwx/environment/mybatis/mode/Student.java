package com.itwx.environment.mybatis.mode;

import java.util.Date;

/**
 * @Author:wx
 * @Date:2019/8/5 10:18
 */
public class Student {
    private int id;
    private String name;
    private Date birth;
    private int score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
