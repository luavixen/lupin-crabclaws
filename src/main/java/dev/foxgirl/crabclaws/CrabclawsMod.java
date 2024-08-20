package dev.foxgirl.crabclaws;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("crabclaws")
public class CrabclawsMod {

    public static final Logger LOGGER = LogManager.getLogger();

    public static final CrabclawsConfig CONFIG = CrabclawsConfig.loadConfig();

    public CrabclawsMod() {
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        CrabclawsModItems.REGISTRY.register(bus);
        CrabclawsModTabs.REGISTRY.register(bus);
        MinecraftForge.EVENT_BUS.addListener(this::onLootTableLoad);
    }

    private static final ResourceLocation UNDERWATER_RUIN_SMALL = ResourceLocation.parse("minecraft:chests/underwater_ruin_small");
    private static final ResourceLocation UNDERWATER_RUIN_BIG = ResourceLocation.parse("minecraft:chests/underwater_ruin_big");

    public void onLootTableLoad(LootTableLoadEvent event) {
        if (CONFIG.shouldSpawnClawsInRuins) {
            if (event.getName().equals(UNDERWATER_RUIN_SMALL) || event.getName().equals(UNDERWATER_RUIN_BIG)) {
                event.getTable().addPool(
                    LootPool
                        .lootPool()
                        .name("crabclaws:crab_claw")
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(EmptyLootItem.emptyItem().setWeight(CONFIG.probabilityOfClawsInRuins))
                        .add(LootItem.lootTableItem(CrabclawsModItems.CRAB_CLAW.get()).setWeight(1))
                        .build()
                );
                LOGGER.info("Added crab claw loot pool to {}", event.getName());
            }
        }
    }

}
