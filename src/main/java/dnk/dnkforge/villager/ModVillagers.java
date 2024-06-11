package dnk.dnkforge.villager;

import com.google.common.collect.ImmutableSet;
import dnk.dnkforge.DNKforge;
import dnk.dnkforge.block.ModBlocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, DNKforge.MODID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, DNKforge.MODID);

    public static final RegistryObject<PoiType> LOTTERY_STATION_POI = POI_TYPES.register("lottery_station_poi", () -> new PoiType(ImmutableSet.copyOf(ModBlocks.LOTTERY_STATION.get().getStateDefinition().getPossibleStates()), 1, 1));
    public static final RegistryObject<VillagerProfession> VENDEDOR_LOTERIA = VILLAGER_PROFESSIONS.register("vendedor_loteria", () -> new VillagerProfession("vendedor_loteria", x -> x.get() == LOTTERY_STATION_POI.get(), x -> x.get() == LOTTERY_STATION_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_CARTOGRAPHER));

    public static void register(IEventBus modEventBus) {
        POI_TYPES.register(modEventBus);
        VILLAGER_PROFESSIONS.register(modEventBus);
    }
}
