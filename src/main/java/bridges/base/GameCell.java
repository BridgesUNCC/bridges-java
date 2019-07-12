package bridges.base;

import bridges.base.NamedColor;

/**
 *	@brief This class is used to represent cells in GameGrids in BRIDGES.
 *  Each cell has a foreground color, background color, and symbol.
 *
 *	@author David Burlinson
 *	@date 9/06/18
 *
 */
public class GameCell {
  private NamedSymbol symbol;
  private NamedColor bg, fg;
  protected static NamedSymbol[] symbolArray = NamedSymbol.values();

  /*
   * Default Constructor
   */
  public GameCell() {
    bg = NamedColor.black;
    fg = NamedColor.white;
    symbol = NamedSymbol.none;
  }

  /**
  * Constructor with all arguments specified
  *
  * @param bg, fg  - Named Colors from the NamedColor enum
  * @param symbol - symbol index from range 0-255
  *
  */
  public GameCell(NamedColor bg, NamedColor fg, NamedSymbol symbol) {
    this.bg = bg;
    this.fg = fg;
    this.symbol = symbol;
  }


  /**
   *  Set background color using NamedColor Enum argument
   *  @param bg - Named Color from the NamedColor enum
   */
  public void setBGColor(NamedColor bg) {
    this.bg = bg;
  }

  /**
   *  Set foreground color using NamedColor Enum argument
   *  @param fg - Named Color from the NamedColor enum
   */
  public void setFGColor(NamedColor fg) {
    this.fg = fg;
  }

  /**
   *  Set background color using String argument
   *  @param bg - String background color
   */
  public void setBGColor(String bg) {
    this.bg = NamedColor.valueOf(bg);
  }

  /**
   *  Set foreground color using String argument
   *  @param fg - String foreground color
   */
  public void setFGColor(String fg) {
    this.fg = NamedColor.valueOf(fg);
  }

  /**
   *  Set symbol using int argument
   *  @param s - Integer symbol index
   */
  public void setSymbol(int s) {
    if(s < 0 || s > 255) throw new IllegalArgumentException("Symbol " + s + " is invalid; symbols must be specified from the range (0, 255)\n");
    this.symbol = symbolArray[s];
  }

  /**
   *  Set symbol using int argument
   *  @param s - Named symbol
   */
  public void setSymbol(NamedSymbol s) {
    this.symbol = s;
  }

  /**
   *  @return background color
   */
  public NamedColor getBGColor() {
    return this.bg;
  }

  /**
   *  @return object color
   */
  public NamedColor getFGColor() {
    return this.fg;
  }

  /**
   *  @return symbol
   */
  public NamedSymbol getSymbol() {
    return this.symbol;
  }

  /**
   *  @return background color as byte (index of value in NamedColor)
   */
  public byte getBGByte() {
    return (byte) this.bg.ordinal();
  }

  /**
   *  @return foreground color as byte (index of value in NamedColor)
   */
  public byte getFGByte() {
    return (byte) this.fg.ordinal();
  }

  /**
   *  @return symbol as byte
   */
  public byte getSymbolByte() {
    return (byte) this.symbol.ordinal();
  }
}
