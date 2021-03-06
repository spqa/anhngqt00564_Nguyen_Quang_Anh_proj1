package org.database;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class DataAccess
{
	protected File root;
	protected String extension=".xlsx";
	public DataAccess(File r) 
	{
		root = r;
	}
	public DataAccess(){
		
	}
		
	public<T1> List<T1> readList(String name, Class<T1> cl) throws Exception 
	{
		File f = new File(root.getAbsolutePath() + "/" + name + extension);
		return readList(f, cl);
	}
	
	@SuppressWarnings("unchecked")
	public<T1> List<T1> readList(File f, Class<T1> cl) throws Exception
	{
		List<T1> items = new ArrayList<T1>();
		
		for(Object s: readList(f))
		{
			Map<String, Object> sjj = (Map<String, Object>)s;
			T1 tjj = cl.newInstance();
			for(String k: sjj.keySet()) 
			{
				Object vk = sjj.get(k);
				cl.getField(k).set(tjj, vk);
			}
			
			items.add(tjj);
		}
		
		return items;
	}	

	public abstract List<Object> readList(File f) throws Exception; 

	public abstract<T1> void writeList(List<T1> items, File f) throws Exception;



	
}
