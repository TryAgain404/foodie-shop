package com.imooc.core.validate.code;

import com.imooc.utils.exception.RRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author TryAgain404
 * @mail TryAgain500@163.com
 * @date 2021-1-4 15:25
 */
@Component
public class ValidateCodeProcessorHolder {
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor processor = validateCodeProcessors.get(name);
        if (processor == null) {
            throw new RRException("验证码处理器" + name + "不存在");
        }
        return processor;
    }
}
