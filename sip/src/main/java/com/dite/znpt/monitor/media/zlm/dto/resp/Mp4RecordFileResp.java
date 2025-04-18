package com.dite.znpt.monitor.media.zlm.dto.resp;

import lombok.Data;

import java.util.List;

/**
 * @Author: huise23
 * @Date: 2022/8/29 13:08
 * @Description:
 */
@Data
public class Mp4RecordFileResp {
    /**
     * 文件列表
     */
    private List<String> paths;
    /**
     * 根路径
     */
    private String rootPath;
}

