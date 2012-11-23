package ru.yandex.qatools.htmlelements.pagefactory;

/*
Copyright 2007-2009 Selenium committers

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

import java.lang.reflect.Field;

import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * A factory for producing {@link ElementLocator}s. It is expected that a new ElementLocator will be
 * returned per call.
 */
public abstract class CustomElementLocatorFactory implements ElementLocatorFactory {
	/**
	 * When a field on a class needs to be decorated with an {@link ElementLocator} this method will
	 * be called.
	 * 
	 * @param field
	 * @return An ElementLocator object.
	 */
	public ElementLocator createLocator(Field field) {
		return null;
	}
	
	public ElementLocator createLocator(Class clazz) {
		return null;
	}
}