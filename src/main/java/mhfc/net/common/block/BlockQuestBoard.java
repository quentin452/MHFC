package mhfc.net.common.block;

import java.util.List;
import java.util.Random;

import mhfc.net.MHFCMain;
import mhfc.net.common.tile.TileQuestBoard;
import mhfc.net.common.util.lib.MHFCReference;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BlockQuestBoard extends BlockContainer {

	public static int upMask = 0x8;
	public static int offsetMask = 0x4;
	public static int rotationMask = 0x3;

	public BlockQuestBoard() {
		super(Material.WOOD);
		setHardness(3.0f);
		setCreativeTab(MHFCMain.mhfctabs);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	protected boolean canSilkHarvest() {
		return false;
	}

	@Override
	public boolean onBlockActivated(
			World world,
			BlockPos pos,
			IBlockState state,
			EntityPlayer player,
			EnumHand hand,
			ItemStack heldItem,
			EnumFacing side,
			float hitX,
			float hitY,
			float hitZ) {
		if (!player.isSneaking()) {
			player.openGui(
					MHFCMain.instance(),
					MHFCReference.gui_questboard_id,
					world,
					pos.getX(),
					pos.getY(),
					pos.getZ());
			return true;
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileQuestBoard();
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		switch (rand.nextInt(3)) {
		case 0:
			return Items.APPLE;
		case 1:
			return Items.BED;
		case 2:
			return Items.BEEF;
		default:
			return null;
		}
	}

	@Override
	public IBlockState onBlockPlaced(
			World world,
			BlockPos pos,
			EnumFacing facing,
			float hitX,
			float hitY,
			float hitZ,
			int meta,
			EntityLivingBase placer) {

		if (world.isRemote) {
			super.onBlockPlacedBy(world, x, y, z, entity, stack);
		}
		Vec3d vecPos = new Vec3d(placer.posX, placer.posY + placer.getEyeHeight(), placer.posZ);
		float f1 = MathHelper.cos(-placer.rotationYaw * 0.017453292F - (float) Math.PI);
		float f2 = MathHelper.sin(-placer.rotationYaw * 0.017453292F - (float) Math.PI);
		float f3 = -MathHelper.cos(-placer.rotationPitch * 0.017453292F);
		float f4 = MathHelper.sin(-placer.rotationPitch * 0.017453292F);
		Vec3d look = new Vec3d(f2 * f3 * 160, f4 * 160, f1 * f3 * 160);
		Vec3d blockVec = new Vec3d(pos);
		Vec3d lookAim = look.addVector(vecPos.xCoord, vecPos.yCoord, vecPos.zCoord);
		RayTraceResult movPos = world.rayTraceBlocks(vecPos, lookAim, false, false, true);
		int side = getOppositeSide(blockVec.subtract(movPos.hitVec), look);
		if (side > 1) {
			if (side == 2) {
				meta = 0x6;
			}
			if (side == 3) {
				meta = 0x4;
			}
			if (side == 4) {
				meta = 0x5;
			}
			if (side == 5) {
				meta = 0x7;
			}
		} else {
			float cosAng = (float) (hitZ / (Math.sqrt(hitX * hitX + hitZ * hitZ)));
			float angle = (float) (Math.acos(cosAng) / Math.PI * 180);
			if (hitX > 0) {
				angle = 360 - angle;
			}
			angle += 45;
			angle %= 360;
			int metaData = (int) (angle / 90);
			metaData &= rotationMask;
			if (side == 0) {
				metaData += upMask;
			}
			meta = metaData;
		}
		return 3;
	}

	/**
	 * This method returns the side of the next block which would be hit if the look vector was to be traced through the
	 * block, starting at the hit vector (which is relative to the block).
	 *
	 */
	private int getOppositeSide(Vec3d hitVector, Vec3d lookVector) {
		double dX = Math.signum(lookVector.xCoord);
		double dY = lookVector.yCoord / lookVector.xCoord * dX;
		double dZ = lookVector.zCoord / lookVector.xCoord * dX;
		double targetX = lookVector.xCoord > 0 ? 1.0 : 0.0;
		double targetY = lookVector.yCoord > 0 ? 1.0 : 0.0;
		double targetZ = lookVector.zCoord > 0 ? 1.0 : 0.0;
		double tX = (targetX - hitVector.xCoord) / dX;
		double tY = (targetY - hitVector.yCoord) / dY;
		double tZ = (targetZ - hitVector.zCoord) / dZ;
		double t = tX;
		if (!(tX > 0 && tY >= 0 && tZ >= 0)) {
			MHFCMain.logger().debug("Noooo");
		}
		int side = lookVector.xCoord > 0 ? 4 : 5;
		if (!Double.isNaN(tY) && tY < t) {
			side = lookVector.yCoord > 0 ? 0 : 1;
			t = tY;
		}
		if (!Double.isNaN(tZ) && tZ < t) {
			side = lookVector.zCoord > 0 ? 2 : 3;
		}
		return side;
	}

	@Override
	public void addBlockBoundsByState(IBlockState blockState, List<AxisAlignedBB> bounds) {
		int meta = blockState.getBlock().getMetaFromState(blockState);
		boolean boxUpFlag = ((meta & upMask) == upMask) | ((meta & offsetMask) == offsetMask);
		float maxY = !boxUpFlag ? 0.70f : 1.0f;
		float minY = !boxUpFlag ? 0 : 0.3f;
		float minX, maxX, minZ, maxZ;
		if ((meta & 0x1) == 0) {
			minX = 0;
			maxX = 1;
		} else {
			if ((meta & offsetMask) == offsetMask) {
				if ((meta & rotationMask) == 1) {
					minX = 0.75f;
					maxX = 1.0f;
				} else {
					minX = 0;
					maxX = 0.25f;
				}
			} else {
				minX = 0.375f;
				maxX = 0.625f;
			}
		}
		if ((meta & 0x1) == 1) {
			minZ = 0;
			maxZ = 1;
		} else {
			if ((meta & offsetMask) == offsetMask) {
				if ((meta & rotationMask) == 2) {
					minZ = 0.75f;
					maxZ = 1.0f;
				} else {
					minZ = 0;
					maxZ = 0.25f;
				}
			} else {
				minZ = 0.375f;
				maxZ = 0.625f;
			}
		}
	}

	@Override
	public void addCollisionBoxToList(
			IBlockState state,
			World worldIn,
			BlockPos pos,
			AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes,
			Entity entityIn) {
		this.addBlockBoundsByState(state, collidingBoxes);
	}
}
