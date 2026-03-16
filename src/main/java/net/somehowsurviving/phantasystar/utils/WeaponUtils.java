package net.somehowsurviving.phantasystar.utils;

import com.google.common.collect.Multimap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.somehowsurviving.phantasystar.registry.ModTags;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public class WeaponUtils {

    private WeaponUtils() {} // prevent instantiation

    public static String getWeaponSpecial(ItemStack stack) {
        if (stack == null || stack.isEmpty()) return null;

        CompoundTag tag = stack.getTag();

        if (tag != null) {
            // Check if weapon has a revealed special first
            if (tag.contains("special")) {
                return tag.getString("special");
            }
            // Then check hidden special for rolled weapons
            if (tag.contains("hidden_special")) {
                return tag.getString("hidden_special");
            }
        }

        System.out.println("Item in hand: " + stack.getItem());
        System.out.println("In blizzard tag: " + stack.getItem().builtInRegistryHolder().is(ModTags.Items.BLIZZARD_WEAPONS));
        // fallback to item tags
        if (ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.ARREST_WEAPONS).contains(stack.getItem())) return "arrest";
        if (ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.BERSERK_WEAPONS).contains(stack.getItem())) return "berserk";
        if (ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.BLIZZARD_WEAPONS).contains(stack.getItem())) return "blizzard";
        if (ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.BURN_WEAPONS).contains(stack.getItem())) return "burn";
        if (ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.CHAOS_WEAPONS).contains(stack.getItem())) return "chaos";
        if (ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.CHARGE_WEAPONS).contains(stack.getItem())) return "charge";
        if (ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.DEMON_WEAPONS).contains(stack.getItem())) return "demon";
        if (ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.DEVIL_WEAPONS).contains(stack.getItem())) return "devil";
        if (ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.DRAIN_WEAPONS).contains(stack.getItem())) return "drain";
        if (ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.FILL_WEAPONS).contains(stack.getItem())) return "fill";
        if (ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.FLAME_WEAPONS).contains(stack.getItem())) return "flame";
        if (ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.FREEZE_WEAPONS).contains(stack.getItem())) return "freeze";
        if (ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.GEIST_WEAPONS).contains(stack.getItem())) return "geist";
        if (ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.GUSH_WEAPONS).contains(stack.getItem())) return "gush";
        if (ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.HAVOC_WEAPONS).contains(stack.getItem())) return "havoc";
        if (ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.HEART_WEAPONS).contains(stack.getItem())) return "heart";
        if (ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.HELL_WEAPONS).contains(stack.getItem())) return "hell";
        if (ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.KING_WEAPONS).contains(stack.getItem())) return "king";
        if (ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.LORD_WEAPONS).contains(stack.getItem())) return "lord";
        if (ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.MIND_WEAPONS).contains(stack.getItem())) return "mind";
        if (ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.SOUL_WEAPONS).contains(stack.getItem())) return "soul";
        if (ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.SPIRIT_WEAPONS).contains(stack.getItem())) return "spirit";
        if (ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.SUPERCHARGE_WEAPONS).contains(stack.getItem())) return "supercharge";

        return null;
    }

    public static void rollHiddenSpecial(ItemStack stack) {

        if (stack == null || stack.isEmpty()) return;

        CompoundTag tag = stack.getOrCreateTag();

        // Prevent rerolling weapons that already have specials
        if (tag.contains("special") || tag.contains("hidden_special")) return;

        Random random = new Random();

        if (random.nextFloat() > 0.40f) return;

        List<String> specials = List.of(
                "heat",
                "fire",
                "flame",
                "burn",
                "shock",
                "thunder",
                "storm",
                "tempest",
                "draw",
                "drain",
                "fill",
                "gush",
                "heart",
                "mind",
                "soul",
                "geist",
                "master",
                "lord",
                "king",
                "bind",
                "hold",
                "seize",
                "arrest",
                "panic",
                "riot",
                "havoc",
                "chaos",
                "ice",
                "frost",
                "freeze",
                "blizzard",
                "berserk",
                "spirit",
                "charge",
                "devil",
                "demon",
                "dim",
                "shadow",
                "dark",
                "hell"
        );

        String roll = specials.get(random.nextInt(specials.size()));

        tag.putBoolean("identified", false);
        tag.putString("hidden_special", roll);
    }

    public static void revealHiddenSpecial(ItemStack stack) {

        CompoundTag tag = stack.getTag();
        if (tag == null) return;

        if (!tag.contains("hidden_special")) return;

        String hidden = tag.getString("hidden_special");

        tag.putString("special", hidden);
        tag.putBoolean("identified", true);
        tag.remove("hidden_special");
    }

    public static float getWeaponDamage(ItemStack weapon) {
        float base = 1f;
        Multimap<Attribute, AttributeModifier> modifiers =
                weapon.getAttributeModifiers(EquipmentSlot.MAINHAND);
        Collection<AttributeModifier> damageModifiers = modifiers.get(Attributes.ATTACK_DAMAGE);
        for (AttributeModifier mod : damageModifiers) {
            base += mod.getAmount();
        }
        return base;
    }
}
