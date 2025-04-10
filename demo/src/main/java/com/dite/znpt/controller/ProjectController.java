package com.dite.znpt.controller;


import com.dite.znpt.domain.vo.ProjectListReq;
import com.dite.znpt.domain.vo.ProjectResp;
import com.dite.znpt.domain.entity.ProjectEntity;
import com.dite.znpt.service.ProjectService;
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
@Api(tags = "项目信息")
@RestController
@RequestMapping("/project")
public class ProjectController {
    @Resource
    private ProjectService projectService;

    @ApiOperation(value = "获取项目信息列表", httpMethod = "GET")
    @GetMapping("/list")
    public Result<PageInfo<ProjectResp>> list(ProjectListReq projectReq) {
        PageInfo<ProjectResp> page = projectService.selectList(projectReq);
        return Result.ok(page);
    }

    @ApiOperation(value = "根据项目信息Id获取详细信息", httpMethod = "GET")
    @GetMapping("/{projectId}")
    public Result<ProjectResp> getInfo(@PathVariable String projectId) {
        return Result.ok(projectService.selectById(projectId));
    }

    @ApiOperation(value = "新增项目信息", httpMethod = "POST")
    @PostMapping
    public Result<Object> add(@RequestBody ProjectEntity project) {
        projectService.saveData(project);
        return Result.ok();
    }

    @ApiOperation(value = "修改项目信息", httpMethod = "PUT")
    @PutMapping
    public Result<Object> edit(@RequestBody ProjectEntity project) {
        projectService.updateData(project);
        return Result.ok();
    }

    @ApiOperation(value = "删除项目信息", httpMethod = "DELETE")
    @DeleteMapping("/{projectId}")
    public Result<Object> remove(@PathVariable String projectId) {
        projectService.deleteById(projectId);
        return Result.ok();
    }
    
}

