import java.io.IOException;

public class Menu {
    private String[] options;
    private int selectedIndex = 0;

    public Menu(String[] options) {
        this.options = options;
    }

    private void drawMenu() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
        for (int i = 0; i < options.length; i++) {

            System.out.print("\r");
            if (i == selectedIndex) {

                System.out.print("\033[7m" + options[i] + "\033[0m");
            } else {
                System.out.print(options[i]);
            }
            System.out.println();
        }
    }
    
    public int run() throws IOException {
        setTerminalRawMode();
        // Hide the cursor
        System.out.print("\033[?25l");
        try {
            drawMenu();
            while (true) { 
                int ch = System.in.read();
                if (ch == 27) {
                    if (System.in.available() >= 2) {
                        System.in.read();
                        int arrowKey = System.in.read();
                        if (arrowKey == 'A') {
                            selectedIndex--;
                            if (selectedIndex < 0) {
                                selectedIndex = options.length - 1;
                            }
                        } else if (arrowKey == 'B') {
                            selectedIndex++;
                            if (selectedIndex >= options.length) {
                                selectedIndex = 0;
                            }
                        }
                    }
                } else if (ch == '\r' || ch == '\n') {
                    return selectedIndex;
                }
                drawMenu();
            }
        } finally {
            System.out.print("\033[?25h");
            resetTerminalMode();
        }
    }
    
    private void setTerminalRawMode() {
        try {
            Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c", "stty -echo raw </dev/tty"}).waitFor();
        } catch (Exception e) {
            System.err.println("Could not set terminal to raw mode.");
        }
    }

    private void resetTerminalMode() {
        try {
            Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c", "stty echo cooked </dev/tty"}).waitFor();
        } catch (Exception e) {
            System.err.println("Could not reset terminal mode.");
        }
    }
}
