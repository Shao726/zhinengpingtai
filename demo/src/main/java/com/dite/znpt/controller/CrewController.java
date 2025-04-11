package com.dite.znpt.controller;


import com.dite.znpt.domain.Constants;
import com.dite.znpt.domain.vo.CrewListReq;
import com.dite.znpt.domain.vo.CrewResp;
import com.dite.znpt.domain.entity.CrewEntity;
import com.dite.znpt.service.CrewService;
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
@Api(tags = "机组信息")
@RestController
@RequestMapping("/crew")
public class CrewController {
    @Resource
    private CrewService crewService;

    @ApiOperation(value = "获取机组信息列表", httpMethod = "GET")
    @GetMapping("/list")
    public PageResult<CrewResp> list(CrewListReq crewReq) {
        return PageResult.ok(crewService.selectList(crewReq));
    }

    @ApiOperation(value = "根据机组信息Id获取详细信息", httpMethod = "GET")
    @GetMapping("/{crewId}")
    public Result<CrewResp> getInfo(@PathVariable String crewId) {
        return Result.ok(crewService.selectById(crewId));
    }

    @ApiOperation(value = "新增机组信息", httpMethod = "POST")
    @PostMapping
    public Result<Object> add(@RequestBody CrewEntity crew) {
        crewService.saveData(crew);
        return Result.ok();
    }

    @ApiOperation(value = "修改机组信息", httpMethod = "PUT")
    @PutMapping
    public Result<Object> edit(@RequestBody CrewEntity crew) {
        crewService.updateData(crew);
        return Result.ok();
    }

    @ApiOperation(value = "删除机组信息", httpMethod = "DELETE")
    @DeleteMapping("/{crewId}")
    public Result<Object> remove(@PathVariable String crewId) {
        crewService.deleteById(crewId);
        return Result.ok();
    }

    @ApiOperation(value = "导出机组信息", httpMethod = "GET")
    @GetMapping("/export")
    @ResponseExcel(name = "机组信息")
    public List<CrewResp> export(CrewListReq crewReq) {
        return crewService.selectList(crewReq);
    }

    @ApiOperation(value = "导入机组信息", httpMethod = "POST")
    @PostMapping("/import")
    public Result<Object> importData(@RequestExcel List<CrewEntity> dataList, BindingResult bindingResult) {
        // JSR 303 校验通用校验获取失败的数据
        List<ErrorMessage> errorMessageList = (List<ErrorMessage>) bindingResult.getTarget();
        if (errorMessageList != null && !errorMessageList.isEmpty()) {
            return Result.error(Constants.SERVICE_EXCEPTION, "导入失败");
        }
        return Result.okM("导入"+dataList.size()+"条数据");
    }
}

