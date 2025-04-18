package com.dite.znpt.monitor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dite.znpt.monitor.domain.entity.DeviceVideoEntity;
import com.dite.znpt.monitor.domain.vo.video.DeviceVideoListResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: huise23
 * @Date: 2022/8/11 18:09
 * @Description:
 */
public interface DeviceVideoMapper extends BaseMapper<DeviceVideoEntity> {
    /**
    * 条件查询视频设备列表
    * @param status 是否在线
    * @param keyword 设备名称或者编码
    * @return {@link List< DeviceVideoListResp>}
    */
    List<DeviceVideoListResp> selectDeviceVideoList(@Param("status") String status, @Param("keyword") String keyword, @Param("hostAddress") String hostAddress);

}
