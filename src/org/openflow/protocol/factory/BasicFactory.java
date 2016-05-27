package org.openflow.protocol.factory;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.openflow.protocol.OFMessage;
import org.openflow.protocol.OFType;
import org.openflow.protocol.action.OFAction;
import org.openflow.protocol.action.OFActionType;
import org.openflow.protocol.statistics.OFStatistics;
import org.openflow.protocol.statistics.OFStatisticsType;
import org.openflow.protocol.statistics.OFVendorStatistics;


/**
 * A basic OpenFlow factory that supports naive creation of both Messages and
 * Actions.
 *
 * @author David Erickson (daviderickson@cs.stanford.edu)
 * @author Rob Sherwood (rob.sherwood@stanford.edu)
 *
 */
public class BasicFactory implements OFMessageFactory, OFActionFactory,
        OFStatisticsFactory {
    
    public OFMessage getMessage(OFType t) {
        return t.newInstance();
    }

    
    public List<OFMessage> parseMessages(ByteBuffer data) {
        return parseMessages(data, 0);
    }

    
    public List<OFMessage> parseMessages(ByteBuffer data, int limit) {
        List<OFMessage> results = new ArrayList<OFMessage>();
        OFMessage demux = new OFMessage();
        OFMessage ofm;

        while (limit == 0 || results.size() <= limit) {
            if (data.remaining() < OFMessage.MINIMUM_LENGTH)
                return results;

            data.mark();
            demux.readFrom(data);
            data.reset();

            if (demux.getLengthU() > data.remaining())
                return results;

            ofm = getMessage(demux.getType());
            if (ofm instanceof OFActionFactoryAware) {
                ((OFActionFactoryAware)ofm).setActionFactory(this);
            }
            if (ofm instanceof OFMessageFactoryAware) {
                ((OFMessageFactoryAware)ofm).setMessageFactory(this);
            }
            if (ofm instanceof OFStatisticsFactoryAware) {
                ((OFStatisticsFactoryAware)ofm).setStatisticsFactory(this);
            }
            ofm.readFrom(data);
            if (OFMessage.class.equals(ofm.getClass())) {
                // advance the position for un-implemented messages
                data.position(data.position()+(ofm.getLengthU() -
                        OFMessage.MINIMUM_LENGTH));
            }
            results.add(ofm);
        }

        return results;
    }

    
    public OFAction getAction(OFActionType t) {
        return t.newInstance();
    }

    
    public List<OFAction> parseActions(ByteBuffer data, int length) {
        return parseActions(data, length, 0);
    }

    
    public List<OFAction> parseActions(ByteBuffer data, int length, int limit) {
        List<OFAction> results = new ArrayList<OFAction>();
        OFAction demux = new OFAction();
        OFAction ofa;
        int end = data.position() + length;

        while (limit == 0 || results.size() <= limit) {
            if (data.remaining() < OFAction.MINIMUM_LENGTH ||
                    (data.position() + OFAction.MINIMUM_LENGTH) > end)
                return results;

            data.mark();
            demux.readFrom(data);
            data.reset();

            if (demux.getLengthU() > data.remaining() ||
                    (data.position() + demux.getLengthU()) > end)
                return results;

            ofa = getAction(demux.getType());
            ofa.readFrom(data);
            if (OFAction.class.equals(ofa.getClass())) {
                // advance the position for un-implemented messages
                data.position(data.position()+(ofa.getLengthU() -
                        OFAction.MINIMUM_LENGTH));
            }
            results.add(ofa);
        }

        return results;
    }

    
    public OFActionFactory getActionFactory() {
        return this;
    }

    
    public OFStatistics getStatistics(OFType t, OFStatisticsType st) {
        return st.newInstance(t);
    }

    
    public List<OFStatistics> parseStatistics(OFType t, OFStatisticsType st,
            ByteBuffer data, int length) {
        return parseStatistics(t, st, data, length, 0);
    }

    /**
     * @param t
     *            OFMessage type: should be one of stats_request or stats_reply
     * @param st
     *            statistics type of this message, e.g., DESC, TABLE
     * @param data
     *            buffer to read from
     * @param length
     *            length of statistics
     * @param limit
     *            number of statistics to grab; 0 == all
     * 
     * @return list of statistics
     */

    
    public List<OFStatistics> parseStatistics(OFType t, OFStatisticsType st,
            ByteBuffer data, int length, int limit) {
        List<OFStatistics> results = new ArrayList<OFStatistics>();
        OFStatistics statistics = getStatistics(t, st);

        int start = data.position();
        int count = 0;

        while (limit == 0 || results.size() <= limit) {
            // TODO Create a separate MUX/DEMUX path for vendor stats
            if (statistics instanceof OFVendorStatistics)
                ((OFVendorStatistics)statistics).setLength(length);

            /**
             * can't use data.remaining() here, b/c there could be other data
             * buffered past this message
             */
            if ((length - count) >= statistics.getLength()) {
                if (statistics instanceof OFActionFactoryAware)
                    ((OFActionFactoryAware)statistics).setActionFactory(this);
                statistics.readFrom(data);
                results.add(statistics);
                count += statistics.getLength();
                statistics = getStatistics(t, st);
            } else {
                if (count < length) {
                    /**
                     * Nasty case: partial/incomplete statistic found even
                     * though we have a full message. Found when NOX sent
                     * agg_stats request with wrong agg statistics length (52
                     * instead of 56)
                     * 
                     * just throw the rest away, or we will break framing
                     */
                    data.position(start + length);
                }
                return results;
            }
        }
        return results; // empty; no statistics at all
    }
}
