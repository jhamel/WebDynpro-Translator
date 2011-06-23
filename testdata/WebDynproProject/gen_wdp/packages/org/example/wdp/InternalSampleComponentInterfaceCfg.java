// ---------------------------------------------------------------------------
// This file has been generated by the Web Dynpro Code Generator
// DON'T MODIFY!!! CHANGES WILL BE LOST WHENEVER THE FILE GETS GENERATED AGAIN
// ---------------------------------------------------------------------------
package org.example.wdp;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.sap.tc.logging.Location;
import com.sap.tc.webdynpro.progmodel.api.*;
import com.sap.tc.webdynpro.progmodel.gci.*;
import com.sap.tc.webdynpro.progmodel.context.*;
import com.sap.tc.webdynpro.progmodel.gci.*;
import com.sap.tc.webdynpro.services.exceptions.WDRuntimeException;

public class InternalSampleComponentInterfaceCfg
  implements IPrivateSampleComponentInterfaceCfg, com.sap.tc.webdynpro.progmodel.gci.IGCICustomControllerDelegate
{

  /**
   * Location for this controller.
   */
  private static final Location logger = Location.getLocation(InternalSampleComponentInterfaceCfg.class);

  /**
   * Framework implementation of Controller that delegates to this.
   */
  private final com.sap.tc.webdynpro.progmodel.gci.IGCICustomController wdAlterEgo;

  /**
   * Delegate that implements application defined logic
   */
  private final org.example.SampleComponentInterfaceCfg delegate;


  // ---- Context --------------------------------------------------------------

  IGCINodeInfo infoContext;

  {

    infoContext = GCIContext.createNode("Context", null, true, true, false, true, false, true, null,
      (IGCIAttributeInfo[])null, // no attributes
      (IGCINodeInfo[])null // no child nodes
    );

  }
  
  private IContextNode contextNode;

  private void wdInitContextNode() {
    contextNode = new IContextNode(this, infoContext, (Node)null);
  }

  public IWDNode wdGetRootNode() {
    return contextNode;
  }

  public IContextNode wdGetContext() {
    return contextNode;
  }


  // ---- Actions --------------------------------------------------------------

  /**
   * Create a new action for this controller. A unique name for the action is generated automatially.
   * @param eventHandler is the action's eventhandler with proper signature
   * @param text is the text displayed in the UI element triggering this action
   */
  public IWDAction wdCreateAction(WDActionEventHandler eventHandler, String text) {
    return wdAlterEgo.createAction(null, eventHandler, text, null);
  }

  /**
   * Create a new action with the given name for this controller
   * @param eventHandler is the action's eventhandler with proper signature
   * @param name is the action's name
   * @param text is the text displayed in the UI element triggering this action
   */
  public IWDAction wdCreateNamedAction(WDActionEventHandler eventHandler, String name, String text) {
    return wdAlterEgo.createAction(name, eventHandler, text, null);
  }

  // ---- Controller part ------------------------------------------------------

  /**
   * Creates a new instance of this controller.
   */
  public InternalSampleComponentInterfaceCfg(com.sap.tc.webdynpro.progmodel.gci.IGCICustomController alterEgo) {
    this.wdAlterEgo = alterEgo;
    wdInitContextNode();
    this.delegate = new org.example.SampleComponentInterfaceCfg((IPrivateSampleComponentInterfaceCfg) this);
  }

  /**
   * Returns the public API for this controller instance.
   */
  public com.sap.tc.webdynpro.progmodel.api.IWDController wdGetAPI() {
    return (com.sap.tc.webdynpro.progmodel.api.IWDController) wdAlterEgo;
  }

  /**
   * Hook method called to initialize component_configuration controller.
   */
  public void wdDoInit() {
    logger.pathT("entering: wdDoInit");
    delegate.wdDoInit();
    logger.pathT("exiting: wdDoInit"); 
  }

  /**
   * Hook method called to clean up component_configuration controller.
   */
  public void wdDoExit() {
    logger.pathT("entering: wdDoExit");
    delegate.wdDoExit();
    logger.pathT("exiting: wdDoExit"); 
  }

  public Object wdInvokeEventHandler(String handlerName, IWDCustomEvent event)
    throws NoSuchMethodException {
    
    logger.pathT("entering: wdInvokeEventHandler", new Object[] { handlerName } );
    try {
      throw new NoSuchMethodException("Eventhandler " + handlerName + " not found for event " + event.getName());
    } finally {
      logger.pathT("exiting: wdInvokeEventHandler"); 
    }
  }


  /** external API of controller */
  private External wdExternalInterface;
  
  /** return external API of controller */
  public com.sap.tc.webdynpro.progmodel.api.IWDExternalControllerInterface wdGetExternalInterface() {
    if (wdExternalInterface==null) {
      wdExternalInterface = new External();
    }
    return wdExternalInterface;
  }
  
  private class External
    implements com.sap.tc.webdynpro.progmodel.api.IWDExternalControllerInterface, IExternalSampleComponentInterfaceCfg
  {

    public IWDController wdGetAPI() {
      return wdAlterEgo.getExternalAPI();
    }
    
  }
 }
