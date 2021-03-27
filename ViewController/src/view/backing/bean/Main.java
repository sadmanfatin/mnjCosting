package view.backing.bean;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DecimalFormat;

import java.util.HashMap;
import java.util.StringTokenizer;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;
import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mnj.mfg.model.services.AppModuleImpl;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.input.RichSelectItem;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;
import oracle.adf.view.rich.component.rich.layout.RichPanelTabbed;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.LaunchPopupEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;

import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.adfinternal.view.faces.model.binding.FacesCtrlHierNodeBinding;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSet;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;

import oracle.jbo.server.ViewObjectImpl;
import oracle.jbo.uicli.binding.JUCtrlListBinding;

import oracle.jdbc.OracleTypes;

import org.apache.myfaces.trinidad.event.DisclosureEvent;
import org.apache.myfaces.trinidad.model.UploadedFile;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

import org.codehaus.groovy.tools.shell.Shell;

public class Main {
    private Object otherCharge;
    private Object labTestcharge;
    private Object print;
    private Object commission;
    private RichInputText smsQty;
    private RichInputText orderQty;
    private Object fabricFinance;
    private Object washFinance;
    private RichInputText cmMerchandiser;
    private RichInputText costingMOU;
    private Object trimFinance;
    private RichInputText cm;
    private RichInputText reflectedProfit;
    private RichInputText cost;
    private RichInputText finance;
    private RichInputText profit;
    private RichInputText costPerline;
    private RichInputText producPerLine;
    private RichTable itemTable;
    private RichInputText actualUnitPrice;
    private RichInputText addPriceMOQ;
    private RichInputText finalUnitPrice;
    private RichInputText consPerPcs;
    private RichInputText wasteInPercent;
    private RichInputText wasteInQty;
    private RichInputText consWithWaste;
    private RichInputText buffer;
    private RichInputText finalCons;
    private RichInputText costPerPcs;
    private RichInputText finalCostPerPcs;
    private RichInputText addDeductCost;
    private RichInputText projectionNo;
    private RichInputListOfValues productionUnit;
    private RichOutputText totalFinalCostPerPCS;
    private RichInputText dryCost;
    private RichInputText dryProfit;
    private RichInputText dryTotal;
    private RichInputText dryManualPrice;
    private RichInputText wetCost;
    private RichInputText wetProfit;
    private RichInputText wetTotal;
    private RichInputText wetManualPrice;
    private RichOutputText grandDryTotal;
    private RichOutputText grandWetTotal;
    private RichInputText foBWithOutComm;
    private RichTable subCostingTable;
    private RichSelectOneRadio selectRadio;
    private RichInputText headerID;
    private RichInputText addChargLC;
    private RichInputText addPriceFOB;
    private RichInputText addChrgPrcnt;
    private RichInputText uomConversion;
    private RichTable othersTable;
    private RichInputText testType;
    private RichInputText cmMerchDzn;
    private RichInputText targetPrice;
    private RichInputText difference;
    private RichInputText fabFinanceCost;
    private RichInputListOfValues prefixName;
    private RichInputText prefixCode;
    private RichInputText prodCostPerLine;
    private RichInputText sampleDocNo;
    private RichOutputText totalBpoQty;
    private RichOutputText tbAQty_Bind;
    private RichTable dryDetailsTable;
    private RichTable wetDetailsTable; 
    public int bpoTableBlock=0;    //  if set to 0 then sales order block , if 1 then create bpo block
    
    public static Row currentBpoRow;
    public static String currentBpo;
    
    RichPopup p1 ;
    private RichForm createOrderForm;
    private RichColumn remarkAction;
    private RichInputListOfValues buyerName;
    private RichSelectOneChoice seasonName;
    private UISelectItems seasonN;
    private RichInputText cons_per_pc_feb;
    private RichInputText cost_per_pcs_feb;
    private RichInputText act_unit_prc_feb;
    private RichTable febric_table;
    private RichInputText trimActualPrc;
    private RichInputText trimsConsPrs;
    private RichInputText trimsCostPerPcs;
    private RichInputText otherActualPrc;
    private RichInputText oteherConPcs;
    private RichInputText othersCosPerPc;
    private RichInputText productivity;
    private RichInputText styleEff;
    private RichInputText samValue;
    private RichInputText feb_buffer;
    private RichInputText trim_buffer;
    private RichInputText other_bufer;
    private RichInputText confirm_status;
    private RichTable othersTABLE;
    private RichTable trimsTABLE;
    private RichPanelTabbed panelTabbed;
    private RichTable fabricTABLE;
    private RichInputText fabricFinancePrice;
    private RichInputText fabricConForLine;
    private RichInputText article_value;
    static DecimalFormat format = new DecimalFormat("#");
    private RichInputText trimsFinance;
    private RichInputText washFinanceValue;
    private RichTable orderVsSaleTable;
    private RichTable poDetailsTable;
    private RichTable washCostTable;

    public Main() {
        
        
    }


    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void setOtherCharge(Object otherCharge) {
        this.otherCharge = otherCharge;

    }

    public Object getOtherCharge() {
        return otherCharge;
    }

    public void setLabTestcharge(Object labTestcharge) {
        this.labTestcharge = labTestcharge;
    }

    public Object getLabTestcharge() {
        return labTestcharge;
    }

    public void setPrint(Object print) {
        this.print = print;
    }

    public Object getPrint() {
        return print;
    }

    public void setCommission(Object commission) {
        this.commission = commission;
    }

    public Object getCommission() {
        return commission;
    }

    public void setSmsQty(RichInputText smsQty) {
        this.smsQty = smsQty;

    }

    public RichInputText getSmsQty() {
        return smsQty;
    }

    public void setOrderQty(RichInputText orderQty) {
        this.orderQty = orderQty;
    }

    public RichInputText getOrderQty() {
        return orderQty;
    }

    public void setFabricFinance(Object fabricFinance) {
        this.fabricFinance = fabricFinance;
    }

    public Object getFabricFinance() {
        return fabricFinance;
    }

    public void setWashFinance(Object washFinance) {
        this.washFinance = washFinance;
    }

    public Object getWashFinance() {
        return washFinance;
    }

    public void setCmMerchandiser(RichInputText cmMerchandiser) {
        this.cmMerchandiser = cmMerchandiser;
    }

    public RichInputText getCmMerchandiser() {
        return cmMerchandiser;
    }

    public void setCostingMOU(RichInputText costingMOU) {
        this.costingMOU = costingMOU;
    }

    public RichInputText getCostingMOU() {
        return costingMOU;
    }

    public void setTrimFinance(Object trimFinance) {
        this.trimFinance = trimFinance;
    }

    public Object getTrimFinance() {
        return trimFinance;
    }


    public void setCm(RichInputText cm) {
        this.cm = cm;
    }

    public RichInputText getCm() {
        return cm;
    }

    public void setReflectedProfit(RichInputText reflectedProfit) {
        this.reflectedProfit = reflectedProfit;
    }

    public RichInputText getReflectedProfit() {
        return reflectedProfit;
    }

    public void setCost(RichInputText cost) {
        this.cost = cost;
    }

    public RichInputText getCost() {
        return cost;
    }

    public void setFinance(RichInputText finance) {
        this.finance = finance;
    }

    public RichInputText getFinance() {
        return finance;
    }

    public void setProfit(RichInputText profit) {
        this.profit = profit;
    }

    public RichInputText getProfit() {
        return profit;
    }

    public void setCostPerline(RichInputText costPerline) {
        this.costPerline = costPerline;
    }

    public RichInputText getCostPerline() {
        return costPerline;
    }

    public void setProducPerLine(RichInputText producPerLine) {
        this.producPerLine = producPerLine;
    }

    public RichInputText getProducPerLine() {
        return producPerLine;
    }

    public void setItemTable(RichTable itemTable) {
        this.itemTable = itemTable;
    }

    public RichTable getItemTable() {
        return itemTable;
    }

//    public void setFinanceCalc() {
//
//        //  try {
//
//        double fabric = 0.00, fobVal = 0.00, trim = 0.00, finalCostPerPcsVal =
//            0.00, washTotalVal = 0.00, financialCostValue = 0.00, totalCMVal =
//            0.00, refelectedVal = 0, service = 0.0;
//        DCBindingContainer bindings =
//            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
//        DCIteratorBinding dcIteratorBindings =
//            bindings.findIteratorBinding("MnjMfgPrecostingItemDView1Iterator");
//        // Get all the rows of a iterator
//        Row[] rows = dcIteratorBindings.getAllRowsInRange();
//        for (Row row : rows) {
//            
//            /***************************************/
//            refreshFabricFinance(row);
//            /***************************************/
//            
//            oracle.jbo.domain.Number prefix =
//                (Number)row.getAttribute("ItemPrefix"); //ItemPrefix
//            try {
//                finalCostPerPcsVal =
//                        Double.parseDouble(row.getAttribute("FinalCostPerPcs").toString());
//            } catch (Exception e) {
//                ;
//            }
//            // prefixVal = Integer.parseInt(prefix);
//            if (prefix.intValue() >= 11 && prefix.intValue() <= 12) {
//                fabric = fabric + finalCostPerPcsVal;
//                finalCostPerPcsVal = 0.00;
//            } else if ((prefix.intValue() > 12 && prefix.intValue() <= 36) ||prefix.intValue()==00) {
//                trim = trim + finalCostPerPcsVal;
//                finalCostPerPcsVal = 0.00;
//            }
//            else if (prefix.intValue()==55){
//                service = service + finalCostPerPcsVal;
//            }
//        } //end of for each loop
//
//        double fabricFinanceVal = MyMath.numeric3(getFabricFinance());
//        double washFinanceVal = MyMath.numeric3(getWashFinance());
//        double trimFinanceVal = MyMath.numeric3(getTrimFinance());
//
//        washTotalVal = getSelectedDryTotal() + getSelectedWetTotal();
//        //            financialCostValue =
//        //                    ((fabric * 0.06) - (fabric * (fabricFinanceVal / 100))) +
//        //                    ((trim * 0.06) - (trim * (trimFinanceVal / 100))) +
//        //                    ((washTotalVal * 0.01) -
//        //                     (washTotalVal * (washFinanceVal / 100)));
//        /**
//         * Changed on 15-NOV-15 (Parvez/Bilal)
//         **************************************/
//        financialCostValue =
//                ((fabric * getPOCFinanceCost("FABRIC")) - (fabric * (fabricFinanceVal / 100))) +
//                ((trim * getPOCFinanceCost("TRIMS")) - (trim * (trimFinanceVal / 100))) +
//                ((washTotalVal * getPOCFinanceCost("WASH")) -
//                 (washTotalVal * (washFinanceVal / 100)));
//
//        totalCMVal =
//                financialCostValue + MyMath.numeric(getCost()) + MyMath.numeric(getProfit());
//
//        finance.setValue(MyMath.toNumber(MyMath.round(financialCostValue)));
//        AdfFacesContext.getCurrentInstance().addPartialTarget(finance);
//
////        cm.setValue(MyMath.toNumber(MyMath.round(totalCMVal)));
////        AdfFacesContext.getCurrentInstance().addPartialTarget(cm);
//
//        refelectedVal =
//                MyMath.numeric(getCmMerchandiser()) - MyMath.numeric(getCost()) -
//                financialCostValue;
//
//        reflectedProfit.setValue(MyMath.toNumber(MyMath.round(refelectedVal)));
//        AdfFacesContext.getCurrentInstance().addPartialTarget(reflectedProfit);
//
//        System.out.println(fabric+"------------------------FOBS value print ------------>"+trim);
//        setFOBValues(trim, fabric, washTotalVal, totalCMVal, service);
//
//
//        //        } catch (Exception e) {
//        //            ;
//        //        }
//
//    }
    /***********************************
     * ********Newly added on 22-MAR-16****
     * *****************************************************************************************************/
    public void refreshFabricFinance(Row r ){
        
        int preficCode = (int)MyMath.numeric3(r.getAttribute("ItemPrefix"));
        double fabFinanceCostVal = 0.0;
        
        double costPerPc = MyMath.numeric3(r.getAttribute("CostPerPcs"));
        double addDeduct = MyMath.numeric3(r.getAttribute("AddDeductCost"));
        
        double finalCostPerPcsVal =costPerPc+addDeduct;
        
        System.out.println(preficCode+"New calculation ------------------------------->"+finalCostPerPcsVal);
        
        if(preficCode== 11 || preficCode== 12) {
            fabFinanceCostVal = finalCostPerPcsVal * (MyMath.numeric3(getFabricFinance())/100);   
            
            r.setAttribute("FabFinanceCost",fabFinanceCostVal);
            r.setAttribute("FinalCostPerPcs",(finalCostPerPcsVal+fabFinanceCostVal));
            
        }
        
        
    }
    /**************************************************************************
     * ****************************************************************************************************/

