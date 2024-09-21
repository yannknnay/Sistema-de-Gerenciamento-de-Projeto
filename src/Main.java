import controller.ManagerProject;
import view.GraphicalInterface;

public class Main {
    public static void main(String[] args) {
            ManagerProject manager = new ManagerProject();
            GraphicalInterface graphicalInterface = new GraphicalInterface(manager);
            graphicalInterface.setVisible(true);
    }
}

