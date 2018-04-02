package com.sandvoxel.immersivemagic.common.spells;

import com.sandvoxel.immersivemagic.common.spells.entity.Liquefact;
import com.sandvoxel.immersivemagic.common.spells.entity.SpellLight;
import com.sandvoxel.immersivemagic.common.spells.lib.SpellBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

/**
 * Created by CrazyGrape on 4/1/2018.
 */
public class LiquefactSpell extends SpellBase {
    public LiquefactSpell() {
        super("liquefactspell", "", SpellTypes.THROWABLE_SPELL, Liquefact.class);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

        if(!worldIn.isRemote){
            Liquefact liquef = new Liquefact(worldIn,playerIn);
            liquef.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, (float) -(playerIn.motionX+playerIn.motionY+playerIn.motionZ)+1F, 1.0F);
            worldIn.spawnEntity(liquef);
        }


        return new ActionResult(EnumActionResult.SUCCESS,playerIn.getHeldItem(handIn));
    }
}