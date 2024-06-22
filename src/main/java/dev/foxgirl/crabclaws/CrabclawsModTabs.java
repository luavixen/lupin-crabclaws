package dev.foxgirl.crabclaws;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class CrabclawsModTabs {

    public static final DeferredRegister<CreativeModeTab> REGISTRY;

    static {
        REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "crabclaws");
    }

    @SubscribeEvent
    public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
        if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            tabData.accept(CrabclawsModItems.CRAB_CLAW.get());
        }
    }

}
