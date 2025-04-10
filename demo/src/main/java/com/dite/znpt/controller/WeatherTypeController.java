package com.dite.znpt.controller;


import com.dite.znpt.domain.vo.WeatherTypeListReq;
import com.dite.znpt.domain.vo.WeatherTypeResp;
import com.dite.znpt.domain.entity.WeatherTypeEntity;
import com.dite.znpt.service.WeatherTypeService;
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
@Api(tags = "天气类型")
@RestController
@RequestMapping("/weather-type")
public class WeatherTypeController {
    @Resource
    private WeatherTypeService weatherTypeService;

    @ApiOperation(value = "获取天气类型列表", httpMethod = "GET")
    @GetMapping("/list")
    public Result<PageInfo<WeatherTypeResp>> list(WeatherTypeListReq weatherTypeReq) {
        PageInfo<WeatherTypeResp> page = weatherTypeService.selectList(weatherTypeReq);
        return Result.ok(page);
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
    
}

