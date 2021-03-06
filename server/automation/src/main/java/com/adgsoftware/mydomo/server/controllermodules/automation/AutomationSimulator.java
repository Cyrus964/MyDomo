package com.adgsoftware.mydomo.server.controllermodules.automation;

/*
 * #%L
 * MyDomoServerAutomationModule
 * %%
 * Copyright (C) 2011 - 2013 A. de Giuli
 * %%
 * This file is part of MyDomo done by A. de Giuli (arnaud.degiuli(at)free.fr).
 * 
 *     MyDomo is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     MyDomo is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with MyDomo.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */


import java.text.MessageFormat;
import java.util.Hashtable;

import com.adgsoftware.mydomo.engine.Command;
import com.adgsoftware.mydomo.engine.controller.automation.Automation;
import com.adgsoftware.mydomo.server.controllermodules.ControllerSimulator;

public class AutomationSimulator implements ControllerSimulator {
	
	private static Hashtable<String, String> statusList = new Hashtable<String, String>(); // where, what
	
	@Override
	public String execute(String command) {
		String what = Command.getWhatFromCommand(command);
		String where = Command.getWhereFromCommand(command);
		if (Automation.AutomationStatus.AUTOMATION_DOWN.getCode().equals(what)
				|| Automation.AutomationStatus.AUTOMATION_STOP.getCode().equals(what)
				|| Automation.AutomationStatus.AUTOMATION_UP.getCode().equals(what)) {
			statusList.put(where, what);
			return Command.ACK;
		} else {
			System.out.println("Command not supported [" + command + "]");
			return Command.NACK;
		}
	}
	
	@Override
	public String status(String command) {
		String where = Command.getWhereFromCommand(command);
		String what = statusList.get(where);
		if (what == null) {
			what = Automation.AutomationStatus.AUTOMATION_STOP.getCode();
			statusList.put(where, what);
		}

		return MessageFormat.format(Command.COMMAND, new Object[] {Command.WHO_AUTOMATION, what, where} ) + Command.ACK;
	}

	@Override
	public String getWho() {
		return Command.WHO_AUTOMATION;
	}
}
