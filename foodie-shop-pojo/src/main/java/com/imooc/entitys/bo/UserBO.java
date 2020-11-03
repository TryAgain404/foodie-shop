package com.imooc.entitys.bo;

import com.imooc.utils.validator.group.AddGroup;
import com.imooc.utils.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author TryAgain404
 * @date 2020-10-23 17:05
 */
@Data
public class UserBO {
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "确认密码", name = "confirmPassword", example = "123456", required = true)
    private String username;
    @NotBlank(message = "密码不能为空", groups = {AddGroup.class})
    @ApiModelProperty(value = "确认密码", name = "confirmPassword", example = "123456", required = true)
    private String password;
    @ApiModelProperty(value = "确认密码", name = "confirmPassword", example = "123456", required = false)
    private String confirmPassword;

}
