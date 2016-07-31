package gigabit101.VoidWorlds;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by Gigabit101 on 31/07/2016.
 */
public class Config
{
    public static Config instance = null;

    public static boolean voidEnd;
    public static boolean voidNether;

    public static String CATEGORY_WORLD = "world";


    public static Configuration config;

    private Config(File configFile)
    {
        config = new Configuration(configFile);
        config.load();

        Config.Configs();

        config.save();
    }

    public static Config initialize(File configFile)
    {
        if (instance == null)
        {
            instance = new Config(configFile);
        }
        else
        {
            throw new IllegalStateException("Cannot initialize Crystek config twice");
        }
        return instance;
    }

    public static Config instance()
    {
        if (instance == null)
        {
            throw new IllegalStateException("Instance of Crystek requested before initialization");
        }
        return instance;
    }

    public static void Configs()
    {
        if(config.hasChanged())
        {
            voidEnd = config.get(CATEGORY_WORLD,
                    "voidEnd", true,
                    "if true the end will be generated as a void world")
                    .getBoolean();

            voidNether = config.get(CATEGORY_WORLD,
                    "voidNether", true,
                    "if true the nether will be generated as a void world")
                    .getBoolean();
            config.save();
        }
    }
}
