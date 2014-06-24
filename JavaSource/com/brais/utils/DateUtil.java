package com.brais.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public final class DateUtil {
    
    
   
    
    /**
     * This method generates a string representation of a date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param strDate a string representation of a date
     * @return a converted Date object
     * @throws ParseException when String doesn't match the expected format
     * @see java.text.SimpleDateFormat
     */
    public static Date convertStringToDate(String aMask, String strDate)
            throws ParseException {
        SimpleDateFormat df;
        Date date;
        df = new SimpleDateFormat(aMask);

       

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            //log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }
    
    
    /**
     * @Method_name	: convertDateToString
     * @author 		: Bertho Rafitya Iwasurya
     * @since		: Nov 4, 2013 10:21:08 AM
     * @Description : convert Date ke String
     * @param aMask : format date
     * @param tgl	: tgl
     * @return
     * @throws ParseException
     * @return_type : String
     * @Revision	:
     * #====#===========#===================#===========================#
     * | ID	|    Date	|	    User		|			Description		|
     * #====#===========#===================#===========================#
     * |	|			|					|							|
     * #====#===========#===================#===========================#
     */
    public static String convertDateToString(String aMask, Date tgl)
            throws ParseException {
        SimpleDateFormat df;
        String date;
        df = new SimpleDateFormat(aMask);
     
        date = df.format(tgl);

        return (date);
    }
    
    

   

   
}
