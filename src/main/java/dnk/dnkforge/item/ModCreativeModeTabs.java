package dnk.dnkforge.item;

import dnk.dnkforge.DNKforge;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DNKforge.MODID);

    public static RegistryObject<CreativeModeTab> DNKFORGE_TAB = CREATIVE_MODE_TABS.register("dnkforge_tab", () ->
        CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DNK_ITEM.get())).title(Component.translatable("creativemodetab.dnkforge_tab")).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}