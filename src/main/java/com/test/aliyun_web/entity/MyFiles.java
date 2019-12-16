package com.test.aliyun_web.entity;

import com.mysql.cj.log.Log;

import java.util.Date;

/**
 *
 * 用于存放文件名到数据库
 * @author Q
 */
public class MyFiles {
    private Integer id;
    private String fileName;
    private Date updateDate;
    private Integer downloadCount;
    private Long fileSize;

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "MyFiles{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", updateDate=" + updateDate +
                ", downloadCount=" + downloadCount +
                ", fileSize=" + fileSize +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}
