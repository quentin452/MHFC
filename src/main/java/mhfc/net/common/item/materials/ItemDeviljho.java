package mhfc.net.common.item.materials;

import java.util.List;

import mhfc.net.MHFCMain;
import mhfc.net.common.core.registry.MHFCItemRegistry;
import mhfc.net.common.index.ResourceInterface;
import mhfc.net.common.item.AbstractSubTypedItem;
import mhfc.net.common.item.IItemColored;
import mhfc.net.common.item.ItemColor;
import mhfc.net.common.item.materials.ItemDeviljho.DeviljhoSubType;
import mhfc.net.common.util.SubTypedItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemDeviljho extends AbstractSubTypedItem<DeviljhoSubType> implements IItemColored {
	public static enum DeviljhoSubType implements SubTypedItem.SubTypeEnum<Item> {
		SCALE(ResourceInterface.item_deviljho0_name, ItemColor.GREEN),
		FANG(ResourceInterface.item_deviljho1_name, ItemColor.GREEN),
		HIDE(ResourceInterface.item_deviljho2_name, ItemColor.GREEN),
		TALON(ResourceInterface.item_deviljho3_name, ItemColor.GREEN),
		SCALP(ResourceInterface.item_deviljho4_name, ItemColor.GREEN),
		TAIL(ResourceInterface.item_deviljho5_name, ItemColor.GREEN);

		public final String name;
		public final ItemColor color;

		private DeviljhoSubType(String name, ItemColor color) {
			this.name = name;
			this.color = color;
		}

		@Override
		public String getName() {
			return this.name;
		}

		@Override
		public Item getBaseItem() {
			return MHFCItemRegistry.getRegistry().deviljhodrops;
		}

		@Override
		public ItemColor getColor() {
			return this.color;
		}
	}

	public ItemDeviljho() {
		super(DeviljhoSubType.class);
		setUnlocalizedName(ResourceInterface.item_deviljho_basename);
		setCreativeTab(MHFCMain.mhfctabs);
	}

	@Override
	public void addInformation(
			ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer,
			List<String> par3List,
			boolean par4) {
		switch (itemPerk.getSubType(par1ItemStack)) {
		case SCALP:
			par3List.add("Rare Drop by Deviljho");
			break;
		default:
			par3List.add("Drop by Deviljho");
			break;
		}
	}
}
