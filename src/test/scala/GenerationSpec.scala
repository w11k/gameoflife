/**
 * Copyright (c) 2010 WeigleWilczek.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.weiglewilczek.gameoflife

import org.specs.Specification

class GenerationSpec extends Specification {

  "Generation.next" should {

    "return an empty Generation for the empty Generation" in {
      new Generation(Set.empty).next.aliveCells mustBe Set.empty
    }

    "yield three horizontal cells for three vertical cells" in {
      val vertical3 = Set(Cell(0, -1), Cell(0, 0), Cell(0, 1))
      val horizontal3 = Set(Cell(-1, 0), Cell(0, 0), Cell(1, 0))
      new Generation(vertical3).next.aliveCells mustEqual horizontal3
    }

    "stay constant for a 2x2 block of cells" in {
      val block = Set(Cell(0, 0), Cell(0, 1), Cell(1, 0), Cell(1, 1))
      new Generation(block).next.aliveCells mustEqual block
    }
  }
}
