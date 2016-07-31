package gigabit101.VoidWorlds;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

/**
 * Created by Gigabit101 on 31/07/2016.
 */
@Mod(name = VoidWorlds.MOD_NAME, modid = VoidWorlds.MOD_ID, version = VoidWorlds.MOD_VERSION)
public class VoidWorlds
{
    public static final String MOD_NAME = "VoidWorlds";
    public static final String MOD_ID = "VoidWorlds";
    public static final String MOD_VERSION = "1.0.0";

    @Mod.Instance
    public static VoidWorlds instance;

    public static Config config;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
        instance = this;
        String path = event.getSuggestedConfigurationFile().getAbsolutePath().replace(VoidWorlds.MOD_ID, "VoidWorlds");
        config = Config.initialize(new File(path));
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        if(Config.voidNether)
        {
            DimensionManager.unregisterDimension(-1);
            DimensionManager.registerDimension(-1, DimensionType.register("Nether", "_nether", -1, VoidNetherGenerator.class, false));
        }

        if(Config.voidEnd)
        {
            DimensionManager.unregisterDimension(1);
            DimensionManager.registerDimension(1, DimensionType.register("The End", "_end", 1, VoidEndGenerator.class, false));
        }
    }
}
