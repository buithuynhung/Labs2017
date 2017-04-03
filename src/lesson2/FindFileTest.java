package lesson2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindFileTest {
    @Test
    void fileInFolder() {
        assertTrue(FindFile.fileInFolder( "findFile", "input"));
        assertTrue(FindFile.fileInFolder("findFile2", "input/dir"));
        assertTrue(FindFile.fileInFolder("lab3.docx", "C:/Users/ThuyNhung/Downloads/documents"));
        assertFalse(FindFile.fileInFolder("findFile1", "input"));
        assertFalse(FindFile.fileInFolder("lab3.txt", "input/dir/dir1"));
    }

    @Test
    void fileInSubfolders() {
        assertTrue(FindFile.fileInSubfolders("findFile1", "input"));
        assertTrue(FindFile.fileInSubfolders("findFile2", "input"));
        assertFalse(FindFile.fileInSubfolders("lab3.docx", "C:/Users/ThuyNhung/Desktop/q"));
    }
}