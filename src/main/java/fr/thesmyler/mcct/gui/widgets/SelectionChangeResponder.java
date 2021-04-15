package fr.thesmyler.mcct.gui.widgets;

import fr.thesmyler.mcct.gui.widgets.TweakConfigListWidget.TweakEntry;

public interface SelectionChangeResponder {
	void onSelectionChange(TweakEntry oldEntry, TweakEntry newEntry);
}