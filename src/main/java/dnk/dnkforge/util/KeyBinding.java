package dnk.dnkforge.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

public class KeyBinding {
    public static final String KEY_CATEGORY_DNKFORGE = "key.category.dnkforge.dnkforge";
    public static final String KEY_DNK = "key.dnkforge.dnk";

    public static final KeyMapping DNK_KEY = new KeyMapping(KEY_DNK, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, InputConstants.KEY_O, KEY_CATEGORY_DNKFORGE);
}
