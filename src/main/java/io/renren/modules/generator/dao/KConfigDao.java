package io.renren.modules.generator.dao;

import io.renren.modules.generator.entity.KConfigEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Config 系统配置
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-08 16:20:33
 */
@Mapper
public interface KConfigDao extends BaseMapper<KConfigEntity> {
	
}
