package dev.foxgirl.crabclaws;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.UUID;

@EventBusSubscriber
public class CrabclawsModFunctionality {

    private static final AttributeModifier REACH_MODIFIER = new AttributeModifier(
            ResourceLocation.parse("crabclaws:extra_reach"),
            CrabclawsMod.CONFIG.clawExtraReachAmount, AttributeModifier.Operation.ADD_VALUE
    );

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        var entity = event.player;
        if (entity == null) return;

        var attribute = entity.getAttribute(Attributes.BLOCK_INTERACTION_RANGE);
        if (attribute == null) return;

        if (
            entity.getMainHandItem().getItem() == CrabclawsModItems.CRAB_CLAW.get() ||
            entity.getOffhandItem().getItem() == CrabclawsModItems.CRAB_CLAW.get()
        ) {
            if (!attribute.hasModifier(REACH_MODIFIER.id())) {
                attribute.addTransientModifier(REACH_MODIFIER);
            }
        } else {
            if (attribute.hasModifier(REACH_MODIFIER.id())) {
                attribute.removeModifier(REACH_MODIFIER.id());
            }
        }
    }

}
