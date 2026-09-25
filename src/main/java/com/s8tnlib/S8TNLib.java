package com.s8tnlib;

import net.minecraftforge.fml.common.Mod;

/**
 * S8TNLib's mod, so that FML lists it and a host can declare {@code required-after:s8tnlib}. It does nothing: the
 * library is its {@code com.gtnewhorizon.gtnhlib} classes, which S8TNLibLoadingPlugin puts on the class loader. It
 * must not load any of them, so that it stays harmless whatever a host does with them.
 */
@Mod(
    modid = S8TNLib.MODID,
    useMetadata = true,
    clientSideOnly = true,
    acceptableRemoteVersions = "*",
    acceptedMinecraftVersions = "[1.12.2]"
)
public class S8TNLib {
    public static final String MODID = "s8tnlib";
}
