package io.renren.modules.generator.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.KConfigEntity;
import io.renren.modules.generator.service.KConfigService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * Config 系统配置
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-08 16:20:33
 */
@RestController
@RequestMapping("generator/kconfig")
public class KConfigController {
    @Autowired
    private KConfigService kConfigService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:kconfig:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = kConfigService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:kconfig:info")
    public R info(@PathVariable("id") Integer id){
		KConfigEntity kConfig = kConfigService.getById(id);

        return R.ok().put("kConfig", kConfig);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:kconfig:save")
    public R save(@RequestBody KConfigEntity kConfig){
		kConfigService.save(kConfig);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:kconfig:update")
    public R update(@RequestBody KConfigEntity kConfig){
		kConfigService.updateById(kConfig);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:kconfig:delete")
    public R delete(@RequestBody Integer[] ids){
		kConfigService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
