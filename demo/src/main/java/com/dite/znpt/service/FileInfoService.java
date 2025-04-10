package com.dite.znpt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dite.znpt.domain.entity.FileInfoEntity;
import com.dite.znpt.domain.vo.FileInfoListReq;
import com.dite.znpt.domain.vo.FileInfoResp;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/09 11:48
 * @Description: 文件信息表服务接口
 */
public interface FileInfoService extends IService<FileInfoEntity> {

    /**
     * 功能描述：查询文件信息列表
     *
     * @param fileInfoReq 文件信息
     * @return {@link List }<{@link FileInfoEntity }>
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    PageInfo<FileInfoResp> selectList(FileInfoListReq fileInfoReq);

    /**
     * 功能描述：查询单条文件信息
     *
     * @param fileId 文件信息Id
     * @return {@link FileInfoResp }
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    FileInfoResp selectById(Long fileId);

    /**
     * 功能描述：新增文件信息
     *
     * @param fileInfo 文件信息
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    void saveData(FileInfoEntity fileInfo);

    /**
     * 功能描述：更新文件信息
     *
     * @param fileInfo 文件信息
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    void updateData(FileInfoEntity fileInfo);

    /**
     * 功能描述：删除文件信息
     *
     * @param fileId 文件信息Id
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    void deleteById(Long fileId);
}

