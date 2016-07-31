package gigabit101.VoidWorlds;

import net.minecraft.world.World;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.ChunkProviderEnd;

/**
 * Created by Gigabit101 on 31/07/2016.
 */
public class VoidEndGenerator extends WorldProviderEnd
{
    public IChunkGenerator createChunkGenerator()
    {
        return new ChunkProviderEndVoid(worldObj, worldObj.getSeed());
    }

    public static class ChunkProviderEndVoid extends ChunkProviderEnd
    {
        private World world;

        public ChunkProviderEndVoid(World world, long seed)
        {
            super(world, false, seed);
            this.world = world;
        }

        @Override
        public Chunk provideChunk(int x, int z)
        {
            ChunkPrimer primer = new ChunkPrimer();
            Chunk ret = new Chunk(world, primer, x, z);
            Biome[] biomes = world.getBiomeProvider().loadBlockGeneratorData(null, x * 16, z * 16, 16, 16);
            byte[] ids = ret.getBiomeArray();
            for (int i = 0; i < ids.length; ++i)
            {
                ids[i] = (byte) Biome.getIdForBiome(biomes[i]);
            }
            ret.generateSkylightMap();
            return ret;
        }
    }
}
