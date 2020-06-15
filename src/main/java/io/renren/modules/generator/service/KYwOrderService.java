package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.KYwOrderEntity;

import java.util.Map;

/**
 * 淘宝服务费订单明细表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-15 23:50:37
 */
public interface KYwOrderService extends IService<KYwOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

