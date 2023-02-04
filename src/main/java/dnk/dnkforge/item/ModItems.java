package dnk.dnkforge.item;

import dnk.dnkforge.DNKforge;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DNKforge.MODID);

    public static final RegistryObject<Item> DNK_ITEM = ITEMS.register("dnk_item", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NIGHT_VISION = ITEMS.register("night_vision", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LAPISLAZULISWORD = ITEMS.register("lapislazulisword", () -> new SwordItem(Tiers.DIAMOND, 10, 5f, new Item.Properties().stacksTo(1)));

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
