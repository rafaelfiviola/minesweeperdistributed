
package br.edu.unifei.user;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.edu.unifei.user package. 
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

    private final static QName _ProcessClick_QNAME = new QName("http://testeServerInt.unifei.edu.br/", "processClick");
    private final static QName _SyncBoardResponse_QNAME = new QName("http://testeServerInt.unifei.edu.br/", "syncBoardResponse");
    private final static QName _SyncBoard_QNAME = new QName("http://testeServerInt.unifei.edu.br/", "syncBoard");
    private final static QName _ProcessClickResponse_QNAME = new QName("http://testeServerInt.unifei.edu.br/", "processClickResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.edu.unifei.user
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProcessClickResponse }
     * 
     */
    public ProcessClickResponse createProcessClickResponse() {
        return new ProcessClickResponse();
    }

    /**
     * Create an instance of {@link SyncBoard }
     * 
     */
    public SyncBoard createSyncBoard() {
        return new SyncBoard();
    }

    /**
     * Create an instance of {@link SyncBoardResponse }
     * 
     */
    public SyncBoardResponse createSyncBoardResponse() {
        return new SyncBoardResponse();
    }

    /**
     * Create an instance of {@link ProcessClick }
     * 
     */
    public ProcessClick createProcessClick() {
        return new ProcessClick();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessClick }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://testeServerInt.unifei.edu.br/", name = "processClick")
    public JAXBElement<ProcessClick> createProcessClick(ProcessClick value) {
        return new JAXBElement<ProcessClick>(_ProcessClick_QNAME, ProcessClick.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SyncBoardResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://testeServerInt.unifei.edu.br/", name = "syncBoardResponse")
    public JAXBElement<SyncBoardResponse> createSyncBoardResponse(SyncBoardResponse value) {
        return new JAXBElement<SyncBoardResponse>(_SyncBoardResponse_QNAME, SyncBoardResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SyncBoard }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://testeServerInt.unifei.edu.br/", name = "syncBoard")
    public JAXBElement<SyncBoard> createSyncBoard(SyncBoard value) {
        return new JAXBElement<SyncBoard>(_SyncBoard_QNAME, SyncBoard.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessClickResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://testeServerInt.unifei.edu.br/", name = "processClickResponse")
    public JAXBElement<ProcessClickResponse> createProcessClickResponse(ProcessClickResponse value) {
        return new JAXBElement<ProcessClickResponse>(_ProcessClickResponse_QNAME, ProcessClickResponse.class, null, value);
    }

}
