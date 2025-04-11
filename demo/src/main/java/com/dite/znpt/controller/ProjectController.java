package com.dite.znpt.controller;


import com.dite.znpt.domain.Constants;
import com.dite.znpt.domain.vo.ProjectListReq;
import com.dite.znpt.domain.vo.ProjectResp;
import com.dite.znpt.domain.entity.ProjectEntity;
import com.dite.znpt.service.ProjectService;
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
@Api(tags = "项目信息")
@RestController
@RequestMapping("/project")
public class ProjectController {
    @Resource
    private ProjectService projectService;

    @ApiOperation(value = "获取项目信息列表", httpMethod = "GET")
    @GetMapping("/list")
    public PageResult<ProjectResp> list(ProjectListReq projectReq) {
        return PageResult.ok(projectService.selectList(projectReq));
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

    @ApiOperation(value = "导出项目信息", httpMethod = "GET")
    @GetMapping("/export")
    @ResponseExcel(name = "项目信息")
    public List<ProjectResp> export(ProjectListReq projectReq) {
        return projectService.selectList(projectReq);
    }

    @ApiOperation(value = "导入项目信息", httpMethod = "POST")
    @PostMapping("/import")
    public Result<Object> importData(@RequestExcel List<ProjectEntity> dataList, BindingResult bindingResult) {
        // JSR 303 校验通用校验获取失败的数据
        List<ErrorMessage> errorMessageList = (List<ErrorMessage>) bindingResult.getTarget();
        if (errorMessageList != null && !errorMessageList.isEmpty()) {
            return Result.error(Constants.SERVICE_EXCEPTION, "导入失败");
        }
        return Result.okM("导入"+dataList.size()+"条数据");
    }
}

