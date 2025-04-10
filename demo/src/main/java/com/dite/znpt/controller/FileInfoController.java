package com.dite.znpt.controller;


import com.dite.znpt.domain.vo.FileInfoListReq;
import com.dite.znpt.domain.vo.FileInfoResp;
import com.dite.znpt.domain.entity.FileInfoEntity;
import com.dite.znpt.service.FileInfoService;
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
@Api(tags = "文件信息")
@RestController
@RequestMapping("/file-info")
public class FileInfoController {
    @Resource
    private FileInfoService fileInfoService;

    @ApiOperation(value = "获取文件信息列表", httpMethod = "GET")
    @GetMapping("/list")
    public Result<PageInfo<FileInfoResp>> list(FileInfoListReq fileInfoReq) {
        PageInfo<FileInfoResp> page = fileInfoService.selectList(fileInfoReq);
        return Result.ok(page);
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
    
}

