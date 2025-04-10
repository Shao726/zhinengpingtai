package com.dite.znpt.controller;


import com.dite.znpt.domain.vo.TConstructionListReq;
import com.dite.znpt.domain.vo.TConstructionResp;
import com.dite.znpt.domain.entity.TConstructionEntity;
import com.dite.znpt.service.TConstructionService;
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
@Api(tags = "施工信息")
@RestController
@RequestMapping("/t-construction")
public class TConstructionController {
    @Resource
    private TConstructionService tConstructionService;

    @ApiOperation(value = "获取施工信息列表", httpMethod = "GET")
    @GetMapping("/list")
    public Result<PageInfo<TConstructionResp>> list(TConstructionListReq tConstructionReq) {
        PageInfo<TConstructionResp> page = tConstructionService.selectList(tConstructionReq);
        return Result.ok(page);
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
    
}

