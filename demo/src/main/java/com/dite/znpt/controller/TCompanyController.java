package com.dite.znpt.controller;


import com.dite.znpt.domain.vo.TCompanyListReq;
import com.dite.znpt.domain.vo.TCompanyResp;
import com.dite.znpt.domain.entity.TCompanyEntity;
import com.dite.znpt.service.TCompanyService;
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
@Api(tags = "公司信息")
@RestController
@RequestMapping("/t-company")
public class TCompanyController {
    @Resource
    private TCompanyService tCompanyService;

    @ApiOperation(value = "获取公司信息列表", httpMethod = "GET")
    @GetMapping("/list")
    public Result<PageInfo<TCompanyResp>> list(TCompanyListReq tCompanyReq) {
        PageInfo<TCompanyResp> page = tCompanyService.selectList(tCompanyReq);
        return Result.ok(page);
    }

    @ApiOperation(value = "根据公司信息Id获取详细信息", httpMethod = "GET")
    @GetMapping("/{companyId}")
    public Result<TCompanyResp> getInfo(@PathVariable String companyId) {
        return Result.ok(tCompanyService.selectById(companyId));
    }

    @ApiOperation(value = "新增公司信息", httpMethod = "POST")
    @PostMapping
    public Result<Object> add(@RequestBody TCompanyEntity tCompany) {
        tCompanyService.saveData(tCompany);
        return Result.ok();
    }

    @ApiOperation(value = "修改公司信息", httpMethod = "PUT")
    @PutMapping
    public Result<Object> edit(@RequestBody TCompanyEntity tCompany) {
        tCompanyService.updateData(tCompany);
        return Result.ok();
    }

    @ApiOperation(value = "删除公司信息", httpMethod = "DELETE")
    @DeleteMapping("/{companyId}")
    public Result<Object> remove(@PathVariable String companyId) {
        tCompanyService.deleteById(companyId);
        return Result.ok();
    }
    
}

