package com.thegalaxy.anvilfixlevel;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

/**
 * 客户端类
 * 此类仅在客户端加载，不会在专用服务器上加载
 * 从这里访问客户端代码是安全的
 */
@Mod(value = AnvilFixLevel.MODID, dist = Dist.CLIENT)
// 可以使用 EventBusSubscriber 自动注册类中所有带 @SubscribeEvent 注解的静态方法
@EventBusSubscriber(modid = AnvilFixLevel.MODID, value = Dist.CLIENT)
public class AnvilFixLevelClient {
    /**
     * 客户端构造函数
     *
     * @param container 模组容器
     */
    public AnvilFixLevelClient(ModContainer container) {
        // 允许 NeoForge 为此模组的配置创建配置界面
        // 配置界面可通过：模组屏幕 > 点击你的模组 > 点击配置 来访问
        // 不要忘记在 en_us.json 文件中为配置选项添加翻译
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    /**
     * 客户端设置事件
     * 在客户端初始化时调用
     */
    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // 一些客户端设置代码
        AnvilFixLevel.LOGGER.info("AnvilFixLevel 客户端设置完成");
        AnvilFixLevel.LOGGER.info("玩家名称 >> {}", Minecraft.getInstance().getUser().getName());
    }
}

