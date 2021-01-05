package com.imooc.core.validate.code.image;

import com.imooc.core.validate.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author TryAgain404
 * @mail TryAgain500@163.com
 * @date 2021-1-4 14:38
 */
public class ImageCode extends ValidateCode {

    /**
     *
     */
    private static final long serialVersionUID = -6020470039852318468L;

    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn){
        super(code, expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime){
        super(code, expireTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
