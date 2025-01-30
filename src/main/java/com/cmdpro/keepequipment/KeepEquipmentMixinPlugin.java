package com.cmdpro.keepequipment;

import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.LoadingModList;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class KeepEquipmentMixinPlugin implements IMixinConfigPlugin {
    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.equals("com.cmdpro.keepequipment.mixin.AccessoriesEventHandlerMixin")) {
            return LoadingModList.get().getModFileById("accessories") != null;
        }
        if (mixinClassName.equals("com.cmdpro.keepequipment.mixin.CuriosEventHandlerMixin")) {
            if (LoadingModList.get().getModFileById("accessories") == null) {
                return LoadingModList.get().getModFileById("curios") != null;
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
