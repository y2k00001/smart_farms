package com.neo.common.utils.uuid;

import cn.hutool.core.lang.Snowflake;

/**
 * ID生成器工具类
 *
 * @author ruoyi
 */
public class IdUtils
{

    private static final Long WORKFLOW_ID = 1L;

    private static final Long DATA_CENTER_ID = 11L;


    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    public static String randomUUID()
    {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID()
    {
        return UUID.randomUUID().toString(true);
    }

    /**
     * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 随机UUID
     */
    public static String fastUUID()
    {
        return UUID.fastUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String fastSimpleUUID()
    {
        return UUID.fastUUID().toString(true);
    }

    public static Long getSnowflakeId() {
        Snowflake snowflake = new Snowflake(WORKFLOW_ID, DATA_CENTER_ID);
        return snowflake.nextId();
    }
}
