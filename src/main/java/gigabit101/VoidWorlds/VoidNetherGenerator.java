package gigabit101.VoidWorlds;

import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.ChunkProviderHell;

/**
 * Created by Gigabit101 on 31/07/2016.
 */
public class VoidNetherGenerator extends WorldProviderHell
{
    public IChunkGenerator createChunkGenerator()
    {
        return new ChunkProviderHellVoid(worldObj, false, worldObj.getSeed());
    }

    public static class ChunkProviderHellVoid extends ChunkProviderHell
    {
        private World world;

        public ChunkProviderHellVoid(World world, boolean shouldGenNetherFortress, long seed)
        {
            super(world, shouldGenNetherFortress, seed);
            this.world = world;
        }

        @Override
        public Chunk provideChunk(int x, int z)
        {
            ChunkPrimer data = new ChunkPrimer();

            Chunk ret = new Chunk(world, data, x, z);
            Biome[] biomes = world.getBiomeProvider().loadBlockGeneratorData(null, x * 16, z * 16, 16, 16);
            byte[] ids = ret.getBiomeArray();

            for (int i = 0; i < ids.length; ++i)
            {
                ids[i] = (byte)Biome.getIdForBiome(biomes[i]);
            }

            ret.generateSkylightMap();
            return ret;
        }
    }
}
