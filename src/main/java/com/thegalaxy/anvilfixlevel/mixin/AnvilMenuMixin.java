package com.thegalaxy.anvilfixlevel.mixin;

import com.thegalaxy.anvilfixlevel.AnvilFixLevel;
import com.thegalaxy.anvilfixlevel.Config;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin 到 AnvilMenu 类
 * 用于修改铁砧的经验成本计算和移除修复成本
 */
@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin {

    @Shadow
    @Final
    private DataSlot cost;

    /**
     * 修改 "Too Expensive" 检查的常量
     * 原版检查 cost >= 40，我们将 40 改为一个很大的值
     * 这样就永远不会触发 "Too Expensive" 了
     */
    @ModifyConstant(
        method = "createResult",
        constant = @Constant(intValue = 40)
    )
    private int modifyMaxCost(int original) {
        AnvilFixLevel.LOGGER.debug("修改最大成本限制: {} -> {}", original, Integer.MAX_VALUE);
        return Integer.MAX_VALUE;
    }

    /**
     * 修改 createResult 方法中的输出物品
     * 移除修复成本组件
     */
    @ModifyVariable(
        method = "createResult",
        at = @At(value = "STORE"),
        ordinal = 2
    )
    private ItemStack modifyResultItem(ItemStack resultItem) {
        if (Config.REMOVE_REPAIR_COST.get() && resultItem != null && !resultItem.isEmpty()) {
            resultItem.remove(net.minecraft.core.component.DataComponents.REPAIR_COST);
            AnvilFixLevel.LOGGER.debug("已移除修复成本，物品: {}", resultItem.getItem());
        }
        return resultItem;
    }

    /**
     * 在 createResult 方法的最后强制设置固定成本
     * 这确保无论原版如何计算，最终成本都是我们配置的值
     */
    @Inject(
        method = "createResult",
        at = @At("RETURN")
    )
    private void forceFixedCost(CallbackInfo ci) {
        int currentCost = this.cost.get();
        if (currentCost > 0) {
            int fixedCost = Config.ANVIL_COST.get();
            this.cost.set(fixedCost);
            AnvilFixLevel.LOGGER.debug("强制设置铁砧成本: {} -> {}", currentCost, fixedCost);
        }
    }
}
