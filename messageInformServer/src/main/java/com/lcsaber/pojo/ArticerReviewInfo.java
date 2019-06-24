package com.lcsaber.pojo;

import java.sql.Timestamp;

/**
 * @Author: LiChao
 * @Date: 2019/6/21 15:09
 */
public class ArticerReviewInfo {
    private Long id;
    private Long reviewerId;
    private Long contentId;
    private Integer contentType;
    private Integer level;
    private Integer result;
    private String description;
    private Timestamp reviewTime;
    private Long authorId;

    @Override
    public String toString() {
        return "ArticerReviewInfo{" +
                "id=" + id +
                ", reviewerId=" + reviewerId +
                ", contentId=" + contentId +
                ", contentType=" + contentType +
                ", level=" + level +
                ", result=" + result +
                ", description='" + description + '\'' +
                ", reviewTime=" + reviewTime +
                ", authorId=" + authorId +
                '}';
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Timestamp reviewTime) {
        this.reviewTime = reviewTime;
    }
}
