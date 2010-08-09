/**
 * Copyright (c) 2010 WeigleWilczek.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.weiglewilczek.gameoflife

import org.specs.Specification

class CellSpec extends Specification {

  "A Cell" should {
    "have eight neighbours" in {
      Cell(0, 0).neighbours must haveSize(8)
    }
  }
}
