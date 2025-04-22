package com.dite.znpt.domain;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询结果
 */
@Data
public class PageResult<T> {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("状态码")
    private Integer code;
    @ApiModelProperty("返回内容")
    private String msg;
    @ApiModelProperty("对象列表")
    private List<T> rows;
    @ApiModelProperty("数据对象")
    private Long total;

    public PageResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public PageResult(int code, String msg, List<T> rows, Long total) {
        this.code = code;
        this.msg = msg;
        this.rows = rows;
        this.total = total;
    }

    public static <T> PageResult<T> ok(List<T> rows) {
        return ok(rows, (new PageInfo(rows)).getTotal());
    }

    public static <T> PageResult<T> ok(List<T> rows, Long total) {
        return new PageResult(200, "操作成功", rows, total);
    }

    public static <T> PageResult<T> ok(List<T> rows, Integer total) {
        return new PageResult(200, "操作成功", rows, (long)total);
    }

    public static <T> PageResult<T> error() {
        return error("操作失败");
    }

    public static <T> PageResult<T> error(String msg) {
        return new PageResult(500, msg);
    }

}

