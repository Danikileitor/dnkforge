package dnk.dnkforge.block.custom;

import java.util.Arrays;

import dnk.dnkforge.item.ModItems;
import dnk.dnkforge.util.ModUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class LotteryStation extends Block {

    public LotteryStation(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {

        if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            if (player.getItemInHand(hand).getItem() == ModItems.BOLETO_PRIMITIVA.get()) {
                generarBoleto(player, hand);
                player.getCooldowns().addCooldown(player.getItemInHand(hand).getItem(), 20);;
            } else {
                player.sendSystemMessage(Component.literal("Utiliza un boleto de La Primitiva mientras estés agachado para generar una combinación aleatoria"));
            }
        }

        return super.use(state, level, blockPos, player, hand, blockHitResult);
    }

    public static void generarBoleto(Player player, InteractionHand hand) {
        int boleto[] = new int[6];
        String nombre = new String("");
        for (int i = 0; i < boleto.length; i++) {
            boleto[i] = 51 + i;
        }
        for (int i = 0; i < boleto.length; i++) {
            do {
                boleto[i] = ModUtils.generarAleatorio(1, 49);
            } while (ModUtils.estaRepetido(boleto[i], boleto, i));
        }
        Arrays.sort(boleto);
        for (int i = 0; i < boleto.length; i++) {
            nombre += (boleto[i] + " ");
        }
        nombre = nombre.trim();
        player.getItemInHand(hand).setHoverName(Component.literal(nombre));
    }
}