    public void setFOBValues(double trim, double fabric, double washTotalVal,
                             double totalCMVal, double service) {

       
        // Get the Selected Row key set iterator

        //((FOB*Comission(%))*Order Qty+(FOB*Comission(%))*Sample Qty)/Order Qty
       
        double otherVal = MyMath.numeric3(getOtherCharge()) + MyMath.numeric3(getLabTestcharge()) + service;

        /***********************changed by Parvez/Bilal 11-11-15*****************************************/
        double fob = 0.0;
        if (MyMath.numeric(getProfit()) > 0 ){
        
             fob = trim + fabric + washTotalVal + totalCMVal + otherVal;
        }
        else {
             fob = trim + fabric + washTotalVal + MyMath.numeric(getCmMerchandiser()) + otherVal;
        }

        /*************************************************************************************************/

        double commVal = MyMath.numeric3(getCommission());
        double fobWitcommVal = (fob * commVal / 100) + fob;
        double orderQtyVal = MyMath.numeric(getOrderQty());
        double smsQtyVal = MyMath.numeric3(getSMSQtyFromTable());
        double fobWithSmsVal = 0.00;
        double targetPrice = MyMath.numeric(getTargetPrice());
        
        
        try {
            if (orderQtyVal > 0)
            fobWithSmsVal =((fobWitcommVal) * orderQtyVal + (fobWitcommVal) * smsQtyVal) /orderQtyVal;
            else fobWithSmsVal = 0;
        } catch (Exception e) {
           
           fobWithSmsVal =0.0 ;
            System.out.println("Exception value --===================->"+fobWithSmsVal);
        }
    
        oracle.adf.view.rich.component.UIXTable table = getSubCostingTable();
        java.util.Iterator selectionIt = table.getSelectedRowKeys().iterator();

        while (selectionIt.hasNext()) {
            Object rowKey = selectionIt.next();
            table.setRowKey(rowKey);
            int index = table.getRowIndex();
            FacesCtrlHierNodeBinding row =
                (FacesCtrlHierNodeBinding)table.getRowData(index);
                Row selectedRow = row.getRow();
            selectedRow.setAttribute("FobWitoutComm",
                                     MyMath.toNumber(MyMath.round(fob)));
            if(fobWithSmsVal > fobWitcommVal){
            
                selectedRow.setAttribute("FobWithComm",
                                     MyMath.toNumber(MyMath.round(fobWithSmsVal)));
                selectedRow.setAttribute("Difference",
                                     MyMath.toNumber(MyMath.round(fobWithSmsVal)- targetPrice));
            }else {
                
                selectedRow.setAttribute("FobWithComm",
                                     MyMath.toNumber(MyMath.round(fobWitcommVal)));
                selectedRow.setAttribute("Difference",
                                     MyMath.toNumber(MyMath.round(fobWitcommVal)- targetPrice));
        
        
            }
            
            
            selectedRow.setAttribute("Fob",
                                     MyMath.toNumber(MyMath.round(fobWitcommVal)));
            selectedRow.setAttribute("FobWithSmsSample",
                                     MyMath.toNumber(MyMath.round(fobWithSmsVal)));
            selectedRow.setAttribute("FabricCost",
                                     MyMath.toNumber(MyMath.round(fabric)));
            selectedRow.setAttribute("TrimCost",
                                     MyMath.toNumber(MyMath.round(trim)));
            selectedRow.setAttribute("WashCost",
                                     MyMath.toNumber(MyMath.round(washTotalVal)));
            selectedRow.setAttribute("Others",
                                     MyMath.toNumber(otherVal));
            
            if (MyMath.numeric(getProfit()) > 0 ){
            selectedRow.setAttribute("Cm",
                                     MyMath.toNumber(MyMath.round(totalCMVal)));
            
            cmMerchandiser.setValue(MyMath.toNumber(MyMath.round(totalCMVal)));
            cmMerchDzn.setValue(MyMath.toNumber(MyMath.round(totalCMVal*12)));
           // Sabih 
            }
            else {
                selectedRow.setAttribute("Cm", MyMath.toNumber(MyMath.numeric(getCmMerchandiser())));
            
            cmMerchandiser.setValue(MyMath.toNumber(MyMath.numeric(getCmMerchandiser())));
            cmMerchDzn.setValue(MyMath.toNumber(MyMath.numeric(getCmMerchandiser())*12));
            
            }
        }//end of while loop
        
        //AdfFacesContext.getCurrentInstance().addPartialTarget(subCostingTable);

    }


//    public double getSelectedDryTotal() {
//
//        DCBindingContainer bindings =
//            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
//        DCIteratorBinding dcIteratorBindings =
//            bindings.findIteratorBinding("DryDetailVO1Iterator");
//        // Get all the rows of a iterator
//        double total = 0.00, val = 0.00, val2 = 0.0;
//        String UoM;
//        ;
//        Row[] rows = dcIteratorBindings.getAllRowsInRange();
//        for (Row row : rows) {
//            try { //ManualPrice
//                val  = Double.parseDouble(String.valueOf(row.getAttribute("Total")));
//                val2 = Double.parseDouble(String.valueOf(row.getAttribute("ManualPrice")));
//                UoM  = row.getAttribute("CostUom").toString();
//       // UoM is Added By Sabih on 07Feb2017         
//                if (UoM.equals("DOZ"))
//                {
//                val  = val/12;
//                val2 = val2/12;
//                };
//                       
//                
//            } catch (Exception e) {
//                
//                val2 = 0;
//                
//                UoM  = row.getAttribute("CostUom").toString();
//                
//                if (UoM.equals("DOZ"))
//                {
//                val  = val/12;
//                val2 = val2/12;
//                };
//                 
//            }
//            if (val > 0)
//                total += val;
//            else
//                total += val2;
//
//        } //end of for each loop
//
//        return total;
//
//    }
//
//    public double getSelectedWetTotal() {
//
//        DCBindingContainer bindings =
//            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
//        DCIteratorBinding dcIteratorBindings =
//            bindings.findIteratorBinding("MnjMfgPrecostingWetDView1Iterator");
//        // Get all the rows of a iterator
//
//        double total = 0.00, val = 0.00, val2 = 0.0;
//        String UoM;
//        Row[] rows = dcIteratorBindings.getAllRowsInRange();
//        for (Row row : rows) {
//
//            try { //ManualPrice
//                val  =Double.parseDouble(String.valueOf(row.getAttribute("NewTotalVal")));
//                val2 =Double.parseDouble(String.valueOf(row.getAttribute("ManualPrice")));
//                UoM  = row.getAttribute("CostUom").toString();
//                    // UoM is Added By Sabih on 07Feb2017                  
//                if (UoM.equals("DOZ"))
//                {
//                val  = val/12;
//                val2 = val2/12;
//                };
//                       
//                
//                } catch (Exception e) {
//                
//                val2 = 0;
//                
//                UoM  = row.getAttribute("CostUom").toString();
//                
//                if (UoM.equals("DOZ"))
//                {
//                val  = val/12;
//                val2 = val2/12;
//                };
//                 
//            }
//            if (val > 0)
//                total += val;
//            else
//                total += val2;
//
//        } //end of for each loop
//
//        return total;
//
//    }
    

        
    public double getSelectedDryTotal(Row lineRow) {
                
//            DCBindingContainer bindings =
//                (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
//            DCIteratorBinding dcIteratorBindings =
//                bindings.findIteratorBinding("DryDetailVO1Iterator");
            
            // Get all the rows of a iterator
            
          RowSet  dryRowSet = (RowSet)lineRow.getAttribute("DryDetailVO");
            Row[] rows  = dryRowSet.getAllRowsInRange();
            
            double total = 0.00, val = 0.00, val2 = 0.0;
            
           
            
            for (Row row : rows) {
                try { //ManualPrice
                    val =Double.parseDouble(String.valueOf(row.getAttribute("Total")));
                    val2 =Double.parseDouble(String.valueOf(row.getAttribute("ManualPrice")));
                } catch (Exception e) {
                    val2 = 0;
                }
                if (val > 0)
                    total += val;
                else
                    total += val2;
            } //end of for each loop

            return total;

        }   

    public double getSelectedWetTotal(Row lineRow) {
           

          
          RowSet  wetRowSet = (RowSet)lineRow.getAttribute("MnjMfgPrecostingWetDView");
          Row[] rows = wetRowSet.getAllRowsInRange();
    
          double total = 0.00, val = 0.00, val2 = 0.0;

          
    //        System.out.println("====================== dcIteratorBindings.getAllRowsInRange().length  "  + dcIteratorBindings.getAllRowsInRange().length );
    //        System.out.println("====================== dryRowSet.getAllRowsInRange().length "  + wetRowSet.getAllRowsInRange().length );
    //        System.out.println("======================  am.MnjMfgPrecostingWetDView1().getAllRowsInRange().length "  + am.getMnjMfgPrecostingWetDView1().getAllRowsInRange().length );
          
          
          for (Row row : rows) {

              try { //ManualPrice
                  val  =Double.parseDouble(String.valueOf(row.getAttribute("NewTotalVal")));
                  val2 =Double.parseDouble(String.valueOf(row.getAttribute("ManualPrice")));
              } catch (Exception e) {
                  val2 = 0;
              }
              if (val > 0)
                  total += val;
              else
                  total += val2;

          } //end of for each loop

          return total;

      }

    public void setActualUnitPrice(RichInputText actualUnitPrice) {
        this.actualUnitPrice = actualUnitPrice;
    }

    public RichInputText getActualUnitPrice() {
        return actualUnitPrice;
    }

    public void setAddPriceMOQ(RichInputText addPriceMOQ) {
        this.addPriceMOQ = addPriceMOQ;
    }

    public RichInputText getAddPriceMOQ() {
        return addPriceMOQ;
    }

    public void setFinalUnitPrice(RichInputText finalUnitPrice) {
        this.finalUnitPrice = finalUnitPrice;
    }

    public RichInputText getFinalUnitPrice() {
        return finalUnitPrice;
    }


    public void commonListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here..
       // refreshValues();
        //        FacesContext facesContext = FacesContext.getCurrentInstance();
        //            UIViewRoot root = facesContext.getViewRoot();
        //        System.out.println("Component Id in common listener ---> "+valueChangeEvent.getComponent().getId());
        //
        //        RichInputText inputText = (RichInputText)root.findComponent(valueChangeEvent.getComponent().getId());
        //       // inputText.setValue(valueChangeEvent);
        //        if(valueChangeEvent.getNewValue() == null){
        //        inputText.setSubmittedValue(null);
        //        inputText.resetValue();
        //        }

    }
    
    public void cmMerchDznLsitener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...            
        double merchDzn = MyMath.numeric(getCmMerchDzn());
        double result = 0.0;
        if (merchDzn != 0){
            result = merchDzn /12;  
        }
        cmMerchandiser.setValue(MyMath.toNumber(result));
        AdfFacesContext.getCurrentInstance().addPartialTarget(cmMerchandiser);
    }
    
    public void setMerchValues(Row lineRow){
            
        double merchPc=0 ;
       // merchPc =   MyMath.numeric(getCmMerchandiser());      
        
       try {
            merchPc = Double.parseDouble(lineRow.getAttribute("CmMerchand").toString()  );      
        } catch (Exception e) {
            // TODO: Add catch code
            merchPc=0.0;
           // e.printStackTrace();
        }
         double result = 0.0;       
        result = merchPc * 12;
        
        
      //  cmMerchDzn.setValue(MyMath.toNumber(result));
        am.getMnjMfgPrecostingHView1().getCurrentRow().setAttribute("CmMerchandiserD", MyMath.toNumber(result));
        AdfFacesContext.getCurrentInstance().addPartialTarget(cmMerchDzn);
          
    }

    
    //mt test.....................13-jun

