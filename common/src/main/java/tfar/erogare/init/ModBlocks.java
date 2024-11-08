package tfar.erogare.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EndPortalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class ModBlocks {
    public static final Block MYSTERIOUS_FLESH = new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).friction(0.8F).sound(SoundType.SLIME_BLOCK).noOcclusion());
    public static final Block RAW_CODE = new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(1.5F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops());
    public static final Block WATCHING_FLESH = new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).friction(0.8F).sound(SoundType.SLIME_BLOCK).noOcclusion());
    public static final Block DARKNESS = new EndPortalBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).noCollission()){
        @Override
        public RenderShape getRenderShape(BlockState $$0) {
            return RenderShape.MODEL;
        }
    };
}
