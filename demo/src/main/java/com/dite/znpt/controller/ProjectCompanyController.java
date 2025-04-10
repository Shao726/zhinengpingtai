package com.dite.znpt.controller;


import com.dite.znpt.domain.vo.ProjectCompanyListReq;
import com.dite.znpt.domain.vo.ProjectCompanyResp;
import com.dite.znpt.domain.entity.ProjectCompanyEntity;
import com.dite.znpt.service.ProjectCompanyService;
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
@Api(tags = "项目-公司关联信息")
@RestController
@RequestMapping("/project-company")
public class ProjectCompanyController {
    @Resource
    private ProjectCompanyService projectCompanyService;

    @ApiOperation(value = "获取项目-公司关联信息列表", httpMethod = "GET")
    @GetMapping("/list")
    public Result<PageInfo<ProjectCompanyResp>> list(ProjectCompanyListReq projectCompanyReq) {
        PageInfo<ProjectCompanyResp> page = projectCompanyService.selectList(projectCompanyReq);
        return Result.ok(page);
    }

    @ApiOperation(value = "根据项目-公司关联信息Id获取详细信息", httpMethod = "GET")
    @GetMapping("/{projectId}")
    public Result<ProjectCompanyResp> getInfo(@PathVariable String projectId) {
        return Result.ok(projectCompanyService.selectById(projectId));
    }

    @ApiOperation(value = "新增项目-公司关联信息", httpMethod = "POST")
    @PostMapping
    public Result<Object> add(@RequestBody ProjectCompanyEntity projectCompany) {
        projectCompanyService.saveData(projectCompany);
        return Result.ok();
    }

    @ApiOperation(value = "修改项目-公司关联信息", httpMethod = "PUT")
    @PutMapping
    public Result<Object> edit(@RequestBody ProjectCompanyEntity projectCompany) {
        projectCompanyService.updateData(projectCompany);
        return Result.ok();
    }

    @ApiOperation(value = "删除项目-公司关联信息", httpMethod = "DELETE")
    @DeleteMapping("/{projectId}")
    public Result<Object> remove(@PathVariable String projectId) {
        projectCompanyService.deleteById(projectId);
        return Result.ok();
    }
    
}

