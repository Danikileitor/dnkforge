package dnk.dnkforge.event;

import java.util.List;
import dnk.dnkforge.DNKforge;
import dnk.dnkforge.item.ModItems;
import dnk.dnkforge.villager.ModVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

public class ModEvents {
    @EventBusSubscriber(modid = DNKforge.MODID)
    public static class ForgeEvents {

        @SubscribeEvent
        public static void addCustomTrades(VillagerTradesEvent event) {
            if (event.getType() == ModVillagers.VENDEDOR_LOTERIA.get()) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(ModItems.BOLETO_PRIMITIVA.get(), 1);
                int villagerLevel = 1;

                trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(Items.EMERALD, 10),
                        stack, 10, 8, 0.02F));
            }
        }
    }
}
