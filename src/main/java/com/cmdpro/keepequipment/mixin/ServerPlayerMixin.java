package com.cmdpro.keepequipment.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {
    @Inject(method = "restoreFrom", at = @At(value = "TAIL"), remap = false, cancellable = true)
    private void KeepEquipment$restoreFrom(ServerPlayer that, boolean keepEverything, CallbackInfo ci) {
        ServerPlayer me = (ServerPlayer)(Object)this;
        if (!me.level().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY)) {
            for (int i = 0; i < that.getInventory().armor.size(); i++) {
                me.getInventory().armor.set(i, that.getInventory().armor.get(i));
            }
            for (int i = 0; i < 9; i++) {
                me.getInventory().items.set(i, that.getInventory().items.get(i));
            }
            for (int i = 0; i < that.getInventory().offhand.size(); i++) {
                me.getInventory().offhand.set(i, that.getInventory().offhand.get(i));
            }
        }
    }
}
