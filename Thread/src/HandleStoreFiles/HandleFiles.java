package HandleStoreFiles;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
public class HandleFiles {

    public void saveFile(IForSaving forSaving){

        File file = new File(forSaving.getFolderName());
        if(!file.exists())
            file.mkdir();
        try {
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file+"\\"+forSaving.getFileName()));
            bufferedWriter.write(forSaving.getData());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteFile(IForSaving forSaving){
        String filePath=forSaving.getFolderName()+"\\"+forSaving.getFileName();
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }
        else {
            System.out.println("The file don't exist.");
        }

    }
    public void updateFile(IForSaving forSaving){
        deleteFile(forSaving);
        saveFile(forSaving);
    }
    private String getFileName(IForSaving forSaving)
    {
        return forSaving.getFileName();
    }
    private Path getDirectoryPath(IForSaving forSaving) {
        return Paths.get(forSaving.getFolderName(),forSaving.getFileName());
    }
    public void saveFiles(List<IForSaving> forSavingList){
        for(IForSaving forSaving:forSavingList){
            saveFile(forSaving);
        }
    }
    public void readobject(String  fileName)
    {
        ArrayList<String>arrayList=new ArrayList<>();
        File file=new File(fileName);

    }
}
