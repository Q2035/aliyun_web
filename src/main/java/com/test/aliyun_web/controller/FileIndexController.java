package com.test.aliyun_web.controller;

import com.test.aliyun_web.config.LoginHandlerInterceptor;
import com.test.aliyun_web.util.MatchingCharacters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class FileIndexController {

    private Logger logger = LoggerFactory.getLogger(FileIndexController.class);

    /**
     * 一方面引入files文件索引
     * 一方面将文件名加载进入字典树，便于之后的查询
     * @param model
     * @return 返回下载页面
     */
    @RequestMapping("/files")
    public String files(Model model){
        List<String > files =new ArrayList<>();
        File file = new File("D:" +File.separator + "Software" + File.separator);
        if (!file.exists()){
            logger.info("the file doesn't exists!");
        }else {
            String[] list = file.list();
            files.addAll(Arrays.asList(list));
            MatchingCharacters.insertIntoTrieTree(list);
            logger.info("file-loading successfully");
        }
        model.addAttribute("files",files);
        return "fileOperation/fileDownload";
    }

    @RequestMapping("/upload")
    public String fileUpload(){
        return "fileOperation/fileUpload";
    }

    @RequestMapping({"/","/index"})
    public String index(){
        return "index";
    }
}
