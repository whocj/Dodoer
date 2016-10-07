package com.summer.whm.spider.distributed.utils;

import java.lang.reflect.*;
import java.util.*;

/**
 * @author <a href="mailto:ljw79618@gmail.com">L.J.W</a>
 */
public abstract class ClassUtils {

    /**
     * Suffix for array class names: "[]"
     */
    public static final String ARRAY_SUFFIX = "[]";

    /**
     * Prefix for internal array class names: "[L"
     */
    private static final String INTERNAL_ARRAY_PREFIX = "[L";


    /**
     * Map with primitive wrapper type as key and corresponding primitive
     * type as value, for example: Integer.class -> int.class.
     */
    private static final Map primitiveWrapperTypeMap = new HashMap(8);

    /**
     * Map with primitive type name as key and corresponding primitive
     * type as value, for example: "int" -> "int.class".
     */
    private static final Map primitiveTypeNameMap = new HashMap(16);


    static {
        primitiveWrapperTypeMap.put(Boolean.class, boolean.class);
        primitiveWrapperTypeMap.put(Byte.class, byte.class);
        primitiveWrapperTypeMap.put(Character.class, char.class);
        primitiveWrapperTypeMap.put(Double.class, double.class);
        primitiveWrapperTypeMap.put(Float.class, float.class);
        primitiveWrapperTypeMap.put(Integer.class, int.class);
        primitiveWrapperTypeMap.put(Long.class, long.class);
        primitiveWrapperTypeMap.put(Short.class, short.class);
        Set primitiveTypeNames = new HashSet(16);
        primitiveTypeNames.addAll(primitiveWrapperTypeMap.values());
        primitiveTypeNames.addAll(Arrays.asList(new Class[]{
                boolean[].class, byte[].class, char[].class, double[].class,
                float[].class, int[].class, long[].class, short[].class}));
        for (Iterator it = primitiveTypeNames.iterator(); it.hasNext(); ) {
            Class primitiveClass = (Class) it.next();
            primitiveTypeNameMap.put(primitiveClass.getName(), primitiveClass);
        }
    }


    /**
     * Return the default ClassLoader to use: typically the thread context
     * ClassLoader, if available; the ClassLoader that loaded the ClassUtils
     * class will be used as fallback.
     * <p>Call this method if you intend to use the thread context ClassLoader
     * in a scenario where you absolutely need a non-null ClassLoader reference:
     * for example, for class path resource loading (but not necessarily for
     * <code>Class.forName</code>, which accepts a <code>null</code> ClassLoader
     * reference as well).
     *
     * @return the default ClassLoader (never <code>null</code>)
     * @see java.lang.Thread#getContextClassLoader()
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back to system class loader...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }

    /**
     * Override the thread context ClassLoader with the environment's bean ClassLoader
     * if necessary, i.e. if the bean ClassLoader is not equivalent to the thread
     * context ClassLoader already.
     *
     * @param classLoaderToUse the actual ClassLoader to use for the thread context
     * @return the original thread context ClassLoader, or <code>null</code> if not overridden
     */
    public static ClassLoader overrideThreadContextClassLoader(ClassLoader classLoaderToUse) {
        Thread currentThread = Thread.currentThread();
        ClassLoader threadContextClassLoader = currentThread.getContextClassLoader();
        if (classLoaderToUse != null && !classLoaderToUse.equals(threadContextClassLoader)) {
            currentThread.setContextClassLoader(classLoaderToUse);
            return threadContextClassLoader;
        } else {
            return null;
        }
    }

    /**
     * Replacement for <code>Class.forName()</code> that also returns Class instances
     * for primitives (like "int") and array class names (like "String[]").
     * <p>Always uses the default class loader: that is, preferably the thread context
     * class loader, or the ClassLoader that loaded the ClassUtils class as fallback.
     *
     * @param name the name of the Class
     * @return Class instance for the supplied name
     * @throws ClassNotFoundException if the class was not found
     * @throws LinkageError           if the class file could not be loaded
     * @see Class#forName(String, boolean, ClassLoader)
     * @see #getDefaultClassLoader()
     */
    public static Class forName(String name) throws ClassNotFoundException, LinkageError {
        return forName(name, getDefaultClassLoader());
    }

