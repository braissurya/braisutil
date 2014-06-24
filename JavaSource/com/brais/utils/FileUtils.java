package com.brais.utils;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {


	
	/**
	 * @Method_name	: deleteFile
	 * @author 		: Bertho Rafitya Iwasurya
	 * @since		: Nov 1, 2013 10:16:57 AM
	 * @Description :  Method untuk menghapus suatu file dari server
	 * @param fileName : nama file yang ingin dihapus
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return_type : boolean
	 * @Revision	:
	 * #====#===========#===================#===========================#
	 * | ID	|    Date	|	    User		|			Description		|
	 * #====#===========#===================#===========================#
	 * |	|			|					|							|
	 * #====#===========#===================#===========================#
	 */
	public static void deleteFile(File file) {
		if (file.exists()) {//kalau filenya ada masuk sini 
			if(!file.isDirectory())//kalau bukan direktori masuk sini langsung delete
				file.delete();
			else{// kalau direktori masuk sini
				if(file.list().length==0){//kalau dalam direktori kosong maka langsung delete
					file.delete();
				}else{
					String files[]=file.list();
					//recursive delete seluruh isi direktori sampai file habis
					for(String tmp:files){
						File fileDelete=new File(file,tmp);
						 deleteFile(fileDelete);
					}
					
					if(file.list().length==0){//kalau sudah tidak ada file dalam direktory maka langsung bisa di delete
						file.delete();
					}
				}
			}
		}
	}
	
	
	
	
	
	/**
	 * @Method_name	: listFilesInDirectory
	 * @author 		: Bertho Rafitya Iwasurya
	 * @since		: Nov 1, 2013 10:43:04 AM
	 * @Description : Method untuk melist file2 yang ada dalam suatu directory
	 * @param dir 	: path dir yg ingin di scan 
	 * @return
	 * @return_type : List<File>
	 * @Revision	:
	 * #====#===========#===================#===========================#
	 * | ID	|    Date	|	    User		|			Description		|
	 * #====#===========#===================#===========================#
	 * |	|			|					|							|
	 * #====#===========#===================#===========================#
	 */
	public static List<File> listFilesInDirectory(String dir) {
		File destDir = new File(dir);
		List<File> daftar = new ArrayList<File>();
		if(destDir.exists()) {
			String[] children = destDir.list();
			daftar = new ArrayList<File>();
			for(int i=0; i<children.length; i++) {
				File file = new File(destDir+"/"+children[i]);
				daftar.add(file);
			}
		}
		return daftar;
	}
	
	/**
	 * @Method_name	: copyfile
	 * @author 		: Bertho Rafitya Iwasurya
	 * @since		: Nov 1, 2013 10:31:35 AM
	 * @Description : modul untuk copy file 
	 * @param srFile
	 * @param dtFile
	 * @return_type : void
	 * @Revision	:
	 * #====#===========#===================#===========================#
	 * | ID	|    Date	|	    User		|			Description		|
	 * #====#===========#===================#===========================#
	 * |	|			|					|							|
	 * #====#===========#===================#===========================#
	 */
	public static void copyfile(String srFile, String dtFile){
	    try{
	      File f1 = new File(srFile);
	      File f2 = new File(dtFile);
	      InputStream in = new FileInputStream(f1);
	      
	      //For Overwrite the file.
	      OutputStream out = new FileOutputStream(f2);

	      byte[] buf = new byte[1024];
	      int len;
	      while ((len = in.read(buf)) > 0){
	        out.write(buf, 0, len);
	      }
	      in.close();
	      out.close();
	    }
	    catch(FileNotFoundException ex){
	      System.out.println(ex.getMessage() + " in the specified directory.");
	      System.exit(0);
	    }
	    catch(IOException e){
	      System.out.println(e.getMessage());      
	    }
	  }
	
	/**
	 * @Method_name	: moveFile
	 * @author 		: Bertho Rafitya Iwasurya
	 * @since		: Nov 1, 2013 4:30:52 PM
	 * @Description : pindahkan file ke tempat lain (belum dicoba untuk folder)
	 * @param srFile
	 * @param dtFile
	 * @return_type : void
	 * @Revision	:
	 * #====#===========#===================#===========================#
	 * | ID	|    Date	|	    User		|			Description		|
	 * #====#===========#===================#===========================#
	 * |	|			|					|							|
	 * #====#===========#===================#===========================#
	 */
	public static void moveFile(String srFile, String dtFile){
		copyfile(srFile, dtFile);
		deleteFile(new File(srFile));
	}
	
	/**
	 * @Method_name	: getFileName
	 * @author 		: Bertho Rafitya Iwasurya
	 * @since		: Nov 1, 2013 4:00:00 PM
	 * @Description : modul untuk ambil nama filenya aja tanpa extension 
	 * @param file
	 * @return
	 * @return_type : String
	 * @Revision	:
	 * #====#===========#===================#===========================#
	 * | ID	|    Date	|	    User		|			Description		|
	 * #====#===========#===================#===========================#
	 * |	|			|					|							|
	 * #====#===========#===================#===========================#
	 */
	public static String getFileName(File file){
		if(!file.isFile())return "";
		String filename=file.getName();
		String[]splitName=filename.split("\\.");
		
		return filename.replace("."+splitName[splitName.length-1],"");
	}
	
	/**
	 * @Method_name	: getFileType
	 * @author 		: Bertho Rafitya Iwasurya
	 * @since		: Nov 1, 2013 3:58:19 PM
	 * @Description : ambil filetype / extension
	 * @param file
	 * @return
	 * @return_type : String
	 * @Revision	:
	 * #====#===========#===================#===========================#
	 * | ID	|    Date	|	    User		|			Description		|
	 * #====#===========#===================#===========================#
	 * |	|			|					|							|
	 * #====#===========#===================#===========================#
	 */
	public static String getFileType(File file){
		if(!file.isFile())return "";
		String filename=file.getName();
		String[]splitName=filename.split("\\.");
		
		return splitName[splitName.length-1];
	}
	
	
	public static void writeToFile(String pathName,String fileName,List<String> lsText){
		
        try {
        	File path=new File(pathName);
        	if(!path.exists()){
        		path.mkdirs();
        	}
			 File file=new File(pathName+"/"+fileName);
			  BufferedWriter output = new BufferedWriter(new FileWriter(file));
			  for(String text:lsText){
				  output.write(text);
				  output.newLine();
			  }
			  output.close();
        } catch ( IOException e ) {
           e.printStackTrace();
        }
	}
	
	public static void copyDirectory(File sourceLocation , File targetLocation) throws IOException {
	   
		if (sourceLocation.isDirectory()) {
	        if (!targetLocation.exists()) {
	            targetLocation.mkdir();
	        }

	        String[] children = sourceLocation.list();
	        for (int i=0; i<children.length; i++) {
	            copyDirectory(new File(sourceLocation, children[i]),
	                    new File(targetLocation, children[i]));
	        }
	    } else {

	        InputStream in = new FileInputStream(sourceLocation);
	        OutputStream out = new FileOutputStream(targetLocation);

	        // Copy the bits from instream to outstream
	        byte[] buf = new byte[1024];
	        int len;
	        while ((len = in.read(buf)) > 0) {
	            out.write(buf, 0, len);
	        }
	        in.close();
	        out.close();
	    }
	}

	public static void main (String [] arg){
		List<String> lsText=new ArrayList<String>();
		lsText.add("public String apa");
		lsText.add("public String ajaa");
		writeToFile(System.getProperty("user.dir")+"/springCreator/com/bca/model", "test.java", lsText);
	}

}
