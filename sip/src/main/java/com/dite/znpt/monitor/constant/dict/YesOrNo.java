package com.dite.znpt.monitor.constant.dict;

/**
 * YesOrNo
 *
 * @author huise23
 * @since 2023-07-28 15:30:10
 */
public enum YesOrNo implements ValueAndLabel {

    YES("Y","是"),
    NO("N", "否");

    private final String value;
    private final String label;

    YesOrNo(String value, String label) {
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
