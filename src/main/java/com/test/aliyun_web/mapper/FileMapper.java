package com.test.aliyun_web.mapper;

import com.test.aliyun_web.entity.MyFiles;
import org.apache.ibatis.annotations.Select;

public interface FileMapper {

    @Select("select * from myFiles where fileName=#{fileName}")
    public MyFiles selectFileByFileName(String fileName);
}
