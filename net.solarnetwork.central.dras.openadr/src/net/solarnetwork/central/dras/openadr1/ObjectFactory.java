//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.04.29 at 01:11:32 PM NZST 
//


package net.solarnetwork.central.dras.openadr1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the net.solarnetwork.central.dras.openadr1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetEventStates_QNAME = new QName("http://www.openadr.org/DRAS/DRASClientSOAP/", "GetEventStates");
    private final static QName _SetEventStateConfirmation_QNAME = new QName("http://www.openadr.org/DRAS/DRASClientSOAP/", "SetEventStateConfirmation");
    private final static QName _SetEventStateConfirmationResponse_QNAME = new QName("http://www.openadr.org/DRAS/DRASClientSOAP/", "SetEventStateConfirmationResponse");
    private final static QName _EventState_QNAME = new QName("http://www.openadr.org/DRAS/EventState", "eventState");
    private final static QName _EventStatus_QNAME = new QName("http://www.openadr.org/DRAS/EventState", "EventStatus");
    private final static QName _GetEventStatesResponse_QNAME = new QName("http://www.openadr.org/DRAS/DRASClientSOAP/", "GetEventStatesResponse");
    private final static QName _OperationModeValue_QNAME = new QName("http://www.openadr.org/DRAS/EventState", "OperationModeValue");
    private final static QName _EventStateDrEventData_QNAME = new QName("http://www.openadr.org/DRAS/EventState", "drEventData");
    private final static QName _EventStateCustomData_QNAME = new QName("http://www.openadr.org/DRAS/EventState", "customData");
    private final static QName _SimpleClientEventDataOperationModeSchedule_QNAME = new QName("http://www.openadr.org/DRAS/EventState", "operationModeSchedule");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: net.solarnetwork.central.dras.openadr1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OperationState }
     * 
     */
    public OperationState createOperationState() {
        return new OperationState();
    }

    /**
     * Create an instance of {@link GetEventStates }
     * 
     */
    public GetEventStates createGetEventStates() {
        return new GetEventStates();
    }

    /**
     * Create an instance of {@link SmartClientDREventData }
     * 
     */
    public SmartClientDREventData createSmartClientDREventData() {
        return new SmartClientDREventData();
    }

    /**
     * Create an instance of {@link GeneralInfoInstance }
     * 
     */
    public GeneralInfoInstance createGeneralInfoInstance() {
        return new GeneralInfoInstance();
    }

    /**
     * Create an instance of {@link GetEventStatesResponse }
     * 
     */
    public GetEventStatesResponse createGetEventStatesResponse() {
        return new GetEventStatesResponse();
    }

    /**
     * Create an instance of {@link EventInfoValue }
     * 
     */
    public EventInfoValue createEventInfoValue() {
        return new EventInfoValue();
    }

    /**
     * Create an instance of {@link EventStateConfirmation }
     * 
     */
    public EventStateConfirmation createEventStateConfirmation() {
        return new EventStateConfirmation();
    }

    /**
     * Create an instance of {@link SetEventStateConfirmation }
     * 
     */
    public SetEventStateConfirmation createSetEventStateConfirmation() {
        return new SetEventStateConfirmation();
    }

    /**
     * Create an instance of {@link EventState.CustomData }
     * 
     */
    public EventState.CustomData createEventStateCustomData() {
        return new EventState.CustomData();
    }

    /**
     * Create an instance of {@link SetEventStateConfirmationResponse }
     * 
     */
    public SetEventStateConfirmationResponse createSetEventStateConfirmationResponse() {
        return new SetEventStateConfirmationResponse();
    }

    /**
     * Create an instance of {@link GeneralInfoValue }
     * 
     */
    public GeneralInfoValue createGeneralInfoValue() {
        return new GeneralInfoValue();
    }

    /**
     * Create an instance of {@link SimpleClientEventData }
     * 
     */
    public SimpleClientEventData createSimpleClientEventData() {
        return new SimpleClientEventData();
    }

    /**
     * Create an instance of {@link EventState }
     * 
     */
    public EventState createEventState() {
        return new EventState();
    }

    /**
     * Create an instance of {@link EventInfoInstance }
     * 
     */
    public EventInfoInstance createEventInfoInstance() {
        return new EventInfoInstance();
    }

    /**
     * Create an instance of {@link SimpleClientEventData.OperationModeSchedule }
     * 
     */
    public SimpleClientEventData.OperationModeSchedule createSimpleClientEventDataOperationModeSchedule() {
        return new SimpleClientEventData.OperationModeSchedule();
    }

    /**
     * Create an instance of {@link ListOfEventStates }
     * 
     */
    public ListOfEventStates createListOfEventStates() {
        return new ListOfEventStates();
    }

    /**
     * Create an instance of {@link net.solarnetwork.central.dras.openadr1.CustomData }
     * 
     */
    public net.solarnetwork.central.dras.openadr1.CustomData createCustomData() {
        return new net.solarnetwork.central.dras.openadr1.CustomData();
    }

    /**
     * Create an instance of {@link EventInfoTypeID }
     * 
     */
    public EventInfoTypeID createEventInfoTypeID() {
        return new EventInfoTypeID();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEventStates }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openadr.org/DRAS/DRASClientSOAP/", name = "GetEventStates")
    public JAXBElement<GetEventStates> createGetEventStates(GetEventStates value) {
        return new JAXBElement<GetEventStates>(_GetEventStates_QNAME, GetEventStates.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetEventStateConfirmation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openadr.org/DRAS/DRASClientSOAP/", name = "SetEventStateConfirmation")
    public JAXBElement<SetEventStateConfirmation> createSetEventStateConfirmation(SetEventStateConfirmation value) {
        return new JAXBElement<SetEventStateConfirmation>(_SetEventStateConfirmation_QNAME, SetEventStateConfirmation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetEventStateConfirmationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openadr.org/DRAS/DRASClientSOAP/", name = "SetEventStateConfirmationResponse")
    public JAXBElement<SetEventStateConfirmationResponse> createSetEventStateConfirmationResponse(SetEventStateConfirmationResponse value) {
        return new JAXBElement<SetEventStateConfirmationResponse>(_SetEventStateConfirmationResponse_QNAME, SetEventStateConfirmationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EventState }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openadr.org/DRAS/EventState", name = "eventState")
    public JAXBElement<EventState> createEventState(EventState value) {
        return new JAXBElement<EventState>(_EventState_QNAME, EventState.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openadr.org/DRAS/EventState", name = "EventStatus")
    public JAXBElement<String> createEventStatus(String value) {
        return new JAXBElement<String>(_EventStatus_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEventStatesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openadr.org/DRAS/DRASClientSOAP/", name = "GetEventStatesResponse")
    public JAXBElement<GetEventStatesResponse> createGetEventStatesResponse(GetEventStatesResponse value) {
        return new JAXBElement<GetEventStatesResponse>(_GetEventStatesResponse_QNAME, GetEventStatesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openadr.org/DRAS/EventState", name = "OperationModeValue")
    public JAXBElement<String> createOperationModeValue(String value) {
        return new JAXBElement<String>(_OperationModeValue_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SmartClientDREventData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openadr.org/DRAS/EventState", name = "drEventData", scope = EventState.class)
    public JAXBElement<SmartClientDREventData> createEventStateDrEventData(SmartClientDREventData value) {
        return new JAXBElement<SmartClientDREventData>(_EventStateDrEventData_QNAME, SmartClientDREventData.class, EventState.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EventState.CustomData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openadr.org/DRAS/EventState", name = "customData", scope = EventState.class)
    public JAXBElement<EventState.CustomData> createEventStateCustomData(EventState.CustomData value) {
        return new JAXBElement<EventState.CustomData>(_EventStateCustomData_QNAME, EventState.CustomData.class, EventState.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimpleClientEventData.OperationModeSchedule }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openadr.org/DRAS/EventState", name = "operationModeSchedule", scope = SimpleClientEventData.class)
    public JAXBElement<SimpleClientEventData.OperationModeSchedule> createSimpleClientEventDataOperationModeSchedule(SimpleClientEventData.OperationModeSchedule value) {
        return new JAXBElement<SimpleClientEventData.OperationModeSchedule>(_SimpleClientEventDataOperationModeSchedule_QNAME, SimpleClientEventData.OperationModeSchedule.class, SimpleClientEventData.class, value);
    }

}