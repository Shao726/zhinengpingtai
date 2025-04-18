package com.dite.znpt.monitor.media.zlm.dto;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.annotation.JSONField;
import com.dite.znpt.monitor.media.zlm.config.StreamMediaServerConfig;
import com.dite.znpt.monitor.media.zlm.dto.req.BaseReq;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author: huise23
 * @Date: 2022/8/29 10:54
 * @Description: 服务器配置
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class ServerConfig extends BaseReq {
    // ----------------------------------------------- api -----------------------------------------------
    /**
     * 是否调试http api,启用调试后，会打印每次http请求的内容和回复
     * apiDebug=1
     */
    @JSONField(name = "api.apiDebug")
    private Integer apiDebug;
    /**
     * 一些比较敏感的http api在访问时需要提供secret，否则无权限调用
     * 如果是通过127.0.0.1访问,那么可以不提供secret
     * secret=035c73f7-bb6b-4889-a715-d9eb2d1925cc
     */
    @JSONField(name = "api.secret")
    private String apiSecret;
    /**
     * 截图保存路径根目录，截图通过http api(/index/api/getSnap)生成和获取
     * snapRoot=./www/snap/
     */
    @JSONField(name = "api.snapRoot")
    private String apiSnapRoot;
    /**
     * 默认截图图片，在启动FFmpeg截图后但是截图还未生成时，可以返回默认的预设图片
     * defaultSnap=./www/logo.png
     */
    @JSONField(name = "api.defaultSnap")
    private String apiDefaultSnap;
    // ----------------------------------------------- ffmpeg -----------------------------------------------
    /**
     * FFmpeg可执行程序路径,支持相对路径/绝对路径
     * bin=/usr/bin/ffmpeg
     */
    @JSONField(name = "ffmpeg.bin")
    private String ffmpegBin;
    /**
     * FFmpeg拉流再推流的命令模板，通过该模板可以设置再编码的一些参数
     * cmd=%s -re -i %s -c:a aac -strict -2 -ar 44100 -ab 48k -c:v libx264 -f flv %s
     */
    @JSONField(name = "ffmpeg.cmd")
    private String ffmpegCmd;
    /**
     * FFmpeg生成截图的命令，可以通过修改该配置改变截图分辨率或质量
     * snap=%s -i %s -y -f mjpeg -t 0.001 %s
     */
    @JSONField(name = "ffmpeg.snap")
    private String ffmpegSnap;
    /**
     * FFmpeg日志的路径，如果置空则不生成FFmpeg日志
     * 可以为相对(相对于本可执行程序目录)或绝对路径
     * log=./ffmpeg/ffmpeg.log
     */
    @JSONField(name = "ffmpeg.log")
    private String ffmpegLog;
    /**
     * 自动重启的时间(秒), 默认为0, 也就是不自动重启. 主要是为了避免长时间ffmpeg拉流导致的不同步现象
     * restart_sec=0
     */
    @JSONField(name = "ffmpeg.restart_sec")
    private String ffmpegRestartSec;
    // ----------------------------------------------- general -----------------------------------------------
    /**
     * 是否启用虚拟主机
     * enableVhost=0
     */
    @JSONField(name = "general.enableVhost")
    private Integer enableVhost;
    /**
     * 播放器或推流器在断开后会触发hook.on_flow_report事件(使用多少流量事件)，
     * flowThreshold参数控制触发hook.on_flow_report事件阈值，使用流量超过该阈值后才触发，单位KB
     * flowThreshold=1024
     */
    @JSONField(name = "general.flowThreshold")
    private Integer generalFlowThreshold;
    /**
     * 播放最多等待时间，单位毫秒
     * 播放在播放某个流时，如果该流不存在，
     * ZLMediaKit会最多让播放器等待maxStreamWaitMS毫秒
     * 如果在这个时间内，该流注册成功，那么会立即返回播放器播放成功
     * 否则返回播放器未找到该流，该机制的目的是可以先播放再推流
     * maxStreamWaitMS=15000
     */
    @JSONField(name = "general.maxStreamWaitMS")
    private Integer generalMaxStreamWaitMs;
    /**
     * 某个流无人观看时，触发hook.on_stream_none_reader事件的最大等待时间，单位毫秒
     * 在配合hook.on_stream_none_reader事件时，可以做到无人观看自动停止拉流或停止接收推流
     * streamNoneReaderDelayMS=20000
     */
    @JSONField(name = "general.streamNoneReaderDelayMS")
    private Integer generalStreamNoneReaderDelayMs;
    /**
     * 是否全局添加静音aac音频，转协议时有效
     * 有些播放器在打开单视频流时不能秒开，添加静音音频可以加快秒开速度
     * addMuteAudio=1
     */
    @JSONField(name = "general.addMuteAudio")
    private Integer generalAddMuteAudio;
    /**
     * 拉流代理时如果断流再重连成功是否删除前一次的媒体流数据，如果删除将重新开始，
     * 如果不删除将会接着上一次的数据继续写(录制hls/mp4时会继续在前一个文件后面写)
     * resetWhenRePlay=1
     */
    @JSONField(name = "general.resetWhenRePlay")
    private Integer generalResetWhenRePlay;
    /**
     * 是否默认推流时转换成hls，hook接口(on_publish)中可以覆盖该设置
     * publishToHls=1
     */
    @JSONField(name = "general.publishToHls")
    private Integer generalPublishToHls;
    /**
     * 是否默认推流时mp4录像，hook接口(on_publish)中可以覆盖该设置
     * publishToMP4=0
     */
    @JSONField(name = "general.publishToMP4")
    private Integer generalPublishToMP4;
    /**
     * 合并写缓存大小(单位毫秒)，合并写指服务器缓存一定的数据后才会一次性写入socket，这样能提高性能，但是会提高延时
     * 开启后会同时关闭TCP_NODELAY并开启MSG_MORE
     * mergeWriteMS=0
     */
    @JSONField(name = "general.mergeWriteMS")
    private Integer generalMergeWriteMS;
    /**
     * 全局的时间戳覆盖开关，在转协议时，对frame进行时间戳覆盖
     * 该开关对rtsp/rtmp/rtp推流、rtsp/rtmp/hls拉流代理转协议时生效
     * 会直接影响rtsp/rtmp/hls/mp4/flv等协议的时间戳
     * 同协议情况下不影响(例如rtsp/rtmp推流，那么播放rtsp/rtmp时不会影响时间戳)
     * modifyStamp=0
     */
    @JSONField(name = "general.modifyStamp")
    private Integer generalModifyStamp;
    /**
     * 服务器唯一id，用于触发hook时区别是哪台服务器
     * mediaServerId=your_server_id
     */
    @JSONField(name = "general.mediaServerId")
    private String generalMediaServerId;
    /**
     * 转协议是否全局开启或关闭音频
     * enable_audio=1
     */
    @JSONField(name = "general.enable_audio")
    private Integer generalEnableAudio;
    //  ###### 以下是按需转协议的开关，在测试ZLMediaKit的接收推流性能时，请把下面开关置1
    //  ###### 如果某种协议你用不到，你可以把以下开关置1以便节省资源(但是还是可以播放，只是第一个播放者体验稍微差点)，
    //  ###### 如果某种协议你想获取最好的用户体验，请置0(第一个播放者可以秒开，且不花屏)
    /**
     * hls协议是否按需生成，如果hls.segNum配置为0(意味着hls录制)，那么hls将一直生成(不管此开关)
     * hls_demand=0
     */
    @JSONField(name = "general.hls_demand")
    private Integer generalHlsDemand;
    /**
     * rtsp[s]协议是否按需生成
     * rtsp_demand=0
     */
    @JSONField(name = "general.rtsp_demand")
    private Integer generalRtspDemand;
    /**
     * rtmp[s]、http[s]-flv、ws[s]-flv协议是否按需生成
     * rtmp_demand=0
     */
    @JSONField(name = "general.rtmp_demand")
    private Integer generalRtmpDemand;
    /**
     * http[s]-ts协议是否按需生成
     * ts_demand=0
     */
    @JSONField(name = "general.ts_demand")
    private Integer generalTsDemand;
    /**
     * http[s]-fmp4、ws[s]-fmp4协议是否按需生成
     * fmp4_demand=0
     */
    @JSONField(name = "general.fmp4_demand")
    private Integer generalFmp4Demand;
    /**
     * 最多等待未初始化的Track时间，单位毫秒，超时之后会忽略未初始化的Track
     * wait_track_ready_ms=10000
     */
    @JSONField(name = "general.wait_track_ready_ms")
    private Integer generalWaitTrackReadyMs;
    /**
     * 如果流只有单Track，最多等待若干毫秒，超时后未收到其他Track的数据，则认为是单Track
     * 如果协议元数据有声明特定track数，那么无此等待时间
     * wait_add_track_ms=3000
     */
    @JSONField(name = "general.wait_add_track_ms")
    private Integer generalWaitAddTrackMs;
    /**
     * 如果track未就绪，我们先缓存帧数据，但是有最大个数限制，防止内存溢出
     * unready_frame_cache=100
     */
    @JSONField(name = "general.unready_frame_cache")
    private Integer generalUnreadyFrameCache;
    /**
     * 推流断开后可以在超时时间内重新连接上继续推流，这样播放器会接着播放。
     * 置0关闭此特性(推流断开会导致立即断开播放器)
     * 此参数不应大于播放器超时时间
     * continue_push_ms=15000
     */
    @JSONField(name = "general.continue_push_ms")
    private Integer generalContinuePushMs;
    // ----------------------------------------------- hls -----------------------------------------------
    /**
     * hls写文件的buf大小，调整参数可以提高文件io性能
     * fileBufSize=65536
     */
    @JSONField(name = "hls.fileBufSize")
    private Integer hlsFileBufSize;
    /**
     * hls保存文件路径
     * 可以为相对(相对于本可执行程序目录)或绝对路径
     * filePath=./www
     */
    @JSONField(name = "hls.filePath")
    private String hlsFilePath;
    /**
     * hls最大切片时间
     * segDur=2
     */
    @JSONField(name = "hls.segDur")
    private Integer hlsSegDur;
    /**
     * m3u8索引中,hls保留切片个数(实际保留切片个数大2~3个)
     * 如果设置为0，则不删除切片，而是保存为点播
     * segNum=3
     */
    @JSONField(name = "hls.segNum")
    private Integer hlsSegNum;
    /**
     * HLS切片从m3u8文件中移除后，继续保留在磁盘上的个数
     * segRetain=5
     */
    @JSONField(name = "hls.segRetain")
    private Integer hlsSegRetain;
    /**
     * 是否广播 ts 切片完成通知
     * broadcastRecordTs=0
     */
    @JSONField(name = "hls.broadcastRecordTs")
    private Integer hlsBroadcastRecordTs;
    /**
     * 直播hls文件删除延时，单位秒，issue: #913
     * deleteDelaySec=0
     */
    @JSONField(name = "hls.deleteDelaySec")
    private Integer hlsDeleteDelaySec;
    /**
     * 是否保留hls文件，此功能部分等效于segNum=0的情况
     * 不同的是这个保留不会在m3u8文件中体现
     * 0为不保留，不起作用
     * 1为保留，则不删除hls文件，如果开启此功能，注意磁盘大小，或者定期手动清理hls文件
     * segKeep=0
     */
    @JSONField(name = "hls.segKeep")
    private Integer hlsSegKeep;
    // ----------------------------------------------- hook -----------------------------------------------
    /**
     * 在推流时，如果url参数匹对admin_params，那么可以不经过hook鉴权直接推流成功，播放时亦然
     * 该配置项的目的是为了开发者自己调试测试，该参数暴露后会有泄露隐私的安全隐患
     * admin_params=secret=035c73f7-bb6b-4889-a715-d9eb2d1925cc
     */
    @JSONField(name = "hook.admin_params")
    private String hookAdminParams;
    /**
     * 是否启用hook事件，启用后，推拉流都将进行鉴权
     * enable=0
     */
    @JSONField(name = "hook.enable")
    private Integer hookHookEnable;
    /**
     * 播放器或推流器使用流量事件，置空则关闭
     * on_flow_report=https://127.0.0.1/index/hook/on_flow_report
     */
    @JSONField(name = "hook.on_flow_report")
    private String hookOnFlowReport;
    /**
     * 访问http文件鉴权事件，置空则关闭鉴权
     * on_http_access=https://127.0.0.1/index/hook/on_http_access
     */
    @JSONField(name = "hook.on_http_access")
    private String hookOnHttpAccess;
    /**
     * 播放鉴权事件，置空则关闭鉴权
     * on_play=https://127.0.0.1/index/hook/on_play
     */
    @JSONField(name = "hook.on_play")
    private String hookOnPlay;
    /**
     * 推流鉴权事件，置空则关闭鉴权
     * on_publish=https://127.0.0.1/index/hook/on_publish
     */
    @JSONField(name = "hook.on_publish")
    private String hookOnPublish;
    /**
     * 录制mp4切片完成事件
     * on_record_mp4=https://127.0.0.1/index/hook/on_record_mp4
     */
    @JSONField(name = "hook.on_record_mp4")
    private String hookOnRecordMp4;
    /**
     * 录制 hls ts 切片完成事件
     * on_record_ts=https://127.0.0.1/index/hook/on_record_ts
     */
    @JSONField(name = "hook.on_record_ts")
    private String hookOnRecordTs;
    /**
     * rtsp播放鉴权事件，此事件中比对rtsp的用户名密码
     * on_rtsp_auth=https://127.0.0.1/index/hook/on_rtsp_auth
     */
    @JSONField(name = "hook.on_rtsp_auth")
    private String hookOnRtspAuth;
    /**
     * rtsp播放是否开启专属鉴权事件，置空则关闭rtsp鉴权。rtsp播放鉴权还支持url方式鉴权
     * 建议开发者统一采用url参数方式鉴权，rtsp用户名密码鉴权一般在设备上用的比较多
     * 开启rtsp专属鉴权后，将不再触发on_play鉴权事件
     * on_rtsp_realm=https://127.0.0.1/index/hook/on_rtsp_realm
     */
    @JSONField(name = "hook.on_rtsp_realm")
    private String hookOnRtspRealm;
    /**
     * 远程telnet调试鉴权事件
     * on_shell_login=https://127.0.0.1/index/hook/on_shell_login
     */
    @JSONField(name = "hook.on_shell_login")
    private String hookOnShellLogin;
    /**
     * 直播流注册或注销事件
     * on_stream_changed=https://127.0.0.1/index/hook/on_stream_changed
     */
    @JSONField(name = "hook.on_stream_changed")
    private String hookOnStreamChanged;
    /**
     * 服务器启动报告，可以用于服务器的崩溃重启事件监听
     * on_server_started=https://127.0.0.1/index/hook/on_server_started
     */
    @JSONField(name = "hook.on_server_started")
    private String hookOnServerStarted;
    /**
     * server保活上报
     * on_server_keepalive=https://127.0.0.1/index/hook/on_server_keepalive
     */
    @JSONField(name = "hook.on_server_keepalive")
    private String hookOnServerKeepalive;
    /**
     * 无人观看流事件，通过该事件，可以选择是否关闭无人观看的流。配合general.streamNoneReaderDelayMS选项一起使用
     * on_stream_none_reader=https://127.0.0.1/index/hook/on_stream_none_reader
     */
    @JSONField(name = "hook.on_stream_none_reader")
    private String hookOnStreamNoneReader;
    /**
     * 播放时，未找到流事件，通过配合hook.on_stream_none_reader事件可以完成按需拉流
     * on_stream_not_found=https://127.0.0.1/index/hook/on_stream_not_found
     */
    @JSONField(name = "hook.on_stream_not_found")
    private String hookOnStreamNotFound;
    /**
     * 发送rtp(startSendRtp)被动关闭时回调
     * on_send_rtp_stopped=https://127.0.0.1/index/hook/on_send_rtp_stopped
     */
    @JSONField(name = "hook.on_send_rtp_stopped")
    private String hookOnSendRtpStopped;
    /**
     * hook api最大等待回复时间，单位秒
     * timeoutSec=10
     */
    @JSONField(name = "hook.timeoutSec")
    private Integer hookTimeoutSec;
    /**
     * keepalive hook触发间隔,单位秒，float类型
     * alive_interval=10.0
     */
    @JSONField(name = "hook.alive_interval")
    private Float hookAliveInterval;
    /**
     * hook通知失败重试次数,正整数。为0不重试，1时重试一次，以此类推
     * retry=1
     */
    @JSONField(name = "hook.retry")
    private Integer hookRetry;
    /**
     * hook通知失败重试延时，单位秒，float型
     * retry_delay=3.0
     */
    @JSONField(name = "hook.retry_delay")
    private Float hookRetryDelay;
    // ----------------------------------------------- cluster -----------------------------------------------
    /**
     * 设置源站拉流url模板, 格式跟printf类似，第一个%s指定app,第二个%s指定stream_id,
     * 开启集群模式后，on_stream_not_found和on_stream_none_reader hook将无效.
     * 溯源模式支持以下类型:
     * rtmp方式: rtmp://127.0.0.1:1935/%s/%s
     * rtsp方式: rtsp://127.0.0.1:554/%s/%s
     * hls方式: http://127.0.0.1:80/%s/%s/hls.m3u8
     * http-ts方式: http://127.0.0.1:80/%s/%s.live.ts
     * 支持多个源站，不同源站通过分号(;)分隔
     * origin_url=
     */
    @JSONField(name = "cluster.origin_url")
    private String clusterOriginUrl;
    /**
     * 溯源总超时时长，单位秒，float型；假如源站有3个，那么单次溯源超时时间为timeout_sec除以3
     * 单次溯源超时时间不要超过general.maxStreamWaitMS配置
     * timeout_sec=15
     */
    @JSONField(name = "cluster.timeout_sec")
    private Integer clusterTimeoutSec;
    /**
     * 溯源失败尝试次数，-1时永久尝试
     * retry_count=3
     */
    @JSONField(name = "cluster.retry_count")
    private Integer clusterRetryCount;
    // ----------------------------------------------- http -----------------------------------------------
    /**
     * http服务器字符编码，windows上默认gb2312
     * charSet=utf-8
     */
    @JSONField(name = "http.charSet")
    private String httpCharSet;
    /**
     * http链接超时时间
     * keepAliveSecond=30
     */
    @JSONField(name = "http.keepAliveSecond")
    private Integer httpKeepAliveSecond;
    /**
     * http请求体最大字节数，如果post的body太大，则不适合缓存body在内存
     * maxReqSize=40960
     */
    @JSONField(name = "http.maxReqSize")
    private Integer httpMaxReqSize;
    /**
     * 404网页内容，用户可以自定义404网页
     * notFound=<html><head><title>404 Not Found</title></head><body bgcolor="white"><center><h1>您访问的资源不存在！</h1></center><hr><center>ZLMediaKit-4.0</center></body></html>
     */
    @JSONField(name = "http.notFound")
    private String httpNotFound;
    /**
     * http服务器监听端口
     * port=80
     */
    @JSONField(name = "http.port")
    private Integer httpPort;
    /**
     * http文件服务器根目录
     * 可以为相对(相对于本可执行程序目录)或绝对路径
     * rootPath=./www
     */
    @JSONField(name = "http.rootPath")
    private String httpRootPath;
    /**
     * http文件服务器读文件缓存大小，单位BYTE，调整该参数可以优化文件io性能
     * sendBufSize=65536
     */
    @JSONField(name = "http.sendBufSize")
    private Integer httpSendBufSize;
    /**
     * https服务器监听端口
     * sslport=443
     */
    @JSONField(name = "http.sslport")
    private Integer httpSslPort;
    /**
     * 是否显示文件夹菜单，开启后可以浏览文件夹
     * dirMenu=1
     */
    @JSONField(name = "http.dirMenu")
    private Integer httpDirMenu;
    /**
     * 虚拟目录, 虚拟目录名和文件路径使用","隔开，多个配置路径间用";"隔开
     * 例如赋值为 app_a,/path/to/a;app_b,/path/to/b 那么
     * 访问 http://127.0.0.1/app_a/file_a 对应的文件路径为 /path/to/a/file_a
     * 访问 http://127.0.0.1/app_b/file_b 对应的文件路径为 /path/to/b/file_b
     * 访问其他http路径,对应的文件路径还是在rootPath内
     * virtualPath=
     */
    @JSONField(name = "http.virtualPath")
    private String httpVirtualPath;
    /**
     * 禁止后缀的文件使用mmap缓存，使用“,”隔开
     * 例如赋值为 .mp4,.flv
     * 那么访问后缀为.mp4与.flv 的文件不缓存
     * forbidCacheSuffix=
     */
    @JSONField(name = "http.forbidCacheSuffix")
    private String httpForbidCacheSuffix;
    /**
     * 可以把http代理前真实客户端ip放在http头中：https://github.com/ZLMediaKit/ZLMediaKit/issues/1388
     * 切勿暴露此key，否则可能导致伪造客户端ip
     * forwarded_ip_header=
     */
    @JSONField(name = "http.forwarded_ip_header")
    private String httpForwardedIpHeader;
    // ----------------------------------------------- multicast -----------------------------------------------
    /**
     * rtp组播截止组播ip地址
     * addrMax=239.255.255.255
     */
    @JSONField(name = "multicast.addrMax")
    private String multicastAddrMax;
    /**
     * rtp组播起始组播ip地址
     * addrMin=239.0.0.0
     */
    @JSONField(name = "multicast.addrMin")
    private String multicastAddrMin;
    /**
     * 组播udp ttl
     * udpTTL=64
     */
    @JSONField(name = "multicast.udpTTL")
    private Integer multicastUdpTtl;
    // ----------------------------------------------- record -----------------------------------------------
    /**
     * mp4录制或mp4点播的应用名，通过限制应用名，可以防止随意点播
     * 点播的文件必须放置在此文件夹下
     * appName=record
     */
    @JSONField(name = "record.appName")
    private String recordAppName;
    /**
     * mp4录制写文件缓存，单位BYTE,调整参数可以提高文件io性能
     * fileBufSize=65536
     */
    @JSONField(name = "record.fileBufSize")
    private Integer recordFileBufSize;
    /**
     * mp4录制保存、mp4点播根路径
     * 可以为相对(相对于本可执行程序目录)或绝对路径
     * filePath=./www
     */
    @JSONField(name = "record.filePath")
    private String recordFilePath;
    /**
     * mp4录制切片时间，单位秒
     * fileSecond=3600
     */
    @JSONField(name = "record.fileSecond")
    private Integer recordFileSecond;
    /**
     * mp4点播每次流化数据量，单位毫秒，
     * 减少该值可以让点播数据发送量更平滑，增大该值则更节省cpu资源
     * sampleMS=500
     */
    @JSONField(name = "record.sampleMS")
    private Integer recordSampleMs;
    /**
     * mp4录制完成后是否进行二次关键帧索引写入头部
     * fastStart=0
     */
    @JSONField(name = "record.fastStart")
    private Integer recordFastStart;
    /**
     * MP4点播(rtsp/rtmp/http-flv/ws-flv)是否循环播放文件
     * fileRepeat=0
     */
    @JSONField(name = "record.fileRepeat")
    private Integer recordFileRepeat;
    /**
     * MP4录制是否当做播放器参与播放人数统计
     * mp4_as_player=0
     */
    @JSONField(name = "record.mp4_as_player")
    private Integer recordMp4AsPlayer;
    // ----------------------------------------------- rtmp -----------------------------------------------
    /**
     * rtmp必须在此时间内完成握手，否则服务器会断开链接，单位秒
     * handshakeSecond=15
     */
    @JSONField(name = "rtmp.handshakeSecond")
    private Integer rtmpHandshakeSecond;
    /**
     * rtmp超时时间，如果该时间内未收到客户端的数据，
     * 或者tcp发送缓存超过这个时间，则会断开连接，单位秒
     * keepAliveSecond=15
     */
    @JSONField(name = "rtmp.keepAliveSecond")
    private Integer rtmpKeepAliveSecond;
    /**
     * 在接收rtmp推流时，是否重新生成时间戳(很多推流器的时间戳着实很烂)
     * modifyStamp=0
     */
    @JSONField(name = "rtmp.modifyStamp")
    private Integer rtmpModifyStamp;
    /**
     * rtmp服务器监听端口
     * port=1935
     */
    @JSONField(name = "rtmp.port")
    private Integer rtmpPort = 0;
    /**
     * rtmps服务器监听地址
     * sslport=0
     */
    @JSONField(name = "rtmp.sslport")
    private Integer rtmpSslPort = 0;
    // ----------------------------------------------- rtp -----------------------------------------------
    /**
     * 音频mtu大小，该参数限制rtp最大字节数，推荐不要超过1400
     * 加大该值会明显增加直播延时
     * audioMtuSize=600
     */
    @JSONField(name = "rtp.audioMtuSize")
    private Integer rtpAudioMtuSize;
    /**
     * 视频mtu大小，该参数限制rtp最大字节数，推荐不要超过1400
     * videoMtuSize=1400
     */
    @JSONField(name = "rtp.videoMtuSize")
    private Integer rtpVideoMtuSize;
    /**
     * rtp包最大长度限制，单位KB,主要用于识别TCP上下文破坏时，获取到错误的rtp
     * rtpMaxSize=10
     */
    @JSONField(name = "rtp.rtpMaxSize")
    private Integer rtpMaxSize;
    // ----------------------------------------------- rtp_proxy -----------------------------------------------
    /**
     * 导出调试数据(包括rtp/ps/h264)至该目录,置空则关闭数据导出
     * dumpDir=
     */
    @JSONField(name = "rtp_proxy.dumpDir")
    private String proxyDumpDir;
    /**
     * udp和tcp代理服务器，支持rtp(必须是ts或ps类型)代理
     * port=10000
     */
    @JSONField(name = "rtp_proxy.port")
    private Integer proxyPort;
    /**
     * rtp超时时间，单位秒
     * timeoutSec=15
     */
    @JSONField(name = "rtp_proxy.timeoutSec")
    private Integer proxyTimeoutSec;
    /**
     * 随机端口范围，最少确保36个端口
     * 该范围同时限制rtsp服务器udp端口范围
     * port_range=30000-35000
     */
    @JSONField(name = "rtp_proxy.port_range")
    private String proxyPortRange;
    /**
     * rtp h264 负载的pt
     * h264_pt=98
     */
    @JSONField(name = "rtp_proxy.h264_pt")
    private Integer proxyH264Pt;
    /**
     * rtp h265 负载的pt
     * h265_pt=99
     */
    @JSONField(name = "rtp_proxy.h265_pt")
    private Integer proxyH265Pt;
    /**
     * rtp ps 负载的pt
     * ps_pt=96
     */
    @JSONField(name = "rtp_proxy.ps_pt")
    private Integer proxyPsPt;
    /**
     * rtp ts 负载的pt
     * ts_pt=33
     */
    @JSONField(name = "rtp_proxy.ts_pt")
    private Integer proxyTsPt;
    /**
     * rtp opus 负载的pt
     * opus_pt=100
     */
    @JSONField(name = "rtp_proxy.opus_pt")
    private Integer proxyOpusPt;
    /**
     * rtp g711u 负载的pt
     * g711u_pt=0
     */
    @JSONField(name = "rtp_proxy.g711u_pt")
    private Integer proxyG711UPt;
    /**
     * rtp g711a 负载的pt
     * g711a_pt=8
     */
    @JSONField(name = "rtp_proxy.g711a_pt")
    private Integer proxyG711APt;
    // ----------------------------------------------- rtc -----------------------------------------------
    /**
     * rtc播放推流、播放超时时间
     * timeoutSec=15
     */
    @JSONField(name = "rtc.timeoutSec")
    private Integer rtcTimeoutSec;
    /**
     * 本机对rtc客户端的可见ip，作为服务器时一般为公网ip，可有多个，用','分开，当置空时，会自动获取网卡ip
     * 同时支持环境变量，以$开头，如"$EXTERN_IP"; 请参考：https://github.com/ZLMediaKit/ZLMediaKit/pull/1786
     * externIP=
     */
    @JSONField(name = "rtc.externIP")
    private String rtcExternIp;
    /**
     * rtc udp服务器监听端口号，所有rtc客户端将通过该端口传输stun/dtls/srtp/srtcp数据，
     * 该端口是多线程的，同时支持客户端网络切换导致的连接迁移
     * 需要注意的是，如果服务器在nat内，需要做端口映射时，必须确保外网映射端口跟该端口一致
     * port=8000
     */
    @JSONField(name = "rtc.port")
    private Integer rtcPort;
    /**
     * 设置remb比特率，非0时关闭twcc并开启remb。该设置在rtc推流时有效，可以控制推流画质
     * 目前已经实现twcc自动调整码率，关闭remb根据真实网络状况调整码率
     * rembBitRate=0
     */
    @JSONField(name = "rtc.rembBitRate")
    private Integer rtcRembBitRate;
    /**
     * rtc支持的音频codec类型,在前面的优先级更高
     * 以下范例为所有支持的音频codec
     * preferredCodecA=PCMU,PCMA,opus,mpeg4-generic
     */
    @JSONField(name = "rtc.preferredCodecA")
    private String rtcPreferredCodecA;
    /**
     * rtc支持的视频codec类型,在前面的优先级更高
     * 以下范例为所有支持的视频codec
     * preferredCodecV=H264,H265,AV1X,VP9,VP8
     */
    @JSONField(name = "rtc.preferredCodecV")
    private String rtcPreferredCodecV;
    // ----------------------------------------------- srt -----------------------------------------------
    /**
     * srt播放推流、播放超时时间,单位秒
     * timeoutSec=5
     */
    @JSONField(name = "srt.timeoutSec")
    private Integer srtTimeoutSec;
    /**
     * srt udp服务器监听端口号，所有srt客户端将通过该端口传输srt数据，
     * 该端口是多线程的，同时支持客户端网络切换导致的连接迁移
     * port=9000
     */
    @JSONField(name = "srt.port")
    private Integer srtPort;
    /**
     * srt 协议中延迟缓存的估算参数，在握手阶段估算rtt ,然后latencyMul*rtt 为最大缓存时长，此参数越大，表示等待重传的时长就越大
     * latencyMul=4
     */
    @JSONField(name = "srt.latencyMul")
    private Integer srtLatencyMul;
    /**
     * 包缓存的大小
     * pktBufSize=8192
     */
    @JSONField(name = "srt.pktBufSize")
    private Integer srtPktBufSize;
    // ----------------------------------------------- rtsp -----------------------------------------------
    /**
     * rtsp专有鉴权方式是采用base64还是md5方式
     * authBasic=0
     */
    @JSONField(name = "rtsp.authBasic")
    private Integer rtspAuthBasic;
    /**
     * rtsp拉流、推流代理是否是直接代理模式
     * 直接代理后支持任意编码格式，但是会导致GOP缓存无法定位到I帧，可能会导致开播花屏
     * 并且如果是tcp方式拉流，如果rtp大于mtu会导致无法使用udp方式代理
     * 假定您的拉流源地址不是264或265或AAC，那么你可以使用直接代理的方式来支持rtsp代理
     * 如果你是rtsp推拉流，但是webrtc播放，也建议关闭直接代理模式，
     * 因为直接代理时，rtp中可能没有sps pps,会导致webrtc无法播放; 另外webrtc也不支持Single NAL Unit Packets类型rtp
     * 默认开启rtsp直接代理，rtmp由于没有这些问题，是强制开启直接代理的
     * directProxy=1
     */
    @JSONField(name = "rtsp.directProxy")
    private Integer rtspDirectProxy;
    /**
     * rtsp必须在此时间内完成握手，否则服务器会断开链接，单位秒
     * handshakeSecond=15
     */
    @JSONField(name = "rtsp.handshakeSecond")
    private Integer rtspHandshakeSecond;
    /**
     * rtsp超时时间，如果该时间内未收到客户端的数据，
     * 或者tcp发送缓存超过这个时间，则会断开连接，单位秒
     * keepAliveSecond=15
     */
    @JSONField(name = "rtsp.keepAliveSecond")
    private Integer rtspKeepAliveSecond;
    /**
     * rtsp服务器监听地址
     * port=554
     */
    @JSONField(name = "rtsp.port")
    private Integer rtspPort = 0;
    /**
     * rtsps服务器监听地址
     * sslport=0
     */
    @JSONField(name = "rtsp.sslport")
    private Integer rtspSslPort = 0;
    // ----------------------------------------------- shell -----------------------------------------------
    /**
     * 调试telnet服务器接受最大bufffer大小
     * maxReqSize=1024
     */
    @JSONField(name = "shell.maxReqSize")
    private Integer shellMaxReqSize;
    /**
     * 调试telnet服务器监听端口
     * port=0
     */
    @JSONField(name = "shell.port")
    private Integer shellPort;

    public void refreshHook(String ip, String port, StreamMediaServerConfig server) {
        String host = ip + ":" + port;
        this.hookOnFlowReport = StrUtil.format("http://{}/index/hook/on_flow_report", host);
        this.hookOnHttpAccess = StrUtil.format("http://{}/index/hook/on_http_access", host);
        this.hookOnPlay = StrUtil.format("http://{}/index/hook/on_play", host);
        this.hookOnPublish = StrUtil.format("http://{}/index/hook/on_publish", host);
        this.hookOnRecordMp4 = StrUtil.format("http://{}/index/hook/on_record_mp4", host);
        this.hookOnRecordTs = StrUtil.format("http://{}/index/hook/on_record_ts", host);
        this.hookOnRtspAuth = StrUtil.format("http://{}/index/hook/on_rtsp_auth", host);
        this.hookOnRtspRealm = StrUtil.format("http://{}/index/hook/on_rtsp_realm", host);
        this.hookOnShellLogin = StrUtil.format("http://{}/index/hook/on_shell_login", host);
        this.hookOnStreamChanged = StrUtil.format("http://{}/index/hook/on_stream_changed", host);
        this.hookOnStreamNoneReader = StrUtil.format("http://{}/index/hook/on_stream_none_reader", host);
        this.hookOnStreamNotFound = StrUtil.format("http://{}/index/hook/on_stream_not_found", host);
        this.hookOnServerStarted = StrUtil.format("http://{}/index/hook/on_server_started", host);
        this.hookOnServerKeepalive = StrUtil.format("http://{}/index/hook/on_server_keepalive", host);
//        this.hookOnSendRtpStopped = StrUtil.format("http://{}/index/hook/on_send_rtp_stopped", host);
        this.hookOnSendRtpStopped = "";
    }
}
