package com.dite.znpt.controller;


import com.dite.znpt.domain.vo.DefectListReq;
import com.dite.znpt.domain.vo.DefectResp;
import com.dite.znpt.domain.entity.DefectEntity;
import com.dite.znpt.service.DefectService;
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
@Api(tags = "缺陷记录")
@RestController
@RequestMapping("/defect")
public class DefectController {
    @Resource
    private DefectService defectService;

    @ApiOperation(value = "获取缺陷记录列表", httpMethod = "GET")
    @GetMapping("/list")
    public Result<PageInfo<DefectResp>> list(DefectListReq defectReq) {
        PageInfo<DefectResp> page = defectService.selectList(defectReq);
        return Result.ok(page);
    }

    @ApiOperation(value = "根据缺陷记录Id获取详细信息", httpMethod = "GET")
    @GetMapping("/{defectId}")
    public Result<DefectResp> getInfo(@PathVariable String defectId) {
        return Result.ok(defectService.selectById(defectId));
    }

    @ApiOperation(value = "新增缺陷记录", httpMethod = "POST")
    @PostMapping
    public Result<Object> add(@RequestBody DefectEntity defect) {
        defectService.saveData(defect);
        return Result.ok();
    }

    @ApiOperation(value = "修改缺陷记录", httpMethod = "PUT")
    @PutMapping
    public Result<Object> edit(@RequestBody DefectEntity defect) {
        defectService.updateData(defect);
        return Result.ok();
    }

    @ApiOperation(value = "删除缺陷记录", httpMethod = "DELETE")
    @DeleteMapping("/{defectId}")
    public Result<Object> remove(@PathVariable String defectId) {
        defectService.deleteById(defectId);
        return Result.ok();
    }
    
}

