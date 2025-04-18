package com.dite.znpt.monitor.sip.transmit.cmd;

import com.dite.znpt.monitor.domain.entity.DeviceVideoEntity;

/**
 * @Author: huise23
 * @Date: 2022/8/30 8:46
 * @Description:
 */
public interface ISipDeviceCommander {

    /**
    * 查询目录列表
    * @param entity
    * @return {@link boolean}
    */
    boolean queryCatalog(DeviceVideoEntity entity);

    /**
     * 查询设备信息
     * @param entity
     * @return {@link boolean}
     */
    boolean queryDeviceInfo(DeviceVideoEntity entity);

    /**
     * 查询设备状态
     * @param entity
     * @return {@link boolean}
     */
    boolean queryDeviceStatus(DeviceVideoEntity entity);

    /**
     * 请求预览视频流
     *
     * @param entity
     * @param channelCode
     * @param ssrc
     * @param ssrcPort
     * @return {@link String}
     */
    void playStreamCmd(DeviceVideoEntity entity, String channelCode, String ssrc, Integer ssrcPort, String mediaIp);

    /**
    * 停止视频流
    * @param ssrc
    * @return
    */
    void stopStreamCmd(String ssrc);
}
