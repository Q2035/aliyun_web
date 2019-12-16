package com.test.aliyun_web.service.impl;

import com.test.aliyun_web.entity.MyFiles;
import com.test.aliyun_web.mapper.FileMapper;
import com.test.aliyun_web.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileMapper fileMapper;

    @Override
    public MyFiles selectFileByFileName(String fileName) {
        MyFiles myFiles = fileMapper.selectFileByFileName(fileName);
        return myFiles;
    }
}
