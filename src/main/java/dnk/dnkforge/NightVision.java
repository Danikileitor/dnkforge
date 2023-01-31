package dnk.dnkforge;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = DNKforge.MODID)
public class NightVision extends Enchantment {
    protected NightVision(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots){
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public int getMaxLevel(){
        return 1;
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent e){
        if (EnchantmentHelper.getTagEnchantmentLevel(DNKforge.NIGHTVISION.get(), e.player.getItemBySlot(EquipmentSlot.HEAD)) > 0) {
            MobEffectInstance playerEffect = new MobEffectInstance(MobEffects.NIGHT_VISION, 210, 100, false, false);
            e.player.addEffect(playerEffect);
        }
    }
}