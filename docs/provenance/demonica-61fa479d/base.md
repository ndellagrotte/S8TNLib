# Provenance manifest

- baseline: `gtnhlib-base/9644810c00` (upstream layout)
- compared: `f122acc3215f99ca18d01954912d28a64b327f49` (s8tnlib layout)

| scope | verbatim | adapted | new | dropped |
|---|---|---|---|---|
| gtnhlib | 28 | 29 | 3 | 0 |
| tests | 0 | 0 | 2 | 0 |
| upstream-tests | 1 | 0 | 0 | 0 |
| main | 28 | 29 | 3 | 333 |

## Scope `gtnhlib`

- baseline roots: `src/main/java` (`com/gtnewhorizon/gtnhlib/asm/ClassConstantPoolParser.java`, `com/gtnewhorizon/gtnhlib/bytebuf/APIUtil.java`, `com/gtnewhorizon/gtnhlib/bytebuf/CheckIntrinsics.java`, `com/gtnewhorizon/gtnhlib/bytebuf/Checks.java`, `com/gtnewhorizon/gtnhlib/bytebuf/MemoryManage.java`, `com/gtnewhorizon/gtnhlib/bytebuf/MemoryStack.java`, `com/gtnewhorizon/gtnhlib/bytebuf/MemoryUtilities.java`, `com/gtnewhorizon/gtnhlib/bytebuf/MultiReleaseMemCopy.java`, `com/gtnewhorizon/gtnhlib/bytebuf/MultiReleaseTextDecoding.java`, `com/gtnewhorizon/gtnhlib/bytebuf/Pointer.java`, `com/gtnewhorizon/gtnhlib/bytebuf/StackWalkUtil.java`, `com/gtnewhorizon/gtnhlib/bytebuf/package-info.java`, `com/gtnewhorizon/gtnhlib/client/opengl/FBOFunctions.java`, `com/gtnewhorizon/gtnhlib/client/opengl/GLCaps.java`, `com/gtnewhorizon/gtnhlib/client/opengl/UniversalVAO.java`, `com/gtnewhorizon/gtnhlib/client/renderer/CallbackTessellator.java`, `com/gtnewhorizon/gtnhlib/client/renderer/DirectDrawCallback.java`, `com/gtnewhorizon/gtnhlib/client/renderer/DirectTessellator.java`, `com/gtnewhorizon/gtnhlib/client/renderer/ITessellatorInstance.java`, `com/gtnewhorizon/gtnhlib/client/renderer/RuntimeOptionsBridge.java`, `com/gtnewhorizon/gtnhlib/client/renderer/TessellatorManager.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/api/util/ColorABGR.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/api/util/ColorU8.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/api/util/NormI8.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/primitive/ModelPrimitiveView.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/ModelQuad.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/ModelQuadView.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/ModelQuadViewMutable.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/properties/ModelQuadFacing.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/properties/ModelQuadFlags.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/util/MathUtil.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/util/ModelQuadUtil.java`, `com/gtnewhorizon/gtnhlib/client/renderer/postprocessing/DepthTextureProvider.java`, `com/gtnewhorizon/gtnhlib/client/renderer/postprocessing/PostProcessingBridge.java`, `com/gtnewhorizon/gtnhlib/client/renderer/stacks/IStateStack.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/BaseVAO.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/IVertexArrayObject.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/IndexBuffer.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/IndexedVAO.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/VAOManager.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/VaoFunctions.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/VertexArrayUnsupported.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/VertexBufferFactory.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/VertexBufferType.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vbo/IVertexBuffer.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vbo/VBOManager.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vbo/VertexBuffer.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/DefaultVertexFormat.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/VertexFlags.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/VertexFormat.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/VertexFormatElement.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/VertexOptimizer.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/ColorVertexAttributeWriter.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/IVertexAttributeWriter.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/LightVertexAttributeWriter.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/NormalVertexAttributeWriter.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/PositionVertexAttributeWriter.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/TextureVertexAttributeWriter.java`, `com/gtnewhorizon/gtnhlib/compat/Mods.java`, `com/gtnewhorizon/gtnhlib/util/font/IFontParameters.java`)
- compared roots: `src/main/java` (`com/gtnewhorizon/gtnhlib/asm/ClassConstantPoolParser.java`, `com/gtnewhorizon/gtnhlib/bytebuf/APIUtil.java`, `com/gtnewhorizon/gtnhlib/bytebuf/CheckIntrinsics.java`, `com/gtnewhorizon/gtnhlib/bytebuf/Checks.java`, `com/gtnewhorizon/gtnhlib/bytebuf/MemoryManage.java`, `com/gtnewhorizon/gtnhlib/bytebuf/MemoryStack.java`, `com/gtnewhorizon/gtnhlib/bytebuf/MemoryUtilities.java`, `com/gtnewhorizon/gtnhlib/bytebuf/MultiReleaseMemCopy.java`, `com/gtnewhorizon/gtnhlib/bytebuf/MultiReleaseTextDecoding.java`, `com/gtnewhorizon/gtnhlib/bytebuf/Pointer.java`, `com/gtnewhorizon/gtnhlib/bytebuf/StackWalkUtil.java`, `com/gtnewhorizon/gtnhlib/bytebuf/package-info.java`, `com/gtnewhorizon/gtnhlib/client/opengl/FBOFunctions.java`, `com/gtnewhorizon/gtnhlib/client/opengl/GLCaps.java`, `com/gtnewhorizon/gtnhlib/client/opengl/UniversalVAO.java`, `com/gtnewhorizon/gtnhlib/client/renderer/CallbackTessellator.java`, `com/gtnewhorizon/gtnhlib/client/renderer/DirectDrawCallback.java`, `com/gtnewhorizon/gtnhlib/client/renderer/DirectTessellator.java`, `com/gtnewhorizon/gtnhlib/client/renderer/ITessellatorInstance.java`, `com/gtnewhorizon/gtnhlib/client/renderer/RuntimeOptionsBridge.java`, `com/gtnewhorizon/gtnhlib/client/renderer/TessellatorManager.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/api/util/ColorABGR.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/api/util/ColorU8.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/api/util/NormI8.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/primitive/ModelPrimitiveView.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/ModelQuad.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/ModelQuadView.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/ModelQuadViewMutable.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/properties/ModelQuadFacing.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/properties/ModelQuadFlags.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/util/MathUtil.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/util/ModelQuadUtil.java`, `com/gtnewhorizon/gtnhlib/client/renderer/postprocessing/DepthTextureProvider.java`, `com/gtnewhorizon/gtnhlib/client/renderer/postprocessing/PostProcessingBridge.java`, `com/gtnewhorizon/gtnhlib/client/renderer/stacks/IStateStack.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/BaseVAO.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/IVertexArrayObject.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/IndexBuffer.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/IndexedVAO.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/VAOManager.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/VaoFunctions.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/VertexArrayUnsupported.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/VertexBufferFactory.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/VertexBufferType.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vbo/IVertexBuffer.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vbo/VBOManager.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vbo/VertexBuffer.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/DefaultVertexFormat.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/VertexFlags.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/VertexFormat.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/VertexFormatElement.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/VertexOptimizer.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/ColorVertexAttributeWriter.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/IVertexAttributeWriter.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/LightVertexAttributeWriter.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/NormalVertexAttributeWriter.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/PositionVertexAttributeWriter.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/TextureVertexAttributeWriter.java`, `com/gtnewhorizon/gtnhlib/compat/Mods.java`, `com/gtnewhorizon/gtnhlib/util/font/IFontParameters.java`)

