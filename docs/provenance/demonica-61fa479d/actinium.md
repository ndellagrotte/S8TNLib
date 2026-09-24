# Provenance manifest

- baseline: `4a19c95952cb9710211d29bec3440e752b6a2d03` (actinium layout)
- compared: `f122acc3215f99ca18d01954912d28a64b327f49` (s8tnlib layout)

| scope | verbatim | adapted | new | dropped |
|---|---|---|---|---|
| gtnhlib | 58 | 2 | 0 | 0 |
| tests | 2 | 0 | 0 | 0 |
| main | 58 | 2 | 0 | 30 |

## Scope `gtnhlib`

- baseline roots: `GTNHLib/src/main/java` (`com/gtnewhorizon/gtnhlib/asm/ClassConstantPoolParser.java`, `com/gtnewhorizon/gtnhlib/bytebuf/APIUtil.java`, `com/gtnewhorizon/gtnhlib/bytebuf/CheckIntrinsics.java`, `com/gtnewhorizon/gtnhlib/bytebuf/Checks.java`, `com/gtnewhorizon/gtnhlib/bytebuf/MemoryManage.java`, `com/gtnewhorizon/gtnhlib/bytebuf/MemoryStack.java`, `com/gtnewhorizon/gtnhlib/bytebuf/MemoryUtilities.java`, `com/gtnewhorizon/gtnhlib/bytebuf/MultiReleaseMemCopy.java`, `com/gtnewhorizon/gtnhlib/bytebuf/MultiReleaseTextDecoding.java`, `com/gtnewhorizon/gtnhlib/bytebuf/Pointer.java`, `com/gtnewhorizon/gtnhlib/bytebuf/StackWalkUtil.java`, `com/gtnewhorizon/gtnhlib/bytebuf/package-info.java`, `com/gtnewhorizon/gtnhlib/client/opengl/FBOFunctions.java`, `com/gtnewhorizon/gtnhlib/client/opengl/GLCaps.java`, `com/gtnewhorizon/gtnhlib/client/opengl/UniversalVAO.java`, `com/gtnewhorizon/gtnhlib/client/renderer/CallbackTessellator.java`, `com/gtnewhorizon/gtnhlib/client/renderer/DirectDrawCallback.java`, `com/gtnewhorizon/gtnhlib/client/renderer/DirectTessellator.java`, `com/gtnewhorizon/gtnhlib/client/renderer/ITessellatorInstance.java`, `com/gtnewhorizon/gtnhlib/client/renderer/RuntimeOptionsBridge.java`, `com/gtnewhorizon/gtnhlib/client/renderer/TessellatorManager.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/api/util/ColorABGR.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/api/util/ColorU8.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/api/util/NormI8.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/primitive/ModelPrimitiveView.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/ModelQuad.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/ModelQuadView.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/ModelQuadViewMutable.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/properties/ModelQuadFacing.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/properties/ModelQuadFlags.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/util/MathUtil.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/util/ModelQuadUtil.java`, `com/gtnewhorizon/gtnhlib/client/renderer/postprocessing/DepthTextureProvider.java`, `com/gtnewhorizon/gtnhlib/client/renderer/postprocessing/PostProcessingBridge.java`, `com/gtnewhorizon/gtnhlib/client/renderer/stacks/IStateStack.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/BaseVAO.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/IVertexArrayObject.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/IndexBuffer.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/IndexedVAO.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/VAOManager.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/VaoFunctions.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/VertexArrayUnsupported.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/VertexBufferFactory.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/VertexBufferType.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vbo/IVertexBuffer.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vbo/VBOManager.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vbo/VertexBuffer.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/DefaultVertexFormat.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/VertexFlags.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/VertexFormat.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/VertexFormatElement.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/VertexOptimizer.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/ColorVertexAttributeWriter.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/IVertexAttributeWriter.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/LightVertexAttributeWriter.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/NormalVertexAttributeWriter.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/PositionVertexAttributeWriter.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/TextureVertexAttributeWriter.java`, `com/gtnewhorizon/gtnhlib/compat/Mods.java`, `com/gtnewhorizon/gtnhlib/util/font/IFontParameters.java`)
- compared roots: `src/main/java` (`com/gtnewhorizon/gtnhlib/asm/ClassConstantPoolParser.java`, `com/gtnewhorizon/gtnhlib/bytebuf/APIUtil.java`, `com/gtnewhorizon/gtnhlib/bytebuf/CheckIntrinsics.java`, `com/gtnewhorizon/gtnhlib/bytebuf/Checks.java`, `com/gtnewhorizon/gtnhlib/bytebuf/MemoryManage.java`, `com/gtnewhorizon/gtnhlib/bytebuf/MemoryStack.java`, `com/gtnewhorizon/gtnhlib/bytebuf/MemoryUtilities.java`, `com/gtnewhorizon/gtnhlib/bytebuf/MultiReleaseMemCopy.java`, `com/gtnewhorizon/gtnhlib/bytebuf/MultiReleaseTextDecoding.java`, `com/gtnewhorizon/gtnhlib/bytebuf/Pointer.java`, `com/gtnewhorizon/gtnhlib/bytebuf/StackWalkUtil.java`, `com/gtnewhorizon/gtnhlib/bytebuf/package-info.java`, `com/gtnewhorizon/gtnhlib/client/opengl/FBOFunctions.java`, `com/gtnewhorizon/gtnhlib/client/opengl/GLCaps.java`, `com/gtnewhorizon/gtnhlib/client/opengl/UniversalVAO.java`, `com/gtnewhorizon/gtnhlib/client/renderer/CallbackTessellator.java`, `com/gtnewhorizon/gtnhlib/client/renderer/DirectDrawCallback.java`, `com/gtnewhorizon/gtnhlib/client/renderer/DirectTessellator.java`, `com/gtnewhorizon/gtnhlib/client/renderer/ITessellatorInstance.java`, `com/gtnewhorizon/gtnhlib/client/renderer/RuntimeOptionsBridge.java`, `com/gtnewhorizon/gtnhlib/client/renderer/TessellatorManager.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/api/util/ColorABGR.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/api/util/ColorU8.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/api/util/NormI8.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/primitive/ModelPrimitiveView.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/ModelQuad.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/ModelQuadView.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/ModelQuadViewMutable.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/properties/ModelQuadFacing.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/model/quad/properties/ModelQuadFlags.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/util/MathUtil.java`, `com/gtnewhorizon/gtnhlib/client/renderer/cel/util/ModelQuadUtil.java`, `com/gtnewhorizon/gtnhlib/client/renderer/postprocessing/DepthTextureProvider.java`, `com/gtnewhorizon/gtnhlib/client/renderer/postprocessing/PostProcessingBridge.java`, `com/gtnewhorizon/gtnhlib/client/renderer/stacks/IStateStack.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/BaseVAO.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/IVertexArrayObject.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/IndexBuffer.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/IndexedVAO.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/VAOManager.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/VaoFunctions.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/VertexArrayUnsupported.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/VertexBufferFactory.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vao/VertexBufferType.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vbo/IVertexBuffer.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vbo/VBOManager.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vbo/VertexBuffer.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/DefaultVertexFormat.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/VertexFlags.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/VertexFormat.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/VertexFormatElement.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/VertexOptimizer.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/ColorVertexAttributeWriter.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/IVertexAttributeWriter.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/LightVertexAttributeWriter.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/NormalVertexAttributeWriter.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/PositionVertexAttributeWriter.java`, `com/gtnewhorizon/gtnhlib/client/renderer/vertex/writers/TextureVertexAttributeWriter.java`, `com/gtnewhorizon/gtnhlib/compat/Mods.java`, `com/gtnewhorizon/gtnhlib/util/font/IFontParameters.java`)

