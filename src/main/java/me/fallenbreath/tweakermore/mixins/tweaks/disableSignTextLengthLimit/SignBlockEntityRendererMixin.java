package me.fallenbreath.tweakermore.mixins.tweaks.disableSignTextLengthLimit;

import me.fallenbreath.tweakermore.config.TweakerMoreConfigs;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Group;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(SignBlockEntityRenderer.class)
public abstract class SignBlockEntityRendererMixin
{
	@SuppressWarnings("UnresolvedMixinReference")
	@Group(min = 1, max = 1)
	@ModifyArg(
			method = {  // lambda method in method render
					"method_32159",  // vanilla
					"lambda$render$2"  // after being polluted by optifine
			},
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/font/TextRenderer;wrapLines(Lnet/minecraft/text/StringVisitable;I)Ljava/util/List;",
					remap = true
			),
			remap = false
	)
	private int disableSignTextLengthLimit(int maxLength)
	{
		if (TweakerMoreConfigs.DISABLE_SIGN_TEXT_LENGTH_LIMIT.getBooleanValue())
		{
			maxLength = Integer.MAX_VALUE;
		}
		return maxLength;
	}
}
