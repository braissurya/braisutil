package com.brais.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 * @author 		: Bertho Rafitya Iwasurya
 * @since		: Oct 30, 2013 8:55:59 AM
 * @Description : modul untuk Zip unzip file menggunakan library Zip4j
 * @Revision	:
 * #====#===========#===================#===========================#
 * | ID	|    Date	|	    User		|			Description		|
 * #====#===========#===================#===========================#
 * |	|			|					|							|
 * #====#===========#===================#===========================#
 */
public class ZippingUtils {
	
	public static void main(String [] args) throws ZipException{
//		zipFolder("C:\\1st\\TUTORIAL\\jms_tutorial", "C:\\1st\\TUTORIAL\\hasilzip_jms_tutorial.zip", "123");
//		extractAllFileToPath("C:\\1st\\TUTORIAL\\hasilzip_jms_tutorial.zip", "C:\\1st\\TUTORIAL\\hasilextract_jms_tutorial", "123");
		String [] sp="CAB/13/05/063456|0034|02-MAY-13|1|0|A|8|0|4140000000|0|0|0|0|0|0|8|0|4653870000|1|0|15000000000|0|0|3|0|3950000|0|0|0|0||U874660|02-MAY-13|U874660|02-MAY-13|U973181|02-MAY-13||U874660|1|0|19|0|3932430000|0|0|0|0|0||||||||||||||1".split("\\|");
		System.out.println(sp.toString());
	}

	
	/**
	 * @Method_name	: extractAllFile
	 * @author 		: Bertho Rafitya Iwasurya
	 * @since		: Oct 30, 2013 9:11:58 AM
	 * @Description : modul untuk extract semua file yg ada di zip file 
	 * @param pathZip : path file zip berada
	 * @param pathResultExtract : path hasil dari zip
	 * @param zipPassword : bila ada password silahkan isi password 
	 * @throws ZipException
	 * @return_type : void
	 * @Revision	:
	 * #====#===========#===================#===========================#
	 * | ID	|    Date	|	    User		|			Description		|
	 * #====#===========#===================#===========================#
	 * |	|			|					|							|
	 * #====#===========#===================#===========================#
	 */
	public static void extractAllFileToPath(String pathZip,String pathResultExtract,String zipPassword) throws ZipException{
		// Initiate ZipFile object with the path/name of the zip file.
		ZipFile zipFile = new ZipFile(pathZip);
		
		// Check to see if the zip file is password protected 
		if (zipFile.isEncrypted()) {
			// if yes, then set the password for the zip file
			zipFile.setPassword(zipPassword);
		}
		
		// Extracts all files to the path specified
		zipFile.extractAll(pathResultExtract);
	}
	
	/**
	 * @Method_name	: zipFiles
	 * @author 		: Bertho Rafitya Iwasurya
	 * @since		: Oct 30, 2013 10:08:10 AM
	 * @Description : modul untuk zip file 
	 * @param filesToZip : List file yang mau di zip
	 * @param pathResultZip : hasil zip mau ditaruh dimana ?
	 * @param zipPassword : apakah mau dikasi password? apa passwordnya? klo tidak mau isi null aja
	 * @throws ZipException
	 * @return_type : void
	 * @Revision	:
	 * #====#===========#===================#===========================#
	 * | ID	|    Date	|	    User		|			Description		|
	 * #====#===========#===================#===========================#
	 * |	|			|					|							|
	 * #====#===========#===================#===========================#
	 */
	public static void  zipFiles(List<File> filesToZip, String pathResultZip, String zipPassword) throws ZipException {
		
		 //Initiate ZipFile object with the path/name of the zip file.
			ZipFile zipFile = new ZipFile(pathResultZip);
			
			// Build the list of files to be added in the array list
			// Objects of type File have to be added to the ArrayList
			ArrayList filesToAdd = new ArrayList();
			for(File f:filesToZip){
				filesToAdd.add(f);
			}
			// Initiate Zip Parameters which define various properties such
			// as compression method, etc. More parameters are explained in other
			// examples
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); // set compression method to deflate compression
			
			// Set the compression level. This value has to be in between 0 to 9
			// Several predefined compression levels are available
			// DEFLATE_LEVEL_FASTEST - Lowest compression level but higher speed of compression
			// DEFLATE_LEVEL_FAST - Low compression level but higher speed of compression
			// DEFLATE_LEVEL_NORMAL - Optimal balance between compression level/speed
			// DEFLATE_LEVEL_MAXIMUM - High compression level with a compromise of speed
			// DEFLATE_LEVEL_ULTRA - Highest compression level but low speed
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); 
			
			if(zipPassword!=null)addPasswordAes(parameters, zipPassword);
			
			// Now add files to the zip file
			// Note: To add a single file, the method addFile can be used
			// Note: If the zip file already exists and if this zip file is a split file
			// then this method throws an exception as Zip Format Specification does not 
			// allow updating split zip files
			zipFile.addFiles(filesToAdd, parameters);
	
	}
	
	/**
	 * @Method_name	: zipFolder
	 * @author 		: Bertho Rafitya Iwasurya
	 * @since		: Oct 30, 2013 10:12:59 AM
	 * @Description : modul untuk zip 1 folder
	 * @param pathFolder 
	 * @param pathResultZip
	 * @param zipPassword
	 * @throws ZipException
	 * @return_type : void
	 * @Revision	:
	 * #====#===========#===================#===========================#
	 * | ID	|    Date	|	    User		|			Description		|
	 * #====#===========#===================#===========================#
	 * |	|			|					|							|
	 * #====#===========#===================#===========================#
	 */
	public static void  zipFolder(String pathFolder, String pathResultZip, String zipPassword) throws ZipException {
		// Initiate ZipFile object with the path/name of the zip file.
		ZipFile zipFile = new ZipFile(pathResultZip);
		
		// Folder to add
		String folderToAdd = pathFolder;
		
		// Initiate Zip Parameters which define various properties such
		// as compression method, etc.
		ZipParameters parameters = new ZipParameters();
		
		// set compression method to store compression
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		
		// Set the compression level
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
		
		if(zipPassword!=null)addPasswordAes(parameters, zipPassword);
		
		// Add folder to the zip file
		zipFile.addFolder(folderToAdd, parameters);
	}
	
	
	public static void addPasswordAes(ZipParameters parameters,String zipPassword){
		// Set the encryption flag to true
		// If this is set to false, then the rest of encryption properties are ignored
		parameters.setEncryptFiles(true);
		
		// Set the encryption method to AES Zip Encryption
		parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
		
		// Set AES Key strength. Key strengths available for AES encryption are:
		// AES_STRENGTH_128 - For both encryption and decryption
		// AES_STRENGTH_192 - For decryption only
		// AES_STRENGTH_256 - For both encryption and decryption
		// Key strength 192 cannot be used for encryption. But if a zip file already has a
		// file encrypted with key strength of 192, then Zip4j can decrypt this file
		parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
		
		// Set password
		parameters.setPassword(zipPassword);
	}
	
	
}
