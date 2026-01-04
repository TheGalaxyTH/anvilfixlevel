package com.thegalaxy.anvilfixlevel;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * 模组配置类
 */
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // 铁砧操作的固定经验等级消耗。设置为 0 可禁用所有消耗
    // 此配置替代原版的累计修复成本系统
    public static final ModConfigSpec.IntValue ANVIL_COST = BUILDER
            .comment("铁砧操作的固定经验等级消耗。设置为 0 可禁用所有消耗。")
            .comment("此配置替代原版的累计修复成本系统。")
            .defineInRange("anvilCost", 1, 0, 100);

    // 是否移除物品的修复成本惩罚
    // 设置为 true 可防止"过于昂贵！"消息出现
    public static final ModConfigSpec.BooleanValue REMOVE_REPAIR_COST = BUILDER
            .comment("是否移除物品的修复成本惩罚。")
            .comment("设置为 true 可防止“过于昂贵！”消息出现。")
            .define("removeRepairCost", true);

    static final ModConfigSpec SPEC = BUILDER.build();
}
