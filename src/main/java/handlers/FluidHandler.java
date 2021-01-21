package handlers;


public class FluidHandler {

	public static void registerCustomMeshesAndStates()
	{
		oil();
	}
	
	public static void oil()
	{
		/*
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(BlockInit.OIL_BLOCK), new ItemMeshDefinition() 
		{
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) 
			{
				return new ModelResourceLocation(Reference.MODID + ":oil", "fluid");
			}
		});

		ModelLoader.setCustomStateMapper(BlockInit.OIL_BLOCK, new StateMapperBase() 
		{	
			@Override
			protected ModelResourceLocation getModelResourceLocation(BlockState state) 
			{
				return new ModelResourceLocation(Reference.MODID + ":oil", "fluid");
			}
		});
		*/
	}
}
