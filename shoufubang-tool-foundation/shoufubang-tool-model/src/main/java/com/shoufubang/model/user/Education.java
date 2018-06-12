package com.shoufubang.model.user;

import java.util.Date;

public class Education {
    private Integer id;

    private Integer userid;

    private Date graduatetime;

    private String studyresult;

    private String graduate;

    private String educationdegree;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getGraduatetime() {
        return graduatetime;
    }

    public void setGraduatetime(Date graduatetime) {
        this.graduatetime = graduatetime;
    }

    public String getStudyresult() {
        return studyresult;
    }

    public void setStudyresult(String studyresult) {
        this.studyresult = studyresult;
    }

    public String getGraduate() {
        return graduate;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }

    public String getEducationdegree() {
        return educationdegree;
    }

    public void setEducationdegree(String educationdegree) {
        this.educationdegree = educationdegree;
    }
}