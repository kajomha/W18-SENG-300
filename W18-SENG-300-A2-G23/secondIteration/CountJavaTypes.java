package secondIteration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

//import javax.swing.JFileChooser;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class CountJavaTypes {
	
	 public static Hashtable<String, int[]> table = new Hashtable<String, int[]>();
	
	 public static void main(String[] args) throws IOException{
		 
		 // Store command line arguments
		 String pathname = args[0];
		 
		 //Boolean to check if directory has a .java file
		 boolean no_file = true;
		 
		 //Print confirmations of recieved input
		 System.out.println("You have selected the following directory:\n\t" + pathname + "\n");
		 
		 //Open the directory to read files from
		 File directory = new File(pathname);
		 //Lists all files from directory
		 File[] fileList = directory.listFiles();
		 List<File>tempList = Arrays.asList(fileList);
		 List<File>list = new ArrayList<>();
		 for (int i = 0; i<tempList.size(); i++) {
			 list.add(tempList.get(i));
		 }
		 
		 for (int i = 0; i<list.size(); i++) {
			 if (list.get(i).isDirectory()) {
				 String newpath = list.get(i).getAbsolutePath();
				 File subdir = new File(newpath);
				 fileList = subdir.listFiles();
				 
				 for (int j=0;j<fileList.length;j++) {
					 list.add(fileList[j]);
				 }
				 
			 }
			 
			 if (list.get(i).getName().endsWith(".jar")) {
				 JarFile jar = new JarFile(list.get(i).getAbsolutePath());
				 Enumeration<JarEntry> e = jar.entries();
				 while (e.hasMoreElements()) {
					 JarEntry entry = (JarEntry)e.nextElement();
					 String jarfiles = entry.getName();
					 InputStream in = null;
					 
					 if (jarfiles.endsWith(".java")) {
						 
						 URL inputURL = new URL("jar:file:/" + list.get(i).getAbsolutePath()+"!/"+jarfiles);
						 JarURLConnection conn = (JarURLConnection)inputURL.openConnection();
						 in = conn.getInputStream();
					 
						 BufferedReader reader = new BufferedReader(new InputStreamReader(in));
						 StringBuilder out = new StringBuilder();
						 String jartext;
						 while ((jartext = reader.readLine()) != null) {
							 out.append(jartext);
						 }
						 String jarcode = out.toString();
						 no_file = false;
						 parse(jarcode,pathname);
					 
						 reader.close();
					 }

				 }
			 }
			 
			 
		 }
		 

		 
		 //Loop to iterate though files in the directory
		 for (int i = 0; i<list.size(); i++) {
			 if (list.get(i).isFile()) {
				 
				 //Look for files with .java extension
				 int temp = list.get(i).getName().lastIndexOf('.');
				 String filetype = list.get(i).getName().substring(temp+1);
				 
				 if (filetype.contentEquals("java")) {
					 //Set boolean to show .java file was found
					 no_file = false;
					 
					 //Use scanner to read code from file. Stores whole doc in a string
					 String code = new Scanner(list.get(i)).useDelimiter("\\A").next();

					 // Parse the given code; this method will update the hashtable
					 parse(code, pathname);

				 }
			 }
		 }
		 
		 //Print out results
		 if (no_file == true) {
			 System.out.println("This directory does not contian any files with the exptension: '.java'");
		 }
		 
		 else {
			 reportTable();
		 }
		 
	  }  
	 
	 //Parse method
	 public static void parse(String str, String pathname) {
		 
		 //Create AST Parser
		    
			ASTParser parser = ASTParser.newParser(AST.JLS9);
			parser.setSource(str.toCharArray());
			
			Map<String, String> options = JavaCore.getOptions();
			JavaCore.setComplianceOptions(JavaCore.VERSION_1_5, options);
			parser.setCompilerOptions(options);
			
			//parser.setBindingsRecovery(true);
			parser.setResolveBindings(true);
			parser.setKind(ASTParser.K_COMPILATION_UNIT);
			
			parser.setEnvironment(new String[] { System.getProperty("java.home") + "/lib/rt.jar" }, new String[] { pathname },
					null, false);
			
			parser.setUnitName("");
			
			//Initialize counters to 0
			int[] counters = {0,0};
			
			Hashtable<String, int[]> collection = new Hashtable<String, int[]>();
			
			final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
	 
			//Visit string
			cu.accept(new ASTVisitor() {
					 
				//Visits Class and Interface declaration, i.e. the assign1 class.
				public boolean visit(TypeDeclaration node) {
					
					String nodename = node.getName().getFullyQualifiedName();
					
					//System.out.println("Type Declaration: " + nodename);
					
					updateTable(nodename, "Declaration");
					
					return true;				

				}			
				
				//Visits annotations
				public boolean visit(AnnotationTypeDeclaration node) {
					
					String nodename = node.getName().getFullyQualifiedName();
					
					//System.out.println("Annotation: " + nodename);
					
					updateTable(nodename, "Declaration");
					
					return false;				
				}
				
				//Visits Enumerations
				public boolean visit(EnumDeclaration node) {
					
					String nodename = node.getName().getFullyQualifiedName();
					
					//System.out.println("Enumeration: " + nodename);
					
					updateTable(nodename, "Declaration");
					
					return false;				
				}
				
				//Used to count references after getting variable names
				Set<String> names = new HashSet<String>();
				
				//Looks like we're not supposed to count primitive + String var declarations as type declarations
				//The following arary turned list is used to test if give type is one of them
				String[] primitive = new String[] {"int", "char", "short", "double", "boolean", "byte", "float", "long", "String" };
				List<String> list = Arrays.asList(primitive);
					
				//Count varriable declarations and get names
				public boolean visit(FieldDeclaration fd) {
					
					//gets the type of the declarations in the list
					Type type = fd.getType();
					List fragmentList = fd.fragments();
					VariableDeclarationFragment varDec;
					SimpleName name;
					
					//Extracts the name from each variable declaration of this type
					for	(int i = 0; i < fragmentList.size(); i++)
					{
						
						//varname is added to hash in order to count references later
						varDec = fragmentList.get(i);
						name = varDec.getName();
						this.names.add(name.toString());
						
						//This will make sure that if input is primitive or String, declarations count is not incremented
						//and instead, reference count is incremented
						if (list.contains(type) == false) {
							counters[0] = counters[0] + 1;
						}else {
							counters[1] = counters[1] + 1;
						}
					}
					
					return false;				
				}	
				
				
	 
				//Count references for all vars for given data type
				public boolean visit(SimpleName node) {
					
					String nodename = node.getFullyQualifiedName();
					
					//System.out.println("SimpleName: " + node.getFullyQualifiedName());
					updateTable(nodename, "Reference");
					
					return true;
				}
				
				@Override
				public boolean visit(SimpleType node) {
					
					if (node.resolveBinding() != null) {
						
						String nodename = node.resolveBinding().getBinaryName();

						updateTable(nodename, "Reference");
						
					}
					
					return false;

				}
				
				@Override
				public boolean visit(ArrayAccess node) {
					

					if (node.resolveTypeBinding() != null) {
						
						String nodename = node.resolveTypeBinding().getBinaryName();

						updateTable(nodename, "Reference");
						
					}
					
					return false;

				}
				
			});
			//Return updated counters
			// return collection;
		}
	 
	 // takes the name we got from a node, and a string telling us whether to increment Reference or Declaration count
	 
	 public static void updateTable (String nodename, String type) {
		 
		int index = 0;
		 
		if (type.equals("Reference")) {
			index = 1;
		} else if (type.equals("Declaration")) {
			index = 0;
		}
		
		int i_array[] = {0,0};
			
		if (table.get(nodename) == null) {
				
			i_array[index] = 1;
			table.put(nodename, i_array);
		
		} else {
				
			i_array = table.get(nodename);
			i_array[index] += 1;
			table.put(nodename,  i_array);
			
		}
			
		//System.out.println("Count for \"" + nodename + "\" is " + table.get(nodename)[index]);
		 
	 }
	 
	 // displays all of the information stored in our table
	 
	 public static void reportTable () {
		 
		 // use an enumeration of keys to find the type names, and then use the keys to get the counts
		 
		 Enumeration<String> elements = table.keys();
		 
		 while(elements.hasMoreElements()) {
			 
			 String s = elements.nextElement();
			 int[] a = table.get(s);
			 
			 System.out.println(s +". Declarations found: " + a[0] + "; references found: " + a[1] + ".");
			 
		 }
		 
		 
	 }
	 
}
