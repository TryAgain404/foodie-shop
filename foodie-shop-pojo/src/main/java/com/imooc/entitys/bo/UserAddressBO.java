package com.imooc.entitys.bo;

import com.imooc.utils.validator.group.AddGroup;
import com.imooc.utils.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author TryAgain404
 */
@Data
public class UserAddressBO {

    private String addressId;
    @NotBlank(message = "用户ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String userId;
    @NotBlank(message = "收件人姓名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String receiver;
    @NotBlank(message = "收件人手机号不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String mobile;
    @NotBlank(message = "省份不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String province;
    @NotBlank(message = "城市不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String city;
    @NotBlank(message = "区县不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String district;
    @NotBlank(message = "详细地址不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String detail;

}
