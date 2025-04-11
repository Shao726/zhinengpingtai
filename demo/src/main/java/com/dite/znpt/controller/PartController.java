package com.dite.znpt.controller;


import com.dite.znpt.domain.Constants;
import com.dite.znpt.domain.vo.PartListReq;
import com.dite.znpt.domain.vo.PartResp;
import com.dite.znpt.domain.entity.PartEntity;
import com.dite.znpt.service.PartService;
import com.dite.znpt.domain.Result;
import com.dite.znpt.domain.PageResult;
import com.pig4cloud.plugin.excel.annotation.RequestExcel;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.pig4cloud.plugin.excel.vo.ErrorMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 */
@Api(tags = "")
@RestController
@RequestMapping("/part")
public class PartController {
    @Resource
    private PartService partService;

    @ApiOperation(value = "获取列表", httpMethod = "GET")
    @GetMapping("/list")
    public PageResult<PartResp> list(PartListReq partReq) {
        return PageResult.ok(partService.selectList(partReq));
    }

    @ApiOperation(value = "根据Id获取详细信息", httpMethod = "GET")
    @GetMapping("/{partId}")
    public Result<PartResp> getInfo(@PathVariable String partId) {
        return Result.ok(partService.selectById(partId));
    }

    @ApiOperation(value = "新增", httpMethod = "POST")
    @PostMapping
    public Result<Object> add(@RequestBody PartEntity part) {
        partService.saveData(part);
        return Result.ok();
    }

    @ApiOperation(value = "修改", httpMethod = "PUT")
    @PutMapping
    public Result<Object> edit(@RequestBody PartEntity part) {
        partService.updateData(part);
        return Result.ok();
    }

    @ApiOperation(value = "删除", httpMethod = "DELETE")
    @DeleteMapping("/{partId}")
    public Result<Object> remove(@PathVariable String partId) {
        partService.deleteById(partId);
        return Result.ok();
    }

    @ApiOperation(value = "导出", httpMethod = "GET")
    @GetMapping("/export")
    @ResponseExcel(name = "")
    public List<PartResp> export(PartListReq partReq) {
        return partService.selectList(partReq);
    }

    @ApiOperation(value = "导入", httpMethod = "POST")
    @PostMapping("/import")
    public Result<Object> importData(@RequestExcel List<PartEntity> dataList, BindingResult bindingResult) {
        // JSR 303 校验通用校验获取失败的数据
        List<ErrorMessage> errorMessageList = (List<ErrorMessage>) bindingResult.getTarget();
        if (errorMessageList != null && !errorMessageList.isEmpty()) {
            return Result.error(Constants.SERVICE_EXCEPTION, "导入失败");
        }
        return Result.okM("导入"+dataList.size()+"条数据");
    }
}

