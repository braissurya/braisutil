package com.brais.utils;

import java.io.BufferedReader;
import java.io.Serializable;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Clob;
import java.text.CharacterIterator;
import java.text.DecimalFormat;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.util.StringUtils;



/**
 * Class ini digunakan untuk fungsi2 berhubungan dengan string
 * 
 * <p>Saat ini class ini sudah digunakan di :
 * <ul>
 * 	<li></li>
 * </ul>
 * 
 * @author Yusuf
 * @since Nov 29, 2005
 */
public class StringUtil implements Serializable { 

	private static final long serialVersionUID = 7523152599859817721L;
	
	
	public static String modelNameConvension(String tableName){
		String [] split=tableName.split("_");
		String result="";
		if(split.length > 1){
			int a=0;
			for(String sp:split){
				
				result+=camelHumpAndTrim(sp);
					
				a++;
			}
		}else{
			result=camelHumpAndTrim(tableName);
		}
		return result;
	}
	
	public static String colomnNameConvension(String colomnName){
		String [] split=colomnName.split("_");
		String result="";
		if(split.length > 0){
			int a=0;
			for(String sp:split){
				if(a==0)	result+=sp.toLowerCase();	
				else result+=camelHumpAndTrim(sp);				
				a++;
			}
		}else{
			result=colomnName;
		}
		return result;
	}
	/**
	 * Fungsi yang mengikuti fungsi RPAD di Oracle, contoh: rpad("0", "YUSUF",
	 * 10) menghasilkan "00000YUSUF"
	 * 
	 * @param karakter
	 *            Karakter untuk melengkapi sisa string
	 * @param kata
	 *            String yang mau dipanjangkan
	 * @param panjang
	 *            Panjang dari string hasilnya
	 * @return String hasil penggabungan dari karakter dan kata
	 * @see Fungsi RPAD di Oracle
	 */
	public static String rpad(String karakter, String kata, int panjang) {
		if(kata==null) return null;
		StringBuffer result = new StringBuffer();
		if (kata.length() < panjang) {
			for (int i = 0; i < panjang - kata.length(); i++) {
				result.append(karakter);
			}
			result.append(kata);
			return result.toString();
		} else {
			return kata;
		}
	}
	

	public static String formatCurrency(String currency, BigDecimal amount) {
		if (amount == null){
			return "-";
//		}else if(amount.toString().indexOf(".")==-1){
//			return (currency != null ? currency : "") + new DecimalFormat("#,##0;(#,##0)").format(amount);
		}else{
			return (currency != null ? currency : "") + new DecimalFormat("#,##0.00;(#,##0.00)").format(amount);
		}
	}
	
	/**
	 * Fungsi untuk mem-format string nomor polis
	 * 
	 * @param kata
	 *            string yang akan di format (hanya boleh terdiri atas angka
	 * @return String hasil yang sudah diformat
	 * @see Kolom MSPO_POLICY_HOLDER pada table EKA.MST_POLICY
	 */
	public static String nomorPolis(String kata) {
		
		if(kata==null){
			return kata;
		}else if(kata.length()==9){
			return kata.substring(0,2)+"."+kata.substring(2);
		}else if(kata.length()==11){
			return kata.substring(0,2)+"."+kata.substring(2,6)+"."+kata.substring(6);
		}else if(kata.length()==14){
			return kata.substring(0,2)+"."+kata.substring(2,5)+"."+kata.substring(5,9)+"."+kata.substring(9);
		}else return kata;

	}
	
	/**
	 * 
	 * @author 		: Bertho Rafitya Iwasurya
	 * @since		: Apr 3, 2012 10:23:50 AM
	 * @Description :
	 *	formatnya : xx-xxxx-xxx-xxxxxx
	 * @param kata
	 * @return
	 */
	public static String nomorsertifikatdmtm(String kata) {
		
		if(kata==null){
			return kata;
		}else if(kata.length()==15){
			return kata.substring(0,2)+"-"+kata.substring(2,6)+"-"+kata.substring(6,9)+"-"+kata.substring(9);
		}else return kata;

	}

	/**
	 * Fungsi untuk mem-format string nomor spaj
	 * 
	 * @param kata
	 *            string yang akan di format (hanya boleh terdiri atas angka
	 * @return String hasil yang sudah diformat
	 * @see Kolom REG_SPAJ pada table EKA.MST_POLICY
	 */
	public static String nomorSPAJ(String kata) {
		if (kata == null) {
			return "";
		} else if (kata.length() == 7) {
			return kata;
		} else if (kata.length() == 9) {
			return kata.substring(0, 4) + "." + kata.substring(4);
		} else if (kata.length() == 11) {
			return kata.substring(0, 2) + "." + kata.substring(2, 6) + "."
					+ kata.substring(6);
		} else
			return "";
	}
	
