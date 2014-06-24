package com.brais.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.DataBindingException;



import com.brais.db.DBPooling;
import com.ibatis.common.resources.Resources;

/**
 * @author : Bertho Rafitya Iwasurya
 * @since : Oct 30, 2013 8:19:35 AM
 * @Description : aplikasi kecil untuk bantu aplikasi ebos
 * @Revision :
 *           #====#===========#===================#===========================#
 *           | ID | Date | User | Description |
 *           #====#===========#===================#===========================#
 *           | | | | |
 *           #====#===========#===================#===========================#
 */
public class EBosHelp {
    public static final String path = System.getProperty("user.dir");

    public static void main(String[] args) throws IOException {

	boolean exit = false;
	BufferedReader br = null;

	do {
	    System.out.println("Selamat Datang di EBOS Helper !!!\n\n\n " +
		    "Mau pakai default DB (jdbc.properties) ? [YA/GA] \n");

	    br = new BufferedReader(new InputStreamReader(System.in));
	    String dbDefault = br.readLine();
	    if (!StringUtil.isEmpty(dbDefault))
		dbDefault = dbDefault.toLowerCase();
	    else
		dbDefault = "";

	    boolean dbConnected = false;
	    DBPooling db = null;
	    if (dbDefault.equals("ya")) {
		FileInputStream f = new FileInputStream(path + "\\conf\\jdbc.properties");
		Properties props = new Properties();
		props.load(f);
		String driver = props.getProperty("jdbc.driver");
		String url = props.getProperty("jdbc.url");
		String user = props.getProperty("jdbc.username");
		String pass = props.getProperty("jdbc.password");

		try {
		    db = new DBPooling(driver, url, user, pass);
		    dbConnected = true;
		} catch (SQLException e) {
		    System.out.println("\n\n\nAda error !! :\n"
			    + "	" + e.getMessage() + "\n\n\n");
		}

	    } else if (dbDefault.equals("ga")) {
		do {
		    System.out.println("Silahkan pilih database : \n" +
			    "1 -->Oracle \n" +
			    "2 -->MySql \n" +
			    "3 -->MSSql \n" +
			    "exit --> keluar");

		    br = new BufferedReader(new InputStreamReader(System.in));

		    String modul = br.readLine();

		    if (modul.equals("1")) {
			System.out.print("Masukkan hostname database :");
			br = new BufferedReader(new InputStreamReader(System.in));
			String hostname = br.readLine();
			System.out.print("Masukkan port database :");
			br = new BufferedReader(new InputStreamReader(System.in));
			String port = br.readLine();
			System.out.print("Masukkan sid / service database :");
			br = new BufferedReader(new InputStreamReader(System.in));
			String skema = br.readLine();
			System.out.print("Masukkan user database :");
			br = new BufferedReader(new InputStreamReader(System.in));
			String user = br.readLine();

			System.out.print("Masukkan password database :");
			br = new BufferedReader(new InputStreamReader(System.in));
			String pass = br.readLine();

			String driver = "oracle.jdbc.OracleDriver";
			String url = "jdbc:oracle:thin:@" + hostname + ":" + port + ":" + skema;

			try {
			    db = new DBPooling(driver, url, user, pass);
			    dbConnected = true;
			} catch (SQLException e) {
			    System.out.println("\n\n\nAda error !! :\n"
				    + "	" + e.getMessage() + "\n\n\n");
			}

		    } else if (modul.equals("2") || modul.equals("3")) {
			System.out.println("hehehe :) Maaf belum diimplementasikan =====>  Wani piro");

		    } else if (modul.toUpperCase().equals("EXIT")) {
			exit = true;
			System.out.println("Wah bentar amat, baru sebentar dah keluar aja. xixixixixixi\n" +
				"C U");

		    } else {
			System.out.println("eh jangan asal teken donk!! ");
		    }
		    System.out.println("\n\n== press ENTER to continue ==\n\n");
		    br = new BufferedReader(new InputStreamReader(System.in));
		    br.readLine();
		    clearScreen();
		} while (!exit);
	    } else {
		System.out.println("eh jangan asal teken donk!! ");
	    }

	    if (dbConnected) {
		boolean back = false;
		clearScreen();
		do {

		    try {
			System.out.println("....Koneksi Database Berhasil\n\n");
			System.out.println("Silahkan modul yang ingin dijalankan : \n" +
				"0 --> Test Sysdate \n" +
				"1 --> Import Table dari file zip SPOOL Ebos\n" +
				"2 --> Import Table dari file SPOOL Ebos\n" +
				"3 --> Delete TABEL terkait WSID\n" +
				"4 --> Delete TABEL SETORAN dari TGL ... \n" +
				".. --> back to select Database");
			br = new BufferedReader(new InputStreamReader(System.in));
			String pilih = br.readLine();

			if (pilih.equals("0")) {
			    System.out.println("Sekarang tanggal :");
			    db.query("select sysdate from dual");
			} else if (pilih.equals("1")) {
			    importTableFromSpoolZIP(db);
			} else if (pilih.equals("2")) {
			    importTableFromSpoolFile(db);
			} else if (pilih.equals("3")) {
			    clearScreen();
			    System.out.println("Masukkan kode WSID yang ingin di DELETE (delimited by ;) :");
			    br = new BufferedReader(new InputStreamReader(System.in));

			    String wsid = br.readLine().replace(" ", "").replace(";", "','");
			    
			    System.out.println("delete from MS_COUNTERRPT where wsid IN ('" + wsid + "')");
			    db.update("delete from MS_COUNTERRPT where wsid IN ('" + wsid + "')");
			    
			    System.out.println("delete from SUSPECT_REGISTER where kode_wsid IN ('" + wsid + "')");
			    db.update("delete from SUSPECT_REGISTER where kode_wsid IN ('" + wsid + "')");
			    
			    System.out.println("delete from register_wsid where kode_wsid IN ('" + wsid + "')");
			    db.update("delete from register_wsid where kode_wsid IN ('" + wsid + "')");
			    
			    System.out.println("delete from konf_atm_pkd where kode_wsid IN ('" + wsid + "')");
			    db.update("delete from konf_atm_pkd where kode_wsid IN ('" + wsid + "')");
			    
			    System.out.println("delete from konf_atm_cabd where kode_wsid IN ('" + wsid + "')");
			    db.update("delete from konf_atm_cabd where kode_wsid IN ('" + wsid + "')");
			    
			    System.out.println("delete from konf_ast_pktd  where kode_wsid IN ('" + wsid + "')");
			    db.update("delete from konf_ast_pktd  where kode_wsid IN ('" + wsid + "')");
			    
			    System.out.println("delete from outstanding_tanpa_admin where kode_wsid IN ('" + wsid + "')");
			    db.update("delete from outstanding_tanpa_admin where kode_wsid IN ('" + wsid + "')");
			    
			    System.out.println("delete from outstanding_bongkaran_cr where kode_wsid IN ('" + wsid + "')");
			    db.update("delete from outstanding_bongkaran_cr where kode_wsid IN ('" + wsid + "')");
			    
			    System.out.println("delete from outstanding_pengisian_cr where kode_wsid IN ('" + wsid + "')");
			    db.update("delete from outstanding_pengisian_cr where kode_wsid IN ('" + wsid + "')");
			    
			    System.out.println("delete  from outstanding_hapus_cr  where kode_wsid in ('" + wsid + "')");
			    db.update("delete  from outstanding_hapus_cr  where kode_wsid in ('" + wsid + "')");
			    
			    db.comit();
			    System.out.println("TABLE-TABLE WSID BERHASIL DI DELETE");
			} else if (pilih.equals("4")) {
			    clearScreen();
			    System.out.println("Table Setoran yang di hapus dari Tgl (ddMMyyyy) :");
			    br = new BufferedReader(new InputStreamReader(System.in));

			    String tgl = br.readLine().replace(" ", "").replace(";", "','");

			    System.out.println("delete from setoran_cabang WHERE trunc(TGL_LAPOR) >= TO_DATE('"+tgl+"','DDMMYYYY')");
			    db.update("delete from setoran_cabang WHERE trunc(TGL_LAPOR) >= TO_DATE('"+tgl+"','DDMMYYYY')");
			    
			    System.out.println("delete from setoran_PKT WHERE trunc(TGL_LAPOR) >= TO_DATE('"+tgl+"','DDMMYYYY')");
			    db.update("delete from setoran_PKT WHERE trunc(TGL_LAPOR) >= TO_DATE('"+tgl+"','DDMMYYYY')");
			   
			    db.comit();
			    System.out.println("TABLE-TABLE SETORAN BERHASIL DI DELETE");
			} else if (pilih.equals("..")) {
			    System.out.println("balik ke pilih db ya");
			    back = true;
			}
		    } catch (Exception e) {
			System.out.println("\n\n\nAda error !! :\n"
				+ "	" + e.getMessage() + "\n\n\n");

		    }
		    System.out.println("\n\n== press ENTER to continue ==\n\n");
		    br = new BufferedReader(new InputStreamReader(System.in));
		    br.readLine();
		    clearScreen();
		} while (!back);
		try {
		    db.shutdown();
		} catch (SQLException e) {
		    System.out.println("\n\n\nAda error !! :\n"
			    + "	" + e.getMessage() + "\n\n\n");
		}
	    }

	    System.out.println("\n\n== press ENTER to continue ==\n\n");
	    br = new BufferedReader(new InputStreamReader(System.in));
	    br.readLine();
	    clearScreen();
	} while (!exit);

	try {
	    br.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    public static void clearScreen() {
	for (int i = 0; i < 10; i++) {
	    System.out.println("\n");
	}
	/*
	 * try { String os= System.getProperty("os.name");
	 * if(os.contains("Windows")){ Runtime.getRuntime().exec("cls"); }else{
	 * Runtime.getRuntime().exec("clear"); } } catch (IOException e) { //
	 * TODO Auto-generated catch block //e.printStackTrace(); }
	 */
    }

    public static void importTableFromSpoolZIP(DBPooling db) throws Exception {
	boolean exit = false;
	List<File> lsZipFile = new ArrayList<File>();
	System.out.print("....Anda memilih modul Import Table dari file zip SPOOL Ebos\n\n");

	System.out.println("Silahkan taruh file Zip ke folder zipZone, lalu tekan ENTER untuk mulai proses.");
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	br.readLine();
	List<File> tmpLs = FileUtils.listFilesInDirectory(path + "\\warZone\\zipZone");

	for (File f : tmpLs) {
	    String fileType = FileUtils.getFileType(f);
	    if (fileType.equals("zip"))
		lsZipFile.add(f);
	}

	exit = false;

	if (!lsZipFile.isEmpty()) {
	    for (File f : lsZipFile) {
		// extract file zip
		ZippingUtils.extractAllFileToPath(path + "\\warZone\\zipZone\\" + f.getName(), path + "\\warZone\\fileZone\\" + FileUtils.getFileName(f), null);

		// ambil file yang ada di folder hasil extract
		List<File> filesExtract = FileUtils.listFilesInDirectory(path + "\\warZone\\fileZone\\" + FileUtils.getFileName(f));
		try {
		    for (File fe : filesExtract) {
			prosesFileImportQuery(fe, db);
		    }
		    // house keeping
		    FileUtils.deleteFile(new File(path + "\\warZone\\fileZone\\" + FileUtils.getFileName(f)));// delete
													      // hasil
													      // zip
		    FileUtils.moveFile(path + "\\warZone\\zipZone\\" + f.getName(), path + "\\warZone\\peaceZone\\" + f.getName());// pindahkan
																   // ke
																   // peaceZone
																   // (sudah
																   // selesai
																   // diproses)

		    db.comit();
		} catch (Exception e) {
		    e.printStackTrace();
		    FileUtils.deleteFile(new File(path + "\\warZone\\fileZone\\" + FileUtils.getFileName(f)));// delete
													      // hasil
													      // zip
		}

	    }
	} else {
	    System.out.println("Tidak ada file Zip yang diproses");
	}

    }

    public static void importTableFromSpoolFile(DBPooling db) throws Exception {
	boolean exit = false;
	List<File> lsFile = new ArrayList<File>();
	System.out.print("....Anda memilih modul Import Table dari file zip SPOOL Ebos\n\n");

	System.out.println("Silahkan taruh file ke folder fileZone, lalu tekan Enter untuk mulai proses.");
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	br.readLine();
	lsFile = FileUtils.listFilesInDirectory(path + "\\warZone\\fileZone");

	exit = false;

	if (!lsFile.isEmpty()) {
	    for (File f : lsFile) {

		prosesFileImportQuery(f, db);

		// house keeping
		FileUtils.moveFile(path + "\\warZone\\fileZone\\" + f.getName(), path + "\\warZone\\peaceZone\\" + f.getName());// pindahkan
																// ke
																// peaceZone
																// (sudah
																// selesai
																// diproses)
		db.comit();
	    }
	} else {
	    System.out.println("Tidak ada file yang akan diproses");
	}

    }

    public static Date convertTanggal(String tgl) throws ParseException {
	Date hasil = null;

	try {
	    hasil = DateUtil.convertStringToDate("dd-MMM-yy", tgl);
	} catch (ParseException e) {
	    // TODO Auto-generated catch block
	    hasil = DateUtil.convertStringToDate("MM/dd/yyyy HH:mm:ss", tgl);
	}

	return hasil;
    }

    public static void prosesFileImport(File fe, DBPooling db) throws IOException, SQLException, ParseException {

	if (!fe.isFile())
	    return;
	if (!FileUtils.getFileType(fe).equals("txt"))
	    return;

	// baca file
	BufferedReader buff = new BufferedReader(new FileReader(fe));
	String line = null;
	int baris = 0;
	String queryInsert = "insert into ${TABLE} (${KOLOM}) values(${VALUES})".replace("${TABLE}", FileUtils.getFileName(fe));
	String[] kolomTable = null, valueTable = null;
	PreparedStatement pst = null;
	while ((line = buff.readLine()) != null) {
	    if (baris == 0) {
		queryInsert = queryInsert.replace("${KOLOM}", line.replace("|", ","));
		kolomTable = line.split("\\|");
		String q = "";
		int k = 0;
		for (String klm : kolomTable) {
		    if (k == 0)
			q += "?";
		    else
			q += ",?";
		    k++;
		}
		queryInsert = queryInsert.replace("${VALUES}", q);
		pst = db.getDbConnection().prepareStatement(queryInsert);
	    } else {
		valueTable = (line + "|${END}").split("\\|");

		if (kolomTable.length != valueTable.length - 1) {
		    System.err.println("Jumlah kolom table (" + kolomTable.length + ") dan Value (" + valueTable.length + ") tidak sama pada file " + fe.getName() + " pada baris ke - " + baris);
		    throw new RuntimeException("Jumlah kolom table (" + kolomTable.length + ") dan Value (" + valueTable.length + ") tidak sama pada file " + fe.getName() + " pada baris ke - " + baris);
		}

		int k = 0;
		for (String klm : kolomTable) {
		    String dataTypeCol = (String) db.querySelect("select DATA_TYPE FROM all_tab_columns where table_name = upper('" + FileUtils.getFileName(fe) + "') and column_name=upper('" + kolomTable[k] + "')").get(0).get("DATA_TYPE");
		    String value = valueTable[k];
		    if (dataTypeCol.equals("DATE")) {
			if (StringUtil.isEmpty(value))
			    pst.setTimestamp(k + 1, null);
			else
			    pst.setTimestamp(k + 1, new Timestamp(convertTanggal(value).getTime()));
		    } else {
			if (StringUtil.isEmpty(value))
			    pst.setObject(k + 1, null);
			pst.setObject(k + 1, value);
		    }
		    k++;
		}
		System.out.println(queryInsert +
			"\n value :" + line.replace("\\|", ","));
		pst.addBatch();

	    }
	    baris++;
	}
	pst.executeBatch();
	// tutup file nya
	try {
	    buff.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    public static void prosesFileImportQuery(File fe, DBPooling db) throws IOException, SQLException, ParseException {

	if (!fe.isFile())
	    return;
	if (!FileUtils.getFileType(fe).equals("txt"))
	    return;

	// baca file
	BufferedReader buff = new BufferedReader(new FileReader(fe));
	String line = null;
	int baris = 0;
	String queryInsert = "insert into ${TABLE} (${KOLOM}) values(${VALUES})".replace("${TABLE}", FileUtils.getFileName(fe));
	String[] kolomTable = null, valueTable = null;
	PreparedStatement pst = null;
	try {
	    while ((line = buff.readLine()) != null) {
		if (baris == 0) {
		    queryInsert = queryInsert.replace("${KOLOM}", line.replace("|", ","));
		    kolomTable = line.split("\\|");
		} else {
		    valueTable = (line + "|${END}").split("\\|");

		    if (kolomTable.length != valueTable.length - 1) {
			try {
			    buff.close();
			} catch (IOException e) {
			    e.printStackTrace();
			}
			System.err.println("Jumlah kolom table (" + kolomTable.length + ") dan Value (" + valueTable.length + ") tidak sama pada file " + fe.getName() + " pada baris ke - " + baris);
			throw new RuntimeException("Jumlah kolom table (" + kolomTable.length + ") dan Value (" + valueTable.length + ") tidak sama pada file " + fe.getName() + " pada baris ke - " + baris);
		    }

		    int k = 0;
		    String q = "";
		    for (String klm : kolomTable) {
			String dataTypeCol = (String) db.querySelect("select DATA_TYPE FROM all_tab_columns where table_name = upper('" + FileUtils.getFileName(fe) + "') and column_name=upper('" + kolomTable[k] + "')").get(0).get("DATA_TYPE");
			String value = valueTable[k];
			if (k == 0)
			    q += "";
			else
			    q += ",";
			if (dataTypeCol.equals("DATE")) {
			    if (StringUtil.isEmpty(value))
				q += null;
			    else
				q += "to_date('" + (new SimpleDateFormat("dd-MMM-yy HH.mm.ss").format(convertTanggal(value))) + "','DD-MON-RR HH24.MI.SS')";
			} else if (dataTypeCol.equals("NUMBER")) {
			    if (StringUtil.isEmpty(value))
				q += null;
			    else
				q += value;
			} else {
			    if (StringUtil.isEmpty(value))
				q += null;
			    else
				q += "'" + value.replace("'", "") + "'";
			}

			k++;
		    }
		    String queryExecute = queryInsert.replace("${VALUES}", q);
		    System.out.println(queryExecute);
		    db.update(queryExecute);
		}
		baris++;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    try {
		buff.close();
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }
	    throw new RuntimeException(e.fillInStackTrace());
	}
	// tutup file nya
	try {
	    buff.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

}
