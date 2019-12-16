package com.test.aliyun_web.controller;

import com.test.aliyun_web.entity.MyFiles;
import com.test.aliyun_web.service.FileService;
import com.test.aliyun_web.util.MatchingCharacters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

@Controller
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Value("${file.baseLocation}")
    private String baseLocation;

    @Autowired
    FileService fileService;

    @ResponseBody
    @GetMapping("/hello")
    public MyFiles hello() {
        MyFiles myFiles = fileService.selectFileByFileName("jdk11.tar.gz");
        System.out.println(myFiles);
        return myFiles;
    }

    /**
     * 文件下载
     * 用RESTful风格传入文件名
     * 单个文件下载，可以考虑添加文件夹的下载 文件夹的话可以先压缩成rar格式(先不弄)
     * 解决了下载报错getOutputStream()已被占用的问题
     * @param fileToDownloadName:下载的文件
     * @return 下载页面
     */
    @ResponseBody
    @GetMapping("/file/download/{fileToDownloadName}")
    public String fileDownload(@PathVariable("fileToDownloadName")String fileToDownloadName,
                               Model model,
                               HttpServletResponse response) throws UnsupportedEncodingException {
        String fileName=baseLocation+fileToDownloadName;
        String msg =null;
        if (fileName!= null){
            File file = new File(fileName);
            if (file.exists()){
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/force-download");
                response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileToDownloadName.getBytes("utf-8"),"ISO8859-1"));
                response.addHeader("Content-Length",""+file.length());
                byte[] buffer =new byte[1024];
                FileInputStream fis =null;
                BufferedInputStream bis =null;
                OutputStream os =null;
                try {
                    fis =new FileInputStream(file);
                    bis =new BufferedInputStream(fis);
                    os =response.getOutputStream();
                    int i =bis.read(buffer);
                    while (i !=-1){
                        os.write(buffer,0,i);
                        i =bis.read(buffer);
                    }
                    try {
                        os.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return "fail to download";
                } catch (FileNotFoundException e) {
                    logger.info("the file doesn't exits:"+fileToDownloadName);
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (os!=null){
                        try {
                            os.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (bis!=null){
                        try {
                            bis.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    if (fis !=null){
                        try {
                            fis.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        msg ="fail to download";
        model.addAttribute("msg",msg);
        return "success";
    }

    /**
     *
     * @param file 表单中input框代表的file文件
     * @param request
     * @param model 用于存放msg，返回上传信息
     * @return 直接返回下载页面
     */
    @PostMapping("/file/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file,
                             HttpServletRequest request,
                             Model model){
//        设置文件上传或者失败的提示字符
        String msg="null";
        try {
            if (file.isEmpty()){
                msg ="the file is empty";
                System.out.println(msg);
                return "redirect:/files?msg="+msg;
            }
//            获取文件名
            String fileName =file.getOriginalFilename();
            logger.info(request.getRemoteAddr()+":上传文件名为:" +fileName);
            String path =baseLocation +fileName;
            File dest =new File(path);
            if (!dest.exists()){
                dest.mkdir();
            }
            file.transferTo(dest);
        }catch (IllegalStateException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        msg ="upload successful";
        System.out.println(msg);
        return "redirect:/files?msg="+msg;
    }

    /**
     * 文件搜索 使用字典树
     * @param str 查询的字符串
     * @return 返回结果集的json串
     */
    @GetMapping("/file/search")
    public String  fileSearch(@RequestParam("search")String str,
                              Model model){
        boolean hello = MatchingCharacters.searchStringOnTrieTree(str);
        if (hello){
            List<String> list = MatchingCharacters.listAllStringsOnTrieTree(str);
            model.addAttribute("files",list);
            return "fileOperation/fileDownload";
        }else {
            return "redirect:/files?msg=no files found";
        }
    }


}
