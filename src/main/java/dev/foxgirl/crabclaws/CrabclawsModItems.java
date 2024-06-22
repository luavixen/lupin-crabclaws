package dev.foxgirl.crabclaws;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CrabclawsModItems {

    public static final DeferredRegister<Item> REGISTRY;
    public static final RegistryObject<Item> CRAB_CLAW;

    public CrabclawsModItems() {
    }

    static {
        REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, "crabclaws");
        CRAB_CLAW = REGISTRY.register("crab_claw", () -> {
            return new CrabClawItem();
        });
    }

}
