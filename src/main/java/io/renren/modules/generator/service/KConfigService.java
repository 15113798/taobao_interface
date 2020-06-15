package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.KConfigEntity;

import java.util.Map;

/**
 * Config 系统配置
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-08 16:20:33
 */
public interface KConfigService extends IService<KConfigEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

