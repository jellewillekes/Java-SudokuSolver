import java.util.*;
import java.io.*;

public class Sudoku { 
  private Cell[][] mySudoku;
  
   public Sudoku (){
   this.mySudoku = new Cell[9][9];
  }
    
  //hints are the solved cells, no 0 in it
  public Queue<Cell> hints() {
    Queue<Cell> hints = new Queue<Cell>();
    for (int i=0; i<9; i++) {
      for (int j=0; j<9; j++) {
        if (this.mySudoku[i][j].theValue() !=0){
          hints.enqueue(this.mySudoku[i][j]);
        }
      }
    }
    return hints;
  }
     public Cell[][] returnSudoku(){
    return this.mySudoku;
    }
    
  //Logic rules
  //you've already fill in the unsovled cell with numbers from 1 to 9, compare it with the solved cell
  //if the candidate is duplicated with the solved cell(hints), then delete it from the candidats list
  //so you check by rows first
     public static void logicRules( Queue<Cell> hints, Cell[][] mySudoku, Cell duplicatedNum){
    for (int i=0; i<9; i++|){
     
    if ((!duplicatedNum.isEmpty()) && (duplicatedNum.colIndex() !=i) && (!mySudoku[duplicatedNum.rowIndex()][i].isEmpty())){
     mySudoku[duplicatedNum.rowIndex()][i].remove(duplicatedNum.theValue());
    
    
      if ((mySudoku[duplicatedNum.rowIndex()][i].theValue() ==0) && (mySudoku[duplicatedNum.rowIndex()][i].solved())){
          mySudoku[duplicatedNum.rowIndex()][i].theValue(mySudoku[duplicatedNum.rowIndex()][i].returnCandidates().get(0));
          hints.enqueue(mySudoku[duplicatedNum.rowIndex()][i]);
      }
    }
  }
           
    //then you check by columns
    for (int j=0; j<9; j++){
      //
    if ((!duplicatedNum.isEmpty()) && (duplicatedNum.rowIndex() !=j) && (!mySudoku[j][duplicatedNum.colIndex()].isEmpty())) {
        mySudoku[j][duplicatedNum.colIndex()].remove(duplicatedNum.theValue());
    
    if ((mySudoku[j][duplicatedNum.colIndex()].theValue() ==0) && (mySudoku[j][duplicatedNum.colIndex()].solved())){
          mySudoku[j][duplicatedNum.colIndex()].theValue(mySudoku[j][duplicatedNum.colIndex()].returnCandidates().get(0));
          hints.enqueue(mySudoku[j][duplicatedNum.colIndex()]);
    }
      }
    }
     
    //finally you check by block
    for (int i =0; i <9; i++) {
          for (int j =0; j<9; j++) {
            if ((mySudoku[i][j].blockIndex()==duplicatedNum.blockIndexr()) && (i!=duplicatedNum.rowIndex())||(j!=duplicatedNum.colIndex())) {
               mySudoku[i][j].remove(duplicatedNum.theValue());
             
              if ((mySudoku[i][j].theValue() ==0) && (mySudoku[i][j].solved())) {
                mySudoku[i][j].theValue(mySudoku[i][j].getCandidates().get(0));
                hints.enqueue(mySudoku[i][j]);}
            }
          }
        }
     }
     
     //Generlization
     //you check if there is an unique number in row, column or block, cause this can be a hint
     //first you check by rows
     public static boolean ifRowGen (Cell[][] mySudoku, Queue<Cell> hints){
    if (hints.isEmpty()) {
      for (int i=0; i<9; i++) { //first you fix a row
        for (int k=1; k<10;k++) {  //candidates
          int count = 0;
          for (int j =0; j<9; j++) {  
            if ((mySudoku[i][j].theValue() ==0)  && (mySudoku[i][j].ifcontains(k))){ //if there is a duplicated candidate
              count++;
            }
          }
          if (count == 1){  //you find the unique candidate
            return true;
          }
        }
      }
    }
    return false;
     }
     
