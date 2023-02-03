package dnk.dnkforge.item;

import dnk.dnkforge.DNKforge;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DNKforge.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTabs {
    public static CreativeModeTab DNKFORGE_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        DNKFORGE_TAB = event.registerCreativeModeTab(new ResourceLocation(DNKforge.MODID, "dnkforge_tab"), builder -> builder.icon(() -> new ItemStack(ModItems.DNK_ITEM.get())).title(Component.translatable("creativemodetab.dnkforge_tab")));
    }
}