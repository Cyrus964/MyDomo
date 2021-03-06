package com.adgsoftware.mydomo.engine.controller.heating.dimension;

/*
 * #%L
 * MyDomoEngine
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

import com.adgsoftware.mydomo.engine.controller.DimensionStatusImpl;
import com.adgsoftware.mydomo.engine.controller.DimensionValue;
import com.adgsoftware.mydomo.engine.controller.DimensionValueImpl;
import com.adgsoftware.mydomo.engine.controller.heating.HeatingZone;
import com.adgsoftware.mydomo.engine.controller.heating.Offset;
import com.adgsoftware.mydomo.engine.controller.heating.Offset.Mode;

public class SetOffset extends DimensionStatusImpl {

	private int LOCAL_OFFSET_POS = 0;
	
	public SetOffset() {
		super(new DimensionValue[] { 
				new DimensionValueImpl()  // OL: Local Offset
				},
				HeatingZone.HeatingZoneDimension.LOCAL_OFFSET.getCode()
		);
	}

	public Offset getLocalOffset() {
		String val = getStringValue(LOCAL_OFFSET_POS);
		if ("00".equals(val)) {
			return new Offset(Offset.Mode.ON, 0);
		} else if ("01".equals(val)) {
			return new Offset(Offset.Mode.ON, 1);
		} else if ("11".equals(val)) {
			return new Offset(Offset.Mode.ON, -1);
		} else if ("02".equals(val)) {
			return new Offset(Offset.Mode.ON, 2);
		} else if ("12".equals(val)) {
			return new Offset(Offset.Mode.ON, -2);
		} else if ("03".equals(val)) {
			return new Offset(Offset.Mode.ON, 3);
		} else if ("13".equals(val)) {
			return new Offset(Offset.Mode.ON, -3);
		} else if ("4".equals(val)) {
			return new Offset(Offset.Mode.OFF, 0);
		} else if ("5".equals(val)) {
			return new Offset(Offset.Mode.PROTECTION, 0);
		} else {
			return null;
		}
	}
	
	
	public void setLocalOffset(Offset offset) {
		
		String val;
		Mode mode = offset.getMode();
		int degree = offset.getDegree();
		
		if (Offset.Mode.ON.equals(mode) && 3 == degree) {
			val = "00";
		} else if (Offset.Mode.ON.equals(mode) && 1 == degree) {
			val = "01";
		} else if (Offset.Mode.ON.equals(mode) && -1 == degree) {
			val = "11";
		} else if (Offset.Mode.ON.equals(mode) &&  2 == degree) {
			val = "02";
		} else if (Offset.Mode.ON.equals(mode) &&  -2 == degree) {
			val = "12";
		} else if (Offset.Mode.ON.equals(mode) &&  3 == degree) {
			val = "03";
		} else if (Offset.Mode.ON.equals(mode) &&  -3 == degree) {
			val = "13";
		} else if (Offset.Mode.OFF.equals(mode) &&  0 == degree) {
			val = "4";
		} else if (Offset.Mode.PROTECTION.equals(mode) && 0 == degree) {
			val = "5";
		} else {
			throw new RuntimeException("Offset ["+ mode + ";" + degree + "] unSupported!");
		}
		
		setStringValue(val, LOCAL_OFFSET_POS);
	}
}
