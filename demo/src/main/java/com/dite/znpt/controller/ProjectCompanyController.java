package com.dite.znpt.controller;


import com.dite.znpt.domain.Constants;
import com.dite.znpt.domain.vo.ProjectCompanyListReq;
import com.dite.znpt.domain.vo.ProjectCompanyResp;
import com.dite.znpt.domain.entity.ProjectCompanyEntity;
import com.dite.znpt.service.ProjectCompanyService;
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
@Api(tags = "项目-公司关联信息")
@RestController
@RequestMapping("/project-company")
public class ProjectCompanyController {
    @Resource
    private ProjectCompanyService projectCompanyService;

    @ApiOperation(value = "获取项目-公司关联信息列表", httpMethod = "GET")
    @GetMapping("/list")
    public PageResult<ProjectCompanyResp> list(ProjectCompanyListReq projectCompanyReq) {
        return PageResult.ok(projectCompanyService.selectList(projectCompanyReq));
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

    @ApiOperation(value = "导出项目-公司关联信息", httpMethod = "GET")
    @GetMapping("/export")
    @ResponseExcel(name = "项目-公司关联信息")
    public List<ProjectCompanyResp> export(ProjectCompanyListReq projectCompanyReq) {
        return projectCompanyService.selectList(projectCompanyReq);
    }

    @ApiOperation(value = "导入项目-公司关联信息", httpMethod = "POST")
    @PostMapping("/import")
    public Result<Object> importData(@RequestExcel List<ProjectCompanyEntity> dataList, BindingResult bindingResult) {
        // JSR 303 校验通用校验获取失败的数据
        List<ErrorMessage> errorMessageList = (List<ErrorMessage>) bindingResult.getTarget();
        if (errorMessageList != null && !errorMessageList.isEmpty()) {
            return Result.error(Constants.SERVICE_EXCEPTION, "导入失败");
        }
        return Result.okM("导入"+dataList.size()+"条数据");
    }
}