### Adapted (29)

- `com/gtnewhorizon/gtnhlib/bytebuf/CheckIntrinsics.java`
- `com/gtnewhorizon/gtnhlib/bytebuf/MemoryManage.java`
- `com/gtnewhorizon/gtnhlib/bytebuf/MemoryUtilities.java`
- `com/gtnewhorizon/gtnhlib/bytebuf/MultiReleaseMemCopy.java`
- `com/gtnewhorizon/gtnhlib/bytebuf/MultiReleaseTextDecoding.java`
- `com/gtnewhorizon/gtnhlib/bytebuf/Pointer.java`
- `com/gtnewhorizon/gtnhlib/bytebuf/StackWalkUtil.java`
- `com/gtnewhorizon/gtnhlib/bytebuf/package-info.java`
- `com/gtnewhorizon/gtnhlib/client/opengl/UniversalVAO.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/CallbackTessellator.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/DirectTessellator.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/TessellatorManager.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/ModelQuad.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/properties/ModelQuadFacing.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/cel/util/ModelQuadUtil.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vao/VAOManager.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vbo/IVertexBuffer.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vbo/VertexBuffer.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vertex/VertexFlags.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vertex/VertexFormat.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vertex/VertexFormatElement.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/ColorVertexAttributeWriter.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/IVertexAttributeWriter.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/LightVertexAttributeWriter.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/NormalVertexAttributeWriter.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/PositionVertexAttributeWriter.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/TextureVertexAttributeWriter.java`
- `com/gtnewhorizon/gtnhlib/compat/Mods.java`
- `com/gtnewhorizon/gtnhlib/util/font/IFontParameters.java`

### New (3)

- `com/gtnewhorizon/gtnhlib/client/renderer/RuntimeOptionsBridge.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/postprocessing/DepthTextureProvider.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/postprocessing/PostProcessingBridge.java`

### Dropped (0)


## Scope `tests`

- baseline roots: `src/test/java` (`com/gtnewhorizon/gtnhlib/bytebuf/MemoryUtilitiesTest.java`, `com/gtnewhorizon/gtnhlib/client/renderer/TessellatorManagerTest.java`)
- compared roots: `src/test/java` (`com/gtnewhorizon/gtnhlib/bytebuf/MemoryUtilitiesTest.java`, `com/gtnewhorizon/gtnhlib/client/renderer/TessellatorManagerTest.java`)

