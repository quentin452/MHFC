package mhfc.net.common.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import mhfc.net.MHFCMain;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MHFCConfigPotionId {
    public static Configuration config;
    private static final List<ConfigCategory> allCategories = new ArrayList();
    private static String CATEOGY_BIOMES;
    public static int PotionKirinBlessId;
    public static int PotionPaintedId;
    public static int PotionParalyzeId;
    public static int PotionFlashedId;

    private static void setupCategories() {
        CATEOGY_BIOMES = makeCategory("potion ids");
    }

    private static String makeCategory(String name) {
        ConfigCategory category = config.getCategory(name);
        category.setLanguageKey(MHFCMain.instance + ".config." + name);
        allCategories.add(category);
        return name;
    }

    public static void setupAndLoad(FMLPreInitializationEvent event) {
        config = new Configuration(new File(event.getModConfigurationDirectory(), "MonsterHunterFORMcPotionId.cfg"));
        setupCategories();
        load();
    }

    public static void load() {
        PotionKirinBlessId = config.get(CATEOGY_BIOMES, "PotionKirinBless ID", 50)
            .getInt();
        PotionPaintedId = config.get(CATEOGY_BIOMES, "PotionPainted ID", 51)
            .getInt();
        PotionParalyzeId = config.get(CATEOGY_BIOMES, "PotionParalyze Biome ID", 52)
            .getInt();
        PotionFlashedId = config.get(CATEOGY_BIOMES, "PotionFlashed Biome ID", 53)
            .getInt();
        if (config.hasChanged()) {
            config.save();
        }
    }
}
