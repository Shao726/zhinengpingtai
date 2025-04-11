package com.dite.znpt.controller;


import com.dite.znpt.domain.Constants;
import com.dite.znpt.domain.vo.FileInfoListReq;
import com.dite.znpt.domain.vo.FileInfoResp;
import com.dite.znpt.domain.entity.FileInfoEntity;
import com.dite.znpt.service.FileInfoService;
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
@Api(tags = "文件信息")
@RestController
@RequestMapping("/file-info")
public class FileInfoController {
    @Resource
    private FileInfoService fileInfoService;

    @ApiOperation(value = "获取文件信息列表", httpMethod = "GET")
    @GetMapping("/list")
    public PageResult<FileInfoResp> list(FileInfoListReq fileInfoReq) {
        return PageResult.ok(fileInfoService.selectList(fileInfoReq));
    }

    @ApiOperation(value = "根据文件信息Id获取详细信息", httpMethod = "GET")
    @GetMapping("/{fileId}")
    public Result<FileInfoResp> getInfo(@PathVariable Long fileId) {
        return Result.ok(fileInfoService.selectById(fileId));
    }

    @ApiOperation(value = "新增文件信息", httpMethod = "POST")
    @PostMapping
    public Result<Object> add(@RequestBody FileInfoEntity fileInfo) {
        fileInfoService.saveData(fileInfo);
        return Result.ok();
    }

    @ApiOperation(value = "修改文件信息", httpMethod = "PUT")
    @PutMapping
    public Result<Object> edit(@RequestBody FileInfoEntity fileInfo) {
        fileInfoService.updateData(fileInfo);
        return Result.ok();
    }

    @ApiOperation(value = "删除文件信息", httpMethod = "DELETE")
    @DeleteMapping("/{fileId}")
    public Result<Object> remove(@PathVariable Long fileId) {
        fileInfoService.deleteById(fileId);
        return Result.ok();
    }

    @ApiOperation(value = "导出文件信息", httpMethod = "GET")
    @GetMapping("/export")
    @ResponseExcel(name = "文件信息")
    public List<FileInfoResp> export(FileInfoListReq fileInfoReq) {
        return fileInfoService.selectList(fileInfoReq);
    }

    @ApiOperation(value = "导入文件信息", httpMethod = "POST")
    @PostMapping("/import")
    public Result<Object> importData(@RequestExcel List<FileInfoEntity> dataList, BindingResult bindingResult) {
        // JSR 303 校验通用校验获取失败的数据
        List<ErrorMessage> errorMessageList = (List<ErrorMessage>) bindingResult.getTarget();
        if (errorMessageList != null && !errorMessageList.isEmpty()) {
            return Result.error(Constants.SERVICE_EXCEPTION, "导入失败");
        }
        return Result.okM("导入"+dataList.size()+"条数据");
    }
}

