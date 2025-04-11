package com.dite.znpt.controller;


import com.dite.znpt.domain.Constants;
import com.dite.znpt.domain.vo.TCompanyListReq;
import com.dite.znpt.domain.vo.TCompanyResp;
import com.dite.znpt.domain.entity.TCompanyEntity;
import com.dite.znpt.service.TCompanyService;
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
@Api(tags = "公司信息")
@RestController
@RequestMapping("/t-company")
public class TCompanyController {
    @Resource
    private TCompanyService tCompanyService;

    @ApiOperation(value = "获取公司信息列表", httpMethod = "GET")
    @GetMapping("/list")
    public PageResult<TCompanyResp> list(TCompanyListReq tCompanyReq) {
        return PageResult.ok(tCompanyService.selectList(tCompanyReq));
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

    @ApiOperation(value = "导出公司信息", httpMethod = "GET")
    @GetMapping("/export")
    @ResponseExcel(name = "公司信息")
    public List<TCompanyResp> export(TCompanyListReq tCompanyReq) {
        return tCompanyService.selectList(tCompanyReq);
    }

    @ApiOperation(value = "导入公司信息", httpMethod = "POST")
    @PostMapping("/import")
    public Result<Object> importData(@RequestExcel List<TCompanyEntity> dataList, BindingResult bindingResult) {
        // JSR 303 校验通用校验获取失败的数据
        List<ErrorMessage> errorMessageList = (List<ErrorMessage>) bindingResult.getTarget();
        if (errorMessageList != null && !errorMessageList.isEmpty()) {
            return Result.error(Constants.SERVICE_EXCEPTION, "导入失败");
        }
        return Result.okM("导入"+dataList.size()+"条数据");
    }
}

