import java.io.File;
import java.util.List;
import java.util.Random;

public class Files {
    // /boot, /sys, /bin, /etc
    public static int num = 4;
    
    public static void main(String[] args) {
        // File[] roots = File.listRoots();
        File[] important = new File[num];
        important[0] = new File("/boot");
        important[1] = new File("/lib");
        important[2] = new File("/bin");
        important[3] = new File("/etc");
        Random random = new Random();
        
        File selected = list(important[random.nextInt(num)]);
        switch(((int)Math.random() * 4) + 4) {
            case 1:
                //placeholder
                break;
            case 2:
                //placeholder
                break;
            case 3:
                //placeholder
                break;
            case 4: 
                //placeholder
                break;
        }

    }

    // method to recursively get all file names
    private static void listfiles(File dir, List<File> SudoFilesList){
        if (dir == null || !dir.exists() || !dir.isDirectory() || !dir.canRead()) {
            return;
        }

        File[] files = dir.listFiles();
        System.out.println("Current file:" + dir.getAbsolutePath());

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listfiles(file, SudoFilesList);
                } else if (file.isFile() && file.canRead()) {
                    SudoFilesList.add(file);
                }

            }
        }
    }
    
    private static File list(File dir) {
        Random random = new Random();
        File[] current = dir.listFiles();
        if (current == null || current.length == 0) {
            return dir; // Return the directory itself if it has no files
        }
        File randomFile = current[random.nextInt(current.length)];
        
        if (randomFile.isDirectory()) {
            return list(randomFile); // Recursive call must return a File
        } else {
            return randomFile; // Return the file if it's not a directory
        }
    }
}