package com.sandvoxel.immersivemagic.common.spells.lib;

import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.Reference;
import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import com.sandvoxel.immersivemagic.api.spell.ISpellRegstier;
import com.sandvoxel.immersivemagic.common.items.lib.ItemBase;
import com.sandvoxel.immersivemagic.common.magicdata.AffinitiesProvider;
import com.sandvoxel.immersivemagic.common.spells.SpellTypes;
import com.sandvoxel.immersivemagic.common.spells.entity.SpellEntityBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import javax.annotation.Nullable;

public class SpellBase extends ItemBase implements ISpellRegstier {
    private SpellTypes spellType;
    @Nullable
    private Class<? extends SpellEntityBase> entityClass;

    public SpellBase(String internalName, String resourcePath, SpellTypes spellType,Class<? extends SpellEntityBase> entityClass) {
        super(internalName, resourcePath);
        this.spellType = spellType;
        this.entityClass = entityClass;
        this.setCreativeTab(ImmersiveMagic.tabimmmag);
    }

    @Override
    public SpellTypes getSpellType() {
        return spellType;
    }

    protected void dispOutOfMana(EntityPlayer playerIn) {
        playerIn.sendStatusMessage(new TextComponentTranslation("You have no mana to cast this spell!", new Object[0]), true);
    }

    protected void dispNoAffinity(EntityPlayer playerIn) {
        playerIn.sendStatusMessage(new TextComponentTranslation("You do not have the affinity required to cast this spell!", new Object[0]), true);
    }
    

    @Override
    public void RegisterSpellEntity(int id) {
        ResourceLocation resourceLocation = new ResourceLocation(Reference.MOD_ID + ":spell_" + getInteneralName());
        EntityRegistry.registerModEntity(resourceLocation, entityClass,"spell",id, Reference.MOD_ID,64,10,true);
    }
}
