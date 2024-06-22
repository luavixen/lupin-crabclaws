package dev.foxgirl.crabclaws;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.UUID;

@EventBusSubscriber
public class CrabclawsModFunctionality {

    private static final AttributeModifier REACH_MODIFIER = new AttributeModifier(
            UUID.fromString("61df419d-4f62-4fee-a151-909344b439e7"),
            "crabclaws_extra_reach", 3.0, AttributeModifier.Operation.ADDITION
    );

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        var entity = event.player;
        if (entity == null) return;

        var attribute = entity.getAttribute(ForgeMod.BLOCK_REACH.get());
        if (attribute == null) return;

        if (
                entity.getMainHandItem().getItem() == CrabclawsModItems.CRAB_CLAW.get() ||
                        entity.getOffhandItem().getItem() == CrabclawsModItems.CRAB_CLAW.get()
        ) {
            if (!attribute.hasModifier(REACH_MODIFIER)) {
                attribute.addTransientModifier(REACH_MODIFIER);
            }
        } else {
            if (attribute.hasModifier(REACH_MODIFIER)) {
                attribute.removeModifier(REACH_MODIFIER);
            }
        }
    }

}
