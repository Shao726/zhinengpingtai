package com.dite.znpt.monitor.constant.dict;

public enum SipTransferMode implements ValueAndLabel {

    UDP("UDP","UDP"),
    TCP("TCP","TCP");

    private final String value;
    private final String label;

    SipTransferMode(String value, String label) {
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
