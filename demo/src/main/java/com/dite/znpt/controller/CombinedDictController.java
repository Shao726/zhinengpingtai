package com.dite.znpt.controller;


import com.dite.znpt.domain.Constants;
import com.dite.znpt.domain.vo.CombinedDictListReq;
import com.dite.znpt.domain.vo.CombinedDictResp;
import com.dite.znpt.domain.entity.CombinedDictEntity;
import com.dite.znpt.service.CombinedDictService;
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
@Api(tags = "字典")
@RestController
@RequestMapping("/combined-dict")
public class CombinedDictController {
    @Resource
    private CombinedDictService combinedDictService;

    @ApiOperation(value = "获取字典列表", httpMethod = "GET")
    @GetMapping("/list")
    public PageResult<CombinedDictResp> list(CombinedDictListReq combinedDictReq) {
        return PageResult.ok(combinedDictService.selectList(combinedDictReq));
    }

    @ApiOperation(value = "根据字典Id获取详细信息", httpMethod = "GET")
    @GetMapping("/{dictId}")
    public Result<CombinedDictResp> getInfo(@PathVariable String dictId) {
        return Result.ok(combinedDictService.selectById(dictId));
    }

    @ApiOperation(value = "新增字典", httpMethod = "POST")
    @PostMapping
    public Result<Object> add(@RequestBody CombinedDictEntity combinedDict) {
        combinedDictService.saveData(combinedDict);
        return Result.ok();
    }

    @ApiOperation(value = "修改字典", httpMethod = "PUT")
    @PutMapping
    public Result<Object> edit(@RequestBody CombinedDictEntity combinedDict) {
        combinedDictService.updateData(combinedDict);
        return Result.ok();
    }

    @ApiOperation(value = "删除字典", httpMethod = "DELETE")
    @DeleteMapping("/{dictId}")
    public Result<Object> remove(@PathVariable String dictId) {
        combinedDictService.deleteById(dictId);
        return Result.ok();
    }

    @ApiOperation(value = "导出字典", httpMethod = "GET")
    @GetMapping("/export")
    @ResponseExcel(name = "字典")
    public List<CombinedDictResp> export(CombinedDictListReq combinedDictReq) {
        return combinedDictService.selectList(combinedDictReq);
    }

    @ApiOperation(value = "导入字典", httpMethod = "POST")
    @PostMapping("/import")
    public Result<Object> importData(@RequestExcel List<CombinedDictEntity> dataList, BindingResult bindingResult) {
        // JSR 303 校验通用校验获取失败的数据
        List<ErrorMessage> errorMessageList = (List<ErrorMessage>) bindingResult.getTarget();
        if (errorMessageList != null && !errorMessageList.isEmpty()) {
            return Result.error(Constants.SERVICE_EXCEPTION, "导入失败");
        }
        return Result.okM("导入"+dataList.size()+"条数据");
    }
}