### Adapted (2)

- `com/gtnewhorizon/gtnhlib/compat/Mods.java`
- `com/gtnewhorizon/gtnhlib/util/font/IFontParameters.java`

### New (0)


### Dropped (0)


## Scope `tests`

- baseline roots: `GTNHLib/src/test/java` (`com/gtnewhorizon/gtnhlib/bytebuf/MemoryUtilitiesTest.java`, `com/gtnewhorizon/gtnhlib/client/renderer/TessellatorManagerTest.java`)
- compared roots: `src/test/java` (`com/gtnewhorizon/gtnhlib/bytebuf/MemoryUtilitiesTest.java`, `com/gtnewhorizon/gtnhlib/client/renderer/TessellatorManagerTest.java`)

### Adapted (0)


### New (0)


### Dropped (0)


## Scope `main`

- baseline roots: `GTNHLib/src/main/java`
- compared roots: `src/main/java`

### Adapted (2)

- `com/gtnewhorizon/gtnhlib/compat/Mods.java`
- `com/gtnewhorizon/gtnhlib/util/font/IFontParameters.java`

### New (0)


### Dropped (30)

- `com/gtnewhorizon/gtnhlib/ClientProxy.java`
- `com/gtnewhorizon/gtnhlib/GTNHLib.java`
- `com/gtnewhorizon/gtnhlib/blockpos/BlockPos.java`
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
- `com/gtnewhorizon/gtnhlib/client/renderer/vbo/IModelCustomExt.java`
- `com/gtnewhorizon/gtnhlib/core/GTNHLibCore.java`
- `com/gtnewhorizon/gtnhlib/util/ObjectPooler.java`
