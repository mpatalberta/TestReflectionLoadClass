package com.pat.example3;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class TestReflectionLoadClass {

	public static void main(String[] args) {
		System.out.println("TestReflectionLoadClass");
		
		
		Class myruntimeclass = null;

		myruntimeclass = LoadClassFromJar("/Users/mpatalberta/myproj/classes/myclass.jar"
					                     ,"com.pat.example2.MyRunTimeClass");
		if(myruntimeclass!=null)
        {
        	System.out.println("Class has been loaded");
        }
        else
        {
        	System.out.println("unable to load class");
        	return;
        }
		
		
		// create the object with the default constructor
		
		Object myruntimeclassobj = null;
	    Field f=null;
       try {
		myruntimeclassobj = myruntimeclass.newInstance();
		// object constructed now find a set method with parameter of a string
			Class c;
			c = myruntimeclassobj.getClass();
			String s1;
			
			s1 = doSet(c,myruntimeclassobj,"hellopatty");
			System.out.println("afterset>"+s1+"<");
			s1 = doGet(c,myruntimeclassobj);
			System.out.println("afterget>"+s1+"<");
			
       } catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
       } catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
       }
		
       
       
       
        // example create the object at runtime with a string passed in on the constructor       
		Object myruntimeclassobj2 = null;
   	   
			try {
				String s1;
				Class c;
				
				myruntimeclassobj2 = myruntimeclass.getConstructor(String.class).newInstance("Hello Pat");
				c = myruntimeclassobj2.getClass();
				
				s1 = doGet(c,myruntimeclassobj2);
				System.out.println("afterget>"+s1+"<");
				
				s1 = doSet(c,myruntimeclassobj2,"hellopatty");
				System.out.println("afterset>"+s1+"<");
				
				s1 = doGet(c,myruntimeclassobj2);
				System.out.println("afterget>"+s1+"<");
				
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// object constructed now find a set method with parameter of a string
			
	    // repeat loading the class itself
		 ClassLoader parentClassLoader = MyClassLoader.class.getClassLoader();
		    MyClassLoader classLoader = new MyClassLoader(parentClassLoader
		    		,"/Users/mpatalberta/bldjava/com/pat/example2/MyRunTimeClass.class");
		    myruntimeclass = null;
		    try {
				myruntimeclass = classLoader.loadClass("com.pat.example4.MyRunTimeClass");
			
		    } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
		    if(myruntimeclass != null)
		    {
		    	System.out.println("able to load the class");
				// create the object with the default constructor
				
				Object myruntimeclassobj4 = null;

		       try {
				myruntimeclassobj4 = myruntimeclass.newInstance();
				// object constructed now find a set method with parameter of a string
					Class c;
					c = myruntimeclassobj4.getClass();
					String s1;
					
					s1 = doSet(c,myruntimeclassobj4,"hellopatty");
					System.out.println("afterset>"+s1+"<");
					s1 = doGet(c,myruntimeclassobj4);
					System.out.println("afterget>"+s1+"<");
					
		       } catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		       } catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		       }	
		       
		       // now do another loading
				try {
					String s1;
					Class c;
					
					myruntimeclassobj4 = myruntimeclass.getConstructor(String.class).newInstance("Hello Pat");
					c = myruntimeclassobj4.getClass();
					
					s1 = doGet(c,myruntimeclassobj4);
					System.out.println("afterget>"+s1+"<");
					
					s1 = doSet(c,myruntimeclassobj4,"hellopatty");
					System.out.println("afterset>"+s1+"<");
					
					s1 = doGet(c,myruntimeclassobj4);
					System.out.println("afterget>"+s1+"<");
					
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		       
	    	
		    	
		    }
		    else
		    {
		    	System.out.println("unable to load the class file");
		    }
		    

			
	   
	   
	   System.out.println("exiting");
		


	}

	
	// dynamically load a jar file
	static public Class LoadClassFromJar(String pathtojar,String myclass)
	{
		
	
		File file  = new File(pathtojar);
		URL url=null;
		try {
			url = file.toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		URL[] urls = new URL[]{url};
		ClassLoader cl = new URLClassLoader(urls);

		Class cls = null;
		try {
			cls = cl.loadClass(myclass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	 return cls;
	}
   


	public static String doGet(Class c, Object obj) {
		String sRes =null;
		Method[] m;
		m = c.getMethods();
		for(int i = 0;i<m.length;i++)
		{	

            	if(m[i].getName().matches("Get"))
            	{
						try {
							sRes = (String)m[i].invoke(obj);
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
            	}

		}
		return sRes;
	}

	public static String doSet(Class c,Object obj ,String s)
	{
		String sRes =null;
		Method[] m;
		m = c.getMethods();
		for(int i = 0;i<m.length;i++)
		{	

            	if(m[i].getName().matches("Set"))
            	{
						try {
							sRes = (String)m[i].invoke(obj, "hellopatty");
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
            	}

		}
		
		return sRes;
	}
   
   

}
