package com.dite.znpt.web.controller;

import cn.hutool.core.bean.BeanUtil;
import com.dite.znpt.domain.PageResult;
import com.dite.znpt.domain.Result;
import com.dite.znpt.monitor.constant.IotRespMessage;
import com.dite.znpt.monitor.constant.dict.DeviceStatus;
import com.dite.znpt.monitor.constant.dict.SipTransferMode;
import com.dite.znpt.monitor.constant.dict.StreamTransferMode;
import com.dite.znpt.monitor.domain.entity.DeviceVideoEntity;
import com.dite.znpt.monitor.domain.req.MonitorConfigAddReq;
import com.dite.znpt.monitor.domain.resp.DeviceVideoResp;
import com.dite.znpt.monitor.domain.vo.video.DeviceVideoChannelEditReq;
import com.dite.znpt.monitor.domain.vo.video.DeviceVideoChannelListResp;
import com.dite.znpt.monitor.domain.vo.video.DeviceVideoEditReq;
import com.dite.znpt.monitor.domain.vo.video.DeviceVideoListResp;
import com.dite.znpt.monitor.media.zlm.config.StreamMediaServerConfig;
import com.dite.znpt.monitor.service.DeviceVideoChannelService;
import com.dite.znpt.monitor.service.DeviceVideoService;
import com.dite.znpt.monitor.service.IpConfigService;
import com.dite.znpt.monitor.sip.config.SipConfig;
import com.dite.znpt.monitor.sip.transmit.cmd.ISipDeviceCommander;
import com.dite.znpt.monitor.utils.DictUtils;
import com.dite.znpt.util.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: huise23
 * @Date: 2022/8/8 10:39
 * @Description:
 */
@Api(tags = "视频管理")
@RestController
@RequestMapping("/monitoring/video")
public class VideoController {

    @Resource
    private SipConfig sipConfig;

    @Resource
    private StreamMediaServerConfig streamMediaServerConfigService;

    @Resource
    private DeviceVideoService deviceVideoService;

    @Resource
    private IpConfigService ipConfigService;

    @Resource
    private DeviceVideoChannelService deviceVideoChannelService;

    @Resource
    private ISipDeviceCommander sipDeviceCommander;

    @ApiOperation(value = "获取信令服务器配置信息", notes = "iot:video:sip:view", httpMethod = "GET")
    @GetMapping("/sipServerConfig")
    public Result getSipServerConfig(){
        return Result.ok(BeanUtil.copyProperties(sipConfig, SipConfig.class));
    }

    @ApiOperation(value = "获取流媒体服务配置信息", notes = "iot:video:media:view", httpMethod = "GET")
    @GetMapping("/streamMediaServerConfig")
    public Result getStreamMediaServerConfig(){
        return Result.ok(BeanUtil.copyProperties(streamMediaServerConfigService, StreamMediaServerConfig.class));
    }

    @ApiOperation(value = "查询监控IP配置列表", notes = "iot:config:list", httpMethod = "GET")
    @GetMapping("/config/list")
    public Result<List<String>> configList(){
        PageUtil.startPage();
        return Result.ok(ipConfigService.configList());
    }

    @ApiOperation(value = "配置监控IP列表,每次全量传ip列表", notes = "iot:config:add", httpMethod = "POST")
    @PostMapping("/config/add")
    public Result configAdd(@RequestBody MonitorConfigAddReq req){
        ipConfigService.configAdd(req);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询视频设备列表", notes = "iot:video:list", httpMethod = "GET")
    @GetMapping("/device/page")
    public PageResult<DeviceVideoListResp> pageDevice(
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "hostAddress", required = false) String hostAddress){
        PageUtil.startPage();
        return deviceVideoService.selectDeviceVideoList(status, keyword,hostAddress);
    }

    @ApiOperation(value = "查看视频设备数量", notes = "iot:video:list", httpMethod = "GET")
    @GetMapping("/device/count")
    public Result countDevice(){
        return Result.ok(deviceVideoService.countDeviceVideoNum());
    }

    @ApiOperation(value = "查看视频设备详情", notes = "iot:video:view", httpMethod = "GET")
    @GetMapping("/device/{videoId}")
    public Result viewDevice(@PathVariable("videoId") Long videoId){
        final DeviceVideoEntity entity = deviceVideoService.getById(videoId);
        final DeviceVideoResp resp = BeanUtil.copyProperties(entity, DeviceVideoResp.class);
        resp.setStatusLabel(DictUtils.getDictLabel(DeviceStatus.class, resp.getStatus()));
        resp.setStreamModeLabel(DictUtils.getDictLabel(StreamTransferMode.class, resp.getStreamMode()));
        resp.setTransportLabel(DictUtils.getDictLabel(SipTransferMode.class, resp.getTransport()));
        return Result.ok(resp);
    }

