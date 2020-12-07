package com.lsg.wandroidmvvm.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsg on 2020/11/3
 */
public class ProjectArticleBean {
    /**
     * apkLink :
     * audit : 1
     * author : wangjianxiandev
     * canEdit : false
     * chapterId : 294
     * chapterName : 完整项目
     * collect : false
     * courseId : 13
     * desc : 简单天气APP，使用彩云天气api，使用Kotlin语言基于MVVM模式结合JetPack组件库：LiveData、ViewModel、Lifecycle、Navigation、Room组件，以及使用协程+Retrofit进行网络请求 开发的一款天气app
     * descMd :
     * envelopePic : https://www.wanandroid.com/blogimgs/1493b21f-34d7-4e0f-b33d-1ebe1b432691.png
     * fresh : false
     * id : 15181
     * link : https://www.wanandroid.com/blog/show/2786
     * niceDate : 2020-09-09 23:42
     * niceShareDate : 2020-09-09 23:42
     * origin :
     * prefix :
     * projectLink : https://github.com/wangjianxiandev/Weather
     * publishTime : 1599666121000
     * realSuperChapterId : 293
     * selfVisible : 0
     * shareDate : 1599666121000
     * shareUser :
     * superChapterId : 294
     * superChapterName : 开源项目主Tab
     * tags : [{"name":"项目","url":"/project/list/1?cid=294"}]
     * title : 简单天气--Kotlin+JetPack+协程+MVVM架构
     * type : 0
     * userId : -1
     * visible : 1
     * zan : 0
     */

    private String apkLink;
    private int audit;
    private String author;
    private boolean canEdit;
    private int chapterId;
    private String chapterName;
    private boolean collect;
    private int courseId;
    private String desc;
    private String descMd;
    private String envelopePic;
    private boolean fresh;
    private int id;
    private String link;
    private String niceDate;
    private String niceShareDate;
    private String origin;
    private String prefix;
    private String projectLink;
    private long publishTime;
    private int realSuperChapterId;
    private int selfVisible;
    private long shareDate;
    private String shareUser;
    private int superChapterId;
    private String superChapterName;
    private String title;
    private int type;
    private int userId;
    private int visible;
    private int zan;
    private List<TagsBean> tags;

    public String getApkLink() {
        return apkLink == null ? "" : apkLink;
    }

    public void setApkLink(String apkLink) {
        this.apkLink = apkLink == null ? "" : apkLink;
    }

    public int getAudit() {
        return audit;
    }

    public void setAudit(int audit) {
        this.audit = audit;
    }

    public String getAuthor() {
        return author == null ? "" : author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? "" : author;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName == null ? "" : chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName == null ? "" : chapterName;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getDesc() {
        return desc == null ? "" : desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? "" : desc;
    }

    public String getDescMd() {
        return descMd == null ? "" : descMd;
    }

    public void setDescMd(String descMd) {
        this.descMd = descMd == null ? "" : descMd;
    }

    public String getEnvelopePic() {
        return envelopePic == null ? "" : envelopePic;
    }

    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic == null ? "" : envelopePic;
    }

    public boolean isFresh() {
        return fresh;
    }

    public void setFresh(boolean fresh) {
        this.fresh = fresh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link == null ? "" : link;
    }

    public void setLink(String link) {
        this.link = link == null ? "" : link;
    }

    public String getNiceDate() {
        return niceDate == null ? "" : niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate == null ? "" : niceDate;
    }

    public String getNiceShareDate() {
        return niceShareDate == null ? "" : niceShareDate;
    }

    public void setNiceShareDate(String niceShareDate) {
        this.niceShareDate = niceShareDate == null ? "" : niceShareDate;
    }

    public String getOrigin() {
        return origin == null ? "" : origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? "" : origin;
    }

    public String getPrefix() {
        return prefix == null ? "" : prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix == null ? "" : prefix;
    }

    public String getProjectLink() {
        return projectLink == null ? "" : projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink == null ? "" : projectLink;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public int getRealSuperChapterId() {
        return realSuperChapterId;
    }

    public void setRealSuperChapterId(int realSuperChapterId) {
        this.realSuperChapterId = realSuperChapterId;
    }

    public int getSelfVisible() {
        return selfVisible;
    }

    public void setSelfVisible(int selfVisible) {
        this.selfVisible = selfVisible;
    }

    public long getShareDate() {
        return shareDate;
    }

    public void setShareDate(long shareDate) {
        this.shareDate = shareDate;
    }

    public String getShareUser() {
        return shareUser == null ? "" : shareUser;
    }

    public void setShareUser(String shareUser) {
        this.shareUser = shareUser == null ? "" : shareUser;
    }

    public int getSuperChapterId() {
        return superChapterId;
    }

    public void setSuperChapterId(int superChapterId) {
        this.superChapterId = superChapterId;
    }

    public String getSuperChapterName() {
        return superChapterName == null ? "" : superChapterName;
    }

    public void setSuperChapterName(String superChapterName) {
        this.superChapterName = superChapterName == null ? "" : superChapterName;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title == null ? "" : title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }

    public List<TagsBean> getTags() {
        if (tags == null) {
            return new ArrayList<>();
        }
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public static class TagsBean {
        /**
         * name : 项目
         * url : /project/list/1?cid=294
         */

        private String name;
        private String url;

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name == null ? "" : name;
        }

        public String getUrl() {
            return url == null ? "" : url;
        }

        public void setUrl(String url) {
            this.url = url == null ? "" : url;
        }
    }
}
