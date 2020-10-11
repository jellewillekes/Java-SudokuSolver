import java.util.*;

Public class Cell
  private int col;
  private int row;
  private int value;
  
  //assign 1-9 to all the unsolved cells
  public Cell(int row, int col, int value){
    private ArrayList<Integer> candidates;
    this.col= col;
    this.row= row;
    this.value=value;
    this.candidates = new ArrayList<>Integer();
    for(int i=1; i<10; i++){
      if(this.value==0){
        this.candidates.add(i);
      }}}
  
  //the number of the column
  public int colIndex(){
    return this.col;
  }
  
  //the number of the row
  public int rowIndex(){
    return this.row;
  }
  
  //the value in each cell
  public int theValue(){
    return this.value;
  }
  
    //the number of the block
  public int blockIndex(){
    return ((this.row/3)*3 + this.col/3 + 1);}
  
  //the candidates
    public ArrayList<Integer> returnCandidates(){
    return this.candidates;
    }
    
  //solved and unsolved
  public boolean solved(){
    if(this.candidates.size()==1){
    return true;
  }
    return false;
  }
  
  //set the cell's value
public void setValue(int value){
  this.value = value; 
  this.candidates.clear();
}

 // if it contains the value
  public boolean ifcontains(int value){
    return this.candidates.ifcontains(new.Integer(value));
  }
  
  //method to remove a candiate, if it is duplicated 
  public void remove (int candidate) {
    if (this.candidates.ifcontains(new Integer(candidate))){
      this.candidates.remove(new Integer(candidate));
    }
    
    //it is an empty cell,if the value of the cell is 0 and there is no candidate
    public boolean isEmpty(){
      if((this.value==0) && (this.candidate.size()==0)){
        return true;
      }
      return false;
    }
    
  // if it is in the same block
    public boolean sameBlock(Cell otherCell){
       if ((this.row/3 == otherCell.rowIndex()/3) && (this.col/3 == otherCell.colIndex()/3)) {
      return true;
       }
    return false;
    }
      
      
  
    
    
   
  