package io.renren.modules.generator.dao;

import io.renren.modules.generator.entity.KYwOrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 淘宝服务费订单明细表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-15 23:50:37
 */
@Mapper
public interface KYwOrderDao extends BaseMapper<KYwOrderEntity> {
	
}
