package com.dite.znpt.monitor.service.impl;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dite.znpt.exception.ServiceException;
import com.dite.znpt.monitor.domain.entity.IpConfigEntity;
import com.dite.znpt.monitor.domain.req.MonitorConfigAddReq;
import com.dite.znpt.monitor.mapper.IpConfigMapper;
import com.dite.znpt.monitor.service.IpConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Date: 2023/09/05 16:39
 * @Description: 监控设备IP配置表服务实现类
 */
@Service
public class IpConfigServiceImpl extends ServiceImpl<IpConfigMapper, IpConfigEntity> implements IpConfigService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void configAdd(MonitorConfigAddReq req) {
        //先删除再新增--全量新增
        final List<IpConfigEntity> configAdds = req.getIpAddresses().stream().map(this::BuildConfigEntity).collect(Collectors.toList());
        //校验是否有重复的--用前三位来判断重复
        checkDup(configAdds);
        deleteConfig();
        this.saveBatch(configAdds);
    }



    @Override
    public List<String> configList() {
        LambdaQueryWrapper<IpConfigEntity> wrapper = new LambdaQueryWrapper<>();
        final List<IpConfigEntity> ifConfigs = this.list(wrapper);
        return ifConfigs.stream().map(t->t.getIp()).collect(Collectors.toList());
    }

    private void deleteConfig(){
        LambdaQueryWrapper<IpConfigEntity> wrapper = new LambdaQueryWrapper<>();
        this.remove(wrapper);
    }

    private void checkDup(List<IpConfigEntity> configAdds) {
        final List<String> ipTopThreeList = configAdds.stream().map(t -> t.getIpTopThree()).collect(Collectors.toList());
        LambdaQueryWrapper<IpConfigEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(IpConfigEntity::getIpTopThree,ipTopThreeList)
                .last("limit 1");
        final IpConfigEntity one = this.getOne(wrapper);
        if(one != null){
            throw new ServiceException("与现有视频地址重复,请重新选择！");
        }
    }

    private IpConfigEntity BuildConfigEntity( String ip) {
        IpConfigEntity entity = new IpConfigEntity();
        final String[] ipArray = ip.split("\\.");
        ipValidate(ipArray);
        entity.setIp(ip);
        entity.setIpTopThree(ipArray[0]+"."+ipArray[1]+"."+ipArray[2]);
        return entity;
    }

    private void ipValidate(String[] ipArray) {
        if(ipArray.length!=4){
            throw new ServiceException("ip地址长度不对");
        }
        for (int i = 0; i < 4; i++) {
            if(! (NumberUtil.isInteger(ipArray[i]) || (i==3 && "*".equals(ipArray[i])))){
                throw new ServiceException("ip地址为非数字");
            }
        }
    }
}
