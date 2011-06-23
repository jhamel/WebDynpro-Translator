// ---------------------------------------------------------------------------
// This file has been generated partially by the Web Dynpro Code Generator.
// MODIFY CODE ONLY IN SECTIONS ENCLOSED BY @@begin AND @@end.
// ALL OTHER CHANGES WILL BE LOST IF THE FILE IS REGENERATED.
// ---------------------------------------------------------------------------
package org.example;

// 
// IMPORTANT NOTE: 
// _ALL_ IMPORT STATEMENTS MUST BE PLACED IN THE FOLLOWING SECTION ENCLOSED
// BY @@begin imports AND @@end. FURTHERMORE, THIS SECTION MUST ALWAYS CONTAIN
// AT LEAST ONE IMPORT STATEMENT (E.G. THAT FOR IPrivateSampleComponent).
// OTHERWISE, USING THE ECLIPSE FUNCTION "Organize Imports" FOLLOWED BY
// A WEB DYNPRO CODE GENERATION (E.G. PROJECT BUILD) WILL RESULT IN THE LOSS
// OF IMPORT STATEMENTS.
//
//@@begin imports
import org.example.wdp.IPrivateSampleComponent;
//@@end

//@@begin documentation
//@@end

public class SampleComponent
{
  /**
   * Logging location.
   */
  private static final com.sap.tc.logging.Location logger = 
    com.sap.tc.logging.Location.getLocation(SampleComponent.class);

  static 
  {
    //@@begin id
    String id = "$Id$";
    //@@end
    com.sap.tc.logging.Location.getLocation("ID.com.sap.tc.webdynpro").infoT(id);
  }

  /**
   * Private access to the generated Web Dynpro counterpart 
   * for this controller class.  </p>
   *
   * Use <code>wdThis</code> to gain typed access to the context,
   * to trigger navigation via outbound plugs, to get and enable/disable
   * actions, fire declared events, and access used controllers and/or 
   * component usages.
   *
   * @see org.example.wdp.IPrivateSampleComponent for more details
   */
  private final IPrivateSampleComponent wdThis;

  /**
   * Root node of this controller's context. </p>
   *
   * Provides typed access not only to the elements of the root node 
   * but also to all nodes in the context (methods node<i>XYZ</i>()) 
   * and their currently selected element (methods current<i>XYZ</i>Element()). 
   * It also facilitates the creation of new elements for all nodes 
   * (methods create<i>XYZ</i>Element()). </p>
   *
   * @see org.example.wdp.IPrivateSampleComponent.IContextNode for more details.
   */
  private final IPrivateSampleComponent.IContextNode wdContext;

  /**
   * A shortcut for <code>wdThis.wdGetAPI()</code>. </p>
   * 
   * Represents the generic API of the generic Web Dynpro counterpart 
   * for this controller. </p>
   */
  private final com.sap.tc.webdynpro.progmodel.api.IWDComponent wdControllerAPI;
  
  /**
   * A shortcut for <code>wdThis.wdGetAPI().getComponent()</code>. </p>
   * 
   * Represents the generic API of the Web Dynpro component this controller 
   * belongs to. Can be used to access the message manager, the window manager,
   * to add/remove event handlers and so on. </p>
   */
  private final com.sap.tc.webdynpro.progmodel.api.IWDComponent wdComponentAPI;
  
  public SampleComponent(IPrivateSampleComponent wdThis)
  {
    this.wdThis = wdThis;
    this.wdContext = wdThis.wdGetContext();
    this.wdControllerAPI = wdThis.wdGetAPI();
    this.wdComponentAPI = wdThis.wdGetAPI().getComponent();
  }

  //@@begin javadoc:wdDoInit()
  /** Hook method called to initialize controller. */
  //@@end
  public void wdDoInit()
  {
    //@@begin wdDoInit()
    //@@end
  }

  //@@begin javadoc:wdDoExit()
  /** Hook method called to clean up controller. */
  //@@end
  public void wdDoExit()
  {
    //@@begin wdDoExit()
    //@@end
  }

  //@@begin javadoc:wdDoPostProcessing()
  /**
   * Hook called to handle data retrieval errors before rendering.
   *
   * After doModifyView(), the Web Dynpro Framework gets all context data needed
   * for rendering by validating the contexts (which in turn calls the supply
   * functions and supplying relation roles). In this hook, the application
   * should handle the errors which occurred during validation of the contexts.
   * 
   * Using preorder depth-first traversal, this hook is called for all component
   * controllers starting with the current root component.
   *
   * Permitted operations:
   * - Flushing model queue
   * - Creating messages
   * - Reading context and model data
   *
   * Forbidden operations: 
   * - Invalidating model data
   * - Manipulating the context
   * - Firing outbound plugs
   * - Creating components
   * - ...   
   *
   * @param isCurrentRoot true if this is the root of the current request
   */
  //@@end
  public void wdDoPostProcessing(boolean isCurrentRoot)
  {
    //@@begin wdDoPostProcessing()
    //@@end
  }

  //@@begin javadoc:wdDoBeforeNavigation()
  /**
   * Hook before the navigation phase starts.
   *
   * This hook allows you to flush the model queue and handle any
   * errors that occur. Firing outbound plugs is allowed in this hook.
   *
   * Using preorder depth-first traversal, this hook is called for all component
   * controllers starting with the current root component.
   *
   * @param isCurrentRoot true if this is the root of the current request
   */
  //@@end
  public void wdDoBeforeNavigation(boolean isCurrentRoot)
  {
    //@@begin wdDoBeforeNavigation()
    //@@end
  }
  
  //@@begin javadoc:wdDoApplicationStateChange()
  /**
   * Hook that informs the application about a state change.
   * <p>
   * This hook is called e.g. to tell the application that will be
   * <ul>
   *  <li>left via a suspend plug and therefore should go into a suspend/sleep
   *      mode with minimal need of resources. errors that occur. Firing 
   *      outbound plugs is allowed in this hook.
   *  <li>left due to a timeout and could write it's state to a data base if the 
   *      user comes back later on
   * </ul>
   *
   * The concrete reason is available via IWDApplicationStateChangeInfo
   * <p>
   * <b>Important</b>: This hook is called for the top level component only!
   *
   * @param stateChangeInfo contains the information about the nature of the state change
   * @param stateChangeReturn allows the application to ask for a different state change. 
   *        The framework is allowed to ignore it considering i.e. the current resources situation.
   */
  //@@end
  public void wdDoApplicationStateChange(com.sap.tc.webdynpro.progmodel.api.IWDApplicationStateChangeInfo stateChangeInfo, com.sap.tc.webdynpro.progmodel.api.IWDApplicationStateChangeReturn stateChangeReturn)
  {
    //@@begin wdDoApplicationStateChange()
    //@@end
  }

  /*
   * The following code section can be used for any Java code that is 
   * not to be visible to other controllers/views or that contains constructs
   * currently not supported directly by Web Dynpro (such as inner classes or
   * member variables etc.). </p>
   *
   * Note: The content of this section is in no way managed/controlled
   * by the Web Dynpro Designtime or the Web Dynpro Runtime. 
   */
  //@@begin others
  //@@end
}
