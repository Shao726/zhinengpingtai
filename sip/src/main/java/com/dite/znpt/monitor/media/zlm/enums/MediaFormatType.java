package com.dite.znpt.monitor.media.zlm.enums;

import lombok.Getter;

/**
 * @Author: huise23
 * @Date: 2022/8/31 10:29
 * @Description:
 */
@Getter
public enum MediaFormatType {
    /**
     * FLV
     */
    flv(".live.flv"),
    /**
     * MP4
     */
    mp4(".live.mp4"),
    /**
     * HLS
     */
    hls("/hls.m3u8"),
    /**
     * RTS
     */
    rts(".live.ts");

    private final String suffix;

    MediaFormatType(String suffix) {
        this.suffix = suffix;
    }

    public static String getSuffix(String name) {
        for (MediaFormatType value : MediaFormatType.values()) {
            if (value.name().equals(name)) {
                return value.getSuffix();
            }
        }
        return "";
    }
}