     public static void rowGen(Cell[][] mySudoku, Queue<Cell> hints) {
    for (int i=0; i<9; i++) {  //we fix a row
      for (int k=1; k<10;k++){
        int count = 0;
        int col = 0;
        for (int j=0; j<9; j++) {
          if ((mySudoku[i][j].theValue() ==0)  && (finalSudoku[i][j].contains(k)) &&(!finalSudoku[i][j].isEmpty())){
            col = j;
            count++;
          } 
        }
        if (count ==1){
          mySudoku[i][col].setValue(k);
          hints.enqueue(mySudoku[i][col]);
        }
      }
    }
  }
     
     //then you check by columns
      public static boolean ifColGen(Cell[][] mySudoku, Queue<Cell> hints) {
    if (hints.isEmpty()) {
    for (int j=0;j<9;j++) {  //you fix a column
      for (int k=1; k<10; k++) {
        int count = 0;
        for (int i=0; i<9; i++) { 
          if ((mySudoku[i][j].theValue() ==0) && (mySudoku[i][j].ifcontains(k)) ){
            count++;
          }
        }
        if (count == 1){
          return true;
        }
      }
    }
    }
    return false;
  }
      
    public static void colGen(Cell[][] mySudoku, Queue<Cell> hints) {
    for (int j=0;j<9;j++) {   //you fix a column
      for (int k=1; k<10; k++) {
        int count = 0;
        int row=0;
        for (int i=0; i<9; i++) {
          if ((mySudoku[i][j].theValue() ==0) && (mySudoku[i][j].contains(k)) && (!mySudoku[i][j].isEmpty())) {
            row = i;
            count++;
          }
        }
        if (count == 1){
          mySudoku[row][j].setValue(k);
          hints.enqueue(mySudoku[row][j]);
        }
      }
    }
  }
      
      //you chek by blocks
   public static boolean ifBlockGen (Cell[][] mySudoku, Queue<Cell> hints) {
    if (hints.isEmpty()){
    for (int b=1; b<10; b++){    //block Index 
      for (int k=1; k<10; k++){  //candidates
        int count = 0;
        for (int i=0; i<9; i++){
          for (int j=0; j<9; j++){
            if ((mySudoku[i][j].theValue() ==0) && (mySudoku[i][j].ifcontains(k) && (mySudoku[i][j].blockIndex() == b))){
              count++;
            }
        }
        if (count ==1){
          return true;
        }
        }
      }
    }
    }
    return false;
   }  
   
   public static void boxGen(Cell[][] mySudoku, Queue<Cell> hints) {
    for (int b=1; b<10; b++) {    //the first block
      for (int k=1; k<10; k++) {   //candidates
        int count = 0;
        int row=0;
        int col=0;
        for (int i=0; i<9; i++) {
          for (int j=0; j<9; j++) {
            if ((mySudoku[i][j].theValue() ==0) && (mySudoku[i][j].blockIndex() == b)&& (mySudoku[i][j].ifcontains(k))&& (!mySudoku[i][j].isEmpty())) {
              row = i;
              col = j;
              count++;
             }
          }
        }
        
        if (count ==1){
          mySudoku[row][col].setValue(k);
          hints.enqueue(mySudoku[row][col]);
          }
        }
      }
    }
   
   // print out the reslut
    public void print() {
    for (int i=0; i<9;i++){
      for (int j=0;j<9;j++){
        System.out.print(this.mylSudoku[i][j].theValue() + " ");
      }
       System.out.println();
    }
  }
    
    //Enumeration
    public boolean solved() {
    int solvedNum = 0;
    for (int i=0; i<9; i++) {
      for (int j =0; j<9; j++) {
        if (this.mySudoku[i][j].theValue()!=0) {
          solvedNum++;
        }
      }
    }
    if (solvedNum!==81){
      return false;
    }
    return true;
    }
   
     
       
         
  
  