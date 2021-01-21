package tileEntities;

import net.minecraft.nbt.CompoundNBT;

public interface IRestorableTileEntity {

    void writeRestorableToNBT(CompoundNBT nbtTagCompound);
    void readRestorableFromNBT(CompoundNBT nbtTagCompound);
}