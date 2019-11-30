/*
 * Copyright (C) 2014 - 2019 | Wurst-Imperium | All rights reserved.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package uwu.smsgamer.Paste;

public enum Category {
	COMBAT("Combat"),
	EXPLOIT("Exploit"),
	MISC("Misc"),
	MOVEMENT("Movement"),
	PLAYER("Player"),
	RENDER("Render"),
	WORLD("World");

	private final String name;

	Category(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
