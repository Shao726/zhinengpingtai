package com.dite.znpt.monitor.constant;

/**
 * @author yunp
 * @since 2022/8/4
 * @description 响应文案定义
 */
public class IotRespMessage {

    public static final String UNKNOWN_FAIL = "未知错误";

    public static final String ID_NOT_FOUND = "数据不存在";

    public static final String PARAMETER_ERROR = "参数有误";

    public static final String NO_PERMISSION = "没有访问权限";

    public static final String PARENT_AREA_NOT_FOUND = "上级分区不存在";

    public static final String CAR_TYPE_HAS_CARS = "该车型下仍有车辆，无法删除";

    public static final String CAR_BRAND_HAS_CARS = "该品牌下仍有车辆，无法删除";

    public static final String CAR_PLATE_NUMBER_EXIST = "该车牌号车辆已存在";

    public static final String CAR_PLATE_NUMBER_NOT_EXIST = "该车牌号车辆不存在";

    public static final String CAR_HAS_BIND_TAG_OR_SPEED_MONITOR = "车辆已绑定车载标签或速度检测仪，禁止删除";

    public static final String CAR_FREIGHT_NOT_EXIST = "货物不存在";

    public static final String CAR_HAS_BIND_ALARM_TEMPLATE = "该车辆已绑定其他告警模板";

    public static final String USER_HAS_BIND_ALARM_TEMPLATE = "该人员已绑定其他告警模板";

    public static final String REPETITION_ALARM_FOR_AREA = "该厂区分区下已有同类型告警，无法重复添加";

    public static final String REPETITION_ALARM_NOTIFY_CONFIG = "已存在相同部门层级的告警消息推送配置";

    public static final String DEVICE_TERMINAL_HAS_BEEN_BIND = "该标签卡已被其他人绑定";

    public static final String DEVICE_TERMINAL_TYPE_ERROR = "标签卡类型有误";

    public static final String DEVICE_TERMINAL_NOT_FOUND_OR_STATUS_ERROR = "标签卡未找到或状态异常";

    public static final String DEVICE_CANNOT_EDIT = "设备未启用或已停用才可编辑";

    public static final String VIDEO_DEVICE_CANNOT_DELETE = "视频设备禁止删除";

    public static final String DEVICE_CANNOT_DELETE = "未启用的设备才可删除";

    public static final String DEVICE_HAS_BIND_TO_GROUP = "设备已经绑定至该分组";

    public static final String DEVICE_VIDEO_CANNOT_DELETE = "禁止删除在线视频设备";

    public static final String DEVICE_VIDEO_CANNOT_SYNC = "禁止更新离线视频设备";

    public static final String DEVICE_INACTIVE = "设备未启用";

    public static final String CODE_HAS_BEEN_USED = "编号已被占用";

    public static final String NAME_HAS_BEEN_USED = "名称已被占用";

    public static final String FLAG_HAS_BEEN_USED = "标识已被占用";

    public static final String GROUP_HAS_CHILD_CANNOT_REMOVE = "分组有下级分组，无法删除";

    public static final String GROUP_HAS_DEVICE_CANNOT_REMOVE = "分组下有绑定设备，无法删除";

    public static final String PRODUCT_PUBLISH_CANNOT_DELETE = "该产品已发布，不可删除";

    public static final String PRODUCT_HAS_DEVICE_CANNOT_REMOVE = "产品下存在设备关联，需删除设备后进行操作";

    public static final String MSG_PROTOCOL_PUBLISH_CANNOT_DELETE = "消息协议未发布，发布协议后操作";

    public static final String CATEGORY_CANNOT_DELETE = "该产品分类信息已被产品关联，不可删除";

    public static final String SCENE_CANNOT_DELETE = "该场景信息已被产品关联，不可删除";

    public static final String SWITCH_PARAM_HAS_BEEN_USED = "该参数在设备服务中只能定义一个";

    public static final String CONFIG_CANNOT_DELETE = "该配置已被通知模板关联，不可删除， 请取消关联后重试。";

    public static final String TEMPLATE_CANNOT_DELETE = "该通知模板已被场景联动关联，不可删除， 请取消关联后操作。";

    public static final String PROTOCOL_CANNOT_DELETE_BY_PUBLISHED = "当前协议状态为已发布,不可删除.";

    public static final String PROTOCOL_CANNOT_DELETE_WITH_PRODUCT = "该协议已被产品关联，不可删除，请删除关联后操作!";

    public static final String PROTOCOL_CANNOT_UN_PUBLISH =  "协议已被产品发布，不可取消!";

    public static final String PROTOCOL_TYPE_CANNOT_NULL = "协议类型不能为空";

    public static final String PROTOCOL_CLASS_NAME_CANNOT_EMPTY = "协议类型为Jar或者Local类型时，必须指定协议类名.";

    public static final String PROTOCOL_FILE_PATH_CANNOT_EMPTY = "协议类型为Jar或者Local类型时，必须上传或者指定协议文件路径.";

    public static final String DATA_ILLEGAL =  "数据非法";

    public static final String IMPORT_ERROR =  "导入出错";

    public static final String COMMON_DEVICE_ATTR_DUPLICATE_ERROR =  "订阅的通用设备点位全局不唯一";

    public static final String PROTOCOL_NOT_EXISTS =  "协议不存在";

    public static final String RULE_NOT_EXISTS =  "上报规则不存在";

    public static final String DEVICE_NOT_EXISTS =  "设备不存在";

    public static final String DATA_DATA_TREND_TIME_TYPE_NOT_EXISTS = "设备数据趋势时间类型不存在";
    public static final String DATA_DATA_TREND_DATA_TYPE_NOT_EXISTS = "设备数据趋势数据类型不存在";
    public static final String CRON_ERROR = "cron表达式输入错误:";
}
