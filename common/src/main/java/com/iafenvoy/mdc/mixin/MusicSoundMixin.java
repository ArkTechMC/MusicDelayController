package com.iafenvoy.mdc.mixin;

import com.iafenvoy.mdc.MDCConfig;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MusicSound.class)
public abstract class MusicSoundMixin {
    @Shadow
    public abstract RegistryEntry<SoundEvent> getSound();

    @Inject(method = "getMinDelay", at = @At("HEAD"), cancellable = true)
    private void handleMinDelay(CallbackInfoReturnable<Integer> cir) {
        MDCConfig.MusicConfig cfg = MDCConfig.INSTANCE.get(this.getSound().value().getId());
        if (cfg == null) return;
        cir.setReturnValue(cfg.enable() ? cfg.minDelay() : Integer.MAX_VALUE);
    }

    @Inject(method = "getMaxDelay", at = @At("HEAD"), cancellable = true)
    private void handleMaxDelay(CallbackInfoReturnable<Integer> cir) {
        MDCConfig.MusicConfig cfg = MDCConfig.INSTANCE.get(this.getSound().value().getId());
        if (cfg == null) return;
        cir.setReturnValue(cfg.enable() ? cfg.maxDelay() : Integer.MAX_VALUE);
    }
}
