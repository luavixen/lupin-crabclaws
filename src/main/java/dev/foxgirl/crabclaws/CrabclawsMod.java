package dev.foxgirl.crabclaws;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("crabclaws")
public class CrabclawsMod {

    public CrabclawsMod() {
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        CrabclawsModItems.REGISTRY.register(bus);
        CrabclawsModTabs.REGISTRY.register(bus);
    }

}