	public static String nomorPAS(String kata) {
		if (kata == null) {
			return "";
		}  else
			return kata.substring(0, 4) + " " + kata.substring(4, 8) + " "
			+ kata.substring(8, 12) + " "+ kata.substring(12) ;
	}
	/**
	 * Fungsi untuk mem-format string mirip seperti PowerBuilder
	 * 
	 * @param kata
	 *            string yang akan di format (hanya boleh terdiri atas angka
	 * @return String hasil yang sudah diformat
	 */	
	public String formatMask(Object kt, String format) { //contoh: formatMask("12345678901", "@@.@@@@.@.@@@@");
		if(kt==null || format==null) return "";
		String kata;
		if(!(kt instanceof String)) kata = kt.toString();
		else kata = (String) kt;
		
		String[] temp = format.split("\\.");
		if(temp.length==1) return kata;
		StringBuffer result = new StringBuffer();
		
		try{
			for(int i=0; i<temp.length; i++){
				result.append( (i!=0?".":"") + (kata.length()<temp[i].length()?kata:kata.substring(0, temp[i].length())) );
				kata = kata.substring(temp[i].length());
			}
		}catch(Exception e){}
		
		return result.toString();
	}
	
	/**
	 * Fungsi untuk me-return string kosong berisi spasi (space) sepanjang karakter yang diberikan
	 * 
	 * @param panjang
	 *            panjang string yang dikembalikan
	 * @return String kosong berisi spasi
	 */
	public static String spasi(int panjang) {
		StringBuffer result = new StringBuffer();
		for(int i=0; i<panjang; i++)result.append(" ");
		return result.toString();
	}
	
	/**
	 * Fungsi untuk me-return string yang angka depannya saja di ambil berdasarkan posisi index dari ~X
	 * 
	 * @param prod
	 *            Nama produk yang berisi plan dan nomor bisnis (810~X1)
	 * @return String plan atau index ke 0 sampai batas index ~X
	 */
	public static String getPlan(String prod){
		String simbol="~";
		String plan;
		int pos=prod.indexOf(simbol);
		plan=prod.substring(0,pos);
		
		return plan;
	}

	/**
	 * Fungsi untuk me-return string yang Belakangnya saja di ambil berdasarkan posisi index dari ~X
	 * 
	 * @param prod
	 *            Nama produk yang berisi plan dan nomor bisnis (810~X1)
	 * @return String bisnis number atau index ke ~X sampai teraknir
	 */
	public static String getBisnisNumber(String prod){
		String simbol="X";
		String bisnisNumber;
		int pos=prod.indexOf(simbol);
		bisnisNumber=prod.substring(pos+1,prod.length());
		
		return bisnisNumber;
	}
	
	/**
	 * Fungsi untuk me-return string yang Belakangnya saja di ambil berdasarkan posisi index dari -
	 * 
	 * @param prod
	 *            Nama Bisnis yang berisi plan dan nomor bisnis (HCP R-100)
	 * @return String bisnis number atau index ke - sampai teraknir
	 */
	public static String getAngkaNamaBisnis(String bisnisName){
		String simbol="-";
		String bisnisNumber;
		int pos=bisnisName.indexOf(simbol);
		bisnisNumber=bisnisName.substring(pos+1,bisnisName.length());
		
		return bisnisNumber;
	}
	
	/**
	 * Fungsi ini untuk menggantikan symbol pada URL sesuai dengan yg diberikan.Misal "/" dalam "%2F"
	 * @author Deddy
	 * @since Jul 1, 2008 (11:53:48 AM)
	 * @param url
	 * @return
	 */
	public static String escapeHTML(String url) {
		return StringUtils.replace(StringUtils.replace(StringUtils.replace(StringUtils.replace(url, "/", "%2F"), "?", "%3F"), "=", "%3D"), "&", "%26");
	}
	
	/**
	 * Fungsi ini untuk mengubah semua huruf menjadi huruf besar.
	 * @author Deddy
	 * @since Jul 1, 2008 (12:00:20 PM)
	 * @param string
	 * @return
	 */
	public static String upper(String string) {
		if(string==null) return null;
		else return string.toUpperCase();
	}

