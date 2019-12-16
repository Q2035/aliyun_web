package com.test.aliyun_web.service;

import com.test.aliyun_web.entity.MyFiles;

public interface FileService {
    public MyFiles selectFileByFileName(String fileName);
}
