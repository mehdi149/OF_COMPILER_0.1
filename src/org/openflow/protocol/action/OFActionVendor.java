package org.openflow.protocol.action;

import java.nio.ByteBuffer;

/**
 *
 * @author David Erickson (daviderickson@cs.stanford.edu)
 */
public class OFActionVendor extends OFAction {
    public static int MINIMUM_LENGTH = 8;

    protected int vendor;

    public OFActionVendor() {
        super();
        super.setType(OFActionType.VENDOR);
        super.setLength((short) MINIMUM_LENGTH);
    }

    /**
     * @return the vendor
     */
    public int getVendor() {
        return vendor;
    }

    /**
     * @param vendor the vendor to set
     */
    public void setVendor(int vendor) {
        this.vendor = vendor;
    }

    
    public void readFrom(ByteBuffer data) {
        super.readFrom(data);
        this.vendor = data.getInt();
    }

    
    public void writeTo(ByteBuffer data) {
        super.writeTo(data);
        data.putInt(this.vendor);
    }

    
    public int hashCode() {
        final int prime = 379;
        int result = super.hashCode();
        result = prime * result + vendor;
        return result;
    }

    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof OFActionVendor)) {
            return false;
        }
        OFActionVendor other = (OFActionVendor) obj;
        if (vendor != other.vendor) {
            return false;
        }
        return true;
    }
}
