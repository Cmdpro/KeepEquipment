package com.cmdpro.keepequipment.mixin;

import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
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

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Inject(method = "dropEquipment", at = @At(value = "HEAD"), remap = false, cancellable = true)
    private void KeepEquipment$dropEquipment(CallbackInfo ci) {
        Player me = (Player) (Object) this;
        if (!me.level().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY)) {
            var list = me.getInventory().items;
            for (int i = 9; i < list.size(); i++) {
                ItemStack itemstack = list.get(i);
                if (!itemstack.isEmpty()) {
                    me.drop(itemstack, true, false);
                    list.set(i, ItemStack.EMPTY);
                }
            }
            ci.cancel();
        }
    }
}
