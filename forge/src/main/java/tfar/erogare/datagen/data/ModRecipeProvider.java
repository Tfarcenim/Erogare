package tfar.erogare.datagen.data;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
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
                .unlockedBy("has_shatered_hilt",has(ModItems.SHATTERED_HILT))
                .save(pWriter);
    }
}
