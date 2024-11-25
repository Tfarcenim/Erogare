package tfar.erogare.datagen.data;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;
import tfar.erogare.Erogare;
import tfar.erogare.init.ModItems;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, ModItems.CODE_SWORD)
                .requires(ModItems.SHATTERED_BLADE)
                .requires(ModItems.SHATTERED_HILT)
                .unlockedBy(getHasName(ModItems.SHATTERED_HILT),has(ModItems.SHATTERED_HILT))
                .save(pWriter);

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GREEN_MYSTERIOUS_SUBSTANCE),Ingredient.of(Items.SHIELD),Ingredient.of(ModItems.MYSTERIOUS_GEM),
                RecipeCategory.COMBAT,ModItems.CODE_SHIELD)
                .unlocks("has_mysterious_gem", has(ModItems.MYSTERIOUS_GEM))
                .save(pWriter, Erogare.id("code_shield"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GREEN_MYSTERIOUS_SUBSTANCE),Ingredient.of(Items.IRON_SWORD),Ingredient.of(ModItems.BROWN_MYSTERIOUS_SUBSTANCE),
                        RecipeCategory.COMBAT,ModItems.SACRIFICIAL_BLADE)
                .unlocks("has_mysterious_substance", has(ModItems.GREEN_MYSTERIOUS_SUBSTANCE))
                .save(pWriter, Erogare.id("sacrificial_blade"));
    }
}
