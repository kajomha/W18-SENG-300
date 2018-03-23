package CountJavaTypes;

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
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class CountJavaTypes {
	
	 public static Hashtable<String, int[]> table = new Hashtable<String, int[]>();
	
	 public static void main(String[] args) throws FileNotFoundException{
		 
		 // Store command line arguments
		 String pathname = args[0];
		
		 //Print confirmations of recieved input
		 System.out.println("You have selected the following directory:\n\t" + pathname + "\n");
		 
		 //Open the directory to read files from
		 File directory = new File(pathname);
		 //Lists all files from directory
		 File[] list = directory.listFiles();
		 
		 //Boolean to check if directory has a .java file
		 boolean no_file = true;

		 
		 //Loop to iterate though files in the directory
		 for (int i = 0; i<list.length; i++) {
			 if (list[i].isFile()) {
				 
				 //Look for files with .java extension
				 int temp = list[i].getName().lastIndexOf('.');
				 String filetype = list[i].getName().substring(temp+1);
				 
				 if (filetype.contentEquals("java")) {
					 //Set boolean to show .java file was found
					 no_file = false;
					 
					 //Use scanner to read code from file. Stores whole doc in a string
					 String code = new Scanner(list[i]).useDelimiter("\\A").next();

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
					
				/* not sure what do do about variable declarations yet. don't know if we need them?
				//Count varriable declarations and get names
				public boolean visit(VariableDeclarationFragment node) {
					
					System.out.println("VARDFRA" + node.getName().getFullyQualifiedName());
					
					//Get parent node of VariableDeclarationFragment to get the data type
					String field = node.getParent().toString();
					
					//The following is a get around since I havent been able to resolve bindings
					//It simply rediects an input of java.lang.String to become String
					String type2;
					
					if (type.equals("java.lang.String")) {
						type2 = "String";
					}else {
						type2 = type;
					}
					
					//Checks to see of gotten parent string, which houses the entire variable declaration,
					//starts with type2 + [] to get the type of the declared variable
					// the + " " is used so that an input of "int" does not trigger "int[]"
					//This is a work around as well
					if (field.startsWith(type2 + " ")) {
						
						//varname is added to hash in order to count references later
						SimpleName name = node.getName();
						this.names.add(name.getIdentifier());
						
						//This will make sure that if input is primitive or String, declarations count is not incremented
						//and instead, reference count is incremented
						if (list.contains(type2) == false) {
							counters[0] = counters[0] + 1;
						}else {
							counters[1] = counters[1] + 1;
						}
					}
					
					return false;				
				}	
				
				*/
	 
				//Count references for all vars for given data type
//				public boolean visit(SimpleName node) {
//					
//					if (node.resolveBinding() != null) {
//						
//						String nodename = node.resolveBinding().getName();
//
//						updateTable(nodename, "Reference");
//						
//					}
//					
//					return true;
//				}
				
				
				
				@Override
				public boolean visit(SimpleType node) {
					
					if (node.resolveBinding() != null) {
						
						String nodename = node.resolveBinding().getBinaryName();

						updateTable(nodename, "Reference");
						
					} else {
						
						// THIS ONE MIGHT NEED SOME CHANGES
						// currently I can't figure out how to get a fully qualified name for
						// types imported from eclipse
						
						// for example, ASTVisitor should presumably have a fully qualified name of
						// org.eclipse.jdt.core.dom.ASTVisitor
						// but resolveBinding() run on such a node is null
						
						// as such, for now we just add those nodes as non-fully-qualified names
						
						String nodename = node.getName().toString();

						updateTable(nodename, "Reference");
						
					}
					
					return false;

				}
				

				
				public boolean visit(PrimitiveType node) {
					
						
						String nodename = node.getPrimitiveTypeCode().toString();
						
						updateTable(nodename, "Reference");

					
					return false;

				}
				
				public boolean visit(AnonymousClassDeclaration node) {
					
					
					if (node.resolveBinding() != null) {
						
						// MAY NEED TO BE CHANGED!
						// anonymous classes don't have names, obviously, which complicates counting
						// them in our table
						
						// at current I just have it count "Anonymous" as a separate declaration
						// but I don't know if that's how Prof. Walker would want them counted
						
						updateTable("Anonymous", "Declaration");
						
					}

				
				return false;

				}
				
				@Override
				public boolean visit(ArrayAccess node) {
					
						
					String nodename = node.getArray().resolveTypeBinding().getQualifiedName();

					updateTable(nodename, "Reference");
						
					
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