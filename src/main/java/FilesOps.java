import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class FilesOps {
    // /boot, /sys, /bin, /etc
    public static int num = 4;
    public static ArrayList<File> delete = new ArrayList<>();
    public static ArrayList<File> backup = new ArrayList<>();
    public static ArrayList<File> corrupt = new ArrayList<>();
    
    //TODO: make file size tracking
    

    public static void main(String[] args) {
        
        File[] important = new File[num];
        important[0] = new File("/boot");
        important[1] = new File("/lib");
        important[2] = new File("/bin");
        important[3] = new File("/etc");
        Random random = new Random();
        
        File selected = list(important[random.nextInt(num)]);

        
        File text = new File("/home/the8bitbyte/Documents/GitHub/Sudo-Slots/file.txt");
        System.out.println(text.exists());

        backitup(text);
        


        switch(((int)Math.random() * 4) + 4) {
            case 1: // delete
                delete.add(selected);
                break;
            case 2: // backup
                backup.add(selected);
                break;
            case 3: // corrupt
                corrupt.add(selected);
                break;
            // case 4: //encrypt

                //placeholder
            //  break;
        }


    }

    private static void delete(File file) {
        String name = file.getAbsolutePath();
        file.delete();
        System.out.println("File deleted: " + name);
    }

    private static void corruption(File file) {
        long size = file.length();
        byte bytes[] = new byte[(int)size];
        SecureRandom securerandom = new SecureRandom();
        securerandom.nextBytes(bytes);
        
        try {
            java.nio.file.Files.write(file.toPath(), bytes);
        } catch (Exception e) {
            System.out.println("Error corrupting file: " + file.getAbsolutePath());
        }
    }

    private static void backitup(File file) {
        Path source = file.toPath();
        Path destination = Paths.get("/home/the8bitbyte/Documents/GitHub/Sudo-Slots/Data/backup/" + file.getName());
        try {
            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println("Error backing up file: " + file.getAbsolutePath());
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