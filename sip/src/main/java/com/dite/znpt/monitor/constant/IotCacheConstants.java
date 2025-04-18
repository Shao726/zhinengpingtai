package com.dite.znpt.monitor.constant;

/**
 * @author yunp
 * @since 2022/8/4
 * @description 缓存key定义：key全部以iot开头
 */
public class IotCacheConstants {

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 图标库 cache key
     */
    public static final String SYS_ICON_KEY = "sys_icon";

    private static final String IOT_DEVICE_VIDEO_PREFIX = "vs_device_video:";

    public static String getIotDeviceVideoKey(String deviceCode){
        return IOT_DEVICE_VIDEO_PREFIX + deviceCode;
    }

    private final static String CLIENT_TRANSACTION_CACHE_PREFIX = "IOT_CLIENT_TRANSACTION_CACHE:";

    public static String getClientTransactionCacheKey(String ssrc){
        return CLIENT_TRANSACTION_CACHE_PREFIX + ssrc;
    }

}
