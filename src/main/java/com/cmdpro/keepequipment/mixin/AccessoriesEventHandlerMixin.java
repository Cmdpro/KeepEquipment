package com.cmdpro.keepequipment.mixin;

import io.wispforest.accessories.api.DropRule;
import io.wispforest.accessories.api.slot.SlotReference;
import io.wispforest.accessories.impl.AccessoriesEventHandler;
import io.wispforest.accessories.impl.ExpandedSimpleContainer;
import net.minecraft.core.NonNullList;
import net.minecraft.util.Tuple;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;
import top.theillusivec4.curios.common.event.CuriosEventHandler;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

@Mixin(AccessoriesEventHandler.class)
public abstract class AccessoriesEventHandlerMixin {
    @Inject(method = "dropStack", at = @At(value = "HEAD"), remap = false, cancellable = true)
    private static void KeepEquipment$dropStack(DropRule dropRule, LivingEntity entity, ExpandedSimpleContainer container, SlotReference reference, DamageSource source, boolean keepInvEnabled, CallbackInfoReturnable<ItemStack> cir) {
        if (!entity.level().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY)) {
            cir.setReturnValue(null);
        }
    }
}
