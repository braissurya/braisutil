package com.brais.utils;



import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DataBindingException;

import org.apache.velocity.VelocityContext;

import com.brais.db.DBPooling;
import com.brais.model.InfoColumns;


/**
 * 
 * @author 		: Bertho Rafitya Iwasurya
 * @since		: Feb 20, 2013 2:44:10 PM
 * @Description : aplikasi kecil bantu bikin 
 * 				  script insert, update & model
 * 				  dari table
 * @Revision	:
 * #====#=========1==#===================#===========================#
 * | ID	|    Date	|	    User		|			Description		|
 * #====#===========#===================#===========================#
 * |	|			|					|							|
 * #====#===========#===================#===========================#
 */
public class BraisSpringMVCHelp {

	public static final String path = System.getProperty("user.dir");
	
	public static String url;
	public static String driver;
	public static String user;
	public static String pass;
	
	public static String appName;
	public static String packageParent;
	public static String method;
	public static String validation;
	
	public static void main(String[] args) {
		
	
		boolean exit=false;
		
		do{
			BufferedReader br=null;
			try {
				clearScreen();
				System.out.println("Silahkan pilih Database Table : \n" +
						"1 --> MySql \n" +
						"2 --> Oracle \n" +
						"exit --> keluar");
				
				 br = new BufferedReader(new InputStreamReader(System.in));
				
				String modul=br.readLine();
				
				if(modul.equals("1")){
					System.out.print("Masukkan hostname database :");
					br = new BufferedReader(new InputStreamReader(System.in));     
					String  hostname= br.readLine();
					System.out.print("Masukkan port database :");
					br = new BufferedReader(new InputStreamReader(System.in));     
					String  port= br.readLine();
					System.out.print("Masukkan skema database :");
					br = new BufferedReader(new InputStreamReader(System.in));     
					String  skema= br.readLine();
					System.out.print("Masukkan user database :");
					br = new BufferedReader(new InputStreamReader(System.in));     
					  user= br.readLine();
					
					System.out.print("Masukkan password database :");
					br = new BufferedReader(new InputStreamReader(System.in));     
					  pass= br.readLine();
					
					 driver="com.mysql.jdbc.Driver";
					 url="jdbc:mysql://"+hostname+":"+port+"/"+skema+"?autoReconnect=true";
					
					DBPooling db=new DBPooling(driver, url, user, pass);
					
					boolean back=false;
					do{
						
						System.out.print("....Koneksi Database Berhasil\n\n");
						System.out.println("Silahkan modul yang ingin dijalankan : \n" +
								"0 --> Create Model, Insert, Update \n" +
								"1 --> Create Model \n" +
								"2 --> Create Insert Script \n" +
								"3 --> Create Update Script \n" +
								"4 --> Export ALL Table Schema To Spring \n" +
								"5 --> Test Sysdate \n" +
								".. --> back to select Database");
						br = new BufferedReader(new InputStreamReader(System.in));     
						String  pilih= br.readLine();
						
						if(pilih.equals("0")){
							createAllMySql(db,skema);
						}else if(pilih.equals("1")){
							createModelMySql(db,skema);
						}else if(pilih.equals("2")){
							insertScriptMySql(db, skema);
						}else if(pilih.equals("3")){
							updateScriptMySql(db, skema);
						}else if(pilih.equals("4")){
							exportToSpring(db,skema);
						}else if(pilih.equals("5")){
							System.out.println("Sekarang tanggal :");
							db.query("select sysdate()");
						}else {
							System.out.print("balik ke pilih db ya");
							back=true;
						}
					}while(!back);
					db.shutdown();
					
					
					System.out.print("\n\n===enter to continue===");
					br = new BufferedReader(new InputStreamReader(System.in));   
					
					
				}else if(modul.equals("2")){
					System.out.println("hehehe :) Maaf belum diimplementasikan =====>  Wani piro");
					
				}else if(modul.toUpperCase().equals("EXIT")){
					exit=true;
					System.out.println("Wah bentar amat, baru sebentar dah keluar aja. xixixixixixi\n" +
							"C U"); 
					
				}else{
					System.out.println("eh jangan asal teken donk!! ");
				}
				System.out.print("\n\n===enter to continue===");
				br = new BufferedReader(new InputStreamReader(System.in));   
				
			} catch (DataBindingException e) {				
				
				e.printStackTrace();
				System.err.print("Ada error database!!");
				System.out.print("\n\n===enter to continue===");
				br = new BufferedReader(new InputStreamReader(System.in));   
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
				System.err.print("Ada error !!");
				System.out.print("\n\n===enter to continue===");
				br = new BufferedReader(new InputStreamReader(System.in));   
				
			}
		}while(!exit);
		
		

	}
	
	public static void clearScreen(){
		for (int i = 0; i < 100; i++) {
			System.out.println("\n");
		}
		
	}
	
	public  static void exportToSpring(DBPooling db,String skema) throws Exception{

		boolean exit=false;
		do{
			
			System.out.print("....Anda memilih modul Export To Spring\n\n");
			
			System.out.print("Masukkan nama Aplikasi :");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));     
			 appName= br.readLine();
			
			 String folderCreator=path+"/springCreator/"+appName+"_"+DateUtil.convertDateToString("ddMMyyyyHHmmss", new Date());
				 
			System.out.print("Masukkan nama parent package (ex:com.brais) :");
			 br = new BufferedReader(new InputStreamReader(System.in));     
			 packageParent= br.readLine();
			
			String folderSource=folderCreator+"/JavaSource";
			String folderContent=folderCreator+"/WebContent";
			
			String [] packageSplit=packageParent.split("\\.");
			for(String pack:packageSplit){
				folderSource+="/"+pack;
			}
			
			System.out.println("\nMau pakai method apa?  public / private  ");
			 br = new BufferedReader(new InputStreamReader(System.in));     
			 method= br.readLine();
			 
			 System.out.println("" +
					"\nApakah mau pakai validation SPRING 3? yes/no");
			 br = new BufferedReader(new InputStreamReader(System.in));     
			 validation= br.readLine().toUpperCase();
			
			List<Map> lstTable=db.querySelect("select distinct TABLE_NAME from `INFORMATION_SCHEMA`.`COLUMNS` where `TABLE_SCHEMA`='"+skema+"' group by table_name");
			
