package com.dite.znpt.controller;


import com.dite.znpt.domain.vo.PersonnelListReq;
import com.dite.znpt.domain.vo.PersonnelResp;
import com.dite.znpt.domain.entity.PersonnelEntity;
import com.dite.znpt.service.PersonnelService;
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
@Api(tags = "人员信息")
@RestController
@RequestMapping("/personnel")
public class PersonnelController {
    @Resource
    private PersonnelService personnelService;

    @ApiOperation(value = "获取人员信息列表", httpMethod = "GET")
    @GetMapping("/list")
    public Result<PageInfo<PersonnelResp>> list(PersonnelListReq personnelReq) {
        PageInfo<PersonnelResp> page = personnelService.selectList(personnelReq);
        return Result.ok(page);
    }

    @ApiOperation(value = "根据人员信息Id获取详细信息", httpMethod = "GET")
    @GetMapping("/{personId}")
    public Result<PersonnelResp> getInfo(@PathVariable String personId) {
        return Result.ok(personnelService.selectById(personId));
    }

    @ApiOperation(value = "新增人员信息", httpMethod = "POST")
    @PostMapping
    public Result<Object> add(@RequestBody PersonnelEntity personnel) {
        personnelService.saveData(personnel);
        return Result.ok();
    }

    @ApiOperation(value = "修改人员信息", httpMethod = "PUT")
    @PutMapping
    public Result<Object> edit(@RequestBody PersonnelEntity personnel) {
        personnelService.updateData(personnel);
        return Result.ok();
    }

    @ApiOperation(value = "删除人员信息", httpMethod = "DELETE")
    @DeleteMapping("/{personId}")
    public Result<Object> remove(@PathVariable String personId) {
        personnelService.deleteById(personId);
        return Result.ok();
    }
    
}

