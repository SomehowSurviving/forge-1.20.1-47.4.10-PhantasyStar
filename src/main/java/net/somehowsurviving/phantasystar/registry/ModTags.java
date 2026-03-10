package net.somehowsurviving.phantasystar.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {

    public static class Items {

        public static final TagKey<Item> FREEZE_WEAPONS = ItemTags.create(
                new ResourceLocation("phantasystar", "freeze_weapons")
        );

        public static final TagKey<Item> BURN_WEAPONS = ItemTags.create(
                new ResourceLocation("phantasystar", "burn_weapons")
        );

        public static final TagKey<Item> BERSERK_WEAPONS = ItemTags.create(
                new ResourceLocation("phantasystar", "berserk_weapons")
        );

        public static final TagKey<Item> SPIRIT_WEAPONS = ItemTags.create(
                new ResourceLocation("phantasystar", "spirit_weapons")
        );

        public static final TagKey<Item> SHOCK_WEAPONS = ItemTags.create(
                new ResourceLocation("phantasystar", "shock_weapons")
        );

        public static final TagKey<Item> HELL_WEAPONS = ItemTags.create(
                new ResourceLocation("phantasystar", "hell_weapons")
        );

        public static final TagKey<Item> ARREST_WEAPONS = ItemTags.create(
                new ResourceLocation("phantasystar", "arrest_weapons")
        );

        public static final TagKey<Item> HAVOC_WEAPONS = ItemTags.create(
                new ResourceLocation("phantasystar", "havoc_weapons")
        );

        public static final TagKey<Item> CHARGE_WEAPONS = ItemTags.create(
                new ResourceLocation("phantasystar", "charge_weapons")
        );

        public static final TagKey<Item> DEMON_WEAPONS = ItemTags.create(
                new ResourceLocation("phantasystar", "demon_weapons")
        );

        public static final TagKey<Item> HP_STEAL_WEAPONS = ItemTags.create(
                new ResourceLocation("phantasystar", "hp_steal_weapons")
        );

        public static final TagKey<Item> FOOD_STEAL_WEAPONS = ItemTags.create(
                new ResourceLocation("phantasystar", "food_steal_weapons")
        );

    }
}