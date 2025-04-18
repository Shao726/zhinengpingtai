package com.dite.znpt.monitor.constant.dict;

public enum StreamTransferMode implements ValueAndLabel {

    UDP("UDP","UDP"),
    TCP_ACTIVE("TCP-ACTIVE","TCP主动"),
    TCP_PASSIVE("TCP-PASSIVE", "TCP被动");

    private final String value;
    private final String label;

    StreamTransferMode(String value, String label) {
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
