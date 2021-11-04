package com.person.kotlintest.recycle;

/**
 * @anthor tr
 * @date 2021/10/19
 * @desc
 */
public class MsgBean {
    private String created;
    private String content;
    private String contentMore;

    public MsgBean(String created, String content, String contentMore) {
        this.created = created;
        this.content = content;
        this.contentMore = contentMore;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentMore() {
        return contentMore;
    }

    public void setContentMore(String contentMore) {
        this.contentMore = contentMore;
    }
}
