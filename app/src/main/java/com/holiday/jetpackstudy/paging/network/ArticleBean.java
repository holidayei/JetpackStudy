package com.holiday.jetpackstudy.paging.network;

import java.util.List;
import java.util.Objects;

/**
 * 玩安卓首页文章实体
 */
public class ArticleBean {

    private DataBean data;
    private int errorCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class DataBean {

        private int curPage;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;
        private List<Article> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<Article> getDatas() {
            return datas;
        }

        public void setDatas(List<Article> datas) {
            this.datas = datas;
        }

        public static class Article {
            /**
             * apkLink :
             * audit : 1
             * author :
             * canEdit : false
             * chapterId : 502
             * chapterName : 自助
             * collect : false
             * courseId : 13
             * desc :
             * descMd :
             * envelopePic :
             * fresh : true
             * id : 13306
             * link : https://juejin.im/post/5eb4e92ff265da7ba0582755
             * niceDate : 1小时前
             * niceShareDate : 1小时前
             * origin :
             * prefix :
             * projectLink :
             * publishTime : 1588917436000
             * selfVisible : 0
             * shareDate : 1588917436000
             * shareUser : Android_Jiang
             * superChapterId : 494
             * superChapterName : 广场Tab
             * tags : []
             * title : 老生常谈：事件分发
             * type : 0
             * userId : 56200
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
            private List<?> tags;

            public String getApkLink() {
                return apkLink;
            }

            public void setApkLink(String apkLink) {
                this.apkLink = apkLink;
            }

            public int getAudit() {
                return audit;
            }

            public void setAudit(int audit) {
                this.audit = audit;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
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
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
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
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getDescMd() {
                return descMd;
            }

            public void setDescMd(String descMd) {
                this.descMd = descMd;
            }

            public String getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
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
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public String getNiceShareDate() {
                return niceShareDate;
            }

            public void setNiceShareDate(String niceShareDate) {
                this.niceShareDate = niceShareDate;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public String getPrefix() {
                return prefix;
            }

            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }

            public String getProjectLink() {
                return projectLink;
            }

            public void setProjectLink(String projectLink) {
                this.projectLink = projectLink;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
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
                return shareUser;
            }

            public void setShareUser(String shareUser) {
                this.shareUser = shareUser;
            }

            public int getSuperChapterId() {
                return superChapterId;
            }

            public void setSuperChapterId(int superChapterId) {
                this.superChapterId = superChapterId;
            }

            public String getSuperChapterName() {
                return superChapterName;
            }

            public void setSuperChapterName(String superChapterName) {
                this.superChapterName = superChapterName;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
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

            public List<?> getTags() {
                return tags;
            }

            public void setTags(List<?> tags) {
                this.tags = tags;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Article)) return false;
                Article bean = (Article) o;
                return getAudit() == bean.getAudit() &&
                        isCanEdit() == bean.isCanEdit() &&
                        getChapterId() == bean.getChapterId() &&
                        isCollect() == bean.isCollect() &&
                        getCourseId() == bean.getCourseId() &&
                        isFresh() == bean.isFresh() &&
                        getId() == bean.getId() &&
                        getPublishTime() == bean.getPublishTime() &&
                        getSelfVisible() == bean.getSelfVisible() &&
                        getShareDate() == bean.getShareDate() &&
                        getSuperChapterId() == bean.getSuperChapterId() &&
                        getType() == bean.getType() &&
                        getUserId() == bean.getUserId() &&
                        getVisible() == bean.getVisible() &&
                        getZan() == bean.getZan() &&
                        Objects.equals(getApkLink(), bean.getApkLink()) &&
                        Objects.equals(getAuthor(), bean.getAuthor()) &&
                        Objects.equals(getChapterName(), bean.getChapterName()) &&
                        Objects.equals(getDesc(), bean.getDesc()) &&
                        Objects.equals(getDescMd(), bean.getDescMd()) &&
                        Objects.equals(getEnvelopePic(), bean.getEnvelopePic()) &&
                        Objects.equals(getLink(), bean.getLink()) &&
                        Objects.equals(getNiceDate(), bean.getNiceDate()) &&
                        Objects.equals(getNiceShareDate(), bean.getNiceShareDate()) &&
                        Objects.equals(getOrigin(), bean.getOrigin()) &&
                        Objects.equals(getPrefix(), bean.getPrefix()) &&
                        Objects.equals(getProjectLink(), bean.getProjectLink()) &&
                        Objects.equals(getShareUser(), bean.getShareUser()) &&
                        Objects.equals(getSuperChapterName(), bean.getSuperChapterName()) &&
                        Objects.equals(getTitle(), bean.getTitle()) &&
                        Objects.equals(getTags(), bean.getTags());
            }

            @Override
            public int hashCode() {
                return Objects.hash(getApkLink(), getAudit(), getAuthor(), isCanEdit(), getChapterId(), getChapterName(), isCollect(), getCourseId(), getDesc(), getDescMd(), getEnvelopePic(), isFresh(), getId(), getLink(), getNiceDate(), getNiceShareDate(), getOrigin(), getPrefix(), getProjectLink(), getPublishTime(), getSelfVisible(), getShareDate(), getShareUser(), getSuperChapterId(), getSuperChapterName(), getTitle(), getType(), getUserId(), getVisible(), getZan(), getTags());
            }
        }
    }
}
