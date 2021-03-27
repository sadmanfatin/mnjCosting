import java.io.OutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.text.DecimalFormat;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.PhaseEvent;


import mnj.mfg.model.services.AppModuleImpl;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCDataControl;

import oracle.jbo.Row;
import oracle.jbo.RowSet;
import oracle.jbo.ViewObject;


import oracle.jbo.domain.Number;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFRegionUtil;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.faces.context.FacesContext;


public class reportMarchand {
    
    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFCellStyle style1 = workbook.createCellStyle();
    HSSFSheet worksheet = null;
    HSSFRow excelrow = null;
    HSSFCell excelcell = null;
    HSSFCellStyle tableHeaderStyle = workbook.createCellStyle();
    HSSFCellStyle headerStyle = workbook.createCellStyle();
    HSSFCellStyle dataStyle = workbook.createCellStyle();
    HSSFFont hSSFFont1 = workbook.createFont();
    HSSFFont hSSFFont2 = workbook.createFont();
    HSSFPalette palette = workbook.getCustomPalette();
    StringBuilder sb;
    StringBuilder sb1;
    
    //  -----------------------------------------------------------------------------------
    
    String tableHeaderFont = HSSFFont.FONT_ARIAL;
    String colHeaderFont = HSSFFont.FONT_ARIAL;
    Short tableHeaderFontSize = 14;
    Short colHeaderFontSize = 11;
    Short tableHeaderFontColor = HSSFColor.BLACK.index;
    Short colHeaderFontColor = HSSFColor.BLACK.index;
    Short tableHeaderBgColor = HSSFColor.GREEN.index;
    
    byte r1 = (byte)153, r2 = (byte)255, r3 = (byte)153;
    Short colHeaderBgColor = HSSFColor.BLUE.index;
    byte r4 = (byte)153, r5 = (byte)255, r6 = (byte)153;
    Short dataBgColor = HSSFColor.YELLOW.index;
    byte r7 = (byte)240, r8 = (byte)255, r9 = (byte)240;

    String headerHeaderText = "Fabric Header";
    String lineHeaderText = "Fabric Line";
    String detailHeaderText = "Fabric Details";
    String wetHeaderText = "Wet Processing";
    String dryHeaderText = "Dry Processing";

    //----------------------------------------------------------------------------------------
    
    int headerNumOfCol = 17;
    int lineNumOfCol = 10;
    int detailNumOfCol = 6;
    int dryNumOfCol = 2;
    int wetNumOfCol = 2;

    int xlRow = 0;
    int xlRowNew = 0;
    int xlCol = 0;
    int xlColHeader = 0;
    int xlColLine = 7;
    int xlColDetail = 0;
    int xlColDry = 7;
    int xlColWet = 7;
    String colName; 
    
    
    ///////////////////////////////////////////////////
    
    {
        ///////////////////////////////////////////////////
        palette.setColorAtIndex(tableHeaderBgColor, r1, r2, r3);
        palette.setColorAtIndex(colHeaderBgColor, r4, r5, r6);
        palette.setColorAtIndex(dataBgColor, r7, r8, r9);

        headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
        headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headerStyle.setFillForegroundColor(colHeaderBgColor);
        hSSFFont1.setFontName(colHeaderFont);
        hSSFFont1.setFontHeightInPoints(colHeaderFontSize);
        // hSSFFont1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        hSSFFont1.setColor(colHeaderFontColor);
        headerStyle.setFont(hSSFFont1);
        //style.setBorderBottom((short) 6); // double line border
        headerStyle.setBorderTop((short)1); // single lines border
        headerStyle.setBorderBottom((short)1);
        headerStyle.setBorderLeft((short)1);
        headerStyle.setBorderRight((short)1);

        dataStyle.setAlignment(CellStyle.ALIGN_CENTER);
        dataStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        dataStyle.setFillForegroundColor(dataBgColor);
        dataStyle.setBorderBottom((short)1);
        dataStyle.setBorderLeft((short)1);
        dataStyle.setBorderRight((short)1);
        dataStyle.setLocked(true);
        dataStyle.setWrapText(true);
        

        tableHeaderStyle.setAlignment(CellStyle.ALIGN_CENTER);
        tableHeaderStyle.setAlignment(CellStyle.ALIGN_CENTER);
        hSSFFont2.setFontHeightInPoints(tableHeaderFontSize);
        hSSFFont2.setFontName(tableHeaderFont);
        // hSSFFont2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        hSSFFont2.setColor(tableHeaderFontColor);
        tableHeaderStyle.setFont(hSSFFont2);
        tableHeaderStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        tableHeaderStyle.setFillForegroundColor(tableHeaderBgColor);


        ///////////////////////////////////////////////////////
        
       
    }
    ///////////////////////////////////////////////////////
    
    
    