    /**
     * Replacement for <code>Class.forName()</code> that also returns Class instances
     * for primitives (like "int") and array class names (like "String[]").
     *
     * @param name        the name of the Class
     * @param classLoader the class loader to use
     *                    (may be <code>null</code>, which indicates the default class loader)
     * @return Class instance for the supplied name
     * @throws ClassNotFoundException if the class was not found
     * @throws LinkageError           if the class file could not be loaded
     * @see Class#forName(String, boolean, ClassLoader)
     */
    public static Class forName(String name, ClassLoader classLoader) throws ClassNotFoundException, LinkageError {
        Class clazz = resolvePrimitiveClassName(name);
        if (clazz != null) {
            return clazz;
        }
        // "java.lang.String[]" style arrays
        if (name.endsWith(ARRAY_SUFFIX)) {
            String elementClassName = name.substring(0, name.length() - ARRAY_SUFFIX.length());
            Class elementClass = forName(elementClassName, classLoader);
            return Array.newInstance(elementClass, 0).getClass();
        }
        // "[Ljava.lang.String;" style arrays
        int internalArrayMarker = name.indexOf(INTERNAL_ARRAY_PREFIX);
        if (internalArrayMarker != -1 && name.endsWith(";")) {
            String elementClassName = null;
            if (internalArrayMarker == 0) {
                elementClassName = name.substring(INTERNAL_ARRAY_PREFIX.length(), name.length() - 1);
            } else if (name.startsWith("[")) {
                elementClassName = name.substring(1);
            }
            Class elementClass = forName(elementClassName, classLoader);
            return Array.newInstance(elementClass, 0).getClass();
        }
        ClassLoader classLoaderToUse = classLoader;
        if (classLoaderToUse == null) {
            classLoaderToUse = getDefaultClassLoader();
        }
        return classLoaderToUse.loadClass(name);
    }

    /**
     * Resolve the given class name into a Class instance. Supports
     * primitives (like "int") and array class names (like "String[]").
     * <p>This is effectively equivalent to the <code>forName</code>
     * method with the same arguments, with the only difference being
     * the exceptions thrown in case of class loading failure.
     *
     * @param className   the name of the Class
     * @param classLoader the class loader to use
     *                    (may be <code>null</code>, which indicates the default class loader)
     * @return Class instance for the supplied name
     * @throws IllegalArgumentException if the class name was not resolvable
     *                                  (that is, the class could not be found or the class file could not be loaded)
     * @see #forName(String, ClassLoader)
     */
    public static Class resolveClassName(String className, ClassLoader classLoader) throws IllegalArgumentException {
        try {
            return forName(className, classLoader);
        } catch (ClassNotFoundException ex) {
            IllegalArgumentException iae = new IllegalArgumentException("Cannot find class [" + className + "]");
            iae.initCause(ex);
            throw iae;
        } catch (LinkageError ex) {
            IllegalArgumentException iae = new IllegalArgumentException(
                    "Error loading class [" + className + "]: problem with class file or dependent class.");
            iae.initCause(ex);
            throw iae;
        }
    }

    /**
     * Resolve the given class name as primitive class, if appropriate,
     * according to the JVM's naming rules for primitive classes.
     * <p>Also supports the JVM's internal class names for primitive arrays.
     * Does <i>not</i> support the "[]" suffix notation for primitive arrays;
     * this is only supported by {@link #forName}.
     *
     * @param name the name of the potentially primitive class
     * @return the primitive class, or <code>null</code> if the name does not denote
     * a primitive class or primitive array class
     */
    public static Class resolvePrimitiveClassName(String name) {
        Class result = null;
        // Most class names will be quite long, considering that they
        // SHOULD sit in a package, so a length check is worthwhile.
        if (name != null && name.length() <= 8) {
            // Could be a primitive - likely.
            result = (Class) primitiveTypeNameMap.get(name);
        }
        return result;
    }

