/**
 * Copyright (c) 2010 WeigleWilczek.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.weiglewilczek.gameoflife

import java.awt.Color
import javax.swing.border.LineBorder
import scala.swing.{
  BoxPanel, Button, FlowPanel, GridPanel, Label, MainFrame, Orientation, SimpleSwingApplication
}
import scala.swing.event.MouseClicked

object SwingUI extends SimpleSwingApplication {

  override def startup(args: Array[String]) {
    dimension = args.headOption map { arg =>
      try { arg.toInt } catch { case _ => DefaultDimension }
    } getOrElse DefaultDimension
    super.startup(args) 
  }

  override def top = new MainFrame {

    title = "Game of Life"
    resizable = false
    contents = new BoxPanel(Orientation.Vertical) {
      contents += new GenerationPanel(new Generation, update(contents(0) = _))
      contents += Button("Next") {
        val next = contents(0).asInstanceOf[GenerationPanel].generation.next
        update(contents(0) = _)(next)
      }
    }

    private def update(swap: GenerationPanel => Unit)(generation: Generation) {
      swap(new GenerationPanel(generation, update(swap)))
      pack()
    }
  }

  private class GenerationPanel(val generation: Generation, update: Generation => Unit)
    extends GridPanel(dimension, dimension) {

    contents appendAll cells

    private lazy val cells = {
      def cell(cell: Cell, alive: Cell => Boolean) = {
        new FlowPanel {
          border = LineBorder.createBlackLineBorder
          background = if (alive(cell)) Color.BLUE else Color.WHITE
          contents += new Label("")
          listenTo(mouse.clicks)
          reactions += {
            case e: MouseClicked => {
              val aliveCells =
                if (alive(cell)) generation.aliveCells - cell
                else generation.aliveCells + cell
              update(new Generation(aliveCells))
            }
          }
        }
      }
      for {
        y <- 0 until dimension
        x <- 0 until dimension
      } yield cell(Cell(x, y), generation.aliveCells.contains)
    }
  }

  private lazy val DefaultDimension = 25

  private var dimension: Int = _
}
