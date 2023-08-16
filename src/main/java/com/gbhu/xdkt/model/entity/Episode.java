package com.gbhu.xdkt.model.entity;

import java.sql.Date;

/**
 *   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 *   `title` varchar(524) DEFAULT NULL COMMENT '集标题',
 *   `num` int(10) DEFAULT NULL COMMENT '第几集,全局顺序',
 *   `ordered` int(11) DEFAULT NULL COMMENT '顺序，章里面的顺序',
 *   `play_url` varchar(256) DEFAULT NULL COMMENT '播放地址',
 *   `chapter_id` int(11) DEFAULT NULL COMMENT '章节主键id',
 *   `free` tinyint(2) DEFAULT '0' COMMENT '0表示免费，1表示首付',
 *   `video_id` int(10) DEFAULT NULL COMMENT '视频id',
 *   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
 *   PRIMARY KEY (`id`)
 */
public class Episode {

    private Integer id;

    private String title;

    private Integer num;

    private Integer ordered;

    private String playUrl;

    private Integer chapterId;

    private Integer free;

    private Integer video_id;

    private Date createTime;

    @Override
    public String toString() {
        return "Episode{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", num=" + num +
                ", ordered=" + ordered +
                ", playUrl='" + playUrl + '\'' +
                ", chapterId=" + chapterId +
                ", free=" + free +
                ", video_id=" + video_id +
                ", createTime=" + createTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getOrdered() {
        return ordered;
    }

    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getFree() {
        return free;
    }

    public void setFree(Integer free) {
        this.free = free;
    }

    public Integer getVideo_id() {
        return video_id;
    }

    public void setVideo_id(Integer video_id) {
        this.video_id = video_id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
