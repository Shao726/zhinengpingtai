package com.dite.znpt.monitor.media.zlm.dto.event;

import cn.hutool.core.lang.Dict;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: huise23
 * @Date: 2022/8/30 9:10
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HttpAccessReq extends BaseEventReq {
    /**
     * http客户端请求header
     */
    private Dict header;
    /**
     * 访问路径是文件还是目录
     */
    @JSONField(name = "is_dir")
    private Boolean isDir;
    /**
     * 请求访问的文件或目录
     */
    private String path;
}
