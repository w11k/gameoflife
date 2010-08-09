/**
 * Copyright (c) 2010 WeigleWilczek.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.weiglewilczek.gameoflife

class Generation(val aliveCells: Set[Cell] = Set.empty) {
  require(aliveCells != null, "aliveCells must not be null!")

  def next: Generation = {
    val stayingAlive =
      aliveCells filter { 2 to 3 contains aliveNeighbours(_).size }
    val wakingFromDead =
      aliveCells flatMap deadNeighbours filter { aliveNeighbours(_).size == 3 }
    new Generation(stayingAlive ++ wakingFromDead)
  }

  private def aliveNeighbours(cell: Cell) =
    cell.neighbours filter aliveCells.contains

  private def deadNeighbours(cell: Cell) =
    cell.neighbours filter { neighbour => !(aliveCells contains neighbour) }
}
