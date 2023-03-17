package com.ddang_.bloodyutility

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

@Mod(modid = BloodyUtility.MOD_ID, name = BloodyUtility.MOD_NAME, version = BloodyUtility.VERSION)
class BloodyUtility {

    companion object {
        const val MOD_ID = "bloodyUtility"
        const val MOD_NAME = "BloodyUtility"
        const val VERSION = "1.0-SNAPSHOT"

        /**
         * This is the instance of your mod as created by Forge. It will never be null.
         */
        @Mod.Instance(MOD_ID)
        var INSTANCE = this
    }

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    fun preinit(event: FMLPreInitializationEvent?) {
    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    fun init(event: FMLInitializationEvent?) {
    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    fun postinit(event: FMLPostInitializationEvent?) {
    }

}