	/**
	 * Fungsi untuk memecah suatu string yang panjang menjadi beberapa baris
	 * tanpa memecah bagian suatu kata dan bisa diset panjang karakternya
	 * 
	 * @author Yusuf
	 * @since Aug 28, 2008 (8:26:12 PM)
	 * @param string
	 * @param max
	 * @return
	 */
	public static String[] pecahParagraf(String string, int max) {
		StringBuffer tmp = new StringBuffer();
		StringBuffer hasil = new StringBuffer();
		String[] katakata = string.split(" ");
		
		for(String kata : katakata) {			
			if(tmp.length()+kata.length() > max) {
				hasil.append(tmp);
				hasil.append("~");
				tmp = new StringBuffer();
			}
			tmp.append(kata + " ");
		}
		
		if(tmp.toString().length() > 0) hasil.append(tmp);
		
		return hasil.toString().trim().split("~");
	}
	
	/**
	 * Fungsi untuk memecah suatu string yang panjang menjadi beberapa baris
	 * tanpa memecah bagian suatu kata dan bisa diset panjang karakternya
	 * Jika Line breaks maka akan mengikuti 
	 * @param string
	 * @param max
	 * @return
	 * Filename : StringUtil.java
	 * Create By : Bertho Rafitya Iwasurya
	 * Date Created : Feb 16, 2010 9:28:12 AM
	 */
	public static String[] pecahParagrafLineBreaksInclude(String string, int max) {
		StringBuffer tmp = new StringBuffer();
		StringBuffer hasil = new StringBuffer();
		
		String []splitLines=string.split("\n");
		int item=0;
		for (int i = 0; i < splitLines.length; i++) {
			String splitLine=splitLines[i];
			
			String[] katakata = splitLine.split(" ");
			for(String kata : katakata) {			
				if(tmp.length()+kata.length() > max|i!=item) {
					hasil.append(tmp.toString().trim());
					hasil.append("~");
					tmp = new StringBuffer();
					item=i;
				}
				tmp.append(kata + " ");
			}
			
		}
		
		
		if(tmp.toString().length() > 0) hasil.append(tmp);
		
		return hasil.toString().trim().split("~");
	}
	
	/**
	   * Escape characters for text appearing in HTML markup.
	   * 
	   * <P>This method exists as a defence against Cross Site Scripting (XSS) hacks.
	   * This method escapes all characters recommended by the Open Web App
	   * Security Project - 
	   * <a href='http://www.owasp.org/index.php/Cross_Site_Scripting'>link</a>.  
	   * 
	   * <P>The following characters are replaced with corresponding HTML 
	   * character entities : 
	   * <table border='1' cellpadding='3' cellspacing='0'>
	   * <tr><th> Character </th><th> Encoding </th></tr>
	   * <tr><td> < </td><td> &lt; </td></tr>
	   * <tr><td> > </td><td> &gt; </td></tr>
	   * <tr><td> & </td><td> &amp; </td></tr>
	   * <tr><td> " </td><td> &quot;</td></tr>
	   * <tr><td> ' </td><td> &#039;</td></tr>
	   * <tr><td> ( </td><td> &#040;</td></tr> 
	   * <tr><td> ) </td><td> &#041;</td></tr>
	   * <tr><td> # </td><td> &#035;</td></tr>
	   * <tr><td> % </td><td> &#037;</td></tr>
	   * <tr><td> ; </td><td> &#059;</td></tr>
	   * <tr><td> + </td><td> &#043; </td></tr>
	   * <tr><td> - </td><td> &#045; </td></tr>
	   * </table>
	   * 
	   * <P>Note that JSTL's {@code <c:out>} escapes <em>only the first 
	   * five</em> of the above characters.
	   */
	   public static String forHTML(String aText){
	     final StringBuilder result = new StringBuilder();
	     final StringCharacterIterator iterator = new StringCharacterIterator(aText);
	     char character =  iterator.current();
	     while (character != CharacterIterator.DONE ){
	       if (character == '<') {
	         result.append("&lt;");
	       }
	       else if (character == '>') {
	         result.append("&gt;");
	       }
	       else if (character == '&') {
	         result.append("&amp;");
	      }
	       else if (character == '\"') {
	         result.append("&quot;");
	       }
	       else if (character == '\'') {
	         result.append("&#039;");
	       }
	       else if (character == '(') {
	         result.append("&#040;");
	       }
	       else if (character == ')') {
	         result.append("&#041;");
	       }
	       else if (character == '#') {
	         result.append("&#035;");
	       }
	       else if (character == '%') {
	         result.append("&#037;");
	       }
	       else if (character == ';') {
	         result.append("&#059;");
	       }
	       else if (character == '+') {
	         result.append("&#043;");
	       }
	       else if (character == '-') {
	         result.append("&#045;");
	       }
	       else {
	         //the char is not a special one
	         //add it to the result as is
	         result.append(character);
	       }
	       character = iterator.next();
	     }
	     return result.toString();
	  }	
	  
