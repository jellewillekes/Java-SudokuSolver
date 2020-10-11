import java.io.*;


public class SudokuSolver{
  
  public static void main(String[] args) {
    try {
      Sudoku sudoku = new Sudoku(args[0]);
      Queue<Cell> hints = sudoku.hints();
      //Stack<Sudoku> stackOfCopies = new Stack<Sudoku>();
      //Cell firstUnsolved= null;
     
      int countLogicRules=0;
      int countGeneralization=0;
      int countEnumeration = 0;
      
      while (!sudoku.isSolved()){
        
        while(!hints.isEmpty() &&!sudoku.isSolved()) {
            Sudoku.firstSolve(sudoku.getFinalSudoku(), hints);
            countLogicRules++;}
            
        if (Sudoku.foundRowGeneralization(hints, sudoku.getFinalSudoku())) {
          Sudoku.rowGeneralization(hints,sudoku.getFinalSudoku());
          countGeneralization++;}
        if (Sudoku.foundColGeneralization(hints, sudoku.getFinalSudoku())) {
          Sudoku.colGeneralization(hints,sudoku.getFinalSudoku());
          countGeneralization++;}
        if (Sudoku.foundBoxGeneralization(hints, sudoku.getFinalSudoku())){
          Sudoku.boxGeneralization(hints,sudoku.getFinalSudoku());
          countGeneralization++;}
        
        if (hints.isEmpty() ){
          Sudoku.enumeration(sudoku, hints);}
      }
      
          
        
        
        
        
        
        /*if (hints.isEmpty()){
          firstUnsolved = Sudoku.firstUnsolvedCell(sudoku.getFinalSudoku());
          Sudoku.enumerationStack(sudoku, stackOfCopies, firstUnsolved);
          countEnumeration++;}
      }
        
        while (!stackOfCopies.isEmpty()) {
            sudoku = stackOfCopies.pop();
            for (int i=0; i<hints.size();i++) {
              hints.dequeue();}
            hints.enqueue(sudoku.getFinalSudoku()[firstUnsolved.getRow()][firstUnsolved.getCol()]);
            
          outerLoop:
            while (!sudoku.isSolved()) {
            while (!hints.isEmpty() && !sudoku.isSolved()){
              Sudoku.firstSolve(sudoku.getFinalSudoku(), hints);
              countLogicRules++;
              if (Sudoku.containsEmptyCell(sudoku.getFinalSudoku())) {
                break outerLoop;}
            }
            if (!Sudoku.containsEmptyCell(sudoku.getFinalSudoku())) {
              if (Sudoku.foundRowGeneralization(hints, sudoku.getFinalSudoku())) {
                Sudoku.rowGeneralization(hints,sudoku.getFinalSudoku());
                countGeneralization++;}
              if (Sudoku.foundColGeneralization(hints, sudoku.getFinalSudoku())) {
                Sudoku.colGeneralization(hints,sudoku.getFinalSudoku());
                countGeneralization++;}
              if (Sudoku.foundBoxGeneralization(hints, sudoku.getFinalSudoku())){
                Sudoku.boxGeneralization(hints,sudoku.getFinalSudoku());
                countGeneralization++;}
              if (hints.isEmpty()){
                firstUnsolved = Sudoku.firstUnsolvedCell(sudoku.getFinalSudoku());
                Sudoku.enumerationStack(sudoku, stackOfCopies, firstUnsolved);
                countEnumeration++;}
            }
            }
            if (sudoku.isSolved()) {
              break;}
        }*/
              
            if (sudoku.isSolved()) {
              sudoku.print();
              System.out.println("The amount of times the logic rules were used: " + countLogicRules);
              System.out.println("The amount of times the generalization rules were used: " + countGeneralization);
              System.out.println("The amount of times the enumeration rules were used: " + countEnumeration);}
        
    }
    catch (FileNotFoundException e){
          e.printStackTrace();}
  }
}