package com.dite.znpt.monitor.constant.dict;

/**
 * 设备状态
 *
 * @author huise23
 * @since 2023-07-28 15:30:10
 */
public enum DeviceStatus implements ValueAndLabel {

    INACTIV("1", "未启用"),
    ONLINE("2", "在线"),
    OFFLINE("3", "离线"),
    STOP("4", "停用");

    private final String value;
    private final String label;

    DeviceStatus(String value, String label) {
        this.value = value;
        this.label = label;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getLabel() {
        return label;
    }

}
