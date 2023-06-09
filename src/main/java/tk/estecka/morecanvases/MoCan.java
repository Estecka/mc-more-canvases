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

	public static final SimpleConfig config = SimpleConfig.of("More-Canvases").provider(MoCan::DefaultConfig).request();
	public static final int	width_min  = config.getOrDefault("width.min",  1);
	public static final int	width_max  = config.getOrDefault("width.max",  4);
	public static final int	height_min = config.getOrDefault("height.min", 1);
	public static final int	height_max = config.getOrDefault("height.max", 4);

	@Override
	public void onInitialize() {
		LOGGER.info("Generating painting sizes from [{}, {}] to [{}, {}]", width_min, height_min, width_max, height_max);

		if (width_max < width_min)
			LOGGER.error("Width min is greater than width max, no paintings will be generated.");
		if (height_max < height_min)
			LOGGER.error("Height min is greater than height max, no paintings will be generated.");
		if (width_max > 16 || height_max > 16)
			LOGGER.warn("Painting dimensions are suspiciously high. Did you configure the mod properly ?");

		for (int x=width_min ; x<=width_max ; x++)
		for (int y=height_min; y<=height_max; y++)
			Register(x, y);
	}
	
	static private RegistryKey<PaintingVariant>	Register(int x, int y){
		RegistryKey<PaintingVariant> key = RegistryKey.of(RegistryKeys.PAINTING_VARIANT, new Identifier("mocan", "blank"+x+"x"+y));
		Registry.register(Registries.PAINTING_VARIANT, key, new PaintingVariant(x*16, y*16));
		return key;
	}

	static private String	DefaultConfig(String filename){
		return """
		width.min=1
		width.max=4
		height.min=1
		height.max=4
		""";
	}

}
