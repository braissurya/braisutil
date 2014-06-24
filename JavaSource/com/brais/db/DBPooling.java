package com.brais.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class DBPooling {	 
	
	Connection conn;
	String driver;
	String url;
	String user;
	String pass;
	
	
	public DBPooling(String driver,String url, String user, String pass) throws SQLException{		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.url=url;
		this.user=user;
		this.pass=pass;
		
		conn = getDbConnection();
	}
	
	public DBPooling(Connection con){
		conn = con;
	}
	
	
	
	public Connection getDbConnection() throws SQLException
	{
		Connection dbCon = null;
		
			dbCon = DriverManager.getConnection(url, user, pass);
			dbCon.setAutoCommit(false);
		
		return dbCon;
	}
	
	 public void comit() throws SQLException {

	       conn.commit();
	       
	 }
	 
	 public void rollback()throws SQLException{
		 conn.rollback();
	 }
	
	
	 public void shutdown() throws SQLException {

        Statement st = conn.createStatement();

        // db writes out to files and performs clean shuts down
        // otherwise there will be an unclean shutdown
        // when program ends
//        st.execute("SHUTDOWN");
        conn.close();    // if there are no other open connection
    }
	    
    public static final List<Map> toList(ResultSet rs) throws SQLException
    {
        List rows = new ArrayList();
 
        ResultSetMetaData meta   = rs.getMetaData();
        int               colmax = meta.getColumnCount();
        int               i;
        Object            o = null;

        // the result set is a cursor into the data.  You can only
        // point to one row at a time
        // assume we are pointing to BEFORE the first row
        // rs.next() points to next row and returns true
        // or false if there is no next row, which breaks the loop
        for (; rs.next(); ) {
        	Map colmap=new HashMap<String, Object>();
            for (i = 0; i < colmax; ++i) {
            	                
            	colmap.put(meta.getColumnName(i+1), rs.getObject(i+1));
            }
            rows.add(colmap);
           
        }
 
        return rows;
    }
	    
    /**
     * query select tampung dalam list
     * @param expression
     * @return
     * Filename : DBUtils.java
     * Create By : Bertho Rafitya Iwasurya
     * Date Created : Mar 5, 2010 3:08:26 PM
     */
    public List<Map> querySelect(String expression)  {
    	List result=new ArrayList();
        Statement st = null;
        ResultSet rs = null;

        try {
			st = conn.createStatement();
		     // statement objects can be reused with

        // repeated calls to execute but we
        // choose to make a new one each time
        rs = st.executeQuery(expression);    // run the query

        // do something with the result set.
        result =toList(rs);
        
        st.close();    // NOTE!! if you close a statement the associated ResultSet is
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
        // closed too
        // so you should copy the contents to some other object.
        // the result set is invalidated also  if you recycle an Statement
        // and try to execute some other query before the result set has been
        // completely examined.
        return result;
    }

/**
 * QUERY SELECT LEMPAR KE CONSOLE/LOG
 */	
    public synchronized void query(String expression) throws SQLException {

        Statement st = null;
        ResultSet rs = null;

        st = conn.createStatement();         // statement objects can be reused with

        // repeated calls to execute but we
        // choose to make a new one each time
        rs = st.executeQuery(expression);    // run the query

        // do something with the result set.
        dump(rs);
        st.close();    // NOTE!! if you close a statement the associated ResultSet is

        // closed too
        // so you should copy the contents to some other object.
        // the result set is invalidated also  if you recycle an Statement
        // and try to execute some other query before the result set has been
        // completely examined.
    }

/**
 * untuk SQL commands CREATE, DROP, INSERT and UPDATE
 */
	    public synchronized void update(String expression) throws SQLException {

	        Statement st = null;

	        st = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);    // statements
	        
	        
	        int i = st.executeUpdate(expression);    // run the query

	        if (i == -1) {
	            System.out.println("db error : " + expression);
	        }

	        st.close();
	    }    // void update()

	    public static void dump(ResultSet rs) throws SQLException {

	        // the order of the rows in a cursor
	        // are implementation dependent unless you use the SQL ORDER statement
	        ResultSetMetaData meta   = rs.getMetaData();
	        int               colmax = meta.getColumnCount();
	        int               i;
	        Object            o = null;

	        // the result set is a cursor into the data.  You can only
	        // point to one row at a time
	        // assume we are pointing to BEFORE the first row
	        // rs.next() points to next row and returns true
	        // or false if there is no next row, which breaks the loop
	        for (; rs.next(); ) {
	            for (i = 0; i < colmax; ++i) {
	                o = rs.getObject(i + 1);    // Is SQL the first column is indexed

	                // with 1 not 0
	                if(o!=null)
	                System.out.print(meta.getColumnName(i+1)+" :"+o.toString() + " ");
	            }

	            System.out.println(" ");
	        }
	    }    

	/**
	 * @param args
	 * Filename : DBUtils.java
	 * Create By : Bertho Rafitya Iwasurya
	 * Date Created : Mar 5, 2010 3:00:29 PM
	 */
	public static void main(String[] args) {
		try {
			DBPooling db=new DBPooling("oracle.jdbc.OracleDriver","jdbc:oracle:thin:@128.21.34.18:1521:ajsdb","dev","linkdev");
			System.out.println(db.querySelect("SELECT a.NIK, "+
			         "      b.MCL_FIRST, "+
			         "      c.LCA_NAMA, "+
			         "      d.LDE_DEPT, "+
			         "      a.STS_KRY, PASSWORD "+              
			         " FROM HRD.HRD_MST@absen a, "+
			         "      HRD.MST_CLIENT@absen b, "+
			         "      HRD.LST_CABANG@absen c, "+
			         "      HRD.LST_DEPARTMENT@absen d, "+
			         "      Hrd.hrd_pass@absen e "+
			         " WHERE     b.MCL_ID = a.MCL_ID "+
			         "      and a.nik = e.nik "+
			         "      AND a.LCA_ID = c.LCA_ID "+
			         "      AND a.LDE_ID = d.LDE_ID "+
			         "      and a.tgl_keluar is null "+
			         "      and a.nik='2007041723'").get(0));
			
//			db.update("insert into eka.smsserver_out "+
//				      "  (type,recipient,text,wap_url,wap_expiry_date,wap_signal,create_date,originator,encoding,status_report,flash_sms,src_port,dst_port,sent_date,ref_no,priority,status,errors,gateway_id,lus_id,distribusi,nik) "+
//				       " select 'O' type,  "+
//				       "                EKA.UTILS.STANDARD_HP(TRANSLATE (UPPER (phone), "+
//				       "                           '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ =-_.    ,:;/\\(){}[]', "+
//				       "                           '0123456789' "+
//				       "                          )) recipient, "+
//				       "                 'test' text, "+
//				       "                null wap_expiry_date,null wap_expiry_date,null wap_signal,sysdate create_date,''originator,'7' encoding,1 status_report,0 flash_sms,-1 src_port,-1 dst_port,null sent_date ,null ref_no,10 priority,'U' status,0 errors,EKA.UTILS.SMS_GATEWAY(phone)gateway_id, 1 lus_id, 5 distribusi, '2007041723' nik  "+
//				       "        from ( "+
//				       "         SELECT a.NIK, "+
//				       "                b.MCL_FIRST, "+
//				       "                c.LCA_NAMA, "+
//				       "                d.LDE_DEPT, "+
//				       "                a.STS_KRY,trim(  "+
//				       "                case "+
//				       "                when substr( e.mste_phone_no,1,1) = '0' then e.mste_phone_no "+
//				       "                when e.mste_area_code = substr( e.mste_phone_no,1,length(e.mste_area_code)) "+
//				       "                 then e.mste_phone_no "+
//				       "                 else  e.mste_area_code|| e.mste_phone_no "+
//				       "                end) phone "+               
//				       "           FROM HRD.HRD_MST@absen a, "+
//				       "                HRD.MST_CLIENT@absen b, "+
//				       "                HRD.LST_CABANG@absen c, "+
//				       "                HRD.LST_DEPARTMENT@absen d, "+
//				       "                hrd.mst_telfax@absen e "+
//				       "          WHERE     b.MCL_ID = a.MCL_ID "+
//				       "                AND  b.MCL_ID = E.MCL_ID "+
//				       "                AND a.LCA_ID = c.LCA_ID "+
//				       "                AND a.LDE_ID = d.LDE_ID "+
//				       "                and a.tgl_keluar is null "+
//				       "                and e.lte_id=2 "+               
//				       "               and (e.mste_phone_no is not null and trim(e.mste_phone_no) <> '-') "+
//				       "     union "+          
//				       "     SELECT a.NIK, "+
//				       "                b.MCL_FIRST, "+
//				       "                c.LCA_NAMA, "+
//				       "                d.LDE_DEPT, "+
//				       "                a.STS_KRY, "+ 
//				       "                trim(e.smart_hp) phone "+               
//				       "           FROM HRD.HRD_MST@absen a, "+
//				       "                HRD.MST_CLIENT@absen b, "+
//				       "                HRD.LST_CABANG@absen c, "+
//				       "                HRD.LST_DEPARTMENT@absen d, "+
//				       "                hrd.hrd_smart_num@absen e "+
//				       "          WHERE     b.MCL_ID = a.MCL_ID "+
//				       "                AND  a.NIK = E.NIK "+
//				       "                AND a.LCA_ID = c.LCA_ID "+
//				       "                AND a.LDE_ID = d.LDE_ID "+
//				       "                and a.tgl_keluar is null) "+
//				       "    where length(phone) > 8  and nik in ('2013OS1237')");
//			Integer map=(Integer) db.querySelect("select count(*) count from kartuPA where pin ='0000406090' and periode_begin is not null").get(0).get("count");
//			List<Map> bukupas=db.querySelect("select * from eka.mst_buku_pas");
//			
//			Integer gagal=0;
//			Integer cocok=0;
//			for(Map a: bukupas){
//			
//				String nobuku= (String) a.get("NO_BUKU");
//				String pin= (String) a.get("PIN");
//				String produk=(String) a.get("PRODUK");
//				
//				System.out.print("\nNo Buku : "+nobuku+" \t PIN : "+pin+"\t Produk : "+produk+" Status : " );
//				if(!CheckSum.cekPin(pin.substring(2)).equals(produk)){
//					System.out.println("Tidak cocok\n" );
//					gagal++;
//				}else{
//					System.out.println("Cocok\n " );
//					cocok++;
//				}
//				
//				
//			}
//			System.out.println("Total PIN : "+bukupas.size()+" Cocok : "+cocok+" Gagal : "+gagal );
////			System.out.print(db.querySelect("select sysdate from dual").get(0).get("SYSDATE"));
			
//			String jam_malam="";
//			SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
//			SimpleDateFormat parser2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//
//			try {
//				Date sysdate=new Date();
//				String sysdt=parser.format(sysdate);
//				Date malam_from = DateUtil.add(parser2.parse(sysdt+" 00:01"),Calendar.MINUTE,-1);
//				Date malam_to = DateUtil.add(parser2.parse(sysdt+" 00:00"),Calendar.MINUTE,1);
//				Date pagi_from = DateUtil.add(parser2.parse(sysdt+" 00:01"),Calendar.MINUTE,-1);
//				Date pagi_to = DateUtil.add(parser2.parse(sysdt+" 00:00"),Calendar.MINUTE,1);
//				
//				
//				Date userDate=parser2.parse(sysdt+" 00:00");
//					
//				if (userDate.after(malam_from) & userDate.before(malam_to)) {
//			    		jam_malam="and priority<>0";				    
//			    }else if (userDate.after(pagi_from) & userDate.before(pagi_to)) {
//		    		jam_malam="and priority<>0";				    
//			    }
//			    System.out.println(jam_malam);
//			} catch (Exception e) {
//			    // Invalid date was entered
//				e.printStackTrace();
//			}
			
//			System.out.println(pduToBytes("22OK"));
			
			db.shutdown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	public static byte[] pduToBytes(String s)
	{
		byte[] bytes = new byte[s.length() / 2];
		for (int i = 0; i < s.length(); i += 2)
		{
			try{
			bytes[i / 2] = (byte) (Integer.parseInt(s.substring(i, i + 2), 16));
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return bytes;
	}

}

