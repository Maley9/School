import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Folder {

    public void walk(String path){
        File root = new File(path);
        File[] list = root.listFiles();


        BasicFileAttributes attr;


        if(list == null) return;

        for(File f : list){
            if(f.isDirectory()){
                walk(f.getAbsolutePath());
                System.out.println("Dir " + f.getAbsoluteFile());
            }
            else {
                System.out.println("File " + f.getAbsoluteFile());
            }

            try {
                attr = Files.readAttributes(Path.of(path),BasicFileAttributes.class);
                FileTime time = attr.creationTime();

                String pattern = "yyyy-MM-dd HH:mm:ss";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

                String formatted = simpleDateFormat.format(new Date(time.toMillis()));

                System.out.println("Check " + formatted);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {


        Folder folder = new Folder();
        folder.walk("C:\\Users\\Aleksandra\\Desktop\\KursUdemyJava\\Uczelnia");
    }
}
