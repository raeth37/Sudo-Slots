import java.io.File;
import java.io.IOException;

public class SudoSlot {

    public static void main(String[] args) throws IOException {
        File checkFile = new File(System.getProperty("user.home") + "/.check");
        if (!checkFile.exists()) {
            System.out.println("Error: Check file exists");
            System.exit(1);
        }
        if (!System.getProperty("os.name").toLowerCase().contains("linux")) {
            System.out.println("Error: This program is only supported on Linux");
            System.exit(1);
        }

        if (!"root".equals(System.getProperty("user.name"))) {
            System.out.println("Error: Sudo-Slots requires admin permissions to run, please re-run as root");
            System.exit(1);
        }
        System.out.println("all checks passed, continuing with execution");

        String[] menuOptions = {"Play", "Settings", "Exit"};
        Menu menu = new Menu(menuOptions);

        int selectedIndex = menu.run();
        
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("You selected: " + menuOptions[selectedIndex]);
    }
}