    /**
     * Return all interfaces that the given instance implements as array,
     * including ones implemented by superclasses.
     * @param instance the instance to analyze for interfaces
     * @return all interfaces that the given instance implements as array
     */
    public static Class[] getAllInterfaces(Object instance) {
        return getAllInterfacesForClass(instance.getClass());
    }

    /**
     * Return all interfaces that the given class implements as array,
     * including ones implemented by superclasses.
     * <p>If the class itself is an interface, it gets returned as sole interface.
     * @param clazz the class to analyze for interfaces
     * @return all interfaces that the given object implements as array
     */
    public static Class<?>[] getAllInterfacesForClass(Class<?> clazz) {
        return getAllInterfacesForClass(clazz, null);
    }

    /**
     * Return all interfaces that the given class implements as array,
     * including ones implemented by superclasses.
     * <p>If the class itself is an interface, it gets returned as sole interface.
     * @param clazz the class to analyze for interfaces
     * @param classLoader the ClassLoader that the interfaces need to be visible in
     * (may be <code>null</code> when accepting all declared interfaces)
     * @return all interfaces that the given object implements as array
     */
    public static Class<?>[] getAllInterfacesForClass(Class<?> clazz, ClassLoader classLoader) {
        Set<Class> ifcs = getAllInterfacesForClassAsSet(clazz, classLoader);
        return ifcs.toArray(new Class[ifcs.size()]);
    }

    /**
     * Return all interfaces that the given instance implements as Set,
     * including ones implemented by superclasses.
     * @param instance the instance to analyze for interfaces
     * @return all interfaces that the given instance implements as Set
     */
    public static Set<Class> getAllInterfacesAsSet(Object instance) {
        return getAllInterfacesForClassAsSet(instance.getClass());
    }

    /**
     * Return all interfaces that the given class implements as Set,
     * including ones implemented by superclasses.
     * <p>If the class itself is an interface, it gets returned as sole interface.
     * @param clazz the class to analyze for interfaces
     * @return all interfaces that the given object implements as Set
     */
    public static Set<Class> getAllInterfacesForClassAsSet(Class clazz) {
        return getAllInterfacesForClassAsSet(clazz, null);
    }

    /**
     * Return all interfaces that the given class implements as Set,
     * including ones implemented by superclasses.
     * <p>If the class itself is an interface, it gets returned as sole interface.
     * @param clazz the class to analyze for interfaces
     * @param classLoader the ClassLoader that the interfaces need to be visible in
     * (may be <code>null</code> when accepting all declared interfaces)
     * @return all interfaces that the given object implements as Set
     */
    public static Set<Class> getAllInterfacesForClassAsSet(Class clazz, ClassLoader classLoader) {
        if (clazz.isInterface() && isVisible(clazz, classLoader)) {
            return Collections.singleton(clazz);
        }
        Set<Class> interfaces = new LinkedHashSet<Class>();
        while (clazz != null) {
            Class<?>[] ifcs = clazz.getInterfaces();
            for (Class<?> ifc : ifcs) {
                interfaces.addAll(getAllInterfacesForClassAsSet(ifc, classLoader));
            }
            clazz = clazz.getSuperclass();
        }
        return interfaces;
    }

    /**
     * Check whether the given class is visible in the given ClassLoader.
     * @param clazz the class to check (typically an interface)
     * @param classLoader the ClassLoader to check against (may be <code>null</code>,
     * in which case this method will always return <code>true</code>)
     */
    public static boolean isVisible(Class<?> clazz, ClassLoader classLoader) {
        if (classLoader == null) {
            return true;
        }
        try {
            Class<?> actualClass = classLoader.loadClass(clazz.getName());
            return (clazz == actualClass);
            // Else: different interface class found...
        }
        catch (ClassNotFoundException ex) {
            // No interface class found...
            return false;
        }
    }
}

