package com.dite.znpt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dite.znpt.domain.entity.FileInfoEntity;
import com.dite.znpt.domain.vo.FileInfoListReq;
import com.dite.znpt.domain.vo.FileInfoResp;
import com.dite.znpt.service.FileInfoService;
import com.dite.znpt.mapper.FileInfoMapper;
import org.springframework.stereotype.Service;
import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import com.dite.znpt.util.PageUtil;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 文件信息表服务实现类
 */
@Service
@RequiredArgsConstructor
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfoEntity> implements FileInfoService {

    /**
     * 功能描述：查询文件信息列表
     *
     * @param fileInfoReq 文件信息信息
     * @return {@link List }<{@link FileInfoResp }>
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public List<FileInfoResp> selectList(FileInfoListReq fileInfoReq) {
        PageUtil.startPage();
        List<FileInfoResp> fileInfoList= this.baseMapper.queryBySelective(fileInfoReq);
        fileInfoList.forEach(resp -> {
            
        });
        return fileInfoList;
    }

    /**
     * 功能描述：查询单条文件信息
     *
     * @param fileId 文件信息Id
     * @return {@link FileInfoResp }
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public FileInfoResp selectById(Long fileId) {
        FileInfoListReq fileInfoReq = new FileInfoListReq();
        fileInfoReq.setFileId(fileId);

        List<FileInfoResp> list = selectList(fileInfoReq);
        return list.isEmpty() ? CollUtil.getFirst(list) : new FileInfoResp();
    }

    /**
     * 功能描述：新增文件信息
     *
     * @param fileInfo 文件信息
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public void saveData(FileInfoEntity fileInfo) {
//            todo 校验
        save(fileInfo);
    }

    /**
     * 功能描述：更新文件信息
     *
     * @param fileInfo 文件信息
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public void updateData(FileInfoEntity fileInfo) {
//            todo 校验
        updateById(fileInfo);
    }

    /**
     * 功能描述：删除文件信息
     *
     * @param fileId 文件信息Id
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public void deleteById(Long fileId) {
//            todo 校验
        removeById(fileId);
    }

}
