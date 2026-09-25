package com.s8tnlib;

import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

/**
 * Makes S8TNLib's jar a coremod, which is what gets it onto the LaunchClassLoader while coremods load: Cleanroom
 * adds a mods-folder jar that early only when its manifest names an FMLCorePlugin. A host's coremod and tweaker
 * code uses the library's classes (Demonica's GLSM redirector, for one), so a plain mod jar would come too late.
 * FMLCorePluginContainsFMLMod in the manifest keeps the jar a mod as well.
 *
 * <p>It registers no transformer, and it excludes no package from transformation: a host's transformers still
 * rewrite the library's classes. The sorting index only puts it before hosts that keep the default.
 */
@IFMLLoadingPlugin.Name("S8TNLib")
@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.SortingIndex(-1)
public class S8TNLibLoadingPlugin implements IFMLLoadingPlugin {

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {}

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