### Adapted (0)


### New (2)

- `com/gtnewhorizon/gtnhlib/bytebuf/MemoryUtilitiesTest.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/TessellatorManagerTest.java`

### Dropped (0)


## Scope `upstream-tests`

- baseline roots: `src/test/java` (`com/gtnewhorizon/gtnhlib/client/renderer/VertexFormatTest.java`)
- compared roots: `src/test/java` (`com/gtnewhorizon/gtnhlib/client/renderer/VertexFormatTest.java`)

### Adapted (0)


### New (0)


### Dropped (0)


## Scope `main`

- baseline roots: `src/main/java`
- compared roots: `src/main/java`

### Adapted (29)

- `com/gtnewhorizon/gtnhlib/bytebuf/CheckIntrinsics.java`
- `com/gtnewhorizon/gtnhlib/bytebuf/MemoryManage.java`
- `com/gtnewhorizon/gtnhlib/bytebuf/MemoryUtilities.java`
- `com/gtnewhorizon/gtnhlib/bytebuf/MultiReleaseMemCopy.java`
- `com/gtnewhorizon/gtnhlib/bytebuf/MultiReleaseTextDecoding.java`
- `com/gtnewhorizon/gtnhlib/bytebuf/Pointer.java`
- `com/gtnewhorizon/gtnhlib/bytebuf/StackWalkUtil.java`
- `com/gtnewhorizon/gtnhlib/bytebuf/package-info.java`
- `com/gtnewhorizon/gtnhlib/client/opengl/UniversalVAO.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/CallbackTessellator.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/DirectTessellator.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/TessellatorManager.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/ModelQuad.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/properties/ModelQuadFacing.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/cel/util/ModelQuadUtil.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vao/VAOManager.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vbo/IVertexBuffer.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vbo/VertexBuffer.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vertex/VertexFlags.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vertex/VertexFormat.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vertex/VertexFormatElement.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/ColorVertexAttributeWriter.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/IVertexAttributeWriter.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/LightVertexAttributeWriter.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/NormalVertexAttributeWriter.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/PositionVertexAttributeWriter.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/TextureVertexAttributeWriter.java`
- `com/gtnewhorizon/gtnhlib/compat/Mods.java`
- `com/gtnewhorizon/gtnhlib/util/font/IFontParameters.java`

### New (3)

- `com/gtnewhorizon/gtnhlib/client/renderer/RuntimeOptionsBridge.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/postprocessing/DepthTextureProvider.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/postprocessing/PostProcessingBridge.java`

### Dropped (333)

