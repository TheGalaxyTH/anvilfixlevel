package com.thegalaxy.anvilfixlevel;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * AnvilFixLevel - 铁砧修复等级修复模组
 * 提供固定的铁砧经验消耗，移除累计修复成本惩罚
 */
@Mod(AnvilFixLevel.MODID)
public class AnvilFixLevel {
    public static final String MODID = "anvilfixlevel";
    public static final Logger LOGGER = LogUtils.getLogger();

    /**
     * 模组构造函数
     *
     * @param modEventBus 模组事件总线
     * @param modContainer 模组容器
     */
    public AnvilFixLevel(IEventBus modEventBus, ModContainer modContainer) {
        // 注册通用设置方法用于模组加载
        modEventBus.addListener(this::commonSetup);

        // 注册模组的 ModConfigSpec，让 FML 为我们创建和加载配置文件
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    /**
     * 通用设置
     * 在模组加载时执行
     */
    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("AnvilFixLevel 初始化完成！");
        LOGGER.info("铁砧消耗等级设置为: {} 级", Config.ANVIL_COST.get());
        LOGGER.info("移除修复成本: {}", Config.REMOVE_REPAIR_COST.get());
    }
}
