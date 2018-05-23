
package br.edu.unifei.user;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ServiceServer", targetNamespace = "http://testeServerInt.unifei.edu.br/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ServiceServer {


    /**
     * 
     * @param arg0
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "syncBoard", targetNamespace = "http://testeServerInt.unifei.edu.br/", className = "br.edu.unifei.user.SyncBoard")
    @ResponseWrapper(localName = "syncBoardResponse", targetNamespace = "http://testeServerInt.unifei.edu.br/", className = "br.edu.unifei.user.SyncBoardResponse")
    @Action(input = "http://testeServerInt.unifei.edu.br/ServiceServer/syncBoardRequest", output = "http://testeServerInt.unifei.edu.br/ServiceServer/syncBoardResponse")
    public int syncBoard(
        @WebParam(name = "arg0", targetNamespace = "")
        List<Integer> arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "processClick", targetNamespace = "http://testeServerInt.unifei.edu.br/", className = "br.edu.unifei.user.ProcessClick")
    @ResponseWrapper(localName = "processClickResponse", targetNamespace = "http://testeServerInt.unifei.edu.br/", className = "br.edu.unifei.user.ProcessClickResponse")
    @Action(input = "http://testeServerInt.unifei.edu.br/ServiceServer/processClickRequest", output = "http://testeServerInt.unifei.edu.br/ServiceServer/processClickResponse")
    public int processClick(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1);

}
