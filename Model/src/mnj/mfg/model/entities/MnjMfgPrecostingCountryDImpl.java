package mnj.mfg.model.entities;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.TransactionEvent;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Aug 22 16:16:10 BDT 2015
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class MnjMfgPrecostingCountryDImpl extends EntityImpl {
    private static EntityDefImpl mDefinitionObject;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        DetailId {
            public Object get(MnjMfgPrecostingCountryDImpl obj) {
                return obj.getDetailId();
            }

            public void put(MnjMfgPrecostingCountryDImpl obj, Object value) {
                obj.setDetailId((Number)value);
            }
        }
        ,
        LineId {
            public Object get(MnjMfgPrecostingCountryDImpl obj) {
                return obj.getLineId();
            }

            public void put(MnjMfgPrecostingCountryDImpl obj, Object value) {
                obj.setLineId((Number)value);
            }
        }
        ,
        Country {
            public Object get(MnjMfgPrecostingCountryDImpl obj) {
                return obj.getCountry();
            }

            public void put(MnjMfgPrecostingCountryDImpl obj, Object value) {
                obj.setCountry((String)value);
            }
        }
        ,
        Rate {
            public Object get(MnjMfgPrecostingCountryDImpl obj) {
                return obj.getRate();
            }

            public void put(MnjMfgPrecostingCountryDImpl obj, Object value) {
                obj.setRate((Number)value);
            }
        }
        ,
        MnjMfgPrecostingL {
            public Object get(MnjMfgPrecostingCountryDImpl obj) {
                return obj.getMnjMfgPrecostingL();
            }

            public void put(MnjMfgPrecostingCountryDImpl obj, Object value) {
                obj.setMnjMfgPrecostingL((MnjMfgPrecostingLImpl)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(MnjMfgPrecostingCountryDImpl object);

        public abstract void put(MnjMfgPrecostingCountryDImpl object,
                                 Object value);

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        public static AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }
    public static final int DETAILID = AttributesEnum.DetailId.index();
    public static final int LINEID = AttributesEnum.LineId.index();
    public static final int COUNTRY = AttributesEnum.Country.index();
    public static final int RATE = AttributesEnum.Rate.index();
    public static final int MNJMFGPRECOSTINGL = AttributesEnum.MnjMfgPrecostingL.index();

    /**
     * This is the default constructor (do not remove).
     */
    public MnjMfgPrecostingCountryDImpl() {
    }

    /**
     * Gets the attribute value for DetailId, using the alias name DetailId.
     * @return the DetailId
     */
    public Number getDetailId() {
        return (Number)getAttributeInternal(DETAILID);
    }

    /**
     * Sets <code>value</code> as the attribute value for DetailId.
     * @param value value to set the DetailId
     */
    public void setDetailId(Number value) {
        setAttributeInternal(DETAILID, value);
    }

    /**
     * Gets the attribute value for LineId, using the alias name LineId.
     * @return the LineId
     */
    public Number getLineId() {
        return (Number)getAttributeInternal(LINEID);
    }

    /**
     * Sets <code>value</code> as the attribute value for LineId.
     * @param value value to set the LineId
     */
    public void setLineId(Number value) {
        setAttributeInternal(LINEID, value);
    }

    /**
     * Gets the attribute value for Country, using the alias name Country.
     * @return the Country
     */
    public String getCountry() {
        return (String)getAttributeInternal(COUNTRY);
    }

    /**
     * Sets <code>value</code> as the attribute value for Country.
     * @param value value to set the Country
     */
    public void setCountry(String value) {
        setAttributeInternal(COUNTRY, value);
    }

    /**
     * Gets the attribute value for Rate, using the alias name Rate.
     * @return the Rate
     */
    public Number getRate() {
        return (Number)getAttributeInternal(RATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for Rate.
     * @param value value to set the Rate
     */
    public void setRate(Number value) {
        setAttributeInternal(RATE, value);
    }

    /**
     * getAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param attrDef the attribute

     * @return the attribute value
     * @throws Exception
     */
    protected Object getAttrInvokeAccessor(int index,
                                           AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            return AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].get(this);
        }
        return super.getAttrInvokeAccessor(index, attrDef);
    }

    /**
     * setAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param value the value to assign to the attribute
     * @param attrDef the attribute

     * @throws Exception
     */
    protected void setAttrInvokeAccessor(int index, Object value,
                                         AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].put(this, value);
            return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
    }

    /**
     * @return the associated entity MnjMfgPrecostingLImpl.
     */
    public MnjMfgPrecostingLImpl getMnjMfgPrecostingL() {
        return (MnjMfgPrecostingLImpl)getAttributeInternal(MNJMFGPRECOSTINGL);
    }

    /**
     * Sets <code>value</code> as the associated entity MnjMfgPrecostingLImpl.
     */
    public void setMnjMfgPrecostingL(MnjMfgPrecostingLImpl value) {
        setAttributeInternal(MNJMFGPRECOSTINGL, value);
    }

    /**
     * @param detailId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number detailId) {
        return new Key(new Object[]{detailId});
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = EntityDefImpl.findDefObject("mnj.mfg.model.entities.MnjMfgPrecostingCountryD");
        }
        return mDefinitionObject;
    }

    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {
        super.create(attributeList);        
        oracle.jbo.server.SequenceImpl s =new oracle.jbo.server.SequenceImpl("MNJ_MFG_PRECOSTING_COUNTRY_D_S",getDBTransaction());
        oracle.jbo.domain.Number sVal = s.getSequenceNumber();
        setDetailId(sVal);
    }

    /**
     * Add entity remove logic in this method.
     */
    public void remove() {
        super.remove();
    }

    /**
     * Add locking logic here.
     */
    public void lock() {
        super.lock();
    }

    /**
     * Custom DML update/insert/delete logic here.
     * @param operation the operation type
     * @param e the transaction event
     */
    protected void doDML(int operation, TransactionEvent e) {
        super.doDML(operation, e);
    }
}
