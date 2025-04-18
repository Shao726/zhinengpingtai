package com.dite.znpt.web.controller;


import com.dite.znpt.domain.Constants;
import com.dite.znpt.domain.vo.DefectListReq;
import com.dite.znpt.domain.vo.DefectResp;
import com.dite.znpt.domain.entity.DefectEntity;
import com.dite.znpt.service.DefectService;
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
@Api(tags = "缺陷记录")
@RestController
@RequestMapping("/defect")
public class DefectController {
    @Resource
    private DefectService defectService;

    @ApiOperation(value = "获取缺陷记录列表", httpMethod = "GET")
    @GetMapping("/list")
    public PageResult<DefectResp> list(DefectListReq defectReq) {
        return PageResult.ok(defectService.selectList(defectReq));
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

    @ApiOperation(value = "导出缺陷记录", httpMethod = "GET")
    @GetMapping("/export")
    @ResponseExcel(name = "缺陷记录")
    public List<DefectResp> export(DefectListReq defectReq) {
        return defectService.selectList(defectReq);
    }

    @ApiOperation(value = "导入缺陷记录", httpMethod = "POST")
    @PostMapping("/import")
    public Result<Object> importData(@RequestExcel List<DefectEntity> dataList, BindingResult bindingResult) {
        // JSR 303 校验通用校验获取失败的数据
        List<ErrorMessage> errorMessageList = (List<ErrorMessage>) bindingResult.getTarget();
        if (errorMessageList != null && !errorMessageList.isEmpty()) {
            return Result.error(Constants.SERVICE_EXCEPTION, "导入失败");
        }
        return Result.okM("导入"+dataList.size()+"条数据");
    }
}

