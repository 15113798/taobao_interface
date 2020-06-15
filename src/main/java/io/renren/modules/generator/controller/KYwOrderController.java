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

import io.renren.modules.generator.entity.KYwOrderEntity;
import io.renren.modules.generator.service.KYwOrderService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 淘宝服务费订单明细表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-15 23:50:37
 */
@RestController
@RequestMapping("generator/kyworder")
public class KYwOrderController {
    @Autowired
    private KYwOrderService kYwOrderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:kyworder:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = kYwOrderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:kyworder:info")
    public R info(@PathVariable("id") Integer id){
		KYwOrderEntity kYwOrder = kYwOrderService.getById(id);

        return R.ok().put("kYwOrder", kYwOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:kyworder:save")
    public R save(@RequestBody KYwOrderEntity kYwOrder){
		kYwOrderService.save(kYwOrder);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:kyworder:update")
    public R update(@RequestBody KYwOrderEntity kYwOrder){
		kYwOrderService.updateById(kYwOrder);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:kyworder:delete")
    public R delete(@RequestBody Integer[] ids){
		kYwOrderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
