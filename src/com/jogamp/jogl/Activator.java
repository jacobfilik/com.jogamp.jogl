package com.jogamp.jogl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.swt.awt.SWT_AWT;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.jogamp.common.util.JarUtil;

public class Activator implements BundleActivator {
		 
		 // The shared instance
		 private static Activator plugin;
		 
		 /**
		  * Returns the shared instance
		  *
		  * @return the shared instance
		  */
		 public static Activator getDefault() {
		  return plugin;
		 }
		 
		 /**
		  * The constructor
		  */
		 public Activator() {
		 }
		 @Override
		 public void start(BundleContext context) throws Exception {
		  if ("Mac OS X".equals(System.getProperty("os.name"))) {
		   System.out.println("Set SWT_AWT.embeddedFrameClass");
		   SWT_AWT.embeddedFrameClass = "sun.lwawt.macosx.CViewEmbeddedFrame";
		  }
		  
		  JarUtil.setResolver(new JarUtil.Resolver() {
			  @Override
			  public URL resolve(URL url) {
				  try {
					  // System.out.println("before resolution: " + url.toString());
					  URL urlUnresolved = FileLocator.resolve(url);
					  URL urlResolved = new URI(urlUnresolved.getProtocol(), urlUnresolved.getPath(), null)
							  .toURL();
					  // System.out.println("after resolution: " + urlResolved.toString());
					  return (urlResolved);
				  } catch (IOException ioexception) {
					  return (url);
				  } catch (URISyntaxException e) {
					  return (url);
				  }
			  }
		  });
		  plugin = this;
		 }
		 
		 @Override
		 public void stop(BundleContext context) throws Exception {
		  plugin = null;
		 }
		 
		}