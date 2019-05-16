package files;

import static org.junit.Assert.*;
import java.io.*;
import org.junit.Test;


public class FileCheckerTest {
	
	private class MockFilenameFilter implements FilenameFilter{
		public boolean accept(File dir, String name) {
			return true;
		}
	}
	
	private class MockFileListener implements FileListener{
		protected int doAdd = 0;
		protected int doRemove = 0;
		public void fileAdded(FileEvent e) {
			this.doAdd += 1;
		}
		public void fileRemoved(FileEvent e) {
			this.doRemove += 1;
		}
		public int getDoAdd() {
			return this.doAdd;
		}
		public int getDoRemove() {
			return this.doRemove;
		}
	}

	@Test
	public void testConstructor() {
		assertNotNull(new FileChecker(new MockFilenameFilter(), new File("test")));
	}
	
	@Test
	public void testGetFileListeners() {
		FileChecker fc = new FileChecker(new MockFilenameFilter(), new File("test"));
		assertTrue(fc.getFileListeners().isEmpty());
	}

	@Test
	public void testGetFilter() {
		FilenameFilter filter = new MockFilenameFilter();
		FileChecker fc = new FileChecker(filter, new File("test"));
		assertSame(filter, fc.getFilter());
	}

	@Test
	public void testGetPath() {
		File path = new File("test");
		FileChecker fc = new FileChecker(new MockFilenameFilter(), path);
		assertSame(path, fc.getPath());
	}

	@Test
	public void testGetFilesKnown() {
		FileChecker fc = new FileChecker(new MockFilenameFilter(), new File("test"));
		assertTrue(fc.getFilesKnown().isEmpty());
		String newF = "newFile";
		fc.filesKnown.add(newF);
		assertTrue(fc.getFilesKnown().contains(newF));
	}

	@Test
	public void testAddFileListener() {
		FileChecker fc = new FileChecker(new MockFilenameFilter(), new File("test"));
		FileListener l1 = new MockFileListener();
		fc.addFileListener(l1);
		assertTrue(fc.getFileListeners().contains(l1));
		FileListener l2 = new MockFileListener();
		fc.addFileListener(l2);
		assertTrue(fc.getFileListeners().contains(l1));
		assertTrue(fc.getFileListeners().contains(l2));
	}

	@Test
	public void testRemoveFileListener() {
		FileChecker fc = new FileChecker(new MockFilenameFilter(), new File("test"));
		FileListener l1 = new MockFileListener();
		fc.addFileListener(l1);
		FileListener l2 = new MockFileListener();
		fc.addFileListener(l2);
		assertTrue(fc.getFileListeners().contains(l1));
		assertTrue(fc.getFileListeners().contains(l2));
		fc.removeFileListener(l1);
		assertFalse(fc.getFileListeners().contains(l1));
		assertTrue(fc.getFileListeners().contains(l2));
		fc.removeFileListener(l2);
		assertFalse(fc.getFileListeners().contains(l2));
	}
	
	@Test
	public void testActionPerforedFireFileAdded1File() throws IOException{
		// Création de dossier
		File dir = new File("JeTest");
		dir.mkdirs();
		// Création fichier
		File f1 = new File(dir.getPath()+"/testFich1.txt");
		f1.createNewFile();
		//FC
		FileChecker fc = new FileChecker(new MockFilenameFilter(), dir);
		MockFileListener l1 = new MockFileListener();
		fc.addFileListener(l1);
		MockFileListener l2 = new MockFileListener();
		fc.addFileListener(l2);
		assertSame(0,l1.getDoAdd());
		assertSame(0,l2.getDoAdd());
		fc.actionPerformed(null);
		assertSame(1,l1.getDoAdd());
		assertSame(1,l2.getDoAdd());
		// Suppression fichiers et dossier créé
		f1.delete();
		dir.delete();
	}
	
	@Test
	public void testActionPerforedFireFileAdded2Files() throws IOException{
		// Création de dossier
		File dir = new File("JeTest");
		dir.mkdirs();
		// Création fichiers
		File f1 = new File(dir.getPath()+"/testFich1.txt");
		File f2 = new File(dir.getPath()+"/testFich2.txt");
		f1.createNewFile();
		f2.createNewFile();
		//FC
		FileChecker fc = new FileChecker(new MockFilenameFilter(), dir);
		MockFileListener l1 = new MockFileListener();
		fc.addFileListener(l1);
		MockFileListener l2 = new MockFileListener();
		fc.addFileListener(l2);
		assertSame(0,l1.getDoAdd());
		assertSame(0,l2.getDoAdd());
		fc.actionPerformed(null);
		assertSame(2,l1.getDoAdd());
		assertSame(2,l2.getDoAdd());
		// Suppression fichiers et dossier créé
		f1.delete();
		f2.delete();
		dir.delete();
	}
	
	@Test
	public void testActionPerforedFireFileRemoved1File() throws IOException{
		// Création de dossier
		File dir = new File("JeTest");
		dir.mkdirs();
		// Création fichier
		File f1 = new File(dir.getPath()+"/testFich1.txt");
		f1.createNewFile();
		//FC
		FileChecker fc = new FileChecker(new MockFilenameFilter(), dir);
		MockFileListener l1 = new MockFileListener();
		fc.addFileListener(l1);
		MockFileListener l2 = new MockFileListener();
		fc.addFileListener(l2);
		fc.actionPerformed(null);
		// Suppression fichiers
		f1.delete();
		assertSame(0,l1.getDoRemove());
		assertSame(0,l2.getDoRemove());
		fc.actionPerformed(null);
		assertSame(1,l1.getDoRemove());
		assertSame(1,l2.getDoRemove());
		// Suppression dossier 
		dir.delete();
	}
	
	
	@Test
	public void testActionPerforedFireFileRemoved2Files() throws IOException{
		// Création de dossier
		File dir = new File("JeTest");
		dir.mkdirs();
		// Création fichiers
		File f1 = new File(dir.getPath()+"/testFich1.txt");
		File f2 = new File(dir.getPath()+"/testFich2.txt");
		f1.createNewFile();
		f2.createNewFile();
		//FC
		FileChecker fc = new FileChecker(new MockFilenameFilter(), dir);
		MockFileListener l1 = new MockFileListener();
		fc.addFileListener(l1);
		MockFileListener l2 = new MockFileListener();
		fc.addFileListener(l2);
		fc.actionPerformed(null);
		// Suppression fichiers
		f1.delete();
		f2.delete();
		assertSame(0,l1.getDoRemove());
		assertSame(0,l2.getDoRemove());
		fc.actionPerformed(null);
		assertSame(2,l1.getDoRemove());
		assertSame(2,l2.getDoRemove());
		// Suppression dossier 
		dir.delete();
	}
	
}
