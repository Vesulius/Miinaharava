
public class Main {

    public static void main(String[] args) {

        BoardGenerator b = new BoardGenerator();
        
        b.print(b.generateBoard(10, 20, 20));
    }
}
