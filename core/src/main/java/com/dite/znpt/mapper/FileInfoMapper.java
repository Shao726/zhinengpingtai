package com.dite.znpt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dite.znpt.domain.entity.FileInfoEntity;
import com.dite.znpt.domain.vo.FileInfoListReq;
import com.dite.znpt.domain.vo.FileInfoResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 文件信息表数据库访问层
 */
public interface FileInfoMapper extends BaseMapper<FileInfoEntity> {
    List<FileInfoResp> queryBySelective(FileInfoListReq fileInfoReq);
}

