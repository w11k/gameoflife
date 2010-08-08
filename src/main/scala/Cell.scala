/**
 * Copyright (c) 2010 WeigleWilczek.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.weiglewilczek.gameoflife

case class Cell(x: Int, y: Int) {

  def neighbours: Traversable[Cell] =
    for {
      i <- (x - 1 to x + 1)
      j <- (y - 1 to y + 1) if (i != x) || (j != y)
    } yield Cell(i, j)

  override def toString = position

  private lazy val position = "(%s, %s)".format(x, y)
}
