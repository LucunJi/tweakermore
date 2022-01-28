package me.fallenbreath.tweakermore.config;

import com.chocohead.mm.api.ClassTinkerers;
import com.google.common.collect.Lists;
import fi.dy.masa.tweakeroo.config.FeatureToggle;

import java.util.List;

public class TweakerMoreToggles
{
	private static final List<FeatureToggle> FEATURE_TOGGLES = Lists.newArrayList();

	public static final FeatureToggle TWEAKM_AUTO_CLEAN_CONTAINER = fetch("TWEAKM_AUTO_CLEAN_CONTAINER");
	public static final FeatureToggle TWEAKM_AUTO_FILL_CONTAINER = fetch("TWEAKM_AUTO_FILL_CONTAINER");
	public static final FeatureToggle TWEAKM_AUTO_PICK_SCHEMATIC_BLOCK = fetch("TWEAKM_AUTO_PICK_SCHEMATIC_BLOCK");

	public static List<FeatureToggle> getFeatureToggles()
	{
		return FEATURE_TOGGLES;
	}

	private static FeatureToggle fetch(String name)
	{
		FeatureToggle featureToggle = ClassTinkerers.getEnum(FeatureToggle.class, name);
		FEATURE_TOGGLES.add(featureToggle);
		return featureToggle;
	}

	public static class TogglesEarlyRiser implements Runnable
	{
		private static final String FeatureToggleClassPath = "fi.dy.masa.tweakeroo.config.FeatureToggle";

		@Override
		public void run()
		{
			// private FeatureToggle(String name, boolean defaultValue, String defaultHotkey, String comment, String prettyName)
			ClassTinkerers.enumBuilder(FeatureToggleClassPath, String.class, boolean.class, String.class, String.class, String.class).
					addEnum("TWEAKM_AUTO_CLEAN_CONTAINER", "tweakmAutoCleanContainer", false, "", "tweakmAutoCleanContainer.comment", "Auto Clean Container").
					addEnum("TWEAKM_AUTO_FILL_CONTAINER", "tweakmAutoFillContainer", false, "", "tweakmAutoFillContainer.comment", "Auto Fill Container").
					addEnum("TWEAKM_AUTO_PICK_SCHEMATIC_BLOCK", "tweakmAutoPickSchematicBlock", false, "", "tweakmAutoPickSchematicBlock.comment", "Auto Pick Schematic Block").
					build();
		}
	}
}
