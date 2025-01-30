package com.cmdpro.keepequipment.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;
import top.theillusivec4.curios.common.event.CuriosEventHandler;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

@Mixin(CuriosEventHandler.class)
public abstract class CuriosEventHandlerMixin {
    @Inject(method = "handleDrops", at = @At(value = "HEAD"), remap = false, cancellable = true)
    private static void KeepEquipment$handleDrops(String identifier, LivingEntity livingEntity, List<Tuple<Predicate<ItemStack>, ICurio.DropRule>> dropRules, NonNullList<Boolean> renders, IDynamicStackHandler stacks, boolean cosmetic, Collection<ItemEntity> drops, boolean keepInventory, LivingDropsEvent evt, CallbackInfo ci) {
        if (!livingEntity.level().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY)) {
            ci.cancel();
        }
    }
}
