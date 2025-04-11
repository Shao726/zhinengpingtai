package com.dite.znpt.controller;


import com.dite.znpt.domain.Constants;
import com.dite.znpt.domain.vo.TConstructionListReq;
import com.dite.znpt.domain.vo.TConstructionResp;
import com.dite.znpt.domain.entity.TConstructionEntity;
import com.dite.znpt.service.TConstructionService;
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
@Api(tags = "施工信息")
@RestController
@RequestMapping("/t-construction")
public class TConstructionController {
    @Resource
    private TConstructionService tConstructionService;

    @ApiOperation(value = "获取施工信息列表", httpMethod = "GET")
    @GetMapping("/list")
    public PageResult<TConstructionResp> list(TConstructionListReq tConstructionReq) {
        return PageResult.ok(tConstructionService.selectList(tConstructionReq));
    }

    @ApiOperation(value = "根据施工信息Id获取详细信息", httpMethod = "GET")
    @GetMapping("/{constructionId}")
    public Result<TConstructionResp> getInfo(@PathVariable String constructionId) {
        return Result.ok(tConstructionService.selectById(constructionId));
    }

    @ApiOperation(value = "新增施工信息", httpMethod = "POST")
    @PostMapping
    public Result<Object> add(@RequestBody TConstructionEntity tConstruction) {
        tConstructionService.saveData(tConstruction);
        return Result.ok();
    }

    @ApiOperation(value = "修改施工信息", httpMethod = "PUT")
    @PutMapping
    public Result<Object> edit(@RequestBody TConstructionEntity tConstruction) {
        tConstructionService.updateData(tConstruction);
        return Result.ok();
    }

    @ApiOperation(value = "删除施工信息", httpMethod = "DELETE")
    @DeleteMapping("/{constructionId}")
    public Result<Object> remove(@PathVariable String constructionId) {
        tConstructionService.deleteById(constructionId);
        return Result.ok();
    }

    @ApiOperation(value = "导出施工信息", httpMethod = "GET")
    @GetMapping("/export")
    @ResponseExcel(name = "施工信息")
    public List<TConstructionResp> export(TConstructionListReq tConstructionReq) {
        return tConstructionService.selectList(tConstructionReq);
    }

    @ApiOperation(value = "导入施工信息", httpMethod = "POST")
    @PostMapping("/import")
    public Result<Object> importData(@RequestExcel List<TConstructionEntity> dataList, BindingResult bindingResult) {
        // JSR 303 校验通用校验获取失败的数据
        List<ErrorMessage> errorMessageList = (List<ErrorMessage>) bindingResult.getTarget();
        if (errorMessageList != null && !errorMessageList.isEmpty()) {
            return Result.error(Constants.SERVICE_EXCEPTION, "导入失败");
        }
        return Result.okM("导入"+dataList.size()+"条数据");
    }
}

