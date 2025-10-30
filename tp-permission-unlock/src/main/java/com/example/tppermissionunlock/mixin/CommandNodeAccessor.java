package com.example.tppermissionunlock.mixin;

import com.mojang.brigadier.tree.CommandNode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Predicate;

@Mixin(CommandNode.class)
public interface CommandNodeAccessor<S> {
    @Accessor("requirement")
    void setRequirement(Predicate<S> predicate);
}