			if(!lstTable.isEmpty()){
				//baseObject
				FileUtils.writeToFile(folderSource+"/model","BaseObject.java", exportBaseObject());
				
				FileUtils.writeToFile(folderSource+"/model","Role.java", exportModelRole());
				
				//security
				FileUtils.writeToFile(folderSource+"/services","LoginListener.java", exportLoginListener());
				FileUtils.writeToFile(folderSource+"/services","ModifiedUserAuthenticationProvider.java", exportModifiedUserAuthenticationProvider());
				
				//ParentController
				FileUtils.writeToFile(folderSource+"/web/controller","ParentController.java", exportParentController());
				
				//database
				FileUtils.writeToFile(folderCreator+"/JavaSource"+"/META-INF/spring","database.properties", exportDB());
				
				//application Context
				FileUtils.writeToFile(folderCreator+"/JavaSource"+"/META-INF/spring","applicationContext.xml", exportAppContext());
				
				//web MVC
				FileUtils.writeToFile(folderContent+"/WEB-INF/spring","webmvc-config.xml", exportWebMvc());
			
				//web.xml
				FileUtils.writeToFile(folderContent+"/WEB-INF","web.xml", exportWebXml());
				
				//copy lib
				FileUtils.copyDirectory(new File(path+"/JavaSource/templates/default/lib"), new File(folderContent+"/WEB-INF/lib"));
				
				//copy tags
				FileUtils.copyDirectory(new File(path+"/JavaSource/templates/default/tags"), new File(folderContent+"/WEB-INF/tags"));
				
				//copy views
				FileUtils.copyDirectory(new File(path+"/JavaSource/templates/default/views"), new File(folderContent+"/WEB-INF/views"));
								
				//copy images
				FileUtils.copyDirectory(new File(path+"/JavaSource/templates/default/images"), new File(folderContent+"/images"));

				//copy styles
				FileUtils.copyDirectory(new File(path+"/JavaSource/templates/default/styles"), new File(folderContent+"/styles"));
				
				FileUtils.copyfile(path+"/JavaSource/templates/default/alt.properties", folderCreator+"/JavaSource/alt.properties");
				FileUtils.copyfile(path+"/JavaSource/templates/default/log4j.properties", folderCreator+"/JavaSource/log4j.properties");
				FileUtils.copyfile(path+"/JavaSource/templates/default/standard.properties", folderCreator+"/JavaSource/standard.properties");
				FileUtils.copyfile(path+"/JavaSource/templates/default/wide.properties", folderCreator+"/JavaSource/wide.properties");
				
				//copy security
				FileUtils.copyfile(path+"/JavaSource/templates/default/spring/applicationContext-security.xml", folderCreator+"/JavaSource/META-INF/spring/applicationContext-security.xml");
				
				Map<String,List<InfoColumns>> tableList = new HashMap<String, List<InfoColumns>>();
				for(Map mapTable: lstTable){
					String tableName=(String) mapTable.get("TABLE_NAME");
					//generate model
					List<Map> hasil=db.querySelect("SELECT * "+
							   "	FROM `INFORMATION_SCHEMA`.`COLUMNS` "+
							   "	WHERE `TABLE_SCHEMA`='"+skema+"' "+
							   "    AND `TABLE_NAME`='"+tableName+"'");
				
					if(!hasil.isEmpty()){
						      
						String modelName= StringUtil.modelNameConvension(tableName.replace("sys_", ""));
						List<InfoColumns> hasilnya=new ArrayList<InfoColumns>();
						for(Map map:hasil){
							InfoColumns info=new InfoColumns();
							info.namakolom=((String) map.get("COLUMN_NAME"))==null?"":((String) map.get("COLUMN_NAME"));
							info.dataTypekolom=((String) map.get("DATA_TYPE"))==null?"":((String) map.get("DATA_TYPE"));
							info.is_nullable=((String) map.get("IS_NULLABLE"))==null?"":((String) map.get("IS_NULLABLE"));
							info.numericscale=((BigInteger) map.get("NUMERIC_SCALE"))==null?0:((BigInteger) map.get("NUMERIC_SCALE")).intValue();
							info.charactermaximumlength=((BigInteger) map.get("CHARACTER_MAXIMUM_LENGTH"))==null?0:((BigInteger) map.get("CHARACTER_MAXIMUM_LENGTH")).intValue();
							info.namakey=((String) map.get("COLUMN_KEY"))==null?"":((String) map.get("COLUMN_KEY"));
							info.extra=((String) map.get("EXTRA"))==null?"":((String) map.get("EXTRA"));
							info.tableName=tableName;
							info.modelName=modelName;
							info.viewName=StringUtil.camelHumpAndTrim(tableName.replace("_", " ").replace("sys", ""));
							info.viewKolom=StringUtil.camelHumpAndTrim(info.namakolom.replace("_", " ").replace("sys", ""));
							info.modelKolom=info.namakolom.replace("sys_", "");
							
							info.syskolom=info.namakolom.startsWith("sys_");
							info.sysTable=info.tableName.startsWith("sys_");
							
							String dataTypekolom=info.dataTypekolom.toLowerCase();
							
							if(dataTypekolom.equals("int")||dataTypekolom.equals("tinyint")||dataTypekolom.equals("bigint")||dataTypekolom.equals("numeric")||dataTypekolom.equals("number")){
								dataTypekolom="Long";
							}else if(dataTypekolom.equals("blob")||dataTypekolom.equals("varchar")||dataTypekolom.equals("char")||dataTypekolom.equals("text")){
								dataTypekolom="String";
							}else if(dataTypekolom.equals("date")||dataTypekolom.equals("datetime")){
								dataTypekolom="Date";
							}else if(dataTypekolom.equals("longblob")){
								dataTypekolom="byte[]";
							}else if(dataTypekolom.equals("decimal")){
								if(info.numericscale==0)
									dataTypekolom="Long";
								else dataTypekolom="Double";
							}
							info.dataTypekolomJava=dataTypekolom;
							
							
							
							/*
							 * Supported JDBC Types :
							 * BIT		FLOAT	CHAR		TIMESTAMP		OTHER	UNDEFINED
							 * TINYINT	REAL	VARCHAR		BINARY			BLOG	NVARCHAR
							 * SMALLINT	DOUBLE	LONGVARCHAR	VARBINARY		CLOB	NCHAR
							 * INTEGER	NUMERIC	DATE		LONGVARBINARY	BOOLEAN	NCLOB
							 * BIGINT	DECIMAL	TIME		NULL			CURSOR	ARRAY
							 */
							dataTypekolom=info.dataTypekolom.toLowerCase();
							/*if(dataTypekolom.equals("datetime")){
								dataTypekolom="TIMESTAMP";
							}else if(dataTypekolom.equals("number")){
								dataTypekolom="NUMERIC";
							}else if(dataTypekolom.equals("int")){
								dataTypekolom="INTEGER";
							}else if(dataTypekolom.equals("text")){
								dataTypekolom="LONGVARCHAR";
							}else if(dataTypekolom.equals("longblob")){
								dataTypekolom="BLOB";
							}else {
								dataTypekolom=info.dataTypekolom.toUpperCase();
							}
							info.dataTypekolomJDBC=dataTypekolom;*/
							
							hasilnya.add(info);							
						}
						
						tableList.put(modelName, hasilnya);	
						
						//create model
						FileUtils.writeToFile(folderSource+"/model", modelName+".java", exportModelSpring( tableName, modelName, hasilnya));
						
						//create myBatis mapper
						FileUtils.writeToFile(folderSource+"/persistence", modelName+"Mapper.java", exportMapperMyBatis( tableName, modelName, hasilnya));
						
						//create mybatis SQL
						FileUtils.writeToFile(folderSource+"/persistence/sql", modelName+"Mapper.xml", exportSqlMyBatis( tableName, modelName, hasilnya));

						//create spring services
						FileUtils.writeToFile(folderSource+"/services", modelName+"Manager.java", exportServiceMyBatis( tableName, modelName, hasilnya));
						
						//create spring controller
						FileUtils.writeToFile(folderSource+"/web/controller", modelName+"Controller.java", exportControllerSpring( tableName, modelName, hasilnya));
						
						//Validator
						FileUtils.writeToFile(folderSource+"/web/validator", modelName+"Validator.java", exportWebValidator( tableName, modelName, hasilnya));
						
						//View
						FileUtils.writeToFile(folderContent+"/WEB-INF/views/"+modelName.toLowerCase(), "views.xml", exportViewXml(tableName, modelName, hasilnya));
						FileUtils.writeToFile(folderContent+"/WEB-INF/views/"+modelName.toLowerCase(), "update.jspx", exportViewUpdate( tableName, modelName, hasilnya));
						FileUtils.writeToFile(folderContent+"/WEB-INF/views/"+modelName.toLowerCase(), "show.jspx", exportViewShow( tableName, modelName, hasilnya));
						FileUtils.writeToFile(folderContent+"/WEB-INF/views/"+modelName.toLowerCase(), "list.jspx", exportViewList( tableName, modelName, hasilnya));
						FileUtils.writeToFile(folderContent+"/WEB-INF/views/"+modelName.toLowerCase(), "create.jspx", exportViewCreate( tableName, modelName, hasilnya));
						
						
						
						
					}
				}
				
				//Properties
				FileUtils.writeToFile(folderContent+"/WEB-INF/i18n", "application.properties", exportAppProperties(tableList));
				FileUtils.writeToFile(folderContent+"/WEB-INF/i18n", "messages.properties", exportMessageProperties());
				
				FileUtils.writeToFile(folderContent+"/WEB-INF/views", "menu.jspx", exportViewMenu(tableList));
				//Layouts
				FileUtils.writeToFile(folderContent+"/WEB-INF/layouts", "default.jspx", exportDefaultLayout());
				FileUtils.writeToFile(folderContent+"/WEB-INF/layouts", "layouts.xml", exportXmlLayout());
				
				System.out.println("\n\n===Export ALL Table Schema To Spring BERHASIL DI JALANKAN===");
			}else{
				System.err.println("\n\n===Table Tidak ditemukan===");
				br = new BufferedReader(new InputStreamReader(System.in));   
				
			}
		}while(!exit);
	
	}
	
	public static List<String> exportWebXml(){
		List<String> lsModel=new ArrayList<String>();
		VelocityContext context = new VelocityContext();
		
		context.put("appName", StringUtil.camelHumpAndTrim(appName));
        
        
        lsModel.add(StringUtil.velocityTemplate("templates/web.vm", context));
        
        return lsModel;
	}
	
	public static List<String> exportViewMenu(Map<String,List<InfoColumns>> tableList){
		List<String> lsModel=new ArrayList<String>();
		VelocityContext context = new VelocityContext();
		
		context.put("tableList", tableList);
        
        
        lsModel.add(StringUtil.velocityTemplate("templates/views/menu.vm", context));
        
        return lsModel;
	}
	
	public static List<String> exportDB(){
		List<String> lsModel=new ArrayList<String>();
		VelocityContext context = new VelocityContext();
		
		context.put("url", url);
        context.put("driver", driver);
        context.put("username", user);
        context.put("password", pass);
        
        lsModel.add(StringUtil.velocityTemplate("templates/spring/database.vm", context));
        
        return lsModel;
	}
	
	public static List<String> exportViewUpdate(String tableName,String modelName,List<InfoColumns> hasil){
	
		List<String> lsModel=new ArrayList<String>();
		VelocityContext context = new VelocityContext();		
		
		String packageParentDash=packageParent.replace(".", "_");
		List<String> tableList=new ArrayList<String>();
		for(InfoColumns info:hasil){
			String namakolom=info.modelKolom;
			String dataTypekolom=info.dataTypekolomJava;
			String is_nullable=info.is_nullable!=null?info.is_nullable.toLowerCase():null;
			
			
			if(namakolom.equals("id")){
				tableList.add("<field:input field=\""+namakolom+"\" label=\""+info.viewKolom+"\" id=\"c_"+packageParentDash+"_model_"+modelName.toLowerCase()+"_"+namakolom+"\" readonly=\"true\" validationMessageCode=\"field_invalid_number\" />");
				
			}else if(info.syskolom){
				tableList.add("<field:input field=\""+namakolom+"\" label=\""+info.viewKolom+"\" id=\"c_"+packageParentDash+"_model_"+modelName.toLowerCase()+"_"+namakolom+"\" readonly=\"true\" validationMessageCode=\"field_invalid_number\" />");
				
			}else{
				if(dataTypekolom.equals("Date")){
					tableList.add("<field:datetime field=\""+namakolom+"\" label=\""+info.viewKolom+"\" id=\"c_"+packageParentDash+"_model_"+modelName.toLowerCase()+"_"+namakolom+"\" "+(is_nullable.equals("no")?"required=\"true\"":"")+" dateTimePattern=\"${"+modelName.toLowerCase()+"_"+namakolom+"_date_format}\" />");
				}else if(dataTypekolom.equals("Long")){
					tableList.add("<field:input field=\""+namakolom+"\" label=\""+info.viewKolom+"\" id=\"c_"+packageParentDash+"_model_"+modelName.toLowerCase()+"_"+namakolom+"\" "+(is_nullable.equals("no")?"required=\"true\"":"")+" validationMessageCode=\"field_invalid_integer\" />");
				}else if(dataTypekolom.equals("Double")){
					tableList.add("<field:input field=\""+namakolom+"\" label=\""+info.viewKolom+"\" id=\"c_"+packageParentDash+"_model_"+modelName.toLowerCase()+"_"+namakolom+"\" "+(is_nullable.equals("no")?"required=\"true\"":"")+" validationMessageCode=\"field_invalid_number\" />");
				}else{
					tableList.add("<field:input field=\""+namakolom+"\" label=\""+info.viewKolom+"\" id=\"c_"+packageParentDash+"_model_"+modelName.toLowerCase()+"_"+namakolom+"\" "+(is_nullable.equals("no")?"required=\"true\"":"")+" />");
				}
			}
		}
		
		context.put("tableList", tableList);
		context.put("packageParentDash",packageParentDash );
		context.put("modelName", modelName);
		 
		lsModel.add(StringUtil.velocityTemplate("templates/views/update.vm", context));
		 
		return lsModel;
	}
	
	public static List<String> exportViewCreate(String tableName,String modelName,List<InfoColumns> hasil){
		
		List<String> lsModel=new ArrayList<String>();
		VelocityContext context = new VelocityContext();		
		
		String packageParentDash=packageParent.replace(".", "_");
		List<String> tableList=new ArrayList<String>();
		for(InfoColumns info:hasil){
			String namakolom=info.modelKolom;
			String dataTypekolom=info.dataTypekolomJava;
			String is_nullable=info.is_nullable!=null?info.is_nullable.toLowerCase():null;
			Integer charactermaximumlength=info.charactermaximumlength;
			
			
			if(namakolom.equals("id")){
				tableList.add("<field:input field=\""+namakolom+"\" label=\""+info.viewKolom+"\" id=\"c_"+packageParentDash+"_model_"+modelName.toLowerCase()+"_"+namakolom+"\" readonly=\"true\" validationMessageCode=\"field_invalid_number\" />");
			}else if(info.syskolom){
				tableList.add("<field:input field=\""+namakolom+"\" label=\""+info.viewKolom+"\" id=\"c_"+packageParentDash+"_model_"+modelName.toLowerCase()+"_"+namakolom+"\" readonly=\"true\" validationMessageCode=\"field_invalid_number\" />");
			}else{
				if(dataTypekolom.equals("Date")){
					tableList.add("<field:datetime field=\""+namakolom+"\" label=\""+info.viewKolom+"\" id=\"c_"+packageParentDash+"_model_"+modelName.toLowerCase()+"_"+namakolom+"\" "+(is_nullable.equals("no")?"required=\"true\"":"")+" dateTimePattern=\"${"+modelName.toLowerCase()+"_"+namakolom+"_date_format}\" />");
				}else if(dataTypekolom.equals("Long")){
					tableList.add("<field:input field=\""+namakolom+"\" label=\""+info.viewKolom+"\" id=\"c_"+packageParentDash+"_model_"+modelName.toLowerCase()+"_"+namakolom+"\" "+(is_nullable.equals("no")?"required=\"true\"":"")+" validationMessageCode=\"field_invalid_integer\" />");
				}else if(dataTypekolom.equals("Double")){
					tableList.add("<field:input field=\""+namakolom+"\" label=\""+info.viewKolom+"\" id=\"c_"+packageParentDash+"_model_"+modelName.toLowerCase()+"_"+namakolom+"\" "+(is_nullable.equals("no")?"required=\"true\"":"")+" validationMessageCode=\"field_invalid_number\" />");
				}else{
					tableList.add("<field:input field=\""+namakolom+"\" label=\""+info.viewKolom+"\" id=\"c_"+packageParentDash+"_model_"+modelName.toLowerCase()+"_"+namakolom+"\" "+(is_nullable.equals("no")?"required=\"true\"":"")+" "+(charactermaximumlength==null?"":"max=\""+charactermaximumlength+"\"")+"/>");
				}
			}
		}
		
		context.put("tableList", tableList);
		context.put("packageParentDash",packageParentDash );
		context.put("modelName", modelName);
		 
		lsModel.add(StringUtil.velocityTemplate("templates/views/create.vm", context));
		 
		return lsModel;
	}
	
	public static List<String> exportViewShow(String tableName,String modelName,List<InfoColumns> hasil){
		
		List<String> lsModel=new ArrayList<String>();
		VelocityContext context = new VelocityContext();		
		
		String packageParentDash=packageParent.replace(".", "_");
		List<String> tableList=new ArrayList<String>();
		for(InfoColumns info:hasil){
			String namakolom=info.modelKolom;
			String dataTypekolom=info.dataTypekolomJava;
			
			
			if(dataTypekolom.equals("Date")){
				tableList.add("<field:display field=\""+namakolom+"\" label=\""+info.viewKolom+"\" id=\"c_"+packageParentDash+"_model_"+modelName.toLowerCase()+"_"+namakolom+"\"  object=\"${"+modelName.toLowerCase()+"}\"  date=\"true\" dateTimePattern=\"${"+modelName.toLowerCase()+"_"+namakolom+"_date_format}\"/>");
			}else{
				tableList.add("<field:display field=\""+namakolom+"\" label=\""+info.viewKolom+"\" id=\"c_"+packageParentDash+"_model_"+modelName.toLowerCase()+"_"+namakolom+"\"  object=\"${"+modelName.toLowerCase()+"}\" />");
			}
		}
		
		context.put("tableList", tableList);
		context.put("packageParentDash",packageParentDash );
		context.put("modelName", modelName);
		 
		lsModel.add(StringUtil.velocityTemplate("templates/views/show.vm", context));
		 
		return lsModel;
	}
	
	public static List<String> exportViewList(String tableName,String modelName,List<InfoColumns> hasil){
		
		List<String> lsModel=new ArrayList<String>();
		VelocityContext context = new VelocityContext();		
		
		String packageParentDash=packageParent.replace(".", "_");
		List<String> tableList=new ArrayList<String>();
		for(InfoColumns info:hasil){
			String namakolom=info.modelKolom;
			String dataTypekolom=info.dataTypekolomJava;
			
			
			if(dataTypekolom.equals("String")){
				tableList.add("<table:column id=\"c_"+packageParentDash+"_model_"+modelName.toLowerCase()+"_"+namakolom+"\" property=\""+namakolom+"\" label=\""+info.viewKolom+"\" />");
			}
		}
		
		context.put("tableList", tableList);
		
		context.put("packageParentDash",packageParentDash );
		context.put("modelName", modelName);
		 
		lsModel.add(StringUtil.velocityTemplate("templates/views/list.vm", context));
		 
		return lsModel;
	}
	 
    
    
	public static List<String> exportViewXml(String tableName,String modelName,List<InfoColumns> hasil){
		List<String> lsModel=new ArrayList<String>();
		VelocityContext context = new VelocityContext();
		
		context.put("modelName", modelName);
        
        
        lsModel.add(StringUtil.velocityTemplate("templates/views/views.vm", context));
        
        return lsModel;
	}
	
	
	public static List<String> exportWebMvc(){
		List<String> lsModel=new ArrayList<String>();
		VelocityContext context = new VelocityContext();
		
		context.put("packageParent", packageParent);
        
        lsModel.add(StringUtil.velocityTemplate("templates/spring/webmvc-config.vm", context));
        
        return lsModel;
	}
	
	public static List<String> exportModelRole(){
		List<String> lsModel=new ArrayList<String>();
		VelocityContext context = new VelocityContext();
		
		context.put("packageParent", packageParent);
        
        lsModel.add(StringUtil.velocityTemplate("templates/model/Role.vm", context));
        
        return lsModel;
	}
	
	public static List<String> exportLoginListener(){
		List<String> lsModel=new ArrayList<String>();
		VelocityContext context = new VelocityContext();
		
		context.put("packageParent", packageParent);
        
        lsModel.add(StringUtil.velocityTemplate("templates/services/LoginListener.vm", context));
        
        return lsModel;
	}
	
	public static List<String> exportModifiedUserAuthenticationProvider(){
		List<String> lsModel=new ArrayList<String>();
		VelocityContext context = new VelocityContext();
		
		context.put("packageParent", packageParent);
        
        lsModel.add(StringUtil.velocityTemplate("templates/services/ModifiedUserAuthenticationProvider.vm", context));
        
        return lsModel;
	}
	
	public static List<String> exportWebValidator(String tableName,String modelName,List<InfoColumns> hasil){
		List<String> lsModel=new ArrayList<String>();
		VelocityContext context = new VelocityContext();
		
		context.put("packageParent", packageParent);
		context.put("modelName", modelName);
        
        lsModel.add(StringUtil.velocityTemplate("templates/web/Validator.vm", context));
        
        return lsModel;
	}
	
	public static List<String> exportAppContext(){
		List<String> lsModel=new ArrayList<String>();
		VelocityContext context = new VelocityContext();
		
		context.put("username", user);
		context.put("password", pass);
		context.put("url", url);
		context.put("driver", driver);
		context.put("packageParent", packageParent);
		context.put("packageParentSlash", packageParent.replace(".", "/"));
        
        lsModel.add(StringUtil.velocityTemplate("templates/spring/applicationContext.vm", context));
        
        return lsModel;
	}
	
	public static List<String> exportDefaultLayout(){
		List<String> lsModel=new ArrayList<String>();
		VelocityContext context = new VelocityContext();		
        
        lsModel.add(StringUtil.velocityTemplate("templates/layouts/default.vm", context));        
        return lsModel;
	}
	
	public static List<String> exportXmlLayout(){
		List<String> lsModel=new ArrayList<String>();
		VelocityContext context = new VelocityContext();		
        
        lsModel.add(StringUtil.velocityTemplate("templates/layouts/layouts.vm", context));        
        return lsModel;
	}
	
	public static List<String> exportAppProperties(Map<String,List<InfoColumns>> tableList){
		List<String> lsModel=new ArrayList<String>();
		VelocityContext context = new VelocityContext();
		
		  
        context.put("app_name", appName);
        context.put("tableList", tableList);
        context.put("packageParent", packageParent.replace(".", "_"));
        
        lsModel.add(StringUtil.velocityTemplate("templates/prop/application.vm", context));
        
        return lsModel;
	}
	
	public static List<String> exportMessageProperties(){
		List<String> lsModel=new ArrayList<String>();
		VelocityContext context = new VelocityContext();		
        
        lsModel.add(StringUtil.velocityTemplate("templates/prop/messages.vm", context));        
        return lsModel;
	}
	
	public static List<String> exportControllerSpring(String tableName,String modelName,List<InfoColumns> hasil){
		List<InfoColumns> infoPrimary=listPrimary(hasil);
		boolean isAutoIncrement=isAutoIncrementID(infoPrimary);		
		
		String param="";
		String paramDoank="";
		String pathVariable="";
		String pathnya="";
		String pathJsp="";
		String encode="";
		
		if(infoPrimary.size()>1){
			int i=1;
			for(InfoColumns info:infoPrimary){
				String namakolom=info.modelKolom;
				
				if(i==1){
					param+=""+info.dataTypekolomJava+" "+namakolom;
					paramDoank+=namakolom;
					pathVariable+="@PathVariable(\""+namakolom+"\") "+info.dataTypekolomJava+" "+namakolom;	
					pathJsp+=""+namakolom+"";
					encode+="/\" + encodeUrlPathSegment("+modelName.toLowerCase()+".get"+StringUtil.camelHumpAndTrim(namakolom)+"().toString(), httpServletRequest)";
				}else{
					param+=", "+info.dataTypekolomJava+" "+namakolom;
					paramDoank+=", "+namakolom;
					pathVariable+=", @PathVariable(\""+namakolom+"\") "+info.dataTypekolomJava+" "+namakolom;
					pathJsp+="+\"/\"+"+namakolom+"";
					encode+="+\"/\" + encodeUrlPathSegment("+modelName.toLowerCase()+".get"+StringUtil.camelHumpAndTrim(namakolom)+"().toString(), httpServletRequest)";
				}
				pathnya+="/{"+namakolom+"}";
				
				i++;
			}
			
			
		}else if(infoPrimary.size()==1){
			InfoColumns info=infoPrimary.get(0);
			String namakolom=info.modelKolom;
			
			param+=""+info.dataTypekolomJava+" "+namakolom;
			paramDoank+=namakolom;
			pathVariable+="@PathVariable(\""+namakolom+"\") "+info.dataTypekolomJava+" "+namakolom;
			pathnya+="/{"+namakolom+"}";
			encode+="/\" + encodeUrlPathSegment("+modelName.toLowerCase()+".get"+StringUtil.camelHumpAndTrim(namakolom)+"().toString(), httpServletRequest)";
			pathJsp+=""+namakolom+"";
		}else{
			
			param+="?";
			paramDoank+="?";
			pathVariable+="@PathVariable(\"?\") ?";
			pathnya+="/{?}";
			encode+="/\" + encodeUrlPathSegment("+modelName.toLowerCase()+".get?().toString(), httpServletRequest)";
			pathJsp+="?";
		}

		List<String> lsModel=new ArrayList<String>();
		lsModel.add("package "+packageParent+".web.controller;");
		lsModel.add("");
		
		lsModel.add("import java.io.UnsupportedEncodingException;");
		lsModel.add("import java.util.Date;");
		lsModel.add("");
		lsModel.add("import javax.servlet.http.HttpServletRequest;");
		lsModel.add("import javax.validation.Valid;");
		lsModel.add("");
		lsModel.add("import org.apache.log4j.Logger;");
		lsModel.add("import org.joda.time.format.DateTimeFormat;");
		lsModel.add("import org.springframework.beans.factory.annotation.Autowired;");
		lsModel.add("import org.springframework.context.i18n.LocaleContextHolder;");
		lsModel.add("import org.springframework.stereotype.Controller;");
		lsModel.add("import org.springframework.ui.Model;");
		lsModel.add("import org.springframework.validation.BindingResult;");
		lsModel.add("import org.springframework.web.bind.WebDataBinder;");
		lsModel.add("import org.springframework.web.bind.annotation.InitBinder;");
		lsModel.add("import org.springframework.web.bind.annotation.PathVariable;");
		lsModel.add("import org.springframework.web.bind.annotation.RequestMapping;");
		lsModel.add("import org.springframework.web.bind.annotation.RequestMethod;");
		lsModel.add("import org.springframework.web.bind.annotation.RequestParam;");
		lsModel.add("import org.springframework.web.util.UriUtils;");
		lsModel.add("import org.springframework.web.util.WebUtils;");
		lsModel.add("");
		lsModel.add("import "+packageParent+".services."+modelName+"Manager;");
		lsModel.add("import "+packageParent+".web.controller.ParentController;");
		lsModel.add("import "+packageParent+".model."+modelName+";");
		lsModel.add("import "+packageParent+".web.validator."+modelName+"Validator;");
		lsModel.add("");
		
		lsModel.add("@RequestMapping(\"/master/"+modelName.toLowerCase()+"\")");
		lsModel.add("@Controller");
		lsModel.add("public class "+modelName+"Controller extends ParentController{");
		lsModel.add("");
		
		lsModel.add("\tprotected static Logger logger = Logger.getLogger("+modelName+"Controller.class);");
		lsModel.add("");
		
		lsModel.add("\t@Autowired");
		lsModel.add("\tprivate "+modelName+"Manager "+modelName.toLowerCase()+"Manager;");
		lsModel.add("");
		
		lsModel.add("\t@InitBinder");
		lsModel.add("\tpublic void initBinder(WebDataBinder binder) {");
		lsModel.add("\t\tbinder.setValidator(new "+modelName+"Validator());");
		lsModel.add("\t}");
		
		lsModel.add("\t@RequestMapping(method = RequestMethod.POST, produces = \"text/html\")");
		lsModel.add("\tpublic String create(@Valid "+modelName+" "+modelName.toLowerCase()+", BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {");
		lsModel.add("\t\tif (bindingResult.hasErrors()) {");
		lsModel.add("\t\t\tpopulateEditForm(uiModel, "+modelName.toLowerCase()+");");
		lsModel.add("\t\t\treturn \""+modelName.toLowerCase()+"/create\";");
		lsModel.add("\t\t}");
		lsModel.add("\t\tuiModel.asMap().clear();");
		if(modelName.equals("User")){
			lsModel.add("\t\t"+modelName.toLowerCase()+"Manager.saveUserLogin("+modelName.toLowerCase()+");");
		}else{
			lsModel.add("\t\t"+modelName.toLowerCase()+"Manager.save("+modelName.toLowerCase()+");");
		}
		
		lsModel.add("\t\treturn \"redirect:/master/"+modelName.toLowerCase()+encode+";");
		lsModel.add("\t}");
		lsModel.add("");
		
		lsModel.add("\t@RequestMapping(params = \"form\", produces = \"text/html\")");
		lsModel.add("\tpublic String createForm(Model uiModel) {");
		lsModel.add("\t\tpopulateEditForm(uiModel, new "+modelName+"());");
		lsModel.add("\t\treturn \""+modelName.toLowerCase()+"/create\";");
		lsModel.add("\t}");
		lsModel.add("");
		
		lsModel.add("\t@RequestMapping(value = \""+pathnya+"\", produces = \"text/html\")");
		lsModel.add("\tpublic String show("+pathVariable+", Model uiModel) {");
		lsModel.add("\t\taddDateTimeFormatPatterns(uiModel);");
		lsModel.add("\t\tuiModel.addAttribute(\""+modelName.toLowerCase()+"\", "+modelName.toLowerCase()+"Manager.get("+paramDoank+"));");
		lsModel.add("\t\tuiModel.addAttribute(\"itemId\", "+pathJsp+");");
		lsModel.add("\t\treturn \""+modelName.toLowerCase()+"/show\";");
		lsModel.add("\t}");
		lsModel.add("");
		
		lsModel.add("\t@RequestMapping(produces = \"text/html\")");
		lsModel.add("\tpublic String list(@RequestParam(value = \"page\", required = false) Integer page, @RequestParam(value = \"size\", required = false) Integer size,@RequestParam(value = \"search\", required = false) String search, @RequestParam(value = \"sortFieldName\", required = false) String sortFieldName, @RequestParam(value = \"sortOrder\", required = false) String sortOrder, Model uiModel) {");
		lsModel.add("\t\tif (page == null) {");		
		lsModel.add("\t\t\tpage=1;");
		lsModel.add("\t\t}");
		lsModel.add("");
		lsModel.add("\t\t\tint sizeNo = size == null ? 10 : size.intValue();");
		lsModel.add("\t\t\tfinal int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;");
		lsModel.add("\t\t\tuiModel.addAttribute(\""+modelName.toLowerCase()+"List\","+modelName.toLowerCase()+"Manager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );");
		lsModel.add("\t\t\tfloat nrOfPages = (float) "+modelName.toLowerCase()+"Manager.selectPagingCount(search) / sizeNo;");
		lsModel.add("\t\t\tuiModel.addAttribute(\"maxPages\", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));");
		
		lsModel.add("\t\taddDateTimeFormatPatterns(uiModel);");
		lsModel.add("\t\treturn \""+modelName.toLowerCase()+"/list\";");
		lsModel.add("\t}");
		lsModel.add("");	
		
		lsModel.add("\t@RequestMapping(method = RequestMethod.PUT, produces = \"text/html\")");
		lsModel.add("\tpublic String update(@Valid "+modelName+" "+modelName.toLowerCase()+", BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {");
		lsModel.add("\t\tif (bindingResult.hasErrors()) {");
		lsModel.add("\t\t\tpopulateEditForm(uiModel, "+modelName.toLowerCase()+");");
		lsModel.add("\t\t\treturn \""+modelName.toLowerCase()+"/update\";");
		lsModel.add("\t\t}");
		lsModel.add("\t\tuiModel.asMap().clear();");
		
		if(modelName.equals("User")){
			lsModel.add("\t\t"+modelName.toLowerCase()+"Manager.saveUserLogin("+modelName.toLowerCase()+");");
		}else{
			lsModel.add("\t\t"+modelName.toLowerCase()+"Manager.save("+modelName.toLowerCase()+");");
		}
		
		lsModel.add("\t\treturn \"redirect:/master/"+modelName.toLowerCase()+encode+";");
		lsModel.add("\t}");
		lsModel.add("");
			
		lsModel.add("\t@RequestMapping(value = \""+pathnya+"\", params = \"form\", produces = \"text/html\")");
		lsModel.add("\tpublic String updateForm("+pathVariable+", Model uiModel) {");
		lsModel.add("\t\tpopulateEditForm(uiModel, "+modelName.toLowerCase()+"Manager.get("+paramDoank+"));");
		lsModel.add("\t\treturn \""+modelName.toLowerCase()+"/update\";");
		lsModel.add("\t}");
		lsModel.add("");
			
		lsModel.add("\t@RequestMapping(value = \""+pathnya+"\", method = RequestMethod.DELETE, produces = \"text/html\")");
		lsModel.add("\tpublic String delete("+pathVariable+", @RequestParam(value = \"page\", required = false) Integer page, @RequestParam(value = \"size\", required = false) Integer size, Model uiModel) {");
		lsModel.add("\t\t"+modelName+" "+modelName.toLowerCase()+" = "+modelName.toLowerCase()+"Manager.get("+paramDoank+");");
		lsModel.add("\t\t"+modelName.toLowerCase()+"Manager.remove("+paramDoank+");");
		lsModel.add("\t\tuiModel.asMap().clear();");
		lsModel.add("\t\tuiModel.addAttribute(\"page\", (page == null) ? \"1\" : page.toString());");
		lsModel.add("\t\tuiModel.addAttribute(\"size\", (size == null) ? \"10\" : size.toString());");
		lsModel.add("\t\treturn \"redirect:/master/"+modelName.toLowerCase()+"\";");
		lsModel.add("\t}");
		
		lsModel.add("\tvoid addDateTimeFormatPatterns(Model uiModel) {");
		for(InfoColumns info:hasil){
			String namakolom=info.namakolom!=null?info.namakolom.toLowerCase():null;
			String dataTypekolom=info.dataTypekolom!=null?info.dataTypekolom.toLowerCase():null;
			
			if(dataTypekolom.equals("date"))
				lsModel.add("\t\tuiModel.addAttribute(\""+modelName.toLowerCase()+"_"+namakolom+"_date_format\", DateTimeFormat.patternForStyle(\"M-\", LocaleContextHolder.getLocale()));");
			else if(dataTypekolom.equals("datetime"))
				lsModel.add("\t\tuiModel.addAttribute(\""+modelName.toLowerCase()+"_"+namakolom+"_date_format\", DateTimeFormat.patternForStyle(\"MM\", LocaleContextHolder.getLocale()));");
		}
		
		lsModel.add("\t}");
		
		lsModel.add("\tvoid populateEditForm(Model uiModel, "+modelName+" "+modelName.toLowerCase()+") {");
		lsModel.add("\t\tuiModel.addAttribute(\""+modelName.toLowerCase()+"\", "+modelName.toLowerCase()+");");
		lsModel.add("\t\taddDateTimeFormatPatterns(uiModel);");
		lsModel.add("\t}");
		
		lsModel.add("}");
			
		return lsModel;
	}
	
	
	public static List<InfoColumns> listPrimary(List<InfoColumns> hasil){
		List<InfoColumns> infoPrimary=new ArrayList<InfoColumns>();
		for(InfoColumns info:hasil){
			String namakey=info.namakey.toLowerCase();
			
			if(namakey.equals("pri")){
				infoPrimary.add(info);
			}
			
		}
		return infoPrimary;
	}
	
	public static boolean isPrimaryID (List<InfoColumns> infoPrimary){
		if(infoPrimary.isEmpty()) return false;
	
		if(infoPrimary.size()==1){
			for(InfoColumns info:infoPrimary){
				if(info.getNamakolom().toLowerCase().equals("id")){
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static boolean isAutoIncrementID (List<InfoColumns> infoPrimary){
		if(infoPrimary.isEmpty()) return false;
	
		if(infoPrimary.size()==1){
			for(InfoColumns info:infoPrimary){
				if(info.getExtra().toLowerCase().equals("auto_increment")){
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static List<String> exportSqlMyBatis(String tableName,String modelName,List<InfoColumns> hasil){
		List<InfoColumns> infoPrimary=listPrimary(hasil);
		boolean isAutoIncrement=isAutoIncrementID(infoPrimary);		
		
		List<String> lsModel=new ArrayList<String>();
		lsModel.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		lsModel.add("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\""); 
		lsModel.add("\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");

		lsModel.add("<mapper namespace=\""+packageParent+".persistence."+modelName+"Mapper\">");
		lsModel.add("");
		lsModel.add("\t<cache />");
		lsModel.add("");	
		
		lsModel.add("\t<!-- result map-->");
		lsModel.add("\t<resultMap type=\""+modelName+"\" id=\""+modelName+"Map\">");
		
		for(InfoColumns info:infoPrimary){
			lsModel.add("\t\t<id property=\""+info.modelKolom+"\" column=\""+info.modelKolom+"\"  />  ");   
		}
		
		int i=1;
		for(InfoColumns info:hasil){
			lsModel.add("\t\t<result property=\""+info.modelKolom+"\" column=\""+info.modelKolom+"\" />");  
		}
		
		lsModel.add("\t</resultMap>");
		lsModel.add("");
		
		lsModel.add("\t<!-- nama kolom yang ingin ditampilkan -->");
		lsModel.add("\t<sql id=\"defaultColoumn\">");
		i=1;
		for(InfoColumns info:hasil){
			String namakolom=info.namakolom!=null?info.namakolom.toLowerCase():null;
			
			if(hasil.size()==i){
				lsModel.add("\t\tC."+namakolom.toUpperCase()+" as "+info.modelKolom);
			}else{
				lsModel.add("\t\tC."+namakolom.toUpperCase()+" as "+info.modelKolom+",");
			}
			i++;
		}
		lsModel.add("\t</sql>");
		lsModel.add("");
		
		lsModel.add("\t<!-- query select, dengan kolom yang default di atas, apabila mau ditambahkan join juga bisa disini");
		lsModel.add("\tperubahan query ini akan mempengaruhi select paging dan get di bawah  -->");
		lsModel.add("\t<sql id=\"selectQuery\">");
		lsModel.add("\t\tselect * from (");
		lsModel.add("\t\t\tselect <include refid=\"defaultColoumn\" /> from "+tableName+" C");
		lsModel.add("\t\t)T");
		lsModel.add("\t</sql>");
		lsModel.add("");
		
		lsModel.add("\t<!-- kolom apa aja yang mau disearching -->");
		lsModel.add("\t<sql id=\"searchTambahan\">");
		lsModel.add("\t\tWhere 1=1");
		lsModel.add("\t\t\t<!--!!TAMBAHAN parameter untuk search-->");
		lsModel.add("\t</sql>");
		lsModel.add("");
		
		lsModel.add("\t<!-- kolom apa aja yang mau disearching -->");
		lsModel.add("\t<sql id=\"search\">");		
		lsModel.add("\t\tWHERE 1=1 AND (");
		i=1;
		for(InfoColumns info:hasil){
			
			String dataTypekolom=info.dataTypekolomJava;
			
			if(dataTypekolom.equals("String")){	
				if(i==1)
					lsModel.add("\t\t\t "+info.modelKolom+" like  CONCAT('%', #{search}, '%')");
				else
					lsModel.add("\t\t\t OR "+info.modelKolom+" like  CONCAT('%', #{search}, '%')");
				i++;
			}
			
		}	
		lsModel.add("\t\t)");
		lsModel.add("\t</sql>");
		lsModel.add("");
		
		lsModel.add("\t<!-- insert query untuk table ini -->");
		
		if(isAutoIncrement){
			if(infoPrimary.size()==1){
				for(InfoColumns info:infoPrimary){
					String colomnName=info.namakolom.toUpperCase();
					lsModel.add("\t<insert id=\"insert\" parameterType=\""+modelName+"\" useGeneratedKeys=\"true\" keyColumn=\""+colomnName+"\" keyProperty=\""+info.modelKolom+"\">");
				}
			}else{
				lsModel.add("\t<insert id=\"insert\" parameterType=\""+modelName+"\">");
			}
		}else{
			lsModel.add("\t<insert id=\"insert\" parameterType=\""+modelName+"\">");
		}
		
		lsModel.add("\t\tINSERT INTO "+tableName+" (");
		i=1;
		for(InfoColumns info:hasil){
			String namakolom=info.namakolom.toUpperCase();
			
			if(hasil.size()==i){
				lsModel.add("\t\t\t"+namakolom.toUpperCase());
			}else{
				lsModel.add("\t\t\t"+namakolom.toUpperCase()+",");
			}
			i++;
		}					
		lsModel.add("\t\t) values (");
		i=1;
		for(InfoColumns info:hasil){
			String namakolom=info.modelKolom;
			String dataTypekolom=info.dataTypekolom!=null?info.dataTypekolom:null;
			
			/*if(dataTypekolom.equals("number")){
				dataTypekolom=", jdbcType=NUMERIC";
			}else if(dataTypekolom.equals("int")){
				dataTypekolom=", jdbcType=INTEGER";
			}else {*/
				dataTypekolom="";
//			}
			
			if(hasil.size()==i){
				lsModel.add("\t\t\t#{"+namakolom+""+dataTypekolom.toUpperCase()+"}");
			}else{
				lsModel.add("\t\t\t#{"+namakolom+""+dataTypekolom.toUpperCase()+"},");
			}
			i++;
		}			
		lsModel.add("\t\t)");
		lsModel.add("\t</insert>");
		lsModel.add("");
		
		lsModel.add("\t<!-- update query untuk table ini -->");
		lsModel.add("\t<update id=\"update\" parameterType=\""+modelName+"\">");
		lsModel.add("\t\tUPDATE "+tableName);
		lsModel.add("\t\t<set>");
		i=1;
		for(InfoColumns info:hasil){
			String namakolom=info.namakolom!=null?info.namakolom.toLowerCase():null;
			
			
			if(hasil.size()==i){
				lsModel.add("\t\t\t<if test=\""+info.modelKolom+" != null\">"+namakolom.toUpperCase()+" = #{"+info.modelKolom+"} </if>");
			}else{
				 lsModel.add("\t\t\t<if test=\""+info.modelKolom+" != null\">"+namakolom.toUpperCase()+" = #{"+info.modelKolom+"}, </if>");
			}
			i++;
		}	
		
					
		lsModel.add("\t\t</set>");
		lsModel.add("\t\tWHERE  ");
		i=1;
		for(InfoColumns info:hasil){
			String namakolom=info.namakolom.toUpperCase();
			
			String namakey=info.namakey!=null?info.namakey.toLowerCase():null;
			
			if(namakey.equals("pri")){
				if(i==1){
					lsModel.add("\t\t\t"+namakolom+"= #{"+info.modelKolom+"} ");
				}else{
					 lsModel.add("\t\t\tAND "+namakolom+"= #{"+info.modelKolom+"}");
				}
				i++;
			}
		}
		lsModel.add("\t</update>");
		lsModel.add("");
		
		lsModel.add("\t<!-- remove query untuk table ini -->");
		if(infoPrimary.size()>1){
			lsModel.add("\t<delete id=\"remove\" parameterType=\"HashMap\">");
			lsModel.add("\t\tdelete from "+tableName);
			lsModel.add("\t\t <where> ");
			i=1;
			for(InfoColumns info:infoPrimary){
				String namakolom=info.namakolom.toUpperCase();
				
				if(i==1){
					lsModel.add("\t\t\t<if test=\""+info.modelKolom+" != null\">"+namakolom+"= #{"+info.modelKolom+"} </if>");
				}else{
					lsModel.add("\t\t\t<if test=\""+info.modelKolom+" != null\"> AND "+namakolom+"= #{"+info.modelKolom+"} </if>");
				}
				i++;
			}
			lsModel.add("\t\t </where> ");
			lsModel.add("\t</delete>");
			
			lsModel.add("");
			
			lsModel.add("\t<!-- get berdasarkan primary key -->");
			lsModel.add("\t<select id=\"get\" parameterType=\"HashMap\" resultMap=\""+modelName+"Map\">");
			lsModel.add("\t\t<include refid=\"selectQuery\" />");
			lsModel.add("\t\t <where> ");
			i=1;
			for(InfoColumns info:infoPrimary){
				
				if(i==1){
					lsModel.add("\t\t\t<if test=\""+info.modelKolom+" != null\"> T."+info.modelKolom+"= #{"+info.modelKolom+"} </if>");
				}else{
					lsModel.add("\t\t\t<if test=\""+info.modelKolom+" != null\"> AND T."+info.modelKolom+"= #{"+info.modelKolom+"} </if>");
				}
				i++;
			}
			lsModel.add("\t\t </where> ");
			lsModel.add("\t</select>");
		}else if(infoPrimary.size()==1){
			InfoColumns info=infoPrimary.get(0);
			String namakolom=info.namakolom.toUpperCase();
			
			lsModel.add("\t<delete id=\"remove\" parameterType=\""+info.getDataTypekolomJava()+"\">");
			lsModel.add("\t\tdelete from "+tableName+" where "+namakolom+"=#{value}");
			lsModel.add("\t</delete>");
			
			lsModel.add("");
			
			lsModel.add("\t<!-- get berdasarkan primary key -->");
			lsModel.add("\t<select id=\"get\" parameterType=\""+info.getDataTypekolomJava()+"\" resultMap=\""+modelName+"Map\">");
			lsModel.add("\t\t<include refid=\"selectQuery\" />	where T."+info.modelKolom+"=#{value}");
			lsModel.add("\t</select>");
		}else{
			lsModel.add("\t<delete id=\"remove\" parameterType=\"?\">");
			lsModel.add("\t\tdelete from "+tableName+" ?");
			lsModel.add("\t</delete>");
			
			lsModel.add("");
			
			lsModel.add("\t<!-- get berdasarkan primary key -->");
			lsModel.add("\t<select id=\"get\" parameterType=\"?\" resultMap=\""+modelName+"Map\">");
			lsModel.add("\t\t<include refid=\"selectQuery\" />	?");
			lsModel.add("\t</select>");
		}
		
		lsModel.add("");
		
		lsModel.add("\t<!-- get ALL row -->");
		lsModel.add("\t<select id=\"getAll\" resultMap=\""+modelName+"Map\">");
		lsModel.add("\t\t<include refid=\"selectQuery\" />");
		lsModel.add("\t</select>");
		lsModel.add("");
		
		lsModel.add("\t<!-- select berdasarkan paging parameter -->");
		lsModel.add("\t<select id=\"selectPagingList\" parameterType=\""+modelName+"\" resultMap=\""+modelName+"Map\">");		
		lsModel.add("\t\t\tSELECT x.* from (");
		lsModel.add("\t\t\t\tselect * from (");
		lsModel.add("\t\t\t\t\t<include refid=\"selectQuery\" />");
		lsModel.add("\t\t\t\t\t<include refid=\"searchTambahan\" />");
		lsModel.add("\t\t\t\t) y");
		lsModel.add("\t\t\t\t<if test=\"search != null\">");
		lsModel.add("\t\t\t\t\t<include refid=\"search\" />");
		lsModel.add("\t\t\t\t</if>");
		lsModel.add("\t\t\t\t<if test=\"sort != null\">");
		lsModel.add("\t\t\t\t\torder by ${sort}");
		lsModel.add("\t\t\t\t</if>");
		lsModel.add("\t\t\t\t LIMIT #{page} , #{rowcount}");
		lsModel.add("\t\t\t)x");
		//untuk oracle lsModel.add("\t\t)WHERE nomor BETWEEN (#{rowcount} * (#{page}-1))+1 AND (#{rowcount}	+ (#{rowcount} * (#{page}-1)))");
		lsModel.add("\t</select>");
		lsModel.add("");
		
		lsModel.add("\t<!-- select jumlah seluruh data yang akan di paging -->");
		lsModel.add("\t<select id=\"selectPagingCount\" parameterType=\""+modelName+"\" resultType=\"Integer\">");
		lsModel.add("\t\tselect count(*) from(");
		lsModel.add("\t\t\tSELECT x.* from (");
		lsModel.add("\t\t\t\tselect * from (");
		lsModel.add("\t\t\t\t\t<include refid=\"selectQuery\" />");					
		lsModel.add("\t\t\t\t\t<include refid=\"searchTambahan\" />");				
		lsModel.add("\t\t\t\t) y");
		lsModel.add("\t\t\t\t<if test=\"search != null\">");
		lsModel.add("\t\t\t\t\t<include refid=\"search\" />");
		lsModel.add("\t\t\t\t</if>");
		lsModel.add("\t\t\t)x");
		lsModel.add("\t\t) z");
		lsModel.add("\t</select>");
		lsModel.add("");	
		
		lsModel.add("\t<!-- QUERY CUSTOM LAIN-LAIN bisa dimasukkan dibawah ini -->");
		lsModel.add("");
		
		if(modelName.equals("User")){
			InfoColumns info=infoPrimary.get(0);
			lsModel.add("\t<select id=\"loadUserByUsername\" parameterType=\"String\" resultMap=\"UserMap\">");
			lsModel.add("\t\t<include refid=\"selectQuery\" />");
			lsModel.add("\t\twhere T."+info.modelKolom+"=#{value}");
			lsModel.add("\t</select>");
		}

		lsModel.add("</mapper>");

		return lsModel;
	}
	
	
	
	public static List<String> exportServiceMyBatis(String tableName,String modelName,List<InfoColumns> hasil){
		List<InfoColumns> infoPrimary=listPrimary(hasil);
		boolean isAutoIncrement=isAutoIncrementID(infoPrimary);		

		List<String> lsModel=new ArrayList<String>();
		
		lsModel.add("package "+packageParent+".services;");
		lsModel.add("");
		lsModel.add("import java.util.Date;");
		lsModel.add("import java.util.List;");
		lsModel.add("import org.apache.log4j.Logger;");
		lsModel.add("");
		lsModel.add("import org.springframework.beans.factory.annotation.Autowired;");
		if(modelName.equals("User")){
			lsModel.add("import org.springframework.security.authentication.dao.SaltSource;");
			lsModel.add("import org.springframework.security.authentication.encoding.PasswordEncoder;");
		}
		lsModel.add("import org.springframework.stereotype.Service;");
		lsModel.add("import org.springframework.transaction.annotation.Transactional;");
		lsModel.add("");
		lsModel.add("import "+packageParent+".model."+modelName+";");
		lsModel.add("import "+packageParent+".persistence."+modelName+"Mapper;");
		lsModel.add("");
		lsModel.add("/**");
		lsModel.add(" * GENERATE BY BraisSpringMVCHelp");
		lsModel.add(" * @since		: "+new Date());
		lsModel.add(" * @Description: Services for table "+tableName);
		lsModel.add(" * @Revision	:");
		lsModel.add(" */");
		lsModel.add("");
		lsModel.add("@Service(\""+modelName.toLowerCase()+"Manager\")");
		lsModel.add("public class "+modelName+"Manager {");
		lsModel.add("");
		
		lsModel.add("\tprivate static Logger logger = Logger.getLogger("+modelName+"Manager.class);");
		lsModel.add("");
		
		lsModel.add("\t@Autowired");
		lsModel.add("\tprivate "+modelName+"Mapper "+modelName.toLowerCase()+"Mapper;");
		lsModel.add("");
		
		if(modelName.equals("User")){
			lsModel.add("\t@Autowired");
			lsModel.add("\tprivate PasswordEncoder passwordEncoder;");
			lsModel.add("");
		    lsModel.add("\t@Autowired(required = false)");
		    lsModel.add("\tprivate SaltSource saltSource;");
		    lsModel.add("");
		}
		
		if(infoPrimary.size()>1){
			String param="";
			String paramDoank="";
			int i=1;
			for(InfoColumns info:infoPrimary){
				String namakolom=info.modelKolom;
				
				if(i==1){
					param+=""+info.dataTypekolomJava+" "+namakolom;
					paramDoank+=namakolom;
				}else{
					param+=", "+info.dataTypekolomJava+" "+namakolom;
					paramDoank+=", "+namakolom;
				}
				i++;
			}
			
			
			lsModel.add("\t/** Ambil DATA berdasarkan "+paramDoank+" **/");
			lsModel.add("\tpublic "+modelName+" get("+param+") {");
			lsModel.add("\t\treturn "+modelName.toLowerCase()+"Mapper.get("+paramDoank+");");
			lsModel.add("\t}");
			lsModel.add("");
			
			lsModel.add("\t/** Apakah data dengan "+paramDoank+" ini ada? **/");
			lsModel.add("\tpublic boolean exists("+param+") {	");
			lsModel.add("\t\treturn get("+paramDoank+")!=null;");
			lsModel.add("\t}");
			lsModel.add("");
			
			lsModel.add("\t/** Delete data berdasarkan id **/");
			lsModel.add("\t@Transactional");
			lsModel.add("\tpublic void remove("+param+") {");
			lsModel.add("\t\t"+modelName.toLowerCase()+"Mapper.remove("+paramDoank+");");		
			lsModel.add("\t}");
			lsModel.add("");
		}else if(infoPrimary.size()==1){
			InfoColumns info=infoPrimary.get(0);
			String namakolom=info.modelKolom;
			
			lsModel.add("\t/** Ambil DATA berdasarkan "+namakolom+" **/");
			lsModel.add("\tpublic "+modelName+" get("+info.dataTypekolomJava+" "+namakolom+") {");
			lsModel.add("\t\treturn "+modelName.toLowerCase()+"Mapper.get("+namakolom+");");
			lsModel.add("\t}");
			lsModel.add("");
			
			lsModel.add("\t/** Apakah data dengan "+namakolom+" ini ada? **/");
			lsModel.add("\tpublic boolean exists("+info.dataTypekolomJava+" "+namakolom+") {	");
			lsModel.add("\t\treturn get("+namakolom+")!=null;");
			lsModel.add("\t}");
			lsModel.add("");
			
			lsModel.add("\t/** Delete data berdasarkan "+namakolom+" **/");
			lsModel.add("\t@Transactional");
			lsModel.add("\tpublic void remove("+info.dataTypekolomJava+" "+namakolom+") {");
			lsModel.add("\t\t"+modelName.toLowerCase()+"Mapper.remove("+namakolom+");");		
			lsModel.add("\t}");
			lsModel.add("");
			
		}else{
			lsModel.add("\t/** Ambil DATA berdasarkan ? **/");
			lsModel.add("\tpublic "+modelName+" get(?) {");
			lsModel.add("\t\treturn "+modelName.toLowerCase()+"Mapper.get(?);");
			lsModel.add("\t}");
			lsModel.add("");
			
			lsModel.add("\t/** Apakah data dengan ID ini ada? **/");
			lsModel.add("\tpublic boolean exists(?) {	");
			lsModel.add("\t\treturn get(?)!=null;");
			lsModel.add("\t}");
			lsModel.add("");
			
			lsModel.add("\t/** Delete data berdasarkan ? **/");
			lsModel.add("\t@Transactional");
			lsModel.add("\tpublic void remove(?) {");
			lsModel.add("\t\t"+modelName.toLowerCase()+"Mapper.remove(?);");		
			lsModel.add("\t}");
			lsModel.add("");
				
		}
		
		
		lsModel.add("\t/** Ambil jumlah seluruh data **/");
		lsModel.add("\tpublic int selectPagingCount(String search) {");	
		lsModel.add("\t\t"+modelName+" "+modelName.toLowerCase()+"=new "+modelName+"();");
		lsModel.add("\t\t"+modelName.toLowerCase()+".setSearch(search);");		
		lsModel.add("\t\treturn "+modelName.toLowerCase()+"Mapper.selectPagingCount("+modelName.toLowerCase()+");");
		lsModel.add("\t}");
		lsModel.add("");
		
		lsModel.add("\t/** Ambil data paging **/");
		lsModel.add("\tpublic List<"+modelName+"> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {");
		lsModel.add("\t\t"+modelName+" "+modelName.toLowerCase()+"=new "+modelName+"();");
		lsModel.add("\t\t"+modelName.toLowerCase()+".setSearch(search);");
		lsModel.add("\t\t if(sort!=null)"+modelName.toLowerCase()+".setSort(sort+\" \"+sortOrder);");
		lsModel.add("\t\t"+modelName.toLowerCase()+".setPage(page);");
		lsModel.add("\t\t"+modelName.toLowerCase()+".setRowcount(rowcount);");
		lsModel.add("\t\treturn "+modelName.toLowerCase()+"Mapper.selectPagingList("+modelName.toLowerCase()+");");
		lsModel.add("\t}");
		lsModel.add("");
		
		lsModel.add("\t/** Save Model **/");
		lsModel.add("\t@Transactional");
		lsModel.add("\tpublic "+modelName+" save("+modelName+" "+modelName.toLowerCase()+") {");
		
		if(isAutoIncrement){
			InfoColumns info=infoPrimary.get(0);
			lsModel.add("\t\tif ("+modelName.toLowerCase()+".get"+StringUtil.camelHumpAndTrim(info.modelKolom)+"()==null) {");
		}else
			lsModel.add("\t\tif ("+modelName.toLowerCase()+".get?==null) {");
			
		lsModel.add("\t\t\t"+modelName.toLowerCase()+"Mapper.insert("+modelName.toLowerCase()+");");
		lsModel.add("\t\t} else {");
		lsModel.add("\t\t\t"+modelName.toLowerCase()+"Mapper.update("+modelName.toLowerCase()+");");
		lsModel.add("\t\t} ");
		lsModel.add("\t\treturn "+modelName.toLowerCase()+";");
		lsModel.add("\t}");
		
		
		lsModel.add("\t/** Others Method **/");
		lsModel.add("");
		if(modelName.equals("User")){
			InfoColumns info=infoPrimary.get(0);
			
			lsModel.add("\tpublic User getUserByUsername(String "+info.modelKolom+") {");
			lsModel.add("\t\treturn userMapper.loadUserByUsername("+info.modelKolom+");");
	        lsModel.add("\t}");
			
    		lsModel.add("");
	        lsModel.add("\tpublic User saveUserLogin(User user) {");
	        if(isAutoIncrement){				
				lsModel.add("\t\tif (user.get"+StringUtil.camelHumpAndTrim(info.modelKolom)+"()==null) {");
			}else
				lsModel.add("\t\tif (user.get?==null) {");
	        
			lsModel.add("\t\t\tuser.setPassword(passwordEncoder.encodePassword(user.getPassword(), saltSource.getSalt(user)));");
			lsModel.add("\t\t}else if (user.getNewPassword() != null) {");		   
			lsModel.add("\t\t\tuser.setPassword(passwordEncoder.encodePassword(user.getNewPassword(), saltSource.getSalt(user)));");
			lsModel.add("\t\t}else{");
			lsModel.add("\t\t\tuser.setPassword(null);");
			lsModel.add("\t\t}");
			lsModel.add("\t\treturn save(user);");
			lsModel.add("\t\t}");
			lsModel.add("");
		}
		lsModel.add("\t}");

		return lsModel;
	}
	
	public static List<String> exportMapperMyBatis(String tableName,String modelName,List<InfoColumns> hasil){
		List<InfoColumns> infoPrimary=listPrimary(hasil);
		boolean isAutoIncrement=isAutoIncrementID(infoPrimary);		

		List<String> lsModel=new ArrayList<String>();
		
		
		lsModel.add("package "+packageParent+".persistence;");
		lsModel.add("");
		lsModel.add("import java.util.Date;");
		lsModel.add("import java.util.List;");
		lsModel.add("import org.springframework.dao.DataAccessException;");
		lsModel.add("import org.apache.ibatis.annotations.Param;");
		
		lsModel.add("import "+packageParent+".model."+modelName+";");
		lsModel.add("");
		lsModel.add("/**");
		lsModel.add(" * GENERATE BY BraisSpringMVCHelp");
		lsModel.add(" * @since		: "+new Date());
		lsModel.add(" * @Description: Mapper Interface for table "+tableName);
		lsModel.add(" * @Revision	:");
		lsModel.add(" */");
		lsModel.add("");
		lsModel.add("public interface "+modelName+"Mapper {");
		lsModel.add("");
		lsModel.add("\tpublic void insert("+modelName+" "+modelName.toLowerCase()+") throws DataAccessException;");	
		lsModel.add("");
		lsModel.add("\tpublic void update("+modelName+" "+modelName.toLowerCase()+") throws DataAccessException;");	
		lsModel.add("");
		
		if(infoPrimary.size()>1){
			String param="";
			int i=1;
			for(InfoColumns info:infoPrimary){
				String namakolom=info.modelKolom;
				
				if(i==1){
					param+="@Param(\""+namakolom+"\") "+info.dataTypekolomJava+" "+namakolom;
				}else{
					param+=", @Param(\""+namakolom+"\") "+info.dataTypekolomJava+" "+namakolom;
				}
				i++;
			}
			
			
			lsModel.add("\tpublic void remove("+param+") throws DataAccessException;");	
			lsModel.add("");
			lsModel.add("\tpublic "+modelName+" get("+param+") throws DataAccessException;");	
		}else if(infoPrimary.size()==1){
			InfoColumns info=infoPrimary.get(0);
			String namakolom=info.modelKolom;
			lsModel.add("\tpublic void remove("+info.getDataTypekolomJava()+" "+namakolom+") throws DataAccessException;");	
			lsModel.add("");
			lsModel.add("\tpublic "+modelName+" get("+info.getDataTypekolomJava()+" "+namakolom+") throws DataAccessException;");	
		}else{
			lsModel.add("\tpublic void remove(?) throws DataAccessException;");	
			lsModel.add("");
			lsModel.add("\tpublic "+modelName+" get(?) throws DataAccessException;");	
		}
		
		lsModel.add("");
		lsModel.add("\tpublic List<"+modelName+"> getAll() throws DataAccessException;");	
		lsModel.add("");
		lsModel.add("\tpublic List<"+modelName+"> selectPagingList("+modelName+" "+modelName.toLowerCase()+") throws DataAccessException;");		
		lsModel.add("");
		lsModel.add("\tpublic Integer selectPagingCount("+modelName+" "+modelName.toLowerCase()+") throws DataAccessException;");
		lsModel.add("");
		lsModel.add("\t// QUERY CUSTOM yang lain dibawah sini");
		lsModel.add("");
		
		if(modelName.equals("User")){
			InfoColumns info=infoPrimary.get(0);
			lsModel.add("\tpublic User loadUserByUsername(String "+info.modelKolom+") throws DataAccessException;");
		}
		lsModel.add("");
		lsModel.add("}");

		return lsModel;
	}
	
	public static List<String> exportModelSpring(String tableName,String modelName,List<InfoColumns> hasil){
		List<String> lsModel=new ArrayList<String>();
		lsModel.add("package "+packageParent+".model;");
		lsModel.add("");
		lsModel.add("import java.io.Serializable;");
		lsModel.add("import java.util.Date;");
		if(modelName.equals("User")){
			lsModel.add("import java.util.Collection;");
			lsModel.add("import java.util.LinkedHashSet;");
			lsModel.add("import java.util.Set;");
		}
		lsModel.add("");
		lsModel.add("import javax.persistence.Temporal;");
		lsModel.add("import javax.persistence.TemporalType;");
		lsModel.add("import javax.validation.constraints.NotNull;");
		lsModel.add("import javax.validation.constraints.Size;");
		lsModel.add("");
		lsModel.add("import org.springframework.format.annotation.DateTimeFormat;");
		lsModel.add("import org.springframework.format.annotation.NumberFormat;");
		lsModel.add("import org.springframework.format.annotation.NumberFormat.Style;");
		lsModel.add("");
		if(modelName.equals("User")){
			lsModel.add("import org.springframework.security.core.GrantedAuthority;");
			lsModel.add("import org.springframework.security.core.userdetails.UserDetails;");
			lsModel.add("");
			lsModel.add("import "+packageParent+".model.Role;");
			lsModel.add("");
		}
		lsModel.add("import "+packageParent+".model.BaseObject;");
		lsModel.add("");
		lsModel.add("/**");
		lsModel.add(" * GENERATE BY BraisSpringMVCHelp");
		lsModel.add(" * @since		: "+new Date());
		lsModel.add(" * @Description: Model for table "+tableName);
		lsModel.add(" * @Revision	:");
		lsModel.add(" */");
		lsModel.add("");
		lsModel.add("public class "+modelName+" extends BaseObject implements Serializable "+(modelName.equals("User")?", UserDetails":"")+" {");
		lsModel.add("");
		lsModel.add("\t//****************** COLOMN FROM TABLE START HERE ******************/");
		for(InfoColumns info:hasil){
			String namakolom=info.modelKolom;
			String dataTypekolom=info.dataTypekolomJava;
			String dataTypekolomAsli=info.dataTypekolom!=null?info.dataTypekolom.toLowerCase():null;
			String is_nullable=info.is_nullable!=null?info.is_nullable.toLowerCase():null;
			Integer charactermaximumlength=info.charactermaximumlength;
			
			
			
			if(validation.equals("YES")){	
				if(is_nullable.equals("no")){
//					if(dataTypekolom.equals("Integer")){
//						lsModel.add("\t@NotNull");
//					}else if(dataTypekolom.equals("String")){
						lsModel.add("\t@NotNull");
//					}
				}
				if(dataTypekolom.equals("String")){
					lsModel.add("\t@Size(max="+charactermaximumlength+")");
				}else if(dataTypekolomAsli.equals("date")){
					lsModel.add("\t@Temporal(TemporalType.DATE)");
					lsModel.add("\t@DateTimeFormat(style=\"M-\")");
				}else if(dataTypekolomAsli.equals("datetime"))	{
					lsModel.add("\t@Temporal(TemporalType.TIMESTAMP)");
					lsModel.add("\t@DateTimeFormat(style=\"MM\")");
				}else if(dataTypekolom.equals("Double")){
					lsModel.add("\t@NumberFormat(style = Style.CURRENCY)");
				}
			}
			
			lsModel.add("\t"+method.toLowerCase()+" "+dataTypekolom+" "+namakolom+";");
			lsModel.add("");
		}
		
		lsModel.add("\t//****************** COLOMN FROM TABLE END HERE ******************/");
		
		lsModel.add("");
		lsModel.add("");
		 
		lsModel.add("\t//****************** OTHERS START HERE ******************/");
		lsModel.add("");
		lsModel.add("\t"+method.toLowerCase()+" String itemId;");
		if(modelName.equals("User")){
			lsModel.add("\t"+method.toLowerCase()+" String oldPassword;");
			lsModel.add("\t"+method.toLowerCase()+" String newPassword;");
			lsModel.add("\t"+method.toLowerCase()+" String confirmPassword;");
			lsModel.add("");
		}
		lsModel.add("\t//****************** OTHERS END HERE ******************/");
		
		lsModel.add("");
		lsModel.add("");
		
		lsModel.add("\t//****************** CONSTRUCTOR START HERE ******************/");
		lsModel.add("\tpublic "+modelName+"(){");
		lsModel.add("\t\t//TODO: standard constructor free to change");
		lsModel.add("\t}");
		lsModel.add("");
		lsModel.add("\t//****************** CONSTRUCTOR END HERE ******************/");
		
		lsModel.add("");
		lsModel.add("");
		
		lsModel.add("\t//****************** GETTER SETTER START HERE ******************/");
		for(InfoColumns info:hasil){
			String namakolom=info.modelKolom;
			String dataTypekolom=info.dataTypekolomJava;
			
			lsModel.add("\tpublic "+dataTypekolom+" get"+StringUtil.camelHumpAndTrim(namakolom)+"(){ return "+namakolom+"; }");
			lsModel.add("\tpublic void set"+StringUtil.camelHumpAndTrim(namakolom)+"("+dataTypekolom+" "+namakolom+"){ this."+namakolom+" = "+namakolom+"; }");
			lsModel.add("");
			
		}
		lsModel.add("");
		
		List<InfoColumns> infoPrimary=listPrimary(hasil);
		boolean isAutoIncrement=isAutoIncrementID(infoPrimary);	
	
		
		String pathJsp="";
		
		if(infoPrimary.size()>1){
			int i=1;
			for(InfoColumns info:infoPrimary){
				String namakolom=info.modelKolom;
				
				if(i==1){
					pathJsp+=""+namakolom+"";
				}else{
					pathJsp+="+\"/\"+"+namakolom+"";
				}
				i++;
			}
			
			
		}else if(infoPrimary.size()==1){
			InfoColumns info=infoPrimary.get(0);
			String namakolom=info.modelKolom;
			
			pathJsp+=""+namakolom+"";
		}else{
			
			pathJsp+="?";
		}

		lsModel.add("\tpublic String getItemId() {return \"\"+"+pathJsp+";	}");
		lsModel.add("\tpublic void setItemId(String itemId) {this.itemId = itemId;}");
		lsModel.add("");
	
		
		if(modelName.equals("User")){
			lsModel.add("\tpublic String getOldPassword() {return oldPassword;	}");
			lsModel.add("\tpublic void setOldPassword(String oldPassword) {this.oldPassword = oldPassword;}");
			lsModel.add("");
					
			lsModel.add("\tpublic String getNewPassword() {return newPassword;}");
			lsModel.add("\tpublic void setNewPassword(String newPassword) {this.newPassword = newPassword;}");
			lsModel.add("");
			
			lsModel.add("\tpublic String getConfirmPassword() {return confirmPassword;}");
			lsModel.add("\tpublic void setConfirmPassword(String confirmPassword) {this.confirmPassword = confirmPassword;	}");
			lsModel.add("");
		}
		lsModel.add("\t//****************** GETTER SETTER END HERE ******************/");
		lsModel.add("");
		if(modelName.equals("User")){
			
			lsModel.add("\t@Override");
			lsModel.add("\tpublic Collection<? extends GrantedAuthority> getAuthorities() {");
			lsModel.add("\t\tSet<GrantedAuthority> authorities = new LinkedHashSet<GrantedAuthority>();");
			lsModel.add("\t\tauthorities.add(new Role(Role.ROLE_NAME_USER));");
			lsModel.add("\t\treturn authorities;");
			lsModel.add("\t}");
			lsModel.add("");
			
			lsModel.add("\t@Override");
			lsModel.add("\tpublic boolean isAccountNonExpired() {");
			lsModel.add("\t\treturn true;");
			lsModel.add("\t}");
			lsModel.add("");
			
			lsModel.add("\t@Override");
			lsModel.add("\tpublic boolean isAccountNonLocked() {");
			lsModel.add("\t\treturn locked.intValue()==1?true:false;");
			lsModel.add("\t}");
			lsModel.add("");
			
			lsModel.add("\t@Override");
			lsModel.add("\tpublic boolean isCredentialsNonExpired() {");
			lsModel.add("\t\treturn false;");
			lsModel.add("\t}");
			lsModel.add("");
			
			lsModel.add("\t@Override");
			lsModel.add("\tpublic boolean isEnabled() {");
			lsModel.add("\t\treturn active.intValue()==1?true:false;");
			lsModel.add("\t}");
		}
		lsModel.add("}");
		return lsModel;
	}
	
	public static List<String> exportBaseObject(){
		List<String> lsModel=new ArrayList<String>();
		lsModel.add("package "+packageParent+".model;");
		lsModel.add("");
		lsModel.add("import java.io.Serializable;");
		lsModel.add("");
		lsModel.add("/**");
		lsModel.add(" * GENERATE BY BraisSpringMVCHelp");
		lsModel.add(" * @since		: "+new Date());
		lsModel.add(" * @Description: Model Base Object");
		lsModel.add(" * @Revision	:");
		lsModel.add(" */");
		lsModel.add("");
		lsModel.add("public class BaseObject implements Serializable {");
		lsModel.add("");
		
		lsModel.add("\t//search helper");
		lsModel.add("\tpublic Integer rowcount;");
		lsModel.add("\tpublic Integer page;");
		lsModel.add("\tpublic String sort;");
		lsModel.add("\tpublic String search;");	   
		lsModel.add("");
		
		
		lsModel.add("\t//****************** GETTER SETTER START HERE ******************/");
		lsModel.add("\tpublic Integer getRowcount() { return rowcount; }");
		lsModel.add("\tpublic void setRowcount(Integer rowcount) { this.rowcount = rowcount; }");
		lsModel.add("");
		lsModel.add("\tpublic Integer getPage() { return page; }");
		lsModel.add("\tpublic void setPage(Integer page) { this.page = page; }");
		lsModel.add("");
		lsModel.add("\tpublic String getSort() { return sort; }");
		lsModel.add("\tpublic void setSort(String sort) { this.sort = sort; }");
		lsModel.add("");
		lsModel.add("\tpublic String getSearch() { return search; }");
		lsModel.add("\tpublic void setSearch(String search) { this.search = search; }");		
		lsModel.add("");		
		lsModel.add("\t//****************** GETTER SETTER END HERE ******************/");
		lsModel.add("");
		lsModel.add("}");
		return lsModel;
	}
	
	public static List<String> exportParentController(){
		List<String> lsModel=new ArrayList<String>();
		lsModel.add("package "+packageParent+".web.controller;");
		lsModel.add("");
		lsModel.add("import org.apache.log4j.Logger;");
		lsModel.add("");
		lsModel.add("import java.io.UnsupportedEncodingException;");
		lsModel.add("");
		lsModel.add("import javax.servlet.http.HttpServletRequest;");
		lsModel.add("");
		lsModel.add("import org.apache.log4j.Logger;");
		lsModel.add("import org.springframework.web.util.UriUtils;");
		lsModel.add("import org.springframework.web.util.WebUtils;");
		lsModel.add("");
		lsModel.add("/**");
		lsModel.add("* Abstract ParentController sebagai parent dari semua controller");
		lsModel.add("* Cuman untuk meletakkan reference data saja dan beberapa variable");
		lsModel.add("*/");
		lsModel.add("public abstract class ParentController {");
		lsModel.add("");
		lsModel.add("\tprotected static Logger logger = Logger.getLogger(ParentController.class);");
		lsModel.add("");
		
		lsModel.add("\tString encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {");
		lsModel.add("\t\tString enc = httpServletRequest.getCharacterEncoding();");
		lsModel.add("\t\tif (enc == null) {");
		lsModel.add("\t\t\tenc = WebUtils.DEFAULT_CHARACTER_ENCODING;");
		lsModel.add("\t\t}");
		lsModel.add("\t\ttry {");
		lsModel.add("\t\t\tpathSegment = UriUtils.encodePathSegment(pathSegment, enc);");
		lsModel.add("\t\t} catch (UnsupportedEncodingException uee) {}");
		lsModel.add("\t\treturn pathSegment;");
		lsModel.add("\t}");
		lsModel.add("}");
		
		return lsModel;
	}
	
	public  static void createAllMySql(DBPooling db,String skema) throws Exception{

		boolean exit=false;
		do{
			System.out.print("....Anda memilih modul create model\n\n");
			System.out.print("Masukkan nama table :");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));     
			String  table= br.readLine();
			List<Map> hasil=db.querySelect("SELECT * "+
						   "	FROM `INFORMATION_SCHEMA`.`COLUMNS` "+
						   "	WHERE `TABLE_SCHEMA`='"+skema+"' "+
						   "    AND `TABLE_NAME`='"+table+"'");
			
			if(!hasil.isEmpty()){
				System.out.println("\n\n--- Mantap gan....Tabel ditemukan." +
						"\nMau pakai method apa?  public / private  ");
				 br = new BufferedReader(new InputStreamReader(System.in));     
				 String method= br.readLine();
				 
				 System.out.println("" +
						"\nApakah mau pakai validation SPRING 3? yes/no");
				 br = new BufferedReader(new InputStreamReader(System.in));     
				 String validation= br.readLine().toUpperCase();
			
				 System.out.println("\n Masukkan nama Class model : ");
				 br = new BufferedReader(new InputStreamReader(System.in));     
				 String nama= br.readLine();
				
				System.out.println("\npublic class "+nama.replace(" ", "")+" implements Serializable {\n");
				for(Map map:hasil){
					String namakolom=((String) map.get("COLUMN_NAME")).toLowerCase();
					String dataTypekolom=((String) map.get("DATA_TYPE")).toLowerCase();
					String is_nullable=((String) map.get("IS_NULLABLE")).toLowerCase();
					Integer numericscale=((BigInteger) map.get("NUMERIC_SCALE"))==null?0:((BigInteger) map.get("NUMERIC_SCALE")).intValue();
					Integer charactermaximumlength=((BigInteger) map.get("CHARACTER_MAXIMUM_LENGTH"))==null?0:((BigInteger) map.get("CHARACTER_MAXIMUM_LENGTH")).intValue();
					
					if(dataTypekolom.equals("int")||dataTypekolom.equals("tinyint")){
						dataTypekolom="Integer";
					}else if(dataTypekolom.equals("blob")||dataTypekolom.equals("varchar")||dataTypekolom.equals("char")){
						dataTypekolom="String";
					}else if(dataTypekolom.equals("date")||dataTypekolom.equals("datetime")){
						dataTypekolom="Date";
					}else if(dataTypekolom.equals("decimal")){
						if(numericscale==0)
							dataTypekolom="Integer";
						else dataTypekolom="Double";
					}
					
					System.out.println();
					if(validation.equals("YES")){	
						if(is_nullable.equals("no")){
							if(dataTypekolom.equals("Integer")){
								System.out.println("\t@NotNull");
							}else if(dataTypekolom.equals("String")){
								System.out.println("\t@NotEmpty");
							}
						}
						if(dataTypekolom.equals("String"))
						System.out.println("\t@Size(max="+charactermaximumlength+")");
					}
					
					System.out.println("\t"+method.toLowerCase()+" "+dataTypekolom+" "+namakolom+";");
					
				}
				 System.out.println("\n}");
				 
				 System.out.println("\n\n--- Silahkan copy hasil generate model.\n\n");
				 
				 //generate insert
				 
				 System.out.println("INSERT INTO "+table.toUpperCase()+" ( ");
					int count=0;
					for(Map map:hasil){
						String namakolom=((String) map.get("COLUMN_NAME")).toLowerCase();
						String dataTypekolom=((String) map.get("DATA_TYPE")).toLowerCase();
						Integer numericscale=((BigInteger) map.get("NUMERIC_SCALE"))==null?0:((BigInteger) map.get("NUMERIC_SCALE")).intValue();
						Integer charactermaximumlength=((BigInteger) map.get("CHARACTER_MAXIMUM_LENGTH"))==null?0:((BigInteger) map.get("CHARACTER_MAXIMUM_LENGTH")).intValue();
						
						if(count==0){
							System.out.println("\t"+namakolom+" ");
						}else{
							System.out.println("\t, "+namakolom+" ");
						}
						count++;
					}
					System.out.println(") values ( ");
					
					 count=0;
					for(Map map:hasil){
						String namakolom=((String) map.get("COLUMN_NAME")).toLowerCase();
						String dataTypekolom=((String) map.get("DATA_TYPE")).toLowerCase();
						Integer numericscale=((BigInteger) map.get("NUMERIC_SCALE"))==null?0:((BigInteger) map.get("NUMERIC_SCALE")).intValue();
						Integer charactermaximumlength=((BigInteger) map.get("CHARACTER_MAXIMUM_LENGTH"))==null?0:((BigInteger) map.get("CHARACTER_MAXIMUM_LENGTH")).intValue();
						
						if(count==0){
							System.out.print("\t");
						}else{
							System.out.print("\t, ");
						}
						
						if(dataTypekolom.equals("varchar")||dataTypekolom.equals("char")){
							if(validation.equals("YES")){
//								System.out.println("upper(#{"+namakolom+",jdbcType="+dataTypekolom.toUpperCase()+"}) ");
								System.out.println("upper(#{"+namakolom+"}) ");
							}else{
								System.out.println("upper(#"+namakolom+"#) ");
							}
						}else {
							if(validation.equals("YES")){
//								System.out.println("#{"+namakolom+",jdbcType="+dataTypekolom.toUpperCase()+"} ");
								System.out.println("#{"+namakolom+"} ");
							}else{
								System.out.println("upper(#"+namakolom+"#) ");
							}
						}
						
					
						
						
						count++;
					}
					 System.out.println(")");
				
					System.out.println("\n\n--- Silahkan copy hasil generate insert script.\n\n");
					
					//generate update
					
					System.out.println("UPDATE "+table.toUpperCase()+" ");
					if(validation.equals("YES")){
						System.out.println("<set>");
					}else{
						System.out.println("<dynamic prepend=\" set \">");
					}
					 count=1;
					for(Map map:hasil){
						String namakolom=((String) map.get("COLUMN_NAME")).toLowerCase();
						String dataTypekolom=((String) map.get("DATA_TYPE")).toLowerCase();
						Integer numericscale=((BigInteger) map.get("NUMERIC_SCALE"))==null?0:((BigInteger) map.get("NUMERIC_SCALE")).intValue();
						Integer charactermaximumlength=((BigInteger) map.get("CHARACTER_MAXIMUM_LENGTH"))==null?0:((BigInteger) map.get("CHARACTER_MAXIMUM_LENGTH")).intValue();
						
						if(count==hasil.size()){
							if(validation.equals("YES")){
								if(dataTypekolom.equals("varchar")||dataTypekolom.equals("char")){
									System.out.println("\t<if test=\""+namakolom+" != null\"> "+namakolom+" = upper(#{"+namakolom+"})</if> ");
								}else{
									System.out.println("\t<if test=\""+namakolom+" != null\"> "+namakolom+" = #{"+namakolom+"}</if> ");
								}
							}else{
								if(dataTypekolom.equals("varchar")||dataTypekolom.equals("char")){
									System.out.println("\t<isNotNull removeFirstPrepend=\"true\" prepend=\",\" property=\""+namakolom+"\"> "+namakolom+" = upper(#"+namakolom+"#)</isNotNull> ");
								}else{
									System.out.println("\t<isNotNull removeFirstPrepend=\"true\" prepend=\",\" property=\""+namakolom+"\"> "+namakolom+" = #"+namakolom+"#</isNotNull> ");
								}
							}
						}else{
							if(validation.equals("YES")){
								if(dataTypekolom.equals("varchar")||dataTypekolom.equals("char")){
									System.out.println("\t<if test=\""+namakolom+" != null\"> "+namakolom+" = upper(#{"+namakolom+"}), </if> ");
								}else{
									System.out.println("\t<if test=\""+namakolom+" != null\"> "+namakolom+" = #{"+namakolom+"}, </if> ");
								}
							}else{
								if(dataTypekolom.equals("varchar")||dataTypekolom.equals("char")){
									System.out.println("\t<isNotNull removeFirstPrepend=\"true\" prepend=\",\" property=\""+namakolom+"\"> "+namakolom+" = upper(#"+namakolom+"#)</isNotNull> ");
								}else{
									System.out.println("\t<isNotNull removeFirstPrepend=\"true\" prepend=\",\" property=\""+namakolom+"\"> "+namakolom+" = #"+namakolom+"#</isNotNull> ");
								}
							}
						}
						count++;
					}
					if(validation.equals("YES")){
						System.out.println("</set>");
					}else{
						System.out.println("</dynamic>");
					}
					System.out.print("WHERE \t");
					
					 count=0;
					for(Map map:hasil){
						String namakolom=((String) map.get("COLUMN_NAME")).toLowerCase();
						String namakey=((String) map.get("COLUMN_KEY")).toLowerCase();
						String dataTypekolom=((String) map.get("DATA_TYPE")).toLowerCase();
						Integer numericscale=((BigInteger) map.get("NUMERIC_SCALE"))==null?0:((BigInteger) map.get("NUMERIC_SCALE")).intValue();
						Integer charactermaximumlength=((BigInteger) map.get("CHARACTER_MAXIMUM_LENGTH"))==null?0:((BigInteger) map.get("CHARACTER_MAXIMUM_LENGTH")).intValue();
						
						if(namakey.equals("pri"))
						if(count==0){
							if(validation.equals("YES")){
								System.out.print(namakolom+"= #{"+namakolom+"} ");
							}else{
								System.out.print(namakolom+"= #"+namakolom+"# ");
							}
						}else{
							if(validation.equals("YES")){
								System.out.println("AND \t"+namakolom+"= #{"+namakolom+"} ");
							}else{
								System.out.println("AND \t"+namakolom+"= #"+namakolom+"# ");
							}
						}
						
						
						count++;
					}
					 
				
					System.out.print("\n\n--- Silahkan copy hasil generate script update.\n\n");
			
				
				System.out.println("\n\n Apakah anda ingin keluar modul ini? yes/no");
				 br = new BufferedReader(new InputStreamReader(System.in));     
				 String pilih= br.readLine();
				 
				 
				if(pilih.toUpperCase().equals("YES")){
					exit=true;
					System.out.println("\n\n===enter to continue===");
					br = new BufferedReader(new InputStreamReader(System.in));   
					
				}
			}else{
				System.err.println("\n\n===Table Tidak ditemukan===");
				br = new BufferedReader(new InputStreamReader(System.in));   
				
			}
		}while(!exit);
	
	}
	
	public  static void createModelMySql(DBPooling db,String skema) throws Exception{
		boolean exit=false;
		do{
			System.out.print("....Anda memilih modul create model\n\n");
			System.out.print("Masukkan nama table :");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));     
			String  table= br.readLine();
			List<Map> hasil=db.querySelect("SELECT * "+
						   "	FROM `INFORMATION_SCHEMA`.`COLUMNS` "+
						   "	WHERE `TABLE_SCHEMA`='"+skema+"' "+
						   "    AND `TABLE_NAME`='"+table+"'");
			
			if(!hasil.isEmpty()){
				System.out.println("\n\n--- Mantap gan....Tabel ditemukan." +
						"\nMau pakai method apa?  public / private  ");
				 br = new BufferedReader(new InputStreamReader(System.in));     
				 String method= br.readLine();
				 
				 System.out.println("" +
						"\nApakah mau pakai validation SPRING 3? yes/no");
				 br = new BufferedReader(new InputStreamReader(System.in));     
				 String validation= br.readLine().toUpperCase();
			
				 System.out.println("\n Masukkan nama Class model : ");
				 br = new BufferedReader(new InputStreamReader(System.in));     
				 String nama= br.readLine();
				
				System.out.println("\npublic class "+nama.replace(" ", "")+" implements Serializable {\n");
				for(Map map:hasil){
					String namakolom=((String) map.get("COLUMN_NAME")).toLowerCase();
					String dataTypekolom=((String) map.get("DATA_TYPE")).toLowerCase();
					String is_nullable=((String) map.get("IS_NULLABLE")).toLowerCase();
					Integer numericscale=((BigInteger) map.get("NUMERIC_SCALE"))==null?0:((BigInteger) map.get("NUMERIC_SCALE")).intValue();
					Integer charactermaximumlength=((BigInteger) map.get("CHARACTER_MAXIMUM_LENGTH"))==null?0:((BigInteger) map.get("CHARACTER_MAXIMUM_LENGTH")).intValue();
					
					if(dataTypekolom.equals("int")||dataTypekolom.equals("tinyint")){
						dataTypekolom="Integer";
					}else if(dataTypekolom.equals("blob")||dataTypekolom.equals("varchar")||dataTypekolom.equals("char")){
						dataTypekolom="String";
					}else if(dataTypekolom.equals("date")||dataTypekolom.equals("datetime")){
						dataTypekolom="Date";
					}else if(dataTypekolom.equals("decimal")){
						if(numericscale==0)
							dataTypekolom="Integer";
						else dataTypekolom="Double";
					}
					
					System.out.println();
					if(validation.equals("YES")){	
						if(dataTypekolom.equals("Integer")){
							System.out.println("\t@NotNull");
						}else if(dataTypekolom.equals("String")){
							System.out.println("\t@NotEmpty");
						}
						if(dataTypekolom.equals("String"))
						System.out.println("\t@Size(max="+charactermaximumlength+")");
					}
					
					System.out.println("\t"+method.toLowerCase()+" "+dataTypekolom+" "+namakolom+";");
					
				}
				 System.out.println("\n}");
			
				System.out.println("\n\n--- Silahkan copy hasil generate model." +
						"\n\n Apakah anda ingin keluar modul ini? yes/no");
				 br = new BufferedReader(new InputStreamReader(System.in));     
				 String pilih= br.readLine();
				 
				 
				if(pilih.toUpperCase().equals("YES")){
					exit=true;
					System.out.println("\n\n===enter to continue===");
					br = new BufferedReader(new InputStreamReader(System.in));   
					
				}
			}else{
				System.err.println("\n\n===Table Tidak ditemukan===");
				br = new BufferedReader(new InputStreamReader(System.in));   
				
			}
		}while(!exit);
	}
	
	
	public  static void insertScriptMySql(DBPooling db,String skema) throws Exception{
		boolean exit=false;
		do{
			System.out.print("....Anda memilih modul Insert Script\n\n");
			System.out.println("Masukkan nama table :");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));     
			String  table= br.readLine();
			List<Map> hasil=db.querySelect("SELECT * "+
						   "	FROM `INFORMATION_SCHEMA`.`COLUMNS` "+
						   "	WHERE `TABLE_SCHEMA`='"+skema+"' "+
						   "    AND `TABLE_NAME`='"+table+"'");
			
			if(!hasil.isEmpty()){
				System.out.println("\n\n--- Mantap gan....Tabel ditemukan." +
						"\nMau pakai Spring 3 ga?  yes / no  ");
				 br = new BufferedReader(new InputStreamReader(System.in));     
				 String jdbcType= br.readLine().toUpperCase();
				 
				
				System.out.println("INSERT INTO "+table.toUpperCase()+" ( ");
				int count=0;
				for(Map map:hasil){
					String namakolom=((String) map.get("COLUMN_NAME")).toLowerCase();
					String dataTypekolom=((String) map.get("DATA_TYPE")).toLowerCase();
					Integer numericscale=((BigInteger) map.get("NUMERIC_SCALE"))==null?0:((BigInteger) map.get("NUMERIC_SCALE")).intValue();
					Integer charactermaximumlength=((BigInteger) map.get("CHARACTER_MAXIMUM_LENGTH"))==null?0:((BigInteger) map.get("CHARACTER_MAXIMUM_LENGTH")).intValue();
					
					if(count==0){
						System.out.println("\t"+namakolom+" ");
					}else{
						System.out.println("\t, "+namakolom+" ");
					}
					count++;
				}
				System.out.println(") values (");
				
				 count=0;
				for(Map map:hasil){
					String namakolom=((String) map.get("COLUMN_NAME")).toLowerCase();
					String dataTypekolom=((String) map.get("DATA_TYPE")).toLowerCase();
					Integer numericscale=((BigInteger) map.get("NUMERIC_SCALE"))==null?0:((BigInteger) map.get("NUMERIC_SCALE")).intValue();
					Integer charactermaximumlength=((BigInteger) map.get("CHARACTER_MAXIMUM_LENGTH"))==null?0:((BigInteger) map.get("CHARACTER_MAXIMUM_LENGTH")).intValue();
					
					if(count==0){
						System.out.print("\t");
					}else{
						System.out.print("\t, ");
					}
					
					if(dataTypekolom.equals("varchar")||dataTypekolom.equals("char")){
						if(jdbcType.equals("YES")){
//							System.out.println("upper(#{"+namakolom+",jdbcType="+dataTypekolom.toUpperCase()+"}) ");
							System.out.println("upper(#{"+namakolom+"}) ");
						}else{
							System.out.println("upper(#"+namakolom+"#) ");
						}
					}else {
						if(jdbcType.equals("YES")){
//							System.out.println("#{"+namakolom+",jdbcType="+dataTypekolom.toUpperCase()+"} ");
							System.out.println("#{"+namakolom+"} ");
						}else{
							System.out.println("upper(#"+namakolom+"#) ");
						}
					}
					
				
					
					
					count++;
				}
				 System.out.println(")");
			
				System.out.println("\n\n--- Silahkan copy hasil generate insert script." +
						"\n\n Apakah anda ingin keluar modul ini? yes/no");
				 br = new BufferedReader(new InputStreamReader(System.in));     
				 String pilih= br.readLine();
				 
				 
				if(pilih.toUpperCase().equals("YES")){
					exit=true;
					System.out.println("\n\n===enter to continue===");
					br = new BufferedReader(new InputStreamReader(System.in));   
					
				}
			}else{
				System.err.println("\n\n===Table Tidak ditemukan===");
				br = new BufferedReader(new InputStreamReader(System.in));   
				
			}
		}while(!exit);
	}
	
	public  static void updateScriptMySql(DBPooling db,String skema) throws Exception{
		boolean exit=false;
		do{
			System.out.print("....Anda memilih modul Update Script\n\n");
			System.out.println("Masukkan nama table :");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));     
			String  table= br.readLine();
			List<Map> hasil=db.querySelect("SELECT * "+
						   "	FROM `INFORMATION_SCHEMA`.`COLUMNS` "+
						   "	WHERE `TABLE_SCHEMA`='"+skema+"' "+
						   "    AND `TABLE_NAME`='"+table+"'");
			
			if(!hasil.isEmpty()){
				System.out.println("\n\n--- Mantap gan....Tabel ditemukan." +
						"\nMau pakai Spring 3 ga?  yes / no  ");
				 br = new BufferedReader(new InputStreamReader(System.in));     
				 String jdbcType= br.readLine().toUpperCase();
				 
				
				System.out.println("UPDATE "+table.toUpperCase()+" ");
				if(jdbcType.equals("YES")){
					System.out.println("<set>");
				}else{
					System.out.println("<dynamic prepend=\" set \"> ");
				}
				int count=1;
				for(Map map:hasil){
					String namakolom=((String) map.get("COLUMN_NAME")).toLowerCase();
					String dataTypekolom=((String) map.get("DATA_TYPE")).toLowerCase();
					Integer numericscale=((BigInteger) map.get("NUMERIC_SCALE"))==null?0:((BigInteger) map.get("NUMERIC_SCALE")).intValue();
					Integer charactermaximumlength=((BigInteger) map.get("CHARACTER_MAXIMUM_LENGTH"))==null?0:((BigInteger) map.get("CHARACTER_MAXIMUM_LENGTH")).intValue();
					
					if(count==hasil.size()){
						if(jdbcType.equals("YES")){
							if(dataTypekolom.equals("varchar")||dataTypekolom.equals("char")){
								System.out.println("\t<if test=\""+namakolom+" != null\"> "+namakolom+" = upper(#{"+namakolom+"})</if> ");
							}else{
								System.out.println("\t<if test=\""+namakolom+" != null\"> "+namakolom+" = #{"+namakolom+"}</if> ");
							}
						}else{
							if(dataTypekolom.equals("varchar")||dataTypekolom.equals("char")){
								System.out.println("\t<isNotNull removeFirstPrepend=\"true\" prepend=\",\" property=\""+namakolom+"\"> "+namakolom+" = upper(#"+namakolom+"#)</isNotNull> ");
							}else{
								System.out.println("\t<isNotNull removeFirstPrepend=\"true\" prepend=\",\" property=\""+namakolom+"\"> "+namakolom+" = #"+namakolom+"#</isNotNull> ");
							}
						}
					}else{
						if(jdbcType.equals("YES")){
							if(dataTypekolom.equals("varchar")||dataTypekolom.equals("char")){
								System.out.println("\t<if test=\""+namakolom+" != null\"> "+namakolom+" = upper(#{"+namakolom+"}), </if> ");
							}else{
								System.out.println("\t<if test=\""+namakolom+" != null\"> "+namakolom+" = #{"+namakolom+"}, </if> ");
							}
						}else{
							if(dataTypekolom.equals("varchar")||dataTypekolom.equals("char")){
								System.out.println("\t<isNotNull removeFirstPrepend=\"true\" prepend=\",\" property=\""+namakolom+"\"> "+namakolom+" = upper(#"+namakolom+"#)</isNotNull> ");
							}else{
								System.out.println("\t<isNotNull removeFirstPrepend=\"true\" prepend=\",\" property=\""+namakolom+"\"> "+namakolom+" = #"+namakolom+"#</isNotNull> ");
							}
						}
					}
					count++;
				}
				if(jdbcType.equals("YES")){
					System.out.println("</set> ");
				}else{
					System.out.println("</dynamic> ");
				}
				System.out.print("WHERE \t ");
				
				 count=0;
				for(Map map:hasil){
					String namakolom=((String) map.get("COLUMN_NAME")).toLowerCase();
					String namakey=((String) map.get("COLUMN_KEY")).toLowerCase();
					String dataTypekolom=((String) map.get("DATA_TYPE")).toLowerCase();
					Integer numericscale=((BigInteger) map.get("NUMERIC_SCALE"))==null?0:((BigInteger) map.get("NUMERIC_SCALE")).intValue();
					Integer charactermaximumlength=((BigInteger) map.get("CHARACTER_MAXIMUM_LENGTH"))==null?0:((BigInteger) map.get("CHARACTER_MAXIMUM_LENGTH")).intValue();
					
					if(namakey.equals("pri"))
					if(count==0){
						if(jdbcType.equals("YES")){
							System.out.print(namakolom+"= #{"+namakolom+"} ");
						}else{
							System.out.print(namakolom+"= #"+namakolom+"# ");
						}
					}else{
						if(jdbcType.equals("YES")){
							System.out.println("AND \t"+namakolom+"= #{"+namakolom+"} ");
						}else{
							System.out.println("AND \t"+namakolom+"= #"+namakolom+"# ");
						}
					}
					
					
					count++;
				}
				 
			
				System.out.print("\n\n--- Silahkan copy hasil generate script update." +
						"\n\n Apakah anda ingin keluar modul ini? yes/no");
				 br = new BufferedReader(new InputStreamReader(System.in));     
				 String pilih= br.readLine();
				 
				 
				if(pilih.toUpperCase().equals("YES")){
					exit=true;
					System.out.print("\n\n===enter to continue===");
					br = new BufferedReader(new InputStreamReader(System.in));   
					
				}
			}else{
				System.err.print("\n\n===Table Tidak ditemukan===");
				br = new BufferedReader(new InputStreamReader(System.in));   
				
			}
		}while(!exit);
	}
	
	
	

}

