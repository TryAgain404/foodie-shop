package com.imooc.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author TryAgain404
 * TODO: 直接使用控制台输出日志
 **/
@Slf4j
public class MybatisPlusOutImpl implements Log {

    static final Logger logger = LoggerFactory.getLogger(MybatisPlusOutImpl.class);

    public MybatisPlusOutImpl(String clazz) {
        // Do Nothing
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public void error(String s, Throwable e) {
        logger.error(s);
        e.printStackTrace(System.err);
    }

    @Override
    public void error(String s) {
        logger.error(s);
    }

    @Override
    public void debug(String s) {
        logger.debug(s);
    }

    @Override
    public void trace(String s) {
        logger.trace(s);
    }

    @Override
    public void warn(String s) {
        logger.warn(s);
    }
}