    public reportMarchand() {
    }
    AppModuleImpl appM = getAppModuleImpl();

    private AppModuleImpl getAppModuleImpl() {
    DCBindingContainer bindingContainer =
    (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    //BindingContext bindingContext = BindingContext.getCurrent();
    DCDataControl dc =
    bindingContainer.findDataControl("AppModuleDataControl"); // Name of application module in datacontrolBinding.cpx
    AppModuleImpl appM = (AppModuleImpl)dc.getDataProvider();
    return appM;
    }

    public double addCellValue(int col) {
        return 0.0;
    }

    public void main(FacesContext facesContext, OutputStream outputStream) {
        // Add event code here...
        xlRow = 0;
        int  count =1;
                xlCol = 0;
                worksheet = workbook.createSheet("Line" + count++);
                /* password required for locks to become effective */
              // worksheet.protectSheet("protected");
                for (int i = 0; i < 200; i++) {
                    worksheet.createRow(i);
                }
        printRowHeader( headerNumOfCol, xlCol);
        ViewObject report = appM.getdetailsReport1();
        double summArray[]=new double [7];
        double ttlFob=0.0,orderQty=0.0,ttlProfit=0.0,ttlDryCost=0.0,ttlWetCost=0.0,ttlWashCost=0.0,ttlCm=0.0;
        
        
        int number=1;
        Row[] rows = report.getAllRowsInRange();
        for (Row row : rows) {
            if( number==1){
                printRowHeader(row, headerNumOfCol, xlCol);
                number++;
               // this.xlCol = xlColLine;
            // this.xlRow = 0;
            }
            try{
                ttlFob=Double.parseDouble(row.getAttribute("TtlFob").toString());
                
            }
            catch(Exception e){
                ttlFob=0 ;
            }
            summArray[0]=summArray[0]+ttlFob;
            try{
                orderQty=Double.parseDouble(row.getAttribute("Quantity").toString());
                
            }
            catch(Exception e){
               orderQty=0 ;
            }
            summArray[1]=summArray[1]+orderQty;
            
            try{
                ttlProfit=Double.parseDouble(row.getAttribute("TtlProfit").toString());
                
            }
            catch(Exception e){
              ttlProfit =0  ;
            }
            summArray[2]=summArray[2]+ttlProfit;
            try{
                ttlDryCost=Double.parseDouble(row.getAttribute("TotalDryCost").toString());
                
            }
            catch(Exception e){
               ttlDryCost=0 ;
            }
            summArray[3]=summArray[3]+ttlDryCost;
            try{
               ttlWetCost =Double.parseDouble(row.getAttribute("TotalWetCost").toString());
                
            }
            catch(Exception e){
               ttlWetCost =0  ;
            }
            summArray[4]=summArray[4]+ttlWetCost;
            try{
                ttlWashCost=Double.parseDouble(row.getAttribute("TotalWashCost").toString());
                
            }
            catch(Exception e){
               ttlWashCost =0 ;
            }
            summArray[5]=summArray[5]+ttlWashCost;
            try{
                ttlCm=Double.parseDouble(row.getAttribute("TtlCm").toString());
                
            }
            catch(Exception e){
               ttlCm=0 ;
            }
            summArray[6]=summArray[6]+ttlCm;
            
            
            printRowData(row, headerNumOfCol, xlCol);
            xlCol=0;
          // this.xlRowNew = xlRow;
           // this.xlRow = xlRowNew;
            
        }
        
        printRowData(summArray,headerNumOfCol, xlCol);
        xlCol=0;
        
        int numofCol=6;
        
        printAttributeForOtherCOst(numofCol, xlCol);
        
        
        try {
        workbook.write(outputStream);
        outputStream.flush();
        } catch (IOException e) {
        }
    }
    
    /**---------------------------------------------------------------------------------------------
                      method for printing  column headers in table in excel
    -------------------------------------------------------------------------------------------------*/


    public void printRowHeader(Row row, int numOfCol, int xlCol) {
        excelrow = (HSSFRow)worksheet.getRow(xlRow);
        
        
        for (String colName : row.getAttributeNames()) {
            if (row.getAttributeIndexOf(colName) < numOfCol) {
              //  worksheet.addMergedRegion(new CellRangeAddress(xlRow,xlRow+1,0,0));
                excelcell = excelrow.createCell(xlCol);
                excelcell.setCellStyle(headerStyle);
                sb = new StringBuilder(colName);
                 sb1 = new StringBuilder();
                char index;
                for (int i = 0; i < sb.length(); i++) {
                    index = sb.charAt(i);
                    if (i != 0 && Character.isUpperCase(index)) {
                        sb1.append(" ");
                        sb1.append(index);
                    } else {
                        sb1.append(index);
                    }
                }
             
                colName = sb1.toString();
                System.out.println(colName);
               
              
                
                excelcell.setCellValue(colName);
                worksheet.autoSizeColumn(xlCol);
             
                xlCol += 1;
            }
        }
        this.xlRow++;
    }
    
    
    /**------------------------------------------------------------------------------------------
                           method for printing  column data in table in excel
     ---------------------------------------------------------------------------------------------*/

    public void printRowData(Row row, int numOfCol, int xlCol) {

        excelrow = (HSSFRow)worksheet.getRow(xlRow);
        for (String colName : row.getAttributeNames()) {
            if (row.getAttributeIndexOf(colName) < numOfCol) {
                excelcell = excelrow.createCell(xlCol);
               
                if (row.getAttribute(colName) != null) {
                    if(colName.equalsIgnoreCase("Profit")||colName.equalsIgnoreCase("TtlProfit")){
                    String value=null;
                    System.out.println("........colName is "+colName);
                        excelcell.setCellValue(value);
                    }else{
                        excelcell.setCellValue(row.getAttribute(colName).toString());
                    }
                   
                    
                 
                    excelcell.setCellStyle(dataStyle);
                 
                    worksheet.autoSizeColumn(xlCol);
                   
                   
                }
                else  excelcell.setCellStyle(dataStyle);
                xlCol += 1;
            }
            this.xlCol = xlCol;
        }
     this.xlRow++;
    }
       
     /**--------------------------------------------------------------------------------------------
                                method for printing title of table in excel 
      -----------------------------------------------------------------------------------------------*/
    

    private void printTableHeader(int numOfCol, int xlCol, String headerText) {
        if (xlRow > 0) {
            this.xlRow += 3;
        }
        excelrow = (HSSFRow)worksheet.getRow(xlRow);
        excelcell = excelrow.createCell(xlCol);
        //
        CellRangeAddress cellRange =
            new CellRangeAddress(xlRow, //first row (0-based)
                xlRow, //last row  (0-based)
                xlCol, //first column (0-based)
                xlCol + numOfCol - 1); //last column  (0-based)
        excelcell.setCellStyle(tableHeaderStyle);
        worksheet.addMergedRegion(cellRange);
        excelcell.setCellValue(headerText);
        HSSFRegionUtil.setBorderTop(tableHeaderStyle.BORDER_THIN, cellRange,
                                    worksheet, workbook);
        HSSFRegionUtil.setBorderLeft(tableHeaderStyle.BORDER_THIN, cellRange,
                                     worksheet, workbook);
        HSSFRegionUtil.setBorderRight(tableHeaderStyle.BORDER_THIN, cellRange,
                                      worksheet, workbook);
        HSSFRegionUtil.setBorderBottom(tableHeaderStyle.BORDER_THIN, cellRange,
                                       worksheet, workbook);
        this.xlRow++;
    }
     
    /**----------------------------------------------------------------------------------------------
                   method for adding all values of a particular column and printing in excel
     -------------------------------------------------------------------------------------------------*/
    
    private void addColValues(RowSet rowSet, String colName, int xlCol) {
       // this.xlRow += 1;
        int colIndex = 0;
        rowSet.reset();
        if (rowSet.hasNext()) {
            Row row = rowSet.next();
            colIndex = row.getAttributeIndexOf(colName);
            excelrow = (HSSFRow)worksheet.getRow(xlRow);
            int a = xlCol + colIndex - 3;
            excelcell = excelrow.createCell(a);
            excelcell.setCellStyle(dataStyle);
            excelcell.setCellValue("Total :");
            worksheet.autoSizeColumn(xlColDry);
        }
        rowSet.reset();
        double total = 0;
        while (rowSet.hasNext()) {
            Row row = rowSet.next();
            String s1 = null;
            Double d1=0.0;
            try{
                s1= row.getAttribute(colName).toString();
                 d1 = Double.parseDouble(s1);
            }
            catch(Exception e){
               ;
            }
            total += d1;
        }
        total =Double.parseDouble(new DecimalFormat("##.####").format(total));
        excelcell = excelrow.createCell(xlCol + colIndex-2);
        excelcell.setCellStyle(dataStyle);
        excelcell.setCellValue("" + total);
        worksheet.autoSizeColumn(xlCol);
    }


    public void doOnPageLoad() {
        System.out.println("+++++++++++++++++++++++ doOnPageLoad called +++++++++++++++++++");
    }

    private void printHeaderForSAM( int numOfCol, int xlCol) {
        excelrow = (HSSFRow)worksheet.getRow(xlRow);
         String[] array = {"AVG FOB", "AVG N/P", "B","Apple","Orange"};
         
        excelcell = excelrow.createCell(xlCol);
        excelcell.setCellStyle(headerStyle);
        for(int i=0;i<4;i++){
            
            
            
         excelcell = excelrow.createCell(xlCol);
            excelcell.setCellStyle(headerStyle);
            excelcell.setCellValue(array[i]);
            worksheet.autoSizeColumn(xlCol);  
              
             
           
                
                
             
                xlCol += 1;
        }
        this.xlRow++;
        
        
        
    }

    private void printAttributeForOtherCOst(int detailNumOfCol, int xlCol) {
        System.out.println("========================================  printAttributeForOtherCOst ");
       int dataxlCol= xlCol;
       ViewObject report= appM.getdetailsReport1();
        excelrow = (HSSFRow)worksheet.getRow(xlRow+3);
        double [] ArrayData = new double [6];
        
       Row[] rows = report.getAllRowsInRange();
        double avfob=0.0,avprofit=0.0,avcm=0.0,avdry=0.0,avwet=0.0,totalWash=0.0;
        double fob=0.0,pro=0.0,cm=0.0,dry=0.0,wet=0.0, quantity=0.0, totalQuantity = 0.0;
        double count=0.0  ;
        double ttlWashCost=0.0 , avgWashCost = 0.0;
        for (Row row : rows) {
            try{
               fob =Double.parseDouble( row.getAttribute("TtlFob").toString());
            }
            catch(Exception e) {
           fob =0 ; 
            }
            avfob=avfob+fob;
            try{
               quantity = Double.parseDouble( row.getAttribute("Quantity").toString());
            }
            catch(Exception e) {
                 quantity =0   ; 
            }
            totalQuantity=totalQuantity+quantity;
            
            try{
               pro= Double.parseDouble( row.getAttribute("TtlProfit").toString());
            }
            catch(Exception e) {
              pro= 0  ;
            }
            avprofit=avprofit+pro;
            try{
              cm=  Double.parseDouble( row.getAttribute("TtlCm").toString());
            }
            catch(Exception e) {
               cm=  0  ;
            }
            avcm=avcm+cm;
            
            try{
              dry=  Double.parseDouble( row.getAttribute("TotalDryCost").toString());
            }
            catch(Exception e) {
              dry= 0  ;
            }
            avdry=avdry+dry;
            try{
              wet= Double.parseDouble( row.getAttribute("TotalWetCost").toString());
            }
            catch(Exception e) {
              wet= 0  ;
            }
            avwet=avwet+wet;
            
            try{
              ttlWashCost= Double.parseDouble( row.getAttribute("TotalWashCost").toString());
            }
            catch(Exception e) {
               ttlWashCost=0 ;
            }
            avgWashCost = avgWashCost + ttlWashCost;
            
            count++;
        }
        
        ArrayData[0]=Double.parseDouble(new DecimalFormat("##.##").format(avfob/totalQuantity));
        ArrayData[1]=Double.parseDouble(new DecimalFormat("##.##").format(avprofit/totalQuantity));
        ArrayData[2]=Double.parseDouble(new DecimalFormat("##.##").format(avcm/totalQuantity));
        ArrayData[3]=Double.parseDouble(new DecimalFormat("##.##").format(avdry/totalQuantity));
        ArrayData[4]=Double.parseDouble(new DecimalFormat("##.##").format(avwet/totalQuantity));
        ArrayData[5]=Double.parseDouble(new DecimalFormat("##.##").format((avgWashCost/totalQuantity)));
        
        
         String[] Array = {"AVG FOB", "AVG N/P", "AVG CM", "DRY AVG", "WET AVG", "TTL WASH AVG"};
        for(int i=0;i<6;i++) {
            
            try {
                excelcell = excelrow.createCell(xlCol);
            } catch (Exception e) {
                // TODO: Add catch code
                e.printStackTrace();
            }
         
            excelcell.setCellStyle(headerStyle);
            System.out.println("string array data "+Array[i]);
            
            excelcell.setCellValue(Array[i]);
            worksheet.autoSizeColumn(xlCol);
            
            xlCol += 1;
            
        }
        
        
        try {
            excelrow = (HSSFRow)worksheet.getRow(xlRow+4);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
       
            //String[] Arrayy = {"1", "2", "3", "4", "5"};
        for(int i=0;i<6;i++) {
            
            try {
                excelcell = excelrow.createCell(dataxlCol);
            } catch (Exception e) {
                // TODO: Add catch code
                e.printStackTrace();
            }
            
            excelcell.setCellStyle(dataStyle);
            System.out.println("string array data "+ArrayData[i]);
            if(i==1){
            String value=null;
                excelcell.setCellValue(value);
            }else{
                excelcell.setCellValue(ArrayData[i]);
            }
            
            worksheet.autoSizeColumn(dataxlCol);
            
            dataxlCol += 1;
            
        }
        this.xlRow++;
    }


    private void printRowHeader(int headerNumOfCol, int xlCol) {
        excelrow = (HSSFRow)worksheet.getRow(xlRow);
                        excelcell = excelrow.createCell(xlCol);
                        excelcell.setCellStyle(headerStyle);
       worksheet.addMergedRegion(new CellRangeAddress(0,0,0,16));
       
        sb1 = new StringBuilder();
        ViewObject searchView= appM.getFilterView1();
       String buyer=null,season=null;
       try{
           buyer=searchView.getCurrentRow().getAttribute("Buyer").toString();
       }
       catch(Exception e){
           ;
       }
        try{
            season=searchView.getCurrentRow().getAttribute("Season").toString();
        }
        catch(Exception e){
            ;
        }
        
       
        excelcell.setCellValue("COSTING SUMMARY "+"("+"BUER: "+buyer+" & "+"SEASON: "+season+")");
        worksheet.autoSizeColumn(xlCol);
        xlCol += 1;
        
       
    //        for (String colName : row.getAttributeNames()) {
    //            if (row.getAttributeIndexOf(colName) < numOfCol) {
    //                excelcell = excelrow.createCell(xlCol);
    //                excelcell.setCellStyle(headerStyle);
    //                sb = new StringBuilder(colName);
    //                 sb1 = new StringBuilder();
    //                char index;
    //                for (int i = 0; i < sb.length(); i++) {
    //                    index = sb.charAt(i);
    //                    if (i != 0 && Character.isUpperCase(index)) {
    //                        sb1.append(" ");
    //                        sb1.append(index);
    //                    } else {
    //                        sb1.append(index);
    //                    }
    //                }
    //
    //                colName = sb1.toString();
    //                System.out.println(colName);
    //
    //
    //
    //                excelcell.setCellValue(colName);
    //                worksheet.autoSizeColumn(xlCol);
    //
    //                xlCol += 1;
    //            }
    //        }
        this.xlRow++;
        this.xlRow++;
    }

    private void printRowData(double[] summArray, int headerNumOfCol,
        int xlCol) {
        excelrow = (HSSFRow)worksheet.getRow(xlRow);
                        excelcell = excelrow.createCell(xlCol);
                        excelcell.setCellStyle(headerStyle);
        excelcell.setCellValue("Total");
        worksheet.autoSizeColumn(xlCol);
        xlCol += 2;
        excelrow = (HSSFRow)worksheet.getRow(xlRow);
                        excelcell = excelrow.createCell(xlCol);
                        excelcell.setCellStyle(headerStyle);
      //  String totalProfitValue=null;
         excelcell.setCellValue(summArray[1]);
      // excelcell.setCellValue(totalProfitValue);
        worksheet.autoSizeColumn(xlCol);
        xlCol += 2;
        excelrow = (HSSFRow)worksheet.getRow(xlRow);
                        excelcell = excelrow.createCell(xlCol);
                        excelcell.setCellStyle(headerStyle);
        excelcell.setCellValue(summArray[0]);
        worksheet.autoSizeColumn(xlCol);
        xlCol += 2;
        excelrow = (HSSFRow)worksheet.getRow(xlRow);
                        excelcell = excelrow.createCell(xlCol);
                        excelcell.setCellStyle(headerStyle);
        String totalProfitValue=null;               
        excelcell.setCellValue(totalProfitValue);
      //  excelcell.setCellValue(summArray[2]);
        worksheet.autoSizeColumn(xlCol);
        xlCol += 1;
        excelrow = (HSSFRow)worksheet.getRow(xlRow);
                        excelcell = excelrow.createCell(xlCol);
                        excelcell.setCellStyle(headerStyle);
        excelcell.setCellValue(summArray[1]);
        worksheet.autoSizeColumn(xlCol);
        xlCol += 2;
        excelrow = (HSSFRow)worksheet.getRow(xlRow);
                        excelcell = excelrow.createCell(xlCol);
                        excelcell.setCellStyle(headerStyle);
        excelcell.setCellValue(summArray[3]);
        worksheet.autoSizeColumn(xlCol);
        xlCol += 1;
        excelrow = (HSSFRow)worksheet.getRow(xlRow);
                        excelcell = excelrow.createCell(xlCol);
                        excelcell.setCellStyle(headerStyle);
        excelcell.setCellValue(summArray[1]);
        worksheet.autoSizeColumn(xlCol);
        xlCol += 2;
        excelrow = (HSSFRow)worksheet.getRow(xlRow);
                        excelcell = excelrow.createCell(xlCol);
                        excelcell.setCellStyle(headerStyle);
        excelcell.setCellValue(summArray[4]);
        worksheet.autoSizeColumn(xlCol);
        
        xlCol += 2;
        excelrow = (HSSFRow)worksheet.getRow(xlRow);
                        excelcell = excelrow.createCell(xlCol);
                        excelcell.setCellStyle(headerStyle);
        excelcell.setCellValue(summArray[5]);
        worksheet.autoSizeColumn(xlCol);
        xlCol += 2;
        excelrow = (HSSFRow)worksheet.getRow(xlRow);
                        excelcell = excelrow.createCell(xlCol);
                        excelcell.setCellStyle(headerStyle);
        excelcell.setCellValue(summArray[6]);
        worksheet.autoSizeColumn(xlCol);
        
        
    }
    
}
