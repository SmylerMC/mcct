package fr.smyler.mcct.gui.widgets;

import fr.smyler.mcct.gui.widgets.TweakConfigListWidget.TweakEntry;

public interface SelectionChangeResponder {
	void onSelectionChange(TweakEntry oldEntry, TweakEntry newEntry);
}