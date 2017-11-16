package com.shop.service.impl;

import com.google.common.collect.Lists;
import com.shop.service.FileService;
import com.shop.utils.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * FileServiceImpl
 *
 * @author Yarn
 * @create 2017/11/14/20:04
 */
@Service
public class FileServiceImpl implements FileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public String upload(MultipartFile file,String path){
        //获取原文件名
        String fileName = file.getOriginalFilename();
        //得到文件后缀名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        //重命名文件
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        logger.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}",fileName,path,uploadFileName);
        //得到文件夹路径
        File fileDir = new File(path);
        if(!fileDir.exists()){
            //文件夹不存在的话 就创建文件夹
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }

        File targetFile = new File(path,uploadFileName);
        try {
            //文件已经上传成功了
            file.transferTo(targetFile);
            //已经上传到ftp服务器上
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            // 删除tomcat下上传的文件
            //targetFile.delete();
        } catch (IOException e) {
            logger.error("上传文件异常",e);
            return null;
        }
        return targetFile.getName();
    }

}
