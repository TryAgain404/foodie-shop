package com.imooc.service.center;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.entitys.Users;
import com.imooc.entitys.bo.center.CenterUserBO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author TryAgain404
 * @date 2020-11-18 10:58
 */
public interface CenterUserService extends IService<Users> {
    Users updateUserFace(MultipartFile file,  String userId);

    Users updateUserInfo(String userId, CenterUserBO centerUserBO);

    Users queryUserInfo(String userId);
}
