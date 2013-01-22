package de.vorb.subpixelillustrator

import java.awt.Desktop
import java.awt.image.BufferedImage
import java.io.File
import scala.swing._
import scala.swing.event.ButtonClicked
import javax.imageio.ImageIO
import javax.swing.{ JOptionPane, JSpinner, SpinnerNumberModel, UIManager }
import javax.swing.filechooser.FileFilter
import javax.swing.ImageIcon

/**
 * Simple application that lets you scale an image
 */
object SubpixelIllustrator extends SimpleSwingApplication {

  try {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
  } catch { case e: Throwable => }

  /**
   * Creates the GUI.
   */
  def top = new MainFrame {
    title = "Subpixel Illustrator"

    val factor = new SpinnerNumberModel(9, 3, 60, 3)
    val openResult = new CheckBox {
      selected = true
    }
    var source: File = null
    var ext: String = null

    def warnNoInputFile() =
      JOptionPane.showMessageDialog(self,
        "Please choose a source image first",
        "No source file specified",
        JOptionPane.WARNING_MESSAGE)

    contents = new BoxPanel(Orientation.Vertical) {
      border = Swing.EmptyBorder(8, 40, 8, 40)

      // source chooser
      contents += new FlowPanel {
        contents += new Button {
          text = "Choose source image ..."
          reactions += {
            case ButtonClicked(b) =>
              val fc = new FileChooser {
                title = "Choose source image"
                fileFilter = new FileFilter {
                  def accept(f: File) = {
                    ext = {
                      val name = f.getName.split("\\.")
                      name(name.size - 1)
                    }

                    f.isDirectory || (f.canRead && ext == "png")
                  }

                  def getDescription = "PNG images"
                }
              }

              if (fc.showOpenDialog(b) == FileChooser.Result.Approve)
                source = fc.selectedFile
          }
        }
      }

      // scale factor
      contents += new FlowPanel {
        contents += new Label("Scale factor")
        contents += Component.wrap(new JSpinner(factor))

        contents += new Button {
          text = "Preview"

          reactions += {
            case ButtonClicked(b) =>
              if (source == null)
                warnNoInputFile()
              else
                try {
                  val result = scaleUp(ImageIO.read(source),
                    factor.getNumber.intValue)
                  new Dialog(top) {
                    title = "Preview"
                    modal = true
                    contents = new Label {
                      icon = new ImageIcon(result)
                    }

                    centerOnScreen()
                    visible = true
                  }
                } catch {
                  case e => e.printStackTrace
                }
          }
        }
      }

      // save button
      contents += new FlowPanel {
        contents += new Button {
          text = "Save as ..."

          reactions += {
            case ButtonClicked(b) =>
              if (source == null)
                warnNoInputFile()
              else {
                val fc = new FileChooser {
                  title = "Save result"
                  fileFilter = new FileFilter {
                    def accept(f: File) = f.canWrite
                    def getDescription = "PNG images"
                  }
                }

                if (fc.showSaveDialog(b) == FileChooser.Result.Approve) {
                  val destination =
                    if (!fc.selectedFile.getName().endsWith(".png"))
                      new File(fc.selectedFile.getAbsolutePath + ".png")
                    else
                      fc.selectedFile

                  try {
                    val result = scaleUp(ImageIO.read(source),
                      factor.getNumber.intValue)
                    ImageIO.write(result, "PNG", destination)

                    if (openResult.selected)
                      try {
                        Desktop.getDesktop().open(destination)
                      } catch {
                        case _ =>
                          JOptionPane.showMessageDialog(self,
                            "The result cannot be opened.",
                            "Missing permissions",
                            JOptionPane.WARNING_MESSAGE)
                      }
                  } catch {
                    case _ => JOptionPane.showMessageDialog(self,
                      "The result cannot be saved to the selected location.",
                      "Missing permissions",
                      JOptionPane.ERROR_MESSAGE)
                  }
                }
              }
          }
        }

        contents += new Label("Open result")
        contents += openResult
      }
    }

    resizable = false
    centerOnScreen()
  }

  /**
   * Resizes an image by a factor, showing illustrating the subpixels.
   */
  def scaleUp(input: BufferedImage, factor: Int) = {
    require(factor > 0, "factor must be greater than one")
    require(factor % 3 == 0, "factor must be dividable by three")

    val width = input.getWidth
    val height = input.getHeight
    val step = factor / 3

    val out = new BufferedImage(input.getWidth * factor,
      input.getHeight * factor, BufferedImage.TYPE_INT_RGB)

    for {
      _y <- 0 until height
      (yMin, yMax) = (_y * factor, (_y + 1) * factor)
      _x <- 0 until width
      (xMin, xMax) = (_x * factor, (_x + 1) * factor)
      _rgb = input.getRGB(_x, _y)
      y <- yMin until yMax
      x <- xMin until xMax
      rgb = {
        if (x < xMin + step) getRed(_rgb)
        else if (xMax - step <= x) getBlue(_rgb)
        else getGreen(_rgb)
      }
    } {
      out.setRGB(x, y, rgb)
    }

    out
  }

  /**
   * Get red component from a RGB color.
   */
  def getRed(rgb: Int) = rgb & 0xFF0000

  /**
   * Get green component from a RGB color.
   */
  def getGreen(rgb: Int) = rgb & 0x00FF00

  /**
   * Get blue component from a RGB color.
   */
  def getBlue(rgb: Int) = rgb & 0x0000FF
}