    @ApiOperation(value = "编辑视频设备", notes = "iot:video:edit", httpMethod = "PUT")
    @PutMapping("/device/{videoId}")
    public Result editDevice(@PathVariable("videoId") Long videoId, @RequestBody DeviceVideoEditReq req){
        deviceVideoService.editDeviceVideo(videoId, req);
        return Result.ok();
    }

    @ApiOperation(value = "更新视频设备", notes = "iot:video:sync", httpMethod = "PUT")
    @PutMapping("/device/sync/{videoId}")
    public Result syncDevice(@PathVariable("videoId") Long videoId){
        DeviceVideoEntity entity = deviceVideoService.getById(videoId);
        if(DeviceStatus.ONLINE.getValue().equals(entity.getStatus())){
            sipDeviceCommander.queryCatalog(entity);
            return Result.ok();
        }else {
            return Result.error(IotRespMessage.DEVICE_VIDEO_CANNOT_SYNC);
        }
    }

    @ApiOperation(value = "删除视频设备", notes = "iot:video:delete", httpMethod = "DELETE")
    @DeleteMapping("/device/{videoId}")
    public Result deleteDevice(@PathVariable("videoId") Long videoId){
        return deviceVideoService.removeByVideoId(videoId);
    }

    @ApiOperation(value = "分页查询视频通道列表", notes = "iot:video:channel:list", httpMethod = "GET")
    @GetMapping("/channel/page/{videoId}")
    public PageResult<DeviceVideoChannelListResp> pageChannel(@PathVariable("videoId") Long videoId, @RequestParam(value = "keyword", required = false) String keyword){
        return deviceVideoChannelService.selectDeviceVideoChannel(videoId, keyword);
    }

    @ApiOperation(value = "分页查询所有视频通道列表", notes = "iot:video:channel:list", httpMethod = "GET")
    @GetMapping("/channel/page")
    public PageResult<DeviceVideoChannelListResp> pageAllChannel(@RequestParam(value = "keyword", required = false) String keyword){
        return deviceVideoChannelService.selectAllDeviceVideoChannel(keyword);
    }

    @ApiOperation(value = "查看视频通道", notes = "iot:video:channel:view", httpMethod = "GET")
    @GetMapping("/channel/{channelCode}")
    public Result viewChannel(@PathVariable("channelCode") String channelCode){
        return Result.ok(deviceVideoChannelService.getDeviceVideoChannelDetail(channelCode));
    }

    @ApiOperation(value = "编辑视频通道", notes = "iot:video:channel:edit", httpMethod = "PUT")
    @PutMapping("/channel/{channelId}")
    public Result editChannel(@PathVariable("channelId") Long channelId, @RequestBody DeviceVideoChannelEditReq req){
        deviceVideoChannelService.editDeviceVideoChannel(channelId, req);
        return Result.ok();
    }

    @ApiOperation(value = "删除视频通道", notes = "iot:video:channel:delete", httpMethod = "DELETE")
    @DeleteMapping("/channel/{channelId}")
    public Result deleteChannel(@PathVariable("channelId") Long channelId){
        return deviceVideoChannelService.removeByChannelIds(Arrays.asList(channelId));
    }

    @ApiOperation(value = "播放直播视频", notes = "iot:video:play", httpMethod = "GET")
    @GetMapping("/play/live/{channelCode}")
    public Result play(@PathVariable("channelCode") String channelCode){
        return Result.ok(deviceVideoChannelService.play(channelCode));
    }

    @ApiOperation(value = "查询视频设备是否在线", notes = "iot:video:isOnline", httpMethod = "GET")
    @GetMapping("/channel/isOnline/{channelCode}")
    public Result isOnline(@PathVariable("channelCode") String channelCode){
        return Result.ok(deviceVideoChannelService.isOnline(channelCode));
    }

    @ApiOperation(value = "停止播放直播", notes = "iot:video:stop", httpMethod = "GET")
    @GetMapping("/stop/live/{channelCode}")
    public Result stop(@PathVariable("channelCode") String channelCode){
        deviceVideoChannelService.stop(channelCode);
        return Result.ok();
    }
}
