package com.dite.znpt.controller;


import com.dite.znpt.domain.vo.CombinedDictListReq;
import com.dite.znpt.domain.vo.CombinedDictResp;
import com.dite.znpt.domain.entity.CombinedDictEntity;
import com.dite.znpt.service.CombinedDictService;
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
@Api(tags = "字典")
@RestController
@RequestMapping("/combined-dict")
public class CombinedDictController {
    @Resource
    private CombinedDictService combinedDictService;

    @ApiOperation(value = "获取字典列表", httpMethod = "GET")
    @GetMapping("/list")
    public Result<PageInfo<CombinedDictResp>> list(CombinedDictListReq combinedDictReq) {
        PageInfo<CombinedDictResp> page = combinedDictService.selectList(combinedDictReq);
        return Result.ok(page);
    }

    @ApiOperation(value = "根据字典Id获取详细信息", httpMethod = "GET")
    @GetMapping("/{dictId}")
    public Result<CombinedDictResp> getInfo(@PathVariable String dictId) {
        return Result.ok(combinedDictService.selectById(dictId));
    }

    @ApiOperation(value = "新增字典", httpMethod = "POST")
    @PostMapping
    public Result<Object> add(@RequestBody CombinedDictEntity combinedDict) {
        combinedDictService.saveData(combinedDict);
        return Result.ok();
    }

    @ApiOperation(value = "修改字典", httpMethod = "PUT")
    @PutMapping
    public Result<Object> edit(@RequestBody CombinedDictEntity combinedDict) {
        combinedDictService.updateData(combinedDict);
        return Result.ok();
    }

    @ApiOperation(value = "删除字典", httpMethod = "DELETE")
    @DeleteMapping("/{dictId}")
    public Result<Object> remove(@PathVariable String dictId) {
        combinedDictService.deleteById(dictId);
        return Result.ok();
    }
    
}

