package com.dite.znpt.monitor.media.zlm.dto.resp;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.List;

/**
 * @Author: huise23
 * @Date: 2022/8/29 10:33
 * @Description: 请求响应基类
 */
@Data
public class BaseResp {
    /**
     * code == 0时代表完全成功
     */
    private Integer code;
    /**
     * 失败提示
     */
    private String msg;
    /**
     * 失败具体原因
     */
    private String result;
    /**
     * 返回数据
     */
    private String data;
    /**
     * 配置项变更个数
     */
    private Integer changed;
    /**
     * false:未录制,true:正在录制
     */
    private Boolean status;
    /**
     * 接收端口，方便获取随机端口号
     */
    private Integer port;
    /**
     * 是否找到记录并关闭
     */
    private Integer hit;

    public String getMsg() {
        return StrUtil.format("{}:{}", code, msg);
    }

    public Boolean isSuccess() {
        return code == 0;
    }

    public <T> T getData(Class<T> clazz) {
        return JSON.parseObject(data, clazz);
    }

    public <T> List<T> getList(Class<T> clazz) {
        return JSON.parseArray(data, clazz);
    }
}
