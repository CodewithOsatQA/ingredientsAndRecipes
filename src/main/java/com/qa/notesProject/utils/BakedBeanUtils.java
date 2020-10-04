package com.qa.notesProject.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BakedBeanUtils {
	
	public static void mergeNotNUll(Object src, Object tgt) {
		BeanUtils.copyProperties(src, tgt, getNullPropertyNames(src));
		
	}
	
	private static String [] getNullPropertyNames(Object src) {
		final BeanWrapper wrappedSourceObject = new BeanWrapperImpl(src);
		
		Set<String> nameOfProperties = new HashSet<>();
		for (PropertyDescriptor propertyDescriptor : wrappedSourceObject.getPropertyDescriptors()) {
			if(wrappedSourceObject.getPropertyValue(propertyDescriptor.getName()) == null)
					nameOfProperties.add(propertyDescriptor.getName());
		}
		return nameOfProperties.toArray(new String[nameOfProperties.size()]);
	}

}
