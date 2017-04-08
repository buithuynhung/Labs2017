package lesson2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileSearch {

    private ArrayList<String> result = new ArrayList<>();
    private int count;

    public ArrayList<String> getResult() {
        if (count != 0) {
            for (String str : result) {
                System.out.println(str);
            }
        }
        return result;
    }

    public int getCount() {
        if (count == 0) System.out.println("Нет такого файла в данной директории!");
        else System.out.println("\nFound " + count + " result:\n");
        return count;
    }

    public void search(String fileName, File directory, boolean flagCmd) throws FileNotFoundException {

        if (!directory.isDirectory()) throw new FileNotFoundException();

        if (directory.canRead()) {

            for (File temp : directory.listFiles()) {
                if (flagCmd && temp.isDirectory())
                    search(fileName, temp, flagCmd);
                else {
                    if (temp.getName().equals(fileName)) {
                        result.add(temp.getAbsoluteFile().toString());
                        count++;
                    }
                }
            }
        } else System.out.println(directory.getAbsoluteFile() + "Permission Denied");
    }

    public static void main(String[] args) throws FileNotFoundException {

        FileSearch fileSearch = new FileSearch();
        List<String> cmdLine = Arrays.asList(args);

        if (cmdLine.contains("-h")) {
            System.out.println("Введите нужные вам команды и имя файла, \n" +
                    "[-d] - поиск файла в директории \n" +
                    "[-r] - поиск файла в поддиректриях \n");
        } else {
            if (args.length < 1 || args.length > 4) throw new IndexOutOfBoundsException();
            File directory = new File(new File("").getAbsolutePath());


            if (cmdLine.contains("-d")) {
                String dirName = cmdLine.get(cmdLine.indexOf("-d") + 1).toString();
                directory = new File(dirName);
                System.out.println("Директория, в которой нужно проверить наличие файла: " + directory);
            }
            String fileName = args[args.length - 1];
            System.out.println("Имя найденного файла: " + fileName);

            if (!cmdLine.contains("-r")) {
                fileSearch.search(fileName, directory, false);
                fileSearch.getCount();
                fileSearch.getResult();
            } else {
                fileSearch.search(fileName, directory, true);
                fileSearch.getCount();
                fileSearch.getResult();
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof FileSearch) {
            FileSearch other = (FileSearch) obj;
            if (other.count != this.count) return false;
            for (int i = 0; i < this.result.size(); i++) {
                if (this.result.get(i).equals( other.result.get(i)))
                    return false;
            }
            return true;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (String str: this.result) {
            sb.append(str).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(result.toArray()) + count;
    }
}
