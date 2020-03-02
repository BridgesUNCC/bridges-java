package bridges.data_src_dependent;

public class ElevationData {

    private int [][] data;
    private int cols;
    private int rows;
    private double xll;
    private double yll;
    private double cellsize;
    private int maxVal;


    public ElevationData() {
        this.data = null;
        this.cols = 0;
        this.rows = 0;
        this.xll = 0;
        this.yll = 0;
        this.cellsize = 0;
        this.maxVal = 0;
    }

    public ElevationData (int[][] data, int cols, int rows, double xll, double yll, double cellsize, int maxVal){
        this.setData(data);
        this.setCols(cols);
        this.setRows(rows);
        this.setxll(xll);
        this.setyll(yll);
        this.setCellSize(cellsize);
        this.setMaxVal(maxVal);
    }

    public int[][] getData(){
        return data;
    }

    public void setData(int[][]data){
        this.data = data;
    }

    public int getCols(){
        return cols;
    }

    public void setCols(int cols){
        this.cols = cols;
    }

    public int getRows(){
        return rows;
    }

    public void setRows(int rows){
        this.rows = rows;
    }

    public double getxll(){
        return xll;
    }

    public void setxll(double xll){
        this.xll = xll;
    }

    public double getyll(){
        return yll;
    }
    
    public void setyll(double yll){
        this.yll = yll;
    }

    public double getCellSize(){
        return cellsize;
    }

    public void setCellSize(double cellsize){
        this.cellsize = cellsize;
    }

    public int getMaxVal(){
        return maxVal;
    }

    public void setMaxVal(int maxVal){
        this.maxVal = maxVal;
    }


}