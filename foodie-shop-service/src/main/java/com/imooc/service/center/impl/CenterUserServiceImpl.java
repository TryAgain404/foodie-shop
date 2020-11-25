package com.imooc.service.center.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.config.resource.FileUpload;
import com.imooc.entitys.Users;
import com.imooc.entitys.bo.center.CenterUserBO;
import com.imooc.mapper.UsersMapper;
import com.imooc.service.center.CenterUserService;
import com.imooc.utils.DateUtil;
import com.imooc.utils.StringUtils;
import com.imooc.utils.exception.RRException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author TryAgain404
 * @mail TryAgain500@163.com
 * @date 2020-11-18 10:59
 */
@Service("centerUserService")
public class CenterUserServiceImpl extends ServiceImpl<UsersMapper, Users> implements CenterUserService {

    final
    FileUpload fileUpload;

    public CenterUserServiceImpl(FileUpload fileUpload) {
        this.fileUpload = fileUpload;
    }

    @Override
    public Users updateUserFace(MultipartFile file, String userId) {

        String uploadPathPrefix = uploadHead(file, userId);
        // 获取图片服务地址
        String imageServerUrl = fileUpload.getImageServerUrl();
        // 由于浏览器可能存在缓存的情况，所以在这里，我们需要加上时间戳来保证更新后的图片可以及时刷新
        String finalUserFaceUrl = imageServerUrl + uploadPathPrefix
                + "?t=" + DateUtil.getCurrentDateString(DateUtil.DATE_PATTERN);

        Users user = getById(userId);
        user.setFace(finalUserFaceUrl);
        saveOrUpdate(user);
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Users updateUserInfo(String userId, CenterUserBO centerUserBO) {
        Users user = getById(userId);
        BeanUtils.copyProperties(centerUserBO, user);
        this.saveOrUpdate(user);
        return user;
    }

    @Override
    public Users queryUserInfo(String userId) {
        return getById(userId);
    }

    private String uploadHead(MultipartFile file, String userId) {
        String fileSpace = fileUpload.getImageUserFaceLocation();
        // 在路径上为每一个用户增加一个userid，用于区分不同用户上传
        String uploadPathPrefix = File.separator + userId;
        // 开始文件上传
        if (file != null) {
            FileOutputStream fileOutputStream = null;
            try {
                // 获得文件上传的文件名称
                String fileName = file.getOriginalFilename();
                if (StringUtils.isNotBlank(fileName)) {
                    // 文件重命名  imooc-face.png -> ["imooc-face", "png"]
                    String[] fileNameArr = fileName.split("\\.");

                    // 获取文件的后缀名
                    String suffix = fileNameArr[fileNameArr.length - 1];
                    if (!"png".equalsIgnoreCase(suffix) && !"jpg".equalsIgnoreCase(suffix) && !"jpeg".equalsIgnoreCase(suffix)) {
                        throw new RRException("图片格式不正确");
                    }
                    // face-{userid}.png
                    // 文件名称重组 覆盖式上传，增量式：额外拼接当前时间
                    String newFileName = "face-" + userId + "." + suffix;
                    // 上传的头像最终保存的位置
                    String finalFacePath = fileSpace + uploadPathPrefix + File.separator + newFileName;
                    // 用于提供给web服务访问的地址
                    uploadPathPrefix += ("/" + newFileName);

                    File outFile = new File(finalFacePath);
                    if (outFile.getParentFile() != null) {
                        // 创建文件夹
                        outFile.getParentFile().mkdirs();
                    }
                    // 文件输出保存到目录
                    fileOutputStream = new FileOutputStream(outFile);
                    InputStream inputStream = file.getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            throw new RRException("文件不能为空！");
        }
        return uploadPathPrefix;
    }
}
