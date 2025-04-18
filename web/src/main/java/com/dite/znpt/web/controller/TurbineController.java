package com.dite.znpt.web.controller;


import com.dite.znpt.domain.Constants;
import com.dite.znpt.domain.vo.TurbineListReq;
import com.dite.znpt.domain.vo.TurbineResp;
import com.dite.znpt.domain.entity.TurbineEntity;
import com.dite.znpt.service.TurbineService;
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
@Api(tags = "机组-项目关联")
@RestController
@RequestMapping("/turbine")
public class TurbineController {
    @Resource
    private TurbineService turbineService;

    @ApiOperation(value = "获取机组-项目关联列表", httpMethod = "GET")
    @GetMapping("/list")
    public PageResult<TurbineResp> list(TurbineListReq turbineReq) {
        return PageResult.ok(turbineService.selectList(turbineReq));
    }

    @ApiOperation(value = "根据机组-项目关联Id获取详细信息", httpMethod = "GET")
    @GetMapping("/{turbineCode}")
    public Result<TurbineResp> getInfo(@PathVariable String turbineCode) {
        return Result.ok(turbineService.selectById(turbineCode));
    }

    @ApiOperation(value = "新增机组-项目关联", httpMethod = "POST")
    @PostMapping
    public Result<Object> add(@RequestBody TurbineEntity turbine) {
        turbineService.saveData(turbine);
        return Result.ok();
    }

    @ApiOperation(value = "修改机组-项目关联", httpMethod = "PUT")
    @PutMapping
    public Result<Object> edit(@RequestBody TurbineEntity turbine) {
        turbineService.updateData(turbine);
        return Result.ok();
    }

    @ApiOperation(value = "删除机组-项目关联", httpMethod = "DELETE")
    @DeleteMapping("/{turbineCode}")
    public Result<Object> remove(@PathVariable String turbineCode) {
        turbineService.deleteById(turbineCode);
        return Result.ok();
    }

    @ApiOperation(value = "导出机组-项目关联", httpMethod = "GET")
    @GetMapping("/export")
    @ResponseExcel(name = "机组-项目关联")
    public List<TurbineResp> export(TurbineListReq turbineReq) {
        return turbineService.selectList(turbineReq);
    }

    @ApiOperation(value = "导入机组-项目关联", httpMethod = "POST")
    @PostMapping("/import")
    public Result<Object> importData(@RequestExcel List<TurbineEntity> dataList, BindingResult bindingResult) {
        // JSR 303 校验通用校验获取失败的数据
        List<ErrorMessage> errorMessageList = (List<ErrorMessage>) bindingResult.getTarget();
        if (errorMessageList != null && !errorMessageList.isEmpty()) {
            return Result.error(Constants.SERVICE_EXCEPTION, "导入失败");
        }
        return Result.okM("导入"+dataList.size()+"条数据");
    }
}

