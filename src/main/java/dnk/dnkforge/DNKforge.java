package dnk.dnkforge;

import com.mojang.logging.LogUtils;
import dnk.dnkforge.block.ModBlocks;
import dnk.dnkforge.enchantment.ModEnchantments;
import dnk.dnkforge.item.ModCreativeModeTabs;
import dnk.dnkforge.item.ModItems;
import dnk.dnkforge.villager.ModVillagers;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(DNKforge.MODID)
public class DNKforge
{
    public static final String MODID = "dnkforge";
    private static final Logger LOGGER = LogUtils.getLogger();

    public DNKforge()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModEnchantments.register(modEventBus);
        ModVillagers.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HELLO FROM COMMON SETUP");
        event.enqueueWork(() -> {
            ModVillagers.registerPOIs();
        });
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event)
    {
        if (event.getTab() == ModCreativeModeTabs.DNKFORGE_TAB) {
            event.accept(ModBlocks.DNK_BLOCK);
            event.accept(ModItems.DNK_ITEM);
            event.accept(ModItems.NIGHT_VISION);
            event.accept(ModItems.LAPISLAZULISWORD);
            event.accept(ModBlocks.LOTTERY_STATION);
            event.accept(ModItems.BOLETO_PRIMITIVA);
            event.accept(ModItems.PRIMITIVA);
        }

        if (event.getTab() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.DNK_BLOCK);
        }

        if (event.getTab() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModBlocks.LOTTERY_STATION);
        }

        if (event.getTab() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.LAPISLAZULISWORD);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
