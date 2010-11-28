package com.bulain.common.util;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class Util {
	private static final String COMA = "_";
	
	private Util(){}
	
	public static String getSessionKey(Class actionClz, Class modelClz){
		return getShortClassName(actionClz) + COMA + getShortClassName(modelClz);
	}
	
	public static String getCacheKey(Class clz){
		return getShortClassName(clz);
	}
	
	public static String getShortClassName(Class clz){
		String clsName = clz.getName();
		int index = clsName.lastIndexOf(".");
		return clsName.substring(index+1);
	}
	
	/**
	 * Page List
	 * 
	 * @param curr
	 * @param total
	 * @return
	 */
	public static List<Integer> getPageList(int curr, int total){
		List<Integer> listLeft = new ArrayList<Integer>();
		List<Integer> listRight = new ArrayList<Integer>();

		int left = Math.max(1, curr-4);
		int right = Math.min(curr+4, total);
		
		for(int i=left; i<curr; i++){
			listLeft.add(i);
		}
		for(int i=curr; i<=right; i++){
			listRight.add(i);
		}
		
		if(listLeft.size()<4 && listRight.size()>=5){
			int subLeft = Math.min(right, total);
			int subRight = Math.min(curr+8-listLeft.size(), total);
			
			for(int i=subLeft+1; i<=subRight; i++){
				listRight.add(i);
			}
		}
		
		if(listRight.size()<5 && listLeft.size()>=4){
			int subLeft = Math.max(curr-9+listRight.size(), 1);
			int subRight = Math.max(left, 1);
			for(int i=subRight-1; i>=subLeft; i--){
				listLeft.add(0, i);
			}
		}
		
		
		if(listLeft.size()>0){
			int tmp = ((Integer)listLeft.get(0)).intValue();
			if(tmp-1<4){
				for(int i=tmp-1; i>=1; i--){
					listLeft.add(0, i);
				}
			}else{
				listLeft.add(0, -1);
				listLeft.add(0, 2);
				listLeft.add(0, 1);
			}
		}
		
		if(listRight.size()>0){
			int tmp = ((Integer)listRight.get(listRight.size()-1)).intValue();
			if(total-tmp<4){
				for(int i=tmp+1; i<=total; i++){
					listRight.add(i);
				}
			}else{
				listRight.add(-1);
				listRight.add(total-1);
				listRight.add(total);
			}
		}
		
		listLeft.addAll(listRight);
		
		return listLeft;
	}
}
