package com.dite.znpt.monitor.domain.vo.video;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 视频播放响应
 * @author huise23
 * @since 2024-11-26 14:03:41
 */
@Data
@Builder
public class VideoPayResp {

    @ApiModelProperty(value = "播放方式")
    private String mediaType;

    @ApiModelProperty(value = "流媒体播放地址")
    private List<StreamMediaFormat> streamMediaFormatList;

}
