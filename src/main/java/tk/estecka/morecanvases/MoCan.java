package tk.estecka.morecanvases;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoCan implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("MoCan");

	public static final int	width_min = 1;
	public static final int	width_max = 4;
	public static final int	height_min = 1;
	public static final int	height_max = 4;

	@Override
	public void onInitialize() {
		LOGGER.debug("Registering painting sizes [", width_min, ", ", width_max, "] to [", height_min, ", ", height_max,"]");
		for (int x=width_min ; x<=width_max ; x++)
		for (int y=height_min; y<=height_max; y++)
			Register(x, y);
	}
	
	public static RegistryKey<PaintingVariant> Register(int x, int y){
		RegistryKey<PaintingVariant> key = RegistryKey.of(RegistryKeys.PAINTING_VARIANT, new Identifier("mocan", "blank"+x+"x"+y));
		Registry.register(Registries.PAINTING_VARIANT, key, new PaintingVariant(x*16, y*16));
		return key;
	}

}
