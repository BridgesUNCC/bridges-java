package bridges.data_src_dependent;

public class ElevationData {

    private int [][] data;
    private int cols;
    private int rows;
    private int xll;
    private int yll;
    private int cellsize;
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

    public ElevationData (int[][] data, int cols, int rows, int xll, int yll, int cellsize, int maxVal){
        this.setData(data);
        this.setCols(cols);
        this.setRows(rows);
        this.setxll(xll);
        this.yll = yll;
        this.cellsize = cellsize;
        this.maxVal = maxVal;
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

    public int getxll(){
        return xll;
    }

    public void setxll(int xll){
        this.xll = xll;
    }

    public int getyll(){
        return yll;
    }
    
    public void setyll(int yll){
        this.yll = yll;
    }

    public int getCellSize(){
        return cellsize;
    }

    public void setCellSize(int cellsize){
        this.cellsize = cellsize;
    }

    public int getMaxVal(){
        return maxVal;
    }

    public void setMaxVal(int maxVal){
        this.maxVal = maxVal;
    }


}