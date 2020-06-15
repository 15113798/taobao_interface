package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.KConfigDao;
import io.renren.modules.generator.entity.KConfigEntity;
import io.renren.modules.generator.service.KConfigService;


@Service("kConfigService")
public class KConfigServiceImpl extends ServiceImpl<KConfigDao, KConfigEntity> implements KConfigService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<KConfigEntity> page = this.page(
                new Query<KConfigEntity>().getPage(params),
                new QueryWrapper<KConfigEntity>()
        );

        return new PageUtils(page);
    }

}