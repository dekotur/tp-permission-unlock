package com.example.tppermissionunlock.mixin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.command.TeleportCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(TeleportCommand.class)
public class TeleportCommandRegisterMixin {

    @Inject(method = "register", at = @At("TAIL"))
    private static void tpperm$unlock(CommandDispatcher<ServerCommandSource> dispatcher, CallbackInfo ci) {
        allowEveryone(dispatcher, "tp");
        allowEveryone(dispatcher, "teleport");
    }

    private static void allowEveryone(CommandDispatcher<ServerCommandSource> dispatcher, String literal) {
        CommandNode<ServerCommandSource> node = dispatcher.getRoot().getChild(literal);
        if (node == null) return;
        setAllRequirements(node, s -> true);
    }

    @SuppressWarnings("unchecked")
    private static void setAllRequirements(CommandNode<ServerCommandSource> node, Predicate<ServerCommandSource> predicate) {
        ((CommandNodeAccessor<ServerCommandSource>) (Object) node).setRequirement(predicate);
        for (CommandNode<ServerCommandSource> child : node.getChildren()) {
            setAllRequirements(child, predicate);
        }
        CommandNode<ServerCommandSource> redirect = node.getRedirect();
        if (redirect != null) {
            setAllRequirements(redirect, predicate);
        }
    }
}


