package com.imooc.entitys.BO;

import com.imooc.utils.validator.group.AddGroup;
import com.imooc.utils.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author TryAgain404
 * @date 2020-10-23 17:05
 */
@Data
public class UsnerBO {
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String username;
    @NotBlank(message = "密码不能为空", groups = {AddGroup.class})
    private String password;
    @NotBlank(message = "确认密码不能为空", groups = {AddGroup.class})
    private String confirmPassword;
}
