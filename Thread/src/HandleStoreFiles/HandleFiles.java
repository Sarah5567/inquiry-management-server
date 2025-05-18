package HandleStoreFiles;
import Business.*;
import Data.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
        Representative representative = null;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                System.out.println(values.length);
                for (String value : values) {
                    arrayList.add(value);
                }
            }
            System.out.println(arrayList.size());
            InquiryManager.nextCodeVal = Integer.valueOf(arrayList.get(1));

            switch (arrayList.get(0)) {
                case "Question":
                    newObj = new Question();
                    newObj.parseFromFile(arrayList);
                    break;
                case "Request":
                    newObj = new Request();
                    newObj.parseFromFile(arrayList);
                    break;
                case "Complaint":
                    newObj = new Complaint();
                    newObj.parseFromFile(arrayList);
                    break;
                case "Representative":
                    representative = new Representative();
                    representative.parseFromFile(arrayList);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newObj == null ? representative : newObj;
    }
    public void moveInquiryToHistory(IForSaving iforSavingToDelete){
        boolean foundToDelete = false;
        Inquiry inquiryToDelete = (Inquiry)iforSavingToDelete;
        File[] folders = {
                new File("Complaint"),
                new File("Request"),
                new File("Question")
        };
        for (File folder : folders) {
            if (foundToDelete) break;
            File[] files = folder.listFiles();
            if (files != null)
                for (File file : files) {
                    try {
                        Inquiry inquiryFromFile = (Inquiry) readFile(file);
                        if (inquiryToDelete.getCode() == inquiryFromFile.getCode()) {
                            deleteFile(iForSaving(inquiryFromFile));
                            saveInquiryInHistory(iforSavingToDelete);
                            foundToDelete = true;
                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        }
        return result;
    }
    public void saveInquiryInHistory(IForSaving iForSaving){
        File file = new File("History");
        if (!file.exists())
            file.mkdir();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file + "\\" + iforSaving.getFileName()));
            bufferedWriter.write(iforSaving.getData());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


