package mhfc.net.common.core.registry;

import java.util.Arrays;

import mhfc.net.common.config.MHFCConfigPotionId;
import org.apache.logging.log4j.Level;

import mhfc.net.MHFCMain;
import mhfc.net.common.helper.MHFCReflectionHelper;
import mhfc.net.common.item.ItemColor;
import mhfc.net.common.potion.PotionFlashed;
import mhfc.net.common.potion.PotionKirinBless;
import mhfc.net.common.potion.PotionPainted;
import mhfc.net.common.potion.PotionParalyze;
import mhfc.net.common.util.services.IServiceKey;
import net.minecraft.potion.Potion;

public class MHFCPotionRegistry {
    private static final int MAX_POTION_IDS = 32767;
    private static final int DEFAULT_OFFSET = 256;

    public static void staticInit() {}

    private static final IServiceKey<MHFCPotionRegistry> serviceAccess = RegistryWrapper
        .registerService("potion registry", MHFCPotionRegistry::new, MHFCMain.initPhase);

    private int nextID = DEFAULT_OFFSET;

    public static Potion stun;
    public final Potion flashed;
    public final Potion kirin_blessing;
    public final Potion painted;

    private MHFCPotionRegistry() {
        kirin_blessing = new PotionKirinBless(MHFCConfigPotionId.PotionKirinBlessId, false, 591932);
        painted = new PotionPainted(MHFCConfigPotionId.PotionPaintedId, true, ItemColor.PINK.getRGB(), true);
        stun = new PotionParalyze(MHFCConfigPotionId.PotionParalyzeId, true, 999999);
        flashed = new PotionFlashed(MHFCConfigPotionId.PotionFlashedId);

        MHFCMain.logger().info("Potions registered");
    }

    public static MHFCPotionRegistry getRegistry() {
        return serviceAccess.getService();
    }
}
