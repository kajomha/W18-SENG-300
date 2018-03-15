package Test_CountJavaTypes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import CountJavaTypes.CountJavaTypes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

//import javax.swing.JFileChooser;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;

class Test_CountJavaTypes {

	//which points to a base directory on your machine - edit this to the dir of your preference
	private static String BASEDIR = "C:\\Users\\jomha\\Desktop\\Eclipse_Workspace";
	
	// Initializing different test directories
	String [] pathname = new String[2];
	
	String Empty = "\\W18-SENG-300-A1-G40-V2\\Tests\\Empty";
	String JavaAndOtherFiles = "\\W18-SENG-300-A1-G40-V2\\Tests\\JavaAndOtherFiles";
	String JavaWithoutJavaExtension = "\\W18-SENG-300-A1-G40-V2\\Tests\\JavaWithoutJavaExtension";
	String MultiJavaFiles = "\\W18-SENG-300-A1-G40-V2\\Tests\\MultiJavaFiles";
	String NoJavaFiles = "\\W18-SENG-300-A1-G40-V2\\Tests\\NoJavaFiles";
	String NonJavaFileWithJavaExtension = "\\W18-SENG-300-A1-G40-V2\\Tests\\NonJavaFileWithJavaExtension";
	String NonJavaFileWithJavaExtensionPlusTrueJavaFile = "\\W18-SENG-300-A1-G40-V2\\Tests\\NonJavaFileWithJavaExtensionPlusTrueJavaFile";
	String OneJavaFile = "\\W18-SENG-300-A1-G40-V2\\Tests\\OneJavaFile";
	String NoSuchDirectory = "\\ALRIGHT\\ALRIGHT\\ALRIGHT";
	
	
	/**
     * Should print "This directory does not contian any files with the exptension: '.java'" to terminal.
     */
	@Test
	void testEmpty() throws FileNotFoundException {
		CountJavaTypes counter = new CountJavaTypes();

		
		pathname[0] = BASEDIR + Empty;
		pathname[1] = "int";
		
		System.out.println(counter.main(pathname));
	}
	
	/**
     * Check when a directory has different files including .java files
     * Should only accept files with .java extension
     */
	@Test
	void testJavaAndOtherFiles() throws FileNotFoundException {
		CountJavaTypes counter = new CountJavaTypes();

		
		pathname[0] = BASEDIR + JavaAndOtherFiles;
		pathname[1] = "int";
		
		System.out.println(counter.main(pathname));
	}
	
	/**
     * Check when a directory has a file with java types but is not a .java file
     * Should not accept this file
     */
	@Test
	void testJavaWithoutJavaExtension() throws FileNotFoundException {
		CountJavaTypes counter = new CountJavaTypes();

		
		pathname[0] = BASEDIR + JavaWithoutJavaExtension;
		pathname[1] = "int";
		
		System.out.println(counter.main(pathname));
	}
	
	/**
     * Check when a directory that has multiple .java files
     * Should accept all files in this directory
     */
	@Test
	void testMultiJavaFiles() throws FileNotFoundException {
		CountJavaTypes counter = new CountJavaTypes();

		
		pathname[0] = BASEDIR + MultiJavaFiles;
		pathname[1] = "int";
		
		System.out.println(counter.main(pathname));
	}
	
	/**
     * Check when a directory that has other files but no .java files
     * Should not accept any files in this directory
     */
	@Test
	void testNoJavaFiles() throws FileNotFoundException {
		CountJavaTypes counter = new CountJavaTypes();

		
		pathname[0] = BASEDIR + NoJavaFiles;
		pathname[1] = "int";
		
		System.out.println(counter.main(pathname));
	}
	
	/**
     * Check when a directory that has a file with .java extension but does not compile
     * Should accept this file but counters should both return 0
     */
	@Test
	void testNonJavaFileWithJavaExtension() throws FileNotFoundException {
		CountJavaTypes counter = new CountJavaTypes();

		
		pathname[0] = BASEDIR + NonJavaFileWithJavaExtension;
		pathname[1] = "int";
		
		System.out.println(counter.main(pathname));
	}
	
	/**
     * Check when a directory that has a file with .java extension but does not compile and others that do
     * Should accept all files
     */
	@Test
	void testNonJavaFileWithJavaExtensionPlusTrueJavaFile() throws FileNotFoundException {
		CountJavaTypes counter = new CountJavaTypes();

		
		pathname[0] = BASEDIR + NonJavaFileWithJavaExtensionPlusTrueJavaFile;
		pathname[1] = "int";
		
		System.out.println(counter.main(pathname));
	}
	
	/**
     * Check when a directory that has a file with .java extension but does not compile and others that do
     * Should accept all files
     */
	@Test
	void testOneJavaFile() throws FileNotFoundException {
		CountJavaTypes counter = new CountJavaTypes();

		
		pathname[0] = BASEDIR + OneJavaFile;
		pathname[1] = "int";
		
		System.out.println(counter.main(pathname));
	}
	
	/**
     * Check when a directory does not exist
     * Should throw exception
     */
	@Test
	void testNoSuchDirectory() throws FileNotFoundException {
		CountJavaTypes counter = new CountJavaTypes();

		
		pathname[0] = BASEDIR + NoSuchDirectory;
		pathname[1] = "int";
		
		System.out.println(counter.main(pathname));
	}
	
	
    /**
     * Checks that a parser can be constructed for JLS9.
     */
    @Test
    public void testCreateParserForJLS9() {
    	assertNotNull(ASTParser.newParser(AST.JLS9));
    }

    
    

}
