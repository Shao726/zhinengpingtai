package com.dite.znpt.controller;


import com.dite.znpt.domain.vo.TurbineListReq;
import com.dite.znpt.domain.vo.TurbineResp;
import com.dite.znpt.domain.entity.TurbineEntity;
import com.dite.znpt.service.TurbineService;
import com.dite.znpt.domain.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huise23
 * @date 2025/04/09 14:37
 */
@Api(tags = "机组-项目关联")
@RestController
@RequestMapping("/turbine")
public class TurbineController {
    @Resource
    private TurbineService turbineService;

    @ApiOperation(value = "获取机组-项目关联列表", httpMethod = "GET")
    @GetMapping("/list")
    public Result<PageInfo<TurbineResp>> list(TurbineListReq turbineReq) {
        PageInfo<TurbineResp> page = turbineService.selectList(turbineReq);
        return Result.ok(page);
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
    
}

