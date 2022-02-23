/*  Original Licensing Copyright
 * 
 *  Contains a number of functional interfaces.
 *  Copyright (C) 2022  DZ-FSDev
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.dz_fs_dev.common;

/**
 * Contains a number of functional interfaces.
 * 
 * @author DZ-FSDev
 * @since 17.0.2
 * @version 0.0.1
 */
class DZFuntionalInterfaces{
	@FunctionalInterface interface Function2<ONE, TWO, RET>{
		public RET apply(ONE one, TWO two);
	}
	
	@FunctionalInterface interface Function3<ONE, TWO, THREE, RET>{
		public RET apply(ONE one, TWO two, THREE three);
	}
	
	@FunctionalInterface interface Function4<ONE, TWO, THREE, FOUR, RET>{
		public RET apply(ONE one, TWO two, THREE three, FOUR four);
	}
	
	@FunctionalInterface interface Function5<ONE, TWO, THREE, FOUR, FIVE, RET>{
		public RET apply(ONE one, TWO two, THREE three, FOUR four, FIVE five);
	}
}
