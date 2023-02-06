package dnk.dnkforge.event;

import dnk.dnkforge.DNKforge;
import dnk.dnkforge.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

public class ClientEvents {
    @EventBusSubscriber(modid = DNKforge.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SuppressWarnings({"resource", "null"})
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event){
            if (KeyBinding.DNK_KEY.consumeClick()) {
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("Pulsaste " + (char)event.getKey()));
            }
        }
    }

    @EventBusSubscriber(modid = DNKforge.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event){
            event.register(KeyBinding.DNK_KEY);
        }
    }
}
