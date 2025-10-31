package com.example.tppermissionunlock.mixin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.command.TeleportCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Predicate;

@Mixin(TeleportCommand.class)
public class TeleportCommandRegisterMixin {

    @Redirect(
        method = "register",
        at = @At(
            value = "INVOKE",
            target = "Lcom/mojang/brigadier/builder/ArgumentBuilder;requires(Ljava/util/function/Predicate;)Lcom/mojang/brigadier/builder/ArgumentBuilder;"
        )
    )
    private static ArgumentBuilder<ServerCommandSource, ?> tpperm$forceRequiresTrue(
        ArgumentBuilder<ServerCommandSource, ?> builder,
        Predicate<ServerCommandSource> original
    ) {
        return builder.requires(s -> true);
    }
}