	   /**
	     * @param words: input String 
	     * @return String yang berformat camel and hump dan di trim
	     * ex: "  saMueL baKtiAr " menjadi "Samuel Baktiar"
	     */
	    public static String camelHumpAndTrim( String words )
	    {
	        StringBuffer result = new StringBuffer( "" );
	        String ch;
	        boolean isCap;
	        words = words.trim();
	        int size = words.length();

	        for( int i = 0; i < size; i++ )
	        {
	            ch = words.substring( i, i + 1 );
	            if( i == 0 )
	            {
	                isCap = true;
	            }
	            else if( ch.equals( " " ) )
	            {
	                result = result.append( " " );
	                isCap = true;
	                i++;
	                ch = words.substring( i, i + 1 );
	            }
	            else
	            {
	                isCap = false;
	            }

	            ch = isCap? ch.toUpperCase() : ch.toLowerCase();
	            result = result.append( ch );
	        }

	        return result.toString();
	    } 
	    
	    /**
	     * @author alfian_h
	     * @param cl
	     * @return
	     * 
	     * Methods untuk konversi data CLOB menjadi String
	     */
	    public String convertCLOBtoString(Clob cl){
			String str = null;
			if(cl==null){
				return null;
			}else {
			    StringBuffer strOut = new StringBuffer();
			    String aux;
			    try {
			    	BufferedReader br = new BufferedReader(cl.getCharacterStream());
			        while ((aux=br.readLine())!=null)
			        strOut.append(aux);
			        str = strOut.toString();
			    } catch (java.sql.SQLException e1) {
			    	str = e1.toString();
			    } catch (java.io.IOException e2) {
			    	str = e2.toString();
				}
			}
			return str;
		}
	    
	    /**
		 * Fungsi ini untuk mengecek apakah suatu field ada isinya
		 * @author Yusuf Sutarko
		 * @since May 2, 2007 (7:40:39 AM)
		 * @param object
		 * @return
		 */
		public static boolean isEmpty(Object object) {
			if(object==null) return true;
			else	if(object instanceof String) {
				String tmp = (String) object;
				if(tmp.trim().equals("")) return true;
				else return false;
			}else if(object instanceof List) {
				List<?> tmp = (List<?>) object;
				return tmp.isEmpty();
			}else if(object instanceof Map){
				return ((Map<?, ?>) object).isEmpty();
//			}else if(object instanceof Integer || object instanceof Long|| object instanceof Double|| object instanceof Float|| 
//					object instanceof BigDecimal || object instanceof Date){
//				return false;
			}
			return true;
		}	
		
		public static String velocityTemplate(String template,VelocityContext context){
			 /*  first, get and initialize an engine  */
	        VelocityEngine ve = new VelocityEngine();
	        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
	        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
	        ve.init();
	        /*  next, get the Template  */
	        Template t = ve.getTemplate( template );
	        
	        /*  create a context and add data */
//	        VelocityContext context = new VelocityContext();
//	        context.put("name", "World");
	        
	        /* now render the template into a StringWriter */
	        StringWriter writer = new StringWriter();
	        t.merge( context, writer );
	        
	        /* show the World */
	        return writer.toString();
		}
		

		public static void main(String[]args){
			/*VelocityContext context = new VelocityContext();
			Map<String,List<Map>> tableList = new HashMap<String, List<Map>>();
			  
			 
			List<String> listCol = new ArrayList<String>();
					   
			listCol.add("CAT");
			listCol.add("DOG");
			listCol.add("Lions");
					  
			
			  
	        context.put("modelName", "World");
	        context.put("tableList", listCol);
	        context.put("packageParent", "com_maibro");
			System.out.println(velocityTemplate("templates/views/create.vm", context));*/
			
			System.out.println(modelNameConvension("smsserver_calls"));
		}
}