//    public void refreshValues() {
//
//        setOthersValues();
//        setMerchValues();
//
//        double actualUnitPriceVal = MyMath.numeric(getActualUnitPrice());
//              
//        double addPriceMoqVal = MyMath.numeric(getAddPriceMOQ());
//
//        double addChrgLCVal = MyMath.numeric(getAddChargLC());
//        double addChrgPrcntVal = MyMath.numeric(getAddChrgPrcnt());
//        double addPriceFOBVal = MyMath.numeric(getAddPriceFOB());
//        double lcCalculated = 0.00;
//        double actualUnitPriceCalc = 0.00;
//
//        try {
//            if (MyMath.numeric(getUomConversion()) != 0) {
//                addChrgLCVal =
//                        addChrgLCVal / MyMath.numeric(getUomConversion());
//                actualUnitPriceCalc =
//                        actualUnitPriceVal / MyMath.numeric(getUomConversion());
//                addPriceFOBVal = addPriceFOBVal / MyMath.numeric(getUomConversion());
//                addPriceMoqVal =addPriceMoqVal /  MyMath.numeric(getUomConversion());
//            }
//        } catch (Exception e) {
//            ;
//
//        }
//        
//        actualUnitPriceCalc =
//                (actualUnitPriceCalc != 0 ? actualUnitPriceCalc : actualUnitPriceVal);
//
//        try {
//            lcCalculated =
//                    (addChrgLCVal != 0 ? addChrgLCVal : ((addChrgPrcntVal) /100 *actualUnitPriceCalc));
//        } catch (Exception e) {
//            ;
//        }
//
//        
//
//        double finalUnitPriceVal =
//            MyMath.round(actualUnitPriceCalc + addPriceMoqVal + lcCalculated +
//                         +addPriceFOBVal);
//
//        //if (finalUnitPriceVal > 0)
//        finalUnitPrice.setValue(MyMath.toNumber(MyMath.roundTo4(finalUnitPriceVal)));
//        AdfFacesContext.getCurrentInstance().addPartialTarget(finalUnitPrice);
//
//        double consperPcsVal = MyMath.numeric(getConsPerPcs());
//        double wasteInPercVal = MyMath.numeric(getWasteInPercent());
//
//        double wasteInQtyVal = consperPcsVal * (wasteInPercVal / 100);
//        // if (wasteInQtyVal > 0)
//        wasteInQty.setValue(MyMath.toNumber(wasteInQtyVal));
//        AdfFacesContext.getCurrentInstance().addPartialTarget(wasteInQty);
//
//        double consWithWasteVal = consperPcsVal * (1 + wasteInPercVal / 100);
//        //  if (consWithWasteVal > 0)
//        consWithWaste.setValue(MyMath.toNumber(consWithWasteVal));
//        AdfFacesContext.getCurrentInstance().addPartialTarget(consWithWaste);
//
//        double bufferVal = MyMath.numeric(getBuffer());
//        double finalConsVal = bufferVal + consWithWasteVal;
//
//        // if (finalConsVal > 0)
//        finalCons.setValue(MyMath.toNumber(MyMath.round(finalConsVal)));
//        AdfFacesContext.getCurrentInstance().addPartialTarget(finalCons);
//
//
//        double costPerPcsVal = finalUnitPriceVal * finalConsVal;
//        //  if (costPerPcsVal > 0)
//       // costPerPcsVal = Math.ceil((double)MyMath.roundTo3(costPerPcsVal)) ;
//        costPerPcs.setValue(MyMath.toNumber(MyMath.roundUp(costPerPcsVal)));
//        AdfFacesContext.getCurrentInstance().addPartialTarget(costPerPcs);
//
//        double addDeductCostVal = MyMath.numeric(getAddDeductCost());
//        double finalCostPerPcsVal = costPerPcsVal + addDeductCostVal;
//        
//        
//        double fabFinanceCostVal =0.0;
//        
//        if(MyMath.numeric(getPrefixCode())== 11 || MyMath.numeric(getPrefixCode())== 12) {
//            fabFinanceCostVal = finalCostPerPcsVal * (MyMath.numeric3(getFabricFinance())/100);
//        }
//        fabFinanceCost.setValue(MyMath.toNumber(fabFinanceCostVal));
//        AdfFacesContext.getCurrentInstance().addPartialTarget(fabFinanceCost);
//        
//        // refreshTotal(finalCostPerPcsVal);
//        // if (finalCostPerPcsVal > 0)                   
//        finalCostPerPcs.setValue(MyMath.toNumber(MyMath.roundUp(finalCostPerPcsVal + fabFinanceCostVal)));
//     //   finalCostPerPcs.setValue(MyMath.toNumber(MyMath.roundUp(finalCostPerPcsVal )));
//        AdfFacesContext.getCurrentInstance().addPartialTarget(finalCostPerPcs);
//
//   // CHANGE bY BILAL 03MAR17    double costPerLineVal = MyMath.numeric(getCostPerline());
//        double costPerLineVal = MyMath.numeric(getProdCostPerLine());
//        System.out.println("===================costperline"+costPerLineVal);
//        double prodPerLineVal = MyMath.numeric(getProducPerLine());
//        System.out.println("===================prodPerLineVal"+prodPerLineVal);
//        double costVal = 0.00;
//        if (costPerLineVal != 0 && prodPerLineVal != 0) {
//
//            costVal = costPerLineVal / prodPerLineVal;
//        }
//        
//        // if (costVal > 0)
//        System.out.println("===================costVal"+costVal);
//        cost.setValue(MyMath.toNumber(MyMath.round(costVal)));
//        
//        AdfFacesContext.getCurrentInstance().addPartialTarget(cost);
//        System.out.println("===================cost"+cost);
//
//
//        //        double dryCostVal = MyMath.numeric(getDryCost());
//        //        double dryProfitVal = MyMath.numeric(getDryProfit());
//        //        double dryTotalVal = dryCostVal + dryProfitVal;
//        //        // refreshGrandDryTotal(dryTotalVal);
//        //        if (dryTotalVal > 0)
//        //            dryTotal.setValue(MyMath.toNumber(MyMath.round(dryTotalVal)));
//        //        AdfFacesContext.getCurrentInstance().addPartialTarget(dryTotal);
//
//
//        //        double wetCostVal = MyMath.numeric(getWetCost());
//        //        double wetProfitVal = MyMath.numeric(getWetProfit());
//        //        double wetTotalVal = wetCostVal + wetProfitVal;
//        //        // refreshWetGrandTotal(wetTotalVal);
//        //        if (wetTotalVal > 0)
//        //            wetTotal.setValue(MyMath.toNumber(MyMath.round(wetTotalVal)));
//        //        AdfFacesContext.getCurrentInstance().addPartialTarget(wetTotal);
//
//        setFinanceCalc();
//
//
//    }


    public void setConsPerPcs(RichInputText consPerPcs) {
        this.consPerPcs = consPerPcs;
    }

    public RichInputText getConsPerPcs() {
        return consPerPcs;
    }

    public void setWasteInPercent(RichInputText wasteInPercent) {
        this.wasteInPercent = wasteInPercent;
    }

    public RichInputText getWasteInPercent() {
        return wasteInPercent;
    }

    public void setWasteInQty(RichInputText wasteInQty) {
        this.wasteInQty = wasteInQty;
    }

    public RichInputText getWasteInQty() {
        return wasteInQty;
    }

    public void setConsWithWaste(RichInputText consWithWaste) {
        this.consWithWaste = consWithWaste;
    }

    public RichInputText getConsWithWaste() {
        return consWithWaste;
    }

    public void setBuffer(RichInputText buffer) {
        this.buffer = buffer;
    }

    public RichInputText getBuffer() {
        return buffer;
    }

    public void setFinalCons(RichInputText finalCons) {
        this.finalCons = finalCons;
    }

    public RichInputText getFinalCons() {
        return finalCons;
    }

    public void setCostPerPcs(RichInputText costPerPcs) {
        this.costPerPcs = costPerPcs;
    }

    public RichInputText getCostPerPcs() {
        return costPerPcs;
    }

    public void setFinalCostPerPcs(RichInputText finalCostPerPcs) {
        this.finalCostPerPcs = finalCostPerPcs;
    }

    public RichInputText getFinalCostPerPcs() {
        return finalCostPerPcs;
    }

    public void setAddDeductCost(RichInputText addDeductCost) {
        this.addDeductCost = addDeductCost;
    }

    public RichInputText getAddDeductCost() {
        return addDeductCost;
    }

    public void setProjectionNo(RichInputText projectionNo) {
        this.projectionNo = projectionNo;
    }

    public RichInputText getProjectionNo() {
        return projectionNo;
    }

    public void setProductionUnit(RichInputListOfValues productionUnit) {
        this.productionUnit = productionUnit;
    }

    public RichInputListOfValues getProductionUnit() {
        return productionUnit;
    }




    public void productionCalculation(Row lineRow) {
       // Add event code here...
       String unit = null;
      
         
           try {
               unit = am.getMnjMfgPrecostingHView1().getCurrentRow().getAttribute("ProductionUnit").toString();
               
           } catch (Exception e) {
           // TODO: Add catch code
           ;
           }  
       
       //System.out.println("Unit----->>"+unit);
       
       try {
           // mt 10-jun   // producPerLine.setValue(MyMath.toNumber(MyMath.round(getProdPerLineFromProc(unit, getProjectionNo().getValue()))));
           lineRow.setAttribute("ProdCostLinePerH",MyMath.toNumber(getProdCostUnitWise(unit)));
           //  prodCostPerLine.setValue(MyMath.toNumber(getProdCostUnitWise(unit)));
           // mt 10-jun    // AdfFacesContext.getCurrentInstance().addPartialTarget(producPerLine);
           AdfFacesContext.getCurrentInstance().addPartialTarget(prodCostPerLine);
       } catch (Exception e) {
           ;
       }
      // refreshValues();
    }



    public void productionListener(ValueChangeEvent valueChangeEvent) {
            // Add event code here...
            System.out.println("Unit Lsitener");
            String unit = null;
            try {
               System.out.println("Production Unit------->>"+getProductionUnit().getValue().toString());
                unit = valueChangeEvent.getNewValue().toString();
            } catch (Exception e) {
                ;
            }
            
            RowSet lineRowSet = (RowSet)am.getMnjMfgPrecostingHView1().getCurrentRow().getAttribute("MnjMfgPrecostingLView");
            Row lineRow ;
            while(lineRowSet.hasNext()){
                lineRow = lineRowSet.next();
                
                
                lineRow.setAttribute("ProdCostLinePerH",MyMath.toNumber(getProdCostUnitWise(unit)));
            }

          
            AdfFacesContext.getCurrentInstance().addPartialTarget(prodCostPerLine);

           //refreshValues();
        }

    public double getProdCostUnitWise(String unit) {

        OperationBinding operationBinding =
            executeOperation("getProdCostUnitWise");
        operationBinding.getParamsMap().put("unitName", unit);
        operationBinding.execute();
        
        Object methodReturnValue = operationBinding.getResult();
        double value = Double.parseDouble(methodReturnValue.toString());
        System.out.println("...........---------unit cost="+value);
        return value;
       
    }
    
    //................................test smv & Style eff-----------------------------//
    
    public double SMV() {
      String unitName = null;
            ViewObject hvo = am.getMnjMfgPrecostingHView1();
            try {
                unitName= hvo.getCurrentRow().getAttribute("ProductionUnit").toString();
            } catch (Exception e) {
                ;
            }
         //end of if condition
         String date=null;
         try {
             date= hvo.getCurrentRow().getAttribute("CreationDate").toString();
         } catch (Exception e) {
             ;
         }
          //end of if condition
         
         System.out.println(" creation date is ----------------------->"+date);
        
        
        double srno = 0;
        String stmt = "BEGIN :1 := mnj_get_preccost_smv(:2,:3); end;";
        java.sql.CallableStatement cs =am.getDBTransaction().createCallableStatement(stmt, 1);
        try {
            cs.registerOutParameter(1, OracleTypes.NUMBER);
            cs.setString(2, unitName);
            cs.setString(3,date);
            cs.execute();
            srno = cs.getDouble(1);
            cs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return srno;
    }
    
    public double FactoryAvgEff() {
      String unitName = null;
            ViewObject hvo = am.getMnjMfgPrecostingHView1();
            try {
                unitName= hvo.getCurrentRow().getAttribute("ProductionUnit").toString();
            } catch (Exception e) {
                ;
            }
        String date=null;
        try {
            date= hvo.getCurrentRow().getAttribute("CreationDate").toString();
        } catch (Exception e) {
            ;
        }
         //end of if condition
      
      System.out.println(" creation date is ----------------------->"+date);
        double srno = 0;
        String stmt = "BEGIN :1 :=mnj_get_Factory_avg_eff(:2,:3); end;";
        java.sql.CallableStatement cs =am.getDBTransaction().createCallableStatement(stmt, 1);
        try {
            cs.registerOutParameter(1, OracleTypes.NUMBER);
            cs.setString(2, unitName);
            cs.setString(3,date);
            cs.execute();
            srno = cs.getDouble(1);
            cs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return srno;
    }
    
    
    


    public double getProdPerLineFromProc(String name, Object projNo) {


        OperationBinding operationBinding =
            executeOperation("getCostProdPerLine");


        //        BindingContext bctx = BindingContext.getCurrent();
        //        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        //        OperationBinding operationBinding =
        //            bindings.getOperationBinding("getCostProdPerLine");


        operationBinding.getParamsMap().put("name", name);
        operationBinding.getParamsMap().put("projectionNo", projNo);


        //invoke method
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            System.out.println("if errors-->");
            // List errors = operationBinding.getErrors();
        }
        //optional
        Object methodReturnValue = operationBinding.getResult();
        double value = Double.parseDouble(methodReturnValue.toString());
        return value;
    }

    public void setTotalFinalCostPerPCS(RichOutputText totalFinalCostPerPCS) {
        this.totalFinalCostPerPCS = totalFinalCostPerPCS;
    }

    public RichOutputText getTotalFinalCostPerPCS() {
        return totalFinalCostPerPCS;
    }

    public void refreshTotal(double finalCostPerPcsVal) {

        if (MyMath.numeric(getFinalCostPerPcs()) > finalCostPerPcsVal) {
            //totalFinalCostPerPCS.setValue(arg0);
            double newTotalVale =
                MyMath.numeric2(getTotalFinalCostPerPCS()) - (MyMath.numeric(getFinalCostPerPcs()) -
                                                              finalCostPerPcsVal);
            totalFinalCostPerPCS.setValue(MyMath.toNumber(newTotalVale));
            AdfFacesContext.getCurrentInstance().addPartialTarget(totalFinalCostPerPCS);
        } else {
            double newTotalVale =
                MyMath.numeric2(getTotalFinalCostPerPCS()) + (finalCostPerPcsVal -
                                                              MyMath.numeric(getFinalCostPerPcs()));
            totalFinalCostPerPCS.setValue(MyMath.toNumber(newTotalVale));
            AdfFacesContext.getCurrentInstance().addPartialTarget(totalFinalCostPerPCS);
        }
    } //end of method


    public void setDryCost(RichInputText dryCost) {
        this.dryCost = dryCost;
    }

    public RichInputText getDryCost() {
        return dryCost;
    }

    public void setDryProfit(RichInputText dryProfit) {
        this.dryProfit = dryProfit;
    }

    public RichInputText getDryProfit() {
        return dryProfit;
    }

    public void setDryTotal(RichInputText dryTotal) {
        this.dryTotal = dryTotal;
    }

    public RichInputText getDryTotal() {
        return dryTotal;
    }

    public void setDryManualPrice(RichInputText dryManualPrice) {
        this.dryManualPrice = dryManualPrice;
    }

    public RichInputText getDryManualPrice() {
        return dryManualPrice;
    }

    public void setWetCost(RichInputText wetCost) {
        this.wetCost = wetCost;
    }

    public RichInputText getWetCost() {
        return wetCost;
    }

    public void setWetProfit(RichInputText wetProfit) {
        this.wetProfit = wetProfit;
    }

    public RichInputText getWetProfit() {
        return wetProfit;
    }

    public void setWetTotal(RichInputText wetTotal) {
        this.wetTotal = wetTotal;
    }

    public RichInputText getWetTotal() {
        return wetTotal;
    }

    public void setWetManualPrice(RichInputText wetManualPrice) {
        this.wetManualPrice = wetManualPrice;
    }

    public RichInputText getWetManualPrice() {
        return wetManualPrice;
    }

    public void refreshGrandDryTotal(double dryTotalVal) {

        if (MyMath.numeric(getDryTotal()) > dryTotalVal) {
            //totalFinalCostPerPCS.setValue(arg0);
            double newTotalVale =
                MyMath.numeric2(getGrandDryTotal()) - (MyMath.numeric(getDryTotal()) -
                                                       dryTotalVal);
            grandDryTotal.setValue(MyMath.toNumber(newTotalVale));
            AdfFacesContext.getCurrentInstance().addPartialTarget(grandDryTotal);
        } else {
            double newTotalVale =
                MyMath.numeric2(getGrandDryTotal()) + (dryTotalVal -
                                                       MyMath.numeric(getDryTotal()));
            grandDryTotal.setValue(MyMath.toNumber(newTotalVale));
            AdfFacesContext.getCurrentInstance().addPartialTarget(grandDryTotal);
        }

    }

    public void refreshWetGrandTotal(double wetTotalVal) {

        if (MyMath.numeric(getWetTotal()) > wetTotalVal) {
            //totalFinalCostPerPCS.setValue(arg0);
            double newTotalVale =
                MyMath.numeric2(getGrandWetTotal()) - (MyMath.numeric(getWetTotal()) -
                                                       wetTotalVal);
            grandWetTotal.setValue(MyMath.toNumber(newTotalVale));
            AdfFacesContext.getCurrentInstance().addPartialTarget(grandWetTotal);
        } else {
            double newTotalVale =
                MyMath.numeric2(getGrandWetTotal()) + (wetTotalVal -
                                                       MyMath.numeric(getWetTotal()));
            grandWetTotal.setValue(MyMath.toNumber(newTotalVale));
            AdfFacesContext.getCurrentInstance().addPartialTarget(grandWetTotal);
        }

    }

    public void setGrandDryTotal(RichOutputText grandDryTotal) {
        this.grandDryTotal = grandDryTotal;
    }

    public RichOutputText getGrandDryTotal() {
        return grandDryTotal;
    }

    public void setGrandWetTotal(RichOutputText grandWetTotal) {
        this.grandWetTotal = grandWetTotal;
    }

    public RichOutputText getGrandWetTotal() {
        return grandWetTotal;
    }

    public void setFoBWithOutComm(RichInputText foBWithOutComm) {
        this.foBWithOutComm = foBWithOutComm;
    }

    public RichInputText getFoBWithOutComm() {
        return foBWithOutComm;
    }

    public void setSubCostingTable(RichTable subCostingTable) {
        this.subCostingTable = subCostingTable;
    }

    public RichTable getSubCostingTable() {
        return subCostingTable;
    }

    public String refershButton() {
        
        String checkMendatoryFieldsStatus = checkMendatoryFields();
        
        if(checkMendatoryFieldsStatus != "ok" ){
          //  System.out.println("-----------  checkMendatoryFieldsStatus != \"ok\" ");            
            this.showMessage(checkMendatoryFieldsStatus , "error");
            System.out.println(am.getMnjMfgPrecostingLView1().getCurrentRow().getAttribute("FabricDesc")); 
      
            return null ;
        }
        
        ViewObject headerVo = am.getMnjMfgPrecostingHView1();
        ViewObject lineVo = am.getMnjMfgPrecostingLView1();
        RowSet  lineRowSet = (RowSet)headerVo.getCurrentRow().getAttribute("MnjMfgPrecostingLView") ;
        
    
        //  System.out.println("09090909090909090  currentLineId  "+   lineVo.getCurrentRow().getAttribute("LineId"));
        Row lineRow;
        while ( lineRowSet.hasNext()) {
           
               lineRow  = lineRowSet.next();
             //  System.out.println("-----------------  lineRow      " + lineRow.getAttribute("LineId"));               
               managementCal(0 ,lineRow );
               productionCalculation(lineRow);
               ValueCalculation( lineRow);
               setfabricDescription(lineRow);
               
        }
        
        // System.out.println("09090909090909090  currentLineId  "+   lineVo.getCurrentRow().getAttribute("LineId"));
        
        am.StyleTracking();           
        am.getDBTransaction().commit();        
        this.refreshQueryKeepingCurrentRow(am.getMnjMfgPrecostingLView1());
        
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(subCostingTable);
        AdfFacesContext.getCurrentInstance().addPartialTarget(panelTabbed);
        
        
        return null;
    }


    public void editPopupFetchListener(PopupFetchEvent popupFetchEvent) {
        if (popupFetchEvent.getLaunchSourceClientId().contains("cbInsert")) {
         //   System.out.println("Insert event called......!");

        }
    }

    public void editDialogListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().name().equals("ok")) {

            callCopy(getSelectRadio().getValue().toString());


        } else if (dialogEvent.getOutcome().name().equals("cancel")) {
            BindingContainer bindings = getBindings();

        }
    }

    public void editPopupCancelListener(PopupCanceledEvent popupCanceledEvent) {
        BindingContainer bindings = getBindings();

    }


    public void setSelectRadio(RichSelectOneRadio selectRadio) {
        this.selectRadio = selectRadio;
    }

    public RichSelectOneRadio getSelectRadio() {
        return selectRadio;
    }


    public void setHeaderID(RichInputText headerID) {
        this.headerID = headerID;
    }

    public RichInputText getHeaderID() {
        return headerID;
    }

    public Number getSlectedLineID() {

        oracle.adf.view.rich.component.UIXTable table = getSubCostingTable();
        // Get the Selected Row key set iterator

        //((FOB*Comission(%))*Order Qty+(FOB*Comission(%))*Sample Qty)/Order Qty
        java.util.Iterator selectionIt = table.getSelectedRowKeys().iterator();
        Number id = new Number(0);

        while (selectionIt.hasNext()) {
            Object rowKey = selectionIt.next();
            table.setRowKey(rowKey);
            int index = table.getRowIndex();
            FacesCtrlHierNodeBinding row =
                (FacesCtrlHierNodeBinding)table.getRowData(index);
            Row selectedRow = row.getRow();
            id = (Number)selectedRow.getAttribute("LineId");
        }

        return id;

    }


    public void callCopy(String type) {

        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        OperationBinding operationBinding =
            bindings.getOperationBinding("copyRecords");


        operationBinding.getParamsMap().put("type", type);
        operationBinding.getParamsMap().put("headerId",
                                            getHeaderID().getValue());
        operationBinding.getParamsMap().put("lineId", getSlectedLineID());


        //invoke method
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            System.out.println("if errors-->");
            // List errors = operationBinding.getErrors();
        }
        //optional
        Object methodReturnValue = operationBinding.getResult();
        String message = null;
        if (methodReturnValue != null) {
            message = methodReturnValue.toString();
        } else {
            message = "Failed! .";
        }
        FacesMessage fm = new FacesMessage(message);
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
        AdfFacesContext.getCurrentInstance().addPartialTarget(subCostingTable);
    }

    public String createItem() {
        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        OperationBinding operationBinding =
            bindings.getOperationBinding("createItems");

        operationBinding.getParamsMap().put("headerId",
                                            getHeaderID().getValue());


        //invoke method
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            System.out.println("if errors-->");
            // List errors = operationBinding.getErrors();
        }
        //optional
        Object methodReturnValue = operationBinding.getResult();
        String message = null;
        if (methodReturnValue != null) {
            message = methodReturnValue.toString();
        } else {
            message = "Failed! .";
        }
        FacesMessage fm = new FacesMessage(message);
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
        return null;
    }

    public void setAddChargLC(RichInputText addChargLC) {
        this.addChargLC = addChargLC;
    }

    public RichInputText getAddChargLC() {
        return addChargLC;
    }

    public void setAddPriceFOB(RichInputText addPriceFOB) {
        this.addPriceFOB = addPriceFOB;
    }

    public RichInputText getAddPriceFOB() {
        return addPriceFOB;
    }

    //    public String createSubCostingLine() {
    //        BindingContainer bindings = getBindings();
    //        OperationBinding operationBinding =
    //            bindings.getOperationBinding("CreateInsert");
    //        Object result = operationBinding.execute();
    //
    //        if (!operationBinding.getErrors().isEmpty()) {
    //            return null;
    //        }
    //        return null;
    //    }

    public String sendForApproval() {

        OperationBinding ob = executeOperation("Approve");
        ob.getParamsMap().put("headerId", getHeaderID().getValue());
        ob.execute();
        FacesMessage fm = new FacesMessage("Successfully Sent For Approval");
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
        return null;
    }

    public String sendForApprovalForward() {
        // Add event code here...

        OperationBinding ob = executeOperation("ApproveNForward");
        ob.getParamsMap().put("headerId", getHeaderID().getValue());
        ob.execute();
        FacesMessage fm = new FacesMessage("Successfully Sent For Approval");
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);

        return null;
    }


    /*****Generic Method to Get BindingContainer**/
    public BindingContainer getBindingsCont() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    /**
     * Generic Method to execute operation
     * */
    public OperationBinding executeOperation(String operation) {
        OperationBinding createParam =
            getBindingsCont().getOperationBinding(operation);
        return createParam;

    }


    public void setAddChrgPrcnt(RichInputText addChrgPrcnt) {
        this.addChrgPrcnt = addChrgPrcnt;
    }

    public RichInputText getAddChrgPrcnt() {
        return addChrgPrcnt;
    }


    public void setUomConversion(RichInputText uomConversion) {
        this.uomConversion = uomConversion;
    }

    public RichInputText getUomConversion() {
        return uomConversion;
    }

    //    public void FetchLines(ActionEvent actionEvent) {
    //        // Add event code here...
    //
    //
    //        OperationBinding ob = executeOperation("fetchProjectionLines");
    //        ob.getParamsMap().put("headerNo", getProjectionNo().getValue());
    //        ob.execute();
    //
    //
    //
    //    }

    public void setOthersTable(RichTable othersTable) {
        this.othersTable = othersTable;
    }

    public RichTable getOthersTable() {
        return othersTable;
    }

    /*******************************************
     * Set others values
     * *****************************************************************/
    public void setOthersValues() {


        BindingContext bindingContext = BindingContext.getCurrent();
        DCDataControl dc =
            bindingContext.findDataControl("AppModuleDataControl"); //
        ApplicationModule am = dc.getApplicationModule();
        ViewObject findViewObject =
            am.findViewObject("MnjMfgPrecostingLView1");


        //        oracle.adf.view.rich.component.UIXTable table = getOthersTable();
        // Get the Selected Row key set iterator

        //((FOB*Comission(%))*Order Qty+(FOB*Comission(%))*Sample Qty)/Order Qty
        // RowSetIterator selectionIt = findViewObject.createRowSetIterator("a");

        try {
            Row selectedRow = findViewObject.getCurrentRow();

            //  Object id = selectedRow.getAttribute("OtherCharge");


            setOtherCharge(selectedRow.getAttribute("OtherCharge"));

            setLabTestcharge(selectedRow.getAttribute("LabTestCharge"));
            setPrint(selectedRow.getAttribute("Print"));
          //  setFabricFinance(selectedRow.getAttribute("FabricFinance"));
           // setWashFinance(selectedRow.getAttribute("WashFinance"));
           // setTrimFinance(selectedRow.getAttribute("TrimFinance"));
          //  setCommission(selectedRow.getAttribute("CommisonPrcnt"));

            System.out.println("Others Called--->");
        } catch (Exception e) {
            ;
        }


    } //end of others value method

    public Object getSMSQtyFromTable() {

        oracle.adf.view.rich.component.UIXTable table = getSubCostingTable();
        // Get the Selected Row key set iterator

        //((FOB*Comission(%))*Order Qty+(FOB*Comission(%))*Sample Qty)/Order Qty
        java.util.Iterator selectionIt = table.getSelectedRowKeys().iterator();
        Object qty = null;
        while (selectionIt.hasNext()) {
            Object rowKey = selectionIt.next();
            table.setRowKey(rowKey);
            int index = table.getRowIndex();
            FacesCtrlHierNodeBinding row =
                (FacesCtrlHierNodeBinding)table.getRowData(index);
            Row selectedRow = row.getRow();

            qty = selectedRow.getAttribute("SmsQty");

        }
        System.out.println("SMS Qty-->" + qty);
        return qty;
    }

    public void dry(ValueChangeEvent valueChangeEvent) {
        // Add event code here...

        double dryCostVal = MyMath.numeric(getDryCost());
        double dryProfitVal = MyMath.numeric(getDryProfit());
        double dryTotalVal = dryCostVal + dryProfitVal;
        // refreshGrandDryTotal(dryTotalVal);
        // if (dryTotalVal > 0)
        dryTotal.setValue(MyMath.toNumber(MyMath.round(dryTotalVal)));
        AdfFacesContext.getCurrentInstance().addPartialTarget(dryTotal);


    }

    public void wetListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...

        double wetCostVal = MyMath.numeric(getWetCost());
        double wetProfitVal = MyMath.numeric(getWetProfit());
        double wetTotalVal = wetCostVal + wetProfitVal;
        // refreshWetGrandTotal(wetTotalVal);
        // if (wetTotalVal > 0)
        wetTotal.setValue(MyMath.toNumber(MyMath.round(wetTotalVal)));
        AdfFacesContext.getCurrentInstance().addPartialTarget(wetTotal);
    }


    public double getPOCFinanceCost(String type) {
        // Add event code here...
        double val = 0.0;
        OperationBinding ob = executeOperation("getPOCFinCost");
        ob.getParamsMap().put("type", type);
        ob.execute();

        Object methodReturnValue = ob.getResult();
        if (methodReturnValue != null) {
            val = MyMath.numeric3(methodReturnValue.toString());
        }
     
        return val;
    }

    public void setCmMerchDzn(RichInputText cmMerchDzn) {
        this.cmMerchDzn = cmMerchDzn;
    }

    public RichInputText getCmMerchDzn() {
        return cmMerchDzn;
    }


    public void setTargetPrice(RichInputText targetPrice) {
        this.targetPrice = targetPrice;
    }

    public RichInputText getTargetPrice() {
        return targetPrice;
    }

    public void setDifference(RichInputText difference) {
        this.difference = difference;
    }

    public RichInputText getDifference() {
        return difference;
    }

    public void setFabFinanceCost(RichInputText fabFinanceCost) {
        this.fabFinanceCost = fabFinanceCost;
    }

    public RichInputText getFabFinanceCost() {
        return fabFinanceCost;
    }

    public void setPrefixName(RichInputListOfValues prefixName) {
        this.prefixName = prefixName;
    }

    public RichInputListOfValues getPrefixName() {
        return prefixName;
    }

    public void setPrefixCode(RichInputText prefixCode) {
        this.prefixCode = prefixCode;
    }

    public RichInputText getPrefixCode() {
        return prefixCode;
    }

    public void setProdCostPerLine(RichInputText prodCostPerLine) {
        this.prodCostPerLine = prodCostPerLine;
    }

    public RichInputText getProdCostPerLine() {
        return prodCostPerLine;
    }

    public void setSampleDocNo(RichInputText sampleDocNo) {
        this.sampleDocNo = sampleDocNo;
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = fctx.getExternalContext();
        HttpSession userSession = (HttpSession)ectx.getSession(false);
        userSession.setAttribute("sampleDocS", sampleDocNo.getValue());
        
    }

    public RichInputText getSampleDocNo() {
        return sampleDocNo;
    }

    public void LaunchPopupListener(LaunchPopupEvent launchPopupEvent) {
        // Add event code here...
        if (launchPopupEvent.getPopupType().equals
               (LaunchPopupEvent.PopupType.SEARCH_DIALOG))
            {
            
              launchPopupEvent.setLaunchPopup(false);
            }
    }
    
    
    
    public double getBPOTotalQty() {

    System.out.println("Level 1");
        BindingContext bindingContext = BindingContext.getCurrent();
        DCDataControl dc =
            bindingContext.findDataControl("AppModuleDataControl"); //
        ApplicationModule am = dc.getApplicationModule();
        ViewObject findViewObject =am.findViewObject("CreateSaleOrderLinesVO1"); //ImpSaleContractDetailEOView1
   
    System.out.println("Level 2");
                 
        RowSetIterator it = findViewObject.createRowSetIterator("tt");
        double val = 0.00, total = 0.00;
        while (it.hasNext()) {

            Row r = it.next();
            try {
                val = Double.parseDouble(r.getAttribute("BpoQty").toString());
            } catch (Exception e) {
                ;
            }

            total = total + val;
        } //end of while loop
        it.closeRowSetIterator();
        //return roundTo2(total);
        System.out.println("Level 3 ---->"+total);
        
        return total;
        
    }
    


    public void setTotalBpoQty(RichOutputText totalBpoQty) {
        this.totalBpoQty = totalBpoQty;
    }

    public RichOutputText getTotalBpoQty() {
        return totalBpoQty;
    }

    public void BPOQty_VC(ValueChangeEvent valueChangeEvent) {
        // Add event code here...

        
        

   
    }

    public void GetTotalQty(ActionEvent actionEvent) {
        // Add event code here...
        System.out.println(" Total BPO Qty------------>"+getBPOTotalQty());
        totalBpoQty.setValue(getBPOTotalQty());
        AdfFacesContext.getCurrentInstance().addPartialTarget(totalBpoQty);
        System.out.println(" Total BPO Qty 2------------>"+totalBpoQty.getValue().toString());
        
    }


    public void CopyItem_Detail_Bind(ActionEvent actionEvent) {
        // Add event code here...
        OperationBinding ob = executeOperation("CopyItemsDetail");
        ob.execute();
        AdfFacesContext.getCurrentInstance().addPartialTarget(itemTable);
        }



    public void setTbAQty_Bind(RichOutputText tbAQty_Bind) {
        this.tbAQty_Bind = tbAQty_Bind;
    }

    public RichOutputText getTbAQty_Bind() {
        return tbAQty_Bind;
    }


    public void GetTotalQty_TBA(ActionEvent actionEvent) {
        // Add event code here...
    }


    public String callAttachment() throws IOException {
      
        String doc= null;     
        BindingContext bindingContext = BindingContext.getCurrent(); 
        DCDataControl dc = bindingContext.findDataControl("AppModuleDataControl"); //
        ApplicationModule am  = dc.getApplicationModule() ;
        ViewObject findViewObject =  am.findViewObject("MnjMfgPrecostingHView1");
        
        try {
            doc = findViewObject.getCurrentRow().getAttribute("HeaderNo").toString();
        } catch (Exception e) {
            // TODO: Add catch code
            ;
        }    
      
      
    //            String newPage =
    //                "http://192.168.200.115:7003/FileUploading-ViewController-context-root/faces/view1?doc=MB&docNo="+doc;
    //            // String newPage = "http://localhost:7101/PurchaseMemo-ViewController-context-root/faces/SearchPG?headerId="+getBomId().getValue();
    //            FacesContext ctx = FacesContext.getCurrentInstance();
    //            ExtendedRenderKitService erks =
    //                Service.getService(ctx.getRenderKit(), ExtendedRenderKitService.class);
    //            String url = "window.open('" + newPage + "','_self');";
    //            erks.addScript(FacesContext.getCurrentInstance(), url);
            
            //...........chnging....................//
//        FacesContext fc = FacesContext.getCurrentInstance();
//        HttpServletResponse response = (HttpServletResponse)fc.getExternalContext().getResponse();
//        
//        response.sendRedirect("http://192.168.200.106:7003/FileUploading-ViewController-context-root/faces/view1?doc=PC&docNo="+doc);
//        fc.responseComplete();
        //...............changing end..........//
                String newPage =
                 "http://192.168.200.115:7003/FileUploading-ViewController-context-root/faces/view1?doc=CD_Invoice_No&docNo="+doc;
             // String newPage = "http://localhost:7101/PurchaseMemo-ViewController-context-root/faces/SearchPG?headerId="+getBomId().getValue();
             FacesContext ctx = FacesContext.getCurrentInstance();
             ExtendedRenderKitService erks = Service.getService(ctx.getRenderKit(), ExtendedRenderKitService.class);
             String url = "window.open('" + newPage + "','_blank','toolbar=no,location=no,menubar=no,alwaysRaised=yes,height=500,width=1100');";
             erks.addScript(FacesContext.getCurrentInstance(), url);
    
        

        return null;
    }


    //-------------------------------------------------------------------------Farabi-----------------------------------------------------------//
    
    
    public ApplicationModule getAppM(){
        DCBindingContainer bindingContainer =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        //BindingContext bindingContext = BindingContext.getCurrent();
        DCDataControl dc =
            bindingContainer.findDataControl("AppModuleDataControl"); // Name of application module in datacontrolBinding.cpx
        AppModuleImpl appM = (AppModuleImpl)dc.getDataProvider();
        return appM;
    }
    AppModuleImpl am = (AppModuleImpl)this.getAppM();

    public void itemDialogListener(DialogEvent dialogEvent) {
        // Add event code here...
        populateBundlesRec();
        AdfFacesContext.getCurrentInstance().addPartialTarget(panelTabbed);
       // ViewObject febricVO=am.getFebricView1();
    //  febricVO.executeQuery();
       // refershButton();
        AdfFacesContext.getCurrentInstance().addPartialTarget(panelTabbed);

    }
    
    public void populateBundlesRec() {

                ViewObject populatevo = am.getPOCLinesCopyVO1(); // pop up view
                
                // populatevo.executeQuery();
                //System.out.println("--------------------------------populateBundlesRec-----------------------------------------------");
                Row[] r = populatevo.getAllRowsInRange();
                //System.out.println(" ------------------------------populateBundlesRec after row-------------------------------------------------");
                System.out.println("Drop ------->" + r.length);
                for (Row row : r) {
                    //System.out.println("MultiSelect Check--->" + row.getAttribute("MultiSelect"));
                    //System.out.println("-------------------------------------inside for loop------------------------------------------");
                    if (
                        row.getAttribute("MultiSelect") != null &&
                        row.getAttribute("MultiSelect").equals(true)
                       ) 
                    {
                        //System.out.println("-------------------------------------inside if cond------------------------------------------");
                        //System.out.println("MultiSelect --->" + row.getAttribute("MultiSelect"));
                        populateLineDetails(row);
                        String lineId = row.getAttribute("LineId").toString();
                        System.out.println("line id-------------->" +lineId);
                        
                        populateItemDetails(lineId); /// method to populate data
                        
                        populateWetDetails(lineId);
                        populateDryDetails(lineId);
                        
                    }
                }
            }
            
            
    public void populateItemDetails(String lineId) {
                    
                    String query= " SELECT DISTINCT\n" + 
                    "       MMPID.DETAIL_ID,\n" + 
                    "       MMPID.LINE_ID,\n" + 
                    "        MMPID.ITEM_PREFIX, \n" + 
                    "       (select t.value_description\n" + 
                    "         from MNJ_ITEM_PREFIX_V t\n" + 
                    "         where t.flex_value = to_char(MMPID.Item_Prefix))PREFIX_DESC,\n" + 
                    "       MMPID.REF_CODE,\n" + 
                    "       MMPID.ITEM_DESC,\n" + 
                    "       MMPID.UOM,\n" + 
                    "       MMPID.PRICE_UOM,\n" + 
                    "       MMPID.ACTUAL_UNIT_PRICE,\n" + 
                    "       MMPID.ADD_CHARGE_LC,\n" + 
                    "       MMPID.ADD_CRG_PRCNT,\n" + 
                    "       MMPID.FINAL_UNIT_PRICE,\n" + 
                    "       MMPID.CONS_PER_PCS,\n" + 
                    "       MMPID.WASTAGE_IN_PER,\n" + 
                    "       MMPID.COST_PER_PCS,\n" + 
                    "       MMPID.FINAL_COST_PER_PCS,\n" + 
                    "       MMPID.REMARKS,\n" + 
                    "       MMPID.UOM_CONV,\n" + 
                    "       MMPID.SUPPLIER_ID,\n" + 
                    "       MMPID.PAYMENT_TERM_ID,\n" + 
                    "       MMPID.DELIVERY_TERM_ID,\n" + 
                    "       MMPID.STATUS,\n" + 
                    "       MMPID.ADD_PRICE_FOB,\n" + 
                    "       MMPID.ADD_PRICE_MOQ,\n" + 
                    "       MMPID.BUFER,\n" + 
                    "       MMPID.ADD_DEDUCT_COST,\n" + 
                    "       MMPID.WASTAGE_IN_QTY,\n" + 
                    "       MMPID.CONS_WITH_WASTAGE,\n" + 
                    "       MMPID.FINAL_CONS,\n" + 
                    "       MMPID.FAB_FINANCE_COST\n" + 
                    "  FROM MNJ_MFG_PRECOSTING_ITEM_D MMPID,MNJ_MFG_PRECOSTING_L MMPL\n" + 
                    "  WHERE 1=1\n" + 
                    "      AND MMPID.LINE_ID=MMPL.LINE_ID\n" + 
                    "      AND MMPID.LINE_ID=?\n" + 
                    "  ORDER  BY MMPID.DETAIL_ID";
                    
                   
                    ResultSet resultSet=null;                                                                                 
                    PreparedStatement createStatement= am.getDBTransaction().createPreparedStatement(query,0);
                    //System.out.println("2");
                    try {
                        createStatement.setString(1, lineId);
                        resultSet=createStatement.executeQuery();
                        //System.out.println("3");
                        //System.out.println("populateLinesTest ------->");
                        //System.out.println("-------------------------------------populateLinesTestRec------------------------------------------");
                        
                        ViewObject vo = am.getMnjMfgPrecostingItemDView1(); // in which you wants to populate daa
                        
                        removeData(vo);
                        //System.out.println("4");
                        
                        while(resultSet.next()){
                                // code to display the rows in the table.
                                Row linerow = vo.createRow();
                                linerow.setNewRowState(Row.STATUS_INITIALIZED);  
                               linerow.setAttribute("ItemPrefix",resultSet.getString("ITEM_PREFIX"));
                                linerow.setAttribute("PrefixDesc",resultSet.getString("PREFIX_DESC"));
                                System.out.println(resultSet.getString("PREFIX_DESC"));
                                linerow.setAttribute("RefCode", resultSet.getString("REF_CODE")); 
                                linerow.setAttribute("ItemDesc",resultSet.getString("ITEM_DESC"));
                                linerow.setAttribute("Uom",resultSet.getString("UOM"));
                                linerow.setAttribute("PriceUom",resultSet.getString("PRICE_UOM"));
                                linerow.setAttribute("ActualUnitPrice",resultSet.getString("ACTUAL_UNIT_PRICE"));
                                linerow.setAttribute("AddChargeLc",resultSet.getString("ADD_CHARGE_LC"));
                                linerow.setAttribute("AddCrgPrcnt",resultSet.getString("ADD_CRG_PRCNT"));
                                linerow.setAttribute("FinalUnitPrice",resultSet.getString("FINAL_UNIT_PRICE"));
                                linerow.setAttribute("ConsPerPcs",resultSet.getString("CONS_PER_PCS"));
                                linerow.setAttribute("WastageInPer",resultSet.getString("WASTAGE_IN_PER"));
                                linerow.setAttribute("CostPerPcs",resultSet.getString("COST_PER_PCS"));
                                linerow.setAttribute("FinalCostPerPcs",resultSet.getString("FINAL_COST_PER_PCS"));
                                linerow.setAttribute("Remarks",resultSet.getString("REMARKS"));
                                linerow.setAttribute("UomConv",resultSet.getString("UOM_CONV"));   
                            
                                linerow.setAttribute("PaymentTermId",resultSet.getString("PAYMENT_TERM_ID"));  
                                linerow.setAttribute("DeliveryTermId",resultSet.getString("DELIVERY_TERM_ID"));  
                                linerow.setAttribute("Status",resultSet.getString("STATUS"));  
                                linerow.setAttribute("AddPriceFob",resultSet.getString("ADD_PRICE_FOB"));  
                                linerow.setAttribute("AddPriceMoq",resultSet.getString("ADD_PRICE_MOQ"));  
                                linerow.setAttribute("Bufer",resultSet.getString("BUFER"));  
                                linerow.setAttribute("AddDeductCost",resultSet.getString("ADD_DEDUCT_COST"));  
                                linerow.setAttribute("WastageInQty",resultSet.getString("WASTAGE_IN_QTY"));  
                                linerow.setAttribute("ConsWithWastage",resultSet.getString("CONS_WITH_WASTAGE"));  
                                linerow.setAttribute("FinalCons",resultSet.getString("FINAL_CONS"));  
                                linerow.setAttribute("FabFinanceCost",resultSet.getString("FAB_FINANCE_COST"));  
                                
                                vo.insertRow(linerow); 
                        }
        
                    } catch (SQLException e) {
                        System.out.println("Exception in getting query data");
                    }
                    
              

                } 
            
    public void populateWetDetails(String lineId) {
             String query= "  SELECT DISTINCT\n" + 
                         "       MMPWD.LINE_ID, \n" + 
                         "       MMPWD.COST_AMOUNT, \n" + 
                         "       MMPWD.MANUAL_PRICE, \n" + 
                         "       MMPWD.PROCESS_ID, \n" + 
                         "       MMPWD.PROCESS_NAME, \n" + 
                         "       MMPWD.PROFIT, \n" + 
                         "       MMPWD.TOTAL,\n" + 
                         "       (MMPWD.PROFIT + MMPWD.COST_AMOUNT) NEW_TOTAL_VAL\n" + 
                         "       \n" + 
                         "  FROM MNJ_MFG_PRECOSTING_L MMPL,\n" + 
                         "       MNJ_MFG_PRECOSTING_WET_D MMPWD\n" + 
                         "  \n" + 
                         "  WHERE MMPWD.LINE_ID=MMPL.LINE_ID\n" + 
                         "        AND MMPWD.LINE_ID=?";
             
             ResultSet resultSet=null;                                                                                 
             PreparedStatement createStatement= am.getDBTransaction().createPreparedStatement(query,0);
             try {
                 createStatement.setString(1, lineId);
                 resultSet=createStatement.executeQuery();
                 
                 //System.out.println("populateLinesTest ------->");
                 //System.out.println("-------------------------------------populateLinesTestRec------------------------------------------");
                 
                 ViewObject vo = am.getMnjMfgPrecostingWetDView1(); // in which you wants to populate daa
                 removeData(vo);
                
                 while(resultSet.next()){
                         // code to display the rows in the table.
                         Row linerow = vo.createRow();
                         linerow.setNewRowState(Row.STATUS_INITIALIZED);  
                           linerow.setAttribute("ProcessId",resultSet.getString("PROCESS_ID"));
                         linerow.setAttribute("ProcessName",resultSet.getString("PROCESS_NAME"));
                         //System.out.println(resultSet.getString("PROCESS_NAME"));
                         linerow.setAttribute("CostAmount", resultSet.getString("COST_AMOUNT")); 
                         linerow.setAttribute("Profit",resultSet.getString("PROFIT"));
                         linerow.setAttribute("Total",resultSet.getString("TOTAL"));
                         linerow.setAttribute("NewTotalVal",resultSet.getString("NEW_TOTAL_VAL"));
                         linerow.setAttribute("ManualPrice",resultSet.getString("MANUAL_PRICE")); 
                         
                         vo.insertRow(linerow); 
                 }
             
             } catch (SQLException e) {
                 System.out.println("Exception in getting query data");
             }
         }
         
         public void populateDryDetails(String lineId) {
             String query= "SELECT DISTINCT\n" + 
                         "         MMPDD.LINE_ID, \n" + 
                         "       MMPDD.COST_AMOUNT,\n" + 
                         "       MMPDD.MANUAL_PRICE, \n" + 
                         "       MMPDD.PROCESS_ID, \n" + 
                         "       MMPDD.PROCESS_NAME, \n" + 
                         "       MMPDD.PROFIT, \n" + 
                         "       MMPDD.TOTAL\n" + 
                         "       \n" + 
                         "  FROM MNJ_MFG_PRECOSTING_L MMPL,\n" + 
                         "       MNJ_MFG_PRECOSTING_DRY_D MMPDD    \n" + 
                         "  \n" + 
                         "  WHERE MMPDD.LINE_ID=MMPL.LINE_ID\n" + 
                         "        AND MMPDD.LINE_ID=?";
             ResultSet resultSet=null;                                                                                 
             PreparedStatement createStatement= am.getDBTransaction().createPreparedStatement(query,0);
             try {
                 createStatement.setString(1, lineId);
                 resultSet=createStatement.executeQuery();
                 
                 //System.out.println("populateLinesTest ------->");
                 //System.out.println("-------------------------------------populateLinesTestRec------------------------------------------");
                 
                 ViewObject vo = am.getDryDetailVO1(); // in which you wants to populate daa
                 removeData(vo);
                 
                 while(resultSet.next()){
                         // code to display the rows in the table.
                         Row linerow = vo.createRow();
                         linerow.setNewRowState(Row.STATUS_INITIALIZED);  
                         linerow.setAttribute("ProcessId",resultSet.getString("PROCESS_ID"));
                         linerow.setAttribute("ProcessName",resultSet.getString("PROCESS_NAME"));
                         //System.out.println(resultSet.getString("PROCESS_NAME"));
                         linerow.setAttribute("CostAmount", resultSet.getString("COST_AMOUNT")); 
                         linerow.setAttribute("Profit",resultSet.getString("PROFIT"));
                         linerow.setAttribute("Total",resultSet.getString("TOTAL"));
                         linerow.setAttribute("ManualPrice",resultSet.getString("MANUAL_PRICE"));
                         
                         
                         vo.insertRow(linerow); 
                 }
             
             } catch (SQLException e) {
                 System.out.println("Exception in getting query data");
             }
         }
            
        public void removeData(ViewObject vo){
            RowSetIterator iter = vo.createRowSetIterator(null);       
            iter.reset();
            while (iter.hasNext()) {
                Row row = iter.next();
                row.remove();
                // process the row here
            }
            iter.closeRowSetIterator();
            vo.executeEmptyRowSet();  
        }
        
        public void copyItemsPopup(PopupFetchEvent popupFetchEvent) {
            // Add event code here...
              
                ViewObject popVo = am.getPOCLinesCopyVO1();    /// pop up view   
                popVo.executeQuery();
        }
               

        public void setDryDetailsTable(RichTable dryDetailsTable) {
            this.dryDetailsTable = dryDetailsTable;
        }
    
        public RichTable getDryDetailsTable() {
            return dryDetailsTable;
        }
    
        public void setWetDetailsTable(RichTable wetDetailsTable) {
            this.wetDetailsTable = wetDetailsTable;
        }
    
        public RichTable getWetDetailsTable() {
            return wetDetailsTable;
        }

    
       
    //-------------------------------------------------------------------------Farabi-----------------------------------------------------------//


    public void goToSizeDetailForm(ActionEvent actionEvent) {
        // Add event code here...
    }

    public void sizeBreakdownPopUpFetchPreAct(ActionEvent actionEvent) {
        // Add event code here...
        System.out.println("============== in  sizeBreakdownPopUpFetchPreAct method ");
        System.out.println("============== bpo block  : "+bpoTableBlock);
        bpoTableBlock= 1;
        System.out.println("============== bpo block  : "+bpoTableBlock);
        
    }

    public void sizebreakDownCallFromCreateBPOblock(ActionEvent actionEvent) {
        
       bpoTableBlock=1;
        
        
        sizeBreakdownPopUpFetch(bpoTableBlock);
        
    }

  

    public void setP1(RichPopup p1) {
        this.p1 = p1;
    }

    public RichPopup getP1() {
        return p1;
    }

    public void setCreateOrderForm(RichForm createOrderForm) {
        this.createOrderForm = createOrderForm;
    }

    public RichForm getCreateOrderForm() {
        return createOrderForm;
    }

 

    private Row getcurrentBpoRow(int bpoTableBlock) {
        /**this method returns the current row's bpo  from which the
         * size breakdown pop is called based on bpoTableBlock flag in parameter
           if flag = 0 then bpo of first block elsi if flag = 1 then   then bpo of second block  returned          */
        ViewObject vo ;
        if (bpoTableBlock==1){
           
            vo= am.getMnjPrecostCreateBpoDVO1();
            
        }else {
            vo= am.getCreateSaleOrderLinesVO1();
        }
        currentBpo = vo.getCurrentRow().getAttribute("BpoNo").toString();
        return vo.getCurrentRow();
    }

    public void createSizLineRow(ActionEvent actionEvent) {
        ViewObject sizLineVo = am.getCustMnjOntSoObinSizline_LineVO1();
        String country = "";
        String shipMode = "";
        Date deliveryStartDate = null;
        Date deliveryEndDate = null;
        String deliveryType = "";
          
        try{
            country =  currentBpoRow.getAttribute("Country").toString();
            shipMode =  currentBpoRow.getAttribute("ShipMode").toString();
            deliveryStartDate = (Date)currentBpoRow.getAttribute("DeliveryStartDate");
            deliveryEndDate = (Date)currentBpoRow.getAttribute("DeliveryEndDate");
            
           // String deliveryStartDate =  currentBpoRow.getAttribute("DeliveryStartDate").toString();
          //  String deliveryEndDate =  currentBpoRow.getAttribute("DeliveryEndDate").toString();
            deliveryType =  currentBpoRow.getAttribute("Incoterms").toString();     
        }catch(Exception e){
            FacesMessage Message = new FacesMessage("Please fill up country, ship mode and delivery date in sales order first!");   
             Message.setSeverity(FacesMessage.SEVERITY_ERROR);   
             FacesContext fc = FacesContext.getCurrentInstance();   
             fc.addMessage(null, Message);  
             return;
        }
        
        Row sizLine = sizLineVo.createRow();
       // sizLineVo.setCurrentRow(sizLine);
        sizLine.setAttribute("Country", country);
        sizLine.setAttribute("ShipMode", shipMode);
        sizLine.setAttribute("DeliveryDate", deliveryStartDate);
        sizLine.setAttribute("DeliveryEndDate", deliveryEndDate);
        sizLine.setAttribute("DeliveryTerm", deliveryType);
        
    }

    public void sizeBreakdownCallFromCreateSalesOrderBlock(ActionEvent actionEvent) {
      
               bpoTableBlock=0;
             
               sizeBreakdownPopUpFetch(bpoTableBlock);
               
             
    }

    private void sizeBreakdownPopUpFetch( int bpoTableBlock) {
        
       currentBpoRow = getcurrentBpoRow(bpoTableBlock);
       
       
        ViewObject  sizeHeaderVo=am.getsizeHeaderVO1();
        sizeHeaderVo.setWhereClause("BPO_NO = '"+currentBpo+"'");
        sizeHeaderVo.executeQuery();
        //check wheather current bpo heabeen generated to standard form;
        if (sizeHeaderVo.getAllRowsInRange().length==0){
            
                 FacesMessage Message = new FacesMessage("BPO has not been generated yet. Try a few Later");   
                 Message.setSeverity(FacesMessage.SEVERITY_INFO);   
                 FacesContext fc = FacesContext.getCurrentInstance();   
                 fc.addMessage(null, Message); 
                 return ;
        }
        //popup fetch
        RichPopup.PopupHints ph = new  RichPopup.PopupHints();
        getP1().show(ph);
        
        
    }


    public void saveSizeBreakDown(ActionEvent actionEvent) {
        
        System.out.println("=========================    in      saveSizeBreakDown");
        ViewObject sizeHeaderVo =  am.getsizeHeaderVO1();
        Row  currentSizeHeaderRow =  sizeHeaderVo.getCurrentRow();
//        ViewObject lineVo=am.getCustMnjOntSoObinSizline_LineVO1();
//        Row  currentSizeLineRow =  lineVo.getCurrentRow();
//        
        Double stnQuantity;
        Double totalLineQty;
        Double totalDetailQty;
        
        
        FacesMessage Message = new FacesMessage("Total Line Quantity and STN Quantity are not equal. ");   
             Message.setSeverity(FacesMessage.SEVERITY_INFO);   
             FacesContext fc = FacesContext.getCurrentInstance(); 
     //   Number stnQuantity = (Number)currentSizeHeaderRow.getAttribute("OrderedQuantity");
     // Number totalLineQty = (Number)currentSizeHeaderRow.getAttribute("QtyTotal");
                   
        try{
            stnQuantity = Double.parseDouble(currentSizeHeaderRow.getAttribute("OrderedQuantity").toString());
            
            totalLineQty = Double.parseDouble(currentSizeHeaderRow.getAttribute("QtyTotal").toString());
            totalDetailQty = Double.parseDouble(currentSizeHeaderRow.getAttribute("QtyTotal").toString());
            
//            System.out.println("=====================================================================================================");
//            System.out.println("========================= ordered quantity (stn)"+stnQuantity);
//            System.out.println("========================= totalLineQty  "+totalLineQty);
        }
        catch(Exception e){
            fc.addMessage(null, Message); 
            return;
        }
         
     
        if(!stnQuantity.equals(totalLineQty)){
           
            fc.addMessage(null, Message); 
            
            return;
        }
        
        OperationBinding ob1 = executeOperation("Commit");
        ob1.execute();
        
        
    }

    

    private void remark() {
        am.validateLines();
        String ab="check remark";
   // String re= am.validateLines();
        
//    if(re=="Same") {
//        String message ="Check remark";
//        FacesMessage fm = new FacesMessage(message);
//        fm.setSeverity(FacesMessage.SEVERITY_INFO);
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, fm);
//        checkremark();
//        
//    }
        
        
    }

    public void setRemarkAction(RichColumn remarkAction) {
        this.remarkAction = remarkAction;
    }

    public RichColumn getRemarkAction() {
        return remarkAction;
    }

    private void checkremark() {
    
    }

    public void deleteCopy(ActionEvent actionEvent) {
    String flag=null;
        
        ViewObject vo = am.getCreateSaleOrderLinesVO1();
//        String trueFlag="Y";
//        try{
//           flag=vo.getCurrentRow().getAttribute("CloneFlag").toString();
//           
//        }
//        catch(Exception e) {
//                flag="Please Select Copied Row";
//            }
       // Row currRow = .getCurrentRow();
      
        // Add event code here...
      
      
        if (
            vo.getCurrentRow().getAttribute("CloneFlag") != null &&
           vo.getCurrentRow().getAttribute("CloneFlag").equals("Y")
           ) {
          vo.removeCurrentRow();
           //row.remove();
            
            vo.executeQuery();
            System.out.println("...........................dlt........................");
        }
       else {
           flag="Please Select Copied Row";
                   FacesMessage fm = new FacesMessage(flag);
                   fm.setSeverity(FacesMessage.SEVERITY_INFO);
                   FacesContext context = FacesContext.getCurrentInstance();
                   context.addMessage(null, fm);
          
       }
     
    }

    public void filterHeaderTable(ActionEvent actionEvent) {
        ViewObject ovj =  am.getMnjMfgPrecostingHView1();
        ViewObject searchVO=am.getsearchVIEW1();
        String buyer;
        String season;
         
        // System.out.println(".........................................."+buyer);
        // System.out.println(".........................................."+season);
        try{
           buyer=searchVO.getCurrentRow().getAttribute("Buyer").toString();
        }
        catch(Exception e) {
          buyer=null;
        }
          try{
               season=searchVO.getCurrentRow().getAttribute("Season").toString();
          }
          catch(Exception e) {
              season=null;
          }
          
          System.out.println(".........................................."+buyer); 
          System.out.println(".........................................."+season); 
         ovj.setWhereClause("BUYER = '"+buyer+"' AND SEASON = '"+season+"'" );
        //  ovj.setWhereClause("SEASON = '"+season+"'");
        
           ovj.executeQuery();
          
          
           
          
      }
      

    public void setBuyerName(RichInputListOfValues buyerName) {
        this.buyerName = buyerName;
    }

    public RichInputListOfValues getBuyerName() {
        return buyerName;
    }

    public void setSeasonName(RichSelectOneChoice seasonName) {
        this.seasonName = seasonName;
    }

    public RichSelectOneChoice getSeasonName() {
        return seasonName;
    }

    public void setSeasonN(UISelectItems seasonN) {
        this.seasonN = seasonN;
    }

    public UISelectItems getSeasonN() {
        return seasonN;
    }

    public void fabric_tab(DisclosureEvent disclosureEvent) {
        ViewObject fabricVO=am.getFebricView1();
        am.getDBTransaction().commit();
        fabricVO.executeQuery();
        //Shell-Denim
        //Shell-Non Denim
                    //  itemD.setWhereClause("PREFIX_DESC='Shell-Denim' OR PREFIX_DESC='Shell-Non Denim'");
                   //   itemD.executeQuery();
        // Add event code here...
    }

    public void item_tab(DisclosureEvent disclosureEvent) {
        // Add event code here...
        ViewObject itemD=am.getTrimsView1();
        // itemD.setWhereClause(null);
        //Shell-Denim
        //Shell-Non Denim
                    //  itemD.setWhereClause("PREFIX_DESC<>'Shell-Denim' AND PREFIX_DESC<>'Shell-Non Denim'");
                    am.getDBTransaction().commit();
                  itemD.executeQuery();
    }

    private void ValueCalculation(Row  lineRow ) {
        //setOthersValues();
        setMerchValues(lineRow);
    }


    public void febric_costOf_value_cal(ValueChangeEvent valueChangeEvent) {
        ViewObject febricView=am.getFebricView1();
       double total=0.00;
       double actUnitpc=0.00;
        double val = 0.00;
        double buffer=0.00;
        
        
        try{
          actUnitpc=  Double.parseDouble((getAct_unit_prc_feb().getValue().toString()));
        // actUnitpc=  Double.parseDouble( act_unit_prc_feb.getValue().toString());
           //actUnitpc=Double.parseDouble(febricView.getCurrentRow().getAttribute("ActualUnitPrice").toString());
        }
        catch(Exception e) {
            actUnitpc=0.00;
        }
        System.out.println(" actUnitpc------------>"+actUnitpc);
        try{
            val= Double.parseDouble((getCons_per_pc_feb().getValue().toString()));
         //val=Double.parseDouble((valueChangeEvent.getNewValue().toString()));
            // val=Double.parseDouble(cons_per_pc_feb.getValue().toString());
        }
        catch(Exception e) {
            val=0.00;
        }
        System.out.println(" Cons_per_pc_feb------------>"+ val);
        try{
            buffer= Double.parseDouble((getFeb_buffer().getValue().toString()));
            //buffer=Double.parseDouble((febricView.getCurrentRow().getAttribute("Bufer").toString()));
         //val=Double.parseDouble((valueChangeEvent.getNewValue().toString()));
            // val=Double.parseDouble(cons_per_pc_feb.getValue().toString());
        }
        catch(Exception e) {
             buffer=0.00;
        }
        System.out.println(" buffer------------>"+buffer);
        // AdfFacesContext.getCurrentInstance().addPartialTarget(bpoQt_value);
        //System.out.println(" Total BPO Qty------------>"+getBPOTotalQty());
       
        
        total=(val*actUnitpc)+buffer;
       // febricView.getCurrentRow().setAttribute("FinalCostPerPcs", total);
       // total=total+buffer;
       System.out.println(" cost per pcs------------>"+ total);
        febricView.getCurrentRow().setAttribute("CostPerPcs", total);
        
       febricView.getCurrentRow().setAttribute("FinalCostPerPcs", total);
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(cost_per_pcs_feb);
       //AdfFacesContext.getCurrentInstance().addPartialTarget( febric_table);
        // Add event code here...
    }

    public void setCons_per_pc_feb(RichInputText cons_per_pc_feb) {
        this.cons_per_pc_feb = cons_per_pc_feb;
    }

    public RichInputText getCons_per_pc_feb() {
        return cons_per_pc_feb;
    }

    public void setCost_per_pcs_feb(RichInputText cost_per_pcs_feb) {
        this.cost_per_pcs_feb = cost_per_pcs_feb;
    }

    public RichInputText getCost_per_pcs_feb() {
        return cost_per_pcs_feb;
    }

    public void setAct_unit_prc_feb(RichInputText act_unit_prc_feb) {
        this.act_unit_prc_feb = act_unit_prc_feb;
    }

    public RichInputText getAct_unit_prc_feb() {
        return act_unit_prc_feb;
    }

    public void setFebric_table(RichTable febric_table) {
        this.febric_table = febric_table;
    }

    public RichTable getFebric_table() {
        return febric_table;
    }

    public void other_tab(DisclosureEvent disclosureEvent) {
        // Add event code here...
        ViewObject other=am.getOtherView1();
        am.getDBTransaction().commit();
        other.executeQuery();
    }

    public void trimsValuChngCal(ValueChangeEvent valueChangeEvent) {
        ViewObject trimsView=am.getTrimsView1();
        double total=0.00;
        double actUnitpc=0.00;
        double val = 0.00;
        double buffer=0.00;
        
        try{
          actUnitpc=  Double.parseDouble((getTrimActualPrc().getValue().toString()));
            System.out.println("........................actUnitpc for trim= "+actUnitpc);
//            double tes=Double.parseDouble((trimsView.getCurrentRow().getAttribute("ActualUnitPrice").toString()));
//            double te=Double.parseDouble((trimsView.getCurrentRow().getAttribute("PrefixDesc").toString()));
//            System.out.println("......................current..actUnitpc for trim= "+tes);
//            System.out.println("........................prefix for trim= "+te);
        }
        catch(Exception e) {
            actUnitpc=0.00;
        }
        
        try{
            val= Double.parseDouble((getTrimsConsPrs().getValue().toString()));
           // double test=Double.parseDouble((trimsView.getCurrentRow().getAttribute("ConsPerPcs").toString()));
            System.out.println("........................actCons for trim= "+val);
        }
        catch(Exception e) {
            val=0.00;
        }
        
        
        try{
           buffer= Double.parseDouble((getTrim_buffer().getValue().toString()));
        }
        catch(Exception e) {
            buffer=0.00;
        }
        // AdfFacesContext.getCurrentInstance().addPartialTarget(bpoQt_value);
        //System.out.println(" Total BPO Qty------------>"+getBPOTotalQty());
        
        
     total=(val*actUnitpc)+ buffer;
     trimsView.getCurrentRow().setAttribute("CostPerPcs", total);
       trimsView.getCurrentRow().setAttribute("FinalCostPerPcs", total);
       AdfFacesContext.getCurrentInstance().addPartialTarget(trimsCostPerPcs);
      
        
        // Add event code here...
    }

    public void setTrimActualPrc(RichInputText trimActualPrc) {
        this.trimActualPrc = trimActualPrc;
    }

    public RichInputText getTrimActualPrc() {
        return trimActualPrc;
    }

    public void setTrimsConsPrs(RichInputText trimsConsPrs) {
        this.trimsConsPrs = trimsConsPrs;
    }

    public RichInputText getTrimsConsPrs() {
        return trimsConsPrs;
    }

    public void setTrimsCostPerPcs(RichInputText trimsCostPerPcs) {
        this.trimsCostPerPcs = trimsCostPerPcs;
    }

    public RichInputText getTrimsCostPerPcs() {
        return trimsCostPerPcs;
    }

    public void othersValueChngCal(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        ViewObject othersView=am.getOtherView1();
        double total=0.00;
        double actUnitpc=0.00;
        double val = 0.00;
        double bufer=0.00;
        
        try{
          actUnitpc=  Double.parseDouble((getOtherActualPrc().getValue().toString()));
        }
        catch(Exception e) {
            actUnitpc=0.00;
        }
        
        try{
            val= Double.parseDouble((getOteherConPcs().getValue().toString()));
        }
        catch(Exception e) {
            val=0.00;
        }
        
        
        try{
           bufer= Double.parseDouble((getOther_bufer().getValue().toString()));
        }
        catch(Exception e) {
            bufer=0.00;
        }
        
        // AdfFacesContext.getCurrentInstance().addPartialTarget(bpoQt_value);
        //System.out.println(" Total BPO Qty------------>"+getBPOTotalQty());
        
        
        total=val*actUnitpc+bufer;
     othersView.getCurrentRow().setAttribute("CostPerPcs", total);
        othersView.getCurrentRow().setAttribute("FinalCostPerPcs", total);
      AdfFacesContext.getCurrentInstance().addPartialTarget(othersCosPerPc);
        
        
    }

    public void setOtherActualPrc(RichInputText otherActualPrc) {
        this.otherActualPrc = otherActualPrc;
    }

    public RichInputText getOtherActualPrc() {
        return otherActualPrc;
    }

   

    public void setOteherConPcs(RichInputText oteherConPcs) {
        this.oteherConPcs = oteherConPcs;
    }

    public RichInputText getOteherConPcs() {
        return oteherConPcs;
    }

    public void setOthersCosPerPc(RichInputText othersCosPerPc) {
        this.othersCosPerPc = othersCosPerPc;
    }

    public RichInputText getOthersCosPerPc() {
        return othersCosPerPc;
    }

    public void managementCal(int marchand , Row lineRow) {
            
            format.setMinimumFractionDigits(2);
            int check=marchand;
           // System.out.println("ENTER  managementCal ENTER  managementCal ENTER  managementCal ENTER  managementCal ENTER  managementCal ENTER  managementCal");
            ViewObject headerVo=am.getMnjMfgPrecostingHView1();
             ViewObject MnjLineV=am.getMnjMfgPrecostingLView1();
             
            DecimalFormat df = new DecimalFormat("##.00");
            
            double costCal =0.0;
            costCal =  calculateCost(lineRow);
            lineRow.setAttribute("Cost",costCal);
              
            //        try{
            //           profitt =Double.parseDouble((getProfit().getValue().toString()));
            //        }
            //        catch(Exception e) {
            //           profitt=0.00;
            //        }
            //        System.out.println("..........-----------------profit="+profitt);
            
            
            double febricTotal = 0.00;
            double washTotalVal = 0.00;
            double trimTotalVal = 0.00;
            double otherVal=0.00;

            washTotalVal = getSelectedDryTotal(lineRow) + getSelectedWetTotal(lineRow);
            febricTotal=FebricTotal(lineRow);
            trimTotalVal= TrimTotal(lineRow);
            otherVal= othertotal(lineRow);
            
                
//            System.out.println("..........-----------------washTotalVal="+washTotalVal);
//            System.out.println("..........-----------------febricTotal="+febricTotal);
//            System.out.println("..........-----------------trimTotalVal="+trimTotalVal);
//            System.out.println("..........-----------------otherVal="+otherVal);
            
            
            double cmValue=0.00;
            double CM=0.00;
            double finaceValue=0.00;
            double febric=0.00;
            double trim=0.00;
            double wash=0.00;
            double fabricPriceByMrch=0.00;
            double trimsPriceByMrch=0.00;
            double washPriceByMrch=0.00;
            try{
                  fabricPriceByMrch=Double.parseDouble( am.getMnjMfgPrecostingHView1().getCurrentRow().getAttribute("FabricFinance").toString() );
            }
            catch(Exception e){
                fabricPriceByMrch=0.00;
            }
            
            
            try{
                    trimsPriceByMrch=Double.parseDouble( am.getMnjMfgPrecostingHView1().getCurrentRow().getAttribute("TrimFinance").toString() );             
            }
            catch(Exception e){
                trimsPriceByMrch=0.00;
            }
            try{
                washPriceByMrch=Double.parseDouble( am.getMnjMfgPrecostingHView1().getCurrentRow().getAttribute("WashFinance").toString() );          
            }
            catch(Exception e){
                washPriceByMrch=0.00;
            }
            
            febric=febricValue()-fabricPriceByMrch;       
         //   System.out.println("---------------------------------->fabric% "+febricValue()+"adjusted fabric "+febric);
            trim=trimValue()-trimsPriceByMrch;
          //  System.out.println("---------------------------------->trims% "+febricValue()+"adjusted trim "+trim);
            wash=washValue()-washPriceByMrch;
                        
            finaceValue=((febricTotal*febric)/100)+((trimTotalVal*trim)/100)+((washTotalVal*wash)/100);
            //String financeformat=df.format(finaceValue);
            // finaceValue=  Double.parseDouble(financeformat);
            //  finaceValue=  MyMath.round(finaceValue);
            
            
            //vvv  MnjLineV.getCurrentRow().setAttribute("Finance",finaceValue);
            lineRow.setAttribute("Finance",finaceValue);
            
            febricTotal=febricTotal+((febricTotal*fabricPriceByMrch)/100);
            
            trimTotalVal=trimTotalVal+((trimTotalVal*trimsPriceByMrch)/100);
            
            washTotalVal=washTotalVal+((washTotalVal*washPriceByMrch)/100);
            
            System.out.println("trim value after using round="+MyMath.round(trimTotalVal));
            
            lineRow.setAttribute("FabricCost",MyMath.roundTo3(febricTotal));
            
            lineRow.setAttribute("TrimCost",MyMath.roundTo3(trimTotalVal));
            
            lineRow.setAttribute("WashCost",MyMath.roundTo3(washTotalVal)); 
            
            lineRow.setAttribute("Others",MyMath.roundTo3(otherVal));
         
            System.out.println("..........-----------------finaceValue="+finaceValue);
            
            
            try{     
                  CM = Double.parseDouble( lineRow.getAttribute("CmMerchand").toString() );          
            }
            catch(Exception e){
                  CM=0.00;
            }
           
            double samValue=0.00,smvValue=0.00,factoryAvrgEff=0.00,StyleEff=0.00,newCost=0.00;
            try{            
               StyleEff = Double.parseDouble( lineRow.getAttribute("StyleEfficiency").toString() );
                
             }
            catch(Exception e) {
               StyleEff=0.00;
            }
            
            try{
                 samValue = Double.parseDouble( lineRow.getAttribute("Sam").toString() );            
            }
            catch(Exception e) {
               samValue=0.00;
            }
                    
            factoryAvrgEff=FactoryAvgEff();
            smvValue=SMV();
          
            lineRow.setAttribute("FactoryAvgEf", factoryAvrgEff);
            lineRow.setAttribute("Smv",smvValue);
            
            
            if(StyleEff != 0){
                newCost=(((samValue*smvValue)/StyleEff)*factoryAvrgEff);
            }
            // String costformat=df.format(newCost);
            // newCost= Double.parseDouble(costformat);
            newCost=MyMath.round(newCost);
            lineRow.setAttribute("CostNew",newCost);
            
            double totalcost=0.0;       
            totalcost=finaceValue+newCost;        
            lineRow.setAttribute("TotalCost",totalcost);
            
            double calProfit=0.00;
            double setProfit=0.00;
            
            calProfit=MyMath.round(CM-totalcost); 
            cmValue=finaceValue+newCost+calProfit;  
            
            double setCm=0.00;
            double setManagementCm=0.00;
            if(check==1){
                int set=1;
                headerVo.getCurrentRow().setAttribute("CmCalculation",set);
                setCm=cmValue;
                
            }
            try{
                headerVo.getCurrentRow().getAttribute("CmCalculation").toString();
                double prof=0.00;
                try{
                prof =Double.parseDouble(lineRow.getAttribute("Profit").toString());
                }
                catch(Exception e) {
                    prof=calProfit;
                }
                setCm=finaceValue+newCost+prof;  
                setProfit=prof;
                setManagementCm=setCm;
            }
            catch(Exception e) {
                setCm=CM;
               setProfit=calProfit;
                setManagementCm=cmValue;
            }
                        
            lineRow.setAttribute("Profit",setProfit);
            lineRow.setAttribute("Cm",MyMath.round(setCm));
            
            lineRow.setAttribute("CmManagement",MyMath.round(setManagementCm));
            // System.out.println("..........-----------------cmValue="+cmValue);            
             
            double FOB=0.00;
      
             FOB=febricTotal+washTotalVal+trimTotalVal+otherVal+setCm;
            //String fobformat=df.format(FOB);
            // FOB=  Double.parseDouble(fobformat);
        
            calculateAndUpdateFobValues(   FOB, lineRow ); 
           
            AdfFacesContext.getCurrentInstance().addPartialTarget(profit);
            AdfFacesContext.getCurrentInstance().addPartialTarget(subCostingTable);
            AdfFacesContext.getCurrentInstance().addPartialTarget(panelTabbed);
            
        }

    public void setProductivity(RichInputText productivity) {
        this.productivity = productivity;
    }

    public RichInputText getProductivity() {
        return productivity;
    }


    public double FebricTotal(Row lineRow) {


        RowSet child = (RowSet)lineRow.getAttribute("FebricView");
        Row[] r = child.getAllRowsInRange();
       // System.out.println("febric clid row---------------->"+r.length);
        
      double  total=0.00;
      double costperpc=0.00;
      int count =0;
        for (Row row : r) {
            
            oracle.jbo.domain.Number prefix = (Number)row.getAttribute("ItemPrefix"); 
            if ((prefix.intValue() >= 11 && prefix.intValue() <= 12)){
                try{
                  costperpc=  Double.parseDouble( row.getAttribute("CostPerPcs").toString());
                }
                catch(Exception e){
                    costperpc=0.00;
                }
                
                count++;
                total=total+ costperpc;
                
            }
            
           
        }
        
       // System.out.println("febric selected row---------------->"+count);
        return total;
    }

    public double TrimTotal(Row lineRow) {
       
        RowSet child = (RowSet)lineRow.getAttribute("TrimsView");
        Row[] r = child.getAllRowsInRange();
     //   System.out.println("trim clid row---------------->"+r.length);
        
        double  total=0.00;
        double costperpc=0.00;
        int count=0;
       
        for (Row row : r) {
            
            oracle.jbo.domain.Number prefix =
                (Number)row.getAttribute("ItemPrefix"); //ItemPrefix
            if(prefix.intValue() > 12 && prefix.intValue() <= 36) {
                
                try{
                  costperpc=  Double.parseDouble( row.getAttribute("CostPerPcs").toString());
                }
                catch(Exception e){
                    costperpc=0.00;
                }
                // System.out.println("........................,,,,,,trim cost per pcs="+costperpc);
                
                
                
                total=total+ costperpc;
                count++;
            }
            
        }
        
      //  System.out.println("trim total---------------->"+total);
        return total;
        
    }

    public double othertotal(Row lineRow) {

       RowSet child = (RowSet)lineRow.getAttribute("OtherView");
       Row[] r = child.getAllRowsInRange();
       //System.out.println(" other clid row---------------->"+r.length);
        
        double  total=0.00;
        double costperpc=0.00;
        int count=0;
        for (Row row : r) {
            oracle.jbo.domain.Number prefix =
                (Number)row.getAttribute("ItemPrefix"); 
            if (prefix.intValue()==55){
                try{
                  costperpc=  Double.parseDouble( row.getAttribute("CostPerPcs").toString());
                }
                catch(Exception e){
                    costperpc=0.00;
                }
                total=total+ costperpc;
                count++;
            }
            
          
        }
        
       // System.out.println(" other selected row---------------->"+count);
        return total;
        
    }



    public void setStyleEff(RichInputText styleEff) {
        this.styleEff = styleEff;
    }

    public RichInputText getStyleEff() {
        return styleEff;
    }

    public void setSamValue(RichInputText samValue) {
        this.samValue = samValue;
    }

    public RichInputText getSamValue() {
        return samValue;
    }

    public void setFeb_buffer(RichInputText feb_buffer) {
        this.feb_buffer = feb_buffer;
    }

    public RichInputText getFeb_buffer() {
        return feb_buffer;
    }

    public void setTrim_buffer(RichInputText trim_buffer) {
        this.trim_buffer = trim_buffer;
    }

    public RichInputText getTrim_buffer() {
        return trim_buffer;
    }

    public void setOther_bufer(RichInputText other_bufer) {
        this.other_bufer = other_bufer;
    }

    public RichInputText getOther_bufer() {
        return other_bufer;
    }

    public void queryExecute(ActionEvent actionEvent) {
        // Add event code here...
//        ViewObject feb=am.getFebricView1();
//        feb.executeQuery();
//        ViewObject trim=am.getTrimsView1();
//        trim.executeQuery();
//        ViewObject other=am.getOtherView1();
//        other.executeQuery();
        
        
    }

    public String sendToPoc() {
        // Add event code here...
       ViewObject header=am.getMnjMfgPrecostingHView1();
       String yes,confirm;
       yes="Y";
        confirm="Confirmed";
       header.getCurrentRow().setAttribute("TransferStatus", yes);
        header.getCurrentRow().setAttribute("CostingStatus",confirm);
        am.getDBTransaction().commit();
       
        AdfFacesContext.getCurrentInstance().addPartialTarget(confirm_status);
        
        FacesMessage Message = new FacesMessage("Costing Sent to POC");   
        Message.setSeverity(FacesMessage.SEVERITY_INFO);   
        FacesContext fc = FacesContext.getCurrentInstance();   
        fc.addMessage(null, Message); 
        
        return null;
    }

    public void setConfirm_status(RichInputText confirm_status) {
        this.confirm_status = confirm_status;
    }

    public RichInputText getConfirm_status() {
        return confirm_status;
    }

    private double febricValue() {
        
        String name=null;
        String itemType=null;
        ViewObject hvo=am.getMnjMfgPrecostingHView1();
        try {
           itemType= hvo.getCurrentRow().getAttribute("ItemPurchaseType").toString();
        } catch (Exception e) {
            itemType="Fabric"; ;
        }
        if(itemType.equalsIgnoreCase("Imported")){
            name="Fabric-Foreign";
        }else if(itemType.equalsIgnoreCase("Local")){
            name="Fabric-Local";
        }else if(itemType.equalsIgnoreCase("Fabric")){
            name="Fabric";
        }else{
            name="Fabric"; 
        }
        
        System.out.println("ItemPurchaseType is_----------------->"+itemType);
        
        System.out.println("Item name_----------------->"+name);
        double value = 0;
        
        String date=null;
        try {
            date= hvo.getCurrentRow().getAttribute("CreationDate").toString();
        } catch (Exception e) {
            ;
        }
        
        String stmt = "BEGIN :1 := mnj_get_costing_finance_price(:2,:3); end;";
        java.sql.CallableStatement cs =am.getDBTransaction().createCallableStatement(stmt, 1);
        try {
            cs.registerOutParameter(1, OracleTypes.NUMBER);
            cs.setString(2, name);
            cs.setString(3,date);
            cs.execute();
            value = cs.getDouble(1);
            cs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("febric--------------with date---------------------price is"+value);
        return value;
        
         //end of if condition
        
    }

    private double trimValue() {
        String name="Trims";
        double value = 0;
        ViewObject hvo=am.getMnjMfgPrecostingHView1();
        
        String date=null;
        try {
            date= hvo.getCurrentRow().getAttribute("CreationDate").toString();
        } catch (Exception e) {
            ;
        }
        String stmt = "BEGIN :1 := mnj_get_costing_finance_price(:2,:3); end;";
        java.sql.CallableStatement cs =am.getDBTransaction().createCallableStatement(stmt, 1);
        try {
            cs.registerOutParameter(1, OracleTypes.NUMBER);
            cs.setString(2, name);
            cs.setString(3,date);
            cs.execute();
            value = cs.getDouble(1);
            cs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("trims-----------------with date------------------price is"+value);
        return value;
    }

    private double washValue() {
        String name="Wash";
        double value = 0;
        ViewObject hvo=am.getMnjMfgPrecostingHView1();
        
        String date=null;
        try {
            date= hvo.getCurrentRow().getAttribute("CreationDate").toString();
        } catch (Exception e) {
            ;
        }
        String stmt = "BEGIN :1 := mnj_get_costing_finance_price(:2,:3); end;";
        java.sql.CallableStatement cs =am.getDBTransaction().createCallableStatement(stmt, 1);
        try {
            cs.registerOutParameter(1, OracleTypes.NUMBER);
            cs.setString(2, name);
            cs.setString(3,date);
            cs.execute();
            value = cs.getDouble(1);
            cs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("wash-----------------with date------------------price is"+value);
        return value;
    }

    public void setOthersTABLE(RichTable othersTABLE) {
        this.othersTABLE = othersTABLE;
    }

    public RichTable getOthersTABLE() {
        return othersTABLE;
    }

    public void setTrimsTABLE(RichTable trimsTABLE) {
        this.trimsTABLE = trimsTABLE;
    }

    public RichTable getTrimsTABLE() {
        return trimsTABLE;
    }

    public void setPanelTabbed(RichPanelTabbed panelTabbed) {
        this.panelTabbed = panelTabbed;
    }

    public RichPanelTabbed getPanelTabbed() {
        return panelTabbed;
    }

    public void setFabricTABLE(RichTable fabricTABLE) {
        this.fabricTABLE = fabricTABLE;
    }

    public RichTable getFabricTABLE() {
        return fabricTABLE;
    }

    public void setFabricFinancePrice(RichInputText fabricFinancePrice) {
        this.fabricFinancePrice = fabricFinancePrice;
    }

    public RichInputText getFabricFinancePrice() {
        return fabricFinancePrice;
    }

    

    private void populateLineDetails(Row poprow) {
            ViewObject linepopulate=am.getMnjMfgPrecostingLView1();
            ViewObject header=am.getMnjMfgPrecostingHView1();
            ViewObject copyPOCline=am.getPOCLinesCopyVO1();
            //header.getCurrentRow().setAttribute("AgencyCommission",getPopulateValue(poprow,"AgencyCommission"));
           // header.getCurrentRow().setAttribute("FabricFinance",getPopulateValue(poprow,"FabricFinance"));
           // ViewObject vo = am.getTestFillupEOView3(); // in which you wants to populate data
           Row linerow = linepopulate.createRow();
           linerow.setNewRowState(Row.STATUS_INITIALIZED);  
            
            linerow.setAttribute("FabricDesc", getPopulateValue(poprow, "FabricDesc"));
            linerow.setAttribute("FabricContent", getPopulateValue(poprow, "FabricContent")); 
            linerow.setAttribute("WashName", getPopulateValue(poprow, "WashName"));
            linerow.setAttribute("Colour", getPopulateValue(poprow, "Colour"));
            linerow.setAttribute("FabricCost", getPopulateValue(poprow, "FabricCost"));
            linerow.setAttribute("TrimCost", getPopulateValue(poprow, "TrimCost"));
            linerow.setAttribute("WashCost", getPopulateValue(poprow, "WashCost"));
            linerow.setAttribute("Cm",getPopulateValue(poprow, "Cm"));
            // linerow.setAttribute("CmMerchand",getPopulateValue(poprow, "CmMerchand"));
            linerow.setAttribute("FobWithComm", getPopulateValue(poprow, "FobWithComm"));
            linerow.setAttribute("ProdPerLinePerH", getPopulateValue(poprow, "ProdPerLinePerH"));
            linerow.setAttribute("StyleEfficiency", getPopulateValue(poprow, "StyleEfficiency"));
            // linerow.setAttribute("CommisonPrcnt", getPopulateValue(poprow, "CommisonPrcnt"));
            //   header.setAttribute("AgencyCommission", getPopulateValue(poprow, "AgencyCommission"));
            //   linerow.setAttribute("Sam", getPopulateValue(poprow, "Sam"));
            linerow.setAttribute("Shipment", getPopulateValue(poprow,"Shipment"));
            //    linerow.setAttribute("ShipmentValue", getPopulateValue(poprow,"ShipmentValue"));
            // header.getCurrentRow().setAttribute("AgencyCommission",getPopulateValue(poprow,"AgencyCommission"));

            
            AdfFacesContext.getCurrentInstance().addPartialTarget( fabricFinancePrice);
           
            
           // linepopulate.getCurrentRow().setAttribute("WashName", getPopulateValue(poprow, "WashName"));
           
           linepopulate.insertRow(linerow);  
        }
    
    
    
    public String getPopulateValue(Row r, String columnName) {
        //System.out.println("-------------------------------------getPopulateValue------------------------------------------");
        String value = null;
        try {
            value = r.getAttribute(columnName).toString();
            System.out.println(value);
        } catch (Exception e) {
            ;
        }
        return value;
    }

    private void setfabricDescription(Row lineRow) {
                  
                String feb2=null;
                String feb3=null;
                String feb4=null;
                RowSet child = (RowSet)lineRow.getAttribute("FebricView");
                Row[] r = child.getAllRowsInRange();
               // System.out.println("febric clid row---------------->"+r.length);
                
                StringBuilder setVal2 = null;
                setVal2 = new StringBuilder();
                StringBuilder setVal3 = null;
                setVal3 = new StringBuilder();
                StringBuilder setVal4 = null;
                setVal4 = new StringBuilder();
                
                try{
                
                
                int count =0,check=0;
    //            for (Row row : r){
    //                oracle.jbo.domain.Number prefix = (Number)row.getAttribute("ItemPrefix"); 
    //                if ((prefix.intValue() >= 11 && prefix.intValue() <= 12)){
    //                    count++;
    //                }
    //            }
                
                for (Row row : r) {
                    
                    oracle.jbo.domain.Number prefix = (Number)row.getAttribute("ItemPrefix"); 
                    if ((prefix.intValue() >= 11 && prefix.intValue() <= 12)){

                            try{
                              feb2=row.getAttribute("RefCode").toString();
                            }
                            catch(Exception e){
                                feb2=null;
                            }  
                            
                            try{
                              feb3=row.getAttribute("ItemDesc").toString();
                            }
                            catch(Exception e){
                                feb3=null;
                            }   
                               
                    }
                   
                }
                setVal2.append(String.format(feb2));
                setVal4.append(setVal2.toString());
                setVal4.append(String.format(" & "));
                setVal4.append(feb3);
                  
    //            System.out.println("...................... String.format(feb2) = "+String.format(feb2) + "  ------------------   feb2   "+ feb2 ); 
    //            System.out.println("...................... setVal2.toString() = "+ setVal2.toString() + "  ------------------   setVal2  "+ setVal2 );   
                
              //  System.out.println("......................feb cont dec string= "+setVal4.toString());
                    
                lineRow.setAttribute("FabricDesc", setVal2.toString() );
                lineRow.setAttribute("FabricContent",setVal4.toString());
                AdfFacesContext.getCurrentInstance().addPartialTarget(article_value);
                    
            }
            catch(Exception e){
                ;
            }        
            
            // String TBA=String.valueOf(TB);
                             
        }

    public void setFabricConForLine(RichInputText fabricConForLine) {
        this.fabricConForLine = fabricConForLine;
    }

    public RichInputText getFabricConForLine() {
        return fabricConForLine;
    }



    public void setArticle_value(RichInputText article_value) {
        this.article_value = article_value;
    }

    public RichInputText getArticle_value() {
        return article_value;
    }

    public void summaryReportFilter(ActionEvent actionEvent) {
        // Add event code here...
        ViewObject ovj =  am.getdetailsReport1();
        ViewObject searchVO=am.getsearchVIEW1();
        String buyer;
        String season;
         
        // System.out.println(".........................................."+buyer);
        // System.out.println(".........................................."+season);
        try{
           buyer=searchVO.getCurrentRow().getAttribute("Buyer").toString();
        }
        catch(Exception e) {
          buyer=null;
        }
          try{
               season=searchVO.getCurrentRow().getAttribute("Season").toString();
          }
          catch(Exception e) {
              season=null;
          }
          
          System.out.println(".........................................."+buyer); 
          System.out.println(".........................................."+season); 
          
        //  ovj.setWhereClause("SEASON = '"+season+"'");
        
           ovj.executeQuery();
          // ovj.setWhereClause(null);
          
          
           
        
    }

    public String filterMethod() {
        // Add event code here...
        ViewObject ovj = am.getdetailsReport1();
        ViewObject searchVO = am.getFilterView1();
        String buyer, season, unit, fit, itemDescription, enduser, brand, buyerId;
        String filter = null;
        StringBuilder setVal = null;
        setVal = new StringBuilder();
        String[] array = new String[7];
        int i = 0;

        try {
            buyerId = searchVO.getCurrentRow().getAttribute("BuyerId").toString();
            //  String buyerwhrcls="BUYER =byer;
            array[i] = "BUYER_ID = '" + buyerId + "'";
            i++;
            //                 setVal.append(String.format("BUYER = '"+buyer+"'"));
            //                 filter=setVal.toString();
            //                 setVal=null;
        } catch (Exception e) {
            buyerId = null;
        }

        try {
            season =
                    searchVO.getCurrentRow().getAttribute("Season").toString();
            array[i] = "SEASON = '" + season + "'";
            i++;
        } catch (Exception e) {
            season = null;
        }

        try {
            unit = searchVO.getCurrentRow().getAttribute("Unit").toString();
            array[i] = "PRODUCTION_UNIT = '" + unit + "'";
            i++;
        } catch (Exception e) {
            unit = null;
        }
        try {
            fit = searchVO.getCurrentRow().getAttribute("Fit").toString();
            array[i] = "FIT= '" + fit + "'";
            i++;
        } catch (Exception e) {
            fit = null;
        }
        try {
            enduser =
                    searchVO.getCurrentRow().getAttribute("EndUser").toString();
            array[i] = "END_USER= '" + enduser + "'";
            i++;
        } catch (Exception e) {
            enduser = null;
        }
        try {
            itemDescription =
                    searchVO.getCurrentRow().getAttribute("ItemDescription").toString();
            array[i] = "ITEM_DESCRIPTION= '" + itemDescription + "'";
            i++;
        } catch (Exception e) {
            itemDescription = null;
        }
        try {
            brand =
                    searchVO.getCurrentRow().getAttribute("Brand").toString();
            array[i] = "BRAND = '" + brand + "'";
            i++;
        } catch (Exception e) {
            brand = null;
        }
        for (int j = 0; j < i; j++) {
            if(array[j]==null) {
                
                break;
            }
            setVal.append(String.format(array[j])); 
            if(j+1!=i){
                setVal.append(String.format(" AND "));
            }


        }

        filter = setVal.toString();
        System.out.println(filter);
        
        int pram=1;
        ovj.setNamedWhereClauseParam("param",pram);
        ovj.setWhereClause(filter);
        System.out.println("Query:" + "\n" + ovj.getQuery());
        ovj.executeQuery();
//        ovj.setWhereClause("BUYER = '" + buyer + "' AND SEASON = '" + season +
//                           "'");
        return null;
    }

    public void setTrimsFinance(RichInputText trimsFinance) {
        this.trimsFinance = trimsFinance;
    }

    public RichInputText getTrimsFinance() {
        return trimsFinance;
    }

    public void setWashFinanceValue(RichInputText washFinanceValue) {
        this.washFinanceValue = washFinanceValue;
    }

    public RichInputText getWashFinanceValue() {
        return washFinanceValue;
    }

    public void dofilterOrderVsSale(ActionEvent actionEvent) {
        
        ViewObject ovj =  am.getorderVsSaleVO1();
        ViewObject searchVO=am.getsearchVIEW1();
        String buyer;
        String season;
         
        // System.out.println(".........................................."+buyer);
        // System.out.println(".........................................."+season);
        try{
           buyer=searchVO.getCurrentRow().getAttribute("Buyer").toString();
        }
        catch(Exception e) {
          buyer=null;
        }
          try{
               season=searchVO.getCurrentRow().getAttribute("Season").toString();
          }
          catch(Exception e) {
              season=null;
          }
          int pram=1;
          System.out.println(".........................................."+buyer); 
          System.out.println(".........................................."+season); 
         ovj.setNamedWhereClauseParam("param",pram);
         ovj.setWhereClause("BUYER = '"+buyer+"' AND SEASON = '"+season+"'" );
        //  ovj.setWhereClause("SEASON = '"+season+"'");
        
           ovj.executeQuery();
        AdfFacesContext.getCurrentInstance().addPartialTarget(orderVsSaleTable);
          
          
       
    }

    public void setOrderVsSaleTable(RichTable orderVsSaleTable) {
        this.orderVsSaleTable = orderVsSaleTable;
    }

    public RichTable getOrderVsSaleTable() {
        return orderVsSaleTable;
    }

    public void popupFatchListenerForPOdetails(PopupFetchEvent popupFetchEvent) {
        // Add event code here...
        
        ViewObject poDetailsVO=am.getorderDetailsVO1();
        poDetailsVO.executeQuery();
        AdfFacesContext.getCurrentInstance().addPartialTarget(poDetailsTable);
    }

    public void setPoDetailsTable(RichTable poDetailsTable) {
        this.poDetailsTable = poDetailsTable;
    }

    public RichTable getPoDetailsTable() {
        return poDetailsTable;
    }

    public void washCostSearch(ActionEvent actionEvent) {
        // Add event code here...
        ViewObject ovj =  am.getwashCostReport1();
        ViewObject searchVO=am.getsearchVIEW1();
        String buyer;
        String season;
         
        // System.out.println(".........................................."+buyer);
        // System.out.println(".........................................."+season);
        try{
           buyer=searchVO.getCurrentRow().getAttribute("Buyer").toString();
        }
        catch(Exception e) {
          buyer=null;
        }
          try{
               season=searchVO.getCurrentRow().getAttribute("Season").toString();
          }
          catch(Exception e) {
              season=null;
          }
          int pram=1;
          System.out.println(".........................................."+buyer); 
          System.out.println(".........................................."+season); 
         ovj.setNamedWhereClauseParam("param",pram);
         ovj.setWhereClause("BUYER_NAME = '"+buyer+"' AND SEASON = '"+season+"'" );
        //  ovj.setWhereClause("SEASON = '"+season+"'");
        
           ovj.executeQuery();
        AdfFacesContext.getCurrentInstance().addPartialTarget(washCostTable);
        
    }

    public void setWashCostTable(RichTable washCostTable) {
        this.washCostTable = washCostTable;
    }

    public RichTable getWashCostTable() {
        return washCostTable;
    }
    
    
    public void onQueryPageLoad(PhaseEvent phaseEvent) {
        // Add event code here...
        //this method is  executed when the Query page is loaded and restrics of quereing and bringing any row of the table
        Row r= null;
        
        try {
           r = am.getMnjMfgPrecostingHView1().getCurrentRow();
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
      
            if (r == null) {
                 System.out.println("r is null");
            }else {
                System.out.println("r is not null");
            }
     
        if (!AdfFacesContext.getCurrentInstance().isPostback() &&  r==null ) {       

          this.am.getMnjMfgPrecostingHView1().executeEmptyRowSet();
        

        }
    //
        
    }
    
    
    private String checkMendatoryFields() {
        
        String message = "ok";
        ViewObject headerVo = am.getMnjMfgPrecostingHView1();
        RowSet lineRowSet = (RowSet)headerVo.getCurrentRow().getAttribute("MnjMfgPrecostingLView");
            Row currentHeaderRow =  headerVo.getCurrentRow();
            Row lineRow ; 
            
            if(currentHeaderRow.getAttribute("FabricFinance") == null
              || currentHeaderRow.getAttribute("WashFinance") == null
             || currentHeaderRow.getAttribute("TrimFinance") == null
            || currentHeaderRow.getAttribute("ItemPurchaseType") == null)
            {
                message = "Fabric Finance, Wash Finance, Trim Finance or Item Purchase Type  can't be empty!" ;
                return message;
            }
            
            while (lineRowSet.hasNext()){
                lineRow = lineRowSet.next();
                if(lineRow.getAttribute("StyleEfficiency") == null || lineRow.getAttribute("Sam") == null  ){
                    
                    message = " StyleEfficiency or Sam can't be empty for subcosting no:"+ lineRow.getAttribute("HeaderNumber")
                        + ", Wash: "+lineRow.getAttribute("WashName") + ", Color: "+lineRow.getAttribute("Colour");
                  //  System.out.println(lineRow.getAttribute("FabricDesc"));       
                    am.getMnjMfgPrecostingLView1().setCurrentRow(lineRow);
                  //  System.out.println(am.getMnjMfgPrecostingLView1().getCurrentRow().getAttribute("FabricDesc"));           
                    return message;
                }                
            }
      
        return message;
    }
    
    
    public static void showMessage(String messege , String severity ) {
        
        
        FacesMessage fm = new FacesMessage(messege);
        
        if(severity.equals("info")){
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
        }
        else if(severity.equals("warn")){
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
        }
        else if(severity.equals("error")){
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
        }
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
        
    }
    
    
    public void refreshQueryKeepingCurrentRow(ViewObject viewObject )  {
        
        
         Row currentRow;
         Key currentRowKey;
         
         // added on 7.jan.18 to handle exception if view object has no current row
        try{
           currentRow = viewObject.getCurrentRow();
           currentRowKey = currentRow.getKey();
        }
        catch(Exception e){
            return;
        }
        
     
       
      
       int rangePosOfCurrentRow = viewObject.getRangeIndexOf(currentRow);
       int rangeStartBeforeQuery = viewObject.getRangeStart();
       viewObject.executeQuery();
       viewObject.setRangeStart(rangeStartBeforeQuery);
       /*
        * In 10.1.2, there is this convenience method we could use
        * instead of the remaining lines of code
        *
        *  findAndSetCurrentRowByKey(currentRowKey,rangePosOfCurrentRow);
        *  
        */
       
         
       Row[] rows = viewObject.findByKey(currentRowKey, 1);
       if (rows != null && rows.length == 1)
       {
          viewObject.scrollRangeTo(rows[0], rangePosOfCurrentRow);
          viewObject.setCurrentRowAtRangeIndex(viewObject.getRangeIndexOf(rows[0]));
       }
       
               
     }
    
    private double calculateCost(Row lineRow) {

          double costCal=0.00;
                 double productivity=0.00;
                 double production=0.00;
                 try{
                     // production =Double.parseDouble((getProdCostPerLine().getValue().toString())); 
                     // System.out.println("5555=======================   production old "+ getProdCostPerLine().getValue());
                     
                      production   = Double.parseDouble( lineRow.getAttribute("ProdCostLinePerH").toString());
              
                 }
                 catch(Exception e) {
                     production=0.00;
                 }
                 try{
                     
                     //productivity =Double.parseDouble((getProductivity().getValue().toString())); 
                     
                    // System.out.println("5555=======================    productivity old "+ productivity );
                     productivity = Double.parseDouble( lineRow.getAttribute("ProdCostLinePerH").toString());
             
                 }
                 catch(Exception e) {
                     productivity =0.00;
                 }
                 if(productivity !=0){

                    costCal=production/productivity;
                 }
                 else{
                     costCal=0.00;
                 }

              return costCal ;
              


         }



    private void calculateAndUpdateFobValues(Double FOB, Row lineRow) {
    
       FOB=Double.parseDouble(format.format(FOB));

        
        double commVal =0.0; // MyMath.numeric3(getCommission());
        try {
            commVal=Double.parseDouble(lineRow.getAttribute("CommisonPrcnt").toString());
        }
        catch(Exception e){
            commVal=0.0;
        }
        double fobWitcommVal = (FOB * commVal / 100) + FOB;
        
        
        //vvv   double orderQtyVal = MyMath.numeric(getOrderQty());
        double   orderQtyVal=0  ;
        
        try {
            orderQtyVal = Double.parseDouble(am.getMnjMfgPrecostingHView1().getCurrentRow().getAttribute("OrderQty").toString())   ;
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }      
      //  System.out.println("555======================    orderQtyVal old  " +MyMath.numeric(getOrderQty()) + "   orderQtyVal new         " +orderQtyVal );
        
        //vvv  double smsQtyVal = MyMath.numeric3(getSMSQtyFromTable());
        
          double smsQtyVal =0;
        
        try {
            smsQtyVal = Double.parseDouble(  lineRow.getAttribute("SmsQty").toString());
        } catch (Exception e) {
            // TODO: Add catch code
              smsQtyVal=0;
          //  e.printStackTrace();
        }
        // System.out.println("555======================    smsQtyVal old  " +MyMath.numeric3(getSMSQtyFromTable()) + "   smsQtyVal new         " + smsQtyVal); 
          
          double fobWithSmsVal = 0.00;
          
        //        vvv double targetPrice = MyMath.numeric(getTargetPrice());
         double targetPrice ;
        
        try {
            targetPrice = Double.parseDouble(  lineRow.getAttribute("TargetPrice").toString());
        } catch (Exception e) {
            // TODO: Add catch code
          targetPrice = 0.0;
            
         ///   e.printStackTrace();
        }

        
        
        try {
            if (orderQtyVal > 0)
            fobWithSmsVal =((fobWitcommVal) * orderQtyVal + (fobWitcommVal) * smsQtyVal) /orderQtyVal;
            else fobWithSmsVal = 0;
        } catch (Exception e) {
           
           fobWithSmsVal =0.0 ;
            System.out.println("Exception value --===================->"+fobWithSmsVal);
        }


        if (fobWithSmsVal > fobWitcommVal) {
            lineRow.setAttribute("FobWithComm",
                                 Double.parseDouble(format.format(fobWithSmsVal)));

            lineRow.setAttribute("Difference",
                                 Double.parseDouble(format.format(fobWithSmsVal)) -
                                 targetPrice);
        } else {

            lineRow.setAttribute("FobWithComm",
                                 Double.parseDouble(format.format(fobWitcommVal)));
            lineRow.setAttribute("Difference",
                                 Double.parseDouble(format.format(fobWitcommVal)) -
                                 targetPrice);
        }
        lineRow.setAttribute("FobWithSmsSample",
                             Double.parseDouble(format.format(fobWithSmsVal)));

        lineRow.setAttribute("FobWithComm", FOB);
        lineRow.setAttribute("Fob",
                             Double.parseDouble(format.format(fobWitcommVal)));

        //        System.out.println("..........-----------------samValue="+samValue);
        //        System.out.println("..........-----------------smvValue="+smvValue);
        //        System.out.println("..........-----------------factoryAvrgEff="+factoryAvrgEff);
        //        System.out.println("..........-----------------StyleEff="+StyleEff);
        //        System.out.println("..........-----------------newCost="+newCost);
    }
    
    
    
 
}//end of class
