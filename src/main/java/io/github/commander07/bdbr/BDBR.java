package io.github.commander07.bdbr;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BDBR implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("barriers-dont-block-rain");

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
	}
}