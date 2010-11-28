package com.bulain.common.advice;

import java.lang.reflect.Method;
import java.util.Date;

import org.springframework.aop.MethodBeforeAdvice;

import com.bulain.common.model.Entity;

public class UpdateBeforeAdvice implements MethodBeforeAdvice {
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		if(args==null)return;
		for(Object arg : args){
			if(arg instanceof Entity){
				Entity entity = (Entity)arg;
				Date date = new Date();
				entity.setUpdatedAt(date);
				entity.setUpdatedBy("updatedBy");
			}
		}
	}
}
