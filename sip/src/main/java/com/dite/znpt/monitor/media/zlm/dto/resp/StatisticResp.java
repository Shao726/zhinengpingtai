package com.dite.znpt.monitor.media.zlm.dto.resp;

import lombok.Data;

/**
 * @Author: huise23
 * @Date: 2022/8/29 13:47
 * @Description:
 */
@Data
public class StatisticResp {
    private Integer Buffer;
    private Integer BufferLikeString;
    private Integer BufferList;
    private Integer BufferRaw;
    private Integer Frame;
    private Integer FrameImp;
    private Integer MediaSource;
    private Integer MultiMediaSourceMuxer;
    private Integer RtmpPacket;
    private Integer RtpPacket;
    private Integer Socket;
    private Integer TcpClient;
    private Integer TcpServer;
    private Integer TcpSession;
    private Integer UdpServer;
    private Integer UdpSession;
}
