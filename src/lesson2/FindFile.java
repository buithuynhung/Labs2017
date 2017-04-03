package lesson2;

import java.io.File;

public class FindFile {

    /**
     * Поиск файла с заданным в командной строке именем в указанной ключом -d директории
     * по умолчанию в текущей директории.
     */

    public static boolean fileInFolder(String fileName, String directoryName) {
        File directory = new File(directoryName);
        File[] fList = directory.listFiles();
        for (File file1 : fList) {
            if (file1.getName().equals(fileName)) return true;
        }
        return false;
    }

    //Ключ -r указывает на необходимость поиска также во всех поддиректориях.
    public static boolean fileInSubfolders(String fileName, String directoryName) {
        File directory = new File(directoryName);
        File[] fList = directory.listFiles();
        boolean flag = false;
        for (File file1 : fList) {
            if (file1.isDirectory()) flag = fileInSubfolders(fileName, directoryName + "/" + file1.getName());
            if (flag) return true;
            if (file1.getName().equals(fileName)) return true;
        }
        return false;
    }

    /**
     * Имеется 4 варианта коммандной строки:
     * 1."fileName"
     * 2."-d directoryName fileName"
     * 3."-r fileName"
     * 4."-r -d directoryName fileName"
     */
    public static void main(String[] args) {

        String directory = new File("").getAbsolutePath();

        Boolean case2 = args.length == 3 && args[0].equals("-d");
        Boolean case3 = args.length == 2 && args[0].equals("-r");
        Boolean case4 = args.length == 4 && args[0].equals("-r") && args[1].equals("-d");
        if (!(case2 || case3 || case4) && args.length != 1)
            throw new IllegalArgumentException("Коммандная строка не пишется в верном формате");


        //вывод имени директории
        if (case2 || case4) {
            directory = args[args.length - 2];
            System.out.println(directory);
        }

        //вывод имени файла
        String fileName = args[args.length - 1];
        System.out.println(fileName);

        if (!args[0].equals("-r"))
            System.out.println(fileInFolder(fileName, directory));
        else {
            System.out.println(fileInSubfolders(fileName, directory));
        }
    }
}


