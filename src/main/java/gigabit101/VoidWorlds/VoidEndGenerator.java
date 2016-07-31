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
//        private Random endRNG;
//        private WorldGenSpikes spikes = new WorldGenSpikes();
//        private MapGenEndCity endCityGen = new MapGenEndCity(this);

        public ChunkProviderEndVoid(World world, long seed)
        {
            super(world, false, seed);
            this.world = world;
//            this.endRNG = new Random(seed);
        }

//        @Override public void populate(int x, int z)
//        {
//            if (YUNoMakeGoodMap.instance.shouldBeVoid(world))
//            {
//                WorldGenSpikes.EndSpike[] aworldgenspikes$endspike = BiomeEndDecorator.getSpikesForWorld(world);
//
//                for (WorldGenSpikes.EndSpike worldgenspikes$endspike : aworldgenspikes$endspike)
//                {
//                    if (worldgenspikes$endspike.doesStartInChunk(new BlockPos(x*16, 0, z*16)))
//                    {
//                        this.spikes.setSpike(worldgenspikes$endspike);
//                        this.spikes.generate(world, endRNG, new BlockPos(worldgenspikes$endspike.getCenterX(), 45, worldgenspikes$endspike.getCenterZ()));
//                    }
//                }
//            }
//
//            if (YUNoMakeGoodMap.instance.shouldGenerateEndCities(this.world))
//            {
//                this.endCityGen.generateStructure(world, endRNG, new ChunkPos(x, z));
//            }
//
//            if (x == 0 && z == 0)
//            {
//                // Allows exit portal to be placed correctly. DragonFightManager will take over from here...
//                world.setBlockState(new BlockPos(0, 45, 0), Blocks.END_STONE.getDefaultState());
//            }
//        }

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