- `com/gtnewhorizon/gtnhlib/ClientProxy.java`
- `com/gtnewhorizon/gtnhlib/CommonProxy.java`
- `com/gtnewhorizon/gtnhlib/ExampleConfig.java`
- `com/gtnewhorizon/gtnhlib/GTNHLib.java`
- `com/gtnewhorizon/gtnhlib/GTNHLibConfig.java`
- `com/gtnewhorizon/gtnhlib/api/BlockModelInfo.java`
- `com/gtnewhorizon/gtnhlib/api/CapturingTesselator.java`
- `com/gtnewhorizon/gtnhlib/api/IBlockModelProvider.java`
- `com/gtnewhorizon/gtnhlib/api/IBlockWithCustomSound.java`
- `com/gtnewhorizon/gtnhlib/api/ITranslucentItem.java`
- `com/gtnewhorizon/gtnhlib/api/MusicRecordMetadataProvider.java`
- `com/gtnewhorizon/gtnhlib/api/thaumcraft/EnhancedInfusionRecipe.java`
- `com/gtnewhorizon/gtnhlib/api/thaumcraft/FormattedResearchPage.java`
- `com/gtnewhorizon/gtnhlib/asm/ASMUtil.java`
- `com/gtnewhorizon/gtnhlib/asm/ByteCodeUtil.java`
- `com/gtnewhorizon/gtnhlib/asm/SafeClassWriter.java`
- `com/gtnewhorizon/gtnhlib/blockpos/BlockPos.java`
- `com/gtnewhorizon/gtnhlib/blockpos/IBlockPos.java`
- `com/gtnewhorizon/gtnhlib/blockpos/IMutableBlockPos.java`
- `com/gtnewhorizon/gtnhlib/blockpos/IMutableWorldReferent.java`
- `com/gtnewhorizon/gtnhlib/blockpos/IWorldReferent.java`
- `com/gtnewhorizon/gtnhlib/blocks/util/BFSLeafDecay.java`
- `com/gtnewhorizon/gtnhlib/blockstate/command/BlockStateCommand.java`
- `com/gtnewhorizon/gtnhlib/blockstate/core/BlockProperty.java`
- `com/gtnewhorizon/gtnhlib/blockstate/core/BlockPropertyTrait.java`
- `com/gtnewhorizon/gtnhlib/blockstate/core/BlockState.java`
- `com/gtnewhorizon/gtnhlib/blockstate/core/BlockStateImpl.java`
- `com/gtnewhorizon/gtnhlib/blockstate/core/InvalidPropertyJsonException.java`
- `com/gtnewhorizon/gtnhlib/blockstate/core/InvalidPropertyTextException.java`
- `com/gtnewhorizon/gtnhlib/blockstate/core/MetaBlockProperty.java`
- `com/gtnewhorizon/gtnhlib/blockstate/core/TransformableProperty.java`
- `com/gtnewhorizon/gtnhlib/blockstate/core/VectorTransformableProperty.java`
- `com/gtnewhorizon/gtnhlib/blockstate/init/BlockPropertyInit.java`
- `com/gtnewhorizon/gtnhlib/blockstate/init/VanillaBlockProperties.java`
- `com/gtnewhorizon/gtnhlib/blockstate/mixin/BlockSkullExt.java`
- `com/gtnewhorizon/gtnhlib/blockstate/properties/AxisBlockProperty.java`
- `com/gtnewhorizon/gtnhlib/blockstate/properties/BooleanBlockProperty.java`
- `com/gtnewhorizon/gtnhlib/blockstate/properties/DirectionBlockProperty.java`
- `com/gtnewhorizon/gtnhlib/blockstate/properties/FloatBlockProperty.java`
- `com/gtnewhorizon/gtnhlib/blockstate/properties/IntegerBlockProperty.java`
- `com/gtnewhorizon/gtnhlib/blockstate/properties/OrientationBlockProperty.java`
- `com/gtnewhorizon/gtnhlib/blockstate/registry/BlockPropertyFactory.java`
- `com/gtnewhorizon/gtnhlib/blockstate/registry/BlockPropertyRegistry.java`
- `com/gtnewhorizon/gtnhlib/brigadier/BrigadierApi.java`
- `com/gtnewhorizon/gtnhlib/capability/Capabilities.java`
- `com/gtnewhorizon/gtnhlib/capability/CapabilityProvider.java`
- `com/gtnewhorizon/gtnhlib/capability/item/ItemIO.java`
- `com/gtnewhorizon/gtnhlib/capability/item/ItemSink.java`
- `com/gtnewhorizon/gtnhlib/capability/item/ItemSource.java`
- `com/gtnewhorizon/gtnhlib/chat/AbstractChatComponentCustom.java`
- `com/gtnewhorizon/gtnhlib/chat/ChatComponentCustomRegistry.java`
- `com/gtnewhorizon/gtnhlib/chat/customcomponents/AbstractChatComponentBuffer.java`
- `com/gtnewhorizon/gtnhlib/chat/customcomponents/AbstractChatComponentNumber.java`
- `com/gtnewhorizon/gtnhlib/chat/customcomponents/ChatComponentEnergy.java`
- `com/gtnewhorizon/gtnhlib/chat/customcomponents/ChatComponentFluid.java`
- `com/gtnewhorizon/gtnhlib/chat/customcomponents/ChatComponentFluidName.java`
- `com/gtnewhorizon/gtnhlib/chat/customcomponents/ChatComponentItemName.java`
- `com/gtnewhorizon/gtnhlib/chat/customcomponents/ChatComponentNumber.java`
- `com/gtnewhorizon/gtnhlib/client/ResourcePackUpdater/ChatNotifier.java`
- `com/gtnewhorizon/gtnhlib/client/ResourcePackUpdater/GitHubReleaseClient.java`
- `com/gtnewhorizon/gtnhlib/client/ResourcePackUpdater/PackMcmetaReader.java`
- `com/gtnewhorizon/gtnhlib/client/ResourcePackUpdater/PackVersion.java`
- `com/gtnewhorizon/gtnhlib/client/ResourcePackUpdater/ReleaseMatch.java`
- `com/gtnewhorizon/gtnhlib/client/ResourcePackUpdater/ResourcePackUpdateChecker.java`
- `com/gtnewhorizon/gtnhlib/client/ResourcePackUpdater/ResourcePackUpdateEventHandler.java`
- `com/gtnewhorizon/gtnhlib/client/ResourcePackUpdater/RpUpdaterLog.java`
- `com/gtnewhorizon/gtnhlib/client/ResourcePackUpdater/UpdaterMeta.java`
- `com/gtnewhorizon/gtnhlib/client/VillagerNames.java`
- `com/gtnewhorizon/gtnhlib/client/event/LivingEquipmentChangeEvent.java`
- `com/gtnewhorizon/gtnhlib/client/event/RenderTooltipEvent.java`
- `com/gtnewhorizon/gtnhlib/client/event/WorldDeletionEvent.java`
- `com/gtnewhorizon/gtnhlib/client/model/BakeData.java`
- `com/gtnewhorizon/gtnhlib/client/model/BakedModelBuilder.java`
- `com/gtnewhorizon/gtnhlib/client/model/BakedModelQuadContext.java`
- `com/gtnewhorizon/gtnhlib/client/model/ItemContext.java`
- `com/gtnewhorizon/gtnhlib/client/model/JSONVariant.java`
- `com/gtnewhorizon/gtnhlib/client/model/ModelISBRH.java`
- `com/gtnewhorizon/gtnhlib/client/model/NormalHelper.java`
- `com/gtnewhorizon/gtnhlib/client/model/Weighted.java`
- `com/gtnewhorizon/gtnhlib/client/model/WorldContext.java`
- `com/gtnewhorizon/gtnhlib/client/model/baked/BakedModel.java`
- `com/gtnewhorizon/gtnhlib/client/model/baked/MonopartModel.java`
- `com/gtnewhorizon/gtnhlib/client/model/baked/MultipartModel.java`
- `com/gtnewhorizon/gtnhlib/client/model/baked/PileOfQuads.java`
- `com/gtnewhorizon/gtnhlib/client/model/color/BlockColor.java`
- `com/gtnewhorizon/gtnhlib/client/model/color/IBlockColor.java`
- `com/gtnewhorizon/gtnhlib/client/model/loading/BackingResourceManager.java`
- `com/gtnewhorizon/gtnhlib/client/model/loading/GlobalResourceManager.java`
- `com/gtnewhorizon/gtnhlib/client/model/loading/ModelDeserializer.java`
- `com/gtnewhorizon/gtnhlib/client/model/loading/ModelRegistry.java`
- `com/gtnewhorizon/gtnhlib/client/model/loading/ModelResourcePack.java`
- `com/gtnewhorizon/gtnhlib/client/model/loading/RPInfo.java`
- `com/gtnewhorizon/gtnhlib/client/model/loading/ResourceLoc.java`
- `com/gtnewhorizon/gtnhlib/client/model/state/BlockState.java`
- `com/gtnewhorizon/gtnhlib/client/model/state/MissingState.java`
- `com/gtnewhorizon/gtnhlib/client/model/state/MonopartState.java`
- `com/gtnewhorizon/gtnhlib/client/model/state/MultipartState.java`
- `com/gtnewhorizon/gtnhlib/client/model/state/StateDeserializer.java`
- `com/gtnewhorizon/gtnhlib/client/model/state/StateModelMap.java`
- `com/gtnewhorizon/gtnhlib/client/model/unbaked/JSONModel.java`
- `com/gtnewhorizon/gtnhlib/client/model/unbaked/MissingModel.java`
- `com/gtnewhorizon/gtnhlib/client/model/unbaked/MonopartDough.java`
- `com/gtnewhorizon/gtnhlib/client/model/unbaked/UnbakedModel.java`
- `com/gtnewhorizon/gtnhlib/client/opengl/VaoAppleLwjgl3Fallback.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/CapturingTessellator.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/DrawCallback.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/LocalTessellator.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/PrimitiveExtractor.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/QuadExtractor.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/cel/api/util/ColorARGB.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/cel/api/util/ColorMixer.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/line/ModelLine.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/properties/ModelQuadOrientation.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/properties/ModelQuadWinding.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/tri/ModelTriangle.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/cel/polyfill/Maps.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/postprocessing/CustomFramebuffer.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/postprocessing/I3DGeometryRenderer.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/postprocessing/PostProcessingHelper.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/postprocessing/PostProcessingManager.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/postprocessing/SharedDepthFramebuffer.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/postprocessing/shaders/BloomShader.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/postprocessing/shaders/BloomTonemapShader.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/postprocessing/shaders/PostProcessingRenderer.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/postprocessing/shaders/UniversiumShader.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/shader/AutoShaderUpdater.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/shader/IShaderReloadRunnable.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/shader/ShaderProgram.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/stacks/Vector3dStack.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/textures/AnimatedTexture.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/textures/SpriteAnimationMetadata.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/textures/TextureAtlas.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/textures/TextureLoader.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vao/VertexArrayBuffer.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vao/VertexBufferStorage.java`
- `com/gtnewhorizon/gtnhlib/client/renderer/vbo/IModelCustomExt.java`
- `com/gtnewhorizon/gtnhlib/client/title/TitleAPI.java`
- `com/gtnewhorizon/gtnhlib/client/tooltip/LoreHandler.java`
- `com/gtnewhorizon/gtnhlib/client/tooltip/LoreHolder.java`
- `com/gtnewhorizon/gtnhlib/client/tooltip/LoreHolderDiscoverer.java`
- `com/gtnewhorizon/gtnhlib/color/HSVColor.java`
- `com/gtnewhorizon/gtnhlib/color/ImmutableColor.java`
- `com/gtnewhorizon/gtnhlib/color/RGBColor.java`
- `com/gtnewhorizon/gtnhlib/commands/CommandResourcePack.java`
- `com/gtnewhorizon/gtnhlib/commands/GTNHClientCommand.java`
- `com/gtnewhorizon/gtnhlib/commands/ItemInHandCommand.java`
- `com/gtnewhorizon/gtnhlib/commands/TitleCommand.java`
- `com/gtnewhorizon/gtnhlib/compat/FalseTweaks.java`
- `com/gtnewhorizon/gtnhlib/compat/NotEnoughItemsVersionChecker.java`
- `com/gtnewhorizon/gtnhlib/concurrent/ThreadsafeCache.java`
- `com/gtnewhorizon/gtnhlib/concurrent/cas/CasAdapter.java`
- `com/gtnewhorizon/gtnhlib/concurrent/cas/CasList.java`
- `com/gtnewhorizon/gtnhlib/concurrent/cas/CasMap.java`
- `com/gtnewhorizon/gtnhlib/config/Config.java`
- `com/gtnewhorizon/gtnhlib/config/ConfigException.java`
- `com/gtnewhorizon/gtnhlib/config/ConfigFieldParser.java`
- `com/gtnewhorizon/gtnhlib/config/ConfigSyncHandler.java`
- `com/gtnewhorizon/gtnhlib/config/ConfigurationManager.java`
- `com/gtnewhorizon/gtnhlib/config/IConfigElementProxy.java`
- `com/gtnewhorizon/gtnhlib/config/PacketSyncConfig.java`
- `com/gtnewhorizon/gtnhlib/config/SimpleGuiConfig.java`
- `com/gtnewhorizon/gtnhlib/config/SimpleGuiFactory.java`
- `com/gtnewhorizon/gtnhlib/config/SyncedConfigElement.java`
- `com/gtnewhorizon/gtnhlib/core/GTNHLibCore.java`
- `com/gtnewhorizon/gtnhlib/core/GTNHLibCoreModContainer.java`
- `com/gtnewhorizon/gtnhlib/core/GTNHLibLateMixinLoader.java`
- `com/gtnewhorizon/gtnhlib/core/fml/transformers/BlockIconTransformer.java`
- `com/gtnewhorizon/gtnhlib/core/fml/transformers/EventBusSubTransformer.java`
- `com/gtnewhorizon/gtnhlib/core/fml/transformers/FMLTessellatorRedirectorWrapper.java`
- `com/gtnewhorizon/gtnhlib/core/fml/transformers/FMLTessellatorTransformerWrapper.java`
- `com/gtnewhorizon/gtnhlib/core/fml/tweakers/LateTransformerRegistrationTweaker.java`
- `com/gtnewhorizon/gtnhlib/core/rfb/GTNHLibRfbPlugin.java`
- `com/gtnewhorizon/gtnhlib/core/rfb/transformers/RFBTessellatorRedirectorWrapper.java`
- `com/gtnewhorizon/gtnhlib/core/rfb/transformers/RFBTessellatorTransformerWrapper.java`
- `com/gtnewhorizon/gtnhlib/core/shared/GTNHLibClassDump.java`
- `com/gtnewhorizon/gtnhlib/core/shared/package-info.java`
- `com/gtnewhorizon/gtnhlib/core/shared/transformers/TessellatorRedirector.java`
- `com/gtnewhorizon/gtnhlib/core/shared/transformers/TessellatorTransformer.java`
- `com/gtnewhorizon/gtnhlib/datastructs/caches/TimedCache.java`
- `com/gtnewhorizon/gtnhlib/datastructs/extensions/IterableBitSet.java`
- `com/gtnewhorizon/gtnhlib/datastructs/space/ArrayProximityCheck4D.java`
- `com/gtnewhorizon/gtnhlib/datastructs/space/ArrayProximityMap4D.java`
- `com/gtnewhorizon/gtnhlib/datastructs/space/VolumeShape.java`
- `com/gtnewhorizon/gtnhlib/event/PickBlockEvent.java`
- `com/gtnewhorizon/gtnhlib/eventbus/AutoEventBus.java`
- `com/gtnewhorizon/gtnhlib/eventbus/EventBusSubscriber.java`
- `com/gtnewhorizon/gtnhlib/eventbus/EventBusUtil.java`
- `com/gtnewhorizon/gtnhlib/eventbus/MethodInfo.java`
- `com/gtnewhorizon/gtnhlib/eventbus/Phase.java`
- `com/gtnewhorizon/gtnhlib/eventbus/StaticASMEventHandler.java`
- `com/gtnewhorizon/gtnhlib/eventhandlers/ConfigEventHandler.java`
- `com/gtnewhorizon/gtnhlib/gamerules/GameRuleRegistry.java`
- `com/gtnewhorizon/gtnhlib/gamerules/IGameRule.java`
- `com/gtnewhorizon/gtnhlib/geometry/Abstract3DIterator.java`
- `com/gtnewhorizon/gtnhlib/geometry/Axis.java`
- `com/gtnewhorizon/gtnhlib/geometry/CubeIterator.java`
- `com/gtnewhorizon/gtnhlib/geometry/DirectionTransform.java`
- `com/gtnewhorizon/gtnhlib/geometry/OneDegreeOfFreedom.java`
- `com/gtnewhorizon/gtnhlib/geometry/Orientation.java`
- `com/gtnewhorizon/gtnhlib/geometry/ThreeDegreesOfFreedom.java`
- `com/gtnewhorizon/gtnhlib/geometry/Transform.java`
- `com/gtnewhorizon/gtnhlib/geometry/TransformLike.java`
- `com/gtnewhorizon/gtnhlib/geometry/TwoDegreesOfFreedom.java`
- `com/gtnewhorizon/gtnhlib/geometry/VectorTransform.java`
- `com/gtnewhorizon/gtnhlib/hash/Fnv1a32.java`
- `com/gtnewhorizon/gtnhlib/hash/Fnv1a64.java`
- `com/gtnewhorizon/gtnhlib/item/AbstractInventoryIterator.java`
- `com/gtnewhorizon/gtnhlib/item/DroppingItemSink.java`
- `com/gtnewhorizon/gtnhlib/item/FastImmutableItemStack.java`
- `com/gtnewhorizon/gtnhlib/item/ImmutableItemStack.java`
- `com/gtnewhorizon/gtnhlib/item/InsertionItemStack.java`
- `com/gtnewhorizon/gtnhlib/item/InventoryItemSink.java`
- `com/gtnewhorizon/gtnhlib/item/InventoryItemSource.java`
- `com/gtnewhorizon/gtnhlib/item/InventoryIterator.java`
- `com/gtnewhorizon/gtnhlib/item/ItemStack2IntFunction.java`
- `com/gtnewhorizon/gtnhlib/item/ItemStackNBT.java`
- `com/gtnewhorizon/gtnhlib/item/ItemStackPredicate.java`
- `com/gtnewhorizon/gtnhlib/item/ItemTransfer.java`
- `com/gtnewhorizon/gtnhlib/item/SimpleItemIO.java`
- `com/gtnewhorizon/gtnhlib/item/SimpleItemSink.java`
- `com/gtnewhorizon/gtnhlib/item/SimpleItemSource.java`
- `com/gtnewhorizon/gtnhlib/item/StandardInventoryIterator.java`
- `com/gtnewhorizon/gtnhlib/item/WrappedItemIO.java`
- `com/gtnewhorizon/gtnhlib/item/impl/ItemDuctSink.java`
- `com/gtnewhorizon/gtnhlib/item/impl/mfr/DSUInventoryIterator.java`
- `com/gtnewhorizon/gtnhlib/item/impl/mfr/DSUItemIO.java`
- `com/gtnewhorizon/gtnhlib/item/impl/mfr/DSUItemSink.java`
- `com/gtnewhorizon/gtnhlib/item/impl/mfr/DSUItemSource.java`
- `com/gtnewhorizon/gtnhlib/itemrendering/BlockItemTexture.java`
- `com/gtnewhorizon/gtnhlib/itemrendering/IItemTexture.java`
- `com/gtnewhorizon/gtnhlib/itemrendering/ItemRenderUtils.java`
- `com/gtnewhorizon/gtnhlib/itemrendering/ItemTexture.java`
- `com/gtnewhorizon/gtnhlib/itemrendering/ItemWithTextures.java`
- `com/gtnewhorizon/gtnhlib/itemrendering/TexturedItemRenderer.java`
- `com/gtnewhorizon/gtnhlib/keybind/IKeyPressedListener.java`
- `com/gtnewhorizon/gtnhlib/keybind/PacketKeyDown.java`
- `com/gtnewhorizon/gtnhlib/keybind/SyncedKeybind.java`
- `com/gtnewhorizon/gtnhlib/mixin/IMixins.java`
- `com/gtnewhorizon/gtnhlib/mixin/ITargetedMod.java`
- `com/gtnewhorizon/gtnhlib/mixin/MixinBuilder.java`
- `com/gtnewhorizon/gtnhlib/mixin/Phase.java`
- `com/gtnewhorizon/gtnhlib/mixin/Side.java`
- `com/gtnewhorizon/gtnhlib/mixins/Mixins.java`
- `com/gtnewhorizon/gtnhlib/mixins/TargetMods.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/EntityRendererAccessor.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/MixinCommandHandler.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/MixinCommandHelp.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/MixinEntityLivingBase.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/MixinFontRenderer.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/MixinGameRules.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/MixinGuiIngameForge_TitleRender.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/MixinGuiIngame_TitleTick.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/MixinGuiScreen.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/MixinGuiSelectWorld.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/MixinIChatComponentSerializer.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/MixinItemRenderer_Translucency.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/MixinMinecraftServer.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/MixinMinecraft_PickBlockTrap.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/MixinRenderItem_Translucency.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/MixinTessellator.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/MixinTileEntitySkull.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/MixinWavefrontObject.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/block_sounds/MixinEntity.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/block_sounds/MixinEntityHorse.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/block_sounds/MixinEntityLivingBase.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/block_sounds/MixinItemBlock.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/block_sounds/MixinItemSlab.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/block_sounds/MixinPlayerControllerMP.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/block_sounds/MixinRenderGlobal.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/fml/EnumHolderAccessor.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/fml/EventBusAccessor.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/fml/MixinGuiConfig.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/fml/MixinGuiModList.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/fml/MixinJarDiscoverer.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/models/FRMAccessor.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/models/MixinEffectRenderer_FixParticleIcons.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/models/MixinFileResourcePack.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/models/MixinFolderResourcePack.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/models/MixinModelFHC.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/models/MixinRenderBlocks.java`
- `com/gtnewhorizon/gtnhlib/mixins/early/models/SRRMAccessor.java`
- `com/gtnewhorizon/gtnhlib/mixins/late/MixinEnhancedInfusionRecipe.java`
- `com/gtnewhorizon/gtnhlib/network/MessageTitle.java`
- `com/gtnewhorizon/gtnhlib/network/MessageTitleHandler.java`
- `com/gtnewhorizon/gtnhlib/network/NetworkHandler.java`
- `com/gtnewhorizon/gtnhlib/network/PacketMessageAboveHotbar.java`
- `com/gtnewhorizon/gtnhlib/network/PacketViewDistance.java`
- `com/gtnewhorizon/gtnhlib/network/TitlePacketHandler.java`
- `com/gtnewhorizon/gtnhlib/reflect/Fields.java`
- `com/gtnewhorizon/gtnhlib/test/block/BlockTest.java`
- `com/gtnewhorizon/gtnhlib/test/block/BlockTestTint.java`
- `com/gtnewhorizon/gtnhlib/test/block/BlockTestTintMul.java`
- `com/gtnewhorizon/gtnhlib/test/block/TileTestTintMul.java`
- `com/gtnewhorizon/gtnhlib/test/item/TestItem.java`
- `com/gtnewhorizon/gtnhlib/util/AboveHotbarHUD.java`
- `com/gtnewhorizon/gtnhlib/util/AnimatedTooltipHandler.java`
- `com/gtnewhorizon/gtnhlib/util/Callback.java`
- `com/gtnewhorizon/gtnhlib/util/ClientUtil.java`
- `com/gtnewhorizon/gtnhlib/util/CoordinatePacker.java`
- `com/gtnewhorizon/gtnhlib/util/DirectionUtil.java`
- `com/gtnewhorizon/gtnhlib/util/DistanceUtil.java`
- `com/gtnewhorizon/gtnhlib/util/FilesUtil.java`
- `com/gtnewhorizon/gtnhlib/util/GuiText.java`
- `com/gtnewhorizon/gtnhlib/util/ItemRenderUtil.java`
- `com/gtnewhorizon/gtnhlib/util/ItemUtil.java`
- `com/gtnewhorizon/gtnhlib/util/JsonUtil.java`
- `com/gtnewhorizon/gtnhlib/util/MathUtil.java`
- `com/gtnewhorizon/gtnhlib/util/ObjectPooler.java`
- `com/gtnewhorizon/gtnhlib/util/ServerThreadUtil.java`
- `com/gtnewhorizon/gtnhlib/util/StdLCG.java`
- `com/gtnewhorizon/gtnhlib/util/data/BlockMeta.java`
- `com/gtnewhorizon/gtnhlib/util/data/BlockSupplier.java`
- `com/gtnewhorizon/gtnhlib/util/data/IMod.java`
- `com/gtnewhorizon/gtnhlib/util/data/ImmutableBlockMeta.java`
- `com/gtnewhorizon/gtnhlib/util/data/ImmutableItemMeta.java`
- `com/gtnewhorizon/gtnhlib/util/data/ItemId.java`
- `com/gtnewhorizon/gtnhlib/util/data/ItemMeta.java`
- `com/gtnewhorizon/gtnhlib/util/data/ItemStackSupplier.java`
- `com/gtnewhorizon/gtnhlib/util/data/ItemSupplier.java`
- `com/gtnewhorizon/gtnhlib/util/data/Lazy.java`
- `com/gtnewhorizon/gtnhlib/util/data/LazyBlock.java`
- `com/gtnewhorizon/gtnhlib/util/data/LazyItem.java`
- `com/gtnewhorizon/gtnhlib/util/font/FontRendering.java`
- `com/gtnewhorizon/gtnhlib/util/font/GlyphReplacements.java`
- `com/gtnewhorizon/gtnhlib/util/map/ItemStackMap.java`
- `com/gtnewhorizon/gtnhlib/util/numberformatting/Constants.java`
- `com/gtnewhorizon/gtnhlib/util/numberformatting/ExponentialFormat.java`
- `com/gtnewhorizon/gtnhlib/util/numberformatting/NumberFormatConfig.java`
- `com/gtnewhorizon/gtnhlib/util/numberformatting/NumberFormatUtil.java`
- `com/gtnewhorizon/gtnhlib/util/numberformatting/options/CompactOptions.java`
- `com/gtnewhorizon/gtnhlib/util/numberformatting/options/FormatOptions.java`
- `com/gtnewhorizon/gtnhlib/util/numberformatting/options/NumberOptionsBase.java`
- `com/gtnewhorizon/gtnhlib/util/parsing/MathExpressionParser.java`
