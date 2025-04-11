package com.dite.znpt.controller;


import com.dite.znpt.domain.Constants;
import com.dite.znpt.domain.vo.WeatherTypeListReq;
import com.dite.znpt.domain.vo.WeatherTypeResp;
import com.dite.znpt.domain.entity.WeatherTypeEntity;
import com.dite.znpt.service.WeatherTypeService;
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
@Api(tags = "天气类型")
@RestController
@RequestMapping("/weather-type")
public class WeatherTypeController {
    @Resource
    private WeatherTypeService weatherTypeService;

    @ApiOperation(value = "获取天气类型列表", httpMethod = "GET")
    @GetMapping("/list")
    public PageResult<WeatherTypeResp> list(WeatherTypeListReq weatherTypeReq) {
        return PageResult.ok(weatherTypeService.selectList(weatherTypeReq));
    }

    @ApiOperation(value = "根据天气类型Id获取详细信息", httpMethod = "GET")
    @GetMapping("/{weatherCode}")
    public Result<WeatherTypeResp> getInfo(@PathVariable String weatherCode) {
        return Result.ok(weatherTypeService.selectById(weatherCode));
    }

    @ApiOperation(value = "新增天气类型", httpMethod = "POST")
    @PostMapping
    public Result<Object> add(@RequestBody WeatherTypeEntity weatherType) {
        weatherTypeService.saveData(weatherType);
        return Result.ok();
    }

    @ApiOperation(value = "修改天气类型", httpMethod = "PUT")
    @PutMapping
    public Result<Object> edit(@RequestBody WeatherTypeEntity weatherType) {
        weatherTypeService.updateData(weatherType);
        return Result.ok();
    }

    @ApiOperation(value = "删除天气类型", httpMethod = "DELETE")
    @DeleteMapping("/{weatherCode}")
    public Result<Object> remove(@PathVariable String weatherCode) {
        weatherTypeService.deleteById(weatherCode);
        return Result.ok();
    }

    @ApiOperation(value = "导出天气类型", httpMethod = "GET")
    @GetMapping("/export")
    @ResponseExcel(name = "天气类型")
    public List<WeatherTypeResp> export(WeatherTypeListReq weatherTypeReq) {
        return weatherTypeService.selectList(weatherTypeReq);
    }

    @ApiOperation(value = "导入天气类型", httpMethod = "POST")
    @PostMapping("/import")
    public Result<Object> importData(@RequestExcel List<WeatherTypeEntity> dataList, BindingResult bindingResult) {
        // JSR 303 校验通用校验获取失败的数据
        List<ErrorMessage> errorMessageList = (List<ErrorMessage>) bindingResult.getTarget();
        if (errorMessageList != null && !errorMessageList.isEmpty()) {
            return Result.error(Constants.SERVICE_EXCEPTION, "导入失败");
        }
        return Result.okM("导入"+dataList.size()+"条数据");
    }
}

