package com.dite.znpt.web.controller;


import com.dite.znpt.domain.Constants;
import com.dite.znpt.domain.vo.PersonnelListReq;
import com.dite.znpt.domain.vo.PersonnelResp;
import com.dite.znpt.domain.entity.PersonnelEntity;
import com.dite.znpt.service.PersonnelService;
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
@Api(tags = "人员信息")
@RestController
@RequestMapping("/personnel")
public class PersonnelController {
    @Resource
    private PersonnelService personnelService;

    @ApiOperation(value = "获取人员信息列表", httpMethod = "GET")
    @GetMapping("/list")
    public PageResult<PersonnelResp> list(PersonnelListReq personnelReq) {
        return PageResult.ok(personnelService.selectList(personnelReq));
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

    @ApiOperation(value = "导出人员信息", httpMethod = "GET")
    @GetMapping("/export")
    @ResponseExcel(name = "人员信息")
    public List<PersonnelResp> export(PersonnelListReq personnelReq) {
        return personnelService.selectList(personnelReq);
    }

    @ApiOperation(value = "导入人员信息", httpMethod = "POST")
    @PostMapping("/import")
    public Result<Object> importData(@RequestExcel List<PersonnelEntity> dataList, BindingResult bindingResult) {
        // JSR 303 校验通用校验获取失败的数据
        List<ErrorMessage> errorMessageList = (List<ErrorMessage>) bindingResult.getTarget();
        if (errorMessageList != null && !errorMessageList.isEmpty()) {
            return Result.error(Constants.SERVICE_EXCEPTION, "导入失败");
        }
        return Result.okM("导入"+dataList.size()+"条数据");
    }
}

