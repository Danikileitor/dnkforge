package dnk.dnkforge.item.custom;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import dnk.dnkforge.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class BoletoPrimitiva extends Item {

    public BoletoPrimitiva(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            if (player.getItemInHand(hand).hasCustomHoverName()) {
                String nombre = player.getItemInHand(hand).getHoverName().getString().trim();
                String numeros[] = nombre.split(" ");
                if (numeros.length == 6) {
                    boolean repetido = false;
                    int boleto[] = new int[6];
                    for (int i = 0; i < numeros.length; i++) {
                        boleto[i] = Integer.parseInt(numeros[i]);
                    }
                    for (int i = 0; i < boleto.length; i++) {
                        if (estaRepetido(boleto[i], boleto, i) || boleto[i] < 1 || boleto[i] > 49) {
                            player.sendSystemMessage(Component.translatable("item.dnkforge.boleto_primitiva.error"));
                            repetido = true;
                            break;
                        }
                    }
                    if (!repetido) {
                        int aciertos = mostrarAciertos(player, generarBoletoGanador(player), boleto);
                        if (aciertos == 6) {
                            player.addItem(new ItemStack(ModItems.PRIMITIVA.get()));
                        }
                    }
                } else {
                    player.sendSystemMessage(Component.translatable("item.dnkforge.boleto_primitiva.error"));
                }
            } else {
                player.sendSystemMessage(Component.translatable("item.dnkforge.boleto_primitiva.error"));
            }
            player.getCooldowns().addCooldown(this, 100);
        }

        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.translatable("item.dnkforge.boleto_primitiva.info").withStyle(ChatFormatting.AQUA));
        } else {
            components.add(Component.translatable("item.dnkforge.boleto_primitiva.hover").withStyle(ChatFormatting.YELLOW));
        }

        super.appendHoverText(stack, level, components, flag);        
    }

    public static int generarAleatorio(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public static boolean estaRepetido(int numero, int[] numeros, int pos) {
        boolean repetido = false;
        for (int i = 0; i < numeros.length; i++) {
            if (i == pos) {
                continue;
            }
            if (numero == numeros[i]) {
                repetido = true;
            }
        }
        return repetido;
    }

    public static int comprobarAciertos(int[] numeros1, int[] numeros2) {
        int aciertos = 0;
        for (int i = 0; i < numeros1.length; i++) {
            for (int j = 0; j < numeros2.length; j++) {
                if (numeros1[i] == numeros2[j]) {
                    aciertos++;
                }
            }
        }
        return aciertos;
    }

    public static int mostrarAciertos(Player player, int[] numeros1, int[] numeros2) {
        String mensaje = new String("");
        Component traducido = Component.translatable("item.dnkforge.boleto_primitiva.aciertos");
        mensaje += traducido.getString();
        int aciertos = comprobarAciertos(numeros1, numeros2);
        mensaje += aciertos;
        player.sendSystemMessage(Component.literal(mensaje));
        return aciertos;
    }

    public static int[] generarBoletoGanador(Player player) {
        int ganador[] = new int[6];
        String mensaje = new String("");
        Component traducido = Component.translatable("item.dnkforge.boleto_primitiva.combinacion");
        mensaje += traducido.getString();
        for (int i = 0; i < ganador.length; i++) {
            ganador[i] = 51 + i;
        }
        for (int i = 0; i < ganador.length; i++) {
            do {
                ganador[i] = generarAleatorio(1, 49);
            } while (estaRepetido(ganador[i], ganador, i));
        }
        Arrays.sort(ganador);
        for (int i = 0; i < ganador.length; i++) {
            mensaje += (ganador[i] + " / ");
        }
        mensaje = mensaje.substring(0, mensaje.length() - 3);
        player.sendSystemMessage(Component.literal(mensaje));
        return ganador;
    }
}