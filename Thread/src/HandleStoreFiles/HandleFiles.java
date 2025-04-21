package HandleStoreFiles;
import Business.InquiryManager;
import Data.Complaint;
import Data.Inquiry;
import Data.Question;
import Data.Request;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
public class HandleFiles {

    public void saveFile(IForSaving forSaving) {

        File file = new File(forSaving.getFolderName());
        if (!file.exists())
            file.mkdir();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file + "\\" + forSaving.getFileName()));
            bufferedWriter.write(forSaving.getData());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteFile(IForSaving forSaving) {
        String filePath = forSaving.getFolderName() + "\\" + forSaving.getFileName();
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        } else {
            System.out.println("The file don't exist.");
        }

    }

    public void updateFile(IForSaving forSaving) {
        deleteFile(forSaving);
        saveFile(forSaving);
    }

    private String getFileName(IForSaving forSaving) {
        return forSaving.getFileName();
    }

    private Path getDirectoryPath(IForSaving forSaving) {
        return Paths.get(forSaving.getFolderName(), forSaving.getFileName());
    }

    public void saveFiles(List<IForSaving> forSavingList) {
        for (IForSaving forSaving : forSavingList) {
            saveFile(forSaving);
        }
    }

    public IForSaving readFile(File f) {
        ArrayList<String> arrayList = new ArrayList<>();
        Inquiry newObj = null;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for (String value : values) {
                    arrayList.add(value);
                }
            }
            InquiryManager.nextCodeVal= Integer.valueOf(arrayList.get(1));

            switch (arrayList.get(0)) {
                case "Question":
                    newObj = new Question();
                    break;
                case "Request":
                    newObj = new Request();
                    break;
                case "Complaint":
                    newObj = new Complaint();
                    break;
            }
            newObj.parseFromFile(arrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newObj;
    }
}


