package com.dite.znpt.monitor.constant.dict;

/**
 * 摄像头类型
 *
 * @author huise23
 * @since 2023-07-28 15:30:10
 */
public enum CameraType implements ValueAndLabel {

    UNKNOWN("0","未知"),
    BALLHEAD("1","球机");

    private final String value;
    private final String label;

    CameraType(String value, String label) {
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
